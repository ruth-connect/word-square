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
}
