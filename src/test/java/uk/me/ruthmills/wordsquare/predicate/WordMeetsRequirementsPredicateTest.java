package uk.me.ruthmills.wordsquare.predicate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

/**
 * Test suite for Word Meets Requirements Predicate class.
 * 
 * @author ruth
 */
public class WordMeetsRequirementsPredicateTest {

	/**
	 * Test that the predicate returns true when there are no words in the list.
	 */
	@Test
	public void shouldReturnTrue_whenNoWordsInList() {
		// given
		final WordMeetsRequirementsPredicate wordMeetsRequirementsPredicate = new WordMeetsRequirementsPredicate(
				Collections.emptyList());
		final String word = "rose";

		// when
		final boolean result = wordMeetsRequirementsPredicate.test(word);

		// then
		assertThat(result, is(true));
	}

	/**
	 * Test that the predicate returns true when there is one word in the list, and
	 * the new word meets the requirements.
	 */
	@Test
	public void shouldReturnTrue_whenOneWordInList_andNewWordMeetsRequirements() {
		// given
		final WordMeetsRequirementsPredicate wordMeetsRequirementsPredicate = new WordMeetsRequirementsPredicate(
				Arrays.asList("rose"));
		final String word = "oven";

		// when
		final boolean result = wordMeetsRequirementsPredicate.test(word);

		// then
		assertThat(result, is(true));
	}

	/**
	 * Test that the predicate returns false when there is one word in the list, and
	 * the new word does not meet the requirements.
	 */
	@Test
	public void shouldReturnFalse_whenOneWordInList_andNewWordDoesNotMeetRequirements() {
		// given
		final WordMeetsRequirementsPredicate wordMeetsRequirementsPredicate = new WordMeetsRequirementsPredicate(
				Arrays.asList("rose"));
		final String word = "ends";

		// when
		final boolean result = wordMeetsRequirementsPredicate.test(word);

		// then
		assertThat(result, is(false));
	}

	/**
	 * Test that the predicate returns true when there are two words in the list,
	 * and the new word meets the requirements.
	 */
	@Test
	public void shouldReturnTrue_whenTwoWordsInList_andNewWordMeetsRequirements() {
		// given
		final WordMeetsRequirementsPredicate wordMeetsRequirementsPredicate = new WordMeetsRequirementsPredicate(
				Arrays.asList("rose", "oven"));
		final String word = "send";

		// when
		final boolean result = wordMeetsRequirementsPredicate.test(word);

		// then
		assertThat(result, is(true));
	}

	/**
	 * Test that the predicate returns false when there are two words in the list,
	 * and the new word does not meet the requirements.
	 */
	@Test
	public void shouldReturnFalse_whenTwoWordsInList_andNewWordDoesNotMeetRequirements() {
		// given
		final WordMeetsRequirementsPredicate wordMeetsRequirementsPredicate = new WordMeetsRequirementsPredicate(
				Arrays.asList("rose", "oven"));
		final String word = "sand";

		// when
		final boolean result = wordMeetsRequirementsPredicate.test(word);

		// then
		assertThat(result, is(false));
	}
}
