package uk.me.ruthmills.wordsquare;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import uk.me.ruthmills.wordsquare.solution.WordSquare;
import uk.me.ruthmills.wordsquare.solution.WordSquareGenerator;

/**
 * Class to solve a word square.
 * 
 * @author ruth
 */
public class WordSquareSolver {

	/**
	 * Main function. Executes the word square solver for the supplied arguments.
	 * 
	 * @param args Arguments: <length> <letters>
	 * @throws IOException        Thrown if we cannot read from the dictionary file.
	 * @throws URISyntaxException Thrown if there is a problem with the URI syntax.
	 *                            when attempting to locate the dictionary file.
	 */
	public static void main(final String[] args) throws IOException, URISyntaxException {
		if (args.length != 2) {
			// Output the required parameters.
			System.out.println("Required parameters: <length> <letters>");
		} else {
			try {
				// Get the length and letters from the parameters.
				int length = Integer.parseInt(args[0]);
				String letters = args[1];
				final List<WordSquare> solutions = WordSquareSolver.solveWordSquare(length, letters, true);
				if (solutions.size() > 0) {
					System.out.println(solutions.get(0).toString());
				} else {
					System.out.println("No solution exists for this word square");
				}
			} catch (NumberFormatException ex) {
				System.out.println("First parameter (length) must be an integer");
			}
		}
	}

	/**
	 * Solve the word square.
	 * 
	 * @param length  Number of letters in each word.
	 * @param letters Available letters to create the words from.
	 * @return List of possible solutions to the word square (there may be more than
	 *         one for some cases, e.g. dog, oxo, god AND god, oxo, dog).
	 * @throws IOException        Thrown if we cannot read from the dictionary file.
	 * @throws URISyntaxException Thrown if there is a problem with the URI syntax.
	 *                            when attempting to locate the dictionary file.
	 */
	public static List<WordSquare> solveWordSquare(final int length, final String letters)
			throws IOException, URISyntaxException {
		return solveWordSquare(length, letters, false); // return all possible solutions by default, even if more than
														// one.
	}

	/**
	 * Solve the word square.
	 * 
	 * @param length  Number of letters in each word.
	 * @param letters Available letters to create the words from.
	 * @return List of possible solutions to the word square (there may be more than
	 *         one for some cases, e.g. dog, oxo, god AND god, oxo, dog).
	 * @throws IOException        Thrown if we cannot read from the dictionary file.
	 * @throws URISyntaxException Thrown if there is a problem with the URI syntax.
	 *                            when attempting to locate the dictionary file.
	 */
	public static List<WordSquare> solveWordSquare(final int length, final String letters, final boolean firstMatchOnly)
			throws IOException, URISyntaxException {
		// Get the valid word squares, optionally returning only the first if
		// firstMatchOnly is true.
		return WordSquareGenerator.getValidWordSquares(length, letters, firstMatchOnly);
	}
}
