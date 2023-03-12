package uk.me.ruthmills.wordsquare.letters;

/**
 * Factor class to get the prefered implementation of the Available Letters
 * interface. This will let us swap between implementations quickly, if one
 * proves to be more performant than another.
 * 
 * @author ruth
 *
 */
public class AvailableLettersFactory {

	/**
	 * Get an instance of Available Letters.
	 * 
	 * @param letters String containing the available letters.
	 * @return Instance of the preferred implementation of Available Letters.
	 */
	public static AvailableLetters getInstance(String letters) {
		return new HashBagAvailableLetters(letters);
	}
}
