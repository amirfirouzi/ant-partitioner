/**
 * Created by amir on 4/15/17.
 */
public class Program {
  public static void main(String[] args) {
    Model model = new Model(5, 3);

    CostFunction cost = new CostFunction(new int[]{0, 1, 0, 1, 0}, model);
  }
}
