package assignment;

import static org.junit.Assert.fail;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Random;
import java.util.stream.Stream;

import org.junit.Test;


/*
//* Author: Conor Finnegan
 * 	Date: Nov 27th 2022
 * 	Description: Program to generate one million random numbers and output to a file. Then read those numbers from the 
 * 	file, sort them from smallest to largest and output to a new file. 
 */

public class NumberGeneratorAndSorter {

	public static void main(String []args) throws IOException {
		Date date = new Date();
		final String outputFilename = "RandomNumbers"+ date.getTime() +".txt";
		final String sortedFilename = "SortedNumbers "+ date.getTime() +".txt";
		
		generateOutput(outputFilename);
		sortNumbersAndOutput(outputFilename, sortedFilename);
	}
	
	// Method to generate one million random numbers and output to a file
	private static void generateOutput(final String outputFilename) throws IOException {
		Random random = new Random();
		
		BufferedWriter outputWriter = new BufferedWriter(new FileWriter(outputFilename));
		
		int count = 0;
		System.out.println("Starting output");
		while(count < 100000) {
			addTenRandomNumbers(random, outputWriter);
		    count++;
		}
		outputWriter.close();
		System.out.println("Output complete");
	}

	/*
	 * In an effort to reduce the time complexity of the while loop, I am adding 10 random numbers per iteration.
	 * This means we will only need to iterate through the loop One Hundred Thousand times instead of One Million times. 
	 * This can be modified further to reduce the time complexity
	 */	
	private static void addTenRandomNumbers(Random random, BufferedWriter outputWriter) throws IOException {
		outputWriter.write(String.valueOf(random.nextInt()));
		outputWriter.newLine();
		outputWriter.write(String.valueOf(random.nextInt()));
		outputWriter.newLine();
		outputWriter.write(String.valueOf(random.nextInt()));
		outputWriter.newLine();
		outputWriter.write(String.valueOf(random.nextInt()));
		outputWriter.newLine();
		outputWriter.write(String.valueOf(random.nextInt()));
		outputWriter.newLine();
		outputWriter.write(String.valueOf(random.nextInt()));
		outputWriter.newLine();
		outputWriter.write(String.valueOf(random.nextInt()));
		outputWriter.newLine();
		outputWriter.write(String.valueOf(random.nextInt()));
		outputWriter.newLine();
		outputWriter.write(String.valueOf(random.nextInt()));
		outputWriter.newLine();
		outputWriter.write(String.valueOf(random.nextInt()));
		outputWriter.newLine();
	}
	
	/*
	 * Reads the random numbers generated in generateOutput() from the file,
	 * sort them from smallest to largest and output to a new file.
	 */	
	private static void sortNumbersAndOutput(final String outputFilename, final String sortedFilename) throws IOException {
		
		BufferedWriter sortedWriter = new BufferedWriter(new FileWriter(sortedFilename));
		
		System.out.println("Reading from file");
		try (Stream<String> stream = Files.lines(Paths.get(outputFilename))) {
			System.out.println("Starting sorting");
			stream.sorted().forEach(entry -> {
				try {
					sortedWriter.write(entry);
					sortedWriter.newLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			sortedWriter.close();
			System.out.println("Sorting complete");
		} catch (IOException e) {
			System.out.println("Unable to locate file: " + e);
		}
		
	}
	
	/*
	 * JUnit Tests
	 */

	// JUnit to test designed to fail by passing in the incorrect filename
	@Test(expected = java.nio.file.NoSuchFileException.class)
	public void testIncorrectFileNameSortNumbersAndOutput() throws Exception {
		try {
			sortNumbersAndOutput("IncorrectFileName.jsp", "AnyNameWillDoHere.txt");
		} catch (Exception e) {
			fail("Exception " + e);
		}
		
	}
	
	// JUnit to test designed to pass by passing in the correct filename
	@Test
	public void testCorrectFileNameSortNumbersAndOutput() {
		try {
			sortNumbersAndOutput("RandomNumbers.txt", "AnyNameWillDoHere.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
