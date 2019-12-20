//Hibernate

	1. Configuration : Generally written in hibernate.properties or hibernate.cfg.xml files. For Java configuration, you may find class annotated with @Configuration. 
	It is used by Session Factory to work with Java Application and the Database. It represents an entire set of mappings of an application Java Types to an SQL database.
	
	//hibernate.cfg.xml
	<?xml version="1.0" encoding="UTF-8"?>
	<!DOCTYPE hibernate-configuration PUBLIC "-//Hibernate/Hibernate Configuration DTD 3.0//EN" "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
	<hibernate-configuration>
		<session-factory>
			<property name="hibernate.connection.driver_class">com.mysql.jdbc.Driver</property>
			<property name="hibernate.connection.url">jdbc:mysql://localhost:3307/neon</property>
			<property name="hibernate.connection.password">lg225295</property>
			<property name="hibernate.connection.username">root</property>
			<property name="hibernate.dialect">org.hibernate.dialect.MySQLDialect</property>
			<property name="hbm2ddl.auto">create</property> <!--or update-->
			<property name="show_sql">true</property>
			
			<!-- use @Entity or configure it as following by mapping-->
			//
			<mapping class="hibernate.test.dto.EmployeeEntity"></mapping>
			//
		</session-factory>
	</hibernate-configuration>
________________________________________________________________________________________________________________________________________________________________	
	2. Persistent objects : These are plain old Java objects (POJOs), which get persisted as one of the rows in the related table in the database by hibernate.They can be configured in configurations files (hibernate.cfg.xml or hibernate.properties) or annotated with @Entity annotation.
		
		@Temporal(TemporalType.DATE)/TIME/TIMESTAMP
		//@Temporal should only be set on a java.util.Date or java.util.Calendar

		@GeneratedValue Provides for the specification of generation strategies for the values of primary keys.
		//once annotated it will override any other value set in POJO when inserting rercord.
				AUTO – either identity column, sequence or table depending on the underlying DB //Auto
				TABLE – table holding the id //Allocates different seperate table to keep track of generation of id
				IDENTITY – identity column //Generates value using Identity column feature of database Eg. MySQL
				SEQUENCE – sequence //Generates by using sequence object of database table using previous value.


		---------------------------------------------------------------------------------------------------------------
		@Entity
		@Table(name="alien_table")				//if @Table is not there it will automatically pick-up class name.
		public class Alien{
			@Id									//primary key
			@GeneratedValue(strategy = GenerationType.IDENTITY)         //Automatically generates Id.
			@Column(name = "ID", unique = true, nullable = false)
			private int aid;
			
			@Transient							//wont be written in database
			private String aname;
			
			@Column(name="alien_color")         //if @Column is not there it will automatically pick-up variable name.
			private String color;
			
			@Column(name = "EMAIL", unique = true, nullable = false, length = 100)
			private String email;
		
		}
