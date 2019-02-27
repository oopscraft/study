// imports modules
var express = require('express');
var path = require('path');
var mysql = require('mysql');
var ejs = require('ejs');
var fs = require('fs');

// creates app
var app = express();

// static resource
app.use('/static', express.static(path.join(__dirname,'static')));

// defines configure
global.config = {
	server: require('./conf/server.json'),
	dataSource: require('./conf/dataSource.json')
}

// creates dataSource
global.dataSource = mysql.createPool(global.config.dataSource);

// defeins global function
global.response = function(res, view, attribute){
	var file = fs.readFileSync('./views/' + view + '.ejs','utf8');
	var content = ejs.render(file, attribute);
	res.writeHeader(200,{'Content-Type':'text/html'});
	res.write(content);
	res.end();
}

// routes test
var test = require('./modules/test.js');
app.use('/test', test);

// start server
app.listen(global.config.server.port, function(){
	console.log('listeneing port[' + global.config.server.port + ']...');
});
