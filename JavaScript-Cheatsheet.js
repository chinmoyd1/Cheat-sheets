//Jvascript

JavaScript recognizes six primitive (immutable) data types: 
	Boolean, 
	Null, 
	Undefined, 
	Number, 
	String, and 
	Symbol (new with ES6) 
and one type for mutable items: Object.
Note that in JavaScript, arrays are technically a type of object.

//Undefined
JavaScript assigns 'undefined' to any object that has been declared but not initialized or defined. 
In other words, in a case where no value has been explicitly assigned to the variable, JavaScript calls it 'undefined'

//NaN vs undefined
When JavaScript variables are declared, they have an initial value of undefined. 
If you do a mathematical operation on an undefined variable your result will be NaN which means "Not a Number". 
If you concatenate a string with an undefined variable, you will get a literal string of "undefined".

//Decimal Number with JavaScript
function here(){
	 var n = 1.1;
	 n = n*2.1;//2.3100000000000005
	 //for setting precission
	 console.log(n.toFixed(2));//2.31
}
here();

//Escaping Literal Quotes in Strings
var sampleStr = "Alan said, \"Peter is learning JavaScript\".";

//Quoting Strings with Single QuotesPassed
String values in JavaScript may be written with single or double quotes, 
as long as you start and end with the same type of quote. Unlike some other programming languages, single and double quotes work the same in JavaScript.

//Use Bracket Notation to Find the First Character in a StringPassed
Bracket notation is a way to get a character at a specific index within a string.
For example, the character at index 0 in the word "Charles" is "C". So if var firstName = "Charles", 
you can get the value of the first letter of the string by using firstName[0].

//Understand String ImmutabilityPassed
In JavaScript, String values are immutable, which means that they cannot be altered once created.

For example, the following code:

var myStr = "Bob";
myStr[0] = "J";
cannot change the value of myStr to "Job", because the contents of myStr cannot be altered. Note that this does not mean that myStr cannot be changed, just that the individual characters of a string literal cannot be changed. The only way to change myStr would be to assign it with a new string, like this:

var myStr = "Bob";
myStr = "Job";

//Nest one Array within Another ArrayPassed
You can also nest arrays within other arrays, like this: [["Bulls", 23], ["White Sox", 45]]. This is also called a multi-dimensional arrray.

Access Multi-Dimensional Arrays With IndexesPassed
	var arr = [
	  [1,2,3],
	  [4,5,6],
	  [7,8,9],
	  [[10,11,12], 13, 14]
	];
	arr[3]; // equals [[10,11,12], 13, 14]
	arr[3][0]; // equals [10,11,12]
	arr[3][0][1]; // equals 11

//Manipulate Arrays With shift()
pop() always removes the last element of an array. What if you want to remove the first?
Thats where .shift() comes in. It works just like .pop(), except it removes the first element instead of the last.

//Manipulate Arrays With unshift()
.unshift() works exactly like .push(), but instead of adding the element at the end of the array, unshift() adds the element at the beginning of the array.

//Passing Values to Functions with Arguments
function testFun(param1, param2) {
  console.log(param1, param2);
}

//Global Scope and Functions
In JavaScript, scope refers to the visibility of variables. 
Variables which are defined outside of a function block have Global scope. This means, they can be seen everywhere in your JavaScript code.

Variables which are used without the var keyword are automatically created in the global scope. 
This can create unintended consequences elsewhere in your code or when running a function again. You should always declare your variables with var.

//Local Scope and Functions
Variables which are declared within a function, as well as the function parameters have local scope. That means, they are only visible within that function.
Here is a function myTest with a local variable called loc.

//Global vs. Local Scope in FunctionsPassed
It is possible to have both local and global variables with the same name. When you do this, the local variable takes precedence over the global variable.

//Understanding Undefined Value returned from a FunctionPassed
A function can include the return statement but it does not have to. 
In the case that the function doesnt have a return statement, when you call it, the function processes the inner code but the returned value is undefined.
	var sum = 0;
	function addSum(num) {
	  sum = sum + num;
	}
	addSum(3); // sum will be modified but returned value is undefined

//Comparison with the Strict Equality Operator
Strict equality (===) is the counterpart to the equality operator (==). 
However, unlike the equality operator, which attempts to convert both values being compared to a common type, 
the strict equality operator does not perform a type conversion.

//Switch Statements
		switch (num) {
		  case value1:
			statement1;
			break;
		  case value2:
			statement2;
			break;
		...
		  default:
			defaultStatement;
			break;
		}

//Build JavaScript Objects
Objects are similar to arrays, except that instead of using indexes to access and modify their data, you access the data in objects through what are called properties.
Objects are useful for storing data in a structured way, and can represent real world objects, like a cat.

	var cat = {
	  "name": "Whiskers",
	  "legs": 4,
	  "tails": 1,
	  "enemies": ["Water", "Dogs"]
	};

//Accessing Object Properties with Dot NotationPassed
There are two ways to access the properties of an object: dot notation (.) and bracket notation ([]), similar to an array.
	ar myObj = {
	  prop1: "val1",
	  prop2: "val2"
	};
	var prop1val = myObj.prop1; // val1
	var prop2val = myObj.prop2; // val2

//Accessing Object Properties with Bracket Notation
	var myObj = {
	  "Space Name": "Kirk",
	  "More Space": "Spock",
	  "NoSpace": "USS Enterprise"
	};
	myObj["Space Name"]; // Kirk
	myObj['More Space']; // Spock
	myObj["NoSpace"];    // USS Enterprise

//Accessing Object Properties with VariablesPassed
	var dogs = {
	  Fido: "Mutt",  Hunter: "Doberman",  Snoopie: "Beagle"
	};
	var myDog = "Hunter";
	var myBreed = dogs[myDog];
	console.log(myBreed); // "Doberman"

//Updating Object PropertiesPassed
	var ourDog = {
	  "name": "Camper",
	  "legs": 4,
	  "tails": 1,
	  "friends": ["everything!"]
	};
Since hes a particularly happy dog, lets change his name to "Happy Camper". Heres how we update his objects name property: ourDog.name = "Happy Camper"; or ourDog["name"] = "Happy Camper"; Now when we evaluate ourDog.name, instead of getting "Camper", well get his new name, "Happy Camper".

// Add New Properties to a JavaScript ObjectPassed
Heres how we would add a "bark" property to ourDog:
	ourDog.bark = "bow-wow";
	or
	ourDog["bark"] = "bow-wow";
Now when we evaluate ourDog.bark, well get his bark, "bow-wow".          

//Delete Properties from a JavaScript ObjectPassed
delete ourDog.bark;

//Testing Objects for PropertiesPassed
Sometimes it is useful to check if the property of a given object exists or not. 
We can use the .hasOwnProperty(propname) method of objects to determine if that object has the given property name. 
.hasOwnProperty() returns true or false if the property is found or not.
	var myObj = {
	  top: "hat",
	  bottom: "pants"
	};
	myObj.hasOwnProperty("top");    // true
	myObj.hasOwnProperty("middle"); // false
	'top' in myObj;//true

//Accessing Nested Objects
	var ourStorage = {
	  "desk": {
		"drawer": "stapler"
	  },
	  "cabinet": {
		"top drawer": { 
		  "folder1": "a file",
		  "folder2": "secrets"
		},
		"bottom drawer": "soda"
	  }
	};
	ourStorage.cabinet["top drawer"].folder2;  // "secrets"
	ourStorage.desk.drawer; // "stapler"
	
//Accessing Nested Arrays
	var ourPets = [
	  {
		animalType: "cat",
		names: [
		  "Meowzer",
		  "Fluffy",
		  "Kit-Cat"
		]
	  },
	  {
		animalType: "dog",
		names: [
		  "Spot",
		  "Bowser",
		  "Frankie"
		]
	  }
	];
ourPets[0].names[1]; // "Fluffy"
ourPets[1].names[0]; // "Spot"
	
	
//Generate Random Whole Numbers within a Range
Math.floor(Math.random() * (max - min + 1)) + min	
	
