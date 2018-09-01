package nz.co.rajees.springboot;

import nz.co.rajees.springboot.features.AppFeature;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.togglz.core.manager.EnumBasedFeatureProvider;
import org.togglz.core.spi.FeatureProvider;

@SpringBootApplication
public class SpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

	@Bean
	public FeatureProvider featureProvider() {
		return new EnumBasedFeatureProvider(AppFeature.class);
	}
}
