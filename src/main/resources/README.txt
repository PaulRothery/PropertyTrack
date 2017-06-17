# PropertyTrack
Overview
--------
Checks the zillow estimates for a given list of properties. It requires a zillow id in order to use the zillow APIs.


Configuration
-------------
The necessary configuration is set in the file propertyTrack.properties which is in the root of the delivered jar file.

There are 2 things that need to be configured:

	1. The zillow id. This is a personal id that allows you to work with the zillow APIs (ZWSID). The get your id follow these
	   instructions:
	      a. If you do not have a zillow account, set one up by following the instructions on this page:
	           https://zillow.zendesk.com/hc/en-us/articles/218194898-How-can-I-create-a-Zillow-account-
	      b. Register for a ZWSID at this page:
	           https://www.zillow.com/webservice/Registration.htm
       Once you have done they should email you a ZWSID
       
    2. A list of property id's. This is a comma separated list, one for each property to inquire on.
       To get the property id enter the address in zillow. This will take you to a URL like:
         https://www.zillow.com/homes/for_sale/13730754_zpid/globalrelevanceex_sort/39.789791,-105.103502,39.788548,-105.105889_rect/18_zm/ 
	   The property id to put in the propertyTrack.properties file is 13730754	
		
To configure the properties file do the following:

	On Windows:
	
	   1. Navigate to the jar file in the file explorer 
	   2. Right click and open
	   3. Right click and edit the propertyTrack.properties file
	   4. add the ZWSID and the list of property ids
	   5. save
	
	On Mac:
			
	   1. Open a terminal and navigate to the directory with the jar file
	   2. extract the properties file
	        jar -xvf Property-Track.jar propertyTrack.properties
	   3. edit the properties file and add the ZWSID and list or property ids, then save
            vim propertyTrack.properties
	   4. update the jar file with the edited propertyTrack.properties   
            jar -uf Property-Track.jar propertyTrack.properties
       5. delete the extracted file     
            rm propertyTrack.properties 


Execution
---------

On Windows:
  In the file explorer double click the jar file

On Mac:
  1. Open a terminal and navigate to the directory with the jar file
  2. java -cp Property-Track.jar com.paul.Main  
