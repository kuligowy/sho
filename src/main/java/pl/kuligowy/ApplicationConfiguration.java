package pl.kuligowy;

import org.h2.server.web.WebServlet;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import pl.kuligowy.models.Shop;
import pl.kuligowy.models.ShopRepository;

//@Configuration
//@ComponentScan(basePackages = "pl.kuligowy")
@EnableJpaRepositories()
@SpringBootApplication
public class ApplicationConfiguration {

	private static final Logger log = org.slf4j.LoggerFactory.getLogger(ApplicationConfiguration.class);

	public static void main(String[] args) {
		SpringApplication.run(ApplicationConfiguration.class, args);
	}

	@Bean
	ServletRegistrationBean h2servletRegistration() {
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
		registrationBean.addUrlMappings("/console/*");
		return registrationBean;
	}

	@Bean
	public CommandLineRunner demo(ShopRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new Shop("Lidl", 10));
			repository.save(new Shop("Biedronka", 10));
			repository.save(new Shop("Tesco", 10));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Shop customer : repository.findAll()) {
				log.info(customer.toString());
			}

		};
	}

}
