package com.amirfirouzi.models;

/**
 * Created by amir on 4/19/17.
 */
public interface Model {
  int getnTasks();

  int getnMachines();

  int[] getR1();

  int[] getR2();

  int[] getM1();

  int[] getM2();

  int[][] getAdjacency();
}
