/* -*- C++ -*- */
/* $Header: /Users/greg/Dropbox/Carleton/Courses/SYSC\0403303/Project/Elevator/RCS/SimpleEchoServer.cpp,v 1.1 2018/12/17 15:31:55 greg Exp $
 * This class is the server side of a simple echo server based on
 * UDP/IP. The server receives from a client a packet containing a character
 * string, then echoes the string back to the client.
 */

#include <iostream>
#include <string>
#include <sys/types.h> 
#include <sys/socket.h>
#include <errno.h>
#include <netinet/in.h>
#include <arpa/inet.h>

#define BUFSIZE 512
#define PORT 5000

const std::string name( "Server" );

int main( int argc, char ** argv )
{
    struct sockaddr_in server_addr;
    
    /* Create the socket. */

    int server_socket = socket( AF_INET, SOCK_DGRAM, IPPROTO_UDP );
    if ( server_socket < 0 ) {
	std::cerr << name << ": error opening socket: " << strerror( errno ) << std::endl;
	exit( 1 );
    }
    memset( (char *)&server_addr, 0, sizeof( server_addr ));
    server_addr.sin_family = AF_INET;
    server_addr.sin_addr.s_addr = htonl(INADDR_ANY);
    server_addr.sin_port = htons(PORT);

    /* Bind to the port */

    int rc = bind( server_socket, reinterpret_cast<sockaddr *>(&server_addr), sizeof( server_addr ) );
    if ( rc < 0 ) {
	std::cerr << name << ": cannot bind to socket: " << strerror( errno ) << std::endl;
	exit( 1 );
    }

    for ( ;; ) {
	struct sockaddr_in client_addr;
	socklen_t client_len = sizeof( client_addr );

	/* try to receive some data, this is a blocking call */
	
	std::cerr << name << ": waiting for data." << std::endl;
	char buf[BUFSIZE];
	int n_received = recvfrom( server_socket, buf, BUFSIZE, 0, reinterpret_cast<struct sockaddr *>(&client_addr), &client_len );
	if ( n_received < 0 ) {
	    std::cerr << name << ": recvfrom: " << strerror( errno ) << std::endl;
	    exit( 1 );
	}
		
	std::cerr << name << ": Packet received from host: " << inet_ntoa(client_addr.sin_addr) << ", port " << ntohs(client_addr.sin_port) << std::endl;
	std::cerr << name << ": Data: " << buf << std::endl;
	
	/* And transmit it back */

	int n_sent = sendto( server_socket, buf, n_received, 0, reinterpret_cast<struct sockaddr *>(&client_addr), client_len ) ;
	if ( n_sent < 0 ) {
	    std::cerr << name << ": sendto: " << strerror( errno ) << std::endl;
	}
    }
}
