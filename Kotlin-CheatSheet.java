//Kotlin Keywords, Soft Keywords and Identifiers
There are certain words in Kotlin that have special meaning and cannot be used as identifiers(variable name, function name, class name etc). These words are called reserved words or keywords. In this guide, we will learn about keywords and identifiers.

We have two types of keywords:
1. Hard Keywords
2. Soft Keywords

1. Hard Keywords
	These keywords cannot be used as identifiers. For example
	This is valid:

	//valid variable name
	val myvar = 5
	
	This is invalid:
	//error: "else" cannot be used as a variable name
	val else = 5
	
	as	class	break	continue	do	else
	for	fun	false	if	in	interface
	super	return	object	package	null	is
	try	throw	true	this	typeof	typealias
	when	while	val	var		
	
2. Soft Keywords
	Soft keywords are the keywords that are used only in a certain context which means we can use them as identifier. Apart from the above list of keywords, there are other keywords that can be used as identifiers. For example, “by” is a soft keyword which delegates the implementation of an interface to another object. We can use the keyword “by” as identifier as well.

	//valid code
	fun main(args: Array) {
		val by=10
		println(by+10)
	}
	There are several other soft keywords available in Kotlin such as catch, get, finally, field etc.
_____________________________________________________________________________________________________________________________________________________________

//Kotlin Variables and Data Types

There are two types of variables – mutable and immutable. An immutable variable is one whose value cannot be changed, also known as unchangeable or read-only variable. On the other hand the value of the mutable variable can be changed.

	//Immutable variable: val keyword
	Immutable variable is declared using val keyword in Kotlin. In this example, we have declared an immutable variable myName using val keyword and later displayed the value of it.
	
	package beginnersbook

	fun main(args : Array<String>){
		/**
		* This is an immutable variable
		* also called unchangeable variable
		* or read-only variable.
		*/
		val myName = "Chaitanya"
		println("My Name is: "+myName)
	}


	//Mutable variable: var keyword
	Unlike immutable variable, we can change the value of a mutable variable. In kotlin, we use var keyword to declare a mutable variable. Lets take an example to understand this.

	In this example we have declared a mutable variable using var keyword, to demonstrate that we can change the value of it, we have reassigned a different value to myName variable.

	package beginnersbook

	fun main(args : Array<String>){
		/**
		* This is an mutable variable
		* we can change the value of this
		* variable
		*/
		var myName = "Chaitanya"
		myName="Chaitanya Singh"
		println("My Name is: "+myName)
	}
	
	//Type inference
	var website: String = "beginnersbook"

//Kotlin Data Types
1. Numbers – Byte, Short, Int, Long, Float, Double
2. Boolean – True, false
3. Characters
4. Arrays
5. Strings

//we can also know the min and max value of a data type like this:
	var bigByte: Byte = Byte.MAX_VALUE
	var smallByte: Byte = Byte.MIN_VALUE

_____________________________________________________________________________________________________________________________________________________________

//Kotlin Type Casting with examples

Type casting is a process of converting one data type to another type, for example – converting int to long, long to double etc. In this tutorial we will learn how to do type conversion in Kotlin.

	val num1: Int = 101
	val num2: Long = num1.toLong()
	
	1. toChar() – To convert a type to Char type.
	2. toInt() – To convert a type to Int type.
	3. toLong() – To convert a type to Long type.
	4. toFloat() – To convert a type to Float type.
	5. toDouble() – To convert a type to Double type.
	6. toByte() – To convert a type to Byte type.
	7. toShort() – To convert a type to Short type.

_____________________________________________________________________________________________________________________________________________________________
//How to take Input from User

Example 1: Display String entered by user
In this example, we will take the input from user and display it in the output. Here we are using readLine() function to read the string entered on console.

fun main(args: Array<String>) {
    print("Write anything here: ")

    val enteredString = readLine()
    println("You have entered this: $enteredString")
}
Output:

Write anything here: welcome to beginnersbook.com
You have entered this: welcome to beginnersbook.com

Example 2: Taking input and converting it into a different type

As we have seen in the above example that the readLine() function reads the input as a String. If we want to take the input in a different type such as integer, long, double then we need to either explicitly convert the input to another type or use the java Scanner class.

