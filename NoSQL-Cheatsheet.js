Document Bases:
	e.g. MongoDB, Couchbase
Key/Value:
	e.g. Redis, MemcacheD
Column:
	e.g. Cassandra, HBase
Graph:
	e.g. OrientDB, Neo4j
	
	// null, boolean, numeric, String, array and object
	// From MongoDB
	// new Date()
	// Regex
	// Embeded Document
	// Object ID
	// Binary Data
	// Code
	
	"_id" : ObjectId("0 |1 |2 |3 | 4| 5| 6| 7| 8| 9| 10| 11");
				        Timestamp| Machine| PID | Increment
	$ show dbs;
	$ use dbname1;
	$ show collections;
	
	$ db.collection1.find();
	$ db.collection1.find().pretty();
	
	$ db.collection1.save((a:1, b:2, c:3));
	$ doc = {"name": "Smith"};
	$ db.collection1.insert(doc);
	$ db.purchase_orders.insertMany([{},{},{}])
	
	$ db.collection1.update({'title': 'MongoDB Overview'},
	{$set: {'title': 'MongoDB for Developers'}});
	
	$ db.collection1.remove({});
	$ db.collection1.drop();

// Aggregation
	dp.purchase_orders.insertMany([
		{product: "toothbrush", total:4.75, customer: "mike"},
		{product: "milk", total:4.75, customer: "Dave"},
		{product: "pizza", total:4.75, customer: "Tom"},
		{product: "guitar", total:4.75, customer: "mike"},
	]);
	// count
	db.purchase_orders.count({product: "toothbrush"})
	// distinct
	db.purchase_orders.distinct("product")
	// aggregate
	db.purchase_orders.aggregate(
		[
			{$match: {}},   // {$match: {customer: {$in ["Mike", "Karen"]}}},
			{$group: {_id: "$customer", total: {$sum: "$total"}} },
			{$sort: {total: -1}}
		]
	)
	// 
// Sharding	
// GridFS
// Capped Collection

// Tools bundled with MongoDB 
	- mongod: starts server 27017
	- mongo: cli based client
	- mongos: mongodb sharding routing service
	- mongostat: Monitoring-Quick overview of Running instance
	- mongotop: Monitoring-About Read and Write of an instance
	- mongorestore: Loads data from Binary DB dump
	- bsondump & mongodump: Creates Binary Dump, BSONdump makes binary dump redable
	- mongooplog: Replication frrom oplog
	- mongoexport & mongoimport: exports json or CSV format
	- mongoperf: checks Disk IO
	- mongofiles: for GridFS functionality
	
	
// =========================================Monoose for Node.js ==============================================
// ----- Connecting to mongodb
 var myDB = 'mongodb://localhost/dbName';
 mongoose.connect(myDB);
 
// ----- Creating new Model
var mongoose = require('mongoose');

var BookSchema = new mongoose.Schema({
	title: {type: String, required: true, unique: true},
	published: { type: Date, default: Date.now }
	keywords: Arrray,
	published: Boolean,
	// Referencing other documents
	author: {
		type: Schema.ObjetId, // Schema.Type.ObjectId
		ref: 'User'
	}
	// Embeding Document
	detail: {
		modelNumber: Number,
		hardcover: Boolean,
		reviews: Number,
		rank: Number
	}
});

module.exports = mongoose.model('Book', BookSchema);

// ----- Fetching Data
Book.find({})
	.exec((err, books) => {
		if (err) {
			res.send('error has occured');
		} else {
			res.json(books);
		}
	});

// ----- Fetching single data
	Book.findOne({
		_id: req.params.id
	}).exec((err, books) => {
			if (err) {
				res.send('error has occured');
			} else {
				res.json(books);
			}
	});

// ----- Creating Data
	let newBook = new Book();
	newBook.save((err, book) => {
		if(err) {
			res.send('Error Sending Book');
		} else {
			res.send(book);
		}
	});
// ----- Update data
	Book.findOneAndUpdate(_id: req.params.id, {$set: {req.body.title}, {upsert: true}, 
		(err, book) => {
			if(err) {
				res.send('Error Updating Book');
			} else {
				res.send(book);
			}
		})
	});

// ----- Delete data
	Book.findOneAndRemove(_id: req.params.id,
		(err, book) => {
			if(err) {
				res.send('Error Deleting Book');
			} else {
				res.send(book);
			}
		})
	});



// ********************************************************************** //
// Create newuser in mongodb
db.createUser( { user: "admin", pwd: "Pass@123", roles: [{role:"userAdmin", db:"cameraDB"},{role:"readWrite", db:"cameraDB"}] } )

//Bckup
mongodump --db wobot_vms
mongorestore --verbose \path\dump

// Lifecycle of mongo DB
systemctl start mongod -> Start the service

systemctl status mongod -> Status of service

systemctl restart mongod -> restart the service

systemctl reload mongod -> reload the service





	
	
	