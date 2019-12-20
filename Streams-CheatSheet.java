//Optional in java 8
1. Java Optional Class : Every Java Programmer is familiar with NullPointerException. It can crash your code. And it is very hard to avoid it without using too many null checks.
Java 8 has introduced a new class Optional in java.util package. It can help in writing a neat code without using too many null checks. By using Optional, we can specify alternate values to return or alternate code to run. This makes the code more readable because the facts which were hidden are now visible to the developer.
			// Java program without Optional Class 
			public class OptionalDemo{ 
				public static void main(String[] args) { 
					String[] words = new String[10]; 
					String word = words[5].toLowerCase(); 
					System.out.print(word); 
				} 
			} 			
			//Exception in thread "main" java.lang.NullPointerException
To avoid abnormal termination, we use the Optional class. In the following example, we are using Optional. So, our program can execute without crashing.
			// Java program with Optional Class 
			import java.util.Optional;   
			public class OptionalDemo{   
				public static void main(String[] args) {   
					String[] words = new String[10];   
					Optional<String> checkNull = Optional.ofNullable(words[5]);   
					if (checkNull.isPresent()) {   
						String word = words[5].toLowerCase();   
						System.out.print(word);   
					} else  
						System.out.println("word is null");   
				}   
			}
To throw null pointer under controlled cicumstances
	String nullName = null;
	String name = Optional.ofNullable(nullName).orElseThrow(NullPointerException::new);		
-----------------------------------------------------------------------------------------------
We can also create an Optional object with the static method of:

@Test
public void givenNonNull_whenCreatesNonNullable_thenCorrect() {
    String name = "baeldung";
    Optional<String> opt = Optional.of(name);
    assertTrue(opt.isPresent());
}
However, the argument passed to the of() method can't be null. Otherwise, we'll get a NullPointerException:

@Test(expected = NullPointerException.class)
public void givenNull_whenThrowsErrorOnCreate_thenCorrect() {
    String name = null;
    Optional.of(name);
}
But, in case we expect some null values, we can use the ofNullable() method:

@Test
public void givenNonNull_whenCreatesNullable_thenCorrect() {
    String name = "baeldung";
    Optional<String> opt = Optional.ofNullable(name);
    assertTrue(optionalName.isPresent());
}
By doing this, if we pass in a null reference, it doesn't throw an exception but rather returns an empty Optional object:	
		
