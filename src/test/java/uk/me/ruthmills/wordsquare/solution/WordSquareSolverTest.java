package uk.me.ruthmills.wordsquare.solution;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Test;

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
		final List<WordCombination> solutions = WordSquareSolver.solveWordSquare(length, letters);

		// then
		assertThat(solutions, hasSize(2));

		List<String> dogOxoGod = solutions.get(0).getWords();
		assertThat(dogOxoGod.get(0), is("dog"));
		assertThat(dogOxoGod.get(1), is("oxo"));
		assertThat(dogOxoGod.get(2), is("god"));

		List<String> godOxoDog = solutions.get(1).getWords();
		assertThat(godOxoDog.get(0), is("god"));
		assertThat(godOxoDog.get(1), is("oxo"));
		assertThat(godOxoDog.get(2), is("dog"));
	}
}
