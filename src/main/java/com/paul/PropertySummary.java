package com.paul;

import java.text.NumberFormat;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Class to take a map of objects each containing an address and value
 * Simple formatter to the console
 */
public class PropertySummary {

	Map<String,String> summary = new LinkedHashMap<String,String>();

	public void setSummary(Map<String, String> summary) {
		this.summary.putAll(summary);
	}
	
	public void report() {
		
		System.out.println("----------------------------------");		
		System.out.println("---  Current property values   ---");
		System.out.println("----------------------------------");
		
		double totalValue = 0;
		for (Map.Entry<String, String> entry : summary.entrySet()) {
			
			double value = Double.parseDouble(entry.getValue());
			
			totalValue += value;
		    System.out.format("%-20s %13s\n", entry.getKey(), formatAmount(value));
		}

		System.out.format("%-20s %s\n", " ", "-------------");
		
		System.out.format("%-20s %s\n", " ", formatAmount(totalValue));
		
		
		
	}

	private String formatAmount(double value) {
		
		Locale locale = new Locale("en", "US");      
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
		return currencyFormatter.format(value);
	}
	
	
}
