package uk.me.ruthmills.wordsquare.dictionary;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Test suite for the Dictionary class.
 * 
 * @author ruth
 */
public class DictionaryTest {

	// The Dictionary object under test.
	private Dictionary dictionary;

	/**
	 * Set up the test dependencies.
	 */
	@Before
	public void setUp() {
		dictionary = new Dictionary();
	}

	/**
	 * Test that we can read all the expected words from the dictionary file. We
	 * won't need ALL the words for our word square, but this is a good place to
	 * start, to verify that we can actually read from the dictionary file.
	 */
	@Test
	public void shouldReadAllWordsFromDictionaryTextFile() throws IOException, URISyntaxException {
		// when
		List<String> words = dictionary.getAllWords();

		// then
		assertThat(words, hasSize(172820)); // expected number of all the words!
	}
}
