package uk.me.ruthmills.wordsquare.letters;

import java.util.stream.Collectors;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;

/**
 * Hash Bag implementation of available letters.
 * 
 * @author ruth
 */
public class HashBagAvailableLetters implements AvailableLetters {

	// The available letters.
	private final Bag<Byte> availableLetters;

	/**
	 * Constructor.
	 * 
	 * @param letters String containing the available letters.
	 */
	public HashBagAvailableLetters(final String letters) {
		// Create a bag of available letters.
		availableLetters = new HashBag<Byte>();
		for (final Byte letter : letters.getBytes()) {
			availableLetters.add(letter);
		}
	}

	/**
	 * Convert to a String.
	 * 
	 * @return String containing the available letters.
	 */
	@Override
	public String toString() {
		return availableLetters.stream().map(value -> String.valueOf((char) value.byteValue())).sorted()
				.collect(Collectors.joining());
	}
}
