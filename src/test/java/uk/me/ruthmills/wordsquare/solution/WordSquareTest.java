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
	 * Test that the isValid method returns true when the word square is valid.
	 */
	@Test
	public void shouldReturnTrue_whenWordSquareContainsValidWords() {
		// given
		WordSquare wordSquare = new WordSquare(WORD_LENGTH, EXPECTED_WORDS);

		// when
		final boolean isValid = wordSquare.isValid();

		// then
		assertThat(isValid, is(true));
	}

	/**
	 * Test that the isValid method returns false when the word square is invalid.
	 */
	@Test
	public void shouldReturnFalse_whenWordSquareIsInvalid() {
		// given
		WordSquare wordSquare = new WordSquare(WORD_LENGTH, INVALID_WORD_SQUARE);

		// when
		final boolean isValid = wordSquare.isValid();

		// then
		assertThat(isValid, is(false));
	}

	/**
	 * Test that this word square will correctly convert to a string.
	 */
	@Test
	public void shouldConvertToString() {
		// given
		WordSquare wordSquare = new WordSquare(WORD_LENGTH, EXPECTED_WORDS);

		// when
		String wordSquareString = wordSquare.toString();

		// then
		assertThat(wordSquareString, is("rose\noven\nsend\nends\n"));
	}
}
