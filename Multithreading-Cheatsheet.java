//Multithreading
Multithreading is a Java feature that allows concurrent execution of two or more parts of a program for maximum utilization of CPU. Each part of such program is called a thread. So, threads are light-weight processes within a process.

Threads can be created by using two mechanisms :
1. Extending the Thread class

	public class Main {
    class dummyThread extends Thread{
        public void run() {
            System.out.println("Thread Running");
        }
    }

    public static void main(String[] args) {
        Main ob = new Main();
        Main.dummyThread thread1= ob.new dummyThread();     
        thread1.start();
    }
}
2. Implementing the Runnable Interface
	
	public class Main {
    class dummyThread implements Runnable{
        public void run() {
            System.out.println("Thread Running");
        }
    }

    public static void main(String[] args) {
        Main ob = new Main();
        Main.dummyThread thread1= ob.new dummyThread();
        Thread t1 = new Thread(thread1);
        t1.start();
    }
}

//Thread Class vs Runnable Interface

1. If we extend the Thread class, our class cannot extend any other class because Java doesn’t support multiple inheritance. But, if we implement the Runnable interface, our class can still extend other base classes.

2. We can achieve basic functionality of a thread by extending Thread class because it provides some inbuilt methods like yield(), interrupt() etc. that are not available in Runnable interface.
________________________________________________________________________________________________________________________________________________________________________

//Lifecycle and States of a Thread in Java
A thread in Java at any point of time exists in any one of the following states. A thread lies only in one of the shown states at any instant:
	1.	New
	2.	Runnable
	3.	Blocked
	4.	Waiting
	5.	Timed Waiting
	6.	Terminated
	
//Life Cycle of a thread
1.	New Thread: When a new thread is created, it is in the new state. The thread has not yet started to run when thread is in this state. When a thread lies in the new state, it’s code is yet to be run and hasn’t started to execute.(State after creation i.e Thread t1 = new Thread(demoThread);)

2.	Runnable State: A thread that is ready to run is moved to runnable state. In this state, a thread might actually be running or it might be ready run at any instant of time. It is the responsibility of the thread scheduler to give the thread, time to run.
A multi-threaded program allocates a fixed amount of time to each individual thread. Each and every thread runs for a short while and then pauses and relinquishes the CPU to another thread, so that other threads can get a chance to run. When this happens, all such threads that are ready to run, waiting for the CPU and the currently running thread lies in runnable state.(after thread.start())

3.	Blocked/Waiting state:When a thread is temporarily inactive, then it’s in one of the following states:
	* Blocked
	* Waiting
	For example, when a thread is waiting for I/O to complete, it lies in the blocked state. It’s the responsibility of the thread scheduler to reactivate and schedule a blocked/waiting thread. A thread in this state cannot continue its execution any further until it is moved to runnable state. Any thread in these states does not consume any CPU cycle.

	A thread is in the blocked state when it tries to access a protected section of code that is currently locked by some other thread. When the protected section is unlocked, the schedule picks one of the thread which is blocked for that section and moves it to the runnable state. Whereas, a thread is in the waiting state when it waits for another thread on a condition. When this condition is fulfilled, the scheduler is notified and the waiting thread is moved to runnable state.

	If a currently running thread is moved to blocked/waiting state, another thread in the runnable state is scheduled by the thread scheduler to run. It is the responsibility of thread scheduler to determine which thread to run.
	
4.	Timed Waiting: A thread lies in timed waiting state when it calls a method with a time out parameter. A thread lies in this state until the timeout is completed or until a notification is received. For example, when a thread calls sleep or a conditional wait, it is moved to a timed waiting state.

5.	Terminated State: A thread terminates because of either of the following reasons:
	* Because it exists normally. This happens when the code of thread has entirely executed by the program.
	* Because there occurred some unusual erroneous event, like segmentation fault or an unhandled exception.
	A thread that lies in a terminated state does no longer consumes any cycles of CPU.

