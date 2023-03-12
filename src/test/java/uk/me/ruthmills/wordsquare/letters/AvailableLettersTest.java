package uk.me.ruthmills.wordsquare.letters;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

/**
 * Abstract class for testing implementations of the Available Letters
 * interface. We should be able to implement a set of tests which will be common
 * to ALL implementations of the Available Letters interface.
 * 
 * @author ruth
 */
public abstract class AvailableLettersTest {

	/**
	 * Get the available letters instance.
	 * 
	 * @param letters String containing the available letters.
	 */
	abstract AvailableLetters getAvailableLetters(String letters);

	/**
	 * Should construct OK, and convert to a String with the expected letters in it
	 * in alphabetical order.
	 */
	@Test
	public void shouldConstructOK_andConvertToString() {
		// given
		final String AVAILABLE_LETTERS = "aaccdeeeemmnnnoo";

		// when
		final AvailableLetters availableLetters = getAvailableLetters(AVAILABLE_LETTERS);
		final String result = availableLetters.toString();

		// then
		assertThat(result, is(AVAILABLE_LETTERS));
	}
}