//Use the parseInt FunctionPassed
The parseInt() function parses a string and returns an integer. Heres an example:
var a = parseInt("007");
The above function converts the string "007" to an integer 7. If the first character in the string cant be converted into a number, then it returns NaN.	
	
//Use the parseInt Function with a Radix
The parseInt() function parses a string and returns an integer. It takes a second argument for the radix, which specifies the base of the number in the string. The radix can be an integer between 2 and 36.
The function call looks like:
	parseInt(string, radix);
And heres an example:
	var a = parseInt("11", 2);
The radix variable says that "11" is in the binary system, or base 2. This example converts the string "11" to an integer 3.	

//Use Multiple Conditional (Ternary) Operators	
function findGreaterOrEqual(a, b) {
  return (a === b) ? "a and b are equal" : (a > b) ? "a is greater" : "b is greater";
}	
	
//Explore Differences Between the var and let Keywords
One of the biggest problems with declaring variables with the var keyword is that you can overwrite variable declarations without an error.	
	var camper = 'James';
	var camper = 'David';
	console.log(camper);// logs 'David'
	
As you can see in the code above, the camper variable is originally declared as James and then overridden to be David.
In a small application, you might not run into this type of problem, but when your code becomes larger,
you might accidentally overwrite a variable that you did not intend to overwrite. Because this behavior does not throw an error, 
searching and fixing bugs becomes more difficult.
A new keyword called let was introduced in ES6 to solve this potential issue with the var keyword. 
If you were to replace var with let in the variable declarations of the code above, the result would be an error.

let camper = 'James';
let camper = 'David'; // throws an error
	
This error can be seen in the console of your browser. So unlike var, when using let, a variable with the same name can only be declared once. Note the "use strict". This enables Strict Mode, which catches common coding mistakes and "unsafe" actions. For instance:

"use strict";
x = 3.14; // throws an error because x is not declared

-----------------------------------------------------------------------------------------------------------------------------------------------------

//The most recent standardized version is called ECMAScript 6 (ES6), released in 2015. This new version of the language adds some powerful features including:	
	1. Arrow functions
	2. Classes
	3. Modules
	4. Promises
	5. Generators
	6. let and const
	
//ES6: Compare Scopes of the var and let Keywords

When you declare a variable with the var keyword, it is declared globally, or locally if declared inside a function not other block scopes like loops.
	var numArray = [];
	for (var i = 0; i < 3; i++) {
	  numArray.push(i);
	}
	console.log(numArray);
	// returns [0, 1, 2]
	console.log(i);
	// returns 3
	----or----
	if(true){
		var s =7;
	}
	console.log(s);//7 instead of null
	
The let keyword behaves similarly, but with some extra features. 
When you declare a variable with the let keyword inside a block, statement, or expression, its scope is limited to that block, statement, or expression.	
	
//ES6: Declare a Read-Only Variable with the const Keyword
const has all the awesome features that let has, with the added bonus that variables declared using const are read-only. 
They are a constant value, which means that once a variable is assigned with const, it cannot be reassigned.	
	"use strict";
	const FAV_PET = "Cats";
	FAV_PET = "Dogs"; // returns error
	
//ES6: Declare a Read-Only Variable with the const KeywordPassed
const has all the awesome features that let has, with the added bonus that variables declared using const are read-only. They are a constant value, which means that once a variable is assigned with const, it cannot be reassigned.

	"use strict";
	const FAV_PET = "Cats";
	FAV_PET = "Dogs"; // returns error
	
//ES6: Mutate an Array Declared with const
However, it is important to understand that objects (including arrays and functions) assigned to a variable using const are still mutable. Using the const declaration only prevents reassignment of the variable identifier.

	"use strict";
	const s = [5, 6, 7];
	s = [1, 2, 3]; // throws error, trying to assign a const
	s[2] = 45; // works just as it would with an array declared with var or let
	console.log(s); // returns [5, 6, 45]
	
//ES6: Prevent Object Mutation	
As seen in the previous challenge, const declaration alone doesnt really protect your data from mutation. To ensure your data doesnt change, 
JavaScript provides a function Object.freeze to prevent data mutation.
Once the object is frozen, you can no longer add, update, or delete properties from it. Any attempt at changing the object will be rejected without an error.
	let obj = {
	  name:"FreeCodeCamp",
	  review:"Awesome"
	};
	Object.freeze(obj);
	obj.review = "bad"; // will be ignored. Mutation not allowed
	obj.newProp = "Test"; // will be ignored. Mutation not allowed
	console.log(obj); 
	// { name: "FreeCodeCamp", review:"Awesome"}
	
	
	
//Use Arrow Functions to Write Concise Anonymous Functions
In JavaScript, we often dont need to name our functions, especially when passing a function as an argument to another function. 
Instead, we create inline functions. We dont need to name these functions because we do not reuse them anywhere else.
To achieve this, we often use the following syntax:
	const myFunc = function() {
	  const myVar = "value";
	  return myVar;
	}
ES6 provides us with the syntactic sugar to not have to write anonymous functions this way. Instead, you can use arrow function syntax:
	const myFunc = () => {
	  const myVar = "value";
	  return myVar;
	}
When there is no function body, and only a return value, arrow function syntax allows you to omit the keyword return as well as the brackets surrounding the code. This helps simplify smaller functions into one-line statements:

const myFunc = () => "value";
This code will still return value by default.
	
//ES6: Write Arrow Functions with Parameters
Just like a regular function, you can pass arguments into an arrow function.

	// doubles input value and returns it
	const doubler = (item) => item * 2;
	If an arrow function has a single argument, the parentheses enclosing the argument may be omitted.

	// the same function, without the argument parentheses
	const doubler = item => item * 2;
	It is possible to pass more than one argument into an arrow function.

	// multiplies the first input value by the second and returns it
	const multiplier = (item, multi) => item * multi;
	
	
//ES6: Set Default Parameters for Your FunctionsPassed
In order to help us create more flexible functions, ES6 introduces default parameters for functions.
	const greeting = (name = "Anonymous") => "Hello " + name;
	console.log(greeting("John")); // Hello John
	console.log(greeting()); // Hello Anonymous	
	
//ES6: Use the Rest Parameter with Function Parameters
In order to help us create more flexible functions, ES6 introduces the rest parameter for function parameters. 
	With the rest parameter, you can create functions that take a variable number of arguments. 
	These arguments are stored in an array that can be accessed later from inside the function.	
		function howMany(...args) {
		  return "You have passed " + args.length + " arguments.";
		}
		console.log(howMany(0, 1, 2)); // You have passed 3 arguments.
		console.log(howMany("string", null, [1, 2, 3], { })); // You have passed 4 arguments.
		
	The rest parameter eliminates the need to check the args array and allows us to apply map(), filter() and reduce() on the parameters array.
	
//ES6: Use the Spread Operator to Evaluate Arrays In-Place
ES6 introduces the spread operator, which allows us to expand arrays and other expressions in places where multiple parameters or elements are expected.
The ES5 code below uses apply() to compute the maximum value in an array:

	var arr = [6, 89, 3, 45];
	var maximus = Math.max.apply(null, arr); // returns 89
	We had to use Math.max.apply(null, arr) because Math.max(arr) returns NaN. Math.max() expects comma-separated arguments, but not an array.
	The spread operator makes this syntax much better to read and maintain.

	const arr = [6, 89, 3, 45];
	const maximus = Math.max(...arr); // returns 89
	...arr returns an unpacked array. In other words, it spreads the array. However, the spread operator only works in-place, like in an argument to a function or in an array literal. The following code will not work:

	const spreaded = ...arr; // will throw a syntax error	
	
	
//ES6: Use Destructuring Assignment to Extract Values from Objects
Destructuring assignment is special syntax introduced in ES6, for neatly assigning values taken directly from an object.
Consider the following ES5 code:

	const user = { name: 'John Doe', age: 34 };

	const name = user.name; // name = 'John Doe'
	const age = user.age; // age = 34
	Heres an equivalent assignment statement using the ES6 destructuring syntax:

	const { name, age } = user;
	// name = 'John Doe', age = 34
	Here, the name and age variables will be created and assigned the values of their respective values from the user object. You can see how much cleaner this is.
	You can extract as many or few values from the object as you want.	


