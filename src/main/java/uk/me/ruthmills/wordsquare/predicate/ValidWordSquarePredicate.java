package uk.me.ruthmills.wordsquare.predicate;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import uk.me.ruthmills.wordsquare.solution.WordSquare;

/**
 * Predicate class to check if a word square is valid or not.
 * 
 * @author ruth
 */
public class ValidWordSquarePredicate implements Predicate<WordSquare> {

	/**
	 * Check to see if a word square is valid.
	 * 
	 * @param wordSquare The word square to check. PRE-REQUISITES: the words must
	 *                   ALL be the same length, which MUST the same as the number
	 *                   of words.
	 * @return true if it is a valid word square, or false if not.
	 */
	@Override
	public boolean test(final WordSquare wordSquare) {
		// TODO - In Production code, we might want to assert the pre-requisites here
		// (all words the same length, where the length of each word is the same as the
		// number of words). However, in this instance, we will not have created any
		// word squares not meeting those requirements before calling this
		// predicate - so I think we are fine to skip this. Also we need to optimise
		// performance, so it makes sense to avoid wasting time on any unnecessary
		// checks.

		// Get the list of words from the word square.
		List<String> words = wordSquare.getWords();

		// Swap the vertical and horizontal words.
		List<String> verticalWords = translateColumnsToRows(words);

		// If we have a valid word square, the horizontal and vertical words should all
		// match.
		return words.equals(verticalWords);
	}

	/**
	 * Translate columns to rows. This will return a new list of strings where the
	 * vertical and horizontal words are swapped.
	 */
	List<String> translateColumnsToRows(final List<String> words) {
		return IntStream.range(0, words.size()).mapToObj(column -> getColumn(words, column))
				.collect(Collectors.toList());
	}

	/**
	 * Get a column (i.e. a vertical word) from a word square.
	 * 
	 * @param words  The list of words.
	 * @param column The integer index of the column.
	 * @return A String comprising the word in the column.
	 */
	String getColumn(final List<String> words, final int column) {
		return IntStream.range(0, words.size()).mapToObj(row -> words.get(row).charAt(column)).map(String::valueOf)
				.collect(Collectors.joining());
	}
}
