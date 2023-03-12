package uk.me.ruthmills.wordsquare.solution;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import uk.me.ruthmills.wordsquare.exception.FirstWordSquareSolvedException;
import uk.me.ruthmills.wordsquare.exception.InvalidWordSquareException;
import uk.me.ruthmills.wordsquare.letters.AvailableLetters;
import uk.me.ruthmills.wordsquare.letters.AvailableLettersFactory;

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

	private SolutionState(SolutionState solutionState, AvailableLetters letters, List<String> wordShortlist,
			List<String> words) {
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
	public void addWordSquare(WordSquare wordSquare) throws FirstWordSquareSolvedException, InvalidWordSquareException {
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
	 * Get an updated solution state.
	 * 
	 * @param letters       Updated available letters.
	 * @param wordShortlist Updated word shortlist.
	 * @param words         Updated words.
	 * @return updated solution state.
	 */
	public SolutionState getUpdatedSolutionState(AvailableLetters letters, List<String> wordShortlist,
			List<String> words) {
		return new SolutionState(this, letters, wordShortlist, words);
	}
}
