package uk.me.ruthmills.wordsquare.solution;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.ListUtils;

import uk.me.ruthmills.wordsquare.exception.FirstWordSquareSolvedException;
import uk.me.ruthmills.wordsquare.exception.InvalidWordSquareException;
import uk.me.ruthmills.wordsquare.letters.AvailableLetters;
import uk.me.ruthmills.wordsquare.letters.AvailableLettersFactory;
import uk.me.ruthmills.wordsquare.predicate.WordContainsAvailableLettersPredicate;
import uk.me.ruthmills.wordsquare.predicate.WordMeetsRequirementsPredicate;

/**
 * This class represents the state of play during the solving of a word square.
 * We have all the parameters we need in one place, rather than needing to pass
 * a long argument list.
 * 
 * @author ruth
 *
 */
public class SolutionState {

	// The length of each word in the word square.
	private final int length;

	// The remaining letters we have available to make words from.
	private final AvailableLetters letters;

	// Flag indicating if we are to return the first valid word square only, rather
	// than checking if there are any others.
	private final boolean firstMatchOnly;

	// The shortlist of valid words to iterate through.
	private final List<String> wordShortlist;

	// The current list of words we have in the word square so far.
	private final List<String> words;

	// The word squares we have solved so far.
	private final List<WordSquare> wordSquares;

	/**
	 * Constructor. By default we assume that we will return ALL word squares, not
	 * stopping at the first one.
	 * 
	 * @param length  The length of each word in the word square.
	 * @param letters The available letters from which to form words.
	 * @throws IOException Thrown if there is a problem reading from the dictionary.
	 */
	public SolutionState(final int length, final String letters) throws IOException {
		this(length, letters, false);
	}

	/**
	 * Constructor.
	 * 
	 * @param length         The length of each word in the word square.
	 * @param letters        The available letters from which to form words.
	 * @param firstMatchOnly true to stop at the first valid word square, false to
	 *                       continue until we run out of words.
	 * @throws IOException Thrown if there is a problem reading from the dictionary.
	 */
	public SolutionState(final int length, final String letters, final boolean firstMatchOnly) throws IOException {
		this.length = length;
		this.letters = AvailableLettersFactory.getInstance(letters);
		this.firstMatchOnly = firstMatchOnly;
		this.wordShortlist = WordShortlist.getWordShortlist(length, this.letters);
		this.words = new ArrayList<>();
		this.wordSquares = new ArrayList<>();
	}

	/**
	 * Private constructor - for internal use only.
	 * 
	 * @param solutionState Existing solution state.
	 * @param letters       Available letters.
	 * @param wordShortlist Word shortlist.
	 * @param words         Current list of words.
	 */
	private SolutionState(final SolutionState solutionState, final AvailableLetters letters,
			final List<String> wordShortlist, final List<String> words) {
		this.length = solutionState.length;
		this.letters = letters;
		this.firstMatchOnly = solutionState.firstMatchOnly;
		this.wordShortlist = wordShortlist;
		this.words = words;
		this.wordSquares = solutionState.wordSquares;
	}

	/**
	 * Get the length of each word in the word square.
	 * 
	 * @return The length of each word in the word square.
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Get the available letters from which to form words.
	 * 
	 * @return The available letters from which to form words.
	 */
	public AvailableLetters getLetters() {
		return letters;
	}

	/**
	 * Get the flag indicating if we want to stop after we find the first valid word
	 * square.
	 * 
	 * @return true if we want to stop after finding the first match, or false if we
	 *         want to continue until all words have been exhausted.
	 */
	public boolean isFirstMatchOnly() {
		return firstMatchOnly;
	}

	/**
	 * Get the word shortlist - the list of valid words to form word squares from.
	 * 
	 * @return The word shortlist.
	 */
	public List<String> getWordShortlist() {
		return wordShortlist;
	}

	/**
	 * Get the list of words - this is the valid words we have so far on the way to
	 * completing a word square.
	 */
	public List<String> getWords() {
		return words;
	}

	/**
	 * Get the list of word squares.
	 * 
	 * @return List of word squares.
	 */
	public List<WordSquare> getWordSquares() {
		return wordSquares;
	}

	/**
	 * Add a word square (after having found a valid solution).
	 * 
	 * @param wordSquare The word square to add.
	 * @throws FirstWordSquareSolvedException Thrown if the firstMatchOnly flag is
	 *                                        true (we have found the first match,
	 *                                        so there is no need to find any more).
	 * @throws InvalidWordSquareException     Thrown if the word square we are
	 *                                        trying to add is invalid.
	 */
	public void addWordSquare(final WordSquare wordSquare)
			throws FirstWordSquareSolvedException, InvalidWordSquareException {
		// Make sure that the word square is valid.
		if (!wordSquare.isValid()) {
			throw new InvalidWordSquareException(wordSquare);
		}

		// Is the firstMatchOnly flag true?
		if (firstMatchOnly) {
			// We only want the first valid word square. Throw a
			// FirstWordSquareSolvedException.
			throw new FirstWordSquareSolvedException(wordSquare);
		}

		// Add the word square to the list of word squares.
		wordSquares.add(wordSquare);
	}

	/**
	 * Get valid word squares for a given starting word, available letters, and word
	 * shortlist.
	 * 
	 * @param word The current word.
	 * @throws FirstWordSquareSolvedException Thrown if the firstMatchOnly flag is
	 *                                        true (we have found the first match,
	 *                                        so there is no need to find any more).
	 * @throws InvalidWordSquareException     Thrown if a word square we are trying
	 *                                        to add is invalid.
	 */
	public void getValidWordSquaresForStartingWord(final String word)
			throws FirstWordSquareSolvedException, InvalidWordSquareException {
		// Do we have the required number of words in the list of words to make a word
		// square?
		if (words.size() == length - 1) {
			// Add a new word square to end of the list of word squares.
			addWordSquare(new WordSquare(length, ListUtils.union(words, Collections.singletonList(word))));
		} else {
			// Get the remaining letters left, after removing those from the current word
			// from the available letters.
			final AvailableLetters remainingLetters = letters.getRemainingLetters(word);

			if (remainingLetters.getCount() >= length) { // only if we have enough letters left to
															// make a word.
				// Create a predicate based on the remaining letters.
				final WordContainsAvailableLettersPredicate wordContainsAvailableLettersPredicate = new WordContainsAvailableLettersPredicate(
						remainingLetters);

				// Get the remaining words available, by filtering only those left where the
				// word shortlist contains the remaining letters.
				final List<String> remainingWordShortlist = wordShortlist.stream()
						.filter(wordContainsAvailableLettersPredicate).collect(Collectors.toList());

				// Are there any remaining words?
				if (remainingWordShortlist.size() > 0) {
					// Add the current word to the list of words.
					final List<String> updatedWords = ListUtils.union(words, Collections.singletonList(word));

					// Create a predicate based on the words being valid at the next position in the
					// word square.
					WordMeetsRequirementsPredicate wordMeetsRequirementsPredicate = new WordMeetsRequirementsPredicate(
							updatedWords);

					// Iterate for each remaining word in the shortlist.
					for (final String remainingWord : remainingWordShortlist) {
						// Are the requirements met for this being the next word in the word square?
						if (wordMeetsRequirementsPredicate.test(remainingWord)) {
							// Create a new Solution State for the word.
							SolutionState solutionState = new SolutionState(this, remainingLetters,
									remainingWordShortlist, updatedWords);
							// Recursively call this function for the word that is valid.
							solutionState.getValidWordSquaresForStartingWord(remainingWord);
						}
					}
				}
			}
		}
	}
}