--------------------------------------------------------------------------------------------------------------------------------------------------------		
//Streams
2. Stream Creation
There are many ways to create a stream instance of different sources. Once created, the instance will not modify its source, therefore allowing the creation of multiple instances from a single source.

	2.1. Empty Stream
	The empty() method should be used in case of a creation of an empty stream:
	
		Stream<String> streamEmpty = Stream.empty();
	
	2.2. Stream of Collection
	Stream can also be created of any type of Collection (Collection, List, Set):
	
		Collection<String> collection = Arrays.asList("a", "b", "c");
		Stream<String> streamOfCollection = collection.stream();
	
	2.3. Stream of Array
	Array can also be a source of a Stream:

		Stream<String> streamOfArray = Stream.of("a", "b", "c");
		
	They can also be created out of an existing array or of a part of an array:

		String[] arr = new String[]{"a", "b", "c"};
		Stream<String> streamOfArrayFull = Arrays.stream(arr);
		Stream<String> streamOfArrayPart = Arrays.stream(arr, 1, 3);

	2.4. Stream.builder()
	When builder is used the desired type should be additionally specified in the right part of the statement, otherwise the build() method will create an instance of the Stream<Object>:
	
		Stream<String> streamBuilder =
		Stream.<String>builder().add("a").add("b").add("c").build();
		
	2.5. Stream.generate()
	The generate() method accepts a Supplier<T> for element generation. As the resulting stream is infinite, developer should specify the desired size or the generate() method will work until it reaches the memory limit:
	
		Stream<String> streamGenerated = Stream.generate(() -> "element").limit(10);
		
	The code above creates a sequence of ten strings with the value – “element”.

	2.6. Stream.iterate()
	Another way of creating an infinite stream is by using the iterate() method:
	
		Stream<Integer> streamIterated = Stream.iterate(40, n -> n + 2).limit(20);
		
	The first element of the resulting stream is a first parameter of the iterate() method. For creating every following element the specified function is applied to the previous element. In the example above the second element will be 42.
	
	2.7. Stream of Primitives
	Java 8 offers a possibility to create streams out of three primitive types: int, long and double. As Stream<T> is a generic interface and there is no way to use primitives as a type parameter with generics, three new special interfaces were created: 
		-> IntStream, 
		-> LongStream, 
		-> DoubleStream.
	Using the new interfaces alleviates unnecessary auto-boxing allows increased productivity
	
		IntStream intStream = IntStream.range(1, 3);
		LongStream longStream = LongStream.rangeClosed(1, 3);
		
	The range(int startInclusive, int endExclusive) method creates an ordered stream from the first parameter to the second parameter. It increments the value of subsequent elements with the step equal to 1. The result doesn't include the last parameter, it is just an upper bound of the sequence.

	The rangeClosed(int startInclusive, int endInclusive) method does the same with only one difference – the second element is included. These two methods can be used to generate any of the three types of streams of primitives.
	
	2.8. Stream of String
	String can also be used as a source for creating a stream.	
	With the help of the chars() method of the String class. Since there is no interface CharStream in JDK, the IntStream is used to represent a stream of chars instead.
		IntStream streamOfChars = "abc".chars();
		
	2.9. Stream of File
	Java NIO class Files allows to generate a Stream<String> of a text file through the lines() method. Every line of the text becomes an element of the stream:
		Path path = Paths.get("C:\\file.txt");
		Stream<String> streamOfStrings = Files.lines(path);
		Stream<String> streamWithCharset = 
		  Files.lines(path, Charset.forName("UTF-8"));
	The Charset can be specified as an argument of the lines() method.
	
3. Referencing a Stream
   
   It is possible to instantiate a stream and to have an accessible reference to it as long as only intermediate operations were called. Executing a terminal operation makes a stream inaccessible.

   To demonstrate this we will forget for a while that the best practice is to chain sequence of operation. Besides its unnecessary verbosity, technically the following code is valid:
	
		Stream<String> stream = 
		Stream.of("a", "b", "c").filter(element -> element.contains("b"));
		Optional<String> anyElement = stream.findAny();
		
	But an attempt to reuse the same reference after calling the terminal operation will trigger the IllegalStateException:
	
		Optional<String> firstElement = stream.findFirst();
	As the IllegalStateException is a RuntimeException, a compiler will not signalize about a problem. So, it is very important to remember that Java 8 streams can't be reused.
	This kind of behavior is logical because streams were designed to provide an ability to apply a finite sequence of operations to the source of elements in a functional style, but not to store elements.
	So, to make previous code work properly some changes should be done:	
	List<String> elements =
		Stream.of("a", "b", "c").filter(element -> element.contains("b"))
		.collect(Collectors.toList());
		Optional<String> anyElement = elements.stream().findAny();
		Optional<String> firstElement = elements.stream().findFirst();	
		
		
		
4. Stream Pipeline	
	To perform a sequence of operations over the elements of the data source and aggregate their results, three parts are needed – 
		-> the source, 
		-> intermediate operation(s) and 
		-> a terminal operation.

	Intermediate operations return a new modified stream. For example, to create a new stream of the existing one without few elements the skip() method should be used:	
		Stream<String> onceModifiedStream =Stream.of("abcd", "bbcd", "cbcd").skip(1);
		
	If more than one modification is needed, intermediate operations can be chained. Assume that we also need to substitute every element of current Stream<String> with a sub-string of first few chars. This will be done by chaining the skip() and the map() methods:	
		Stream<String> twiceModifiedStream = stream.skip(1).map(element -> element.substring(0, 3));
		
	Only one terminal operation can be used per stream.
	The right and most convenient way to use streams are by a stream pipeline, which is a chain of stream source, intermediate operations, and a terminal operation. For example:
		
		List<String> list = Arrays.asList("abc1", "abc2", "abc3");
		long size = list.stream().skip(1).map(element -> element.substring(0, 3)).sorted().count();
		
