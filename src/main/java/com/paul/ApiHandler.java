package com.paul;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Method to construct the REST API from the given zillow property id,
 * make the call and return the XML string 
 */
public class ApiHandler {

	private RestTemplate restTemplate;
	private String url;

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}
	
	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getDetails(String id) {

		HttpHeaders headers = new HttpHeaders();
		HttpEntity entity = new HttpEntity(headers);

		ResponseEntity<String> result = restTemplate.exchange(url + id, HttpMethod.GET, entity, String.class);
		return result.getBody();
	}


}
