package uk.me.ruthmills.wordsquare.predicate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;

import uk.me.ruthmills.wordsquare.letters.HashBagAvailableLetters;

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
		wordContainsAvailableLettersPredicate = new WordContainsAvailableLettersPredicate(
				new HashBagAvailableLetters(AVAILABLE_LETTERS));
	}

	/**
	 * Test that the predicate returns true if the word is "rose".
	 */
	@Test
	public void shouldReturnTrue_whenWordIsRose() {
		// given
		final String word = "rose";

		// when
		final boolean wordContainsAvailableLetters = wordContainsAvailableLettersPredicate.test(word);

		// then
		assertThat(wordContainsAvailableLetters, is(true));
	}

	/**
	 * Test that the predicate returns true if the word is "oven".
	 */
	@Test
	public void shouldReturnTrue_whenWordIsOven() {
		// given
		final String word = "oven";

		// when
		final boolean wordContainsAvailableLetters = wordContainsAvailableLettersPredicate.test(word);

		// then
		assertThat(wordContainsAvailableLetters, is(true));
	}

	/**
	 * Test that the predicate returns true if the word is "send".
	 */
	@Test
	public void shouldReturnTrue_whenWordIsSend() {
		// given
		final String word = "send";

		// when
		final boolean wordContainsAvailableLetters = wordContainsAvailableLettersPredicate.test(word);

		// then
		assertThat(wordContainsAvailableLetters, is(true));
	}

	/**
	 * Test that the predicate returns true if the word is "ends".
	 */
	@Test
	public void shouldReturnTrue_whenWordIsEnds() {
		// given
		final String word = "ends";

		// when
		final boolean wordContainsAvailableLetters = wordContainsAvailableLettersPredicate.test(word);

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
		final String word = "rave";

		// when
		final boolean wordContainsAvailableLetters = wordContainsAvailableLettersPredicate.test(word);

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
		final String word = "rere"; // nonsense word, but we don't care for the purposes of this test.

		// when
		final boolean wordContainsAvailableLetters = wordContainsAvailableLettersPredicate.test(word);

		// then
		assertThat(wordContainsAvailableLetters, is(false));
	}

	/**
	 * Test that the predicate returns true if the word is "nosed".
	 */
	@Test
	public void shouldReturnTrue_whenWordIsNosed() {
		// given
		final String word = "nosed"; // test a 5-letter word.

		// when
		final boolean wordContainsAvailableLetters = wordContainsAvailableLettersPredicate.test(word);

		// then
		assertThat(wordContainsAvailableLetters, is(true));
	}
}
