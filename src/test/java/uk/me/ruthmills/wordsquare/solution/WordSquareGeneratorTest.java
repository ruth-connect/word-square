package uk.me.ruthmills.wordsquare.solution;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * Test suite for the Word Square Generator class.
 * 
 * @author ruth
 */
public class WordSquareGeneratorTest {

	/**
	 * Test that word CAN be formed from available letters.
	 */
	@Test
	public void shouldReturnTrue_whenWordCanBeFormedFromAvailableLetters() {
		// given
		final String word = "dog";
		final String letters = "dgox";

		// when
		final boolean result = WordSquareGenerator.isWordAbleToBeFormedFromAvailableLetters(word, letters);

		// then
		assertThat(result, is(true));
	}

	/**
	 * Test that word CANNOT be formed from available letters.
	 */
	@Test
	public void shouldReturnFalse_whenWordCannotBeFormedFromAvailableLetters() {
		// given
		final String word = "dog";
		final String letters = "dgx";

		// when
		final boolean result = WordSquareGenerator.isWordAbleToBeFormedFromAvailableLetters(word, letters);

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
				wordShortlist, new ArrayList<String>(), new ArrayList<WordSquare>()).collect(Collectors.toList());

		// then
		assertThat(combinations, hasSize(4));
		assertThat(combinations.get(0).getWords().toString(), is("[dog, dog, oxo]"));
		assertThat(combinations.get(1).getWords().toString(), is("[dog, god, oxo]"));
		assertThat(combinations.get(2).getWords().toString(), is("[dog, oxo, dog]"));
		assertThat(combinations.get(3).getWords().toString(), is("[dog, oxo, god]"));
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
		final List<WordSquare> combinations = WordSquareGenerator.getAllPossibleCombinations(length, letters)
				.collect(Collectors.toList());

		// then
		assertThat(combinations, hasSize(18));
		assertThat(combinations.get(0).getWords().toString(), is("[dog, dog, oxo]"));
		assertThat(combinations.get(1).getWords().toString(), is("[dog, god, oxo]"));
		assertThat(combinations.get(2).getWords().toString(), is("[dog, oxo, dog]"));
		assertThat(combinations.get(3).getWords().toString(), is("[dog, oxo, god]"));
		assertThat(combinations.get(4).getWords().toString(), is("[god, dog, oxo]"));
		assertThat(combinations.get(5).getWords().toString(), is("[god, god, oxo]"));
		assertThat(combinations.get(6).getWords().toString(), is("[god, oxo, dog]"));
		assertThat(combinations.get(7).getWords().toString(), is("[god, oxo, god]"));
		assertThat(combinations.get(8).getWords().toString(), is("[goo, gox, odd]"));
		assertThat(combinations.get(9).getWords().toString(), is("[goo, odd, gox]"));
		assertThat(combinations.get(10).getWords().toString(), is("[gox, goo, odd]"));
		assertThat(combinations.get(11).getWords().toString(), is("[gox, odd, goo]"));
		assertThat(combinations.get(12).getWords().toString(), is("[odd, goo, gox]"));
		assertThat(combinations.get(13).getWords().toString(), is("[odd, gox, goo]"));
		assertThat(combinations.get(14).getWords().toString(), is("[oxo, dog, dog]"));
		assertThat(combinations.get(15).getWords().toString(), is("[oxo, dog, god]"));
		assertThat(combinations.get(16).getWords().toString(), is("[oxo, god, dog]"));
		assertThat(combinations.get(17).getWords().toString(), is("[oxo, god, god]"));
	}
}
