package uk.me.ruthmills.wordsquare.dictionary;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.text.CharSequenceLength.hasLength;

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

	private static final int EXPECTED_NUMBER_OF_ALL_WORDS = 172820;
	private static final int EXPECTED_NUMBER_OF_4_LETTER_WORDS = 3903;

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
		assertThat(words, hasSize(EXPECTED_NUMBER_OF_ALL_WORDS)); // expected number of all the words!
	}

	/**
	 * Test that we can read only the words that are 4 letters long. We filter out
	 * all other words that are not of the required length.
	 */
	@Test
	public void shouldReadOnly4LetterWordsFromDictionaryTextFile() throws IOException, URISyntaxException {
		// given
		int requiredWordLength = 4;

		// when
		List<String> words = dictionary.getWordsOfMatchingLength(requiredWordLength);

		// then
		assertThat(words.size(), lessThan(EXPECTED_NUMBER_OF_ALL_WORDS)); // will be less than all the words!
		assertThat(words, everyItem(hasLength(4))); // every word is 4 letters long.
		assertThat(words, hasSize(EXPECTED_NUMBER_OF_4_LETTER_WORDS)); // check the total number of 4 letter words.
	}
}
