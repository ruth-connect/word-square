package uk.me.ruthmills.wordsquare.solution;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Test;

/**
 * Test suite for the Word Shortlist class.
 * 
 * @author ruth
 */
public class WordShortlistTest {

	/**
	 * Should return the expected word shortlist for a given input.
	 */
	@Test
	public void shouldReturnExpectedWordShortlist() throws IOException, URISyntaxException {
		// given
		final int length = 3;
		final String availableLetters = "ddggoooox";

		// when
		final List<String> wordShortlist = WordShortlist.getWordShortlist(length, availableLetters);

		// then
		assertThat(wordShortlist, hasSize(6));
		assertThat(wordShortlist.get(0), is("dog"));
		assertThat(wordShortlist.get(1), is("god"));
		assertThat(wordShortlist.get(2), is("goo"));
		assertThat(wordShortlist.get(3), is("gox"));
		assertThat(wordShortlist.get(4), is("odd"));
		assertThat(wordShortlist.get(5), is("oxo"));
	}

	/**
	 * Should return the word shortlist for the 4-letter test case.
	 */
	@Test
	public void shouldReturnWordShortlist_for4LetterTestCase() throws IOException, URISyntaxException {
		// given
		final int length = 4;
		final String availableLetters = "aaccdeeeemmnnnoo";

		// when
		final List<String> wordShortlist = WordShortlist.getWordShortlist(length, availableLetters);

		// then
		assertThat(wordShortlist, hasSize(74));
	}

	/**
	 * Should return the word shortlist for the first 5-letter test case.
	 */
	@Test
	public void shouldReturnWordShortlist_forFirst5LetterTestCase() throws IOException, URISyntaxException {
		// given
		final int length = 5;
		final String availableLetters = "aaaeeeefhhmoonssrrrrttttw";

		// when
		final List<String> wordShortlist = WordShortlist.getWordShortlist(length, availableLetters);

		// then
		assertThat(wordShortlist, hasSize(653));
	}

	/**
	 * Should return the word shortlist for the second 5-letter test case.
	 */
	@Test
	public void shouldReturnWordShortlist_forSecond5LetterTestCase() throws IOException, URISyntaxException {
		// given
		final int length = 5;
		final String availableLetters = "aabbeeeeeeeehmosrrrruttvv";

		// when
		final List<String> wordShortlist = WordShortlist.getWordShortlist(length, availableLetters);

		// then
		assertThat(wordShortlist, hasSize(495));
	}

	/**
	 * Should return the word shortlist for the 7-letter test case.
	 */
	@Test
	public void shouldReturnWordShortlist_for7LetterTestCase() throws IOException, URISyntaxException {
		// given
		final int length = 7;
		final String availableLetters = "aaaaaaaaabbeeeeeeedddddggmmlloooonnssssrrrruvvyyy";

		// when
		final List<String> wordShortlist = WordShortlist.getWordShortlist(length, availableLetters);

		// then
		assertThat(wordShortlist, hasSize(2151));
	}
}
