package com.interview.date.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.interview.date.service.DateComparisonService;

/**
 * @author naveen
 *
 */

@RestController
@RequestMapping(value= "/date")
public class DateComparisonController {

	private static final Pattern DATE_VALIDATION_PATTERN = Pattern
			.compile("(0?[1-9]|[12][0-9]|3[01])\\s(0?[1-9]|1[012])\\s((19|20)\\d\\d)");

	@Autowired
	private DateComparisonService dateComparisonService;

	@Value("${input}")
	private String input;

	/**
	 * Validates the input data, and compute the difference between the two dates in
	 * days.
	 *
	 * @param req
	 *            http the request.
	 * @param response
	 *            the http response.
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/difference", method = RequestMethod.GET)
	public ResponseEntity<String> daysBetween() {

		if (StringUtils.isEmpty(input)) {
			throw new IllegalArgumentException("Please enter valid date values");
		}

		final String[] inputDates = input.split(", ");
		Matcher result = DATE_VALIDATION_PATTERN.matcher(inputDates[0]);
		if (!result.matches()) {
			throw new IllegalArgumentException(
					"Please enter valid first date. Valid format DD MM YYYY, DD MM YYYY");
		}
		result = DATE_VALIDATION_PATTERN.matcher(inputDates[1]);
		if (!result.matches()) {
			throw new IllegalArgumentException("Please enter valid second date. Valid format DD MM YYYY, DD MM YYYY");
		}
		final String response = dateComparisonService.daysDifference(inputDates[0], inputDates[1]);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	/**
	 * Simple ping method. Call this to check that the service is up and running. No
	 * input is required.
	 *
	 * @param req
	 *            http the request.
	 * @param response
	 *            the http response.
	 * @return Response body of "true".
	 */
	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public boolean test(HttpServletRequest req, HttpServletResponse response) {
		return true;
	}

}
