import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by amir on 4/15/17.
 */
public class CostFunction {
    Map<Integer, List<Integer>> partitions;

    public CostFunction(int[] selection, Model model) {
        this.partitions = new HashMap<>();

        int crosscut = 0;
        for (int i = 0; i < model.getnMachines(); i++) {
            List<Integer> partitionTasks = find(selection, i);
            partitions.put(i, partitionTasks);
            int internal = InternalCommunication(partitionTasks, model.getAdjacency());
            int loadR1 = InternalLoad(partitionTasks, model.getR1());
            int loadR2 = InternalLoad(partitionTasks, model.getR2());
            crosscut += ExternalCommunication(partitionTasks, model.getAdjacency());
        }
    }

    private int InternalCommunication(List<Integer> partitionTasks, int[][] Adjacency) {
        int sum = 0;
//    ToDo: Can be More Optimized
        for (int i = 0; i < partitionTasks.size(); i++) {
            for (int j = i + 1; j < partitionTasks.size(); j++) {
                if (i < j) {
                    int from = partitionTasks.get(i);
                    int to = partitionTasks.get(j);
                    sum = sum + Adjacency[from][to];
                }
            }

        }
        return sum;
    }

    private int InternalLoad(List<Integer> partitionTasks, int[] Resource) {
        int load = 0;
        for (int i = 0; i < partitionTasks.size(); i++) {
            load += Resource[partitionTasks.get(i)];
        }
        return load;
    }

    private int ExternalCommunication(List<Integer> partitionTasks, int[][] Adjacency) {
        int crosscut = 0;
        //List<Integer> externalLinks = new ArrayList<>();
        //Map<Integer, Integer> nonZeros = new HashMap<>();
        for (int i = 0; i < partitionTasks.size(); i++) {
            int rowNumber = partitionTasks.get(i);
            int[] row = Adjacency[rowNumber];

            for (int j = 0; j < row.length; j++) {
                int currentElement = Adjacency[rowNumber][j];
                if (currentElement != 0 && (!partitionTasks.contains(j))) {
                    //externalLinks.add(currentElement);
                    crosscut += currentElement;
                }
            }
        }
        return crosscut;
    }

    //gets an array of ints
    //returns a collection containing nonzero elements with its position in the array(column number)
    private Map<Integer, Integer> nonZeros(int[] array) {
        Map<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0)
                result.put(array[i], i);
        }
        return result;
    }

    private List<Integer> find(int[] array, int value) {
        List<Integer> results = new ArrayList<Integer>();
        for (int i = 0; i < array.length; i++)
            if (array[i] == value)
                results.add(i);
        return results;
    }
}
