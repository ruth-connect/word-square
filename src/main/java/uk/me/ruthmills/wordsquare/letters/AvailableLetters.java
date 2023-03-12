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

	/**
	 * Get the remaining letters, after removing the letters for the supplied word
	 * from the available letters.
	 * 
	 * @param word The word to remove the letters from.
	 * @return The remaining letters.
	 */
	public AvailableLetters getRemainingLetters(String word);

	/**
	 * Get the count of available letters.
	 * 
	 * @return The count of available letters.
	 */
	public int getCount();
}
