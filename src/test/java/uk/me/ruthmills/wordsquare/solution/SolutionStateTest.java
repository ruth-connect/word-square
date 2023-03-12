package uk.me.ruthmills.wordsquare.solution;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

import uk.me.ruthmills.wordsquare.exception.FirstWordSquareSolvedException;
import uk.me.ruthmills.wordsquare.exception.InvalidWordSquareException;

/**
 * Test suite for the Solution State class.
 * 
 * @author ruth
 */
public class SolutionStateTest {

	// Length of words in word square.
	private static final int WORD_LENGTH = 3;

	// Available letters.
	private static final String AVAILABLE_LETTERS = "ddggoooox";

	// Valid word square.
	private static final WordSquare VALID_WORD_SQUARE = new WordSquare(3, "dog", "oxo", "god");

	// Invalid word square.
	private static final WordSquare INVALID_WORD_SQUARE = new WordSquare(3, "dog", "oxo", "dog");

	/**
	 * Test that we can construct a new Solution State from initial parameters (word
	 * length and available letters).
	 */
	@Test
	public void shouldConstructNewSolutionStateFromInitialParameters() throws IOException {
		// when
		final SolutionState solutionState = new SolutionState(WORD_LENGTH, AVAILABLE_LETTERS);

		// then
		assertThat(solutionState.getLength(), is(WORD_LENGTH));
		assertThat(solutionState.getLetters().toString(), is(AVAILABLE_LETTERS));
		assertThat(solutionState.isFirstMatchOnly(), is(false));
		assertThat(solutionState.getWordShortlist(), hasSize(6));
		assertThat(solutionState.getWords(), hasSize(0));
		assertThat(solutionState.getWordSquares(), hasSize(0));
	}

	/**
	 * Test that we can construct a new Solution State also passing in the first
	 * match only flag.
	 */
	@Test
	public void shouldConstructNewSolutionStateWithFirstMatchOnly() throws IOException {
		// given
		final boolean firstMatchOnly = true;

		// when
		final SolutionState solutionState = new SolutionState(WORD_LENGTH, AVAILABLE_LETTERS, firstMatchOnly);

		// then
		assertThat(solutionState.getLength(), is(WORD_LENGTH));
		assertThat(solutionState.getLetters().toString(), is(AVAILABLE_LETTERS));
		assertThat(solutionState.isFirstMatchOnly(), is(firstMatchOnly));
		assertThat(solutionState.getWordShortlist(), hasSize(6));
		assertThat(solutionState.getWords(), hasSize(0));
		assertThat(solutionState.getWordSquares(), hasSize(0));
	}

	/**
	 * Test that we can add a valid word square.
	 */
	@Test
	public void shouldAddValidWordSquare()
			throws FirstWordSquareSolvedException, InvalidWordSquareException, IOException {
		// given
		final SolutionState solutionState = new SolutionState(WORD_LENGTH, AVAILABLE_LETTERS);

		// when
		solutionState.addWordSquare(VALID_WORD_SQUARE);

		// then
		assertThat(solutionState.getWordSquares(), hasSize(1));
		assertThat(solutionState.getWordSquares().get(0), is(VALID_WORD_SQUARE));
	}

	/**
	 * Test that an Invalid Word Square Exception is thrown when we attempt to add
	 * an invalid word square.
	 */
	@Test
	public void shouldThrowInvalidWordSquareException_whenAddingInvalidWordSquare()
			throws FirstWordSquareSolvedException, InvalidWordSquareException, IOException {
		// given
		final SolutionState solutionState = new SolutionState(WORD_LENGTH, AVAILABLE_LETTERS);

		// when
		try {
			solutionState.addWordSquare(INVALID_WORD_SQUARE);
			fail("Expected InvalidWordSquareException was not thrown!");
		} catch (InvalidWordSquareException ex) {
			// then
			assertThat(ex.getWordSquare(), is(INVALID_WORD_SQUARE));
		}
	}

	/**
	 * Test that a First Word Square Solved Exception is thrown when we add a valid
	 * word square and the firstMatchOnly flag is true.
	 */
	@Test
	public void shouldThrowFirstWordSquareSolvedException_whenAddingFirstWordSquare_andFirstMatchOnlyIsTrue()
			throws FirstWordSquareSolvedException, InvalidWordSquareException, IOException {
		// given
		final SolutionState solutionState = new SolutionState(WORD_LENGTH, AVAILABLE_LETTERS, true);

		// when
		try {
			solutionState.addWordSquare(VALID_WORD_SQUARE);
			fail("Expected FirstWordSquareSolvedException was not thrown!");
		} catch (FirstWordSquareSolvedException ex) {
			// then
			assertThat(ex.getWordSquare(), is(VALID_WORD_SQUARE));
		}
	}
}
