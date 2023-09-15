import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Scanner;

 
public class LowHi {
	
	public static void main(String[] args) {
    FastScanner in = new FastScanner();
 		PrintWriter out = new PrintWriter(System.out);

		int n = in.nextInt();
    int a[] = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = in.nextInt();
    }
    int m = in.nextInt();
    for (int i = 0; i < m; i++) {
      int low = in.nextInt();
      int hi = in.nextInt();

      out.println(count_between(a, low, hi, n));
    }
    out.close();
	}

  public static int count_between(int a[], int lo, int hi, int n) {
    int  answer = 0;
    int  loIndex = -1;
    int  hiIndex = -1;
    int currentIndex = n/2;
    int topIndex = n - 1;
    int bottomIndex = 0;
    if (a[n - 1] < lo || a[0] > hi){
      return 0;
    }
    while(true){
      if(currentIndex == 0 & a[0] >= lo){
        loIndex = 0;
        break;
      }
      if(a[currentIndex] >= lo & a[currentIndex - 1] < lo){
        loIndex = currentIndex;
        break;
      }
      if(a[currentIndex] < lo){
        bottomIndex = currentIndex;
        currentIndex = (topIndex + bottomIndex + 1) / 2;
      }else{
        topIndex = currentIndex;
        currentIndex = (topIndex + bottomIndex) / 2;
      }
    }
    currentIndex = n/2;
    topIndex = n - 1;
    bottomIndex = 0;
    while(true){
      if(currentIndex == n - 1 & a[n - 1] <= hi){
        hiIndex = n - 1;
        break;
      }
      if(a[currentIndex] <= hi & a[currentIndex + 1] > hi){
        hiIndex = currentIndex;
        break;
      }
      if(a[currentIndex] <= hi){
        bottomIndex = currentIndex;
        currentIndex = (topIndex + bottomIndex + 1) / 2;
      }else{
        topIndex = currentIndex;
        currentIndex = (topIndex + bottomIndex) / 2;
      }
    }
    answer = hiIndex - loIndex + 1;
    return answer;

  }




  static class FastScanner {
      BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st=new StringTokenizer("");

      String next() {
        while (!st.hasMoreTokens())
          try { 
            st=new StringTokenizer(br.readLine());				              
          } catch (IOException e) {}
        return st.nextToken();
      }
      
      int nextInt() {
        return Integer.parseInt(next());
      }
  }
}

//      long endTime = System.nanoTime();
//      System.out.println("Took " + (endTime - startTime) / 1000000.0 + " ms");