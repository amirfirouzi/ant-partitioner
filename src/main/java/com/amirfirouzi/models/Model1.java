package com.amirfirouzi;

/**
 * Created by amir on 4/15/17.
 */
public class Model1 implements Model{
  int nTasks;
  int nMachines;

  int[] R1;
  int[] R2;

  int[] M1;
  int[] M2;

  int[][] Adjacency;

  public Model1(int nTasks, int nMachines) {
    this.nTasks = nTasks;
    this.nMachines = nMachines;

//    Resource Demand
//    ===============
//    Requested Resources(type 1:CPU) by tasks
//    R1=randi([50 500],1,nTasks);
    R1 = new int[]{120, 100, 60, 120, 200};

//    Requested Resources(type 2:RAM) by tasks
//    R2=randi([200 2000],1,nTasks);
    R2 = new int[]{1000, 1500, 2000, 250, 1000};

//    Resource Pool
//    ===============
//    Available Resources(type 1) by Machines
//    M1=randi([50 1000],1,nMachines);
    M1 = new int[]{450, 200, 300};

//    Available Resources(type 2) by Machines
//    M2=randi([200 4000],1,nMachines);
    M2 = new int[]{4500, 800, 2000};

//    Adjacency Matrix containing communicational links with weights
    Adjacency = new int[nTasks][nTasks];
    Adjacency[0][2] = 10;
    Adjacency[0][3] = 7;
    Adjacency[1][3] = 12;
    //
    //Adjacency[1][2] = 13;
    Adjacency[2][4] = 20;
    Adjacency[3][4] = 15;
  }

  public int getnTasks() {
    return nTasks;
  }

  public int getnMachines() {
    return nMachines;
  }

  public int[] getR1() {
    return R1;
  }

  public int[] getR2() {
    return R2;
  }

  public int[] getM1() {
    return M1;
  }

  public int[] getM2() {
    return M2;
  }

  public int[][] getAdjacency() {
    return Adjacency;
  }
}
