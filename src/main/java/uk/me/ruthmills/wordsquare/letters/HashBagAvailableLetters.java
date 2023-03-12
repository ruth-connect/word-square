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
	 * Constructor.
	 * 
	 * @param letters Bag containing the available letters.
	 */
	public HashBagAvailableLetters(final Bag<Byte> letters) {
		this.availableLetters = letters;
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

	/**
	 * Check if a word can be formed from the available letters.
	 * 
	 * @param word The word to check.
	 * @return true if the word can be formed from the available letters, or false
	 *         if not.
	 */
	@Override
	public boolean isWordFormable(String word) {
		// Get a copy of the Hash Bag.
		Bag<Byte> availableLetters = new HashBag<Byte>(this.availableLetters);

		// Iterate through each letter in the word.
		for (final Byte letter : word.getBytes()) {
			// If the letter is not in the available letters, return false.
			if (!availableLetters.remove(letter, 1)) {
				return false;
			}
		}

		// All the letters in the word are available. Return true.
		return true;
	}

	/**
	 * Get the remaining letters, after removing the letters for the supplied word
	 * from the available letters.
	 * 
	 * @param word The word to remove the letters from.
	 * @return The remaining letters.
	 */
	@Override
	public AvailableLetters getRemainingLetters(String word) {
		// Get a copy of the Hash Bag.
		Bag<Byte> remainingLetters = new HashBag<Byte>(this.availableLetters);

		// Iterate through each letter in the word.
		for (final Byte letter : word.getBytes()) {
			// Remove the letter from the bag.
			remainingLetters.remove(letter, 1);
		}

		// Return the remaining letters.
		return new HashBagAvailableLetters(remainingLetters);
	}

	/**
	 * Get the count of available letters.
	 * 
	 * @return The count of available letters.
	 */
	@Override
	public int getCount() {
		return availableLetters.size();
	}
}
