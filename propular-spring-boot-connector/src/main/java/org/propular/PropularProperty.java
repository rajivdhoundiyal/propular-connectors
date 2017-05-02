package org.propular;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.ws.rs.core.MediaType;

import org.propular.constants.PropularConstants;
import org.propular.constants.StringConstants;
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

		Client client = Client.create();
		StringBuffer URL = new StringBuffer(propularProperties.getUrl());
		URL.append(StringConstants.SLASH).append(PropularConstants.API_PATH).append(StringConstants.SLASH)
				.append(propularProperties.getProjectId()).append(StringConstants.SLASH)
				.append(propularProperties.getPropertyGroup()).append(StringConstants.SLASH)
				.append(propularProperties.getEnvironment());
		WebResource webResource = client.resource(URL.toString());

		ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON)
				.header(PropularConstants.AUTHORIZATION,
						PropularConstants.BEARER + PropularConstants.SPACE + propularProperties.getSecurityToken())
				.get(ClientResponse.class);

		if (response == null || response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		Map<String, Object> map = new HashMap<String, Object>();
		String responseInStr = null;
		try {
			responseInStr = response.getEntity(String.class);
			map = new JacksonJsonParser().parseMap(responseInStr);
		} catch (Exception e) {
			logger.error("Error in marshalling the server response, the response from server was " + responseInStr
					+ " and headers were " + response.getHeaders(), e);
		}

		Properties properties = new Properties();
		properties.putAll(map);
		try {
			loadProperties(properties);
		} catch (Exception e) {
			logger.error("Error in loading properties, the response from server was " + responseInStr
					+ " and headers were" + response.getHeaders(), e);
		}
		this.properties = properties;
	}

	public Properties getProperties() {
		return properties;
	}
}
