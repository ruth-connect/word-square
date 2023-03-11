package uk.me.ruthmills.wordsquare.dictionary;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
	 * @throws IOException        Thrown if we cannot read from the dictionary file.
	 * @throws URISyntaxException Thrown if there is a problem with the URI syntax.
	 *                            when attempting to locate the dictionary file.
	 */
	public static List<String> getWordsMatchingPredicate(final Predicate<String> predicate)
			throws IOException, URISyntaxException {
		try (Stream<String> linesStream = Dictionary.getWordsStream()) {
			return linesStream.filter(predicate).collect(Collectors.toList());
		}
	}

	/**
	 * Get the contents of the dictionary text file as a stream of strings.
	 * 
	 * @return A stream of strings (one string for each word in the dictionary).
	 * @throws IOException        Thrown if we cannot read from the dictionary file.
	 * @throws URISyntaxException Thrown if there is a problem with the URI syntax.
	 *                            when attempting to locate the dictionary file.
	 */
	private static Stream<String> getWordsStream() throws IOException, URISyntaxException {
		return Files.lines(Paths.get(ClassLoader.getSystemResource(DICTIONARY_TEXT_FILE_NAME).toURI()));
	}
}
