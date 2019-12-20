What’s a CompletableFuture?

CompletableFuture is used for asynchronous programming in Java. Asynchronous programming is a means of writing non-blocking code by running a task on a separate thread than the main application thread and notifying the main thread about its progress, completion or failure.
----------------------------------------------------------------------------------------------------------------------------------------------------------------
Future vs CompletableFuture

CompletableFuture is an extension to Java’s Future API which was introduced in Java 5.

A Future is used as a reference to the result of an asynchronous computation. It provides an isDone() method to check whether the computation is done or not, and a get() method to retrieve the result of the computation when it is done.

Future API was a good step towards asynchronous programming in Java but it lacked some important and useful features -

Limitations of Future
	1.It cannot be manually completed :
	
		Let’s say that you’ve written a function to fetch the latest price of an e-commerce product from a remote API. Since this API call is time-consuming, you’re running it in a separate thread and returning a Future from your function.
		Now, let’s say that If the remote API service is down, then you want to complete the Future manually by the last cached price of the product.
		Can you do this with Future? No!
	
	2.You cannot perform further action on a Future’s result without blocking:

		Future does not notify you of its completion. It provides a get() method which blocks until the result is available.
		You don’t have the ability to attach a callback function to the Future and have it get called automatically when the Future’s result is available.
		
	3.Multiple Futures cannot be chained together :

		Sometimes you need to execute a long-running computation and when the computation is done, you need to send its result to another long-running computation, and so on.
		You can not create such asynchronous workflow with Futures.
	
	4.You can not combine multiple Futures together :
	
		Let’s say that you have 10 different Futures that you want to run in parallel and then run some function after all of them completes. You can’t do this as well with Future.
		
	5.No Exception Handling :

		Future API does not have any exception handling construct.
		
Whoa! So many limitations right? Well, That’s why we have CompletableFuture. You can achieve all of the above with CompletableFuture.
CompletableFuture implements Future and CompletionStage interfaces and provides a huge set of convenience methods for creating, chaining and combining multiple Futures. It also has a very comprehensive exception handling support.		
-----------------------------------------------------------------------------------------------------------------------------------------------------------------
Creating a CompletableFuture

1. The trivial example -

	You can create a CompletableFuture simply by using the following no-arg constructor -
	
		//CompletableFuture<String> completableFuture = new CompletableFuture<String>();
		
	This is the simplest CompletableFuture that you can have. All the clients who want to get the result of this CompletableFuture can call CompletableFuture.get() method -

		//String result = completableFuture.get()
	
	The get() method blocks until the Future is complete. So, the above call will block forever because the Future is never completed.
	
	You can use CompletableFuture.complete() method to manually complete a Future -
		
		//completableFuture.complete("Future's Result")
	
	All the clients waiting for this Future will get the specified result. And, Subsequent calls to completableFuture.complete() will be ignored.
	
	---------------------------------------code------------------------------------
	public static CompletableFuture<Double> getPrice(){
    	CompletableFuture<Double> completableFuture = CompletableFuture.supplyAsync(()-> {
    																						try {
																								Thread.sleep(5000);
																							} catch (Exception e) {
																								e.printStackTrace();
																							}
    																						return 299.89;
    																					 });
    	
    	return completableFuture;
    }
    
    public static <T> CompletableFuture<T> timeout(Duration duration){
    	CompletableFuture<T> completableFuture = new CompletableFuture<T>();    	
    	try {
			Thread.sleep(duration.toMillis());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	completableFuture.completeExceptionally(new TimeoutException());
    	
    	return completableFuture;
    }
    
    public static void findUpdatedPrice(){
    	Double cachedPrice = 300.00;
    	final CompletableFuture<Double> formAPI = getPrice();
    	final CompletableFuture<Double> timeout = timeout(Duration.ofMillis(2000));
    	
    	CompletableFuture<Double> p = formAPI.applyToEither(timeout, price -> price).exceptionally(ex -> {
    	    return cachedPrice;
    	});
    	
    	System.out.println("The Price of SOAP is : "+ p.join());
    	System.out.println("BLOCKED");
    }
	-------------------------------------------------------------------------------
join vs get
	
	The only difference is how methods throw exceptions. get() is declared in Future interface as

		//V get() throws InterruptedException, ExecutionException;
	
	The join() method doesn't throw checked exceptions.

		//public T join()
	
2. Running asynchronous computation using runAsync() -
		If you want to run some background task asynchronously and don’t want to return anything from the task, then you can use CompletableFuture.runAsync() method. It takes a Runnable object and returns CompletableFuture<Void>.		
				// Run a task specified by a Runnable Object asynchronously.
				CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
					// Simulate a long-running Job  
					{
						// Simulate a long-running Job
						try {
							TimeUnit.SECONDS.sleep(1);
						} catch (InterruptedException e) {
							throw new IllegalStateException(e);
						}
						System.out.println("I'll run in a separate thread than the main thread.");
					}
				});

				// Block and wait for the future to complete
				future.get()

