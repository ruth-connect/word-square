package uk.me.ruthmills.wordsquare.solution;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import uk.me.ruthmills.wordsquare.letters.AvailableLetters;
import uk.me.ruthmills.wordsquare.letters.AvailableLettersFactory;

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
	public void shouldGetAllValidSolutionsFor3LetterStartingWord() throws IOException {
		// given
		final int length = 3;
		final AvailableLetters letters = AvailableLettersFactory.getInstance("ddggoooox");
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
		final AvailableLetters letters = AvailableLettersFactory.getInstance("aaaeeeefhhmoonssrrrrttttw");
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
		final AvailableLetters letters = AvailableLettersFactory.getInstance("aaaeeeefhhmoonssrrrrttttw");
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
