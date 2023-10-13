const mysql = require('mysql');

const con = mysql.createConnection({
  host: "localhost",
  user: "root",
  password: "",
  database: 'Lecture_Example'
});

con.connect(function(err) {
  if (err) throw err;
  con.query("SELECT * FROM ORDER_ITEM", function (err, result, fields) {
  	if (err) throw err;
  	console.log(result);
  });
  con.query("SELECT OrderNumber, SKU FROM ORDER_ITEM", function (err, result, fields) {
  	if (err) throw err;
  	console.log(fields);
  });
});

