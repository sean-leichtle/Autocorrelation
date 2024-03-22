import java.util.HashMap;
import java.util.Map;

public class Autocorrelation {

    private String cipherText;

    /*
     * Allows for the initialization of an
     * Autocorrelation-object without a
     * ciphertext.
     */
    public Autocorrelation() { }
    
    /*
     * Allows for the initialization of an
     * Autocorrelation-object with a ciphertext.
     */
    public Autocorrelation(String cipherText) {
        this.cipherText = cipherText;
    }

    /*
     * Returns object's current ciphertext.
     */
    public String getCipherText() {
        return this.cipherText;
    }

    /*
     * Sets object's current ciphertext.
     */
    public void setCipherText(String text) {
        this.cipherText = text;
    }

    /*
     * Preprocess input text to upper case,
     * remove punctuation, spacing and
     * newlines / carriage returns.
     */
    public void normalize() {
        this.cipherText = this.cipherText.toUpperCase();
        this.cipherText = this.cipherText.replaceAll("[\\p{Punct}\\s]+", "");
    }

    /*
     * Preprocess input text to upper case,
     * remove punctuation, spacing and
     * newlines / carriage returns,
     * and replace characters unique to the
     * German language.
     */
    public void normalizeGerman() {
        this.cipherText = this.cipherText.toUpperCase();
        this.cipherText = this.cipherText.replaceAll("[\\p{Punct}\\s]+", "");

        this.cipherText = this.cipherText.replace("Ä", "AE");
        this.cipherText = this.cipherText.replace("Ö", "OE");
        this.cipherText = this.cipherText.replace("Ü", "UE");
        this.cipherText = this.cipherText.replace("ß", "SS");
    }

    /*
     * Shifts a copy of ciphertext an
     * input number of positions.
     */
    public String shiftText(int positions) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < positions; i++) { sb.append("-"); }
        sb.append(this.cipherText.substring(0, this.cipherText.length() - positions));
        return sb.toString();
    }

    /*
     * A helper function which iterates over
     * and prints all shifts of text.
     */
    public void printShifts() {
        for(int i = 0; i < this.cipherText.length(); i++) {
            System.out.println((i < 10 ? "0" + i : i) + ":\t" + this.shiftText(i));
        }
    }

    /*
     * Compares ciphertext and shifted
     * text and returns the number of
     * matching letters for each index.
     */
    public int compareTexts(String shiftedText) {
        int matches = 0;
        for(int i = 0; i < shiftedText.length(); i++) {
            if(this.cipherText.charAt(i) == shiftedText.charAt(i)) { matches++; }
        }
        return matches;
    }

    /*
     * Prints the number of matching letters
     * between ciphertext and each shift of
     * the copy of the ciphertext up to a
     * supplied number of shifts.
     */
    public void autocorrelate1(int shifts) {
        int i = 0;
        while(i < shifts) {
            String st = this.shiftText(i);
            int count = this.compareTexts(st);
            System.out.println(i++ + ": " + count);
        }
    }

    /*
     * Returns a map containing the number
     * of matching letters between ciphertext and
     * each shift of the copy of the ciphertext
     * up to a supplied number of shifts.
     */
    public Map<Integer, Integer> autocorrelate2(int shifts) {
        Map<Integer, Integer> matches = new HashMap<>();
        int i = 0;
        while(i < shifts) {
            String st = this.shiftText(i);
            int count = this.compareTexts(st);
            matches.put(i++, count);
        }
        matches.remove(0); // remove first key-value pair to avoid skewing graph
        return matches;
    }
}