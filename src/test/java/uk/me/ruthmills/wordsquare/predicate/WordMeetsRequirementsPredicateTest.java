package uk.me.ruthmills.wordsquare.predicate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;

import org.junit.Test;

/**
 * Test suite for Word Meets Requirements Predicate class.
 * 
 * @author ruth
 */
public class WordMeetsRequirementsPredicateTest {

	/**
	 * Test that the predicate returns true when there is one word in the list, and
	 * the new word meets the requirements.
	 */
	@Test
	public void shouldReturnTrue_whenOneWordInList_andNewWordMeetsRequirements() {
		// given
		final WordMeetsRequirementsPredicate wordMeetsRequirementsPredicate = new WordMeetsRequirementsPredicate(
				Arrays.asList("dog"));
		final String word = "oxo";

		// when
		boolean result = wordMeetsRequirementsPredicate.test(word);

		// then
		assertThat(result, is(true));
	}

	/**
	 * Test that the predicate returns false when there is one word in the list, and
	 * the predicate does not meet the requirements.
	 */
	@Test
	public void shouldReturnFalse_whenOneWordInList_andNewWordDoesNotMeetRequirements() {
		// given
		final WordMeetsRequirementsPredicate wordMeetsRequirementsPredicate = new WordMeetsRequirementsPredicate(
				Arrays.asList("dog"));
		final String word = "god";

		// when
		boolean result = wordMeetsRequirementsPredicate.test(word);

		// then
		assertThat(result, is(false));
	}
}
