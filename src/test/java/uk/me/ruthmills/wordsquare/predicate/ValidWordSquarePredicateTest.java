package uk.me.ruthmills.wordsquare.predicate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import uk.me.ruthmills.wordsquare.solution.WordSquare;

/**
 * Test suite for the Valid Word Square Predicate class.
 * 
 * @author ruth
 */
public class ValidWordSquarePredicateTest {
	// Word length.
	private static final int WORD_LENGTH = 4;

	// Object to test.
	private ValidWordSquarePredicate validWordSquarePredicate;

	/**
	 * Set up the test dependencies.
	 */
	@Before
	public void setUp() {
		validWordSquarePredicate = new ValidWordSquarePredicate(WORD_LENGTH);
	}

	/**
	 * Test that we can get the expected column (i.e. a vertical word) from the word
	 * square.
	 */
	@Test
	public void shouldReturnColumn_whenColumnIndexIsSupplied() {
		// given
		final List<String> words = Arrays.asList("abcde", "fghij", "klmno", "pqrst", "uvwxy"); // sequences of letters
		final int columnIndex = 1; // second vertical word.

		// when
		final String column = validWordSquarePredicate.getColumn(words, columnIndex);

		// then
		assertThat(column, is("bglqv")); // second vertical word.
	}

	/**
	 * Test that we can translate all the columns to be rows (so all the vertical
	 * words become horizontal words instead).
	 */
	@Test
	public void shouldTranslateColumnsToRows() {
		// given
		final List<String> words = Arrays.asList("abcde", "fghij", "klmno", "pqrst", "uvwxy"); // sequences of letters

		// when
		final List<String> translatedWords = validWordSquarePredicate.translateColumnsToRows(words);

		// then
		assertThat(translatedWords, hasSize(5));
		assertThat(translatedWords.toString(), is("[afkpu, bglqv, chmrw, dinsx, ejoty]"));
	}

	/**
	 * Test that the predicate returns false if the word square has too few words.
	 */
	@Test
	public void shouldReturnFalse_whenWordSquareHasTooFewWords() {
		// given
		final WordSquare wordSquare = new WordSquare(WORD_LENGTH, "rose", "oven", "send");

		// when
		final boolean isValidWordSquare = validWordSquarePredicate.test(wordSquare);

		// then
		assertThat(isValidWordSquare, is(false));
	}

	/**
	 * Test that the predicate returns false if the word square has too many words.
	 */
	@Test
	public void shouldReturnFalse_whenWordSquareHasTooManyWords() {
		// given
		final WordSquare wordSquare = new WordSquare(WORD_LENGTH, "rose", "oven", "send", "ends", "ends");

		// when
		final boolean isValidWordSquare = validWordSquarePredicate.test(wordSquare);

		// then
		assertThat(isValidWordSquare, is(false));
	}

	/**
	 * Test that the predicate returns false if one of the words is too short.
	 */
	@Test
	public void shouldReturnFalse_whenWordIsTooShort() {
		// given
		final WordSquare wordSquare = new WordSquare(WORD_LENGTH, "rose", "oven", "end", "ends");

		// when
		final boolean isValidWordSquare = validWordSquarePredicate.test(wordSquare);

		// then
		assertThat(isValidWordSquare, is(false));
	}

	/**
	 * Test that the predicate returns false if one of the words is too long.
	 */
	@Test
	public void shouldReturnFalse_whenWordIsTooLong() {
		// given
		final WordSquare wordSquare = new WordSquare(WORD_LENGTH, "rose", "coven", "send", "ends");

		// when
		final boolean isValidWordSquare = validWordSquarePredicate.test(wordSquare);

		// then
		assertThat(isValidWordSquare, is(false));
	}

	/**
	 * Test that the predicate returns true if the word square is valid.
	 */
	@Test
	public void shouldReturnTrue_whenWordSquareIsValid() {
		// given
		WordSquare wordSquare = new WordSquare(WORD_LENGTH, "rose", "oven", "send", "ends");

		// when
		boolean isValidWordSquare = validWordSquarePredicate.test(wordSquare);

		// then
		assertThat(isValidWordSquare, is(true));
	}

	/**
	 * Test that the predicate returns false if two of the words have been swapped
	 * so the word square is invalid.
	 */
	@Test
	public void shouldReturnFalse_whenWordsSwappedSoWordSquareIsInvalid() {
		// given
		WordSquare wordSquare = new WordSquare(WORD_LENGTH, "rose", "oven", "ends", "send");

		// when
		boolean isValidWordSquare = validWordSquarePredicate.test(wordSquare);

		// then
		assertThat(isValidWordSquare, is(false));
	}

	/**
	 * Test that the predicate returns false if the word square has an invalid
	 * letter.
	 */
	@Test
	public void shouldReturnFalse_whenWordSquareHasInvalidLetter() {
		// given
		WordSquare wordSquare = new WordSquare(WORD_LENGTH, "rose", "oven", "sand", "ends");

		// when
		boolean isValidWordSquare = validWordSquarePredicate.test(wordSquare);

		// then
		assertThat(isValidWordSquare, is(false));
	}
}
