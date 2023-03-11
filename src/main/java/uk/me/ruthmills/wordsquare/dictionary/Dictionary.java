package uk.me.ruthmills.wordsquare.dictionary;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class represents a dictionary of all the available words.
 * 
 * @author ruth
 */
public class Dictionary {

	private static final String DICTIONARY_TEXT_FILE_NAME = "dictionary.txt";

	/**
	 * Read only the words matching the supplied predicate.
	 * 
	 * @param predicate The predicate to match the words with.
	 * @return A list of only those words matching the predicate.
	 * @throws IOException Thrown if we cannot read from the dictionary file.
	 */
	public static List<String> getWordsMatchingPredicate(final Predicate<String> predicate) throws IOException {
		try (Stream<String> linesStream = Dictionary.getWordsStream()) {
			return linesStream.filter(predicate).collect(Collectors.toList());
		}
	}

	/**
	 * Get the contents of the dictionary text file as a stream of strings.
	 * 
	 * @return A stream of strings (one string for each word in the dictionary).
	 * @throws IOException Thrown if we cannot read from the dictionary file.
	 */
	private static Stream<String> getWordsStream() throws IOException {
		Path path = FileSystems.getDefault().getPath("src/main/resources", DICTIONARY_TEXT_FILE_NAME);
		return Files.lines(path);
	}
}
