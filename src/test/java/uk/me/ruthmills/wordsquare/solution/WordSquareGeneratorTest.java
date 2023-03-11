package uk.me.ruthmills.wordsquare.solution;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Test;

/**
 * Test suite for the Word Square Generator class.
 * 
 * @author ruth
 */
public class WordSquareGeneratorTest {

	/**
	 * Test we can get all possible combinations (including invalid ones) for a
	 * 3-letter word square.
	 */
	@Test
	public void shouldGetAllPossibleCombinations() throws IOException, URISyntaxException {
		// given
		final int length = 3;
		final String letters = "ddggoooox";

		// when
		final List<WordSquare> combinations = WordSquareGenerator.getAllPossibleCombinations(length, letters);

		// then
		assertThat(combinations, hasSize(1));
	}
}
