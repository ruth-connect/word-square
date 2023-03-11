package uk.me.ruthmills.wordsquare.solution;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * Test suite for the Word Square Generator class.
 * 
 * @author ruth
 */
public class WordSquareGeneratorTest {

	/**
	 * Test that word CAN be formed from available letters when there are no
	 * existing words.
	 */
	@Test
	public void shouldReturnTrue_whenWordCanBeFormedFromAvailableLetters_andThereAreNoExistingWords() {
		// given
		final String word = "dog";
		final String letters = "dgox";

		// when
		final boolean result = WordSquareGenerator.isWordAbleToBeFormed(word, letters, new ArrayList<String>());

		// then
		assertThat(result, is(true));
	}

	/**
	 * Test that word CANNOT be formed from available letters.
	 */
	@Test
	public void shouldReturnFalse_whenWordCannotBeFormedFromAvailableLetters_andThereAreNoExistingWords() {
		// given
		final String word = "dog";
		final String letters = "dgx";

		// when
		final boolean result = WordSquareGenerator.isWordAbleToBeFormed(word, letters, new ArrayList<String>());

		// then
		assertThat(result, is(false));
	}

	/**
	 * Test that word CAN be formed when there are available letters AND the word is
	 * valid.
	 */
	@Test
	public void shouldReturnTrue_whenWordCanBeFormedFromAvailableLetters_andWordIsValid() {
		// given
		final String word = "oxo";
		final String letters = "dgooox";

		// when
		final boolean result = WordSquareGenerator.isWordAbleToBeFormed(word, letters, Arrays.asList("dog"));

		// then
		assertThat(result, is(true));
	}

	/**
	 * Test that word CANNOT be formed when there are available letters AND the word
	 * is NOT valid.
	 */
	@Test
	public void shouldReturnTrue_whenWordCanBeFormedFromAvailableLetters_andWordIsNotValid() {
		// given
		final String word = "god";
		final String letters = "dgooox";

		// when
		final boolean result = WordSquareGenerator.isWordAbleToBeFormed(word, letters, Arrays.asList("dog"));

		// then
		assertThat(result, is(false));
	}

	/**
	 * Test getting the remaining letters after removing those in the current word
	 * from the available letters.
	 */
	@Test
	public void shouldGetRemainingLetters_whenRemovingLettersFromCurrentWord() {
		// given
		final String word = "dog";
		final String letters = "ddggoooox";

		// when
		final String remainingLetters = WordSquareGenerator.getRemainingLetters(word, letters);

		// then
		assertThat(remainingLetters, is("dgooox"));
	}

	/**
	 * Test getting all valid combinations for a given starting word in a 3-letter
	 * word square.
	 */
	@Test
	public void shouldGetAllWordSquareCombinationsForStartingWord() throws IOException, URISyntaxException {
		// given
		final int length = 3;
		final String letters = "ddggoooox";
		final String startingWord = "dog";
		final List<String> wordShortlist = WordShortlist.getWordShortlist(length, letters);
		final List<WordSquare> combinations = new ArrayList<>();

		// when
		WordSquareGenerator.getAllWordSquares(startingWord, length, letters, wordShortlist, new ArrayList<String>(),
				combinations);

		// then
		assertThat(combinations, hasSize(1));
		assertThat(combinations.get(0).getWords().toString(), is("[dog, oxo, god]"));
	}

	/**
	 * Test we can get all valid combinations for a 3-letter word square.
	 */
	@Test
	public void shouldGetAllValidCombinations() throws IOException, URISyntaxException {
		// given
		final int length = 3;
		final String letters = "ddggoooox";

		// when
		final List<WordSquare> combinations = WordSquareGenerator.getAllPossibleCombinations(length, letters);

		// then
		assertThat(combinations, hasSize(2));
		assertThat(combinations.get(0).getWords().toString(), is("[dog, oxo, god]"));
		assertThat(combinations.get(1).getWords().toString(), is("[god, oxo, dog]"));
	}
}
