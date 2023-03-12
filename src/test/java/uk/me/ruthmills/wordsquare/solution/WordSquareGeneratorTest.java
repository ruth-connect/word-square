package uk.me.ruthmills.wordsquare.solution;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import uk.me.ruthmills.wordsquare.exception.InvalidWordSquareException;

/**
 * Test suite for the Word Square Generator class.
 * 
 * @author ruth
 */
public class WordSquareGeneratorTest {

	/**
	 * Test we can get all valid solutions for a 3-letter word square (there are two
	 * in this case).
	 */
	@Test
	public void shouldGetAllValidSolutionsFor3LetterWordSquare() throws InvalidWordSquareException, IOException {
		// given
		final int length = 3;
		final String letters = "ddggoooox";
		final boolean firstMatchOnly = false;

		// when
		final List<WordSquare> solutions = WordSquareGenerator.getValidWordSquares(length, letters, firstMatchOnly);

		// then
		assertThat(solutions, hasSize(2));
		assertThat(solutions.get(0).getWords().toString(), is("[dog, oxo, god]"));
		assertThat(solutions.get(1).getWords().toString(), is("[god, oxo, dog]"));
	}

	/**
	 * Test we can get only the first matching solution for a 3-letter word square.
	 */
	@Test
	public void shouldGetFirstMatchingSolutionFor3LetterWordSquare() throws InvalidWordSquareException, IOException {
		// given
		final int length = 3;
		final String letters = "ddggoooox";
		final boolean firstMatchOnly = true;

		// when
		final List<WordSquare> solutions = WordSquareGenerator.getValidWordSquares(length, letters, firstMatchOnly);

		// then
		assertThat(solutions, hasSize(1));
		assertThat(solutions.get(0).getWords().toString(), is("[dog, oxo, god]"));
	}
}