//Taking the input as String and converting it to an int
	Here we are explicitly converting the input to an integer number.

	fun main(args: Array<String>) {
		print("Write any number: ")

		val number = Integer.valueOf(readLine())
		println("The entered number is: $number")
	}
	Output:

		Write any number: 101
		The entered number is: 101

//Taking the input other than String using Scanner class
In this example we are taking the input as an integer and float using the nextInt() and nextFloat() functions respectively. Similarly we can use nextLong(), nextDouble() and nextBoolean() methods to take long, double and boolean inputs respectively.

	import java.util.Scanner
	fun main(args: Array<String>) {
    //creating Scanner object
    val read = Scanner(System.`in`)

    //Taking integer input
    println("Enter an integer number: ")
    var num1 = read.nextInt()

    //Taking float input
    println("Enter a float number: ")
    var num2 = read.nextFloat()

    //Displaying input numbers
    println("First Input Number: "+num1)
    println("Second Input Number: "+num2)
	}
	
	Output:
		Enter an integer number: 
		99
		Enter a float number: 
		10.55
		First Input Number: 99
		Second Input Number: 10.55
_____________________________________________________________________________________________________________________________________________________________
//Kotlin String

	/**
     * These Strings are Immutable which
     * means they are read-only and
     * unchangeable
     */
    val website = "BeginnersBook"

    /**
     * This is how we declare long strings
     */
    val longString = """Welcome to
        Beginnersbook.com"""


//Get String Length in Kotlin
	package beginnersbook

	fun main(args : Array<String>){

		var firstName = "Chaitanya"

		/**
		* String interpolation
		*/
		println("String Length: ${firstName.length}")

		/**
		* Or you can display like this
		*/
		println("String Length: " + firstName.length)
	}

//Compare Strings in Kotlin

		
    /**
     * true if equals, otherwise false
     */
    println("String Equals? : ${str1.equals(str2)}")

    /**
     * 0 if equals, otherwise false
     */
    println("String Equals? : ${str1.compareTo(str2)}")


//Access character in a string at a specific index

	fun main(args : Array<String>){

		var str = "BeginnersBook"

		println("3rd Index: ${str.get(3)}")

		/**
		* Another way of doing the same
		* This is the recommended way
		*/
		println("3rd Index: ${str[3]}")
	}


//Substring
	fun main(args : Array<String>){

		var str = "BeginnersBook"

		/**
		* Here fromIndex is inclusive and
		* toIndex is exclusive which means
		* 5th index char will not be included
		*/
		println("Index from 2 to 5: " +
		str.subSequence(2,5))
	}

//Check whether String contains another String
	fun main(args : Array<String>){

		var str = "beginnersbook.com"

		println("Contains .com: ${str.contains(".com")}")
	}
_____________________________________________________________________________________________________________________________________________________________

//Kotlin Array

Arrays in Kotlin are able to store multiple values of different data types. Of course if we want we can restrict the arrays to hold the values of a particular data types. In this guide, we will discuss about arrays in Kotlin.

var arr = arrayOf(10, "BeginnersBook", 10.99, 'A')

	//Array that can only hold integers

		var arr = arrayOf<Int>(1, 22, 55)

	//Array that can only hold strings

		var arr2 = arrayOf<String>("ab", "bc", "cd")
		
		
//Access Array elements in Kotlin

    var arr = arrayOf(10, "BeginnersBook", 10.99, 'A')

    /**
     * Displaying 4th element
     */
    println(arr[3])

    /**
     * modifying 4th element
     */
    arr[3] = 100

//Kotlin Array set() and get() functions

	//get()
	In the above example we have used the following statement to access the value of 4th element of the array.

	arr[3]
	We can rewrite the same statement using get function like this:

	arr.get(3)
	set()
	In the above example we have used the following statement to modify the value of 4th element in the array.

	arr[3] = 100
	We can rewrite the same statement using set() function like this:

	arr.set(3,100)

//Size of an array

	fun main(args : Array<String>){

		var arr = arrayOf(1, 2, 3, 4, 5)

		println("Size of Array arr is: ${arr.size}")

	}