5. Lazy Invocation	
	Intermediate operations are lazy. This means that they will be invoked only if it is necessary for the terminal operation execution.
		private long counter;
  
		private void wasCalled() {
			counter++;
		}
		
		List<String> list = Arrays.asList(“abc1”, “abc2”, “abc3”);
		counter = 0;
		Stream<String> stream = list.stream().filter(element -> {
			wasCalled();
			return element.contains("2");
		});
		
		As we have a source of three elements we can assume that method filter() will be called three times and the value of the counter variable will be 3. But running this code doesn't change counter at all, it is still zero, so, the filter() method wasn't called even once. The reason why – is missing of the terminal operation.
		
7. Stream Reduction
	The API has many terminal operations which aggregate a stream to a type or to a primitive, for example, count(), max(), min(), sum(), but these operations work according to the predefined implementation. And what if a developer needs to customize a Stream's reduction mechanism? There are two methods which allow to do this – the reduce() and the collect() methods.
	
  7.1. The reduce() Method
	
	OptionalInt reduced =
	IntStream.range(1, 4).reduce((a, b) -> a + b);
	//reduced = 6 (1 + 2 + 3)
	
	int reducedTwoParams =
	IntStream.range(1, 4).reduce(10, (a, b) -> a + b);
	//reducedTwoParams = 16 (10 + 1 + 2 + 3)
	
	int reducedParams = Stream.of(1, 2, 3)
	  .reduce(10, (a, b) -> a + b, (a, b) -> {
		 log.info("combiner was called");
		 return a + b;
	  });
	//The result will be the same as in the previous example (16) and there will be no login which means, that combiner wasn't called. To make a combiner work, a stream should be parallel:
	
	int reducedParallel = Arrays.asList(1, 2, 3).parallelStream()
    .reduce(10, (a, b) -> a + b, (a, b) -> {
       log.info("combiner was called");
       return a + b;
    });
	
	//The result here is different (36) and the combiner was called twice. Here the reduction works by the following algorithm: accumulator ran three times by adding every element of the stream to identity to every element of the stream. These actions are being done in parallel. As a result, they have (10 + 1 = 11; 10 + 2 = 12; 10 + 3 = 13;). Now combiner can merge these three results. It needs two iterations for that (12 + 13 = 25; 25 + 11 = 36).
	
