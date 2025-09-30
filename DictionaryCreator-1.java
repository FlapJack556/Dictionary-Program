/*
 * Name: Jesse Rodriguez
 * Pledge: I pledge my honor that I have abided by the Stevens Honor System.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DictionaryCreator {


    public static void main(String[] args) {
        new DictionaryCreator();
    }


    // Constructor without filename parameter
    public DictionaryCreator() {
        Dictionary dict = readFile("ionDictionary.txt");
        printMenu(dict);
    }

    // Constructor with filename parameter
    public DictionaryCreator(String filename) {
        Dictionary dict = readFile(filename);
        printMenu(dict);
    }

    // Check if file exists
    public boolean fileExists(String filename) {
        File file = new File(filename);
        return file.exists();
    }

    // Read file and create dictionary
    public Dictionary readFile(String filename) {
        Dictionary dictionary = new Dictionary();
        try {
            Scanner scan = new Scanner(new File(filename));
            for (int i = 0; i < 4; i++) scan.nextLine(); // Skip header lines
            // Iterate through every line in the list and convert values into DictionaryItems
            while (scan.hasNextLine()) {
                dictionary.addWordToDictionary(splitWordCountPair(scan.nextLine()));
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return dictionary;
    }

    // Create a new dictionary
    public Dictionary createADictionary() {
        return new Dictionary();
    }

    // Split a line from the file into a DictionaryItem
    private DictionaryItem splitWordCountPair(String line) {
        // Trim whitespace from the input line
        line = line.trim();

        // Split the line using pipe character as delimiter
        String[] parts = line.split("\\|");

        // Check if the line has exactly two parts
        if (parts.length != 2) {
            System.out.println("Invalid line format: " + line);
            return null;
        }

        // Extract word and count parts
        String word = parts[0].trim(); // Trim whitespace from the word part
        String countStr = parts[1].trim(); // Trim whitespace from the count part

        try {
            // Parse the count part to an integer
            int count = Integer.parseInt(countStr);
            return new DictionaryItem(word, count);
        } catch (NumberFormatException e) {
            System.out.println("Invalid count format for line: " + line);
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Prints the menu options for interacting with the Ion Dictionary.
     * Allows the user to choose from options including printing all words,
     * searching for a word, or quitting the program
     */
    public void printMenu(Dictionary dictionary) {
        System.out.println("Welcome to the Ion Dictionary! This dictionary is created from the book Ion by Plato!");
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Please choose one of the following menu items indicated with 1-3\n1: To print all the words in the dictionary, choose 1\n2: To search a word in the dictionary, choose 2\n3: To quit the program, choose 3");
            String input = scan.nextLine();
            if (input.equals("1") || input.equals("2")) {
                processMenuItem(Integer.parseInt(input), scan, dictionary);
            } else if (input.equals("3")) {
                System.out.println("Thanks for using Ion Dictionary! Bye!");
                return;
            } else {
                System.out.println("ERROR! Please enter a number between 1 and 3.");
            }
        }
    }
    /**
     * Processes the selected menu item.
     * If menuItem is 1, prints all words in the dictionary.
     * If menuItem is 2, prompts the user for a word to search and displays its occurrence count.
     */

    private boolean processMenuItem(int menuItem, Scanner scan, Dictionary dictionary) {
        if (menuItem == 1) {
            System.out.println("All the words mentioned in the Ion book!");
            System.out.println("Words");
            System.out.println("-----");
            dictionary.printDictionary();
        } else if (menuItem == 2) {
            System.out.println("Please enter the word you would like to search: ");
            String word = scan.nextLine();
            int cnt = dictionary.searchDictionary(word);
            if (cnt == 0) {
                System.out.println("The word '"+word+"' does not exist in the Ion dictionary!");
            } else {
                System.out.println("The word '"+word+"' occurred "+cnt+" times in the book!");
            }
        }
        return true;
    }
}
