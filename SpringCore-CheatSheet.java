//Spring Core
//We can fetch configuration from xml as well as java class by using following method.

//Using XML
public class SpringDemo {
	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
		Book book = (Book)context.getBean("book");
		System.out.println("Book Name:"+ book.getBookName());
	        context.registerShutdownHook();
	}
} 

//Annotation Based
public class SpringDemo {
   public static void main(String[] args) {
       AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
       ctx.register(AppConfig.class);
       ctx.refresh();
       Book book = ctx.getBean(Book.class);
       System.out.println("Book Name:"+ book.getBookName());
       ctx.close();
   }
} 
________________________________________________________________________________________________________________________________________________________________________
Spring Bean Life Cycle
	1.	Constructor
			Within IoC container, a spring bean is created using class constructor.
	2.	Setter
			Now the dependency injection is performed using setter method.
	3.	BeanNameAware.setBeanName()
			It sets the name of bean in the bean factory that created this bean.
	4.	BeanClassLoaderAware.setBeanClassLoaderAware()
			supplies the bean class loader to a bean instance.
	5.	BeanFactoryAware.setBeanFactory()
			provides the owning factory to a bean instance.
	6.	BeanPostProcessor.postProcessBeforeInintialization()
	7.	@PostConstruct
	8.	InitializingBean.afterPropertiesSet()
	9.	init method
	10.	BeanPostProcessor.postProcessorAfterInitialization()
	11.	***BEAN IS WORKING***
	12.	@PreDestroy
	13.	DisposableBean.destroy()
	14. destroy method
	15.	finalize()
	
//Book.java
public class Book implements  InitializingBean, DisposableBean, BeanFactoryAware, BeanNameAware, BeanClassLoaderAware {
	private String bookName;
	private Book() {
		System.out.println("---inside constructor---");
	}
	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
	       System.out.println("---BeanClassLoaderAware.setBeanClassLoader---");
	}	
	@Override
	public void setBeanName(String name) {
   	       System.out.println("---BeanNameAware.setBeanName---");
	}	
	public void myPostConstruct() {
	    	 System.out.println("---init-method---");
	}	
	@PostConstruct
	public void springPostConstruct() {
	    	 System.out.println("---@PostConstruct---");
	}
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("---BeanFactoryAware.setBeanFactory---");
	}	
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("---InitializingBean.afterPropertiesSet---");
	}	
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
		System.out.println("setBookName: Book name has set.");		
	}
	public void myPreDestroy() {
		System.out.println("---destroy-method---");
	}
	@PreDestroy
	public void springPreDestroy() {
		System.out.println("---@PreDestroy---");
	}
	@Override
	public void destroy() throws Exception {
		System.out.println("---DisposableBean.destroy---");
	}
	@Override
	protected void finalize() {
		System.out.println("---inside finalize---");
	}
} 
	
//Generalized will work for every bean
public class MyBeanPostProcessor implements BeanPostProcessor {
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		System.out.println("BeanPostProcessor.postProcessAfterInitialization");
		return bean;
	}
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		System.out.println("BeanPostProcessor.postProcessBeforeInitialization");
		return bean;
	}
} 
//spring-config.xml

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">
	<context:annotation-config>
    <context:component-scan base-package="com.concretepage"/>
    <bean id="book" class="com.concretepage.Book" init-method="myPostConstruct" destroy-method="myPreDestroy">
      <property name="bookName" value="Mahabharat"/>
    </bean>
    <bean class="com.concretepage.MyBeanPostProcessor"/>
</beans> 

//SpringDemo

public class SpringDemo {
	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
		Book book = (Book)context.getBean("book");
		System.out.println("Book Name:"+ book.getBookName());
	        context.registerShutdownHook();
	}
} 

//Output
---inside constructor---
setBookName: Book name has set.
---BeanNameAware.setBeanName---
---BeanClassLoaderAware.setBeanClassLoader---
---BeanFactoryAware.setBeanFactory---
BeanPostProcessor.postProcessBeforeInitialization
---@PostConstruct---
---InitializingBean.afterPropertiesSet---
---init-method---
BeanPostProcessor.postProcessAfterInitialization
Book Name:Mahabharat
---@PreDestroy---
---DisposableBean.destroy---
---destroy-method--- 
________________________________________________________________________________________________________________________________________________________________________
//Spring scopes
->	<bean id="beanId" class="com.howtodoinjava.BeanClass" scope="request" />
->	@Scope

1.	singleton(default)	: Single bean object instance per spring IoC container.
2.	prototype	: Opposite to singleton, it produces a new instance each and every time a bean is requested.
---------------Only valid in web-aware Spring ApplicationContext.-------------------
3.	request	: 	A single instance will be created and available during complete lifecycle of an HTTP request.
4.	session : 	A single instance will be created and available during complete lifecycle of an HTTP Session.
5.	application : A single instance will be created and available during complete lifecycle of ServletContext.
6.	websocket	: A single instance will be created and available during complete lifecycle of WebSocket.

