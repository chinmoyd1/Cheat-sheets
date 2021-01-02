// ==============================Event Listener==========================
// --------------Events---------------
const EventEmitter = require('events');

const Logger = require('./logger');
const logger = new Logger();

// Register a listener
logger.on('messageLogged', (arg) => {
	console.log('Listener called', arg);
});

logger.log('message');

// -------------Logger-----------------
const EventEmitter = require('events');

class Logger extends EventEmitter{
	log(message) {
		console.log(message);
		this.emit('messageLogged', { id: 1, url: 'http://...'});
	}
}
module.exports = Logger;

// =============================http module==============================
const http = require('http');

const server = http.createServer((req, res) => {
	if (req.url === '/') {
		res.write('Hello World');
		res.end();
	}
	if (req.url === '/api/courses') {
		res.write(JSON.stringify([1, 2, 3]));
		res.end();
	}
});

server.listen(3000);
console.log('Listening on port 3000...');


// ============================Express====================================
const express = require('express);
const app =express();

app.use(express.json()); // will set to req.body property

app.get('/', (req, res) => {
	res.send('Hello world!');
});

app.get('/api/courses/:id', (req, res) => {
	res.send(req.params.id);  
	// for query stringify
	res.send(req.query);
	//res.status(404).send('not found):
});

app.post('/api/corses', (req, res) => {
	if (req.body.name || req.body.name.length < 3){ //validation
			return res.status(400).send('name is required');
	}
		
	const course = {
		const course = {
			id: course.length + 1,
			name: req.body.name //add express.json() middlewear to able to parse json files
		};
		courses.push(course);
		res.send(course);
	}
})

const PORT = process.env.PORT || 3000;
app.listen(PORT, console.log(`listening onn port ${PORT}`));


// ===========================Middlewear==============================
// 			 REQUEST PROCESSING PIPELINE
// request --->json()--->route(req, res)---> response

app.use(function(req, res, next){
	console.log("Logging...");
	next();
});

// for x-www-from-urlencoded which is used for POST from
app.use(express.urlencoded({ extended: true })); // will set to req.body property

// for serving static files
app.use(express.static('public'));

// for passing values in middlewear
app.get('/test', 
  function(req, res, next) { 
    res.locals.custom   = true;
    res.locals.myObject = { hello: 1 };
    next(); 
  }, 
  function(req, res) { 
    console.log('See:', res.locals.custom, res.locals.myObject); 
    return res.status(200).send(res.locals); 
  });
  
// Middlewear Error Hndling
app.get('/', function(req, res){
	var err = new Error("Something went wrong");
	next(err);
});

app.use(funtion(err, req, res, next){
	res.status(500);
	res.send("Oops, something went wrong.");
});

// ========================Connecting-to-MONGO============================
// Schema Types: String, Number, Date, Buffer, Boolean, ObjectID, Array
const mongoose = require('mongoose');

mongoose.connect('mongodb://localhost/playground')
	.then(() => console.log('Connected to MongDB...'))
	.catch(err => console.error('Could not connect to db...', err));

const courseSchema = new mongoose.Schema({
	name: String,
	author: String,
	tags: [ String ],
	date: Date,
	date: { type: Date, default: Date.now },
	isPublised: Boolean
});

const Course = mongoose.model('Course', courseSchema);

async function createCourse() {
	const course = new Course({
		name: 'Node.js Course',
		author: 'Mosh',
		tags: ['node','backend'],
		isPublished: true
	});
	const result = await course.save();
};
async function getCourse() {
	const courses = await Course.find(); // or const courses = await Course.find({ author: 'Mosh' })
										 //.limit(1).sort({name: 1//-1})
										 //.select({ name: 1, tags: 1});
};
async function updateCourse(id) {
	// Approach 1: Query First --> findById()-->modify its properties-->save()
	const course = await Course.findById(id);
	corse.set({ isPublished: true, author: '12' });
	corse.save();
	
	// Approach 2: Update First --> updateDirectly()
	const course = await Course.update({ isPublished: false }); // for bulk
	// Mogo db update opertors $currentDate, $inc, $min, $max, $mul, $rename, $set, $unset
	const result = await Course.update({ _id: id }, {  // for individual
		$set: {
			author: 'newAuthor',
			isPublished: false
		} 
	});  
	// --------------- or -----------------
	const course = await Course.findByIdAndUpdate( id, {  // for individual
		$set: {
			author: 'newAuthor',
			isPublished: false
		}
	}, {new: true}); // to return updated document not the old one
};
async function removeCourse(id){
	const result = Course.deleteOne( {_id: id} );
	// Course.deleteMany( {isPublished: false} );
	// to get deleted course
	const course = await Course.findByIdAndRemove(id);
};




// --------- Comparison Operators ----------
// eq, ne, gt, gte , lt, lte, in, nin
await Course.find({ price: {$gt: 10} });
await Course.find({ price: {$gte: 10, $lte: 20} });
await Course.find({ price: {$in: [10, 20, 30]} });

// ---------- Logical Operators ------------
// or, and
await Course.find().or([ {author: 'Mosh'}, {isPublished: true} ]);
await Course.find().and([ {author: 'Mosh'}, {isPublished: true} ]);

// ------------ Counting -------------
await Course.find().count();

// ------------ Pagination -----------
await Course.find().skip((pageNumber - 1) * pageSize).limit(pageSize);

// **validations in mongoose
	const movie = mongoose.model('Movies', new mongoose.Schema({
		title: {
			type: String, //Number
			required: true,
			trim: true,
			minLength: 5,
			maxLength: 255
		},
		genre: {
			type: genreSchema,
			required: true
		}
	}));

// ======================== Modelling Relationship =========================
// Using References (Normalization) --> CONSISTENCY 
// **There is no refrence between 2 docs
	let author = {
		_id: 'asd8793bj34df354345df1r1rsdf'
	}
	let course = {
		author: 'asd8793bj34df354345df1r1rsdf'
	}
	// -----------------code-------------------
	const Course = mongoose.model('Course', new mongoose.Schema({
		name: String,
		author: {
			type: mongoose.Schema.Types.ObjectId,
			ref: 'Author'
		}
	}));
	
	// ----------------viewing document--------------
	async function listCourses() {
		const courses = await Course
			.find()
			.populate('author')  //.populate('author', 'name -_id')
			.populate('category', 'name') //multiple properties
			.select('authorName');
	}
	
// Using Embeded Documents (Denormalization) --> PERFORMANCE
	let course = {
		author: {
			name: 'Mosh'
		}
	}
// -----------------code-------------------
	const authorSchema =  new mongoose.Schema({
		name: String,
		bio: String,
		website: String
	});
	const Author = mongoose.model('Author', authorSchema);
	
	const Course = mongoose.model('Course', new mongoose.Schema({
		name: String,
		author: authorSchema
		//** tom make required
		//author: {
		//	type: authorSchema,
		//	required: true
		//}
	}));
	
	// ----------------viewing document--------------
	async function listCourses() {
		const courses = await Course
			.find()
			.populate('author')  //.populate('author', 'name -_id')
			.populate('category', 'name') //multiple properties
			.select('authorName');
	}
// Hybrid
	let author = {
		name: 'Mosh',
			:
			:
	}	

	let course = {
		author: {
			id: 'ref',
			name: 'Mosh'
		}
	}


// ===================Transactional===================
const mongoose = require('mongoose');
const Fawn = require('fawn');

Fawn.init(mongoose);

try {
	new Fawn.Task()
	.save('rentals', rental)
	.update('movies', { _id: movie._id}, {
		$inc: { numberInStock: -1}
	})
	.run();
}catch (err) {
	res.status(500);
}

// Mongo Id _id: 
	// 12 bytes, where 2 characte represents 1 byte ,   
		//first 4 byte: is timestamp when id was created
		// 3 bytes: machine identifier
		// 2 bytes: process identifier
		// 3 byte: counter
// Explicitly generate id in mogoose
const id = new mongoose.Types.ObjectId();
id.getTimeStamp();

// check Id validity
mongoose.Types.ObjectId.isValid((req.body.customerId))





// Mongo
0. Is mongoose ORM
1. Middlewear in mongoose for encrypting password?
	Schema.pre
2. Frawn mechanism
3. sort on basis of casesensetive
4. triggers
5. normalized and denormalized
6. join in MongoDB
7. what does objectId consists of
8. mongo aggregation
9. pre-save hooks

// ================================================= Child Process ===================================================
// Start a new child process
	// Async versions
	child_process.spawn(command, [args], {options})
	child_process.exec(command, {options}, callback)
	child_process.execFile(file, [args], {options}, callback)
	child_process.fork(modulePath, [args], {options})

	// Sync versions
	child_process.spawnSync()
	child_process.execSync()
	child_process.execFileSync()
	
// Events in child_process
	"data" => output of stdout stream
	"close" => when stdio streams close
	"disconnected" => after child.disconnect() is called by parent
	"error" => when error occurs(no spawn, cannot kill, message from parent failed)
	"message" => when process.send() is called by child
	"exit" => when process ends

// Functions
	child.disconnect()
	child.kill([signal])
	child.send(message, handle, options, callback)

// Properties
	child.connected
	child.channel
	child.pid
	child.stdin
	child.stdout
	child.stderr
	child.stdio
	
//  {Options}
	cwd 		<string> Current working directory  of the child process
	env 		<Object> Environment key-value pairs
	argv0		<string> Explicitly set the value of argv[0] sent to the child process.
	stdio 		<Array> | <string> Child's stdio configuration. ("pipe", "ignore", "inherit")
	detached 	<boolean> prepare child to run independently of its parent process.
	uid			<number> Sets the user identity of the process.
	gid			<number> Sets the group identity of the process.
	shell		<boolean> | <string> If true, runs commnd inside shell.

	var cp = require("child_proces");
	var progs = {
		list: "ls",
		copy: "cp",
		folder: "mkdir"
	}
	// Stream, it is printing but it is printing in the child process stream, 
	// hence you wont be able to see in main process
	var child = cp.spawn(progs.list, ["-a"], {cwd:".."}); // or cp.spawn(progs.list)
	// to make it visible in parent process
	child.stout.on("data", (data) => {
		console.log(data);
	});
	child.stderr.on("data", (err) => {
		console.log(err);
	});
	child.on("exit", () => {
		console.log("Process finished");
	});
	
	var child = cp.exec(progs.remove + "-r css", {cwd:".."}, (error, stdout, stderr) => { // cp.exec(progs.remove + "-r css")
		if(error){
			console.err(error.name,error.message, erroe.stack);
		} else {
			console.log(stdout);
		}
	});
	
	var child = cp.execFile("ls", ["-a", "-l"], {cwd:".."}, (error, out, err) => {
		if(error){
			throw error;
		} else {
			console.log(out);
		}
	});
	
	var child = cp.fork("xyz.js", [args], {cwd: "./modules"});
	
	child.on("exit", () => {
		console.log("child terminated");
	});
	
// Messaging 
	// Parent
	const cp = require('child_process');
	const path = require('path');
	
	const names = ["duly", "bonheur", "boidam", "adrian"];
	
	var child = cp.fork("forkchild.js", names);
	
	child.on("message", (data) => {
		console.log(`parent received ${data}`);
	});
	child.on("exit", () => {
		console.log("child terminated!");
	});
	
	let interval = setInterval(() => {
		child.send({name:"duly", age:"30", city:"Naples"});
	}, 1000);
	
	setTimout(() => {
		clearInterval(interval);
		child.kill();
	}, 5000);
	
	// Child (forkchild.js)
	var data = process.argv.slice(2);
	function sayHello(names) {
		names.forEach((name) => {
			process.send(`Greetings ${name}`);
		});
	};
	sayHello(data);
	
	process.on("message", (userData) => {
		console.dir(userData, {colors:true});
	});
	
// ======================================== Worker Thredas ===========================================
 
const {Worker, isMainThread, parentPort} = require('worker_threads');
if(isMainThread) {
	// This code is executed in the main thread
	const worker = new Worker(__filename);
	// Listen for message from the worker and print them.
	worker.on('message', (msg) => {console.log(msg); });
} else {
	// This code is executed in the worker thread	
	// Send a message to thr main thread
	parentPort.postMessage('Hello World!');
}
// -----------------------------------------------------------------------------------------------------
// Industrial uses
// app.js
const app = require('express')();
const { Worker,isMainThread } = require('worker_threads');
let id = 0;
function createWorker({id, data}) {
	return new Promise((resolve, reject) => {
		if (isMainThread) {
			const worker = new Worker('./worker.js', { workerData: {id: id, data: data} });
			
				worker.on('message', (data) => {
					console.log('Processed output:', data);
					resolve();
				});
				
				worker.on('error', (error) => {
					console.log('Got error');
					reject();
				});
				
				worker.on('exit', (data) => {
					reject();
				});
		}
	});
}

app.post('/', (req, res) => {
	createWorker({ id: id++, data: req.body });
	return res.staus(200).send('Data recieved');
});

// worker.js
const { parentPort, isMainThread, workerData } = require('worker_threads');

if (!isMainThread) {
	console.log("Inside thread", workerData.id);
	
	const parsedJSON = JSON.parse(workerData.data);
	// Fetch data from S3 and creates thumbnail
	parentPort.postMessage(parsedJSON);
}
	

// ============================================= Buffer, Streams, Pipes ===============================================
var fs = require('fs');
// Here we are storing the entire thing in a buffer, it is bad because we are using the same memory space as JS engine
// Buffers: temporary chunks of data that we create to transport
// synchronous
var data = fs.readFileSync(__dirname + '/data.txt', 'utf8');
console.log(data);

// asynchronous
var data2 = fs.readFile(__dirname + '/data.txt', 'utf8',
	function(err, data){
		console.log(data);
	}
);

// Solution to above problem
// Streams: sequence of data which contains buffer
var readableStream = fs.createReadStream(__dirname + '/data.txt', {encodeing: 'utf8'});
var writableStream = fs.createWriteStream(__dirname + '/data2.txt');

readableStream.on('data', function(dataChunk){
	console.log(dataChunk);
	writablStream.write(dataChunk);
});
// or
readbleStream.pipe(writablStream);

// ============================= Cluster =======================================
var cluster = require('cluster');

if (cluster.isMaster) {
	let cpuCount = require('os').cpus().length;
	
	for (let i=0; i<cpuCount; i+=1) {
		cluster.fork();
	}
	
	cluster.on('exit', function() {
		cluster.fork();
	});
} else {
	// do some work spin up a server
}































