package uk.me.ruthmills.wordsquare.predicate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;

/**
 * Test suite for the Word Contains Available Letters Predicate class.
 * 
 * @author ruth
 */
public class WordContainsAvailableLettersPredicateTest {

	// Available letters to test.
	private static final String AVAILABLE_LETTERS = "eeeeddoonnnsssrv";

	// The Word Contains Available Letters Predicate class under test.
	private WordContainsAvailableLettersPredicate wordContainsAvailableLettersPredicate;

	/**
	 * Set up the test dependencies.
	 */
	@Before
	public void setUp() {
		wordContainsAvailableLettersPredicate = new WordContainsAvailableLettersPredicate(AVAILABLE_LETTERS);
	}

	/**
	 * Test that the predicate returns true if the word is "rose".
	 */
	@Test
	public void shouldReturnTrue_whenWordIsRose() {
		// given
		String word = "rose";

		// when
		boolean wordContainsAvailableLetters = wordContainsAvailableLettersPredicate.test(word);

		// then
		assertThat(wordContainsAvailableLetters, is(true));
	}

	/**
	 * Test that the predicate returns true if the word is "oven".
	 */
	@Test
	public void shouldReturnTrue_whenWordIsOven() {
		// given
		String word = "oven";

		// when
		boolean wordContainsAvailableLetters = wordContainsAvailableLettersPredicate.test(word);

		// then
		assertThat(wordContainsAvailableLetters, is(true));
	}

	/**
	 * Test that the predicate returns true if the word is "send".
	 */
	@Test
	public void shouldReturnTrue_whenWordIsSend() {
		// given
		String word = "send";

		// when
		boolean wordContainsAvailableLetters = wordContainsAvailableLettersPredicate.test(word);

		// then
		assertThat(wordContainsAvailableLetters, is(true));
	}

	/**
	 * Test that the predicate returns true if the word is "ends".
	 */
	@Test
	public void shouldReturnTrue_whenWordIsEnds() {
		// given
		String word = "ends";

		// when
		boolean wordContainsAvailableLetters = wordContainsAvailableLettersPredicate.test(word);

		// then
		assertThat(wordContainsAvailableLetters, is(true));
	}

	/**
	 * Test that the predicate returns false when it contains a letter not in the
	 * available letters.
	 */
	@Test
	public void shouldReturnFalse_whenWordContainsLetterNotInAvailableLetters() {
		// given
		String word = "rave";

		// when
		boolean wordContainsAvailableLetters = wordContainsAvailableLettersPredicate.test(word);

		// then
		assertThat(wordContainsAvailableLetters, is(false));
	}

	/**
	 * Test that the predicate returns false when it contains more instances of a
	 * letter than are available.
	 */
	@Test
	public void shouldReturnFalse_whenWordContainsMoreInstancesOfALetterThanAreAvailable() {
		// given
		String word = "rere"; // nonsense word, but we don't care for the purposes of this test.

		// when
		boolean wordContainsAvailableLetters = wordContainsAvailableLettersPredicate.test(word);

		// then
		assertThat(wordContainsAvailableLetters, is(false));
	}
}