________________________________________________________________________________________________________________________________________________________________________
//Loosely Coupled
The concept of object-oriented is a good design to break your system into a group of reusable objects. However, when system grows larger, especially in Java project, the huge object dependencies will always tightly coupled causing objects very hard to manage or modify. In this scenario, you can use Spring framework to act as a central module to manage all the object dependencies easily and efficiently.

//What is Dependency Injection:
Dependency Injection is the main functionality provided by Spring IOC(Inversion of Control). The Spring-Core module is responsible for injecting dependencies through either Constructor or Setter methods. The design principle of Inversion of Control emphasizes keeping the Java classes independent of each other and the container frees them from object creation and maintenance. These classes, managed by Spring, must adhere to the standard definition of Java-Bean. Dependency Injection in Spring also ensures loose-coupling between the classes.
Dependency Injection (DI) is a design pattern that removes the dependency from the programming code so that it can be easy to manage and test the application. Dependency Injection makes our programming code loosely coupled. 


//Tight Coupling Between Java Objects
class Traveler
{
    Car c=new Car();
    void startJourney()
    {
       c.move();
    }
}
class Car
{
  void move()
  {
     // logic...
  }
}

//Loose Coupling by Spring (DI)
class Traveler
{
	//@Autowired
	//@Qualifier("car")
    Vehicle v;
    public void setVehicle(Vehicle v)
    {
      this.v = v;
    }      

    void startJourney()
    {
       v.move();
    }
}

Interface Vehicle
{
   void move();
}
class Car implements Vehicle
{
    public void move()
    {
         // logic
    }
}
class Bike implements Vehicle
{
    public void move()
    {
         // logic
    }
}
----------------XML----------------
<?xml version="1.0" encoding="UTF-8"?>  
<beans xmlns="http://www.springframework.org/schema/beans" >  
	<bean id="traveller" class="com.spring.example.Traveller">  
		<property name="Vehicle" ref = "car" />  
	</bean>
	<bean id="car" class="com.spring.example.Car" /> 
	
//	<bean id="traveller" class="com.spring.example.Traveller"> 
//        <constructor-arg> 
//            <bean class="com.spring.example.Car" /> 
//        </constructor-arg> 
//	</bean>
//  <bean id="car" class="com.spring.example.Car" /> 

    </bean>
</beans>
---------------Java Class------------
@Configuration
public class MyConfiguration {
    @Bean
    public Traveler getTraveller() {
		return getCar();
	}
	@Bean
    public Car getCar() {
		return new Car();
	}
}

In above example, spring container will inject either Car object or Bike object into the Traveler by calling setter method, So if Car object is replaced with Bike then no changes are required in Traveler class, this means there is loose coupling between Traveler and Vehicle object.
--------------------------------------------------------------------Injecting Values--------------------------------------------------------------------
1. Constructor-Based Dependency Injection

//Book.class
package com.spring.example;  
public class Book {  
    private int id;  
    private String bookName;  
    public Book() {System.out.println("Java");}  
    public Book(int id) {this.id = id;}  
    public Book(String bookName) {  this.bookName = bookName;}  
    public Book(int id, String bookName) {  
        this.id = id;  
        this.bookName = bookName;  
    }  
    void display(){  
        System.out.println(id+" "+bookName);  
    }  
}

//applicationContext.xml
<?xml version="1.0" encoding="UTF-8"?>  
<beans xmlns="http://www.springframework.org/schema/beans"  ">  
	<bean id="book" class="com.spring.example.Book">  
	<constructor-arg value="1" type="int"></constructor-arg>  
		
	// multiple args for constructor
	//	<constructor-arg type="java.lang.String">
	//		<value>mkyong</value>
	//	</constructor-arg>
		
	//	<constructor-arg type="java.lang.String">
	//		<value>188</value>
	//	</constructor-arg>
</bean>  
</beans>

2. Setter-Based Dependency Injection

//BVook.javapackage com.spring.example;  
public class Book {  
    private int id;  
    private String bookName;  
    private String author;  
    public int getId() {  
        return id;  
    }  
    public void setId(int id) {  
        this.id = id;  
    }  
    public String getBookName() {  
        return bookName;  
    }  
    public void setBookName(String bookName) {  
        this.bookName = bookName;  
    }  
    public String getAuthor() {  
        return author;  
    }  
    public void setAuthor(String author) {  
        this.author = author;  
    }  
    void display(){  
        System.out.println(id+" "+bookName+" "+author);  
    }  
}

//applicationContext.xml
<?xml version="1.0" encoding="UTF-8"?>  
<beans xmlns="http://www.springframework.org/schema/beans"  ">  
	<bean id="book" class="com.spring.example.Book">  
		<property name="id">  
			<value>1</value>  
		</property>  
		<property name="bookName">  
			<value>The Complete Reference J2EE</value>  
		</property>  
		<property name="author">  
			<value>Herbert Schildt</value>  
		</property>  
	</bean>  
</beans>













