package com.amirfirouzi;

import com.amirfirouzi.models.*;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.ops.transforms.Transforms;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by amir on 4/15/17.
 */
public class Program {
  public static void main(String[] args) {
    Model4 model = new Model4(8, 3);
    CostFunction.costMode mode= CostFunction.costMode.BestCut;

    //region ACO Parameters
    int maxIt = 100;      // Maximum Number of Iterations
    int nAnt = 50;        // Number of Ants (Population Size)
    int Q = 1;

    int tau0 = 1;         // Initial Pheromone
    float alpha = 1;        // Pheromone Exponential Weight
    float beta = 0.2f;       // Heuristic Exponential Weight
    float rho = 0.05f;       // Evaporation Rate
    //endregion


    //region Initialization
    //ToDo: Heuristic Information
    // eta
//    float[][] tau = new float[model.getnMachines()][model.getnTasks()];
    INDArray tau = Nd4j.ones(model.getnMachines(), model.getnTasks());
//    fillArray(tau, 1);
    float[] bestCosts = new float[maxIt];
    String[] bestSelections = new String[maxIt];

    float bestCost = -1;
    int bestAnt = -1;
    int[] ants = new int[nAnt];
    float[] antCosts = new float[nAnt];
    int[][] antSelections = new int[nAnt][model.getnTasks()];
    CostFunction costObject = new CostFunction(model);

    //endregion


    //region ACO Main Loop

    // iteration loop
    for (int it = 0; it < maxIt; it++) {
      // Move Ants
      bestCost = -1;
      bestAnt = -1;
      for (int ant = 0; ant < nAnt; ant++) {
        //ToDo: empty current ant's Selection
        antSelections[ant] = new int[model.getnTasks()];
        for (int level = 0; level < model.getnTasks(); level++) {
          // Probabilities
          INDArray p = Transforms.pow(tau.getColumn(level), alpha);
          p = p.div(p.sumNumber());

          int selectionCandidate = RouletteWheelSelection(p);
          antSelections[ant][level] = selectionCandidate;
        }//end level Loop

        // Calculate the cost
        float cost = costObject.CalculateCost(antSelections[ant], mode);

        // Update the records if it improves the solution
        if (bestCost == -1)
          bestCost = cost;

        if (bestAnt == -1)
          bestAnt = ant;
        antCosts[ant] = cost;
        if (cost <= bestCost) {
          bestAnt = ant;
          bestCost = cost;
        }

      }//end Ant Loop

      // Update Phromones
      // Move Ants
      for (int ant = 0; ant < nAnt; ant++) {
        for (int level = 0; level < model.getnTasks(); level++) {
          double pheremone = tau.getRow(antSelections[ant][level]).getDouble(level) + (Q / antCosts[ant]);
          tau.putScalar(antSelections[ant][level], level, pheremone);
        }
      }

      // Evaporation
      tau.muli((1 - rho));

      // Store Best Cost & Best Selectin
      bestCosts[it] = bestCost;
      bestSelections[it] = arrayToString(antSelections[bestAnt]);

      // Show Iteration Information
      System.out.println("Iteration: " + it + " Selection= " + bestSelections[it] + " Cost= " + bestCosts[it]);

    }//end Iteration
    System.out.println("\n-------------\n");

    int index = findLatestMinimum(bestCosts);
    System.out.println("Best Answer:\n Iteration: " + index + " Selection= " + bestSelections[index] + " Cost= " + bestCosts[index]);
//    int[] selection = new int[]{0, 1, 0, 1, 0};
    //endregion

  }

  public static int RouletteWheelSelection(INDArray p) {
    Random rand = new Random();

    float n = rand.nextFloat();
    INDArray c = p.cumsum(0);
    int result = p.length() - 1;
    for (int i = 0; i < p.length(); i++) {
      if (c.getFloat(i) >= n) {
        result = i;
        break;
      }
    }
    return result;
  }

  public static String arrayToString(int[] array) {
    String str = "";
    for (int i = 0; i < array.length; i++) {
      str += array[i] + ",";
    }
    if (str != "")
      str = str.substring(0, str.length() - 1);
    return str;
  }

  public static int[] stringToArray(String str) {
    String[] strArray = str.split(",");
    int[] intArray = new int[strArray.length];
    for (int i = 0; i < strArray.length; i++) {
      intArray[i] = Integer.parseInt(strArray[i]);
    }
    return intArray;
  }

  public static void fillArray(float[][] array, float value) {
    for (int i = 0; i < array.length; i++) {
      Arrays.fill(array[i], value);
    }
  }

  public static int findLatestMinimum(float[] array) {
    float min = -1;
    int index = -1;
    for (int i = array.length - 1; i >= 0; i--) {
      if (array[i] < min || (min == -1 && index == -1)) {
        min = array[i];
        index = i;
      }
    }
    return index;
  }
}
