package uk.me.ruthmills.wordsquare.letters;

/**
 * Test suite for the Hash Map implementation of the Available Letters
 * interface.
 * 
 * @author ruth
 */
public class HashMapAvailableLettersTest extends AvailableLettersTest {

	/**
	 * Get the Hash Map implementation of the Available Letters interface.
	 * 
	 * @return HashMapAvailableLetters object.
	 */
	@Override
	AvailableLetters getAvailableLetters(String letters) {
		return new HashMapAvailableLetters(letters);
	}
}