7.2. The collect() Method
	
	Reduction of a stream can also be executed by another terminal operation – the collect() method. It accepts an argument of the type Collector, which specifies the mechanism of reduction. There are already created predefined collectors for most common operations. They can be accessed with the help of the Collectors type.
	
	In this section we will use the following List as a source for all streams:
		List<Product> productList = Arrays.asList(new Product(23, "potatoes"),
		new Product(14, "orange"), new Product(13, "lemon"),
		new Product(23, "bread"), new Product(13, "sugar"));
	
	->Converting a stream to the Collection (Collection, List or Set):
		List<String> collectorCollection = productList.stream().map(Product::getName).collect(Collectors.toList());
		
	->Reducing to String:
		String listToString = productList.stream().map(Product::getName).collect(Collectors.joining(", ", "[", "]"));
	
		The joiner() method can have from one to three parameters (delimiter, prefix, suffix). The handiest thing about using joiner() – developer doesn't need to check if the stream reaches its end to apply the suffix and not to apply a delimiter. Collector will take care of that.
	
	->Processing the average value of all numeric elements of the stream:
		double averagePrice = productList.stream()
			.collect(Collectors.averagingInt(Product::getPrice));
	
	->Processing the sum of all numeric elements of the stream:
		int summingPrice = productList.stream()
			.collect(Collectors.summingInt(Product::getPrice));
	
		Methods averagingXX(), summingXX() and summarizingXX() can work as with primitives (int, long, double) as with their wrapper classes (Integer, Long, Double). One more powerful feature of these methods is providing the mapping. So, developer doesn't need to use an additional map() operation before the collect() method.
	
	->Collecting statistical information about stream’s elements:
			IntSummaryStatistics statistics = productList.stream()
					.collect(Collectors.summarizingInt(Product::getPrice));
					
		By using the resulting instance of type IntSummaryStatistics developer can create a statistical report by applying toString() method. The result will be a String common to this one “IntSummaryStatistics{count=5, sum=86, min=13, average=17,200000, max=23}”.

		It is also easy to extract from this object separate values for count, sum, min, average by applying methods getCount(), getSum(), getMin(), getAverage(), getMax(). All these values can be extracted from a single pipeline.
	
	->Grouping of stream’s elements according to the specified function:
		Map<Integer, List<Product>> collectorMapOfLists = productList.stream()
			.collect(Collectors.groupingBy(Product::getPrice));
			
		In the example above the stream was reduced to the Map which groups all products by their price.
		
	->Dividing stream’s elements into groups according to some predicate:
		Map<Boolean, List<Product>> mapPartioned = productList.stream()
			.collect(Collectors.partitioningBy(element -> element.getPrice() > 15));
			
	->Pushing the collector to perform additional transformation:
			Set<Product> unmodifiableSet = productList.stream()
				.collect(Collectors.collectingAndThen(Collectors.toSet(),
					Collections::unmodifiableSet));
	  In this particular case, the collector has converted a stream to a Set and then created the unmodifiable Set out of it.
	  
	->Custom collector:
		If for some reason, a custom collector should be created, the most easier and the less verbose way of doing so – is to use the method of() of the type Collector.
		
		  Collector<Product, ?, LinkedList<Product>> toLinkedList =
		  Collector.of(LinkedList::new, LinkedList::add, 
			(first, second) -> { 
			   first.addAll(second); 
			   return first; 
			});
		 
		  LinkedList<Product> linkedListOfPersons = productList.stream().collect(toLinkedList);
		  
		 In this example, an instance of the Collector got reduced to the LinkedList<Persone>.
	
------------------------------------------------------------------------------------------------------------------------------------------------------------INTERMEDIATE OPERATION
------------------------------------------------------------------------------------------------------------------------------------------------------------//map
Stream map(Function mapper) returns a stream consisting of the results of applying the given function to the elements of this stream.
Stream map(Function mapper) is an intermediate operation. These operations are always lazy. Intermediate operations are invoked on a Stream instance and after they finish their processing, they give a Stream instance as output.

//Map VS Filter
Filter takes a predicate as an argument so basically you are validating your input/collection against a condition, whereas a map allows you to define or use a existing function on the stream eg you can apply String.toUpperCase(...) etc. and transform your inputlist accordingly.

1. filter
2. map
3. flatmap
4. peek
5. distinct
6. sorted
7. limit

1. filter()

	public void intermediate_filter() {
		long elementsLessThanThree = Stream.of(1, 2, 3, 4).filter(p -> p.intValue() < 3).count();
	}
	
2. map()
	public void intermediate_map() {
		List<String> strings = Stream.of("one", null, "three").map(s -> {
			if (s == null)
				return "[unknown]";
			else
				return s;
		}).collect(Collectors.toList());

		assertThat(strings, contains("one", "[unknown]", "three"));
	}

3. flatmap()

	public void count_distinct_words_java8() throws IOException {
		File file = new File(sourceFileURI);

		long uniqueWords = java.nio.file.Files
				.lines(Paths.get(file.toURI()), Charset.defaultCharset())
				.flatMap(line -> Arrays.stream(line.split(" ."))).distinct()
				.count();

		assertEquals(80, uniqueWords);
	}

4. peek()
The Stream.peek is extremely useful during debugging. It allows you to peek into the stream before an action is encountered. In this snippet we will filter any strings with a size of great than four then call the peek at the stream before the map is called. The peek operation will print out the elements of 'Badgers', 'finals' and 'four'.
	
	public void intermediate_peek() {

		List<String> strings = Stream.of("Badgers", "finals", "four")
				.filter(s -> s.length() >= 4).peek(s -> System.out.println(s))
				.map(s -> s.toUpperCase()).collect(Collectors.toList());

		assertThat(strings, contains("BADGERS", "FINALS", "FOUR"));
	}
