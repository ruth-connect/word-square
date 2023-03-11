package uk.me.ruthmills.wordsquare.predicate;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import uk.me.ruthmills.wordsquare.solution.WordCombination;

/**
 * Predicate class to check if a word square is valid or not.
 * 
 * @author ruth
 */
public class ValidWordSquarePredicate implements Predicate<WordCombination> {

	/**
	 * Check to see if a word combination is a valid word square.
	 * 
	 * @param wordCombination The word combination to check.
	 * @return true if it is a valid word square, or false if not.
	 */
	@Override
	public boolean test(WordCombination wordCombination) {
		List<String> words = wordCombination.getWords();

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
	List<String> translateColumnsToRows(List<String> words) {
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
	String getColumn(List<String> words, int column) {
		return IntStream.range(0, words.size()).mapToObj(row -> words.get(row).charAt(column)).map(String::valueOf)
				.collect(Collectors.joining());
	}
}
