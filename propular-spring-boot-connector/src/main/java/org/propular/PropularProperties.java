package org.propular;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("propular")
public class PropularProperties {

	private String url;
	private String projectId;
	private String propertyGroup;
	private String environment;
	private String securityToken;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

}
