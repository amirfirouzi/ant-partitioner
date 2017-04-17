package com.amirfirouzi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by amir on 4/15/17.
 */
public class Program {
  public static void main(String[] args) {
    Model model = new Model(5, 3);


    //region ACO Parameters
    int maxIt = 500;      // Maximum Number of Iterations
    int nAnt = 50;        // Number of Ants (Population Size)
    int Q = 1;

    int tau0 = 1;         // Initial Pheromone
    int alpha = 1;        // Pheromone Exponential Weight
    float beta = 0.2f;       // Heuristic Exponential Weight
    float rho = 0.15f;       // Evaporation Rate
    //endregion


    //region Initialization
    //ToDo: Heuristic Information
    // eta
    float[][] tau = new float[model.getnMachines()][model.getnTasks()];
    fillArray(tau, 1);
    float[] bestCosts = new float[maxIt];
    Map<Integer, Map<String, String>> Ants = new HashMap();
    //endregion


    //region ACO Main Loop

    // iteration loop
    for (int it = 0; it < maxIt; it++) {
      // Move Ants
      for (int ant = 0; ant < nAnt; ant++) {
        //ToDo: empty current ant's Tour
        for (int level = 0; level < model.getnTasks(); level++) {
          // Probabilities
          //tau[level]
        }

      }
    }
    CostFunction costObject = new CostFunction(model);

    float cost = costObject.CalculateCost(new int[]{0, 1, 0, 1, 0});

    int[] selection = new int[]{0, 1, 0, 1, 0};
    //endregion


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
}
