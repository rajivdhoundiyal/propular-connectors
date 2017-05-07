package org.propular;

import java.util.Properties;

import org.propular.util.PropularRestUtil;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * Hello world!
 *
 */
public class PropularPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
	
	private String url;
	private String projectCode;
	private String propertyGroup;
	private String environment;
	private String securityToken;
	
	private PropularPropertyPlaceholderConfigurer() {
	}
	
	public void init() {
		Properties properties = null;
		try {
			properties = PropularRestUtil.getProperties(url, projectCode, propertyGroup, environment, securityToken);
			loadProperties(properties);
		} catch (Exception e) {
			logger.error("Error in loading properties, the response from server was " + properties, e);
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getPropertyGroup() {
		return propertyGroup;
	}

	public void setPropertyGroup(String propertyGroup) {
		this.propertyGroup = propertyGroup;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getSecurityToken() {
		return securityToken;
	}

	public void setSecurityToken(String securityToken) {
		this.securityToken = securityToken;
	}
}
