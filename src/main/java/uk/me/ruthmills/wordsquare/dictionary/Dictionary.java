package uk.me.ruthmills.wordsquare.dictionary;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
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
	 * Read all the words from the dictionary file.
	 * 
	 * @return A list of all the words from the dictionary file.
	 * @throws IOException        Thrown if we cannot read from the dictionary file.
	 * @throws URISyntaxException Thrown if there is a problem with the URI syntax.
	 */
	public List<String> getAllWords() throws IOException, URISyntaxException {
		try (Stream<String> linesStream = Files
				.lines(Paths.get(ClassLoader.getSystemResource(DICTIONARY_TEXT_FILE_NAME).toURI()))) {
			return linesStream.map(word -> word.toString()).collect(Collectors.toList());
		}
	}

	/**
	 * Read only the words matching the required length from the dictionary file.
	 * 
	 * @param length The required length of the words to return.
	 * @return A list of only those words matching the required length.
	 * @throws IOException        Thrown if we cannot read from the dictionary file.
	 * @throws URISyntaxException Thrown if there is a problem with the URI syntax.
	 */
	public List<String> getWordsOfMatchingLength(int length) throws IOException, URISyntaxException {
		try (Stream<String> linesStream = Files
				.lines(Paths.get(ClassLoader.getSystemResource(DICTIONARY_TEXT_FILE_NAME).toURI()))) {
			return linesStream.map(word -> word.toString()).filter(word -> word.length() == length)
					.collect(Collectors.toList());
		}
	}
}