//Check the element in an array

	fun main(args : Array<String>){

		var arr = arrayOf(1, 22, "CPS")

		println("Array contains CPS: ${arr.contains("CPS")}")

	}

//Kotlin Array first() and last() functions

	fun main(args : Array<String>){

		var arr = arrayOf(1, 22, "CPS")

		println("First Element: ${arr.first()}")
		println("Last Element: ${arr.last()}")

	}

//Finding the index of an element in an array
	
	println("Index of 22: ${arr.indexOf(22)}")

_____________________________________________________________________________________________________________________________________________________________

//Kotlin Ranges

	fun main(args : Array<String>){

		println("Number range:")

		for(num in 1..4){
			println(num)
		}

		println("Character range:")

		for(ch in 'A'..'E'){
			println(ch)
		}
	}
	
//Check element in Ranges
	fun main(args : Array<String>){
		val oneToTen = 1..10 	
		println("3 in oneToTen: ${3 in oneToTen}")
		println("11 in oneToTen: ${11 in oneToTen}")
	}

//Kotlin Range: rangeTo() and downTo() functions

	Instead of .. we can use these functions rangeTo() and downTo(), rangeTo() is for increasing order and downTo() is for decreasing order.
	
	fun main(args : Array<String>){
		val oneToFive = 1.rangeTo(5)
		val sixToThree = 6.downTo(3)

		println("rangeTo:")
		for(x in oneToFive){
			println(x)
		}

		println("downTo")
		for(n in sixToThree){
			println(n)
		}
	}

//Kotlin Range Step
	With the help of step() function we can define the interval between the values. By default the value of step is 1 so when we create range 1..10, it is 1, 2, 3,..10. However if we want a specific interval like 3 then we can define the range like this 1..10.step(3) this way the values would be 1 4 7 10. Lets take an example.
	
	fun main(args : Array<String>){

		val oneToTen = 1..10
		val odd = oneToTen.step(2)

		for(n in odd){
			println(n)
		}
	}

	Output:
	1	3	5	7	9
	
//Kotlin range reverse

	fun main(args : Array<String>){

		val oneToFive = 1..5
		for (n in oneToFive.reversed()){
			println(n)
		}
	}

_____________________________________________________________________________________________________________________________________________________________

//Kotlin When Expression with examples

	The when expression in Kotlin works same as switch case in other programming languages such as C, C++ and Java.
	
	//Kotlin when expression simple example
	
	fun main(args : Array<String>){
		var ch = 'A'
		when(ch){

			'A' -> println("A is a Vowel")
			'E' -> println("E is a Vowel")
			'I' -> println("I is a Vowel")
			'O' -> println("O is a Vowel")
			'U' -> println("U is a Vowel")

			else -> println("$ch is a Consonant")
		}
	}
	
	//We can also rewrite the same code in a more cleaner way like this:

    var ch = 'A'
    when(ch){

        'A', 'E', 'I', 'O', 'U' -> println("$ch is a Vowel")

        else -> println("$ch is a Consonant")
    }
	
	//Kotlin when expression with ranges
	
	fun main(args : Array<String>){
		var num = 78

		when(num) {
			in 1..9 -> println("$num is a single digit number")
			in 10..99 -> println("$num is a two digit number")
			in 100..999 -> println("$num is a three digit number")
			else -> println("$num has more than three digits")
		}
	}

_____________________________________________________________________________________________________________________________________________________________

//Kotlin for Loop with examples

	fun main(args : Array<String>){

		for(n in 10..15){
			println("Loop: $n")
		}
	}
	
//Kotlin for loop using Array

	fun main(args : Array<String>){
		
		val myArray = arrayOf("ab", "bc", "cd", "da")
		for (str in myArray){
			println(str)
		}
	}

//Kotlin for loop iterating though array indices

	fun main(args : Array<String>){

		val myArray = arrayOf("Steve", "Robin", "Kate", "Lucy")
		for (n in myArray.indices){
			println("myArray[$n]: ${myArray[n]}")
		}
	}

//Function withIndex() in for loop

