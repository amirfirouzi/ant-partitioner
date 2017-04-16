/**
 * Created by amir on 4/15/17.
 */
public class Program {
  public static void main(String[] args) {
    Model model = new Model(5, 3);


    //ACO Parameters
    int maxIt = 500;      // Maximum Number of Iterations
    int nAnt = 50;        // Number of Ants (Population Size)
    int Q = 1;

    int tau0 = 1;         // Initial Pheromone
    int alpha = 1;        // Pheromone Exponential Weight
    float beta = 0.2f;       // Heuristic Exponential Weight
    float rho = 0.15f;       // Evaporation Rate

    // Initialization
    //ToDo: Heuristic Information
    //eta

    float[][] tau=new float[model.getnMachines()][model.getnTasks()];


    CostFunction costObject = new CostFunction(model);
    float cost = costObject.CalculateCost(new int[]{0, 1, 0, 1, 0});
  }
}
