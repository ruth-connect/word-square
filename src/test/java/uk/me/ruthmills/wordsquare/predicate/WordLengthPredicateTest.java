package uk.me.ruthmills.wordsquare.predicate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;

import uk.me.ruthmills.wordsquare.predicate.WordLengthPredicate;

/**
 * Test suite for the Word Length Predicate class.
 * 
 * @author ruth
 */
public class WordLengthPredicateTest {

	// Expected word length to test.
	private static final int EXPECTED_WORD_LENGTH = 5;

	// The Word Length Predicate class under test.
	private WordLengthPredicate wordLengthPredicate;

	/**
	 * Set up the test dependencies.
	 */
	@Before
	public void setUp() {
		wordLengthPredicate = new WordLengthPredicate(EXPECTED_WORD_LENGTH);
	}

	/**
	 * Test that the predicate returns false if the word is too short.
	 */
	@Test
	public void shouldReturnFalse_whenWordIsTooShort() {
		// given
		String word = "frog";

		// when
		boolean isWordCorrectLength = wordLengthPredicate.test(word);

		// then
		assertThat(isWordCorrectLength, is(false));
	}

	/**
	 * Test that the predicate returns true if the word is the expected length.
	 */
	@Test
	public void shouldReturnTrue_whenWordIsExpectedLength() {
		// given
		String word = "kebab";

		// when
		boolean isWordCorrectLength = wordLengthPredicate.test(word);

		// then
		assertThat(isWordCorrectLength, is(true));
	}

	/**
	 * Test that the predicate returns false if the word is too long.
	 */
	@Test
	public void shouldReturnFalse_whenWordIsTooLong() {
		// given
		String word = "guitar";

		// when
		boolean isWordCorrectLength = wordLengthPredicate.test(word);

		// then
		assertThat(isWordCorrectLength, is(false));
	}
}