In the above example we have iterated through the array using array indices. Another way of doing the same is with the use of withIndex() function.
	
	fun main(args : Array<String>){

		val myArray = arrayOf("Steve", "Robin", "Kate", "Lucy")
		for((index, value) in myArray.withIndex()){
			println("Value at Index $index is: $value")
		}
	}

_____________________________________________________________________________________________________________________________________________________________

//Kotlin Function with examples

Types of Function in Kotlin
	There are two types of functions in Kotlin:
		1. Standard Library Function
		2. User-defined functions

1. Standard Library Function
	The functions that are already present in the Kotlin standard library are called standard library functions or built-in functions or predefined functions. For example when we need to use the Math.floor() function we do not define the function because it is already present and we can directly call it in our code.
	
	
	fun main(args : Array<String>){
		var num = 16
		println("Square root of $num is: ${Math.sqrt(num.toDouble())}")

	}
	
2. User-defined functions
	
	fun sayHello(){
		println("Hello")
	}
	fun main(args : Array<String>){

		//Calling the function
		sayHello()

	}

//User defined function with arguments and return type
	Syntax:
	fun function_name(param1: data_type, param2: data_type, ...): return_type
	Lets create a user defined function that accepts the arguments and has a return type. In the following program we have declared a function sum. This function accepts variable number of arguments thats why we have used vararg, those arguments are of type integers and the return type of the function is also an integer.
	
	fun sum(vararg numbers: Int): Int
	{
		var sum = 0
		numbers.forEach {num -> sum +=  num}

		return sum
	}
	fun main(args : Array<String>){

		println("Sum: ${sum(10, 20, 30, 40)}")

	}	
	
// Kotlin Inline functions
An inline function can be defined inside the main() function. Lets take an example of inline function. In the following example we have defined an inline function sum which accepts two integer arguments num1 and num2 and return type is integer.

	fun main(args : Array<String>){

		val sum = {num1: Int, num2: Int -> num1 + num2}

		println("6 + 4 = ${sum(6,4)}")

	}	
	
_____________________________________________________________________________________________________________________________________________________________	
//Kotlin Default and Named Argument

We have already learned how a function can have parameters and we can pass the value of those parameters while calling the function, by using default arguments we can set the default value of those parameters while defining a function. Lets take an example to understand default arguments.

	fun main(args: Array<String>) {
		demo()
	}
	fun demo(number:Int= 100, ch: Char ='A'){
		print("Number is: $number and Character is: $ch")
	}
	
//Kotlin Named Arguments

	fun main(args: Array<String>) {
		demo(ch='Z')
	}
	fun demo(number:Int= 100, ch: Char ='A'){
		print("Number is: $number and Character is: $ch")
	}
	
_____________________________________________________________________________________________________________________________________________________________
	
//Kotlin Higher order function with example	
	
	Higher order function or higher level function can have another function as a parameter or return a function or can do both. Till now we have seen how we pass integer, string etc. as parameters to a function but in this guide, we will learn how we pass a function to another function. We will also see how a function returns another function.
	
//Kotlin Higher order function example: Passing a function to another function
	In the following example we are passing a function demo() to another function func(). To pass a function as a parameter to other function we use :: operator before function as shown in the following example.

		fun main(args: Array<String>) {

			func("BeginnersBook", ::demo)

		}

		fun func(str: String, myfunc: (String) -> Unit) {
			print("Welcome to Kotlin tutorial at ")
			myfunc(str)
		}
		fun demo(str: String) {
			println(str)
		}	
	
_____________________________________________________________________________________________________________________________________________________________

//Kotlin Higher order function example: function returns another function
	In the following example the custom function func is returning another function.

	To understand this code, lets look at the function func first, it accepts an integer parameter num and in the return area we have defined a function (Int) -> Int = {num2 -> num2 + num} so this is the other function which also accepts integer parameter and returns the sum of this parameter and num.

	You may be wondering why we have passed the value 20 as a parameter in sum, well this is because the function func returned the function so the sum is the function that will accept the int parameter. This is the same function that we have defined in the return area of function func.

		fun main(args: Array<String>) {

			val sum = func(10)
			println("10 + 20: ${sum(20)}")

		}

		fun func(num: Int): (Int) -> Int = {num2 -> num2 + num}
		
