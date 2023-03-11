package uk.me.ruthmills.wordsquare.solution;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Test suite for the Word Square class.
 * 
 * @author ruth
 */
public class WordSquareTest {

	// List of expected words.
	private static final List<String> EXPECTED_WORDS = Arrays.asList("rose", "oven", "send", "ends");

	// The Word Square object under test.
	private WordSquare wordSquare;

	/**
	 * Set up the test dependencies.
	 */
	@Before
	public void setUp() {
		wordSquare = new WordSquare(EXPECTED_WORDS);
	}

	/**
	 * Test that the word square contains the expected words.
	 */
	@Test
	public void shouldContainExpectedWords() {
		// given
		final List<String> words = wordSquare.getWords();

		// then
		assertThat(words, equalTo(EXPECTED_WORDS));
	}
}
