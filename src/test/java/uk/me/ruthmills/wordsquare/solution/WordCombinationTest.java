package uk.me.ruthmills.wordsquare.solution;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Test suite for the Word Combination class.
 * 
 * @author ruth
 */
public class WordCombinationTest {

	// List of expected words.
	private static final List<String> EXPECTED_WORDS = Arrays.asList("rose", "oven", "send", "ends");

	// The Word Combination object under test.
	private WordCombination wordCombination;

	/**
	 * Set up the test dependencies.
	 */
	@Before
	public void setUp() {
		wordCombination = new WordCombination(EXPECTED_WORDS);
	}

	/**
	 * Test that the word combination contains the expected words.
	 */
	@Test
	public void shouldContainExpectedWords() {
		// given
		final List<String> words = wordCombination.getWords();

		// then
		assertThat(words, equalTo(EXPECTED_WORDS));
	}
}