//ES6: Use Destructuring Assignment to Assign Variables from ObjectsPassed
Destructuring allows you to assign a new variable name when extracting values. You can do this by putting the new name after a colon when assigning the value.
	Using the same object from the last example:

	const user = { name: 'John Doe', age: 34 };
	Heres how you can give new variable names in the assignment:

	const { name: userName, age: userAge } = user;
	// userName = 'John Doe', userAge = 34

//ES6: Use Destructuring Assignment to Assign Variables from Nested Objects
You can use the same principles from the previous two lessons to destructure values from nested objects.

	Using an object similar to previous examples:
		const user = {
		  johnDoe: { 
			age: 34,
			email: 'johnDoe@freeCodeCamp.com'
		  }
		};
	Heres how to extract the values of object properties and assign them to variables with the same name:
		const { johnDoe: { age, email }} = user;
	And heres how you can assign an object properties values to variables with different names:
		const { johnDoe: { age: userAge, email: userEmail }} = user;

//ES6: Use Destructuring Assignment to Assign Variables from Arrays
ES6 makes destructuring arrays as easy as destructuring objects.
One key difference between the spread operator and array destructuring is that the spread operator unpacks all contents of an array into a comma-separated list. Consequently, you cannot pick or choose which elements you want to assign to variables.
Destructuring an array lets us do exactly that:
	const [a, b] = [1, 2, 3, 4, 5, 6];
	console.log(a, b); // 1, 2
The variable a is assigned the first value of the array, and b is assigned the second value of the array. We can also access the value at any index in an array with destructuring by using commas to reach the desired index:
	const [a, b,,, c] = [1, 2, 3, 4, 5, 6];
	console.log(a, b, c); // 1, 2, 5


//ES6: Use Destructuring Assignment with the Rest Parameter to Reassign Array Elements
In some situations involving array destructuring, we might want to collect the rest of the elements into a separate array.
	The result is similar to Array.prototype.slice(), as shown below:
		const [a, b, ...arr] = [1, 2, 3, 4, 5, 7];
		console.log(a, b); // 1, 2
		console.log(arr); // [3, 4, 5, 7]
	Variables a and b take the first and second values from the array. 
	After that, because of rest parameters presence, arr gets rest of the values in the form of an array. 
	The rest element only works correctly as the last variable in the list. 
	As in, you cannot use the rest parameter to catch a subarray that leaves out last element of the original array.
	

//ES6: Use Destructuring Assignment to Pass an Object as a Functions Parameters
In some cases, you can destructure the object in a function argument itself.
Consider the code below:
	const profileUpdate = (profileData) => {
	  const { name, age, nationality, location } = profileData;
	  // do something with these variables
	}
This effectively destructures the object sent into the function. This can also be done in-place:
	const profileUpdate = ({ name, age, nationality, location }) => {
	  /* do something with these fields */
	}
This removes some extra lines and makes our code look neat. This has the added benefit of not having to manipulate an entire object in a function — only the fields that are needed are copied inside the function.


//ES6: Create Strings using Template Literals
A new feature of ES6 is the template literal. This is a special type of string that makes creating complex strings easier.
	Template literals allow you to create multi-line strings and to use string interpolation features to create strings.
	Consider the code below:
	const person = {
	  name: "Zodiac Hasbro",
	  age: 56
	};
	// Template literal with multi-line and string interpolation
	const greeting = `Hello, my name is ${person.name}!
	I am ${person.age} years old.`;

	console.log(greeting); // prints
	// Hello, my name is Zodiac Hasbro!
	// I am 56 years old.

//ES6: Write Concise Object Literal Declarations Using Object Property Shorthand
ES6 adds some nice support for easily defining object literals.
	Consider the following code:
		const getMousePosition = (x, y) => ({
		  x: x,
		  y: y
		});
	getMousePosition is a simple function that returns an object containing two properties. ES6 provides the syntactic sugar to 
	eliminate the redundancy of having to write x: x. You can simply write x once, and it will be converted tox: x (or something equivalent) under the hood. 
	Here is the same function from above rewritten to use this new syntax:
		const getMousePosition = (x, y) => ({ x, y });



//ES6: Write Concise Declarative Functions with ES6
With ES6, You can remove the function keyword and colon altogether when defining functions in objects. Heres an example of this syntax:

When defining functions within objects in ES5, we have to use the keyword function as follows:

	const person = {
	  name: "Taylor",
	  sayHello: function() {
		return `Hello! My name is ${this.name}.`;
	  }
	};

	const person = {
	  name: "Taylor",
	  sayHello() {
		return `Hello! My name is ${this.name}.`;
	  }
	};



//ES6: Use class Syntax to Define a Constructor Function
ES6 provides a new syntax to create objects, using the class keyword.
It should be noted that the class syntax is just syntax, and not a full-fledged class-based implementation of an object-oriented paradigm,
unlike in languages such as Java, Python, Ruby, etc.

In ES5, we usually define a constructor function and use the new keyword to instantiate an object.

	var SpaceShuttle = function(targetPlanet){
	  this.targetPlanet = targetPlanet;
	}
	var zeus = new SpaceShuttle('Jupiter');
	
The class syntax simply replaces the constructor function creation:

	class SpaceShuttle {
	  constructor(targetPlanet) {
		this.targetPlanet = targetPlanet;
	  }
	}
const zeus = new SpaceShuttle('Jupiter');

It should be noted that the class keyword declares a new function, to which a constructor is added. 
This constructor is invoked when new is called to create a new object.


//ES6: Use getters and setters to Control Access to an Object
You can obtain values from an object and set the value of a property within an object.
These are classically called getters and setters.
Getter functions are meant to simply return (get) the value of an objects private variable to the user without the user directly accessing the private variable.
Setter functions are meant to modify (set) the value of an objects private variable based on the value passed into the setter function. 
This change could involve calculations, or even overwriting the previous value completely.

	class Book {
	  constructor(author) {
		this._author = author;
	  }
	  // getter
	  get writer() {
		return this._author;
	  }
	  // setter
	  set writer(updatedAuthor) {
		this._author = updatedAuthor;
	  }
	}
	const lol = new Book('anonymous');
	console.log(lol.writer);  // anonymous
	lol.writer = 'wut';
	console.log(lol.writer);  // wut
	
Notice the syntax used to invoke the getter and setter. 
They do not even look like functions. Getters and setters are important because they hide internal implementation details. 
Note: It is convention to precede the name of a private variable with an underscore (_). However, the practice itself does not make a variable private.


//ES6: Use export to Share a Code Block
Imagine a file called math_functions.js that contains several functions related to mathematical operations. 
One of them is stored in a variable, add, that takes in two numbers and returns their sum. You want to use this function in several different JavaScript files. 
In order to share it with these other files, you first need to export it.

	export const add = (x, y) => {
	  return x + y;
	}
	
The above is a common way to export a single function, but you can achieve the same thing like this:

	const add = (x, y) => {
	  return x + y;
	}

	export { add };

When you export a variable or function, you can import it in another file and use it without having to rewrite the code. 
You can export multiple things by repeating the first example for each thing you want to export, or by placing them all in the export statement of the second example, 
like this:

	export { add, subtract };


//ES6: Reuse Javascript Code Using import
import allows you to choose which parts of a file or module to load. 
In the previous lesson, the examples exported add from the math_functions.js file. 
Heres how you can import it to use in another file:

	import { add } from './math_functions.js';
	
Here, import will find add in math_functions.js, import just that function for you to use, and ignore the rest. 
The ./ tells the import to look for the math_functions.js file in the same folder as the current file. 
The relative file path (./) and file extension (.js) are required when using import in this way.

You can import more than one item from the file by adding them in the import statement like this:
	import { add, subtract } from './math_functions.js';



//ES6: Use * to Import Everything from a File
Suppose you have a file and you wish to import all of its contents into the current file.
This can be done with the import * as syntax. Heres an example where the contents of a file named math_functions.js are imported into a file in the same directory:
	
	import * as myMathModule from "./math_functions.js";
	
