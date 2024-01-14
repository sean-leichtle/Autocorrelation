This repository provides a simple autocorrelation function for the cryptanalysis of the Vigenere cipher and may be used, specifically, to determine the key length of a cipher text.

Both java and python implementations are included. The [Autocorrelation](https://github.com/sean-leichtle/Autocorrelation/blob/main/Autocorrelation.java) class can be used to output the number of matching characters per shift of ciphertext to the console while [AutocorrelationVisual](https://github.com/sean-leichtle/Autocorrelation/blob/main/AutocorrelationVisual.java) class displays the same information as a JavaFX line chart.

In the latter case, to avoid employing a build system (e.g. maven or gradle), the usage of Java 1.8 or "Java 8", which comes packaged with JavaFX, is recommended. This is a temporary, idiosyncratic implementation requiring user input in the first two statements of the start()-method:
- `AutocorrelationVisual acv = new AutocorrelationVisual(""/* insert ciphertext here */);`
- `Map<Integer, Integer> matches = acv.autocorrelate(/* input number of shifts here */);`

[autocorrfuncs.py](https://github.com/sean-leichtle/Autocorrelation/blob/main/autocorrfuncs.py) contains a series of functions, while [autocorrelation.py](https://github.com/sean-leichtle/Autocorrelation/blob/main/autocorrelation.py) includes an object-oriented implementation.