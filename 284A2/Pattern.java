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
    int countTimes = text.length() - patternLength + 1;
    // YOUR CODE GOES HERE.
    for (int i = 0; i < countTimes; i++) {
      if (text.substring(i, i + patternLength).equals(pattern)) {
        occurences++;
      }
    }

    System.out.println(occurences);
  }

  public static int calcHash(String text, int patternLength) {
    int hash = 0;
    for (int i = 0; i < patternLength; i++) {
      hash = 31 * hash + text.charAt(i);
    }
    return hash;
  }

  public static int recalcHash(String text, int patternLength, int oldHash, int oldChar, int newChar) {
    int hash = oldHash - oldChar;
    hash = hash / 31;
    hash = hash + newChar * (int) Math.pow(31, patternLength - 1);
    return hash;
  }
}
