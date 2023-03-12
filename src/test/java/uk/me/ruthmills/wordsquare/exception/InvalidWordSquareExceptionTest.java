package uk.me.ruthmills.wordsquare.exception;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import uk.me.ruthmills.wordsquare.solution.WordSquare;

/**
 * Test suite for the Invalid Word Square exception.
 * 
 * @author ruth
 */
public class InvalidWordSquareExceptionTest {

	/**
	 * Test that we can get the word square from the exception.
	 */
	@Test
	public void shouldGetWordSquareFromException() {
		// given
		final WordSquare wordSquare = new WordSquare(3, "dog", "oxo", "dog");

		// when
		final InvalidWordSquareException invalidWordSquareException = new InvalidWordSquareException(wordSquare);
		final WordSquare wordSquareFromException = invalidWordSquareException.getWordSquare();

		// then
		assertThat(wordSquareFromException, is(wordSquare));
	}
}
