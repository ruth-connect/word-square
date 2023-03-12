package uk.me.ruthmills.wordsquare.letters;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

/**
 * Hash Map implementation of available letters. This is significantly faster
 * than the Hash Bag implementation.
 * 
 * @author ruth
 */
public class HashMapAvailableLetters implements AvailableLetters {

	// The available letters.
	private final Map<Byte, Integer> availableLetters;

	/**
	 * Constructor.
	 * 
	 * @param letters String containing the available letters.
	 */
	public HashMapAvailableLetters(final String letters) {
		// Create a map of available letters.
		availableLetters = new HashMap<Byte, Integer>();
		for (final Byte letter : letters.getBytes()) {
			if (!availableLetters.containsKey(letter)) {
				availableLetters.put(letter, 1);
			} else {
				availableLetters.put(letter, availableLetters.get(letter) + 1);
			}
		}
	}

	/**
	 * Constructor.
	 * 
	 * @param letters Map containing the available letters.
	 */
	public HashMapAvailableLetters(final Map<Byte, Integer> letters) {
		this.availableLetters = letters;
	}

	/**
	 * Convert to a String.
	 * 
	 * @return String containing the available letters.
	 */
	@Override
	public String toString() {
		return availableLetters.entrySet().stream().map(entry -> {
			char letter = (char) entry.getKey().byteValue();
			int count = entry.getValue();
			return StringUtils.repeat(letter, count);
		}).sorted().collect(Collectors.joining());
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
		// Get a copy of the Hash Map.
		Map<Byte, Integer> availableLetters = new HashMap<Byte, Integer>(this.availableLetters);

		// Iterate through each letter in the word.
		for (final Byte letter : word.getBytes()) {
			Integer count = availableLetters.get(letter);
			// If the letter is not in the available letters, return false.
			if (count == null) {
				return false;
			} else {
				if (count > 1) {
					availableLetters.put(letter, count - 1);
				} else {
					availableLetters.remove(letter);
				}
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
		Map<Byte, Integer> remainingLetters = new HashMap<Byte, Integer>(this.availableLetters);

		// Iterate through each letter in the word.
		for (final Byte letter : word.getBytes()) {
			// Remove the letter.
			Integer count = remainingLetters.get(letter);
			if (count > 1) {
				remainingLetters.put(letter, count - 1);
			} else {
				remainingLetters.remove(letter);
			}
		}

		// Return the remaining letters.
		return new HashMapAvailableLetters(remainingLetters);
	}

	/**
	 * Get the count of available letters.
	 * 
	 * @return The count of available letters.
	 */
	@Override
	public int getCount() {
		return availableLetters.entrySet().stream().map(entry -> entry.getValue())
				.collect(Collectors.summingInt(Integer::intValue));
	}
}
