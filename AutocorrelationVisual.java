import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class AutocorrelationVisual extends Application {
    
    private String cipherText;

    /*
     * Allows for the initialization of an
     * Autocorrelation-object without a
     * ciphertext.
     */
    public AutocorrelationVisual() { }
    
    /*
     * Allows for the initialization of an
     * Autocorrelation-object with a ciphertext.
     */
    public AutocorrelationVisual(String cipherText) {
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
     * newlines / carriage returns (\r: Windows),
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
     * Returns a hashmap containing the number
     * of matching lettersbetween ciphertext and
     * each shift of the copy of the ciphertext
     * up to a supplied number of shifts.
     */
    public Map<Integer, Integer> autocorrelate(int shifts) {
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

    /*
     * Displays matches per shift as a line chart.
     * An Autocorrelation object should be
     * initialized with the ciphertext and the number
     * of shifts inserted in the second line of the method as
     * indicated.
     */
    @Override
    public void start(Stage primaryStage) {

        AutocorrelationVisual acv = new AutocorrelationVisual(""/* insert ciphertext in parentheses */);
        Map<Integer, Integer> matches = acv.autocorrelate(31 /* replace 31 with desired number of shifts */);

        NumberAxis xAxis = new NumberAxis("Shifts", 0, 50, 1);
        NumberAxis yAxis = new NumberAxis("Matches", 0, 50, 1);

        xAxis.setMinorTickVisible(false);
        yAxis.setMinorTickVisible(false);

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Autocorrelation analysis");
        lineChart.setLegendVisible(false);

        XYChart.Series data = new XYChart.Series();

        for(Map.Entry<Integer, Integer> entry : matches.entrySet()) {
            data.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
        }

        lineChart.getData().add(data);
 
        Scene view = new Scene(lineChart, 1280, 960);
        primaryStage.setScene(view);
        primaryStage.show();
    }

    /*
     * To display a line chart of an autocorrelation
     * analysis of a ciphertext, insert only
     * "launch(Autocorrelation.class)" in main and
     * and modify the first two lines of the start()-
     * method, above, as instructed.
     */
    public static void main(String[] args) {
        launch(AutocorrelationVisual.class);
    }
}
