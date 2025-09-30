
import java.util.ArrayList;

public class Dictionary {
    private ArrayList<String> wordList;
    private ArrayList<DictionaryItem> dictArrayList;

    // Constructor
    public Dictionary() {
        wordList = new ArrayList<>(1300);
        dictArrayList = new ArrayList<>(1300);
    }

    // Add word to dictionary
    public boolean addWordToDictionary(DictionaryItem item) {
        wordList.add(item.getWord());
        dictArrayList.add(item);
        return true;
    }

    // Print dictionary
    public void printDictionary() {
        for (String word : wordList)
            if (word != null) System.out.println(word);
    }

    // Check if word exists
    public boolean hasWord(String word) {
        return wordList.contains(word);
    }

    // Search dictionary
    public int searchDictionary(String word) {
        int ind = binarySearch(word, 0, wordList.size());
        if (ind == -1) return 0;
        return dictArrayList.get(ind).getCount();
    }
    // Binary search
    private int binarySearch(String word, int low, int high) {
        while (low != high) {
            int mid = (low+high)/2;
            if (word.equals(wordList.get(mid))) return mid;
            if (word.compareTo(wordList.get(mid)) > 0) {
                low = mid+1;
            } else {
                high = mid-1;
            }
        }
        return -1;
    }
}