The above import statement will create an object called myMathModule. 
This is just a variable name, you can name it anything. The object will contain all of the exports from math_functions.js in it, 
so you can access the functions like you would any other object property. Heres how you can use the add and subtract functions that were imported:

	myMathModule.add(2,3);
	myMathModule.subtract(5,3);



//ES6: Create an Export Fallback with export default
In the export lesson, you learned about the syntax referred to as a named export. 
This allowed you to make multiple functions and variables available for use in other files.

There is another export syntax you need to know, known as export default. 
Usually you will use this syntax if only one value is being exported from a file. It is also used to create a fallback value for a file or module.

Below are examples using export default:

		// named function
		export default function add(x, y) {
		  return x + y;
		}

		// anonymous function
		export default function(x, y) {
		  return x + y;
		}

	Utiliites.js

		export function cube(x) {
		  return x * x * x;
		}
		export const foo = Math.PI + Math.SQRT2;
		This can be imported and used as

	App.js

		import { cube, foo } from 'Utilities';
		console.log(cube(3)); // 27
		console.log(foo);    // 4.555806215962888
		Or

		import * as utilities from 'Utilities';
		console.log(utilities.cube(3)); // 27
		console.log(utilities.foo);    // 4.555806215962888
		When export default is used, this is much simpler. Script files just exports one thing. cube.js

		export default function cube(x) {
		  return x * x * x;
		};
		
	and used as App.js

		import Cube from 'cube';
		console.log(Cube(3)); // 27
		
Since export default is used to declare a fallback value for a module or file, you can only have one value be a default export in each module or file. 
Additionally, you cannot use export default with var, let, or const


//ES6: Import a Default Export
In the last challenge, you learned about export default and its uses. 
To import a default export, you need to use a different import syntax. In the following example, 
add is the default export of the math_functions.js file. Here is how to import it:

	import add from "./math_functions.js";
	
The syntax differs in one key place. 
The imported value, add, is not surrounded by curly braces ({}). 
add here is simply a variable name for whatever the default export of the math_functions.js file is. You can use any name here when importing a default.


//ES6: Create a JavaScript Promise
A promise in JavaScript is exactly what it sounds like - you use it to make a promise to do something, usually asynchronously. 
When the task completes, you either fulfill your promise or fail to do so. Promise is a constructor function, so you need to use the new keyword to create one. 
It takes a function, as its argument, with two parameters - resolve and reject. 
These are methods used to determine the outcome of the promise. The syntax looks like this:

	const myPromise = new Promise((resolve, reject) => {

	});


//ES6: Complete a Promise with resolve and reject
A promise has three states: pending, fulfilled, and rejected. 
The promise you created in the last challenge is forever stuck in the pending state because you did not add a way to complete the promise. 
The resolve and reject parameters given to the promise argument are used to do this. resolve is used when you want your promise to succeed, 
and reject is used when you want it to fail. These are methods that take an argument, as seen below.

	const myPromise = new Promise((resolve, reject) => {
	  if(condition here) {
		resolve("Promise was fulfilled");
	  } else {
		reject("Promise was rejected");
	  }
	});
	
The example above uses strings for the argument of these functions, but it can really be anything. 
Often, it might be an object, that you would use data from, to put on your website or elsewhere.


//ES6: Handle a Fulfilled Promise with then
Promises are most useful when you have a process that takes an unknown amount of time in your code (i.e. something asynchronous), 
often a server request. When you make a server request it takes some amount of time, and after it completes you usually want to do something with the response from the server. 
This can be achieved by using the then method. The then method is executed immediately after your promise is fulfilled with resolve. Here’s an example:
	
	myPromise.then(result => {
	  // do something with the result.
	});
	
result comes from the argument given to the resolve method.

//ES6: Handle a Rejected Promise with catch
catch is the method used when your promise has been rejected. It is executed immediately after a promises reject method is called. Here’s the syntax:

	myPromise.catch(error => {
	  // do something with the error.
	});
	
error is the argument passed in to the reject method.

Note: the then and catch methods can be chained to the promise declaration if you choose.


/////////////////////////////////////////////////////////////////////////////
		var promise = new Promise(function(resolve, reject) { 
		  const x = "geeksforgeeks"; 
		  const y = "geeksforgeeks"
		  if(x === y) { 
			resolve(); 
		  } else { 
			reject(); 
		  } 
		}); 
		  
		promise. 
			then(function () { 
				console.log('success, You are a GEEK'); 
			}). 
			catch(function () { 
				console.log('some error has occured'); 
			}); 
////////////////////////////////////////////////////////////////////////////////

//Map-Reduce-Filter-Find In Javascript ES6

//Imperative
// to calculate the sum of array elements
	const sum = (arr) => {
	  let result = 0; 
	  for (let i = 0; i < arr.length; i++) {
		result += arr[i];
	  }  
	  return result;
	};
Imperative style is cool but imagine what if there is a complex mathematics logic here then size of code and the readability will suck. It increases the cognitive load when reading, and over time makes it easier to faulter in reasoning and logic. Also, the main complexity of this code snippet derives from the fact that instead of telling the computer what we want it to do, we are instructing it on how to do it.

Declarative

// calculate the sum of array elements
const sum = (arr) => arr.reduce((total, item) => total += item, 0);

Now, this looks pretty clean, shorter, expressive, concise code, less error prone, easier to maintain and easier to debug.
We are telling computer what we want it to do rather how to do it.

Declarative approach are easily optimisable at complier end and also have less side effects.

Note: if you are concerned about the performance of above two and other javascript function (map, reduce, filter, find) 
then you should read here for small data set and can view here for large data set(100–1000000)
Without more delay, let’s start the real action with most used Javascript function for functional programming.

	//Map

	// definition 
	collection.map((currentValue, index) => {
		// Return element for newArray
	});
	// example
	const arr = [1,2,3,4,5];
	const newArray = arr.map(i => i*10);
	// return a new array with all value as multiple of 10;

Map works on an array and return an array that’s it. 
Above code snippet works on an collection i.e an array and takes a callback with current iteration value, index as arguments and return a new array.

Note: Maps are well suited for change/transforming whole array rather than breaking the flow for some conditions, 
Map suck’s performance wise, check out here but are easy to be used for small data sets.

	//Reduce

	// definition 
	collection.reduce((accumulator, item, index) => {
		// logic to perform to get accumulator as a return value
	}, initialValue for accumulator);
	// example
	const arr = [1,2,3,4,5];
	const total = arr.reduce((acc, item) => acc+= item, 0);
	// return a total as 15
	
Reduce works on an array but can return anything you want it to return. 
As the name speaks for itself it can be reduce to anything and can behave like map, find, filter or any other javascript function. 
The above code snippet works on an array and reduce to compute the total value of item of array.

Explanation of example above : On reduce first run, acc is assigned a 0 value and then acc+= item i.e acc = acc+item which will compute to0+1 i.e 1. 
This 1 will be acc value for next iteration and this continues until we are done with all array items.

	//Find

	// definition 
	collection.find((item) => {
		// return first element that satisfy the condition
	});
	// example
	const arr = [1,2,8,4,5];
	const value = arr.find(i => i%4 == 0);
	// return the first value i.e 8 
	
Find works on an array and return the first element that satisfy the condition in function.
Note: Easy, simple but not efficient on large data set, why ? look here

	//Filter

	// definition 
	collection.filter((currentValue, index) => {
		// logic to filter array on
	});
	// example
	const arr = [1,2,3,4,5];
	const newArray = arr.filter(i => i%2 == 0);
	// return a new array with value [2, 4]
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------

//Regular Expression 

Regular expressions are special strings that represent a search pattern. Also known as "regex" or "regexp", 
they help programmers match, search, and replace text. Regular expressions can appear cryptic because a few characters have special meaning. 
The goal is to combine the symbols and text into a pattern that matches what you want, but only what you want. This section will cover the characters, 
a few shortcuts, and the common uses for writing regular expressions.


