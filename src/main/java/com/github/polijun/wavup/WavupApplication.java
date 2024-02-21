package com.github.polijun.wavup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WavupApplication {

	public static void main(String[] args) {
		SpringApplication.run(WavupApplication.class, args);
	}

	// @Bean
    // CommandLineRunner commandLineRunner(ProductRepository productRepository) {
    //     return args -> {
    //         Style style1 = Style.builder().styleName("fancy").build();
    //         Style style2 = Style.builder().styleName("tushei").build();

    //         // Create a Product with a list containing the created Style
    //         Product product = Product.builder().styles(List.of(style1, style2)).build();

    //         // Save the Product to the repository
    //         productRepository.save(product);
    //     };
    // }
}