3. Run a task asynchronously and return the result using supplyAsync() -
		CompletableFuture.runAsync() is useful for tasks that don’t return anything. But what if you want to return some result from your background task?

		Well, CompletableFuture.supplyAsync() is your companion. It takes a Supplier<T> and returns CompletableFuture<T> where T is the type of the value obtained by calling the given supplier -
		---------------------------------------code------------------------------------
			// Run a task specified by a Supplier object asynchronously
				CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {
					@Override
					public String get() {
						try {
							TimeUnit.SECONDS.sleep(1);
						} catch (InterruptedException e) {
							throw new IllegalStateException(e);
						}
						return "Result of the asynchronous computation";
					}
				});
				
				A Supplier<T> is a simple functional interface which represents a supplier of results. It has a single get() method where you can write your background task and return the result.
				Once again, you can use Java 8’s lambda expression to make the above code more concise -
				// Using Lambda Expression
				CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						throw new IllegalStateException(e);
					}
					return "Result of the asynchronous computation";
				});
			-------------------------------------------------------------------------
				// Block and get the result of the Future
				String result = future.get();
				System.out.println(result);

				//helper Method
					public static String mostRecentQuestionsAbout(String str) throws InterruptedException{
					System.out.println(Thread.currentThread());
					Thread.sleep(2000); 
					return "What is use of volatile in "+str;
				}
				 static ExecutorService executorService = Executors.newFixedThreadPool(2);
				 
				//Blocking Async(Using Future)
				public static void executorSrvice() throws Exception {
					final Callable<String> task = () -> mostRecentQuestionsAbout("java");
					final Future<String> javaQuestionFuture = executorService.submit(task);
					
					final String javaQuestion = javaQuestionFuture.get();   //Blocking
					System.out.println("Most Recent Questions About: "+javaQuestion);
				}
				
				//CompletableFuture is implementation of future with more functionalities
				//supplyAsync(): takes a method and returns Future of that type
				public static void supplyAsync() throws Exception {
					final CompletableFuture<String> java = CompletableFuture.supplyAsync(() -> mostRecentQuestionsAboutErrorFree("java"),executorService);
					
					final String javaQuestion = java.get();   //Blocking but you can chain them now 
					System.out.println("Most Recent Questions About: "+javaQuestion);
				}
				public static void oldSchool() throws Exception {
				final CompletableFuture<String> java = CompletableFuture.supplyAsync(() -> mostRecentQuestionsAboutErrorFree("java"),executorService);
				
				final String javaQuestion = java.get();   //Blocking
				final int length = javaQuestion.length();
				System.out.println("Most Recent Questions About: "+javaQuestion+" --> length: "+length);
			}
			-------------------------------------------------------------------------------
			
		
Note: You might be wondering that - Well, I know that the runAsync() and supplyAsync() methods execute their tasks in a separate thread. But, we never createda thread right?

Yes! CompletableFuture executes these tasks in a thread obtained from the global ForkJoinPool.commonPool().

But hey, you can also create a Thread Pool and pass it to runAsync() and supplyAsync() methods to let them execute their tasks in a thread obtained from your thread pool.

---------------------------------------------------------------------------------------------------------------------------------------------------------------
Transforming and acting on a CompletableFuture

The CompletableFuture.get() method is blocking. It waits until the Future is completed and returns the result after its completion.

