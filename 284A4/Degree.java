import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Degree {


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int order;
        while ((order = Integer.parseInt(reader.readLine().trim())) != 0) {
            int max = 0;
            List<int[]> adjList = new ArrayList<>();
            int[] emptyArray = new int[order];
            for (int i = 0; i < order; i++) {
                String[] line = reader.readLine().split(" ");
                int[] nums = new int[line.length];
                for (int j = 0; j < line.length; j++) {
                  if (line[j].trim().equals("") || line[j] == null) {
                    nums = new int[0];
                    break;
                  }
                  nums[j] = Integer.parseInt(line[j]);
                  emptyArray[i] = 0;
                }
                adjList.add(nums);
            }
            for (int i = 0; i < order; i++) {
                int[] colors = emptyArray.clone();
                int[] distance = emptyArray.clone();
                int length = getLength(adjList, i, colors, distance);
                if (length > max) {
                    max = length;
                }
            }
            System.out.println(max);
        }
    }

    public static int getLength(List<int[]> adjList, int index, int[] colors, int[] distance) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(index);
        colors[index] = 1;
        distance[index] = 0;
        int length = 0;
        while (!queue.isEmpty()) {
            index = queue.poll();
            int[] neighbors = adjList.get(index);
            for(int node : neighbors){
                if (colors[node] == 0) {
                    colors[node] = 1;
                    distance[node] = distance[index] + 1;
                    queue.add(node);
                    if (distance[node] > length) {
                        length = distance[node];
                    }
                }
            }
        }
        return length;
    }
}