________________________________________________________________________________________________________________________________________________________________

	3. Session Factory : Any user application requests Session Factory for a session object. Session Factory uses configuration information from above listed files,
	                     to instantiates the session object appropriately.
	
	4. Session : This represents the interaction between the application and the database at any point of time. 
				 This is represented by the org.hibernate.Session class. The instance of a session can be retrieved from the SessionFactory bean.

				 Session objects provide the main interface to accomplish work with the database. 
				 Persistent objects are saved and retrieved through a Session object. A Session object is lightweight and inexpensive to create. 
				 A Session object does the work of getting a physical connection to the database. Session objects maintain a cache for a single application thread (request).

				 Session objects are not thread safe. 
				 Therefore, session objects should not be kept open for a long time.Applications create and destroy these as needed. 
				 Typically, they are created to complete a single unit of work, but may span many units.

	5.SQL Dialects in Hibernate: The dialect specifies the type of database used in hibernate so that hibernate generate appropriate type of SQL statements. 
								 For connecting any hibernate application with the database, it is required to provide the configuration of SQL dialect.
	
		public class HibernateUtil{
			private static SessionFactory sessionFactory;
			private static SessionFactory buildSessionFactory(){
					try {							
							Configuration configuration = new Configuration().configure();
							
							//When using custom xml other than default hibernate.cgf.xml
							
							Configuration configuration = new Configuration().configure(new File("custom.cgf.xml"));
							
							//when not using mapping <mapping class="hibernate.test.dto.EmployeeEntity"></mapping>
							Configuration configuration = new Configuration().configure().addAnnotatedClass(Alien.class);
							
							//from  hibernate core 4.1 we have to build registry
							ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
							
							sessionFactory = configuration.buildSessionFactory(reg);
													
					} catch (Throwable ex) {
						System.err.println("Initial SessionFactory creation failed." + ex);
						throw new ExceptionInInitializerError(ex);
					}
					return sessionFactory;
			}
				
			public static SessionFactory getSessionFactory() {
				if(sessionFactory == null)
					return buildSessionFactory();
				else
					return sessionFactory;
				
					
			}
 
			public static void shutdown() {
			// Close caches and connection pools
				getSessionFactory().close();
			}
		}
		
		-------------------------------When using only java classes instead of xml-------------------------
		private static SessionFactory buildSessionFactory(){
					try {	
							Configuration configuration = new Configuration();
							
							// Hibernate settings equivalent to hibernate.cfg.xml's properties
							Properties settings = new Properties();
							settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
							settings.put(Environment.URL, "jdbc:mysql://localhost:3306/hibernate_db?useSSL=false");
							settings.put(Environment.USER, "root");
							settings.put(Environment.PASS, "root");
							settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
							settings.put(Environment.SHOW_SQL, "true");
							settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
							settings.put(Environment.HBM2DDL_AUTO, "create-drop");
							configuration.setProperties(settings);
							configuration.addAnnotatedClass(Student.class);
							ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
							sessionFactory = configuration.buildSessionFactory(reg);
													
					} catch (Throwable ex) {
						System.err.println("Initial SessionFactory creation failed." + ex);
						throw new ExceptionInInitializerError(ex);
					}
					return sessionFactory;
		}
		
		---------------------------------//Spring-Hibernate//------------------------------------------
		<?xml version="1.0" encoding="UTF-8"?>
			<beans xmlns="...">
			 
				<bean id="sessionFactory"
				  class="org.springframework.orm.hibernate5.LocalSessionFactoryBean">
					<property name="dataSource"
					  ref="dataSource"/>
					<property name="packagesToScan"
					  value="com.baeldung.hibernate.bootstrap.model"/>
					<property name="hibernateProperties">
						<props>
							<prop key="hibernate.hbm2ddl.auto">
								create-drop
							</prop>
							<prop key="hibernate.dialect">
								org.hibernate.dialect.H2Dialect
							</prop>
						</props>
					</property>
				</bean>
			 
				<bean id="dataSource"
				  class="org.apache.tomcat.dbcp.dbcp2.BasicDataSource">
					<property name="driverClassName" value="org.h2.Driver"/>
					<property name="url" value="jdbc:h2:mem:db;DB_CLOSE_DELAY=-1"/>
					<property name="username" value="sa"/>
					<property name="password" value="sa"/>
				</bean>
			 
				<bean id="txManager"
				  class="org.springframework.orm.hibernate5.HibernateTransactionManager">
					<property name="sessionFactory" ref="sessionFactory"/>
				</bean>
			</beans>
		-----------------------------in Java Class-------------------------
		@Configuration
		@EnableTransactionManagement
		@PropertySource({ "classpath:db.properties" })
		@ComponentScan( basePackages = "com.spring.hibernate" )
		public class HibernateConfig {

			@Autowired
			private Environment env;

			@Bean
			public DataSource getDataSource() {
				BasicDataSource dataSource = new BasicDataSource();
				dataSource.setDriverClassName(env.getProperty("db.driver"));
				dataSource.setUrl(env.getProperty("db.url"));
				dataSource.setUsername(env.getProperty("db.username"));
				dataSource.setPassword(env.getProperty("db.password"));
				return dataSource;
			}

			@Bean
			public LocalSessionFactoryBean getSessionFactory() {
				LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
				factoryBean.setDataSource(getDataSource());

				Properties props=new Properties();
				props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
				props.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
				props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));

				factoryBean.setHibernateProperties(props);
				factoryBean.setAnnotatedClasses(Employee.class);
				return factoryBean;
			}

			@Bean
			public HibernateTransactionManager getTransactionManager() {
				HibernateTransactionManager transactionManager = new HibernateTransactionManager();
				transactionManager.setSessionFactory(getSessionFactory().getObject());
				return transactionManager;
			}

		}
		
		------------------------------db.properties--------------------------
		# MySQL properties
		db.driver=com.mysql.cj.jdbc.Driver
		db.url=jdbc:mysql://localhost:3306/employeedb
		db.username=rick
		db.password=12Shyamali23@#$

		# Hibernate properties
		hibernate.dialect=org.hibernate.dialect.MySQLDialect
		hibernate.show_sql=true
		hibernate.hbm2ddl.auto=update
________________________________________________________________________________________________________________________________________________________________	
	5.DAO : When an application interacts with a database, it is common pattern to separate all the low level data access operations from high level business services. This can be achieved using DAOs, which are objects that provide abstract interfaces to the database. DAOs may be used from services in higher layers of the application, thus connecting a service layer and the database.
	
	public interface GenericDao<T, Id> {
 
		public void persist(T entity);
     
		public void update(T entity);
     
		public T findById(Id id);
     
		public void delete(T entity);
     
		public List<T> findAll();
     
		public void deleteAll();
     
	}
	
