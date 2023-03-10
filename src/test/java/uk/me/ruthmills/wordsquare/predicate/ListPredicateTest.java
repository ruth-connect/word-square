package uk.me.ruthmills.wordsquare.predicate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

/**
 * Test suite for the List Predicate class.
 * 
 * @author ruth
 */
public class ListPredicateTest {

	// Expected word length to test.
	private static final int EXPECTED_WORD_LENGTH = 4;

	// Available letters.
	private static final String AVAILABLE_LETTERS = "eeeeddoonnnsssrv";

	// Word length predicate.
	private WordLengthPredicate wordLengthPredicate;

	// Available letters predicate.
	private WordContainsAvailableLettersPredicate wordContainsAvailableLettersPredicate;

	// List predicate.
	private ListPredicate listPredicate;

	/**
	 * Set up the test dependencies.
	 */
	@Before
	public void setUp() {
		wordLengthPredicate = new WordLengthPredicate(EXPECTED_WORD_LENGTH);
		wordContainsAvailableLettersPredicate = new WordContainsAvailableLettersPredicate(AVAILABLE_LETTERS);

		// The word length predicate should be less expensive in terms of processing
		// time, so we should execute this first.
		// Then, if the word length matches, we can go on and execute the available
		// letters predicate.
		listPredicate = new ListPredicate(Arrays.asList(wordLengthPredicate, wordContainsAvailableLettersPredicate));
	}

	/**
	 * Test that the predicate returns true if the word is "rose".
	 */
	@Test
	public void shouldReturnTrue_whenWordIsRose() {
		// given
		final String word = "rose";

		// when
		final boolean wordContainsAvailableLetters = listPredicate.test(word);

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
		final boolean wordContainsAvailableLetters = listPredicate.test(word);

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
		final boolean wordContainsAvailableLetters = listPredicate.test(word);

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
		final boolean wordContainsAvailableLetters = listPredicate.test(word);

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
		boolean wordContainsAvailableLetters = listPredicate.test(word);

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
		final boolean wordContainsAvailableLetters = listPredicate.test(word);

		// then
		assertThat(wordContainsAvailableLetters, is(false));
	}

	/**
	 * Test that the predicate returns false if the word is "nosed".
	 */
	@Test
	public void shouldReturnFalse_whenWordIsNosed() {
		// given
		final String word = "nosed"; // test a 5-letter word.

		// when
		final boolean wordContainsAvailableLetters = listPredicate.test(word);

		// then
		assertThat(wordContainsAvailableLetters, is(false));
	}
}