_____________________________________________________________________________________________________________________________________________________________
	
//Kotlin Exception Handling with examples

There are two types of exceptions:
	1. Checked exceptions that are declared as part of method signature and are checked at the compile time, for example IOException
	2. Unchecked exceptions do not need to be added as part of method signature and they are checked at the runtime, for example NullPointerException.
	
Note: In Kotlin all exceptions are unchecked.

		fun main(args: Array<String>) {
			try {

				var num = 10/0
				println("BeginnersBook.com")
				println(num)


			} catch (e: ArithmeticException) {
				println("Arithmetic Exception")
			} catch (e: Exception) {
				println(e)
			} finally {
				println("It will print in any case.")
			}
		}	
	
	//How to throw an exception in Kotlin
	
	fun main(args: Array<String>) {
		try{
			println("Before exception")
			throw Exception("Something went wrong here")
			println("After exception")
		}
		catch(e: Exception){
			println(e)

		}
		finally{
			println("You can't ignore me")
		}
	}
	
_____________________________________________________________________________________________________________________________________________________________

//How to create object of class

Example e = Example()
	
	
//How to access the data members and member functions	

	To access the data member number and member function calculateSquare of the class Example using the object e.
		//Access data member
		e.number
		//Access member function
		e.calculateSquare()
	
	
	
	class Example {

		// data member
		private var number: Int = 5

		// member function
		fun calculateSquare(): Int {
			return number*number
		}
	}

	fun main(args: Array<String>) {

		// create obj object of Example class
		val obj = Example()
		println("${obj.calculateSquare()}")
}	
	

_____________________________________________________________________________________________________________________________________________________________

//Kotlin Constructors with examples

Types of Constructor in Kotlin
	
1. Primary Constructor
A primary constructor is the easiest way to initialize the class. It is declared as part of the class header. In the following example we have declared a constructor (val name: String, var age: Int) as part of the class header. This is our primary constructor that initializes the name and age properties (data members) of class Student.

	fun main(args: Array<String>) {

		//creating the object of class Student
		val stu = Student("Chaitanya", 31)

		println("Student Name: ${stu.name}")
		println("Student Age: ${stu.age}")
	}

	class Student(val name: String, var age: Int) {
		//This is my class. For now I am leaving it empty
	}
	
	//Initializer Block inside Kotlin Constructor
	fun main(args: Array<String>) {

		val stu = Student("Chaitanya", 31)
		val stu2 = Student("Chaitanya")
		val stu3 = Student()

	}

	class Student(val name: String = "Student", var age: Int = 99) {
		val stuName: String
		var stuAge: Int
		init{
			if(name == "Student") {
				stuName = "NA"
				stuAge = 0
			}
			else {
				stuName = name.toUpperCase()
				stuAge = age
			}
			println("Student Name is: $stuName")
			println("Student Age is: $stuAge")
		}
	}

2. Kotlin Secondary Constructor

//Syntax of secondary constructor
	class Student {
		constructor(name: String) {
			// code inside constructor
		}
		constructor(name: String, age: Int) {
			// code inside constructor
		}
	}
	
//Example of secondary constructor

	fun main(args: Array<String>){
		val obj = Student ("Ajeet", 30)
	}

	class Student{
		constructor(name: String, age: Int){
			println("Student Name: ${name.toUpperCase()}")
			println("Student Age: $age")
		}
	}

//Calling one secondary constructor from another

	fun main(args: Array<String>){
		val obj = Student ("Ajeet")
	}
	class Student{
		constructor(name: String): this(name, 0){
			println("secondary constructor with one param")
		}
		constructor(name: String, age: Int){
			println("secondary constructor with two param")
			println("Student Name: ${name.toUpperCase()}")
			println("Student Age: $age")
		}
	}


//Kotlin Secondary Constructor example with parent and child class

	fun main(args: Array<String>){
		val stu = Student("Harry", 24)
	}

	open class College{

		constructor(name: String, age: Int){
			println("parent class constructor")
			println("Student Name: ${name.toUpperCase()}")
			println("Student Age: $age")
		}
	}
	class Student: College{
		constructor(name: String, age: Int): super(name,age){
			println("child class constructor")
			println("Student Name: $name")
			println("Student Age: $age")
		}
	}

