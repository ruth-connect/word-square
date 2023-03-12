package uk.me.ruthmills.wordsquare.solution;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.ListUtils;

import uk.me.ruthmills.wordsquare.exception.FirstWordSquareSolvedException;
import uk.me.ruthmills.wordsquare.exception.InvalidWordSquareException;
import uk.me.ruthmills.wordsquare.letters.AvailableLetters;
import uk.me.ruthmills.wordsquare.predicate.WordContainsAvailableLettersPredicate;
import uk.me.ruthmills.wordsquare.predicate.WordMeetsRequirementsPredicate;

/**
 * This class generates valid word squares for the input parameters.
 * 
 * @author ruth
 *
 */
public class WordSquareGenerator {

	/**
	 * Get valid word square combinations.
	 * 
	 * @param length         Number of letters in each word.
	 * @param letters        Available letters to create the words from.
	 * @param firstMatchOnly true to stop at the first matching word square, false
	 *                       to carry on until all possible words are exhausted.
	 * @throws InvalidWordSquareException Thrown if a word square we are trying to
	 *                                    add is invalid.
	 * @throws IOException                Thrown if we cannot read from the
	 *                                    dictionary file.
	 */
	public static List<WordSquare> getValidWordSquares(final int length, final String letters,
			final boolean firstMatchOnly) throws InvalidWordSquareException, IOException {
		try {
			// Initalise the solution state.
			final SolutionState solutionState = new SolutionState(length, letters, firstMatchOnly);

			// Iterate through each word in the shortlist.
			for (final String word : solutionState.getWordShortlist()) {
				// Get any valid word squares beginning with the current word from the
				// shortlist.
				getValidWordSquaresForStartingWord(solutionState, word);
			}

			// Return the word squares.
			return solutionState.getWordSquares();
		} catch (FirstWordSquareSolvedException ex) {
			// The firstMatchOnly flag is true, and we have found the first word square.
			return Collections.singletonList(ex.getWordSquare());
		}
	}

	/**
	 * Get valid word squares for a given starting word, available letters, and word
	 * shortlist.
	 * 
	 * @param solutionState The solution state.
	 * @param word          The current word.
	 * @throws FirstWordSquareSolvedException Thrown if the firstMatchOnly flag is
	 *                                        true (we have found the first match,
	 *                                        so there is no need to find any more).
	 * @throws InvalidWordSquareException     Thrown if a word square we are trying
	 *                                        to add is invalid.
	 */
	static void getValidWordSquaresForStartingWord(final SolutionState solutionState, final String word)
			throws FirstWordSquareSolvedException, InvalidWordSquareException {
		// Do we have the required number of words in the list of words to make a word
		// square?
		if (solutionState.getWords().size() == solutionState.getLength() - 1) {
			// Add the current word to the list of words.
			final List<String> updatedWords = ListUtils.union(solutionState.getWords(),
					Collections.singletonList(word));

			// Add a new word square to end of the list of word squares.
			solutionState.addWordSquare(new WordSquare(solutionState.getLength(), updatedWords));
		} else {
			// Get the remaining letters left, after removing those from the current word
			// from the available letters.
			final AvailableLetters remainingLetters = solutionState.getLetters().getRemainingLetters(word);

			if (remainingLetters.getCount() >= solutionState.getLength()) { // only if we have enough letters left to
																			// make a word.
				// Create a predicate based on the remaining letters.
				final WordContainsAvailableLettersPredicate wordContainsAvailableLettersPredicate = new WordContainsAvailableLettersPredicate(
						remainingLetters);

				// Get the remaining words available, by filtering only those left where the
				// word shortlist contains the remaining letters.
				final List<String> remainingWordShortlist = solutionState.getWordShortlist().stream()
						.filter(wordContainsAvailableLettersPredicate).collect(Collectors.toList());

				// Are there any remaining words?
				if (remainingWordShortlist.size() > 0) {
					// Add the current word to the list of words.
					final List<String> updatedWords = ListUtils.union(solutionState.getWords(),
							Collections.singletonList(word));

					// Create a predicate based on the words being valid at the next position in the
					// word square.
					WordMeetsRequirementsPredicate wordMeetsRequirementsPredicate = new WordMeetsRequirementsPredicate(
							updatedWords);

					for (final String remainingWord : remainingWordShortlist) {
						if (wordMeetsRequirementsPredicate.test(remainingWord)) {
							// Recursively call this function for the word that is valid.
							getValidWordSquaresForStartingWord(solutionState.getUpdatedSolutionState(remainingLetters,
									remainingWordShortlist, updatedWords), remainingWord);
						}
					}
				}
			}
		}
	}
}
