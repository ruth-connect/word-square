package uk.me.ruthmills.wordsquare.letters;

/**
 * Interface representing the available letters from which we can create words
 * for the word square. We can try different implementations, to see which one
 * performs best.
 * 
 * @author ruth
 */
public interface AvailableLetters {

	/**
	 * Check if a word can be formed from the available letters.
	 * 
	 * @param word The word to check.
	 * @return true if the word can be formed from the available letters, or false
	 *         if not.
	 */
	public boolean isWordFormable(String word);
}
