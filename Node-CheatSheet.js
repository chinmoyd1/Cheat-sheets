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