----------------------------------------------------------------------------------------------------------------------------------------------------------------	
	6. Transaction : enables you to achieve data consistency, and rollback incase something goes unexpected.
	
	public class BookDao implements GenericDao<Book, String> {
 
		private Session currentSession;     
		private Transaction currentTransaction;
 
			public BookDao() {
			} 
			public Session openCurrentSession() {
				currentSession = HibernateUtil.getSessionFactory().openSession();
				return currentSession;
			}		 
			public Session openCurrentSessionwithTransaction() {
				currentSession = HibernateUtil.getSessionFactory().openSession();
				currentTransaction = currentSession.beginTransaction();
				return currentSession;
			}			 
			public void closeCurrentSession() {
				currentSession.close();
			}			 
			public void closeCurrentSessionwithTransaction() {
				currentTransaction.commit();
				currentSession.close();
			}			 	 
			public Session getCurrentSession() {
				return currentSession;
			}		 
			public void setCurrentSession(Session currentSession) {
				this.currentSession = currentSession;
			}		 
			public Transaction getCurrentTransaction() {
				return currentTransaction;
			}
		 
			public void setCurrentTransaction(Transaction currentTransaction) {
				this.currentTransaction = currentTransaction;
			}
		 
			public void persist(Book entity) {
				getCurrentSession().save(entity);
			}		 
			public void update(Book entity) {
				getCurrentSession().update(entity);
			}		 
			public Book findById(String id) {
				Book book = (Book) getCurrentSession().get(Book.class, id);
				return book; 
			}		 
			public void delete(Book entity) {
				getCurrentSession().delete(entity);
			}		 
			public List<Book> findAll() {
				List<Book> books = (List<Book>) getCurrentSession().createQuery("from Book").list();
				return books;
			}		 
			public void deleteAll() {
				List<Book> entityList = findAll();
				for (Book entity : entityList) {
					delete(entity);
				}
			}
	}

	
	public class BookService {
		private static BookDao bookDao;

		public BookService() {
			bookDao = new BookDao();
		}
 
		public void persist(Book entity) {
			bookDao.openCurrentSessionwithTransaction();
			bookDao.persist(entity);
			bookDao.closeCurrentSessionwithTransaction();
		}
	 
		public void update(Book entity) {
			bookDao.openCurrentSessionwithTransaction();
			bookDao.update(entity);
			bookDao.closeCurrentSessionwithTransaction();
		}
	 
		public Book findById(String id) {
			bookDao.openCurrentSession();
			Book book = bookDao.findById(id);
			bookDao.closeCurrentSession();
			return book;
		}
	 
		public void delete(String id) {
			bookDao.openCurrentSessionwithTransaction();
			Book book = bookDao.findById(id);
			bookDao.delete(book);
			bookDao.closeCurrentSessionwithTransaction();
		}
	 
		public List<Book> findAll() {
			bookDao.openCurrentSession();
			List<Book> books = bookDao.findAll();
			bookDao.closeCurrentSession();
			return books;
		}
	 
		public void deleteAll() {
			bookDao.openCurrentSessionwithTransaction();
			bookDao.deleteAll();
			bookDao.closeCurrentSessionwithTransaction();
		}
	 
		public BookDao bookDao() {
			return bookDao;
		}
}
--------------------------in Spring-----------------------------
	@Repository
	public class EmployeeDaoImpl implements EmployeeDAO
	{
		@Autowired
		private SessionFactory sessionFactory;
		@Override
		public void addEmployee(EmployeeEntity employee) {
			this.sessionFactory.getCurrentSession().save(employee);
		}
		@SuppressWarnings("unchecked")
		@Override
		public List<EmployeeEntity> getAllEmployees() {
			return this.sessionFactory.getCurrentSession().createQuery("from EmployeeEntity").list();
		}
		@Override
		public void deleteEmployee(Integer employeeId) {
			EmployeeEntity employee = (EmployeeEntity) sessionFactory.getCurrentSession().load(
					EmployeeEntity.class, employeeId);
			if (null != employee) {
				this.sessionFactory.getCurrentSession().delete(employee);
			}
		}
	}
------------------------------------------------------------
	@Service
	public class EmployeeManager
	{
		@Autowired
		private EmployeeDAO employeeDAO;
		@Override
		@Transactional
		public void addEmployee(EmployeeEntity employee) {
			employeeDAO.addEmployee(employee);
		}
		@Override
		@Transactional
		public List<EmployeeEntity> getAllEmployees() {
			return employeeDAO.getAllEmployees();
		}
		@Override
		@Transactional
		public void deleteEmployee(Integer employeeId) {
			employeeDAO.deleteEmployee(employeeId);
		}
		public void setEmployeeDAO(EmployeeDAO employeeDAO) {
			this.employeeDAO = employeeDAO;
		}
	}