//Regular Expressions: Using the Test Method
Regular expressions are used in programming languages to match parts of strings. You create patterns to help you do that matching.

If you want to find the word "the" in the string "The dog chased the cat", you could use the following regular expression: /the/. 
Notice that quote marks are not required within the regular expression.

JavaScript has multiple ways to use regexes. One way to test a regex is using the .test() method. 
The .test() method takes the regex, applies it to a string (which is placed inside the parentheses), and returns true or false if your pattern finds something or not.

	let testStr = "freeCodeCamp";
	let testRegex = /Code/;
	testRegex.test(testStr);
	// Returns true

//Regular Expressions: Match a Literal String with Different Possibilities
Using regexes like /coding/, you can look for the pattern "coding" in another string.

This is powerful to search single strings, but its limited to only one pattern. You can search for multiple patterns using the alternation or OR operator: |.
This operator matches patterns either before or after it. 
For example, if you wanted to match "yes" or "no", the regex you want is /yes|no/.
You can also search for more than just two patterns. You can do this by adding more patterns with more OR operators separating them, like /yes|no|maybe/.

//Regular Expressions: Ignore Case While Matching
Up until now, youve looked at regexes to do literal matches of strings. But sometimes, you might want to also match case differences.
Case (or sometimes letter case) is the difference between uppercase letters and lowercase letters. Examples of uppercase are "A", "B", and "C". 
Examples of lowercase are "a", "b", and "c".
You can match both cases using what is called a flag. There are other flags but here youll focus on the flag that ignores case - the i flag. You can use it by appending it to the regex. 
An example of using this flag is /ignorecase/i. This regex can match the strings "ignorecase", "igNoreCase", and "IgnoreCase".


//Regular Expressions: Extract Matches
So far, you have only been checking if a pattern exists or not within a string. You can also extract the actual matches you found with the .match() method.
To use the .match() method, apply the method on a string and pass in the regex inside the parentheses. Heres an example:

	"Hello, World!".match(/Hello/);
	// Returns ["Hello"]
	let ourStr = "Regular expressions";
	let ourRegex = /expressions/;
	ourStr.match(ourRegex);
	// Returns ["expressions"]


//Regular Expressions: Find More Than the First Match
So far, you have only been able to extract or search a pattern once.

	let testStr = "Repeat, Repeat, Repeat";
	let ourRegex = /Repeat/;
	testStr.match(ourRegex);
	// Returns ["Repeat"]
	To search or extract a pattern more than once, you can use the g flag.

	let repeatRegex = /Repeat/g;
	testStr.match(repeatRegex);
	// Returns ["Repeat", "Repeat", "Repeat"]


//Regular Expressions: Match Anything with Wildcard Period
Sometimes you wont (or dont need to) know the exact characters in your patterns. Thinking of all words that match, say, a misspelling would take a long time. 
Luckily, you can save time using the wildcard character: .
The wildcard character . will match any one character. The wildcard is also called dot and period. 
You can use the wildcard character just like any other character in the regex. For example, 
if you wanted to match "hug", "huh", "hut", and "hum", you can use the regex /hu./ to match all four words.

	let humStr = "Ill hum a song";
	let hugStr = "Bear hug";
	let huRegex = /hu./;
	huRegex.test(humStr); // Returns true
	huRegex.test(hugStr); // Returns true


//Regular Expressions: Match Single Character with Multiple Possibilities
You can search for a literal pattern with some flexibility with character classes. 
Character classes allow you to define a group of characters you wish to match by placing them inside square ([ and ]) brackets.

For example, you want to match "bag", "big", and "bug" but not "bog". You can create the regex /b[aiu]g/ to do this. 
The [aiu] is the character class that will only match the characters "a", "i", or "u".

	let bigStr = "big";
	let bagStr = "bag";
	let bugStr = "bug";
	let bogStr = "bog";
	let bgRegex = /b[aiu]g/;
	bigStr.match(bgRegex); // Returns ["big"]
	bagStr.match(bgRegex); // Returns ["bag"]
	bugStr.match(bgRegex); // Returns ["bug"]
	bogStr.match(bgRegex); // Returns null
	
//Regular Expressions: Match Letters of the Alphabet
You saw how you can use character sets to specify a group of characters to match, 
but thats a lot of typing when you need to match a large range of characters (for example, every letter in the alphabet). 
Fortunately, there is a built-in feature that makes this short and simple.

Inside a character set, you can define a range of characters to match using a hyphen character: -.
For example, to match lowercase letters a through e you would use [a-e].

	let catStr = "cat";
	let batStr = "bat";
	let matStr = "mat";
	let bgRegex = /[a-e]at/;
	catStr.match(bgRegex); // Returns ["cat"]
	batStr.match(bgRegex); // Returns ["bat"]
	matStr.match(bgRegex); // Returns null


//Regular Expressions: Match Numbers and Letters of the Alphabet
Using the hyphen (-) to match a range of characters is not limited to letters. It also works to match a range of numbers.
For example, /[0-5]/ matches any number between 0 and 5, including the 0 and 5.
Also, it is possible to combine a range of letters and numbers in a single character set.

	let jennyStr = "Jenny8675309";
	let myRegex = /[a-z0-9]/ig;
	// matches all letters and numbers in jennyStr
	jennyStr.match(myRegex);


//Regular Expressions: Match Single Characters Not Specified
So far, you have created a set of characters that you want to match, but you could also create a set of characters that you do not want to match. 
These types of character sets are called negated character sets.
To create a negated character set, you place a caret character (^) after the opening bracket and before the characters you do not want to match.

