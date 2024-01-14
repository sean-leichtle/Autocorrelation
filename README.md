# Autocorrelation

This repository provides a simple autocorrelation function for the cryptanalysis of the Vigenere cipher and may be used, specifically, to determine the key length of a cipher text. With respect to the number of matching characters per shift of a copy of the cipher text, any periodic interval occuring between maxima is indicative of key length.

Autocorrelation has some advantages as compared to other popular means of determining key length. Unlike the Kasiski Test, it does not depend upon recurring n-grams of length two or greater, and the implementation is simpler than that of the Friedman Test.

Both Java and Python implementations are included. The [Autocorrelation](https://github.com/sean-leichtle/Autocorrelation/blob/main/Autocorrelation.java) class can be used to output the number of matching characters per shift of ciphertext to the console while the [AutocorrelationVisual](https://github.com/sean-leichtle/Autocorrelation/blob/main/AutocorrelationVisual.java) class displays the same information as a JavaFX line chart.

In the latter case, to avoid having to employ a build system (e.g. maven or gradle), the usage of Java 1.8 or "Java 8", which comes packaged with JavaFX, is recommended. This is a makeshift, idiosyncratic implementation requiring user input in the first two statements of the start()-method:

- `AutocorrelationVisual acv = new AutocorrelationVisual("/* insert ciphertext here */");`
- `Map<Integer, Integer> matches = acv.autocorrelate(/* input number of shifts here */);`

[autocorrfuncs.py](https://github.com/sean-leichtle/Autocorrelation/blob/main/autocorrfuncs.py) contains a series of functions for analysis via autocorrelation, while [autocorrelation.py](https://github.com/sean-leichtle/Autocorrelation/blob/main/autocorrelation.py) includes an object-oriented implementation of the same. Both versions allow for the output of results to the console or as a line chart.
