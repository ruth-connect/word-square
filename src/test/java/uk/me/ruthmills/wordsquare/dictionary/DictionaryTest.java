package uk.me.ruthmills.wordsquare.dictionary;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.text.CharSequenceLength.hasLength;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import uk.me.ruthmills.wordsquare.letters.AvailableLettersFactory;
import uk.me.ruthmills.wordsquare.predicate.ListPredicate;
import uk.me.ruthmills.wordsquare.predicate.WordContainsAvailableLettersPredicate;
import uk.me.ruthmills.wordsquare.predicate.WordLengthPredicate;

/**
 * Test suite for the Dictionary class.
 * 
 * @author ruth
 */
public class DictionaryTest {

	// All the words in the dictionary.
	private static final int EXPECTED_NUMBER_OF_ALL_WORDS = 172820;

	// All the 4-letter words in the dictionary.
	private static final int EXPECTED_NUMBER_OF_4_LETTER_WORDS = 3903;

	// Available letters we are testing with: "eeeeddoonnnsssrv"
	private static final String AVAILABLE_LETTERS = "eeeeddoonnnsssrv";

	// All the words in the dictionary made up of a subset of the letters
	// "eeeeddoonnnsssrv".
	private static final int EXPECTED_NUMBER_OF_WORDS_CONTAINING_ONLY_SUBSET_OF_AVAILABLE_LETTERS = 402;

	// All the 4-letter words in the dictionary made up of a subset of the letters
	// "eeeeddoonnnsssrv".
	private static final int EXPECTED_NUMBER_OF_4_LETTER_WORDS_CONTAINING_ONLY_SUBSET_OF_AVAILABLE_LETTERS = 84;

	/**
	 * Test that we can read all the expected words from the dictionary file. We
	 * won't need ALL the words for our word square, but this is a good place to
	 * start, to verify that we can actually read from the dictionary file.
	 */
	@Test
	public void shouldReadAllWordsFromDictionaryTextFile() throws IOException {
		// when
		final List<String> words = Dictionary
				.getWordsMatchingPredicate(new ListPredicate<String>(Collections.emptyList()));

		// then
		assertThat(words, hasSize(EXPECTED_NUMBER_OF_ALL_WORDS)); // expected number of all the words!
	}

	/**
	 * Test that we can read only the words that are 4 letters long. We filter out
	 * all other words that are not of the required length.
	 */
	@Test
	public void shouldReadOnly4LetterWordsFromDictionaryTextFile() throws IOException {
		// given
		final WordLengthPredicate wordLengthPredicate = new WordLengthPredicate(4);

		// when
		final List<String> words = Dictionary.getWordsMatchingPredicate(wordLengthPredicate);

		// then
		assertThat(words.size(), lessThan(EXPECTED_NUMBER_OF_ALL_WORDS)); // will be less than all the words!
		assertThat(words, everyItem(hasLength(4))); // every word is 4 letters long.
		assertThat(words, hasSize(EXPECTED_NUMBER_OF_4_LETTER_WORDS)); // check the total number of 4 letter words.
	}

	/**
	 * Test that we can read only the words which contain ONLY a subset of the
	 * available letters. We filter out all other words.
	 */
	@Test
	public void shouldReadOnlyWordsWhichContainOnlyASubsetOfAvailableLetters() throws IOException {
		// given
		final WordContainsAvailableLettersPredicate wordContainsAvailableLettersPredicate = new WordContainsAvailableLettersPredicate(
				AvailableLettersFactory.getInstance(AVAILABLE_LETTERS));

		// when
		final List<String> words = Dictionary.getWordsMatchingPredicate(wordContainsAvailableLettersPredicate);

		// then
		assertThat(words.size(), lessThan(EXPECTED_NUMBER_OF_ALL_WORDS)); // will be less than all the words!
		// TODO - would be nice to verify that the words only contain the expected
		// letters here.
		assertThat(words, hasSize(EXPECTED_NUMBER_OF_WORDS_CONTAINING_ONLY_SUBSET_OF_AVAILABLE_LETTERS));
	}

	/**
	 * Test that we can read only the words which are 4 letters long AND which
	 * contain ONLY a subset of the available letters. We filter out all other
	 * words.
	 */
	@Test
	public void shouldReadOnly4LetterWordsWhichContainOnlyASubsetOfAvailableLetters() throws IOException {
		// given
		final ListPredicate<String> listPredicate = new ListPredicate<String>(Arrays.asList(new WordLengthPredicate(4),
				new WordContainsAvailableLettersPredicate(AvailableLettersFactory.getInstance(AVAILABLE_LETTERS))));

		// when
		final List<String> words = Dictionary.getWordsMatchingPredicate(listPredicate);

		// then
		assertThat(words.size(), lessThan(EXPECTED_NUMBER_OF_ALL_WORDS)); // will be less than all the words!
		assertThat(words, everyItem(hasLength(4))); // every word is 4 letters long.
		assertThat(words.size(), lessThan(EXPECTED_NUMBER_OF_4_LETTER_WORDS)); // less than the total number of 4 letter
																				// words.
		// TODO - would be nice to verify that the words only contain the expected
		// letters here.
		assertThat(words.size(), lessThan(EXPECTED_NUMBER_OF_WORDS_CONTAINING_ONLY_SUBSET_OF_AVAILABLE_LETTERS));
		assertThat(words, hasSize(EXPECTED_NUMBER_OF_4_LETTER_WORDS_CONTAINING_ONLY_SUBSET_OF_AVAILABLE_LETTERS));
	}
}