_____________________________________________________________________________________________________________________________________________________________
//Kotlin Inheritance with examples

Note: By default all classes in Kotlin are final so you have to use the open annotation in the parent class, 
this tells the compiler that this class can be inherited by other classes.

	open class Animal(colour: String, age: Int) {
		init {
			println("Color is: $colour.")
			println("Age is: $age")
		}
	}

	class Dog(colour: String, age: Int): Animal(colour, age) {

		fun woof() {
			println("Dog makes sound of woof")
		}
	}

	class Cat(colour: String, age: Int): Animal(colour, age) {

		fun meow() {
			println("Cat makes sound of meow")
		}
	}

	class Horse(colour: String, age: Int): Animal(colour, age) {

		fun neigh() {
			println("Horse makes sound of neigh")
		}
	}

	fun main(args: Array<String>) {
		val d = Dog("Black",4)
		d.woof()
		val c = Cat("White", 1)
		c.meow()
		val h = Horse("Brown", 8)
		h.neigh()
	}

//Overriding member functions and properties in Kotlin

	open class Animal() {
		open fun sound() {
			println("Animal makes a sound")
		}
	}

	class Dog: Animal() {
		override fun sound() {
			println("Dog makes a sound of woof woof")
		}
	}

	fun main(args: Array<String>) {
		val d = Dog()
		d.sound()
	}


	
	open class Animal() {
		open var colour: String = "White"
	}

	class Dog: Animal() {
		override var colour: String = "Black"
		fun sound() {
			println("Dog makes a sound of woof woof")
		}
	}

	fun main(args: Array<String>) {
		val d = Dog()
		d.sound()
		println("${d.colour}")
	}

//Calling data members and member functions of base class from child class
	open class Parent() {
		open var num: Int = 100
		open fun demo(){
			println("demo function of parent class")
		}
	}

	class Child: Parent() {
		override var num: Int  = 101
		override fun demo() {
			super.demo()
			println("demo function of child class")
		}
		fun demo2(){
			println(super.num)
		}
	}

	fun main(args: Array<String>) {
		val obj = Child()
		obj.demo()
		obj.demo2()
	}

_____________________________________________________________________________________________________________________________________________________________
//Kotlin Visibility Modifiers

public: visible everywhere, this is the default visibility modifier in Kotlin which means if you do not specify the modifier, it is by default public.
private: visible inside the file containing the declaration. If a data member or member function is declared private in a class then they are visible in the class only.
protected: Visible inside class and subclasses.
internal: visible inside the same module.


//Kotlin Abstract Class with examples

An abstract class cannot be instantiated, which means we cannot create the object of an abstract class. Unlike other class, an abstract class is always open so we do not need to use the open keyword.

Points to Note:
1. We cannot create the object of an abstract class.
2. Property and member function of an abstract class are by default non-abstract. If you want to override these in the child class then you need to use open keyword for them.
3. If a member function is abstract then it must be implemented in the child class. An abstract member function doesn’t have a body only method signature, the implementation is done in the child class.

//Abstract class Example
	abstract class Student(name: String, age: Int) {

		init {
			println("Student name is: $name")
			println("Student age is: $age")
		}

		//non-abstract function
		fun demo() {
			println("Non-abstract function of abstract class")
		}

		//abstract function
		abstract fun func(message: String)
	}

	class College(name: String, age: Int): Student(name, age) {

		override fun func(message: String) {
			println(message)
		}
	}

	fun main(args: Array<String>) {
		val obj = College("Chaitanya", 31)
		obj.func("I'm a Blogger")
		obj.demo()
	}

//Kotlin Interfaces with examples
In this guide, we will learn about interfaces. Similar to an abstract class, an interface cannot be instantiated because it doesn’t have any constructor.

