package com.paul;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Class to process the property ids. Invokes the api handler to get 
 * the details for each one and call the report on the summary
 */
public class CheckValue {

	private ApiHandler apiHandler;	
	private PropertyParser propertyParser;
	private PropertySummary propertySummary;
	private List<String> propertyIdList;
	
	public ApiHandler getApiHandler() {
		return apiHandler;
	}

	public void setApiHandler(ApiHandler apiHandler) {
		this.apiHandler = apiHandler;
	}

	public PropertyParser getPropertyParser() {
		return propertyParser;
	}

	public void setPropertyParser(PropertyParser propertyParser) {
		this.propertyParser = propertyParser;
	}

	public PropertySummary getPropertySummary() {
		return propertySummary;
	}

	public void setPropertySummary(PropertySummary propertySummary) {
		this.propertySummary = propertySummary;
	}

	public List<String> getPropertyIdList() {
		return propertyIdList;
	}

	public void setPropertyIdList(List<String> propertyIdList) {
		this.propertyIdList = propertyIdList;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	public void processPropertys() {
		
		for (String propertyId : propertyIdList) {
			String propertyDetails = apiHandler.getDetails(propertyId);
			propertySummary.setSummary(propertyParser.processDetails(propertyDetails));
		}
		propertySummary.report();
	}
}
