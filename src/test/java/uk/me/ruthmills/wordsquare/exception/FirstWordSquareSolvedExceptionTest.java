package uk.me.ruthmills.wordsquare.exception;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import uk.me.ruthmills.wordsquare.solution.WordSquare;

/**
 * Test suite for the First Word Square Solved exception.
 * 
 * @author ruth
 */
public class FirstWordSquareSolvedExceptionTest {

	/**
	 * Test that we can get the word square from the exception.
	 */
	@Test
	public void shouldGetWordSquareFromException() {
		// given
		final WordSquare wordSquare = new WordSquare(3, "dog", "oxo", "god");

		// when
		final FirstWordSquareSolvedException firstWordSquareSolvedException = new FirstWordSquareSolvedException(
				wordSquare);
		final WordSquare wordSquareFromException = firstWordSquareSolvedException.getWordSquare();

		// then
		assertThat(wordSquareFromException, is(wordSquare));
	}
}
