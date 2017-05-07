package org.propular.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.propular.constants.PropularConstants;
import org.propular.constants.StringConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class PropularRestUtil {

	private final static Logger LOGGER = LoggerFactory.getLogger(PropularRestUtil.class);

	public static Properties getProperties(String url, String projectCode, String propertyGroup, String environment,
			String securityToken) {
		ClientConfig cc = new DefaultClientConfig();
		cc.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(cc);
		StringBuffer URL = new StringBuffer(url);
		URL.append(StringConstants.SLASH).append(PropularConstants.API_PATH).append(StringConstants.SLASH)
				.append(projectCode).append(StringConstants.SLASH).append(propertyGroup).append(StringConstants.SLASH)
				.append(environment);
		WebResource webResource = client.resource(URL.toString());

		ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).header(PropularConstants.AUTHORIZATION,
				PropularConstants.BEARER + PropularConstants.SPACE + securityToken).get(ClientResponse.class);

		if (response == null || response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = response.getEntity(Map.class);
		} catch (Exception e) {
			LOGGER.error("Error in marshalling the server response, the response from server was " + map
					+ " and headers were " + response.getHeaders(), e);
		}

		Properties properties = new Properties();
		properties.putAll(map);

		return properties;
	}

}
