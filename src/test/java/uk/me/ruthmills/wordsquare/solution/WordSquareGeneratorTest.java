package uk.me.ruthmills.wordsquare.solution;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import uk.me.ruthmills.wordsquare.exception.FirstWordSquareSolvedException;
import uk.me.ruthmills.wordsquare.exception.InvalidWordSquareException;

/**
 * Test suite for the Word Square Generator class.
 * 
 * @author ruth
 */
public class WordSquareGeneratorTest {

	/**
	 * Test getting all valid solutions for a given starting word in a 3-letter word
	 * square (there is only one in this case).
	 */
	@Test
	public void shouldGetAllValidSolutionsFor3LetterStartingWord()
			throws FirstWordSquareSolvedException, InvalidWordSquareException, IOException {
		// given
		final SolutionState solutionState = new SolutionState(3, "ddggoooox");
		final String startingWord = "dog";

		// when
		WordSquareGenerator.getValidWordSquaresForStartingWord(solutionState, startingWord);

		// then
		assertThat(solutionState.getWordSquares(), hasSize(1));
		assertThat(solutionState.getWordSquares().get(0).getWords().toString(), is("[dog, oxo, god]"));
	}

	/**
	 * Test getting all valid solutions for a given starting word in a 5-letter word
	 * square (there are two in this case).
	 */
	@Test
	public void shouldGetAllValidSolutionsFor5LetterStartingWord()
			throws FirstWordSquareSolvedException, InvalidWordSquareException, IOException {
		// given
		final SolutionState solutionState = new SolutionState(5, "aaaeeeefhhmoonssrrrrttttw");
		final String startingWord = "feast";

		// when
		WordSquareGenerator.getValidWordSquaresForStartingWord(solutionState, startingWord);

		// then
		assertThat(solutionState.getWordSquares(), hasSize(2));
		assertThat(solutionState.getWordSquares().get(0).getWords().toString(),
				is("[feast, earth, armer, steno, throw]"));
		assertThat(solutionState.getWordSquares().get(1).getWords().toString(),
				is("[feast, earth, armor, stone, threw]"));
	}

	/**
	 * Test getting the first valid solutions for a given starting word in a
	 * 5-letter word square.
	 */
	@Test
	public void shouldGetFirstValidSolutionFor5LetterStartingWord() throws InvalidWordSquareException, IOException {
		// given
		final SolutionState solutionState = new SolutionState(5, "aaaeeeefhhmoonssrrrrttttw", true);
		final String startingWord = "feast";

		// when
		try {
			WordSquareGenerator.getValidWordSquaresForStartingWord(solutionState, startingWord);
			fail("Expected FirstWordSquareSolvedException was not thrown");
		} catch (FirstWordSquareSolvedException ex) {
			// then
			assertThat(ex.getWordSquare().getWords().toString(), is("[feast, earth, armer, steno, throw]"));
		}
	}

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
