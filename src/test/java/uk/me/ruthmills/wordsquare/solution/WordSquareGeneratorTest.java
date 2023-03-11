package uk.me.ruthmills.wordsquare.solution;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Test suite for the Word Square Generator class.
 * 
 * @author ruth
 */
public class WordSquareGeneratorTest {

	/**
	 * Test getting the remaining letters after removing those in the current word
	 * from the available letters.
	 */
	@Test
	public void shouldGetRemainingLetters_whenRemovingLettersFromCurrentWord() {
		// given
		String word = "dog";
		String letters = "ddggoooox";

		// when
		String remainingLetters = WordSquareGenerator.getRemainingLetters(word, letters);

		// then
		assertThat(remainingLetters, is("dgooox"));
	}

	/**
	 * Test getting all combinations (including invalid ones) for a given starting
	 * word in a 3-letter word square.
	 */
	@Test
	public void shouldGetAllWordSquareCombinationsForStartingWord() throws IOException, URISyntaxException {
		// given
		final int length = 3;
		final String letters = "ddggoooox";
		final String startingWord = "dog";
		final List<String> wordShortlist = WordShortlist.getWordShortlist(length, letters);

		// when
		final List<WordSquare> combinations = WordSquareGenerator.getAllWordSquares(startingWord, length, letters,
				wordShortlist, new ArrayList<String>());

		// then
		assertThat(combinations, hasSize(1));
	}

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
