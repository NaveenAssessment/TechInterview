/**
 * 
 */
package com.interview.date;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author naveen
 * 
 * ----------------------------------------------------------------------------------------
 *  STEPS TO RUN THE APPLICATION AND INVOKE REST SERVICE
 *  ---------------------------------------------------------------------------------------
 *  
 *  1. Run mvn command to generate the jar file from root project directory
 *   mvn clean package
 *  
 *  2. Launch the datecomparison-rest-api as Java application from command line 
 *  java -jar target/datecomparison-rest-api.jar --input.dates="DD MM YYYY, DD MM YYYY"
 *  (example) java -jar target/datecomparison-rest-api.jar --input="01 02 1912, 03 04 1920"
 * 
 *  NOTE:
 *  - This application is packaged as a jar which has Tomcat 8 embedded.
 *  - Make sure you are using JDK 1.8 and Maven 3.x
 *  - Application uses default dates from Application.properties if no options entered when launching the app from command line
 *  - Application runs on default tomcat port 8080. (Override the port no if port used for different service --server.port=9090)
 *  - input dates between year 1900 and 2010
 *   
 *  3. Invoke the Rest Service URI from browser - http://localhost:8080/date/difference
 * 
 *  If the request is successful, an HTTP status code (200) indicating success is sent along with the Earliest date,
 *  Later date and the calculated date difference in days. 
 *  If an error occurs, an HTTP status code (400) indicating bad request is sent along of error message.
 *  
 *  -------------------------------------------------------------------------------------------
 *  DESIGN AND SOLUTION APPROACH
 *  -------------------------------------------------------------------------------------------
 *  Spring Boot Starter Class
 *  		- Spring Boot framework is used to develop the service DateComparisonApplication.java 
 *  
 *  Rest Controller
 *  		- Date comparison service is exposed as a rest service DateComparisonController.java.
 *  
 *  Service Class
 *  		- DateComparisonService.java Provides business logic to the Date Comparison ControllerBusiness
 *  		  Difference of number of days for both input dates is calculated	
 *  
 *  Exception handler
 *  		- DateExceptionHandler exception handler class
 *  
 *  Date Class
 *  		- A custom date class MyDate.java is used to map the input dates and validates the Dates.
 *   	- Constructor takes in a date string in DD MM YYYY format and parses the day, month and year
 *   	- Validates the date for day, month and year as per the calendar days in a month including the leap year
 *   	- Date Difference: To compute the days for a date we would use 00 MAR 0000 as a reference base date.
 *	  	  Length of the year 365.2424 days, which can be written as 365.0000 + 0.2500 -0.0100 + 0.0025 
 *	   	  or represented as fractions 365 + 1/4 -1/100 +1/100 
 *		  Since the number of days are not the same in Feb, for calculation only 5 months is considered starting from Mar to handle the leap year offsets, we will get the corrections 
 *		  (31+ 30 + 31 + 30+ 31 = 153 days) 
 *		  Now the number of days for a date =  365*year+ year/4 - year/100 + year/400 + date + (153*month+8)/5 
 *
 *  -------------------------------------------------------------------------------------------
 *  TEST DATA
 *  -------------------------------------------------------------------------------------------
 *	java -jar target/datecomparison-rest-api.jar --input="01 02 1912, 03 04 1920"		Expected: 01 02 1912, 03 04 1920, 2984
 *                                                       "20 07 1986, 10 08 1982"               10 08 1982, 20 07 1986, 1440
 *                                                       "25 07 1990, 23 04 1990"               23 04 1990, 25 07 1990, 93
 *                                                       "10 10 1882, 04 12 1913"               Please enter valid first date. Valid format DD MM YYYY, DD MM YYYY
 *                                                       "31 06 1956, 20 01 2009"               Invalid dates. Please verify the date, month and year 
 *                                                       
 */

@SpringBootApplication
public class DateComparisonApplication implements ApplicationRunner {

	private static final Logger LOG = LoggerFactory.getLogger(DateComparisonApplication.class);

	/**
	 * @param args
	 */
	public static void main(String ... args) {
		SpringApplication.run(DateComparisonApplication.class, args);

	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		LOG.info("Application started with command-line arguments: {}", Arrays.toString(args.getSourceArgs()));
		LOG.info("NonOptionArgs: {}", args.getNonOptionArgs());
		LOG.info("OptionNames: {}", args.getOptionNames());

		for (String name : args.getOptionNames()) {
			LOG.info("arg-" + name + "=" + args.getOptionValues(name));
		}

		boolean containsOption = args.containsOption("input");
		LOG.info("Test data is input: " + containsOption);
	}

}
