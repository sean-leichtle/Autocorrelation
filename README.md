# Autocorrelation

This repository provides a simple autocorrelation function for the cryptanalysis of the Vigenere cipher and may be used, specifically, to determine the length of the key used to encrypt a ciphertext. With respect to the number of matching characters per shift of a copy of the ciphertext as compared to the ciphertext itself, any periodic interval occuring between maxima is indicative of key length.

Autocorrelation has some advantages over other popular means of determining key length. Unlike the [Kasiski Test](https://en.wikipedia.org/wiki/Kasiski_examination), it does not depend upon recurring n-grams of length two or greater, and the implementation is simpler than that of the [Friedman Test](https://en.wikipedia.org/wiki/Vigenère_cipher#Friedman_test).

Both Java and Python implementations are included.

## Java
The [Autocorrelation](https://github.com/sean-leichtle/Autocorrelation/blob/main/Autocorrelation.java) class can be used to output the number of matching characters per shift of ciphertext to the console while the [AutocorrelationVisual](https://github.com/sean-leichtle/Autocorrelation/blob/main/AutocorrelationVisual.java) class displays the same information as a JavaFX line chart.

In the latter case, to avoid employing a build system (e.g. maven or gradle) or [the somewhat involved process of compiling from the command line](https://inside.java/2023/11/14/package-javafx-native-exec/){:target="_blank"}, the use of Java 1.8 or "Java 8", which comes packaged with JavaFX, is recommended. The implementation requires user input for the ciphertext and desired number of shifts in the first two statements of the `main()`-method:

- `String ciphertext = "/* Insert ciphertext here */";`
- `int shifts = /* Insert desired number of shifts here */;`

## Python
[autocorrfuncs.py](https://github.com/sean-leichtle/Autocorrelation/blob/main/autocorrfuncs.py) contains a series of functions for analysis via autocorrelation, while [autocorrelation.py](https://github.com/sean-leichtle/Autocorrelation/blob/main/autocorrelation.py) includes an object-oriented implementation of the same. Both versions allow for the output of results to the console or as a line chart.