________________________________________________________________________________________________________________________________________________________________	
	7. First-level cache : It represents the default cache used by Hibernate Session object while interacting with the database. 
						   It is also called as session cache and caches objects within the current session. 
						   All requests from the Session object to the database must pass through the first-level cache or session cache. 
						   One must note that the first-level cache is available with the session object until the Session object is live.
	
	//-------------------------------------------------------------
	The first level cache is the session cache. Objects are cached within the current session and they are only alive until the session is closed.

	The second level cache exists as long as the session factory is alive. Keep in mind that in case of Hibernate, second level cache is not a tree of objects;
	object instances are not cached, instead it stores attribute values.
	After this brief introduction (so brief I know) about Hibernate cache, let’s see what is Query Cache and how is interrelated with second level cache.

	Query Cache is responsible for caching the combination of query and values provided as parameters as key, 
	and list of identifiers of objects returned by query execution as values. 
	Note that using Query Cache requires a second level cache too because when query result is get from cache (that is a list of identifiers), 
	Hibernate will load objects using cached identifiers from second level.

	To sum up, and as a conceptual schema, given next query:
		 “from Country where population > :number“, 
	after first execution, Hibernate caches would contain next fictional values (note that number parameter is set to 1000):

	L2 Cache
		[
		id:1, {name=’Spain’, population=1000, ….}
		id:2, {name=’Germany’, population=2000,…}
		….
		] 
	QueryCache
		[{from Country where population > :number, 1000}, {id:2}]
	//-------------------------------------------------------------		

	8. Second-level cache : It is used to store objects across sessions. This needs to be explicitly enabled and one would be required to provide the cache provider for a second-level cache. One of the common second-level cache providers is EhCache.
	
		- If an instance is already present in the first-level cache, it is returned from there
		- If an instance is not found in the first-level cache, and the corresponding instance state is cached in the second-level cache, then the data 
		  is fetched from there and an instance is assembled and returned
		- Otherwise, the necessary data are loaded from the database and an instance is assembled and returned
	
		Different Second level Cache Providers:
			- EH Cache
			- OS Cache
			- Swarm Cache
			- JBoss Cache
		
		i.Maven Dependecy:
			
			<dependency>
				<groupId>org.hibernate</groupId>
				<artifactId>hibernate-ehcache</artifactId>
				<version>5.2.2.Final</version>
			</dependency>
		
		However, make sure that hibernate-ehcache version is equal to Hibernate version which you use in your project, e.g. if you use hibernate-ehcache 5.2.2.Final like in this example, then the version of Hibernate should also be 5.2.2.Final.
		
		ii.Enabling Second-Level Caching:
		
			hibernate.cache.use_second_level_cache=true
			hibernate.cache.region.factory_class=org.hibernate.cache.ehcache.EhCacheRegionFactory
			hibernate.cache.use_query_cache=true
			-------------------------------- OR -----------------------------
			<properties>
				<property name="hibernate.cache.use_second_level_cache" value="true"/>
				<property name="hibernate.cache.region.factory_class" value="org.hibernate.cache.ehcache.EhCacheRegionFactory"/>
				<property name="hibernate.cache.use_query_cache" value="true"/>
			</properties>
			
			//To disable second-level caching (for debugging purposes for example), just set hibernate.cache.use_second_level_cache property to false.
		
		iii.Making an Entity Cacheable:
			
			//In order to make an entity eligible for second-level caching, we annotate it with Hibernate specific @org.hibernate.annotations.Cache annotation //and specify a cache concurrency strategy.
			
			//Some developers consider that it is a good convention to add the standard @javax.persistence.Cacheable annotation as well (although not required //by Hibernate), so an entity class implementation might look like this:
			
				@Entity
				@Cacheable
				@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
				public class Foo {
					@Id
					@GeneratedValue(strategy = GenerationType.AUTO)
					@Column(name = "ID")
					private long id;
				 
					@Column(name = "NAME")
					private String name;
					 
					// getters and setters
				}
		iv.Cache Concurrency Strategy
		
			- READ_ONLY: caching will work for read only operation.
			
				*If your application needs to read, but not modify, instances of a persistent class, a read-only cache can be used.
				*Simplest and best performing strategy.
				*Safe for use in a cluster.
			
			- NONSTRICT_READ_WRITE:  caching will work for read and write but one at a time.  (****MY RECOMENDATION*****)
			
				*Caches data that is sometimes updated without ever locking the cache.
				*If concurrent access to an item is possible, this concurrency strategy makes no guarantee that the item returned from the cache is the
				 latest version available in the database. Configure your cache timeout accordingly! This is an “asynchronous” concurrency strategy.
				*In a JTA environment, hibernate.transaction.manager_lookup_class has to be set.
				
				//Foreg. for Weblogic hibernate.transaction.manager_lookup_class=org.hibernate.transaction.WeblogicTransactionManagerLookup
				
				*For non-managed environments, tx. should be closed when session.close() or session.disconnect() is invoked.
				*This is slower than READ-ONLY but obviously faster than the next one.(READ-WRITE)
				
					• There’s no locking ever.

					• So, when the object is actually being updated in the database, at the point of committing(till database completes the commit), the cache has the old object, database has the new object.

					• Now, if any other session looks for the object , it will look in the cache and find the old object.(DIRTY READ)

					• However, as soon as the commit is complete, the object will be evicted from the cache, so that the next session which looks for the object, will have to look in the database.
					
					->Before and update the tx.commit() you will find that before the commit, the cache contained the entry, after the commit, it’s gone. Hence forcing session2 to look in the database and reload the data in the cache.
					
					->So, nonstrict read/write is appropriate if you don’t require absolute protection from dirty reads, or if the odds of concurrent access are so slim that you’re willing to accept an occasional dirty read. Obviously the window of Dirty Read is during the time when the database is actually updated, and the object has not YET been evicted from the cache.
			
			- READ_WRITE: caching will work for read and write, can be used simultaneously.
				
				*Caches data that is sometimes updated while maintaining the semantics of “read committed” isolation level. If the database is set to “repeatable read”, this concurrency strategy almost maintains the semantics. Repeatable read isolation is compromised in the case of concurrent writes. This is an “asynchronous” concurrency strategy.
				*If the application needs to update data, a read-write cache might be appropriate.
				*This cache strategy should never be used if serializable transaction isolation level is required. In a JTA environment, hibernate.transaction.manager_lookup_class has to be set.
				
				For eg. hibernate.transaction.manager_lookup_class=org.hibernate.transaction.WeblogicTransactionManagerLookup
				
					• As soon as somebody tries to update/delete an item, the item is soft-locked in the cache, so that if any other session tries to look for it, it has to go to the database.

					• Now, once the update is over and the data has been committed, the cache is refreshed with the fresh data and the lock is released, so that other transactions can now look in the CACHE and don’t have to go to the database.

					• So, there is no chance of Dirty Read, and any session will almost ALWAYS read READ COMMITTED data from the database/Cache.
					
						->If transaction rolled back, cache entry still remains locked and later reads from the DB refreshes the cache entry state
						  What if node making transactional change fails (i.e. transaction is committed to DB but not to cache) and cache state is preserved (e.g. in clustered cache), is my cache left corrupted? not really.
						->Since cache entry is locked, other transaction keep reading from the DB directly. Later hibernate times out the locked cache 
						  entry and its contents are refreshed with database state and cache entry is again back for read/write operations.
			
			- TRANSACTIONAL: caching will work for transaction.
				
				*Support for fully transactional cache implementations like JBoss TreeCache.
				*Note that this might be a less scalable concurrency strategy than ReadWriteCache. This is a “synchronous” concurrency strategy
				*Such a cache can only be used in a JTA environment and you must specify hibernate.transaction.manager_lookup_class.
				
				Note: This isn’t available for ehCache singleton ( ava. with a cache server:Terracota)
		
		V.Cache Management:
		
			If expiration and eviction policies are not defined, the cache could grow indefinitely and eventually consume all of available memory. In most cases, Hibernate leaves cache management duties like these to cache providers, as they are indeed specific to each cache implementation.
			
			For example, we could define the following Ehcache configuration to limit the maximum number of cached Foo instances to 1000:
			
			<ehcache>
				<cache name="org.baeldung.persistence.model.Foo" maxElementsInMemory="1000" />
			</ehcache>
		
		vi.Collections are not cached by default, and we need to explicitly mark them as cacheable. For example:
		
			@Entity
			@Cacheable
			@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
			public class Foo {
			 
				@Cacheable
				@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
				@OneToMany
				private Collection<Bar> bars;
			 
				// getters and setters
	
		}
		