For example, /[^aeiou]/gi matches all characters that are not a vowel. 
Note that characters like ., !, [, @, / and white space are matched - the negated vowel character set only excludes the vowel characters.


//Regular Expressions: Match Characters that Occur One or More Times
Sometimes, you need to match a character (or group of characters) that appears one or more times in a row. This means it occurs at least once, and may be repeated.
You can use the + character to check if that is the case. Remember, the character or pattern has to be present consecutively. 
That is, the character has to repeat one after the other.
For example, /a+/g would find one match in "abc" and return ["a"]. Because of the +, it would also find a single match in "aabc" and return ["aa"].

If it were instead checking the string "abab", it would find two matches and return ["a", "a"] because the a characters are not in a row - there is a b between them. 
Finally, since there is no "a" in the string "bcd", it wouldnt find a match.                              


//Regular Expressions: Match Characters that Occur Zero or More Times
The last challenge used the plus + sign to look for characters that occur one or more times. 
Theres also an option that matches characters that occur zero or more times.

The character to do this is the asterisk or star: *.

	let soccerWord = "gooooooooal!";
	let gPhrase = "gut feeling";
	let oPhrase = "over the moon";
	let goRegex = /go*/;
	soccerWord.match(goRegex); // Returns ["goooooooo"]
	gPhrase.match(goRegex); // Returns ["g"]
	oPhrase.match(goRegex); // Returns null


//Regular Expressions: Find Characters with Lazy Matching
In regular expressions, a greedy match finds the longest possible part of a string that fits the regex pattern and returns it as a match. 
The alternative is called a lazy match, which finds the smallest possible part of the string that satisfies the regex pattern.

You can apply the regex /t[a-z]*i/ to the string "titanic". This regex is basically a pattern that starts with t, ends with i, and has some letters in between.
Regular expressions are by default greedy, so the match would return ["titani"]. It finds the largest sub-string possible to fit the pattern.
However, you can use the ? character to change it to lazy matching. "titanic" matched against the adjusted regex of /t[a-z]*?i/ returns ["ti"].

Note: Parsing HTML with regular expressions should be avoided, but pattern matching an HTML string with regular expressions is completely fine.

//Regular Expressions: Find Characters with Lazy Matching
In regular expressions, a greedy match finds the longest possible part of a string that fits the regex pattern and returns it as a match. 
The alternative is called a lazy match, which finds the smallest possible part of the string that satisfies the regex pattern.

You can apply the regex /t[a-z]*i/ to the string "titanic". This regex is basically a pattern that starts with t, ends with i, and has some letters in between.

Regular expressions are by default greedy, so the match would return ["titani"]. It finds the largest sub-string possible to fit the pattern.
However, you can use the ? character to change it to lazy matching. "titanic" matched against the adjusted regex of /t[a-z]*?i/ returns ["ti"].


//Regular Expressions: Match Beginning String Patterns
Prior challenges showed that regular expressions can be used to look for a number of matches. 
They are also used to search for patterns in specific positions in strings.
In an earlier challenge, you used the caret character (^) inside a character set to create a negated character set in the form [^thingsThatWillNotBeMatched]. 
Outside of a character set, the caret is used to search for patterns at the beginning of strings.

	let firstString = "Ricky is first and can be found.";
	let firstRegex = /^Ricky/;
	firstRegex.test(firstString);
	// Returns true
	let notFirst = "You cant find Ricky now.";
	firstRegex.test(notFirst);
	// Returns false

//Regular Expressions: Match Ending String Patterns
In the last challenge, you learned to use the caret character to search for patterns at the beginning of strings. 
There is also a way to search for patterns at the end of strings.
You can search the end of strings using the dollar sign character $ at the end of the regex.

	let theEnding = "This is a never ending story";
	let storyRegex = /story$/;
	storyRegex.test(theEnding);
	// Returns true
	let noEnding = "Sometimes a story will have to end";
	storyRegex.test(noEnding);
	// Returns false


//Regular Expressions: Match All Letters and Numbers
Using character classes, you were able to search for all letters of the alphabet with [a-z]. 
This kind of character class is common enough that there is a shortcut for it, although it includes a few extra characters as well.

The closest character class in JavaScript to match the alphabet is \w. This shortcut is equal to [A-Za-z0-9_]. 
This character class matches upper and lowercase letters plus numbers. Note, this character class also includes the underscore character (_).

	let longHand = /[A-Za-z0-9_]+/;
	let shortHand = /\w+/;
	let numbers = "42";
	let varNames = "important_var";
	longHand.test(numbers); // Returns true
	shortHand.test(numbers); // Returns true
	longHand.test(varNames); // Returns true
	shortHand.test(varNames); // Returns true



//Regular Expressions: Match Everything But Letters and Numbers
Youve learned that you can use a shortcut to match alphanumerics [A-Za-z0-9_] using \w. 
A natural pattern you might want to search for is the opposite of alphanumerics.
You can search for the opposite of the \w with \W. Note, the opposite pattern uses a capital letter. This shortcut is the same as [^A-Za-z0-9_].

	let shortHand = /\W/;
	let numbers = "42%";
	let sentence = "Coding!";
	numbers.match(shortHand); // Returns ["%"]
	sentence.match(shortHand); // Returns ["!"]

//Regular Expressions: Match All Numbers
Youve learned shortcuts for common string patterns like alphanumerics. Another common pattern is looking for just digits or numbers.
The shortcut to look for digit characters is \d, with a lowercase d. This is equal to the character class [0-9], 
which looks for a single character of any number between zero and nine.

//Regular Expressions: Match All Non-Numbers
The last challenge showed how to search for digits using the shortcut \d with a lowercase d. 
You can also search for non-digits using a similar shortcut that uses an uppercase D instead.

The shortcut to look for non-digit characters is \D. This is equal to the character class [^0-9], 
which looks for a single character that is not a number between zero and nine.

//Regular Expressions: Reuse Patterns Using Capture Groups
Some patterns you search for will occur multiple times in a string. It is wasteful to manually repeat that regex. 
There is a better way to specify when you have multiple repeat substrings in your string.
You can search for repeat substrings using capture groups. Parentheses, ( and ), are used to find repeat substrings. 
You put the regex of the pattern that will repeat in between the parentheses.

To specify where that repeat string will appear, you use a backslash (\) and then a number. 
This number starts at 1 and increases with each additional capture group you use. An example would be \1 to match the first group.

The example below matches any word that occurs twice separated by a space:

let repeatStr = "regex regex";
let repeatRegex = /(\w+)\s\1/;
repeatRegex.test(repeatStr); // Returns true
repeatStr.match(repeatRegex); // Returns ["regex regex", "regex"]
Using the .match() method on a string will return an array with the string it matches, along with its capture group.


//Regular Expressions: Use Capture Groups to Search and Replace
Searching is useful. However, you can make searching even more powerful when it also changes (or replaces) the text you match.

You can search and replace text in a string using .replace() on a string. The inputs for .replace() is first the regex pattern you want to search for. 
The second parameter is the string to replace the match or a function to do something.

let wrongText = "The sky is silver.";
let silverRegex = /silver/;
wrongText.replace(silverRegex, "blue");
// Returns "The sky is blue."
You can also access capture groups in the replacement string with dollar signs ($).

"Code Camp".replace(/(\w+)\s(\w+)/, '$2 $1');
// Returns "Camp Code"




{N} — Matches exactly N occurrences of the preceding regular expression.
var regex = /go{2}d/;
console.log(regex.test('good'));
// true
console.log(regex.test('god'));
// false
{N,} — Matches at least N occurrences of the preceding regular expression.
var regex = /go{2,}d/;
console.log(regex.test('good'));
// true
console.log(regex.test('goood'));
// true
console.log(regex.test('gooood'));
// true
{N,M} — Matches at least N occurrences and at most M occurrences of the preceding regular expression (where M > N).
var regex = /go{1,2}d/;
console.log(regex.test('god'));
// true
console.log(regex.test('good'));
// true
console.log(regex.test('goood'));
// false



+ — Matches the preceding expression 1 or more times.
var regex = /\d+/;
console.log(regex.test('8'));
// true
console.log(regex.test('88899'));
// true
console.log(regex.test('8888845'));
// true
* —Matches the preceding expression 0 or more times.
var regex = /go*d/;
console.log(regex.test('gd'));
// true
console.log(regex.test('god'));
// true
console.log(regex.test('good'));
// true
console.log(regex.test('goood'));
// true
? — Matches the preceding expression 0 or 1 time, that is preceding pattern is optional.
var regex = /goo?d/;
console.log(regex.test('god'));
// true
console.log(regex.test('good'));
// true
console.log(regex.test('goood'));
// false



Meta-characters — Meta-characters are characters with a special meaning. There are many meta character but I am going to cover the most important ones here.
\d — Match any digit character ( same as [0-9] ).
\w — Match any word character. A word character is any letter, digit, and underscore. (Same as [a-zA-Z0–9_] ) i.e alphanumeric character.
\s — Match a whitespace character (spaces, tabs etc).
\t — Match a tab character only.
\b — Find a match at beginning or ending of a word. Also known as word boundary.
. — (period) Matches any character except for newline.
\D — Match any non digit character (same as [^0–9]).
\W — Match any non word character (Same as [^a-zA-Z0–9_] ).
\S — Match a non whitespace character.


Advanced
(x) — Matches x and remembers the match. These are called capturing groups. This is also used to create sub expressions within a regular expression. For example :-
var regex = /(foo)bar\1/;
console.log(regex.test('foobarfoo'));
// true
console.log(regex.test('foobar'));
// false
\1 remembers and uses that match from first subexpression within parentheses.
(?:x) — Matches x and does not remember the match. These are called non capturing groups. Here \1 won’t work, it will match the literal \1.
var regex = /(?:foo)bar\1/;
console.log(regex.test('foobarfoo'));
// false
console.log(regex.test('foobar'));
// false
console.log(regex.test('foobar\1'));
// true
x(?=y) — Matches x only if x is followed by y. Also called positive look ahead. For example:
var regex = /Red(?=Apple)/;
console.log(regex.test('RedApple'));
// true
In the above example, match will occur only if Redis followed by Apple.

-------------------------------------------------------------------------------------------------------------------------------------------------------
//Basic Data Structures: Remove Items Using splice()Passed
Ok, so weve learned how to remove elements from the beginning and end of arrays using shift() and pop(), but what if we want to remove an element from 
somewhere in the middle? Or remove more than one element at once? Well, thats where splice() comes in. splice() allows us to do just that: remove any 
number of consecutive elements from anywhere in an array.

splice() can take up to 3 parameters, but for now, well focus on just the first 2. The first two parameters of splice() are integers which represent 
indexes, or positions, of the array that splice() is being called upon. And remember, arrays are zero-indexed, so to indicate the first element of an array,
e would use 0. splice()s first parameter represents the index on the array from which to begin removing elements, while the second parameter indicates 
the number of elements to delete. For example:

let array = [today', 'was', 'not', so', 'great'];

array.splice(2, 2);
// remove 2 elements beginning with the 3rd element
// array now equals [today', 'was', 'great']
splice() not only modifies the array its being called on, but it also returns a new array containing the value of the removed elements:

let array = ['I', 'am', 'feeling', 'really', 'happy'];

let newArray = array.splice(3, 2);
// newArray equals ['really', 'happy']

Basic Data Structures: Add Items Using splice()Passed
Remember in the last challenge we mentioned that splice() can take up to three parameters? Well, you can use the third parameter, comprised of one or more element(s), to add to the array. This can be incredibly useful for quickly switching out an element, or a set of elements, for another.

const numbers = [10, 11, 12, 12, 15];
const startIndex = 3;
const amountToDelete = 1;

numbers.splice(startIndex, amountToDelete, 13, 14);
// the second entry of 12 is removed, and we add 13 and 14 at the same index
console.log(numbers);
// returns [ 10, 11, 12, 13, 14, 15 ]
Here we begin with an array of numbers. We then pass the following to splice(). 
The index at which to begin deleting elements (3), the number of elements to be deleted (1), and the elements (13, 14) to be inserted at that same index. 
Note that there can be any number of elements (separated by commas) following amountToDelete, each of which gets inserted.


//Basic Data Structures: Copy Array Items Using slice()
The next method we will cover is slice(). slice(), rather than modifying an array, copies, or extracts, a given number of elements to a new array, 
leaving the array it is called upon untouched. slice() takes only 2 parameters — the first is the index at which to begin extraction, 
and the second is the index at which to stop extraction (extraction will occur up to, but not including the element at this index). Consider this:

let weatherConditions = ['rain', snow', sleet', 'hail', 'clear'];

let todaysWeather = weatherConditions.slice(1, 3);
// todaysWeather equals [snow', sleet'];
// weatherConditions still equals ['rain', snow', sleet', 'hail', 'clear']

//Basic Data Structures: Copy an Array with the Spread Operator
While slice() allows us to be selective about what elements of an array to copy, among several other useful tasks, ES6s new spread operator allows us to easily copy all of an arrays elements, in order, with a simple and highly readable syntax. The spread syntax simply looks like this: ...

In practice, we can use the spread operator to copy an array like so:

let thisArray = [true, true, undefined, false, null];
let thatArray = [...thisArray];
// thatArray equals [true, true, undefined, false, null]
// thisArray remains unchanged, and is identical to thatArray


//Basic Data Structures: Combine Arrays with the Spread Operator
Another huge advantage of the spread operator, is the ability to combine arrays, or to insert all the elements of one array into another, at any index. With more traditional syntaxes, we can concatenate arrays, but this only allows us to combine arrays at the end of one, and at the start of another. Spread syntax makes the following operation extremely simple:

let thisArray = [sage', 'rosemary', 'parsley', thyme'];

let thatArray = ['basil', 'cilantro', ...thisArray, 'coriander'];
// thatArray now equals ['basil', 'cilantro', sage', 'rosemary', 'parsley', thyme', 'coriander']
Using spread syntax, we have just achieved an operation that would have been more complex and more verbose had we used traditional methods.

-------------------------------

//Basic Data Structures: Iterate Through the Keys of an Object with a for...in Statement
Sometimes you may need to iterate through all the keys within an object. This requires a specific syntax in JavaScript called a for...in statement. For our users object, this could look like:

for (let user in users) {
  console.log(user);
}

// logs:
Alan
Jeff
Sarah
Ryan
In this statement, we defined a variable user, and as you can see, this variable was reset during each iteration to each of the objects keys as the statement looped through the object, resulting in each users name being printed to the console. NOTE: Objects do not maintain an ordering to stored keys like arrays do; thus a keys position on an object, or the relative order in which it appears, is irrelevant when referencing or accessing that key.


-------------------------------------------
//Reverse a String With Built-In Functions — with split(), reverse() and join()

	For this solution, you will use three methods: the String.prototype.split() method, the Array.prototype.reverse() method and the Array.prototype.join() method.
	The split() method splits a String object into an array of string by separating the string into sub strings.
	The reverse() method reverses an array in place. The first array element becomes the last and the last becomes the first.
	The join() method joins all elements of an array into a string.
	


	
-------------------------------------------------------------------------------------------------------------------------------------------------------
//Object Oriented Programming


//Object Oriented Programming: Create a Method on an Object
Objects can have a special type of property, called a method.
Methods are properties that are functions. This adds different behavior to an object. Here is the duck example with a method:

let duck = {
  name: "Aflac",
  numLegs: 2,
  sayName: function() {return "The name of this duck is " + duck.name + ".";}
};
duck.sayName();


//Object Oriented Programming: Define a Constructor Function
Constructors are functions that create new objects. They define properties and behaviors that will belong to the new object. Think of them as a blueprint for the creation of new objects.

Here is an example of a constructor:

function Bird() {
  this.name = "Albert";
  this.color = "blue";
  this.numLegs = 2;
}
This constructor defines a Bird object with properties name, color, and numLegs set to Albert, blue, and 2, respectively. Constructors follow a few conventions:

Constructors are defined with a capitalized name to distinguish them from other functions that are not constructors.
Constructors use the keyword this to set properties of the object they will create. Inside the constructor, this refers to the new object it will create.
Constructors define properties and behaviors instead of returning a value as other functions might.


//Object Oriented Programming: Use a Constructor to Create Objects
Heres the Bird constructor from the previous challenge:                                                                                                        

function Bird() {
  this.name = "Albert";
  this.color  = "blue";
  this.numLegs = 2;
  // "this" inside the constructor always refers to the object being created
}

let blueBird = new Bird();
Notice that the new operator is used when calling a constructor. This tells JavaScript to create a new instance of Bird called blueBird. 
Without the new operator, 
this inside the constructor would not point to the newly created object, giving unexpected results. Now blueBird has all the properties defined 
inside the Bird constructor:

blueBird.name; // => Albert
blueBird.color; // => blue
blueBird.numLegs; // => 2
Just like any other object, its properties can be accessed and modified:

blueBird.name = 'Elvira';
blueBird.name; // => Elvira


//Object Oriented Programming: Extend Constructors to Receive Arguments
The Bird and Dog constructors from last challenge worked well. However, notice that all Birds that are created with the Bird constructor are 
automatically named Albert, are blue in color, and have two legs. What if you want birds with different values for name and color? 
Its possible to change the properties of each bird manually but that would be a lot of work:

let swan = new Bird();
swan.name = "Carlos";
swan.color = "white";
Suppose you were writing a program to keep track of hundreds or even thousands of different birds in an aviary. 
It would take a lot of time to create all the birds, then change the properties to different values for every one. 
To more easily create different Bird objects, you can design your Bird constructor to accept parameters:

function Bird(name, color) {
  this.name = name;
  this.color = color;
  this.numLegs = 2;
}
Then pass in the values as arguments to define each unique bird into the Bird constructor: let cardinal = new Bird("Bruce", "red"); 
This gives a new instance of Bird with name and color properties set to Bruce and red, respectively. The numLegs property is still set to 2. 
The cardinal has these properties:

cardinal.name // => Bruce
cardinal.color // => red
cardinal.numLegs // => 2
The constructor is more flexible. Its now possible to define the properties for each Bird at the time it is created, 
which is one way that JavaScript constructors are so useful. They group objects together based on shared characteristics and behavior 
and define a blueprint that automates their creation.


//Object Oriented Programming: Verify an Objects Constructor with instanceof
Anytime a constructor function creates a new object, that object is said to be an instance of its constructor. 
JavaScript gives a convenient way to verify this with the instanceof operator. instanceof allows you to compare an object to a constructor, 
returning true or false based on whether or not that object was created with the constructor. Heres an example:

let Bird = function(name, color) {
  this.name = name;
  this.color = color;
  this.numLegs = 2;
}

let crow = new Bird("Alexis", "black");

crow instanceof Bird; // => true
If an object is created without using a constructor, instanceof will verify that it is not an instance of that constructor:

let canary = {
  name: "Mildred",
  color: "Yellow",
  numLegs: 2
};

canary instanceof Bird; // => false


//Object Oriented Programming: Understand Own Properties
In the following example, the Bird constructor defines two properties: name and numLegs:

function Bird(name) {
  this.name  = name;
  this.numLegs = 2;
}

let duck = new Bird("Donald");
let canary = new Bird("Tweety");
name and numLegs are called own properties, because they are defined directly on the instance object. That means that duck and canary each 
has its own separate copy of these properties. In fact every instance of Bird will have its own copy of these properties. The following code adds all 
of the own properties of duck to the array ownProps:

let ownProps = [];

for (let property in duck) {
  if(duck.hasOwnProperty(property)) {
    ownProps.push(property);
  }
}

console.log(ownProps); // prints [ "name", "numLegs" ]


//Object Oriented Programming: Use Prototype Properties to Reduce Duplicate Code  (Static of js for variables)
Since numLegs will probably have the same value for all instances of Bird, you essentially have a duplicated variable numLegs inside each Bird instance.
This may not be an issue when there are only two instances, but imagine if there are millions of instances. That would be a lot of duplicated variables.
A better way is to use Bird’s prototype. Properties in the prototype are shared among ALL instances of Bird. Here is how to add numLegs to the Bird prototype:

Bird.prototype.numLegs = 2;
Now all instances of Bird have the numLegs property.

console.log(duck.numLegs);  // prints 2
console.log(canary.numLegs);  // prints 2
Since all instances automatically have the properties on the prototype, think of a prototype as a "recipe" for creating objects. Note that the prototype for 
duck and canary is part of the Bird constructor as Bird.prototype. Nearly every object in JavaScript has a prototype property which is part of the 
constructor function that created it.


//Object Oriented Programming: Iterate Over All Properties
You have now seen two kinds of properties: own properties and prototype properties. Own properties are defined directly on the object instance itself. And prototype properties are defined on the prototype.

function Bird(name) {
  this.name = name;  //own property
}

Bird.prototype.numLegs = 2; // prototype property

let duck = new Bird("Donald");
Here is how you add ducks own properties to the array ownProps and prototype properties to the array prototypeProps:

let ownProps = [];
let prototypeProps = [];

for (let property in duck) {
  if(duck.hasOwnProperty(property)) {
    ownProps.push(property);
  } else {
    prototypeProps.push(property);
  }
}

console.log(ownProps); // prints ["name"]
console.log(prototypeProps); // prints ["numLegs"]


//Object Oriented Programming: Understand the Constructor Property
There is a special constructor property located on the object instances duck and beagle that were created in the previous challenges:

let duck = new Bird();
let beagle = new Dog();

console.log(duck.constructor === Bird);  //prints true
console.log(beagle.constructor === Dog);  //prints true
Note that the constructor property is a reference to the constructor function that created the instance. 
The advantage of the constructor property is that its possible to check for this property to find out what kind of object it is. 
Heres an example of how this could be used:

function joinBirdFraternity(candidate) {
  if (candidate.constructor === Bird) {
    return true;
  } else {
    return false;
  }
}
Note
Since the constructor property can be overwritten (which will be covered in the next two challenges) it’s generally better to use the instanceof
method to check the type of an object.

//Object Oriented Programming: Change the Prototype to a New Object
Up until now you have been adding properties to the prototype individually:

Bird.prototype.numLegs = 2;
This becomes tedious after more than a few properties.

Bird.prototype.eat = function() {
  console.log("nom nom nom");
}

Bird.prototype.describe = function() {
  console.log("My name is " + this.name);
}
A more efficient way is to set the prototype to a new object that already contains the properties. This way, the properties are added all at once:

Bird.prototype = {
  numLegs: 2, 
  eat: function() {
    console.log("nom nom nom");
  },
  describe: function() {
    console.log("My name is " + this.name);
  }
};


//Object Oriented Programming: Use Inheritance So You Don't Repeat Yourself
There's a principle in programming called Don't Repeat Yourself (DRY). 
The reason repeated code is a problem is because any change requires fixing code in multiple places. 
This usually means more work for programmers and more room for errors.

Notice in the example below that the describe method is shared by Bird and Dog:

Bird.prototype = {
  constructor: Bird,
  describe: function() {
    console.log("My name is " + this.name);
  }
};

Dog.prototype = {
  constructor: Dog,
  describe: function() {
    console.log("My name is " + this.name);
  }
};
The describe method is repeated in two places. The code can be edited to follow the DRY principle by creating a supertype (or parent) called Animal:

function Animal() { };

Animal.prototype = {
  constructor: Animal, 
  describe: function() {
    console.log("My name is " + this.name);
  }
};
Since Animal includes the describe method, you can remove it from Bird and Dog:

Bird.prototype = {
  constructor: Bird
};

Dog.prototype = {
  constructor: Dog
};


//Object Oriented Programming: Use Closure to Protect Properties Within an Object from Being Modified Externally
In the previous challenge, bird had a public property name. It is considered public because it can be accessed and changed outside of birds definition.

let bird = {
  name: "Donald",
  numLegs: 2
};

bird.name = "Duffy";
Therefore, any part of your code can easily change the name of bird to any value. Think about things like passwords and bank accounts being easily 
changeable by any part of your codebase. That could cause a lot of issues.

The simplest way to make this public property private is by creating a variable within the constructor function. 
This changes the scope of that variable to be within the constructor function versus available globally. This way, 
the variable can only be accessed and changed by methods also within the constructor function.

function Bird() {
  let hatchedEgg = 10; // private variable

  /* publicly available method that a bird object can use */
  this.getHatchedEggCount = function() { 
    return hatchedEgg;
  };
}
let ducky = new Bird();
ducky.getHatchedEggCount(); // returns 10
Here getHatchedEggCount is a privileged method, because it has access to the private variable hatchedEgg. 
This is possible because hatchedEgg is declared in the same context as getHatchedEggCount. In JavaScript, 
a function always has access to the context in which it was created. This is called closure.


//Object Oriented Programming: Understand the Immediately Invoked Function Expression (IIFE)
A common pattern in JavaScript is to execute a function as soon as it is declared:

(function () {
  console.log("Chirp, chirp!");
})(); // this is an anonymous function expression that executes right away
// Outputs "Chirp, chirp!" immediately

Note that the function has no name and is not stored in a variable. 
The two parentheses () at the end of the function expression cause it to be immediately executed or invoked. 
This pattern is known as an immediately invoked function expression or IIFE.



//Object Oriented Programming: Use an IIFE to Create a Module
An immediately invoked function expression (IIFE) is often used to group related functionality into a single object or module. 
For example, an earlier challenge defined two mixins:

function glideMixin(obj) {
  obj.glide = function() {
    console.log("Gliding on the water");
  };
}
function flyMixin(obj) {
  obj.fly = function() {
    console.log("Flying, wooosh!");
  };
}
We can group these mixins into a module as follows:

let motionModule = (function () {
  return {
    glideMixin: function(obj) {
      obj.glide = function() {
        console.log("Gliding on the water");
      };
    },
    flyMixin: function(obj) {
      obj.fly = function() {
        console.log("Flying, wooosh!");
      };
    }
  }
})(); // The two parentheses cause the function to be immediately invoked
Note that you have an immediately invoked function expression (IIFE) that returns an object motionModule. 
This returned object contains all of the mixin behaviors as properties of the object. The advantage of the module pattern is that all of the motion 
behaviors can be packaged into a single object that can then be used by other parts of your code. Here is an example using it:

motionModule.glideMixin(duck);
duck.glide();