package com.amirfirouzi;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * Created by amir on 4/17/17.
 */
public class LearnND4J {
  public static void main(String[] args) {
    INDArray nd = Nd4j.create(new float[]{1,2,3,4},new int[]{2,2});
    System.out.println(nd);
  }
}
