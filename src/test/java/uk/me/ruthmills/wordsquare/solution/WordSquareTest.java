package uk.me.ruthmills.wordsquare.solution;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * Test suite for the Word Square class.
 * 
 * @author ruth
 */
public class WordSquareTest {

	// Word length.
	private static final int WORD_LENGTH = 4;

	// List of expected words.
	private static final List<String> EXPECTED_WORDS = Arrays.asList("rose", "oven", "send", "ends");

	// Too few words.
	private static final List<String> TOO_FEW_WORDS = Arrays.asList("rose", "oven", "send");

	// Too few letters.
	private static final List<String> TOO_FEW_LETTERS = Arrays.asList("rose", "oven", "end", "ends");

	// Invalid word square, despite correct number of words and letters.
	private static final List<String> INVALID_WORD_SQUARE = Arrays.asList("rose", "oven", "ends", "send");

	/**
	 * Test that the word square contains the expected words.
	 */
	@Test
	public void shouldContainExpectedWords() {
		// given
		WordSquare wordSquare = new WordSquare(WORD_LENGTH, EXPECTED_WORDS);

		// when
		final List<String> words = wordSquare.getWords();

		// then
		assertThat(words, equalTo(EXPECTED_WORDS));
	}

	/**
	 * Test that the word square is valid when it contains valid words.
	 */
	@Test
	public void shouldBeValid_whenWordSquareContainsValidWords() {
		// given
		WordSquare wordSquare = new WordSquare(WORD_LENGTH, EXPECTED_WORDS);

		// when
		final boolean isValid = wordSquare.isValid();

		// then
		assertThat(isValid, is(true));
	}

	/**
	 * Test that the word square is invalid when it contains too few words.
	 */
	@Test
	public void shouldNotBeValid_whenWordSquareContainsTooFewWords() {
		// given
		WordSquare wordSquare = new WordSquare(WORD_LENGTH, TOO_FEW_WORDS);

		// when
		final boolean isValid = wordSquare.isValid();

		// then
		assertThat(isValid, is(false));
	}

	/**
	 * Test that the word square is invalid when one of the words contains too few
	 * letters.
	 */
	@Test
	public void shouldNotBeValid_whenWordSquareContainsWordWithTooFewLetters() {
		// given
		WordSquare wordSquare = new WordSquare(WORD_LENGTH, TOO_FEW_LETTERS);

		// when
		final boolean isValid = wordSquare.isValid();

		// then
		assertThat(isValid, is(false));
	}

	/**
	 * Test that the word square is invalid when the word square is invalid, despite
	 * having the correct number of words, each with the correct number of letters.
	 */
	@Test
	public void shouldNotBeValid_whenWordSquareIsInvalid() {
		// given
		WordSquare wordSquare = new WordSquare(WORD_LENGTH, INVALID_WORD_SQUARE);

		// when
		final boolean isValid = wordSquare.isValid();

		// then
		assertThat(isValid, is(false));
	}
}
