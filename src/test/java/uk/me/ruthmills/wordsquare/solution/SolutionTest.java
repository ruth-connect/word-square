package uk.me.ruthmills.wordsquare.solution;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

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
		assertThat(wordShortlist.get(0), is("dens"));
		assertThat(wordShortlist.get(1), is("devs"));
		assertThat(wordShortlist.get(2), is("doer"));
		assertThat(wordShortlist.get(3), is("does"));
		assertThat(wordShortlist.get(4), is("done"));
		assertThat(wordShortlist.get(5), is("dons"));
		assertThat(wordShortlist.get(6), is("dore"));
		assertThat(wordShortlist.get(7), is("dors"));
		assertThat(wordShortlist.get(8), is("dose"));
		assertThat(wordShortlist.get(9), is("dove"));
		assertThat(wordShortlist.get(10), is("ends"));
		assertThat(wordShortlist.get(11), is("eons"));
		assertThat(wordShortlist.get(12), is("erns"));
		assertThat(wordShortlist.get(13), is("eros"));
		assertThat(wordShortlist.get(14), is("nerd"));
		assertThat(wordShortlist.get(15), is("node"));
		assertThat(wordShortlist.get(16), is("nods"));
		assertThat(wordShortlist.get(17), is("noes"));
		assertThat(wordShortlist.get(18), is("nose"));
		assertThat(wordShortlist.get(19), is("odes"));
		assertThat(wordShortlist.get(20), is("ones"));
		assertThat(wordShortlist.get(21), is("ores"));
		assertThat(wordShortlist.get(22), is("oven"));
		assertThat(wordShortlist.get(23), is("over"));
		assertThat(wordShortlist.get(24), is("redo"));
		assertThat(wordShortlist.get(25), is("reds"));
		assertThat(wordShortlist.get(26), is("rend"));
		assertThat(wordShortlist.get(27), is("revs"));
		assertThat(wordShortlist.get(28), is("rode"));
		assertThat(wordShortlist.get(29), is("rods"));
		assertThat(wordShortlist.get(30), is("roes"));
		assertThat(wordShortlist.get(31), is("rose"));
		assertThat(wordShortlist.get(32), is("rove"));
		assertThat(wordShortlist.get(33), is("send"));
		assertThat(wordShortlist.get(34), is("sned"));
		assertThat(wordShortlist.get(35), is("sone"));
		assertThat(wordShortlist.get(36), is("sord"));
		assertThat(wordShortlist.get(37), is("sore"));
		assertThat(wordShortlist.get(38), is("sorn"));
		assertThat(wordShortlist.get(39), is("vend"));
		assertThat(wordShortlist.get(40), is("voes"));
	}
}