_______________________________________________________________________________________________________________________________________________________________	
//Annotations

		i. 	@Embeddable: JPA provides the @Embedded annotation to declare that a class will be embedded by other entities.
		
			@Embeddable
			public class ContactPerson {
			 
				private String firstName;
			 
				private String lastName;
			 
				private String phone;
			 
				// standard getters, setters
			}
			
			@Embedded: The JPA annotation @Embedded is used to embed a type into another entity.
			
			@Entity
			public class Company {
			 
				@Id
				@GeneratedValue
				private Integer id;
			 
				private String name;
			 
				private String address;
			 
				private String phone;
			 
				@Embedded
				private ContactPerson contactPerson;
			 
				// standard getters, setters
			}
			
		ii. @JoinColumn: This JPA JoinColumns Annotation Example demonstrates how we can map composite primary keys to entities and use them in relationships.
		iii. CascadeType: 
		
			-CascadeType.none(default)
			-CascadeType.save-update
			-CascadeType.update 
			-CascadeType.save
			-CascadeType.delete : deletes orphans ones parent is deleted
			-CascadeType.delete-orphan : delete + allows to delete child relation without deleting parent.
			-CascadeType.all-delete-orphan
			-CascadeType.ALL
________________________________________________________________________________________________________________________________________________________________
@JoinColumn Annotation

