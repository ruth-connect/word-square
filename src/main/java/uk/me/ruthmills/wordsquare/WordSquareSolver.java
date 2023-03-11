package uk.me.ruthmills.wordsquare;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

import uk.me.ruthmills.wordsquare.solution.WordSquare;

/**
 * Class to solve a word square.
 * 
 * @author ruth
 */
public class WordSquareSolver {

	/**
	 * Solve the word square.
	 * 
	 * @param length  Number of letters in each word.
	 * @param letters Available letters to create the words from.
	 * @return List of possible solutions to the word square (there may be more than
	 *         one for some cases, e.g. dog, oxo, god AND god, oxo, dog).
	 * @throws IOException        Thrown if we cannot read from the dictionary file.
	 * @throws URISyntaxException Thrown if there is a problem with the URI syntax.
	 */
	public static List<WordSquare> solveWordSquare(final int length, final String letters)
			throws IOException, URISyntaxException {
		return Collections.emptyList();
	}
}
