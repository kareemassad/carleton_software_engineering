/* -*- C++ -*- */
/* $Header: /Users/greg/Dropbox/Carleton/Courses/SYSC\0403303/Project/Elevator/RCS/SimpleEchoClient.cpp,v 1.1 2018/12/17 15:31:55 greg Exp $
 * Simple UDP client
 */

#include <iostream>
#include <string>
#include <sstream>
#include <cstdlib>
#include <errno.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/socket.h>

#define SERVER_IP_ADDRESS  "127.0.0.1"
#define BUFSIZE 512	//Max length of buffer
#define PORT 5000	//The port on which to send data

const std::string name( "Client" );

int main(void)
{
    struct sockaddr_in server_addr;
    socklen_t server_len = sizeof( server_addr );

    int client_socket = socket( AF_INET, SOCK_DGRAM, IPPROTO_UDP );
    if ( client_socket < 0 ) {
	std::cerr << name << ": Cannot open socket: " << strerror( errno );
	exit( 1 );
    }

    memset((char *) &server_addr, 0, sizeof(server_addr));
    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(PORT);
	
    inet_aton( SERVER_IP_ADDRESS, &server_addr.sin_addr );

    for ( unsigned int i = 0; i < 10; ++i ) {
	std::ostringstream message;
	message << "Packet " << i;
	const std::string& data = message.str();

	std::cerr << name << ": Sending: " << data << std::endl;
	
	/* Send the message (send the NULL too.) */
	int n_sent = sendto( client_socket, data.c_str(), data.size() + 1, 0, reinterpret_cast<struct sockaddr *>(&server_addr), server_len );
	if ( n_sent < 0 ) {
	    std::cerr << name << ": sendto: " << strerror( errno ) << std::endl;
	    exit( 1 );
	}
		
	/* Receive the reply and print it */

	char buf[BUFSIZE];

	int n_received = recvfrom( client_socket, buf, BUFSIZE, 0, reinterpret_cast<struct sockaddr *>(&server_addr), &server_len );
	if ( n_received < 0 ) {
	    std::cerr << name << ": recvfrom: " << strerror( errno ) << std::endl;
	    exit( 1 );
	} else {
	    buf[n_received] = '\0';
	}

	std::cerr << name << ": Received: " << buf << std::endl;
    }

    close( client_socket );
    exit( 0 );
}
