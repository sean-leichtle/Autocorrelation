import matplotlib.pyplot as plt
import pandas as pd
import re
import seaborn as sns

class Autocorrelation():
    """
    A class for the cryptanalysis of the Vigenère
    cipher used to determine key length.
    """

    def __init__(self, ciphertext: str = None) -> None:
        """
        Initializes an Autocorrelation-object
        with or without a ciphertext
        """
        self.ciphertext = ciphertext
    
    def get_ciphertext(self) -> str:
        """
        Returns object's current ciphertext
        """
        return self.ciphertext
    
    def set_ciphertext(self, text: str) -> None:
        """
        Sets object's current ciphertext
        """
        self.ciphertext = text
    
    def normalize(self) -> None:
        """
        Preprocess input text to upper case,
        remove punctuation, spacing and
        newlines / carriage returns (\r: Windows)
        """
        self.ciphertext = self.ciphertext.upper()

        self.ciphertext = re.sub(r'[^\w\s]', '', self.ciphertext)
        self.ciphertext = self.ciphertext.replace(" ", "").replace("\n", "").replace("\r", "")
    
    def normalize_german(self) -> None:
        """
        Preprocess input text to upper case,
        remove punctuation, spacing and
        newlines / carriage returns (\r: Windows),
        and replace characters unique to the
        German language
        """
        self.normalize()

        self.ciphertext = self.ciphertext.replace("Ä", "AE")
        self.ciphertext = self.ciphertext.replace("Ö", "OE")
        self.ciphertext = self.ciphertext.replace("Ü", "UE")
        self.ciphertext = self.ciphertext.replace("ß", "SS")
    
    def shift_text(self, positions: int) -> str:
        """
        Shifts a copy of ciphertext an 
        input number of positions
        """
        return (positions * "-") + self.ciphertext[0:len(self.ciphertext) - positions]
    
    def print_shifts(self) -> None:
        """
        A helper function which iterates over
        and prints all shifts of text
        """
        for i in range(len(self.ciphertext)):
            print(i, ": ", self.shift_text(i))

    def compare_texts(self, shifted_text: str) -> int:
        """
        Compares ciphertext and shifted
        text and returns the number of
        matching letters for each index
        """
        matches = 0

        for i in range(len(shifted_text)):
            if(self.ciphertext[i] == shifted_text[i]):
                matches += 1
        
        return matches
    
    def autocorrelate1(self, number_shifts: int) -> None:
        """
        Prints the number of matching letters
        resulting from a comparison of the
        ciphertext and each shift of a copy
        of the ciphertext up to a supplied
        number of shifts
        """
        for i in range(number_shifts):

            st = self.shift_text(i)

            count = self.compare_texts(st)

            print(i, ": ", count)

    def autocorrelate2(self, number_shifts: int) -> None:
        """
        Returns as a dictionary the number of
        matching letters resulting from a
        comparison of the ciphertext and each
        shift of a copy of the ciphertext up
        to a supplied number of shifts
        """
        matches = {}

        for i in range(number_shifts):
            
            st = self.shift_text(i)

            matches[i] = self.compare_texts(st)
        
        self.display_correlations(matches)
    
    def display_correlations(self, dictionary: dict) -> None:
        """
        A helper function to display
        matches per shift as a line chart
        """
        dictionary.pop(0) # remove first key-value pair to avoid skewing graph

        plt.figure(figsize=(12, 6))
        df = pd.Series(dictionary)
        new_sns = sns.lineplot(data = df, marker="o")
        new_sns.set(xticks=df.keys())

        plt.title('Autocorrelation analysis')
        plt.xlabel('Shifts')
        plt.ylabel('Matches')

        plt.grid()
        plt.show()

ac = Autocorrelation("äö..üü")
ac.normalize_german()
print(ac.get_ciphertext())