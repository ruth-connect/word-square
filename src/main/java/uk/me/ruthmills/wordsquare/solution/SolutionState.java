package uk.me.ruthmills.wordsquare.solution;

import java.util.ArrayList;
import java.util.List;

import uk.me.ruthmills.wordsquare.exception.FirstWordSquareSolvedException;
import uk.me.ruthmills.wordsquare.exception.InvalidWordSquareException;
import uk.me.ruthmills.wordsquare.letters.AvailableLetters;

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

	// The current word we are evaluating.
	private String word;

	// The current list of words we have in the word square so far.
	private List<String> words;

	// The word squares we have solved so far.
	private final List<WordSquare> wordSquares;

	/**
	 * Constructor. By default we assume that we will return ALL word squares, not
	 * stopping at the first one.
	 * 
	 * @param length  The length of each word in the word square.
	 * @param letters The available letters from which to form words.
	 */
	public SolutionState(final int length, final AvailableLetters letters) {
		this(length, letters, false);
	}

	/**
	 * Constructor.
	 * 
	 * @param length         The length of each word in the word square.
	 * @param letters        The available letters from which to form words.
	 * @param firstMatchOnly true to stop at the first valid word square, false to
	 *                       continue until we run out of words.
	 */
	public SolutionState(final int length, final AvailableLetters letters, final boolean firstMatchOnly) {
		this.length = length;
		this.letters = letters;
		this.firstMatchOnly = firstMatchOnly;
		wordSquares = new ArrayList<>();
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
	 * @exception FirstWordSquareSolvedException Thrown if the firstMatchOnly flag
	 *                                           is true (we have found the first
	 *                                           match, so there is no need to find
	 *                                           any more).
	 * @exception InvalidWordSquareException     Thrown if the word square we are
	 *                                           trying to add is invalid.
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
}