//Implementing Thread States in Java
In Java, to get the current state of the thread, use Thread.getState() method to get the current state of the thread. Java provides java.lang.Thread.State class that defines the ENUM constants for the state of a thread, as summary of which is given below:

1.	Constant type: New
	Declaration: public static final Thread.State NEW
	Description: Thread state for a thread which has not yet started.
2.	Constant type: Runnable
	Declaration: public static final Thread.State RUNNABLE
	Description: Thread state for a runnable thread. A thread in the runnable state is executing in the Java virtual machine but it may be waiting for other resources from the operating system such as processor.
3.	Constant type: Blocked
	Declaration: public static final Thread.State BLOCKED
	Description: Thread state for a thread blocked waiting for a monitor lock. A thread in the blocked state is waiting for a monitor lock to enter a synchronized block/method or reenter a synchronized block/method after calling Object.wait().
4.	Constant type: Waiting
	Declaration: public static final Thread.State WAITING
	Description: Thread state for a waiting thread. Thread state for a waiting thread. A thread is in the waiting state due to calling one of the following methods:
		Object.wait with no timeout
		Thread.join with no timeout
		LockSupport.park
	A thread in the waiting state is waiting for another thread to perform a particular action.
5.	Constant type: Timed Waiting
	Declaration: public static final Thread.State TIMED_WAITING
	Description: Thread state for a waiting thread with a specified waiting time. A thread is in the timed waiting state due to calling one of the following methods with a specified positive waiting time:
		Thread.sleep
		Object.wait with timeout
		Thread.join with timeout
		LockSupport.parkNanos
		LockSupport.parkUntil
6.	Constant type: Terminated
	Declaration: public static final Thread.State TERMINATED
	Description: Thread state for a terminated thread. The thread has completed execution.
________________________________________________________________________________________________________________________________________________________________________
//Inter-thread Communication in Java

What is Polling and what are problems with it?
	The process of testing a condition repeatedly till it becomes true is known as polling.
	Polling is usually implemented with the help of loops to check whether a particular condition is true or not. If it is true, certain action is taken. This waste many CPU cycles and makes the implementation inefficient.
	For example, in a classic queuing problem where one thread is producing data and other is consuming it.

How Java multi threading tackles this problem?
	To avoid polling, Java uses three methods namely wait(), notify() and notifyAll().
	All these methods belong to object class as final so that all classes have them. They must be used within a synchronized block only.
		* wait():	It tells the calling thread to give up the lock and go to sleep until some other thread enters the same monitor and calls notify().
		* notify():	It wakes up one single thread that called wait() on the same object. It should be noted that calling notify() does not actually give up a lock on a 				resource.
		* notifyAll():	It wakes up all the threads that called wait() on the same object.

//Difference between wait and sleep?
		__________________________________________________________________________________________________________________________
	   ||			 	wait()										 |			 	       sleep()			                     ||
	   ||------------------------------------------------------------------------------------------------------------------------||
	   ||wait() method releases the lock        					 |sleep() method doesn't release the lock.     			     ||
	   ||is the method of Object class          					 |is the method of Thread class    			  				 ||
	   ||is the non-static method      								 |is the static method     					  				 ||
	   ||should be notified by notify() or notifyAll() methods       |after the specified amount of time, sleep is completed.    ||
	   ||____________________________________________________________|___________________________________________________________||
	   
//yield() method
Theoretically, to ‘yield’ means to let go, to give up, to surrender. A yielding thread tells the virtual machine that it’s willing to let other threads be scheduled in its place. This indicates that it’s not doing something too critical. Note that it’s only a hint, though, and not guaranteed to have any effect at all.

