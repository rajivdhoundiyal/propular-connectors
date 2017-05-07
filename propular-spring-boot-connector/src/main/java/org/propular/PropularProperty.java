package org.propular;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.ws.rs.core.MediaType;

import org.propular.constants.PropularConstants;
import org.propular.constants.StringConstants;
import org.propular.util.PropularRestUtil;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.core.io.support.PropertiesLoaderSupport;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class PropularProperty extends PropertiesLoaderSupport {

	private Properties properties;
	private PropularProperties propularProperties;

	public PropularProperty(PropularProperties propularProperties) throws IOException {
		this.propularProperties = propularProperties;
		process();
	}

	private void process() {
		Properties properties = null;
		try {
			properties = PropularRestUtil.getProperties(propularProperties.getUrl(), propularProperties.getProjectCode(), 
					propularProperties.getPropertyGroup(), propularProperties.getEnvironment(), propularProperties.getSecurityToken());
			loadProperties(properties);
			this.properties = properties;
		} catch (Exception e) {
			logger.error("Error in loading properties, the response from server was " + properties, e);
		}
	}

	public Properties getProperties() {
		return properties;
	}
}
