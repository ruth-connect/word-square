package uk.me.ruthmills.wordsquare.letters;

/**
 * Test suite for the Hash Bag implementation of the Available Letters
 * interface.
 * 
 * @author ruth
 */
public class HashBagAvailableLettersTest extends AvailableLettersTest {

	/**
	 * Get the Hash Bag implementation of the Available Letters interface.
	 * 
	 * @return HashBagAvailableLetters object.
	 */
	@Override
	AvailableLetters getAvailableLetters(String letters) {
		return new HashBagAvailableLetters(letters);
	}
}
