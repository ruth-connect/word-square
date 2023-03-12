package uk.me.ruthmills.wordsquare.letters;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Test;

/**
 * Test suite for the Available Letters Factory class.
 * 
 * @author ruth
 */
public class AvailableLettersFactoryTest {

	/**
	 * Test that we get an instance of Available Letters.
	 */
	@Test
	public void shouldGetInstanceOfAvailableLetters() {
		// given
		final String AVAILABLE_LETTERS = "ddeeeennnoorsssv";

		// when
		AvailableLetters availableLetters = AvailableLettersFactory.getInstance(AVAILABLE_LETTERS);

		// then
		assertThat(availableLetters, notNullValue());
		assertThat(availableLetters.toString(), is(AVAILABLE_LETTERS));
	}
}
