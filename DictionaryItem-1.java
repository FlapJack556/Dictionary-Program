public class DictionaryItem {
    private String word;
    private int count;

    // Constructor
    public DictionaryItem(String word, int count) {
        this.word = word;
        this.count = count;
    }

    // Getter for word
    public String getWord() {
        return word;
    }

    // Setter for word
    public void setWord(String word) {
        this.word = word;
    }

    // Getter for count
    public int getCount() {
        return count;
    }

    // Setter for count
    public void setCount(int count) {
        this.count = count;
    }
}