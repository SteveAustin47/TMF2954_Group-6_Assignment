// Encoded.java
// Created by Stephen [106446] (Core logic)
// Modified by [Member 2] (GUI integration if needed)
// Checked by [Member 3] (Testing, error handling suggestions)

public class Encoded {
    // Data fields for input, count, result, and groupID
    private String inputText;   // Stores user input string
    private int charCount;      // Number of non-space characters
    private String resultText;  // Encoded output string
    private final String groupID = "G03/DE-G6"; // Hardcoded group ID (replace with actual)

    // Created by Miles: Default constructor
    // Check to ensure empty initialization works correctly
    public Encoded() {
        this.inputText = "";
        this.charCount = 0;
        this.resultText = "";
    }

    // Constructor with input
    // Require checking 
    public Encoded(String inputText) {
        this.inputText = inputText;
        this.charCount = countCharacters(inputText);
        int shift = generateShift();
        this.resultText = applyCipher(inputText, shift);
    }

    // Validate input string
    // Suggestion: Add unit tests for edge cases (empty string, uppercase letters, symbols)
    public boolean checkStringValidity(String inputText) {
        return inputText.matches("[a-z0-9 ]+"); 
        // Only lowercase letters, digits, and spaces allowed
    }

    // Count non-space characters
    // Suggestion: Verify counts with strings containing multiple spaces
    public int countCharacters(String inputText) {
        int count = 0;
        for (char c : inputText.toCharArray()) {
            if (c != ' ') count++;
        }
        return count;
    }

    // Generate group-specific shift
    public int generateShift() {
        int hash = groupID.hashCode();
        int groupShift = Math.abs(hash % 10) + 1; 
        // Always between 1 and 10
        return groupShift + charCount; 
        // Final shift = groupShift + non-space characters
    }

    // Apply cipher encoding
    // Suggestion: Add test cases for digits, letters, and spaces
    public String applyCipher(String inputText, int finalShift) {
        StringBuilder encoded = new StringBuilder();
        for (char c : inputText.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                encoded.append((char) ((c - 'a' + finalShift) % 26 + 'a'));
            } else if (c >= '0' && c <= '9') {
                encoded.append((char) ((c - '0' + finalShift) % 10 + '0'));
            } else {
                encoded.append(c); // spaces unchanged
            }
        }
        return encoded.toString();
    }

    // Getter for result
    public String getResultText() {
        return resultText;
    }

    // Getter for charCount
    public int getCharCount() {
        return charCount;
    }
}