In a One-to-Many/Many-to-One relationship, the owning side is usually defined on the ‘many' side of the relationship. It's usually the side which owns the foreign key.
The @JoinColumn annotation defines that actual physical mapping on the owning side:

mappedBy Attribute

Once we have defined the owning side of the relationship, Hibernate already has all the information it needs to map that relationship in our database. 




//@One to One
		//In hibernate there are 3 ways to create one-to-one relationships between two entities. Either way we have to use @OneToOne annotation.
		1. Hibernate one to one mapping with foreign key association
			
			//AccountEntity.java
			private id;
			@OneToOne(mappedBy="account")
			private EmployeeEntity employee;

			//EmployeeEntity.java		
			@OneToOne
			@JoinColumn(name="ACCOUNT_ID")
			private AccountEntity account;

			--------------------------------PRACTICAL------------------------------------------
			@Entity
			@Table(name="people")
			public class Person {
				@Id
			    @GeneratedValue(strategy = GenerationType.AUTO)
			    @Column(name = "id", unique = true, nullable = false)
			    private Long id;

   				@OneToOne(cascade = CascadeType.ALL, mappedBy = "person")
			    private Address address;
			}

			@Entity
			@Table(name = "address")
			public class Address {
			    @Id
			    @Column(name = "address_id")
			    long address_id1;

			    @OneToOne
			    @JoinColumn(name = "address_id") //takes the name of child column which will act as primary_key for foreign_key parent
			    @MapsId
			    private Person person;
			}

			//@MapsId tells Hibernate to use the id column of address as both primary key and foreign key. 
			//Also, notice that the @Id column of the Address entity no longer uses the @GeneratedValue annotation.

			        Person person = new Person();
			        Address address = new Address();

			        person.setAddress(address);  //order doesn't matters
			        address.setPerson(person);
			        personManagement.createPerson(person);

			------------------------------to make same primary key as foreign key----------------------------
			@Entity
			@Table(name="people")
			public class Person {
			 	@Id
			    @GeneratedValue(strategy = GenerationType.IDENTITY)
			    @Column(name = "people_id", unique = true, nullable = false)
			    private Long person_id;

			    @OneToOne(cascade = CascadeType.ALL)
			    @PrimaryKeyJoinColumn
			    private Address address;
			}

			@Entity
			@Table(name = "address")
			public class Address {
			    @Id
			    @Column(name = "address_id")
			    long address_id1;

			    @OneToOne(mappedBy="address", cascade=CascadeType.ALL)
			    private Person person;



		2. Hibernate one to one mapping with common join table
		
			//EmployeeEntity.java	
			@OneToOne(cascade = CascadeType.ALL)
			@JoinTable(name="EMPLOYEE_ACCCOUNT", joinColumns = @JoinColumn(name="EMPLOYEE_ID"),inverseJoinColumns = @JoinColumn(name="ACCOUNT_ID"))
			private AccountEntity account;
		
		3. Hibernate one to one mapping with shared primary key
			
			//EmployeeEntity.java		
			@OneToOne(cascade = CascadeType.ALL)
			@PrimaryKeyJoinColumn
			private AccountEntity account;
			
			//AccountEntity.java
			@OneToOne(mappedBy="account", cascade=CascadeType.ALL)
			private EmployeeEntity employee;
			
			
//@ManyToOne or @OneToMany 
			1. Hibernate one to many mapping with foreign key association

			@Entity
			public class Item {
			 
				@ManyToOne(fetch = FetchType.LAZY)
				@JoinColumn(name = "fk_order")
				private PurchaseOrder order;
				...
			}
				
			@Entity
			public class PurchaseOrder {
     
				@OneToMany(mappedBy = "order")
				private List<Item> items = new ArrayList<Item>();
				...
			}
			------------------------------PRACTICAL-------------------------------------
			//OneToMany
			@Entity
			@Table(name="people")
			public class Person {
			    @Id
			    @GeneratedValue(strategy = GenerationType.IDENTITY)
			    @Column(name = "people_id", unique = true, nullable = false)
			    private Long person_id;

			    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
			    private Set<Orders> orders;
			}

			@Entity
			@Table(name = "orders")
			public class Orders {
			    @Id
			    @GeneratedValue(strategy = GenerationType.IDENTITY)
			    private long order_id;

			    @ManyToOne
			    @JoinColumn(name = "customer_id", nullable=false)
			    private Person person;
			}

			2. Hibernate one to many mapping with join table

				@Entity(name = "JoinTableEmployeeEntity")
				@Table(name = "Employee", uniqueConstraints = {
				@UniqueConstraint(columnNames = "ID"),
				@UniqueConstraint(columnNames = "EMAIL") })
				public class EmployeeEntity implements Serializable
				{
				    @Id
				    @GeneratedValue(strategy = GenerationType.IDENTITY)
				    @Column(name = "ID", unique = true, nullable = false)
				    private Integer employeeId;
				 
				    @OneToMany(cascade=CascadeType.ALL)
				    @JoinTable(name="EMPLOYEE_ACCOUNT", joinColumns=@JoinColumn(name="EMPLOYEE_ID"), inverseJoinColumns=@JoinColumn(name="ACCOUNT_ID"))
				    private Set<AccountEntity> accounts;
				}
//@Many to Many
			
			@Entity
			@Table(name = "Employee")
			public class Employee { 
				// ...
			  
				@ManyToMany(cascade = { CascadeType.ALL })
				@JoinTable(
					name = "Employee_Project", 
					joinColumns = @JoinColumn(name = "employee_id"),inverseJoinColumns = @JoinColumn(name = "project_id")
				)
				Set<Project> projects = new HashSet<>();
				
				// standard constructor/getters/setters
			}

			@Entity
			@Table(name = "Project")
			public class Project {    
				// ...  
			  
				@ManyToMany(mappedBy = "projects")
				private Set<Employee> employees = new HashSet<>();
				 
				// standard constructors/getters/setters   
			}
			
			--------------------------PRACTICAL-----------------------
			@Entity
			@Table(name="people")
			public class Person {
			    @Id
			    @GeneratedValue(strategy = GenerationType.IDENTITY)
			    @Column(name = "people_id", unique = true, nullable = false)
			    private Long person_id;	

			    @ManyToMany(cascade = CascadeType.ALL)
			    @JoinTable( name="person_project", joinColumns=@JoinColumn(name="person_id"),inverseJoinColumns = @JoinColumn(name="project_id"))
			    private Set<Projects> projects;
			}
			

			@Entity
			public class Projects {
			    @Id
			    @GeneratedValue(strategy = GenerationType.IDENTITY)
			    private long project_id;

			    @ManyToMany(mappedBy = "projects")
    			private Set<Person> resources;
			}


			//The relationship definition consists of two mandatory and one optional part. The entity attribute List authors and the @ManyToMany annotation are required. The attribute models the association, and the annotation declares the kind of relationship. The @JoinTable annotation is optional.
			
			//It allows you to define the name of the join table and foreign key columns that store the many-to-many association.
			
			*	That’s OK, if you implement a basic Java class or if you model a One-to-Many/Many-to-One association. But you should never use a List if you 	model a Many-to-Many association.
			*	Hibernate handles remove operations on Many-to-Many relationships that are mapped to a java.util.List very inefficiently.
			*	It first removes all records from the association table before it inserts all remaining ones.
			*	You should instead model a many-to-many association as a java.util.Set.
			*	Hibernate then handles remove operations on the association much better. It now only removes the expected records from the association and
				keeps the others untouched.

________________________________________________________________________________________________________________________________________________________________
//Connection-Pooling
	
	1.	Why connection pooling?
	
		If we analyze the sequence of steps involved in a typical database connection life cycle, we’ll understand why:
			1.Opening a connection to the database using the database driver
			2.Opening a TCP socket for reading/writing data
			3.Reading / writing data over the socket
			4.Closing the connection
			5.Closing the socket
		It becomes evident that database connections are fairly expensive operations, and as such, should be reduced to a minimum in every possible use case (in edge cases, just avoided).
		Here’s where connection pooling implementations come into play.
		By just simply implementing a database connection container, which allows us to reuse a number of existing connections, we can effectively save the cost of performing a huge number of expensive database trips, hence boosting the overall performance of our database-driven applications.
		
		HikariCP:
		
		public class HikariCPDataSource {
			private static HikariConfig config = new HikariConfig();
			private static HikariDataSource ds;
			 
			static {
				config.setJdbcUrl("jdbc:h2:mem:test");
				config.setUsername("user");
				config.setPassword("password");
				config.addDataSourceProperty("cachePrepStmts", "true");
				config.addDataSourceProperty("prepStmtCacheSize", "250");
				config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
				ds = new HikariDataSource(config);
			}
			 
			public static Connection getConnection() throws SQLException {
				return ds.getConnection();
			}
			 
			private HikariCPDataSource(){}
		}
			
	____________________________________Configuration_In_ORM________________________________________________
	
	  <!-- Hikari specific properties -->
	  <property name="connection.provider_class">com.zaxxer.hikari.hibernate.HikariConnectionProvider</property>
	  <property name="hikari.dataSource.cachePrepStmts">true</property>
	  <property name="hikari.dataSource.prepStmtCacheSize">250</property>
	  <property name="hikari.dataSource.prepStmtCacheSqlLimit">2048</property>
	  <property name="hikari.dataSource.useServerPrepStmts">true</property>
	  <property name="hikari.maximumPoolSize">30</property>
	  <property name="hikari.idleTimeout">30000</property>

	  <!-- Database connection properties -->
	  <property name="hibernate.hikari.dataSourceClassName">com.mysql.jdbc.jdbc2.optional.MysqlDataSource</property>
	  <property name="hikari.dataSource.url">jdbc:mysql://127.0.0.1/sample</property>
	  <property name="hikari.dataSource.user">root</property>
	  <property name="hikari.dataSource.password">tiger</property>
	  
	 -------------------------------------------JAVA----------------------------------------------------
	 	// HikariCP settings								
			// Maximum waiting time for a connection from the pool
			settings.put("hibernate.hikari.connectionTimeout", "20000");
			// Minimum number of ideal connections in the pool
			settings.put("hibernate.hikari.minimumIdle", "10");
			// Maximum number of actual connection in the pool
			settings.put("hibernate.hikari.maximumPoolSize", "20");
			// Maximum time that a connection is allowed to sit ideal in the pool
			settings.put("hibernate.hikari.idleTimeout", "300000");
	
	---------------------------------------------In-Spring-----------------------------------------------
		private DataSource dataSource() {
		  final HikariDataSource ds = new HikariDataSource();
			  ds.setMaximumPoolSize(100);
			  ds.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
			  ds.addDataSourceProperty("url", url);
			  ds.addDataSourceProperty("user", username);
			  ds.addDataSourceProperty("password", password);
			  ds.addDataSourceProperty("cachePrepStmts", true);
			  ds.addDataSourceProperty("prepStmtCacheSize", 250);
			  ds.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
			  ds.addDataSourceProperty("useServerPrepStmts", true);
		  return ds;
		}
________________________________________________________________________________________________________________________________________________________________
//HQL

	i.	Session.save(obj);
		Session.update(obj);
		Session.delete(obj);
		Session.get(Laptop.class, 6);
		Session.load(Laptop.class, 6);
		
		
		//Load: gives a proxy object, only when you use it , it will give you object.
		Laptop lap = Session.load(Laptop.class, 6); //creates aproxy object
		System.out.println(lap);					//query will be fired only when it is used.
		
	ii. Query q = session.createQuerry("from student");  //where also works here// also select * doesn't work //
		List<Student> students = q.list();
		
		//we can use select with column names
		Query q = session.createQuery("select rollno,name,marks from Student wherer rollno=7");
		Object[] student = (Object[])q.uniqueResult();
		System.out.println(student[0]+" : "+student[1]+" : "+student[2]);
		
		//for multiple records
		Query q = session.createQuery("select rollno,name,marks from Student");
		List<Object[]> students = (List<Object[]>)q.list();
		 
		//To use prepared Statement
		int roll = 1113;
		Query q = session.createQuery("select rollno,name,marks from Student wherer rollno= :roll");
		q.setPreparedStatement("roll", roll);
		
		Query q = session.createQuery("from student where rollno=7");
		 Student student = (Student)q.uniqueResult();
		 
//SQL in hibernate (Native queries)

	  //Following is the syntax to get entity objects as a whole from a native sql query via addEntity().
	i. SQLQuery query = session.createSQLQuery("select * from student");
	   query.addEntity(Student.class);
	   List<Student> students = query.list();
	   
	   //Following queries were all about returning scalar values, basically returning the "raw" values from the result set.
	ii.	SQLQuery query = session.createSQLQuery("select * from student");
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List students = query.list();
		
		for(Object o : students){
			Map m = (Map)o;
			System.out.println(m.get("name")+" : "+m.get("class"));
		}
		
	iii. NamedSQL query

		String sql = "SELECT * FROM EMPLOYEE WHERE id = :employee_id";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(Employee.class);
		query.setParameter("employee_id", 10);
		List results = query.list();
________________________________________________________________________________________________________________________________________________________________
//hbm2ddl.auto (Dont use production servers, only for development purpose)
It can hold 4 properties:
	->validate: It will just validate the existing database and schema will not update or make changes to database schema but will throw exception if something is changed in annotated Model classes.
	->update: It will just change the schema, E.g: Column Name change or any other update Data will be safe. But in experience in does weird results(Hibernate introduced just for experimentational pupose dint use in production). 
	->create: It creates tables and columnsfrom POJO in java, but every time it drops the hole schema along with data and creates schema based on model classes.
	->create-drop: It does the same task as ceate except when the sessionFactory object in program is explicitly closed it drops the whole schema along with data.
					sessionFactory.close(); 
					Mainly used during Development.
________________________________________________________________________________________________________________________________________________________________
	
Dependencies:
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-core</artifactId>
    <version>5.4.2.Final</version>
</dependency>

<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-orm</artifactId>
    <version>5.1.6.RELEASE</version>
</dependency>

<dependency>
    <groupId>com.h2database</groupId> 
    <artifactId>h2</artifactId>
    <version>1.4.197</version>
</dependency>

<dependency>
    <groupId>org.apache.tomcat</groupId>
    <artifactId>tomcat-dbcp</artifactId>
    <version>9.0.1</version>
</dependency>