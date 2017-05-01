package org.propular;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The class setup's the Auto Configuration setup for spring boot.
 *
 */

@Configuration
@EnableConfigurationProperties(PropularProperties.class)
public class PropularAutoConfig {

	@Autowired
	PropularProperties propularProperties;
	
	@Bean
	@ConditionalOnMissingBean
	public PropularProperty loadProperties() throws IOException {
		return new PropularProperty(propularProperties);
	}

}
