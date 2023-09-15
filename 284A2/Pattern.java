import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Pattern {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String pattern = br.readLine();
    String text = br.readLine();
    int occurences = 0;
    int patternLength = pattern.length();
    int countTimes = text.length() - patternLength;
    // YOUR CODE GOES HERE.
    int h = 1;
    int patternHash = calcHash(pattern, patternLength);
    int textHash = calcHash(text, patternLength);
    for (int i = 0; i < patternLength - 1; i++) {
      h = (h * 256) % 769;
      if (h < 0) {
        h += 769;
      }
    }
    for (int i = 0; i < countTimes; i++) {
      if (textHash == patternHash) {
        if (text.substring(i, i + patternLength).equals(pattern)) {
          occurences++;
        }
      }
      textHash =
          recalcHash(h, patternLength, textHash, text.charAt(i), text.charAt(i + patternLength));
    }
    if (textHash == patternHash) {
      if (text.substring(countTimes, countTimes + patternLength).equals(pattern)) {
        occurences++;
      }
    }
    System.out.println(occurences);
  }

  public static int calcHash(String text, int patternLength) {
    int hash = 0;
    for (int i = 0; i < patternLength; i++) {
      hash = (256 * hash + text.charAt(i)) % 769;
    }
    if (hash < 0) {
        hash += 769;
      }
    return hash;
  }

  public static int recalcHash(int h, int patternLength, int oldHash, char oldChar, char newChar) {
    int hash = (256 * (oldHash - oldChar * h) + newChar) % 769;
    if (hash < 0) {
      hash += 769;
    }
    return hash;
  }
}