Points to Note:
1. An interface can have both abstract and non-abstract function.
2. An interface can only have abstract property (data member), non-abstract properties are not allowed.
3. A class can implement more than one interfaces.
4. All abstract properties and abstract member functions of an interface must be overriden in the classes that implement it.

	//Kotlin Interfaces Example
	interface X {

		fun demoX() {
			println("demoX function")
		}
		fun funcX()
	}

	interface Y  {
		fun demoY() {
			println("demoY function")
		}
		fun funcY()
	}

	// This class implements X and Y interfaces
	class MyClass: X, Y {
		override fun funcX() {
			println("Hello")
		}
		override fun funcY() {
			println("Hi")
		}

	}

	fun main(args: Array<String>) {
		val obj = MyClass()
		obj.demoX()
		obj.demoY()
		obj.funcX()
		obj.funcY()
	}

//When multiple interfaces have same method name
	interface X {

		fun demo() {
			println("demoX function")
		}
	}

	interface Y  {
		fun demo() {
			println("demoY function")
		}
	}

	// This class implements X and Y interfaces
	class MyClass: X, Y{
		override fun demo() {
			super<Y>.demo()
		}
	}

	fun main(args: Array<String>) {
		val obj = MyClass()
		obj.demo()
	}

_____________________________________________________________________________________________________________________________________________________________

//Kotlin Nested and Inner Class with examples

	class OuterClass {

		val oStr = "Outer Class"

		class NestedClass {
			val nStr = "Nested Class"
			fun demo() = "demo() function of nested class"
		}
	}

	fun main(args: Array<String>) {
		// accessing data member of nested class
		println(OuterClass.NestedClass().nStr)

		// accessing member function of nested class
		println(OuterClass.NestedClass().demo())

		// creating object of the Nested class
		val obj = OuterClass.NestedClass()
		println(obj.demo())
	}

//Kotlin Nested class – cannot access members of outer class
The following program will throw compilation error because a nested class does not have access to the members of outer class. 
Here the nested class is trying to access the member oStr which belongs to outer class, thus we are getting a compilation error. 
We can solve this issue by using inner class which is discussed in next section of this same article.

	class OuterClass {

		val oStr = "Outer Class"

		class NestedClass {
			val nStr = "Nested Class"
			fun demo() = "demo() function using $oStr"
		}
	}

	fun main(args: Array<String>) {
		println(OuterClass.NestedClass().demo())
	}

//Inner class

	Kotlin inner class is declared using inner modifier. 
	Inner classes have access to the members of the outer class. 
	Lets take the same example that we have seen above using the inner class instead of nested class. 
	Here we are accessing the member oStr of outer class using the inside inner class.

	class OuterClass {

		val oStr = "Outer Class"

		inner class InnerClass {
			val nStr = "Nested Class"
			fun demo() = "demo() function using $oStr"
		}
	}

	fun main(args: Array<String>) {
		val o = OuterClass()
		println("${o.InnerClass().demo()}")

		val i = OuterClass().InnerClass()
		println("${i.demo()}")
	}


_____________________________________________________________________________________________________________________________________________________________
//Kotlin Data Class with examples

In Kotlin, you can create a data class to hold the data. 
The reason why would you want to mark a class as data is to let compiler know that you are creating this class for holding the data, 
compiler then creates several functions automatically for your data class which would be helpful in managing data. 
In this guide, we will learn data class and the functions that are automatically generated by compiler.

Automatically generated functions for data class in Kotlin
For now I am just mentioning the name of the functions here, we will see each one of them with the help of examples.
1. equals()
2. hashCode()
3. toString()
4. copy()
5. componentN()

//Kotlin Data Class Requirements
In order to mark a class as data, the class must fulfil certain requirements. The requirements are as follows:
1. The primary constructor of the data class must have at least one parameter. Also, the parameters are either marked val or var.
2. The class cannot be marked as open, abstract, sealed or inner.
3. The class can extend (inherit) other class and it can also implements other interfaces.


//Kotlin Data Class Example
In the following example, we have a class Student, which we have marked as data class. Since we have declared this class as data, compiler has automatically generated several functions such as copy(), toString(), equals() etc. for this class, we will discuss these functions in next few examples.

data class Student(val name: String, val age: Int)

fun main(args: Array<String>) {
    val stu = Student("Chaitanya", 31)
    val stu2 = Student("Ajeet", 30)
    println("Student Name is: ${stu.name}")
    println("Student Age is:  ${stu.age}")
    println("Student Name is: ${stu2.name}")
    println("Student Age is:  ${stu2.age}")
}