5. distinct
	Stream.distinct will find unique elements in a stream according to their .equals behavior. A use case to use distinct is when you want to find the distinct number of words in a file.
	
	public void intermediate_distinct() {

		List<Integer> distinctIntegers = IntStream.of(5, 6, 6, 6, 3, 2, 2)
				.distinct()
				.boxed()
				.collect(Collectors.toList());

		assertEquals(4, distinctIntegers.size());
		assertThat(distinctIntegers, contains(
				5, 6, 3, 2));

	}

6. sorted
	The Stream.sorted method will return a stream sorted according to natural order. Below, we will create a stream of ints then calling the sort which will return the numbers in ascending order.
	
	public void intermediate_sorted() {
		List<Integer> sortedNumbers = Stream.of(5, 3, 1, 3, 6).sorted()
				.collect(Collectors.toList());

		assertThat(sortedNumbers, contains(1, 3, 3, 5, 6));
	}

7. limit
	
	List<String> vals = Stream.of("limit", "by", "two").limit(2)
            .collect(Collectors.toList());

		assertThat(vals, contains("limit", "by"));
	}
------------------------------------------------------------------------------------------------------------------------------------------------------------
TERMINAL OPERATION
------------------------------------------------------------------------------------------------------------------------------------------------------------
1. The allMatch() operation
2. The anyMatch() operation
3. The noneMatch() operation
4. The collect() operation
5. The count() operation
6. The forEach() operation
7. The min() operation
8. The max() operation
9. The reduce() operation
10. The get() operation
------------------------------------------------------
1. allMatch()
	//The allMatch()operation answers the question: Do all elements in the stream meet this condition? It returns true if and only if all elements match a provided predicate, otherwise return false.

	boolean areAllMale = listPersons.stream().allMatch(p -> p.getGender().equals(Gender.MALE)); 
	System.out.println("Are all persons male? " + areAllMale);
	
	boolean useGMail = listPersons.stream().allMatch(p -> p.getEmail().endsWith("gmail.com"));
	System.out.println("Does everyone use GMail? " + useGMail);

2. anyMatch()
	//The anyMatch() operation returns true if any element in the stream matches a provided predicate. In other words, it answers the following question: Is there any element that meets this condition?
	
	boolean anyFemale = listPersons.stream().anyMatch(p -> p.getGender().equals(Gender.FEMALE));
	System.out.println("Is there any female? " + anyFemale);
	
3. noneMatch()
	//In contrast to the allMatch() operation, the noneMatch() operation returns true if no elements in the stream match a provided predicate. In other words, it answers the question: Does no element meet this condition?
	boolean noYahooMail = listPersons.stream().noneMatch(p -> p.getEmail().endsWith("yahoo.com"));
	System.out.println("No one uses Yahoo mail? " + noYahooMail);
	
