package uk.me.ruthmills.wordsquare.solution;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.io.IOException;
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
	 * Test getting all valid solutions for a given starting word in a 3-letter word
	 * square (there is only one in this case).
	 */
	@Test
	public void shouldGetAllValidSolutionsFor3LetterStartingWord() throws IOException {
		// given
		final int length = 3;
		final String letters = "ddggoooox";
		final String startingWord = "dog";
		final List<String> wordShortlist = WordShortlist.getWordShortlist(length, letters);
		final List<WordSquare> solutions = new ArrayList<>();
		final boolean firstMatchOnly = false;

		// when
		WordSquareGenerator.getValidWordSquaresForStartingWord(startingWord, length, letters, wordShortlist,
				new ArrayList<String>(), solutions, firstMatchOnly);

		// then
		assertThat(solutions, hasSize(1));
		assertThat(solutions.get(0).getWords().toString(), is("[dog, oxo, god]"));
	}

	/**
	 * Test getting all valid solutions for a given starting word in a 5-letter word
	 * square (there are two in this case).
	 */
	@Test
	public void shouldGetAllValidSolutionsFor5LetterStartingWord() throws IOException {
		// given
		final int length = 5;
		final String letters = "aaaeeeefhhmoonssrrrrttttw";
		final String startingWord = "feast";
		final List<String> wordShortlist = WordShortlist.getWordShortlist(length, letters);
		final List<WordSquare> solutions = new ArrayList<>();
		final boolean firstMatchOnly = false;

		// when
		WordSquareGenerator.getValidWordSquaresForStartingWord(startingWord, length, letters, wordShortlist,
				new ArrayList<String>(), solutions, firstMatchOnly);

		// then
		assertThat(solutions, hasSize(2));
		assertThat(solutions.get(0).getWords().toString(), is("[feast, earth, armer, steno, throw]"));
		assertThat(solutions.get(1).getWords().toString(), is("[feast, earth, armor, stone, threw]"));
	}

	/**
	 * Test getting the first valid solutions for a given starting word in a
	 * 5-letter word square.
	 */
	@Test
	public void shouldGetFirstValidSolutionFor5LetterStartingWord() throws IOException {
		// given
		final int length = 5;
		final String letters = "aaaeeeefhhmoonssrrrrttttw";
		final String startingWord = "feast";
		final List<String> wordShortlist = WordShortlist.getWordShortlist(length, letters);
		final List<WordSquare> solutions = new ArrayList<>();
		final boolean firstMatchOnly = true;

		// when
		WordSquareGenerator.getValidWordSquaresForStartingWord(startingWord, length, letters, wordShortlist,
				new ArrayList<String>(), solutions, firstMatchOnly);

		// then
		assertThat(solutions, hasSize(1));
		assertThat(solutions.get(0).getWords().toString(), is("[feast, earth, armer, steno, throw]"));
	}

	/**
	 * Test we can get all valid solutions for a 3-letter word square (there are two
	 * in this case).
	 */
	@Test
	public void shouldGetAllValidSolutionsFor3LetterWordSquare() throws IOException {
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
	public void shouldGetFirstMatchingSolutionFor3LetterWordSquare() throws IOException {
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
