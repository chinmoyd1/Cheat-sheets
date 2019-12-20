2.2. Using @ComponentScan in a Spring Boot Application
The trick with Spring Boot is that many things happen implicitly. We use the @SpringBootApplication annotation, but it's just a combination of three annotations:
		1.@Configuration
		2.@EnableAutoConfiguration
		3.@ComponentScan

@SpringBootApplication
public class SpringbootIn10StepsApplication {
	public static void main(String[] args) {
		ApplicationContext applicationContext = 
				SpringApplication.run(SpringbootIn10StepsApplication.class, args);
		
		for (String name : applicationContext.getBeanDefinitionNames()) {
			System.out.println(name);
		}
	}
}

//
@SpringBootApplication
public class SpringBootComponentScanApp {
    private static ApplicationContext applicationContext;
 
    @Bean
    public ExampleBean exampleBean() {
        return new ExampleBean();
    }
 
    public static void main(String[] args) {
        applicationContext = SpringApplication.run(SpringBootComponentScanApp.class, args);
        checkBeansPresence(
          "cat", "dog", "rose", "exampleBean", "springBootComponentScanApp");
 
    }
 
    private static void checkBeansPresence(String... beans) {
        for (String beanName : beans) {
            System.out.println("Is " + beanName + " in ApplicationContext: " + 
              applicationContext.containsBean(beanName));
        }
    }
}