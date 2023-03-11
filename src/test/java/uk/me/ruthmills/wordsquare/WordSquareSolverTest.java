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

		final List<String> dogOxoGod = solutions.get(0).getWords();
		assertThat(dogOxoGod, hasSize(3));
		assertThat(dogOxoGod.get(0), is("dog"));
		assertThat(dogOxoGod.get(1), is("oxo"));
		assertThat(dogOxoGod.get(2), is("god"));

		final List<String> godOxoDog = solutions.get(1).getWords();
		assertThat(godOxoDog, hasSize(3));
		assertThat(godOxoDog.get(0), is("god"));
		assertThat(godOxoDog.get(1), is("oxo"));
		assertThat(godOxoDog.get(2), is("dog"));
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

		final List<String> solution = solutions.get(0).getWords();
		assertThat(solution, hasSize(4));
		assertThat(solution.get(0), is("rose"));
		assertThat(solution.get(1), is("oven"));
		assertThat(solution.get(2), is("send"));
		assertThat(solution.get(3), is("ends"));
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

		final List<String> solution = solutions.get(0).getWords();
		assertThat(solution, hasSize(4));
		assertThat(solution.get(0), is("moan"));
		assertThat(solution.get(1), is("once"));
		assertThat(solution.get(2), is("acme"));
		assertThat(solution.get(3), is("need"));
	}

	/**
	 * Test that we can solve the first required 5-letter word square.
	 */
	@Test
	public void shouldSolveFirst5LetterWordSquare() throws IOException, URISyntaxException {
		// given
		final int length = 5;
		final String letters = "aaaeeeefhhmoonssrrrrttttw";

		// when
		final List<WordSquare> solutions = WordSquareSolver.solveWordSquare(length, letters);

		// then
		assertThat(solutions, hasSize(1));

		final List<String> solution = solutions.get(0).getWords();
		assertThat(solution, hasSize(5));
		assertThat(solution.get(0), is("feast"));
		assertThat(solution.get(1), is("earth"));
		assertThat(solution.get(2), is("armor"));
		assertThat(solution.get(3), is("stone"));
		assertThat(solution.get(4), is("threw"));
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

		final List<String> solution = solutions.get(0).getWords();
		assertThat(solution, hasSize(5));
		assertThat(solution.get(0), is("heart"));
		assertThat(solution.get(1), is("ember"));
		assertThat(solution.get(2), is("above"));
		assertThat(solution.get(3), is("revue"));
		assertThat(solution.get(4), is("trees"));
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
		final List<WordSquare> solutions = WordSquareSolver.solveWordSquare(length, letters);

		// then
		assertThat(solutions, hasSize(1));

		final List<String> solution = solutions.get(0).getWords();
		assertThat(solution, hasSize(7));
		assertThat(solution.get(0), is("bravado"));
		assertThat(solution.get(1), is("renamed"));
		assertThat(solution.get(2), is("analogy"));
		assertThat(solution.get(3), is("valuers"));
		assertThat(solution.get(4), is("amoebas"));
		assertThat(solution.get(5), is("degrade"));
		assertThat(solution.get(6), is("odyssey"));
	}
}
