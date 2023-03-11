package uk.me.ruthmills.wordsquare;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Test;

import uk.me.ruthmills.wordsquare.solution.WordSquare;

/**
 * Test suite for the Word Square Solver class.
 * 
 * @author ruth
 */
public class WordSquareSolverTest {

	/**
	 * Test that we can solve a word square when there are two possible solutions:
	 * dog, oxo, god AND god, oxo, dog.
	 */
	@Test
	public void shouldSolveWordSquare_whenThereAreTwoSolutions() throws IOException, URISyntaxException {
		// given
		final int length = 3;
		final String letters = "ddggoooox";

		// when
		final List<WordSquare> solutions = WordSquareSolver.solveWordSquare(length, letters);

		// then
		assertThat(solutions, hasSize(2));
		assertThat(solutions.get(0).isValid(), is(true));
		assertThat(solutions.get(1).isValid(), is(true));

		final List<String> dogOxoGod = solutions.get(0).getWords();
		assertThat(dogOxoGod, hasSize(3));
		assertThat(dogOxoGod.toString(), is("[dog, oxo, god]"));

		final List<String> godOxoDog = solutions.get(1).getWords();
		assertThat(godOxoDog, hasSize(3));
		assertThat(godOxoDog.toString(), is("[god, oxo, dog]"));
	}

	/**
	 * Test that we can solve the example word square: rose, oven, send, ends
	 */
	@Test
	public void shouldSolveExampleWordSquare() throws IOException, URISyntaxException {
		// given
		final int length = 4;
		final String letters = "eeeeddoonnnsssrv";

		// when
		final List<WordSquare> solutions = WordSquareSolver.solveWordSquare(length, letters);

		// then
		assertThat(solutions, hasSize(1));
		assertThat(solutions.get(0).isValid(), is(true));

		final List<String> solution = solutions.get(0).getWords();
		assertThat(solution, hasSize(4));
		assertThat(solution.toString(), is("[rose, oven, send, ends]"));
	}

	/**
	 * Test that we can solve the required 4-letter word square.
	 */
	@Test
	public void shouldSolve4LetterWordSquare() throws IOException, URISyntaxException {
		// given
		final int length = 4;
		final String letters = "aaccdeeeemmnnnoo";

		// when
		final List<WordSquare> solutions = WordSquareSolver.solveWordSquare(length, letters);

		// then
		assertThat(solutions, hasSize(1));
		assertThat(solutions.get(0).isValid(), is(true));

		final List<String> solution = solutions.get(0).getWords();
		assertThat(solution, hasSize(4));
		assertThat(solution.toString(), is("[moan, once, acme, need]"));
	}

	/**
	 * Test that we can solve the first required 5-letter word square. Return all
	 * possible solutions (there are 2).
	 */
	@Test
	public void shouldSolveFirst5LetterWordSquareAllSolutions() throws IOException, URISyntaxException {
		// given
		final int length = 5;
		final String letters = "aaaeeeefhhmoonssrrrrttttw";

		// when
		final List<WordSquare> solutions = WordSquareSolver.solveWordSquare(length, letters);

		// then
		assertThat(solutions, hasSize(2)); // this actually has 2 solutions, just as I predicted was possible with the
											// simpler case (dog, oxo, god AND god, oxo, dog).
		assertThat(solutions.get(0).isValid(), is(true));
		assertThat(solutions.get(1).isValid(), is(true));

		final List<String> solution1 = solutions.get(0).getWords();
		assertThat(solution1, hasSize(5));
		assertThat(solution1.toString(), is("[feast, earth, armer, steno, throw]"));

		final List<String> solution2 = solutions.get(1).getWords();
		assertThat(solution2, hasSize(5));
		assertThat(solution2.toString(), is("[feast, earth, armor, stone, threw]"));
	}

	/**
	 * Test that we can solve the first required 5-letter word square. Return only
	 * the first solution.
	 */
	@Test
	public void shouldSolveFirst5LetterWordSquareFirstSolutionOnly() throws IOException, URISyntaxException {
		// given
		final int length = 5;
		final String letters = "aaaeeeefhhmoonssrrrrttttw";

		// when
		final List<WordSquare> solutions = WordSquareSolver.solveWordSquare(length, letters, true);

		// then
		assertThat(solutions, hasSize(1)); // first solution only, this time.
		assertThat(solutions.get(0).isValid(), is(true));

		final List<String> solution1 = solutions.get(0).getWords();
		assertThat(solution1, hasSize(5));
		assertThat(solution1.toString(), is("[feast, earth, armer, steno, throw]"));
	}

	/**
	 * Test that we can solve the second required 5-letter word square.
	 */
	@Test
	public void shouldSolveSecond5LetterWordSquare() throws IOException, URISyntaxException {
		// given
		final int length = 5;
		final String letters = "aabbeeeeeeeehmosrrrruttvv";

		// when
		final List<WordSquare> solutions = WordSquareSolver.solveWordSquare(length, letters);

		// then
		assertThat(solutions, hasSize(1));
		assertThat(solutions.get(0).isValid(), is(true));

		final List<String> solution = solutions.get(0).getWords();
		assertThat(solution, hasSize(5));
		assertThat(solution.toString(), is("[heart, ember, above, revue, trees]"));
	}

	/**
	 * Test that we can solve the 7-letter word square.
	 */
	@Test
	public void shouldSolve7LetterWordSquare() throws IOException, URISyntaxException {
		// given
		final int length = 7;
		final String letters = "aaaaaaaaabbeeeeeeedddddggmmlloooonnssssrrrruvvyyy";

		// when
		// N.B. We pass the "firstMatchOnly" flag as true here - this is because there
		// is only one solution (I checked) - and it's relatively early on in the total
		// number of possible starting words. Hence, to avoid wasting time, there is no
		// point in waiting for any other solutions - as there aren't any for this case
		// anyway!
		final List<WordSquare> solutions = WordSquareSolver.solveWordSquare(length, letters, true);

		// then
		assertThat(solutions, hasSize(1));
		assertThat(solutions.get(0).isValid(), is(true));

		final List<String> solution = solutions.get(0).getWords();
		assertThat(solution, hasSize(7));
		assertThat(solution.toString(), is("[bravado, renamed, analogy, valuers, amoebas, degrade, odyssey]"));
	}
}