But, that’s not what we want right? For building asynchronous systems we should be able to attach a callback to the CompletableFuture which should automatically get called when the Future completes.

That way, we won’t need to wait for the result, and we can write the logic that needs to be executed after the completion of the Future inside our callback function.

You can attach a callback to the CompletableFuture using 
	->thenApply() 
	->thenAccept() 
	->thenRun()
	
	1. thenApply()
	You can use thenApply() method to process and transform the result of a CompletableFuture when it arrives.
	
		// Create a CompletableFuture
		CompletableFuture<String> whatsYourNameFuture = CompletableFuture.supplyAsync(() -> {
		   try {
			   TimeUnit.SECONDS.sleep(1);
		   } catch (InterruptedException e) {
			   throw new IllegalStateException(e);
		   }
		   return "Rajeev";
		});

		// Attach a callback to the Future using thenApply()
		CompletableFuture<String> greetingFuture = whatsYourNameFuture.thenApply(name -> {
		   return "Hello " + name;
		});

		// Block and get the result of the future.
		System.out.println(greetingFuture.get()); // Hello Rajeev
		
		You can also write a sequence of transformations on the CompletableFuture by attaching a series of thenApply() callback methods. The result of one thenApply() method is passed to the next in the series -
		
			CompletableFuture<String> welcomeText = CompletableFuture.supplyAsync(() -> {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
				   throw new IllegalStateException(e);
				}
				return "Rajeev";
			}).thenApply(name -> {
				return "Hello " + name;
			}).thenApply(greeting -> {
				return greeting + ", Welcome to the CalliCoder Blog";
			});

			System.out.println(welcomeText.get());
			// Prints - Hello Rajeev, Welcome to the CalliCoder Blog	
			-------------------------------------------------------------------------
			    //Callback 'thenApply()'
    //Returns CompletableFuture
    public static void thenApply() throws Exception {
    	final CompletableFuture<String> java = CompletableFuture.supplyAsync(() -> mostRecentQuestionsAboutErrorFree("java"),executorService);	
    	//doesn't matter 'java' is future of String, what matters is what inside the future i.e a String
    	final CompletableFuture<String>java1 = java.thenApply(str->{
    																System.out.println("Most Recent Questions About: "+str+" --> length: "+str.length());
    																return "Most Recent Questions About: "+str+" --> length: "+str.length();
    																});
    	final CompletableFuture<String>java2 = java1.thenApply(str->{
    																System.out.println("Most Recent Questions About: "+str+" --> length: "+str.length());
    																return str;
    																});
    	//final CompletableFuture<Integer>java3 = java2.thenApply(str -> str.length());
    	//------------------------OR--------------------------
    	final CompletableFuture<Integer>java3 = java2.thenApply(String::length);

    	System.out.println("Length : "+java3.get());
    }
    
    //Callback 'thenApply()'
    //Returns CompletableFuture
    //Chaining
    public static void thenApplyChained() throws Exception {
    	final CompletableFuture<String> java = CompletableFuture.supplyAsync(() -> mostRecentQuestionsAboutErrorFree("java"),executorService);	
    	//doesn't matter 'java' is future of String, what matters is what inside the future i.e a String
    	final CompletableFuture<String>java1 = java.thenApply(str->{
    																 System.out.println("Most Recent Questions About: "+str+" --> length: "+str.length());
    																 return "Most Recent Questions About: "+str+" --> length: "+str.length();}).
    												thenApply(str->{
    																 System.out.println("Most Recent Questions About: "+str+" --> length: "+str.length());
    																 return str;
    																});
    	final CompletableFuture<Integer>java3 = java1.thenApply(String::length);

    	System.out.println("Non Blocking");
    	System.out.println("Length : "+java3.get());
    }
    
    public static CompletableFuture<String> javaQuestion(){
    	return CompletableFuture.supplyAsync(() -> mostRecentQuestionsAboutErrorFree("java"),executorService);
    }
    
    public static CompletableFuture<Integer> findLength(String str){
    	System.out.println("Most Recent Questions About: "+str+" --> length: "+str.length());
    	return CompletableFuture.supplyAsync(() -> str.length(),executorService);
    }
    
    
		-------------------------------------------------------------------------
