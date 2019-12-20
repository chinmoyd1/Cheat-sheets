//JDBC
															Analogy 
Step 1: import package                              |       I
						import java.sql.*

Step 2: Load the driver								|       Love
						load mysql-connector.jar

Step 3: Register the driver							|       Raspberry
						Class.forName("com.mysql.jdbc.Driver")

Step 4: Establish the connection                    |		Extra
						Connection con = DriverManager.getConnection(url, username, password); 

Step 5: Create the statement                        |       Cheesy
					-> Statement
					-> PrepareStatement
					-> CallableStatement

						Statement st = con.createStatement();

Step 6: Execute the querry                          |       Eggy
						ResultSet rs = st.executeQuerry("SELECT * FROM STUDENTS");

Step 7: Process result								|       Pasta
						wfile(rs.next()){
							System.out.println(rs.getInt(1)+" "+rs.getString());
						}

Step 8: Close connection                            |		Chowmin
						st.close();
						rs.close();


 public static void main(String[] args) throws ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/custom";
        String username = "root";
        String password = "1223";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = null;
        Statement sts = null;
        try {
            con = DriverManager.getConnection(url, username, password);
            sts = con.createStatement();
            ResultSet rs = sts.executeQuery("SELECT * FROM students");
            while (rs.next()) {
                System.out.println(rs.getInt("StudentID") + " " + rs.getString("LastName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                sts.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

ResultSet executeQuery(String sql) throws SQLException :
This method is used for SQL statements which retrieve some data from the database. 
For example is SELECT statement. This method is meant to be used for select queries which fetch some data from the database. 
This method returns one java.sql.ResultSet object which contains the data returned by the query.

int executeUpdate(String sql) throws SQLException :
This method is used for SQL statements which update the database in some way. 
For example INSERT, UPDATE and DELETE statements. All these statements are DML(Data Manipulation Language) statements. 
This method can also be used for DDL(Data Definition Language) statements which return nothing. For example CREATE and ALTER statements. 
This method returns an int value which represents the number of rows affected by the query. This value will be 0 for the statements which return nothing.

boolean execute(String sql) throws SQLException :
This method can be used for all types of SQL statements. 
If you don’t know which method to use for you SQL statements, then this method can be the best option. This method returns a boolean value. 
TRUE indicates that statement has returned a ResultSet object and FALSE indicates that statement has returned an int value or returned nothing.
_____________________________________________________________________________________________________________________________________________________________
Spring JDBC

Spring JdbcTemplate is the most important class in Spring JDBC package.

Spring JdbcTemplate

	-> JDBC produces a lot of boiler plate code, such as opening/closing a connection to a database, handling sql exceptions etc. 
	   It makes the code extremely cumbersome and difficult to read.

	-> Implementing JDBC in the Spring Framework takes care of working with many low-level operations (opening/closing connections, executing SQL queries, etc.).	

	-> Thanks to this, when working with the database in the Spring Framework, we only need to define the connection parameters from the database and register the
	   SQL query, the rest of the work for us is performed by Spring.

	-> JDBC in Spring has several classes (several approaches) for interacting with the database. The most common of these is using the JdbcTemplate class. 
	   This is the base class that manages the processing of all events and database connections.

	-> The JdbcTemplate class executes SQL queries, iterates over the ResultSet, and retrieves the called values, updates the instructions and procedure calls, 
	   “catches” the exceptions, and translates them into the exceptions defined in the org.springframwork.dao package.

	-> Instances of the JdbcTemplate class are thread-safe. This means that by configuring a single instance of the JdbcTemplate class, 
	   we can then use it for several DAO objects.

	-> When using JdbcTemplate, most often, it is configured in the Spring configuration file. After that, it is implemented using bean in DAO classes.

------------------------------------------------------------------------------------------------------------------------------------------------------------
Java DataSource
Most of the times we are looking for 
->loose coupling for 
->connectivity so that we can switch databases easily,
->connection pooling for transaction management and 
->distributed systems support. 
 JDBC DataSource is the preferred approach if you are looking for any of these features in your application. 
 Java DataSource interface is present in javax.sql package and it only declare two overloaded methods getConnection() and getConnection(String str1,String str2).

------------------------------------------------------------------------------------------------------------------------------------------------------------
JDBC DataSource

It is the responsibility of different Database vendors to provide different kinds of implementation of DataSource interface. 
For example MySQL JDBC Driver provides basic implementation of DataSource interface with com.mysql.jdbc.jdbc2.optional.MysqlDataSource class and Oracle 
database driver implements it with oracle.jdbc.pool.OracleDataSource class.
These implementation classes provide methods through which we can provide database server details with user credentials. 
Some of the other common features provided by these JDBC DataSource implementation classes are;

Caching of PreparedStatement for faster processing
Connection timeout settings
Logging features
ResultSet maximum size threshold

public static DataSource getMySQLDataSource() {
		Properties props = new Properties();
		FileInputStream fis = null;
		MysqlDataSource mysqlDS = null;
		try {
			fis = new FileInputStream("db.properties");
			props.load(fis);
			mysqlDS = new MysqlDataSource();
			mysqlDS.setURL(props.getProperty("MYSQL_DB_URL"));
			mysqlDS.setUser(props.getProperty("MYSQL_DB_USERNAME"));
			mysqlDS.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mysqlDS;
	}
-----------------------------------------------------------------------------------------------------------------------------------------------------------
Spring DataSource

		@Configuration
		@ComponentScan({"com.model", "com.dao"})
		@PropertySource("classpath:database.properties")
		public class AppConfig {
		    @Autowired
		    Environment environment;

		    private final String URL = "url";
		    private final String USER = "dbuser";
		    private final String DRIVER = "driver";
		    private final String PASSWORD = "dbpassword";

		    @Bean
		    DataSource dataSource() {
		        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		        driverManagerDataSource.setUrl(environment.getProperty(URL));
		        driverManagerDataSource.setUsername(environment.getProperty(USER));
		        driverManagerDataSource.setPassword(environment.getProperty(PASSWORD));
		        driverManagerDataSource.setDriverClassName(environment.getProperty(DRIVER));
		        return driverManagerDataSource;
		    }
		}
-----------------------------------------------------------------------------------------------------------------------------------------------------------
RowMapper

For fetching data from database we need to implement interface RowMapper. This interface has only one method mapRow(ResultSet resultSet, int i), 
which will return one instance of our model class (i.e. Person).

		public class PersonMapper implements RowMapper<Person> {

		    public Person mapRow(ResultSet resultSet, int i) throws SQLException {

		        Person person = new Person();
		        person.setId(resultSet.getLong("id"));
		        person.setFirstName(resultSet.getString("first_name"));
		        person.setLastName(resultSet.getString("last_name"));
		        person.setAge(resultSet.getInt("age"));
		        return person;
		    }
		}

//DAO

		public interface PersonDAO {
		    Person getPersonById(Long id);

		    List<Person> getAllPersons();

		    boolean deletePerson(Person person);

		    boolean updatePerson(Person person);

		    boolean createPerson(Person person);
		}

//PersonDAOImpl
		@Component
		public class PersonDAOImpl implements PersonDAO {

		    JdbcTemplate jdbcTemplate;

		    private final String SQL_FIND_PERSON = "select * from people where id = ?";
		    private final String SQL_DELETE_PERSON = "delete from people where id = ?";
		    private final String SQL_UPDATE_PERSON = "update people set first_name = ?, last_name = ?, age  = ? where id = ?";
		    private final String SQL_GET_ALL = "select * from people";
		    private final String SQL_INSERT_PERSON = "insert into people(id, first_name, last_name, age) values(?,?,?,?)";

		    @Autowired
		    public PersonDAOImpl(DataSource dataSource) {
		        jdbcTemplate = new JdbcTemplate(dataSource);
		    }

		    public Person getPersonById(Long id) {
		        return jdbcTemplate.queryForObject(SQL_FIND_PERSON, new Object[]{id}, new PersonMapper());
		    }

		    public List<Person> getAllPersons() {
		        return jdbcTemplate.query(SQL_GET_ALL, new PersonMapper());
		    }

		    public boolean deletePerson(Person person) {
		        return jdbcTemplate.update(SQL_DELETE_PERSON, person.getId()) > 0;
		    }

		    public boolean updatePerson(Person person) {
		        return jdbcTemplate.update(SQL_UPDATE_PERSON, person.getFirstName(), person.getLastName(), person.getAge(),
		                person.getId()) > 0;
		    }

		    public boolean createPerson(Person person) {
		        return jdbcTemplate.update(SQL_INSERT_PERSON, person.getId(), person.getFirstName(), person.getLastName(),
		                person.getAge()) > 0;
		    }
		}
------------------------------------------------------------------------------------------------------------------------------------------------------------
More About 

Example of PreparedStatement in Spring JdbcTemplate

We can execute parameterized query using Spring JdbcTemplate by the help of execute() method of JdbcTemplate class. 
To use parameterized query, we pass the instance of PreparedStatementCallback in the execute method.

		public Boolean saveEmployeeByPreparedStatement(final Employee e){  
		    String query="insert into employee values(?,?,?)";  
		    return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
			    @Override  
			    public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {  
			              
			        ps.setInt(1,e.getId());  
			        ps.setString(2,e.getName());  
			        ps.setFloat(3,e.getSalary());  
			              
			        return ps.execute();
		    });  



Spring NamedParameterJdbcTemplate Example

Spring provides another way to insert data by named parameter. 
In such way, we use names instead of ?(question mark). So it is better to remember the data for the column.

		public  void save (Emp e){  
		String query="insert into employee values (:id,:name,:salary)";  
		  
		Map<String,Object> map=new HashMap<String,Object>();  
		map.put("id",e.getId());  
		map.put("name",e.getName());  
		map.put("salary",e.getSalary());  
		  
		template.execute(query,map,new PreparedStatementCallback() {  
		    @Override  
		    public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {  
		        return ps.executeUpdate();  
		    }  
		});  
		}  