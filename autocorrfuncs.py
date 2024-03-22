import matplotlib.pyplot as plt
import pandas as pd
import re
import seaborn as sns

"""
A series of functions for the cryptanalysis of
the Vigenère cipher used to determine key length.
"""

def normalize(ciphertext) -> str:
        """
        Preprocess input text to upper case,
        remove punctuation, spacing and
        newlines / carriage returns (\r: Windows)
        """
        ciphertext = ciphertext.upper()

        ciphertext = re.sub(r'[^\w\s]', '', ciphertext)
        ciphertext = ciphertext.replace(" ", "").replace("\n", "").replace("\r", "")

        return ciphertext

def normalize_german(ciphertext) -> str:
        """
        Preprocess input text to upper case,
        remove punctuation, spacing and
        newlines / carriage returns (\r: Windows),
        and replace characters unique to the
        German language
        """
        ciphertext = normalize(ciphertext)

        ciphertext = ciphertext.replace("Ä", "AE")
        ciphertext = ciphertext.replace("Ö", "OE")
        ciphertext = ciphertext.replace("Ü", "UE")
        ciphertext = ciphertext.replace("ß", "SS")

        return ciphertext

def shift_text(ciphertext: str, positions: int) -> str:
        """
        Shifts a copy of ciphertext an 
        input number of positions
        """
        return (positions * "-") + ciphertext[0:len(ciphertext) - positions]

def print_shifts(ciphertext: str) -> None:
        """
        A helper function which iterates over
        and prints all shifts of text
        """
        for i in range(len(ciphertext)):
            print(i, ": ", shift_text(ciphertext, i))

def compare_texts(ciphertext: str, shifted_text: str) -> int:
        """
        Compares ciphertext and shifted
        text and returns the number of
        matching letters for each index
        """
        matches = 0

        for i in range(len(shifted_text)):
            if(ciphertext[i] == shifted_text[i]):
                matches += 1
        
        return matches

def autocorrelate1(ciphertext: str, shifts: int) -> None:
        """
        Prints the number of matching letters
        resulting from a comparison of the
        ciphertext and each shift of a copy
        of the ciphertext up to a supplied
        number of shifts
        """
        for i in range(shifts):
               st = shift_text(ciphertext, i)
               count = compare_texts(ciphertext, st)
               print(i, ": ", count)

def autocorrelate2(ciphertext: str, shifts: int) -> dict:
        """
        Returns as a dictionary the number of
        matching letters resulting from a
        comparison of the ciphertext and each
        shift of a copy of the ciphertext up
        to a supplied number of shifts
        """
        matches = {}

        for i in range(shifts):
            
            st = shift_text(ciphertext, i)

            matches[i] = compare_texts(ciphertext, st)
        
        return matches

def display_correlations(dictionary: dict) -> None:
    """
    A helper function to display as a line graph
    the number of matching letters between ciphertext
    and each shift of the copy of the ciphertext
    """
    dictionary.pop(0)   # remove first key-value pair to avoid skewing graph
    
    plt.figure(figsize=(12, 6))
    df = pd.Series(dictionary)
    new_sns = sns.lineplot(data = df, marker="o")
    new_sns.set(xticks=df.keys())

    plt.title('Autokorrelationsanalyse')
    plt.xlabel('Verschiebungen')
    plt.ylabel('Übereinstimmungen')

    plt.grid()
    plt.show()