2. thenAccept() and thenRun()
		If you don’t want to return anything from your callback function and just want to run some piece of code after the completion of the Future, then you can use thenAccept() and thenRun() methods. These methods are consumers and are often used as the last callback in the callback chain.
		
		    //Callback 'thenAccept()'
			//But return void
			public static void callbacksCallbacksEverywhere() throws Exception {
				final CompletableFuture<String> java = CompletableFuture.supplyAsync(() -> mostRecentQuestionsAboutErrorFree("java"),executorService);	
				//doesn't matter 'java' is future of String, what matters is what inside the future i.e a String
				 CompletableFuture<Void> java1 = java.thenAccept(str-> System.out.println("Most Recent Questions About: "+str+" --> length: "+str.length()));
				 
				System.out.println("Non Blocking : "+java1);
				//prints : java.util.concurrent.CompletableFuture@736e9adb[Not completed]
			}
		CompletableFuture.thenAccept() takes a Consumer<T> and returns CompletableFuture<Void>. It has access to the result of the CompletableFuture on which it is attached.
			// thenAccept() example
			CompletableFuture.supplyAsync(() -> {
				return ProductService.getProductDetail(productId);
			}).thenAccept(product -> {
				System.out.println("Got product detail from remote service " + product.getName())
			});
			
		While thenAccept() has access to the result of the CompletableFuture on which it is attached, thenRun() doesn’t even have access to the Future’s result. It takes a Runnable and returns CompletableFuture<Void> -
			// thenRun() example
			CompletableFuture.supplyAsync(() -> {
				// Run some computation  
			}).thenRun(() -> {
				// Computation Finished.
			});
		
note: A note about async callback methods -
All the callback methods provided by CompletableFuture have two async variants -
	// thenApply() variants
	<U> CompletableFuture<U> thenApply(Function<? super T,? extends U> fn)
	<U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn)
	<U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)
These async callback variations help you further parallelize your computations by executing the callback tasks in a separate thread.
			final CompletableFuture<User> user = CompletableFuture.supplyAsync(() -> getUser(userId));

			final CompletableFuture<CreditRating> rating1 = 
					user.thenApplyAsync(CreditRatingService::getCreditRatingSystem1);
			final CompletableFuture<CreditRating> rating2 = 
					user.thenApplyAsync(CreditRatingService::getCreditRatingSystem2);

			rating1
				.thenCombineAsync(rating2, CreditRating::combine)
				.thenAccept(System.out::println);
				
Here I have two actions waiting for user details. Once the details are available, I want to get two credit ratings from two different systems. Since I want to do it in parallel, I have to use at least one async method. Without async, the code would use only one thread so both credit rating tasks would be executed serially.

I have added combine phase that waits for both credit rating tasks to complete. It’s better to make this async too, but from different reason. Without async, the same thread as in rating1 would be used. But you do not want to block the thread while waiting for the rating2 task. You want to return it to the pool and get a thread only when it is needed.

When both tasks are ready, we can combine the result and print it in the same thread, so the last thenAccept is without async.

As we can see, both method variants are useful. Use async, when you need to execute more tasks in parallel or you do not want to block a thread while waiting for a task to finish. But since it is not easy to reason about such programs, I would recommend to use async everywhere. It might be little bit less effective, but the difference is negligible. For very small small price, it gives you the freedom to delegate your thinking to the machine. Just make everything async and let the fork-join framework care about the optimization.

---------------------------------------------------------------------------------------------------------------------------------------------------------------
Combining two CompletableFutures together:

