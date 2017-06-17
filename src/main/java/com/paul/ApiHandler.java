package com.paul;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
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

		ResponseEntity<String> result = null;
		try {
			result = restTemplate.exchange(url + id, HttpMethod.GET, entity, String.class);
		} catch (IllegalArgumentException e) {
			System.out.println("Illegal Zillow ID, check ZSWID is correct in url propery of propertyTrack.properties");
			System.exit(0);
		}
		
		String propertyDetails = result.getBody();
		
		/* Randomly we get a status code of 301, URL has moved permanently.
		 * However the new URL returned in the result was the same as the original.
		 * Therefore retry with the same URL and it works OK.
		 * May need to change if the 301 occurs with consecutive calls but it hasn't
		 * yet.
		 */
		if (result.getStatusCode() == HttpStatus.MOVED_PERMANENTLY) {
			propertyDetails = retryGet(id, entity, result);			
		} 
		return propertyDetails;
	}

	private String retryGet(String id, HttpEntity entity, ResponseEntity<String> result) {

		result = restTemplate.exchange(url + id, HttpMethod.GET, entity, String.class);
		return result.getBody();
		
	}


}