Let’s list down important points from above definition:
	*	Yield is a Static method and Native too.
	*	Yield tells the currently executing thread to give a chance to the threads that have equal priority in the Thread Pool.
	*	There is no guarantee that Yield will make the currently executing thread to runnable state immediately.
	*	It can only make a thread from Running State to Runnable State, not in wait or blocked state.	
	
		public class Main {
			public static void main(String[] args) throws InterruptedException    {
				Thread producer = new Producer();
				Thread consumer = new Consumer();

				consumer.setPriority(Thread.MIN_PRIORITY); //Min Priority
				producer.setPriority(Thread.MAX_PRIORITY); //Max Priority

				producer.start();
				consumer.start();
			}
		}
		class Producer extends Thread {
			public void run()
			{
				for (int i = 0; i < 5; i++)
				{
					System.out.println("I am Producer : Produced Item " + i);
					Thread.yield();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}

		class Consumer extends Thread {
			public void run()
			{
				for (int i = 0; i < 5; i++)
				{
					System.out.println("I am Consumer : Consumed Item " + i);
					Thread.yield();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
//Producer Consumer problem
public class Main {
    public static class PC
    {
        LinkedList<Integer> list = new LinkedList<>();
        int capacity = 2;

        // Function called by producer thread
        public void produce() throws InterruptedException
        {
            int value = 0;
            while (true)
            {
                synchronized (this)
                {
                    while (list.size()==capacity)
                        wait();

                    System.out.println("Producer produced-" + value);
                    list.add(value++);
                    notify();
                    Thread.sleep(1000);
                }
            }
        }

        // Function called by consumer thread
        public void consume() throws InterruptedException
        {
            while (true)
            {
                synchronized (this)
                {
                    // consumer thread waits while list
                    // is empty
                    while (list.size()==0)
                        wait();

                    //to retrive the ifrst job in the list
                    int val = list.removeFirst();

                    System.out.println("Consumer consumed-"+ val);

                    // Wake up producer thread
                    notify();

                    // and sleep
                    Thread.sleep(1000);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final PC pc = new PC();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try{ pc.produce();}
                catch(InterruptedException e){ e.printStackTrace();}
            }
        });

        Thread t2 = new Thread(new Runnable(){
            @Override
            public void run(){
                try{  pc.consume(); }
                catch(InterruptedException e) { e.printStackTrace();}
            }
        });

        // Start both threads
        t1.start();
        t2.start();

        // t1 finishes before t2
        t1.join();
        t2.join();
    }
}
________________________________________________________________________________________________________________________________________________________________________
//Java Thread Priority in Multithreading

In a Multi threading environment, thread scheduler assigns processor to a thread based on priority of thread. Whenever we create a thread in Java, it always has some priority assigned to it. Priority can either be given by JVM while creating the thread or it can be given by programmer explicitly.
Accepted value of priority for a thread is in range of 1 to 10. There are 3 static variables defined in Thread class for priority.

public static int MIN_PRIORITY: This is minimum priority that a thread can have. Value for this is 1.
public static int NORM_PRIORITY: This is default priority of a thread if do not explicitly define it. Value for this is 5.
public static int MAX_PRIORITY: This is maximum priority of a thread. Value for this is 10.

Get and Set Thread Priority:
	1.	public final int getPriority(): java.lang.Thread.getPriority() method returns priority of given thread.
	2.	public final void setPriority(int newPriority): java.lang.Thread.setPriority() method changes the priority of thread to the value newPriority. This method throws IllegalArgumentException if value of parameter newPriority goes beyond minimum(1) and maximum(10) limit.

________________________________________________________________________________________________________________________________________________________________________
//Synchronization in Java
//Synchronized method
	class Table{  
		 synchronized void printTable(int n){//synchronized method  
		   for(int i=1;i<=5;i++){  
			 System.out.println(n*i);  
			 try{  
			  Thread.sleep(400);  
			 }catch(Exception e){System.out.println(e);}  
		   }  
		 }  
	}  
		  
	public class TestSynchronization3{  
		public static void main(String args[]){  
			final Table obj = new Table();//only one object  
		  
			Thread t1=new Thread(){  
									public void run(){  
										obj.printTable(5);  
								    }  
								  };  
		Thread t2=new Thread(){  
								public void run(){  
									obj.printTable(100);  
							    }  
							  };  		  
		t1.start();  
		t2.start();  
		}  
	} 
//Synchronized block
Synchronized block can be used to perform synchronization on any specific resource of the method.
Suppose you have 50 lines of code in your method, but you want to synchronize only 5 lines, you can use synchronized block.
If you put all the codes of the method in the synchronized block, it will work same as the synchronized method.

	void printTable(int n){  
	   synchronized(this){//synchronized block  
		 for(int i=1;i<=5;i++){  
		  System.out.println(n*i);  
		  try{  
		   Thread.sleep(400);  
		  }catch(Exception e){System.out.println(e);}  
		 }  
	   }  
	}
Points to remember for Synchronized block
	* Synchronized block is used to lock an object for any shared resource.
	* Scope of synchronized block is smaller than the method.	

Important points:

1.	When a thread enters into synchronized method or block, it acquires lock and once it completes its task and exits from the synchronized method, it releases the 	lock.
2.	When thread enters into synchronized instance method or block, it acquires Object level lock and when it enters into synchronized static method or block it 	    acquires class level lock.
3.	Java synchronization will throw null pointer exception if Object used in synchronized block is null. For example, If in synchronized(instance) , instance is null 	then it will throw null pointer exception.
4.	In Java, wait(), notify() and notifyAll() are the important methods that are used in synchronization.
5.	You can not apply java synchronized keyword with the variables.
6.	Don’t synchronize on the non-final field on synchronized block because the reference to the non-final field may change anytime and then different threads might 	synchronize on different objects i.e. no synchronization at all.
________________________________________________________________________________________________________________________________________________________________________
//How to make the perfect Singleton?

The purpose of the Singleton class is to control object creation, limiting the number of objects to only one. The singleton allows only one entry point to create the new instance of the class.
Since there is only one Singleton instance, any instance fields of a Singleton will occur only once per class, just like static fields. Singletons are often useful where you have to control the resources, such as database connections or sockets.

1. Eager initialization: This approach has one drawback. Here instance is created even though client application might not be using it. This might be a considerable issue if your Singleton class in creating a database connection or creating a socket. This may cause the memory leak problem. The solution is to create the new instance of the class, when needed. This can be achieved by Lazy Initialization method.
		public class SingletonClass {

			private static volatile SingletonClass sSoleInstance = new SingletonClass();

			//private constructor.
			private SingletonClass(){}

			public static SingletonClass getInstance() {
				return sSoleInstance;
			}
		}

2. Lazy initialization:
		public class SingletonClass {

			private static SingletonClass sSoleInstance;

			private SingletonClass(){}  //private constructor.

			public static SingletonClass getInstance(){
				if (sSoleInstance == null){ //if there is no instance available... create new one
					sSoleInstance = new SingletonClass();
				}

				return sSoleInstance;
			}
		}
//making thread safe
1. Make getInstance() synchronized:
		public class SingletonClass {

			private static SingletonClass sSoleInstance;

			//private constructor.
			private SingletonClass(){} 

			public synchronized static SingletonClass getInstance(){
				if (sSoleInstance == null){ //if there is no instance available... create new one
					sSoleInstance = new SingletonClass();
				}

				return sSoleInstance;
			}
		}
But, there are some cons of using this approach:
	*	Slow performance because of locking overhead.
	*	Unnecessary synchronization that is not required once the instance variable is initialized.

2. Double check locking method:In this, you will make the Singleton class in the synchronized block if the instance is null. So, the synchronized block will be executed only when the sSoleInstance is null and prevent unnecessary synchronization once the instance variable is initialized.

	public class SingletonClass {
		private static volatile SingletonClass sSoleInstance;

		//private constructor.
		private SingletonClass(){}

		public static SingletonClass getInstance() {
			//Double check locking pattern
			if (sSoleInstance == null) { //Check for the first time
			  
				synchronized (SingletonClass.class) {   //Check for the second time.
				  //if there is no instance available... create new one
				  if (sSoleInstance == null) sSoleInstance = new SingletonClass();
				}
			}

			return sSoleInstance;
		}
	}
________________________________________________________________________________________________________________________________________________________________________

//Thread Pool
//What is ThreadPool in Java?
A thread pool reuses previously created threads to execute current tasks and offers a solution to the problem of thread cycle overhead and resource thrashing. Since the thread is already existing when the request arrives, the delay introduced by thread creation is eliminated, making the application more responsive.
	* Java provides the Executor framework which is centered around the Executor interface, its sub-interface –ExecutorService and the class-ThreadPoolExecutor, which implements both of these interfaces. By using the executor, one only has to implement the Runnable objects and send them to the executor to execute.
	* They allow you to take advantage of threading, but focus on the tasks that you want the thread to perform, instead of thread mechanics.
	* To use thread pools, we first create a object of ExecutorService and pass a set of tasks to it. ThreadPoolExecutor class allows to set the core and maximum pool size.The runnables that are run by a particular thread are executed sequentially.
		_________________________________________________________________________________________________
	   ||			 	Method   	   |			 	       Description		                        ||
	   ||-----------------------------------------------------------------------------------------------||
	   ||newFixedThreadPool(int)       |Creates a fixed size thread pool.   			                ||
	   ||newCachedThreadPool()         |Creates a thread pool that creates new 							||
       ||                          	   |threads as needed, but will reuse previously 					||
       ||                              |constructed threads when they are available  					||
	   ||newSingleThreadExecutor()     |Creates a single thread.  										||
	   ||______________________________|________________________________________________________________||

		// creates a thread pool with MAX_T no. of  
        // threads as the fixed pool size(Step 2) 
        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);   
         
        // passes the Task objects to the pool to execute (Step 3) 
        pool.execute(r1); 
        pool.execute(r2); 
        pool.execute(r3); 
        pool.execute(r4); 
        pool.execute(r5);  
          
        // pool shutdown ( Step 4) 
        pool.shutdown();   

//Risks in using Thread Pools
1.	Deadlock : While deadlock can occur in any multi-threaded program, thread pools introduce another case of deadlock, one in which all the executing threads are waiting for the results from the blocked threads waiting in the queue due to the unavailability of threads for execution.
2.	Thread Leakage :Thread Leakage occurs if a thread is removed from the pool to execute a task but not returned to it when the task completed. As an example, if the thread throws an exception and pool class does not catch this exception, then the thread will simply exit, reducing the size of the thread pool by one. If this repeats many times, then the pool would eventually become empty and no threads would be available to execute other requests.
3.	Resource Thrashing :If the thread pool size is very large then time is wasted in context switching between threads. Having more threads than the optimal number may cause starvation problem leading to resource thrashing as explained.

//Important Points
1.	Don’t queue tasks that concurrently wait for results from other tasks. This can lead to a situation of deadlock as described above.
2.	Be careful while using threads for a long lived operation. It might result in the thread waiting forever and would eventually lead to resource leakage.
3.	The Thread Pool has to be ended explicitly at the end. If this is not done, then the program goes on executing and never ends. Call shutdown() on the pool to end the executor. If you try to send another task to the executor after shutdown, it will throw a RejectedExecutionException.
4.	One needs to understand the tasks to effectively tune the thread pool. If the tasks are very contrasting then it makes sense to use different thread pools for different types of tasks so as to tune them properly.

//Tuning Thread Pool
The optimum size of the thread pool depends on the number of processors available and the nature of the tasks. On a N processor system for a queue of only computation type processes, a maximum thread pool size of N or N+1 will achieve the maximum efficiency.But tasks may wait for I/O and in such a case we take into account the ratio of waiting time(W) and service time(S) for a request; resulting in a maximum pool size of N*(1+ W/S) for maximum efficiency.

________________________________________________________________________________________________________________________________________________________________________
//Semaphore in Java
A semaphore controls access to a shared resource through the use of a counter. If the counter is greater than zero, then access is allowed. If it is zero, then access is denied. What the counter is counting are permits that allow access to the shared resource. Thus, to access the resource, a thread must be granted a permit from the semaphore.

//Working of semaphore
In general, to use a semaphore, the thread that wants access to the shared resource tries to acquire a permit.
	* If the semaphore’s count is greater than zero, then the thread acquires a permit, which causes the semaphore’s count to be decremented.
	* Otherwise, the thread will be blocked until a permit can be acquired.
	* When the thread no longer needs an access to the shared resource, it releases the permit, which causes the semaphore’s count to be incremented.
	* If there is another thread waiting for a permit, then that thread will acquire a permit at that time.
	
//Constructors in Semaphore class 
	Semaphore(int num)
	Semaphore(int num, boolean how)
Here, num specifies the initial permit count. Thus, it specifies the number of threads that can access a shared resource at any one time. If it is one, then only one thread can access the resource at any one time. By default, all waiting threads are granted a permit in an undefined order. By setting how to true, you can ensure that waiting threads are granted a permit in the order in which they requested access.

//Using Semaphores as Locks(preventing race condition)
We can use a semaphore to lock access to a resource, each thread that wants to use that resource must first call acquire( ) before accessing the resource to acquire the lock. When the thread is done with the resource, it must call release( ) to release lock. Here is an example that demonstrate this:

		public class Main {
			public static void main(String args[]) throws InterruptedException
			{
				// creating a Semaphore object
				// with number of permits 1
				Semaphore sem = new Semaphore(1);

				// creating two threads with name A and B
				// Note that thread A will increment the count
				// and thread B will decrement the count
				MyThread mt1 = new MyThread(sem, "A");
				MyThread mt2 = new MyThread(sem, "B");

				// stating threads A and B
				mt1.start();
				mt2.start();

				// waiting for threads A and B

				// count will always remain 0 after
				// both threads will complete their execution
				System.out.println("count: " + Shared.count);
			}
		}

		class Shared
		{
			static int count = 0;
		}

		class MyThread extends Thread
		{
			Semaphore sem;
			String threadName;
			public MyThread(Semaphore sem, String threadName)
			{
				super(threadName);
				this.sem = sem;
				this.threadName = threadName;
			}

			@Override
			public void run() {

				// run by thread A
				if(this.getName().equals("A"))
				{
					System.out.println("Starting " + threadName);
					try
					{
						// First, get a permit.
						System.out.println(threadName + " is waiting for a permit.");

						// acquiring the lock
						sem.acquire();

						System.out.println(threadName + " gets a permit.");

						// Now, accessing the shared resource.
						// other waiting threads will wait, until this
						// thread release the lock
						for(int i=0; i < 5; i++)
						{
							Shared.count++;
							System.out.println(threadName + ": " + Shared.count);

							// Now, allowing a context switch -- if possible.
							// for thread B to execute
							Thread.sleep(1000);
						}
					} catch (InterruptedException exc) {
						System.out.println(exc);
					}

					// Release the permit.
					System.out.println(threadName + " releases the permit.");
					sem.release();
				}

				// run by thread B
				else
				{
					System.out.println("Starting " + threadName);
					try
					{
						// First, get a permit.
						System.out.println(threadName + " is waiting for a permit.");

						// acquiring the lock
						sem.acquire();

						System.out.println(threadName + " gets a permit.");

						// Now, accessing the shared resource.
						// other waiting threads will wait, until this
						// thread release the lock
						for(int i=0; i < 5; i++)
						{
							Shared.count--;
							System.out.println(threadName + ": " + Shared.count);

							// Now, allowing a context switch -- if possible.
							// for thread A to execute
							Thread.sleep(1000);
						}
					} catch (InterruptedException exc) {
						System.out.println(exc);
					}
					// Release the permit.
					System.out.println(threadName + " releases the permit.");
					sem.release();
				}
			}
		}

________________________________________________________________________________________________________________________________________________________________________

//Countdown Latch
CountDownLatch is used to make sure that a task waits for other threads before it starts. To understand its application, let us consider a server where the main task can only start when all the required services have started.

//Working of CountDownLatch:
When we create an object of CountDownLatch, we specify the number of threads it should wait for, all such thread are required to do count down by calling CountDownLatch.countDown() once they are completed or ready to the job. As soon as count reaches zero, the waiting task starts running.

public class CountDownLatchDemo 
{ 
    public static void main(String args[])  
                   throws InterruptedException 
    { 
        // Let us create task that is going to  
        // wait for four threads before it starts 
        CountDownLatch latch = new CountDownLatch(4); 
  
        // Let us create four worker  
        // threads and start them. 
        Worker first = new Worker(1000, latch,  
                                  "WORKER-1"); 
        Worker second = new Worker(2000, latch,  
                                  "WORKER-2"); 
        Worker third = new Worker(3000, latch,  
                                  "WORKER-3"); 
        Worker fourth = new Worker(4000, latch,  
                                  "WORKER-4"); 
        first.start(); 
        second.start(); 
        third.start(); 
        fourth.start(); 
  
        // The main task waits for four threads 
        latch.await(); 
  
        // Main thread has started 
        System.out.println(Thread.currentThread().getName() + 
                           " has finished"); 
    } 
} 
  
// A class to represent threads for which 
// the main thread waits. 
class Worker extends Thread 
{ 
    private int delay; 
    private CountDownLatch latch; 
  
    public Worker(int delay, CountDownLatch latch, 
                                    String name) 
    { 
        super(name); 
        this.delay = delay; 
        this.latch = latch; 
    } 
  
    @Override
    public void run() 
    { 
        try
        { 
            Thread.sleep(delay); 
            latch.countDown(); 
            System.out.println(Thread.currentThread().getName() 
                            + " finished"); 
        } 
        catch (InterruptedException e) 
        { 
            e.printStackTrace(); 
        } 
    } 
} 

________________________________________________________________________________________________________________________________________________________________________

//Daemon thread in Java
Daemon thread is a low priority thread that runs in background to perform tasks such as garbage collection.
Properties:
	* They can not prevent the JVM from exiting when all the user threads finish their execution.
    * JVM terminates itself when all user threads finish their execution
	* If JVM finds running daemon thread, it terminates the thread and after that shutdown itself. JVM does not care whether Daemon thread is running or not.
	* It is an utmost low priority thread.

Methods:
	1. void setDaemon(boolean status): This method is used to mark the current thread as daemon thread or user thread. For example if I have a user thread tU then tU.setDaemon(true) would make it Daemon thread. On the other hand if I have a Daemon thread tD then by calling tD.setDaemon(false) would make it user thread.
	syntax:
		public final void setDaemon(boolean on)
	parameters:
		on : if true, marks this thread as a daemon thread.
	exceptions:
		IllegalThreadStateException: if only this thread is active.
		SecurityException: if the current thread cannot modify this thread.
	2. boolean isDaemon(): This method is used to check that current is daemon. It returns true if the thread is Daemon else it returns false.
	Syntax:
		public final boolean isDaemon()
	returns: 
		This method returns true if this thread is a daemon thread;
		false otherwise

Daemon vs User Threads:
1.	Priority: When the only remaining threads in a process are daemon threads, the interpreter exits. This makes sense because when only daemon threads remain, there is no other thread for which a daemon thread can provide a service.

2.	Usage: Daemon thread is to provide services to user thread for background supporting task.

________________________________________________________________________________________________________________________________________________________________________


