1. Combine two dependent futures using thenCompose() -

	Let’s say that you want to fetch the details of a user from a remote API service and once the user’s detail is available, you want to fetch his Credit rating from another service.
	
		CompletableFuture<User> getUsersDetail(String userId) {
			return CompletableFuture.supplyAsync(() -> {
				return UserService.getUserDetails(userId);
			});	
		}

		CompletableFuture<Double> getCreditRating(User user) {
			return CompletableFuture.supplyAsync(() -> {
				return CreditRatingService.getCreditRating(user);
			});
		}
		
	So, Rule of thumb here - If your callback function returns a CompletableFuture, and you want a flattened result from the CompletableFuture chain (which in most cases you would), then use thenCompose().
		
		//Callback 'thenApply()' is WRONG
		//Returns CompletableFuture
		//Chaining
		//The CompletableFuture.join() method is similar to the get method, but it throws an unchecked exception in case the Future does not complete normally.
		public static void thenApplyIsWorng() throws Exception { 		
			//doesn't matter 'java' is future of String, what matters is what inside the future i.e a String
			final CompletableFuture<CompletableFuture<Integer>>java1 = javaQuestion().
																			thenApply(UserService::findLength);    	

			System.out.println(java1.join());
		}
		
		//Then Compose()
		//The CompletableFuture.join() method is similar to the get method, but it throws an unchecked exception in case the Future does not complete normally.
		public static void thenComposeChained() throws Exception { 		
			//doesn't matter 'java' is future of String, what matters is what inside the future i.e a String
			final CompletableFuture<Integer>java1 = javaQuestion().
															thenCompose(UserService::findLength);    	

			System.out.println(java1.join());
		}    
		
2. Combine two independent futures using thenCombine() -

While thenCompose() is used to combine two Futures where one future is dependent on the other, thenCombine() is used when you want two Futures to run independently and do something after both are complete.

	System.out.println("Retrieving weight.");
	CompletableFuture<Double> weightInKgFuture = CompletableFuture.supplyAsync(() -> {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
		   throw new IllegalStateException(e);
		}
		return 65.0;
	});

	System.out.println("Retrieving height.");
	CompletableFuture<Double> heightInCmFuture = CompletableFuture.supplyAsync(() -> {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
		   throw new IllegalStateException(e);
		}
		return 177.8;
	});

	System.out.println("Calculating BMI.");
	CompletableFuture<Double> combinedFuture = weightInKgFuture
			.thenCombine(heightInCmFuture, (weightInKg, heightInCm) -> {
		Double heightInMeter = heightInCm/100;
		return weightInKg/(heightInMeter*heightInMeter);
	});

	System.out.println("Your BMI is - " + combinedFuture.get());
	------------------------------------------------------------------------
	    //Blocking Async
    public static void WaitForFirstOrAll() throws Exception {
    	final Future<String> java = findQuestionsAbout("java");
    	final Future<String> scala = findQuestionsAbout("scala");
    	
    	final String javaResult = java.get();
      		System.out.println("Most Recent Questions About: "+javaResult);
    	final String scalaResult = scala.get();
      		System.out.println("Most Recent Questions About: "+scalaResult);
      	System.out.println("BLOCKING");
    }
    
    //Helper-method
    public static CompletableFuture<String> findQuestionsAboutCompletableFuture(String str) throws InterruptedException{
        CompletableFuture<String> future = null;
        
		future = CompletableFuture.supplyAsync(()-> mostRecentQuestionsAboutErrorFree(str),executorService);
			
        return future;
    }
    
    //thenCombine()
    //non-blocking
    public static void thenCombine() throws Exception { 
    	final CompletableFuture<String> java = findQuestionsAboutCompletableFuture("java");
    	final CompletableFuture<String> scala = findQuestionsAboutCompletableFuture("scala");
    	
    	final CompletableFuture<Integer> integLength = java.thenCombine(scala, (String javaTitle, String scalaTitle)->javaTitle.length()+ scalaTitle.length());    	
    	System.out.println("NON-BLOCKING"+integLength);
    	System.out.println(integLength.join());
    	System.out.println("BLOCKED");
    }
	
	//thenEither()
    public static void either() throws Exception{
    	final CompletableFuture<String> java = findQuestionsAboutCompletableFuture("java");
    	final CompletableFuture<String> scala = findQuestionsAboutCompletableFuture("scala");
    	
    	final CompletableFuture<String> both = scala.applyToEither(java, title -> title.toUpperCase());
    	
    	System.out.println("NON-BLOCKING");
    	System.out.println(both.join()+" <--Congrats first.");
    }
	--------------------------------------------------------------------------------
