/**
 * 
 */
package com.interview.date.util;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author naveen
 *
 */
@RunWith(SpringRunner.class)
public class TestMyDate {

	@Test
	public void testValidDate() {
		String strDate = "10 08 1982";
		assertNotNull(new MyDate(strDate));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInValidMonthDate() {
		String strDate = "10 13 1982";
		new MyDate(strDate);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInValidDayDate() {
		String strDate = "41 12 1982";
		new MyDate(strDate);
	}

}
