package pl.kuligowy;

import org.h2.server.web.WebServlet;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import pl.kuligowy.models.Item;
import pl.kuligowy.models.ItemRepository;
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
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/*").allowedOrigins("*")
				.expo;
			}
		};
	}

	@Bean
	public CommandLineRunner demo(ShopRepository shopRepository, ItemRepository itemRepository) {
		return (args) -> {
			// save a couple of customers
			shopRepository.save(new Shop("Lidl", 10));
			shopRepository.save(new Shop("Biedronka", 10));
			shopRepository.save(new Shop("Tesco", 10));

			itemRepository.save(new Item(1, "twarog", 10));
			itemRepository.save(new Item(2, "platki", 10));
			itemRepository.save(new Item(3, "mleko", 10));

			log.info("Shops found with findAll():");
			log.info("-------------------------------");
			for (Shop shop : shopRepository.findAll()) {
				log.info(shop.toString());
			}

			log.info("Items found with findAll():");
			log.info("-------------------------------");
			for (Item item : itemRepository.findAll()) {
				log.info(item.toString());
			}

		};
	}

}