3. Combining multiple CompletableFutures together	

	We used thenCompose() and thenCombine() to combine two CompletableFutures together. Now, what if you want to combine an arbitrary number of CompletableFutures? Well, you can use the following methods to combine any number of CompletableFutures -

	static CompletableFuture<Void>	 allOf(CompletableFuture<?>... cfs)
	static CompletableFuture<Object> anyOf(CompletableFuture<?>... cfs)
	
	    //allOf()
    public static void allOf() throws Exception{
    	final CompletableFuture<String> python = findQuestionsAboutCompletableFuture("python");
    	final CompletableFuture<String> javaScript = findQuestionsAboutCompletableFuture("javaScript");
    	final CompletableFuture<String> java = findQuestionsAboutCompletableFuture("java");
    	final CompletableFuture<String> scala = findQuestionsAboutCompletableFuture("scala");
    	
    	final CompletableFuture<Void> allCompleted = CompletableFuture.allOf(javaScript, python, java, scala);

    	//Async Block
    	//Following will only run when all finished
    	//As all are finished we can call get safely
    	allCompleted.thenRun(()-> {
    								try {
										System.out.println(python.get());
										System.out.println(javaScript.get());
	    								System.out.println(java.get());
	    								System.out.println(scala.get());
									} catch (Exception e) {
										e.printStackTrace();
								    }
    							
    	});
      	System.out.println("non-BLOCKED");
   }
    
    //anyOf()
    public static void anyOf() throws Exception{
    	final CompletableFuture<String> python = findQuestionsAboutCompletableFuture("python");
    	final CompletableFuture<String> javaScript = findQuestionsAboutCompletableFuture("javaScript");
    	final CompletableFuture<String> java = findQuestionsAboutCompletableFuture("java");
    	final CompletableFuture<String> scala = findQuestionsAboutCompletableFuture("scala");
    	
    	final CompletableFuture<Object> firstComleted = CompletableFuture.anyOf(javaScript, python, java, scala);

    	//Async Block
    	//Following will only run when all finished
    	//As all are finished we can call get safely
    	firstComleted.thenAccept((Object result)-> {
										System.out.println("First Arrival-->"+((String)result).length());
    							
    	});
      	System.out.println("non-BLOCKED");
   }
---------------------------------------------------------------------------------------------------------------------------------------------------------------
CompletableFuture Exception Handling

Let’s first understand how errors are propagated in a callback chain. Consider the following CompletableFuture callback chain -

		CompletableFuture.supplyAsync(() -> {
			// Code which might throw an exception
			return "Some result";
		}).thenApply(result -> {
			return "processed result";
		}).thenApply(result -> {
			return "result after further processing";
		}).thenAccept(result -> {
			// do something with the final result
		});
		
If an error occurs in the original supplyAsync() task, then none of the thenApply() callbacks will be called and future will be resolved with the exception occurred. If an error occurs in first thenApply() callback then 2nd and 3rd callbacks won’t be called and the future will be resolved with the exception occurred, and so on.

1. Handle exceptions using exceptionally() callback

The exceptionally() callback gives you a chance to recover from errors generated from the original Future. You can log the exception here and return a default value.

		Integer age = -1;

		CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> {
			if(age < 0) {
				throw new IllegalArgumentException("Age can not be negative");
			}
			if(age > 18) {
				return "Adult";
			} else {
				return "Child";
			}
		}).exceptionally(ex -> {
			System.out.println("Oops! We have an exception - " + ex.getMessage());
			return "Unknown!";
		});

		System.out.println("Maturity : " + maturityFuture.get()); 
		
Note that, the error will not be propagated further in the callback chain if you handle it once.

2. Handle exceptions using the generic handle() method

The API also provides a more generic method - handle() to recover from exceptions. It is called whether or not an exception occurs.

		Integer age = -1;

		CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> {
			if(age < 0) {
				throw new IllegalArgumentException("Age can not be negative");
			}
			if(age > 18) {
				return "Adult";
			} else {
				return "Child";
			}
		}).handle((res, ex) -> {
			if(ex != null) {
				System.out.println("Oops! We have an exception - " + ex.getMessage());
				return "Unknown!";
			}
			return res;
		});

		System.out.println("Maturity : " + maturityFuture.get());
		
If an exception occurs, then the res argument will be null, otherwise, the ex argument will be null.