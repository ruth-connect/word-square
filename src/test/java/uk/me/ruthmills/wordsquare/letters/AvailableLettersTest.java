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

	// Available letters to test.
	private static final String AVAILABLE_LETTERS = "ddeeeennnoorsssv";

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
		// when
		final AvailableLetters availableLetters = getAvailableLetters(AVAILABLE_LETTERS);
		final String result = availableLetters.toString();

		// then
		assertThat(result, is(AVAILABLE_LETTERS));
	}

	/**
	 * Test that the word "rose" is formable from the available letters.
	 */
	@Test
	public void shouldReturnTrue_whenWordIsRose() {
		// given
		final String word = "rose";

		// when
		final AvailableLetters availableLetters = getAvailableLetters(AVAILABLE_LETTERS);
		final boolean wordContainsAvailableLetters = availableLetters.isWordFormable(word);

		// then
		assertThat(wordContainsAvailableLetters, is(true));
	}

	/**
	 * Test that the word "oven" is formable from the available letters.
	 */
	@Test
	public void shouldReturnTrue_whenWordIsOven() {
		// given
		final String word = "oven";

		// when
		final AvailableLetters availableLetters = getAvailableLetters(AVAILABLE_LETTERS);
		final boolean wordContainsAvailableLetters = availableLetters.isWordFormable(word);

		// then
		assertThat(wordContainsAvailableLetters, is(true));
	}

	/**
	 * Test that the word "send" is formable from the available letters.
	 */
	@Test
	public void shouldReturnTrue_whenWordIsSend() {
		// given
		final String word = "send";

		// when
		final AvailableLetters availableLetters = getAvailableLetters(AVAILABLE_LETTERS);
		final boolean wordContainsAvailableLetters = availableLetters.isWordFormable(word);

		// then
		assertThat(wordContainsAvailableLetters, is(true));
	}

	/**
	 * Test that the word "ends" is formable from the available letters.
	 */
	@Test
	public void shouldReturnTrue_whenWordIsEnds() {
		// given
		final String word = "ends";

		// when
		final AvailableLetters availableLetters = getAvailableLetters(AVAILABLE_LETTERS);
		final boolean wordContainsAvailableLetters = availableLetters.isWordFormable(word);

		// then
		assertThat(wordContainsAvailableLetters, is(true));
	}

	/**
	 * Test that a word is not formable if it contains a letter not in the available
	 * letter).
	 */
	@Test
	public void shouldReturnFalse_whenWordContainsLetterNotInAvailableLetters() {
		// given
		final String word = "rave";

		// when
		final AvailableLetters availableLetters = getAvailableLetters(AVAILABLE_LETTERS);
		final boolean wordContainsAvailableLetters = availableLetters.isWordFormable(word);

		// then
		assertThat(wordContainsAvailableLetters, is(false));
	}

	/**
	 * Test that a word is not formable when it contains more instances of a letter
	 * than are available.
	 */
	@Test
	public void shouldReturnFalse_whenWordContainsMoreInstancesOfALetterThanAreAvailable() {
		// given
		final String word = "rere"; // nonsense word, but we don't care for the purposes of this test.

		// when
		final AvailableLetters availableLetters = getAvailableLetters(AVAILABLE_LETTERS);
		final boolean wordContainsAvailableLetters = availableLetters.isWordFormable(word);

		// then
		assertThat(wordContainsAvailableLetters, is(false));
	}

	/**
	 * Test that the word "nosed" is formable from the available letters.
	 */
	@Test
	public void shouldReturnTrue_whenWordIsNosed() {
		// given
		final String word = "nosed"; // test a 5-letter word.

		// when
		final AvailableLetters availableLetters = getAvailableLetters(AVAILABLE_LETTERS);
		final boolean wordContainsAvailableLetters = availableLetters.isWordFormable(word);

		// then
		assertThat(wordContainsAvailableLetters, is(true));
	}

	/**
	 * Test getting the remaining letters after removing those in the current word
	 * from the available letters.
	 */
	@Test
	public void shouldGetRemainingLetters_whenRemovingLettersFromCurrentWord() {
		// given
		final String word = "dog";
		final String letters = "ddggoooox";

		// when
		AvailableLetters availableLetters = getAvailableLetters(letters);
		final String remainingLetters = availableLetters.getRemainingLetters(word).toString();

		// then
		assertThat(remainingLetters, is("dgooox"));
	}

	/**
	 * Test getting the count of available letters.
	 */
	@Test
	public void shouldGetCountOfAvailableLetters() {
		// given
		final String letters = "ddggoooox";

		// when
		AvailableLetters availableLetters = getAvailableLetters(letters);
		int count = availableLetters.getCount();

		// then
		assertThat(count, is(letters.length()));
	}
}
