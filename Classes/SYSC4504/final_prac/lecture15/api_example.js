const path = require("path");
const fs = require("fs");
const process = require("process");
// "npm install express"
const express = require("express");
const app = express();

// 1- find the path of the json file
const jsonPath = path.join(__dirname, 'companies.json');
console.log(jsonPath);

let companies;

fs.readFile(jsonPath, (err, data) => {
	if(err)
		console.log('Unable to read json file\n');
	else
		companies = JSON.parse(data);
});

app.get("/", (req, resp) => {resp.json(companies)});


app.get('/companies/:symbol', (req, resp) => {
	const symbolToFind = req.params.symbol.toUpperCase();
	const matches = companies.filter(obj => symbolToFind === obj.symbol);
	resp.json(matches);
});

app.get('/companies/name/:substring', (req, resp) => {
	const substring = req.params.substring.toLowerCase();
	const values = Object.values(companies);
	const matches = values.filter(obj => obj.name.toLowerCase().includes(substring));
	resp.json(matches);
});


// Use express to listen to port
let port = 8080;

app.listen(port, () => {
    console.log("Server running at port= " + port);
});

