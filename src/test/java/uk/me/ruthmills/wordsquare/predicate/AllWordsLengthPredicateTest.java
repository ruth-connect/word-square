package uk.me.ruthmills.wordsquare.predicate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import uk.me.ruthmills.wordsquare.solution.WordSquare;

/**
 * Test suite for the All Words Length Predicate class.
 * 
 * @author ruth
 */
public class AllWordsLengthPredicateTest {

	// Word length.
	private static final int WORD_LENGTH = 4;

	// Object under test.
	private AllWordsLengthPredicate allWordsLengthPredicate;

	/**
	 * Set up the test dependencies.
	 */
	@Before
	public void setUp() {
		allWordsLengthPredicate = new AllWordsLengthPredicate(WORD_LENGTH);
	}

	/**
	 * Test that the predicate returns false if there is a word that is too short.
	 */
	@Test
	public void shouldReturnFalse_whenWordPresentThatIsTooShort() {
		// given
		final WordSquare wordSquare = new WordSquare(WORD_LENGTH, Arrays.asList("test", "one", "newt"));

		// when
		final boolean areAllWordsExpectedLength = allWordsLengthPredicate.test(wordSquare);

		// then
		assertThat(areAllWordsExpectedLength, is(false));
	}

	/**
	 * Test that the predicate returns true if all the words are the correct length.
	 */
	@Test
	public void shouldReturnTrue_whenAllWordsAreCorrectLength() {
		// given
		final WordSquare wordSquare = new WordSquare(WORD_LENGTH, Arrays.asList("test", "once", "newt"));

		// when
		final boolean areAllWordsExpectedLength = allWordsLengthPredicate.test(wordSquare);

		// then
		assertThat(areAllWordsExpectedLength, is(true));
	}

	/**
	 * Test that the predicate returns false if there is a word that is too long.
	 */
	@Test
	public void shouldReturnFalse_whenWordPresentThatIsTooLong() {
		// given
		final WordSquare wordSquare = new WordSquare(WORD_LENGTH, Arrays.asList("tests", "once", "newt"));

		// when
		final boolean areAllWordsExpectedLength = allWordsLengthPredicate.test(wordSquare);

		// then
		assertThat(areAllWordsExpectedLength, is(false));
	}
}