4. collect()
//The collect() operation accumulates elements in a stream into a container such as a collection. 

		-----------------------------------------------------------------------
		.collect(
		    Collectors.toList()
			Collectors.averagingDouble()
			Collectors.summingDouble()
			Collectors.summarizingDouble()
			Collectors.mapping( ,Collectors.toList())
			Collectors.joining(",")
			Collectors.groupingBy()
			Collectors.counting()
			Collectors.groupingByConcurrent()
			Collectors.toCollection()
			)
		-----------------------------------------------------------------------
		
		1. Calculating Statistical Values		
		//Collectors

				class Employee {
					private String empId;
					private String name;
					private Double salary;
					private String department;
					
					public Employee(String empId, String name, Double salary, String department) {
						this.empId = empId;
						this.name = name;
						this.salary = salary;
						this.department = department;
					}
					// getters and toString
				}
			
			//Finding avg.    	
				Double averageSalary = employees.stream().collect(Collectors.averagingDouble(Employee::getSalary));
			//Similarly there are
				- averagingInt
				- averagingLong
				
			//Finding Total Salary
				Double totalSalary = employees.stream().collect(Collectors.summingDouble(Employee::getSalary));
			//Similarly there are
				- summingInt
				- summingLong
				
			
			//Finding Max Salary
				Employee maxSalary = employees.stream().max(Comparator.comparing(Employee::getSalary)).get();
				
				
			//Calculating Statistics in One Shot
			DoubleSummaryStatistics statistics = employees.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
			System.out.println("Average: " + statistics.getAverage() + ", Total: " + statistics.getSum() + ", Max: " + statistics.getMax() + ", Min: "+ statistics.getMin());
				
			//Similarly there are
				- summarizingInt
				- summarizingLong
				
		2. Mapping and Joining Stream	
		 //mapping is used to map a particular values from to DS to another.
		 
				//Mapping Only Employee Names
					List<String> employeeNames = employees.stream().collect(Collectors.mapping(Employee::getName,Collectors.toList()));
				// [John Nhoj, South Htuos, Reet Teer, Prateema Rai, Yogen Rai]
				
				
		//The joining() method of Collectors Class, in Java, is used to join various elements of a character or string array into a single string object
				//Joining Employee Names
					String joinedNames = employees.stream().map(Employee::getName).collect(Collectors.joining(","));
					
					String employeeNamesStr = employees.stream().map(Employee::getName).collect(Collectors.joining(", ", "Employees = {", "}"));

					
		3. Grouping Elements	
			
			//Grouping employees by Department
			Map<String, List<Employee>> deptEmps = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));
			
			
			//So, counting employees per department would be:
			Map<String, Long> empPerDept = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
			
			//Calculating Average Salary per Department With Sorted Department Name
			Map<String, Double> averageSalaryDeptSorted = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, TreeMap::new, Collectors.averagingDouble(Employee::getSalary)));
			
			//There is a  ConcurrentHashMap version of groupBy(),  leveraging multi-core architectures.
			groupingByConcurrent()
			
			//sorted
			Set<String> setEmails = listPersons.stream().map(p -> p.getEmail()).collect(Collectors.toCollection(TreeSet::new));
			
5.  count()
	//The count() operation simply returns total number of elements in the stream. The following example finds how many people are male:
		long totalMale = listPersons.stream().filter(p -> p.getGender().equals(Gender.MALE)).count();

6.  forEach()
	//The forEach() operation performs an action for each element in the stream, thus creating a side effect, such as print out information of each female person as shown in the following example:
		listPersons.stream().filter(p -> p.getGender().equals(Gender.FEMALE)).forEach(System.out::println);

7.  min()
	//The min(comparator) is a special reduction operation that returns the minimum element in the stream according to the provided comparator. It returns an Optional which is a container object that contains the value.
		
		Optional<Person> min = listPersons.stream().filter(p -> p.getGender().equals(Gender.FEMALE)).min((p1, p2) -> p1.getAge() - p2.getAge());
		if (min.isPresent()) {
			Person youngestGirl = min.get();
			System.out.println("The youngest girl is: "
						+ youngestGirl + " (" + youngestGirl.getAge() + ")");
		}

8. max()
	//Similar to the min() operation, the max()is a special reduction operation that returns the maximum element in the stream according to the specified comparator. The following example finds the oldest male person in the list:
	
	Optional<Person> max = listPersons.stream()
            .filter(p -> p.getGender().equals(Gender.MALE))
            .max((p1, p2) -> p1.getAge() - p2.getAge());
	if (max.isPresent()) {
		Person oldestMan = max.get();
		System.out.println("The oldest man is: "
					+ oldestMan + " (" + oldestMan.getAge() + ")");
	}


//Using comparator 
		//Find Max in a List of Integers
    	int maxNumber = Stream.of(1,2,3,4,7,77,33,66,55).max(Comparator.comparingInt(Integer::valueOf)).get();
    	System.out.println("maxNumber: "+maxNumber);
	

	