package pl.kuligowy;

import java.math.BigDecimal;
import java.util.Date;
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
import pl.kuligowy.controllers.ItemController;
import pl.kuligowy.controllers.ReceiptController;
import pl.kuligowy.controllers.ShopController;

import pl.kuligowy.models.Article;
import pl.kuligowy.models.Shop;
import pl.kuligowy.models.ShopRepository;
import pl.kuligowy.models.ArticleRepository;
import pl.kuligowy.models.Receipt;
import pl.kuligowy.models.ReceiptItem;
import pl.kuligowy.models.ReceiptRepository;

//@Configuration
//@ComponentScan(basePackages = "pl.kuligowy")
@EnableJpaRepositories()
@SpringBootApplication(scanBasePackages = {"pl.kuligowy"},
        exclude = {ItemController.class, ReceiptController.class, ShopController.class})
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

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/api/**");
//            }
//        };
//    }

    @Bean
    public CommandLineRunner demo(ShopRepository shopRepository, ArticleRepository itemRepository, ReceiptRepository receiptRepository) {
        return (args) -> {
            // save a couple of customers
            Shop s = shopRepository.save(new Shop("Lidl"));
            shopRepository.save(new Shop("Biedronka"));
            shopRepository.save(new Shop("Tesco"));

            Article a1 = itemRepository.save(new Article("twarog"));
            Article a2 = itemRepository.save(new Article("platki"));
            itemRepository.save(new Article("mleko"));

            log.info("Shops found with findAll():");
            log.info("-------------------------------");
            for (Shop shop : shopRepository.findAll()) {
                log.info(shop.toString());
            }

            log.info("Items found with findAll():");
            log.info("-------------------------------");
            for (Article item : itemRepository.findAll()) {
                log.info(item.toString());
            }

            Receipt receipt = new Receipt(s, new Date());
//                        receipt.getReceiptItems().add(new ReceiptItem(a1, BigDecimal.valueOf(45), 10, receipt));
//                        receipt.getReceiptItems().add(new ReceiptItem(a2, BigDecimal.valueOf(25), 5, receipt));
            receipt.getReceiptItems().add(new ReceiptItem(a2, BigDecimal.valueOf(45), 10, receipt));
            receipt.getReceiptItems().add(new ReceiptItem(a1, BigDecimal.valueOf(25), 5, receipt));
            receiptRepository.save(receipt);
        };
    }

}
