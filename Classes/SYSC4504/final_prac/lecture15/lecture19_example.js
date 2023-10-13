//http://localhost:3000/img1.jpg
const http = require("http");
const path = require("path");
const url = require("url");
const fs = require("fs");
const process = require("process");

const server = http.createServer(function(request, response){
	
	// get file name
	let requestedFile = url.parse(request.url).pathname;
	console.log(requestedFile);
	
	// attach file name to the path
	let filename = path.join(process.cwd(), requestedFile);
	console.log(filename);

	// check if file exists
	fs.stat(filename, function(err, stats){
		if(err){
			response.writeHead(404,{"Content-Type": "text/html"});		
			response.write("<h1> 404 Error</h1>\n");
			response.write("the requested file isn't on the machine\n");
			response.write(err + "\n");
			response.end();
			return;
		}
	});

	
	// read the file
	fs.readFile(filename, "binary", function(err, file){
		if(err){
			response.writeHead(500, {"Content-Type": "text/html"});
			response.write("<h1>500 Error</h1>\n");
			response.write(err + "\n");
			response.end();
			return;
		}
		response.writeHead(200);
		response.write(file, "binary");
		response.end();
	});
	
	
	// response.writeHead(200,{"Content-Type": "text/plain"});			
// 	response.write("Hello this is our first node.js application");
// 	response.end();
});

const port = 3000;
server.listen(port);

console.log("Server running at port= " + port);