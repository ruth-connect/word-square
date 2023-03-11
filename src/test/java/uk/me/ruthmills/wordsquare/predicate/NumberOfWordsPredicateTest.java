package uk.me.ruthmills.wordsquare.predicate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import uk.me.ruthmills.wordsquare.solution.WordSquare;

/**
 * Test suite for the Number Of Words Predicate class.
 * 
 * @author ruth
 *
 */
public class NumberOfWordsPredicateTest {

	// Required number of words.
	private static final int NUMBER_OF_WORDS = 4;

	// Object under test.
	private NumberOfWordsPredicate numberOfWordsPredicate;

	/**
	 * Set up the test dependencies.
	 */
	@Before
	public void setUp() {
		numberOfWordsPredicate = new NumberOfWordsPredicate(NUMBER_OF_WORDS);
	}

	/**
	 * Test that the predicate returns false when there are too few words.
	 */
	@Test
	public void shouldReturnFalse_whenThereAreTooFewWords() {
		// given
		final WordSquare wordSquare = new WordSquare(Arrays.asList("banana", "apple", "orange")); // we don't care about
																									// word
		// length here.
		// when
		final boolean hasExpectedNumberOfWords = numberOfWordsPredicate.test(wordSquare);

		// then
		assertThat(hasExpectedNumberOfWords, is(false));
	}

	/**
	 * Test that the predicate returns true when there are the correct number of
	 * words.
	 */
	@Test
	public void shouldReturnTrue_whenThereAreTheCorrectNumberOfWords() {
		// given
		final WordSquare wordSquare = new WordSquare(Arrays.asList("banana", "apple", "orange", "pear"));

		// when
		final boolean hasExpectedNumberOfWords = numberOfWordsPredicate.test(wordSquare);

		// then
		assertThat(hasExpectedNumberOfWords, is(true));
	}

	/**
	 * Test that the predicate returns false when there are too many words.
	 */
	@Test
	public void shouldReturnFalse_whenThereAreTooManyWords() {
		// given
		WordSquare wordSquare = new WordSquare(Arrays.asList("banana", "apple", "orange", "pear", "grape"));

		// when
		boolean hasExpectedNumberOfWords = numberOfWordsPredicate.test(wordSquare);

		// then
		assertThat(hasExpectedNumberOfWords, is(false));
	}
}
