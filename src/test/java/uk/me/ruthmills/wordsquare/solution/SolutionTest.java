package uk.me.ruthmills.wordsquare.solution;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Test suite for the Solution class.
 * 
 * @author ruth
 */
public class SolutionTest {

	// The number of letters in each word.
	private static final int NUM_LETTERS_IN_WORD = 4;

	// The available letters.
	private static final String AVAILABLE_LETTERS = "eeeeddoonnnsssrv";

	// The expected word shortlist size.
	private static final int EXPECTED_WORD_SHORTLIST_SIZE = 41;

	// The Solution object under test.
	private Solution solution;

	/**
	 * Set up the test dependencies.
	 */
	@Before
	public void setUp() {
		solution = new Solution(NUM_LETTERS_IN_WORD, AVAILABLE_LETTERS);
	}

	/**
	 * Should get the expected word shortlist for the number of letters in each
	 * word, and the set of available letters.
	 */
	@Test
	public void shouldGetExpectedWordShortlist() throws IOException, URISyntaxException {
		// when
		List<String> wordShortlist = solution.getWordShortlist();

		// then
		assertThat(wordShortlist, hasSize(EXPECTED_WORD_SHORTLIST_SIZE));
	}
}
