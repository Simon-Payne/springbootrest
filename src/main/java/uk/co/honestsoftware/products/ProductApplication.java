package uk.co.honestsoftware.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ProductApplication {

    static FixerData fixerData;

    @Autowired
    private ProductAppProperties applicationProperties;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            String url = "http://data.fixer.io/api/latest?access_key=" + applicationProperties.getApiKey();
            ResponseEntity<FixerData> response = restTemplate.getForEntity(url, FixerData.class);
            if(response.getStatusCode().is2xxSuccessful()) {
                response.toString();
                fixerData = response.getBody();
            } else {
                System.err.println(response.toString());
                throw new RuntimeException("Unable to initialise ProductController as no data or invalid data returned from Fixer");
            }
            System.out.println("Fixer returned currency rates:\n\n" + fixerData + "\n\n");
        };
    }


    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

    public FixerData getFixerData() {
        return fixerData;
    }

}