//Data class copy() method
By using copy() method in data class, we can copy few of the properties of other objects. Lets take an example. 
Here we have an object stu of Student class that contains the name, 
age and subject details of a Student “Steve” and we have created another object stu2 with the name “Lucy” and copying the age and subject 
details of object stu using the copy() method of data class.

data class Student(val name: String, val age: Int, val sub: String)

fun main(args: Array<String>) {
    val stu = Student("Steve", 26, "Math")

    // copying the age and subject from object stu
    val stu2 = stu.copy(name = "Lucy")

    println("stu: Name ${stu.name}, Age ${stu.age} & Subject ${stu.sub}")
    println("stu2: Name ${stu2.name}, Age ${stu2.age} & Subject ${stu2.sub}")
}

//Data class componentN() method
The componentN() method of data class destructure an object into a number of variables. In the following example 
we have an object stu of Student class and we are destructuring the object into number of variables using the componentN() method. 
The component1() method returns the value of the first property of the object, component2() returns the value of second property and so on.

data class Student(val name: String, val age: Int, val sub: String)

fun main(args: Array<String>) {
    val stu = Student("Steve", 26, "Math")
    val name = stu.component1()
    val age = stu.component2()
    val sub = stu.component3()
    println("Name is: $name")
    println("Age is: $age")
    println("SUbject is: $sub")
}

_____________________________________________________________________________________________________________________________________________________________
//Kotlin Sealed Class with examples

A sealed class is used for representing restricted class hierarchy where an object or a value can have one of the types from a limited set of values. You can think of a sealed class as an extension of enum class. The set of values in enum class is also restricted, 
however an enum constant can have only single instance while a subclass of a sealed class can have multiple instances.

//Why we need a Sealed class?
Lets first understand the need of a sealed class. In the following example we have a class Color which has three subclasses Red, Orange and Blue.
Here the when expression in eval() method must use a else branch else we will get a compilation error. 
Now if we try to add a subclass to the class Color, the code in the else branch will execute which is a default code. 
The compiler should warn us that the code for the newly added sub class doesn’t exist and thus we should get a warning or error for 
adding a new subclass without adding the logic in when expression. This problem is solved using Sealed class.

	open class Color{
		class Red : Color()
		class Orange : Color()
		class Blue : Color()
	}
	fun eval(c: Color) =
			when (c) {
				is Color.Red -> println("Paint in Red Color")
				is Color.Orange -> println("Paint in Orange Color")
				is Color.Blue -> println("Paint in Blue Color")
				else -> println("Paint in any Color")
			}

	fun main(args: Array<String>) {
		val r = Color.Red()
		eval(r)
	}
	

//Kotlin Sealed class Example
In Kotlin, sealed class is declared using the sealed keyword just before the class keyword in the class header.

Lets take the same example that we have seen above using the sealed class. Here we have solved the above problem by marking the class Color as sealed.

A sealed class as name suggests only take the values from a limited set of values. Here else branch is not mandatory in when expression because compiler
 knows that the class is sealed and it only needs the expression for the three derived classes, no default else branch needed. Now if we try to add 
 the new subclass to the class Color, it would not create unnecessary bugs rather it would throw an error.

For example if we add a new subclass White to the sealed class Color and do not add the expression for White subclass in when 
expression then we will get this error – Error:(12, 9) Kotlin: 'when' expression must be exhaustive, add necessary 'is White' branch or 'else' branch instead


	sealed class Color{
		class Red : Color()
		class Orange : Color()
		class Blue : Color()
	}
	fun eval(c: Color) =
			when (c) {
				is Color.Red -> println("Paint in Red Color")
				is Color.Orange -> println("Paint in Orange Color")
				is Color.Blue -> println("Paint in Blue Color")
			}

	fun main(args: Array<String>) {
		val r = Color.Red()
		eval(r)
	}
	
Rules of a Sealed class in Kotlin
1. We cannot create the object of a sealed class which means a sealed class cannot be instantiated.
2. All the subclasses of a sealed class must be declared within the same file where the sealed class is declared.
3. The constructor of sealed class is by default private and we cannot make it non-private.