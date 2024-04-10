import java.util.Arrays;
import java.util.Random;

public class TicTacToeABC {
    private char[] board;
    private int populationSize;
    private int maxIterations;
    private char[][] solutions;
    private Random random;

    public TicTacToeABC() {
        this.board = new char[9];
        Arrays.fill(board, ' '); // Initialize empty board
        this.populationSize = 10;
        this.maxIterations = 100;
        this.solutions = new char[populationSize][9];
        this.random = new Random();
    }

    public void initializePopulation() {
        for (int i = 0; i < populationSize; i++) {
            solutions[i] = generateRandomMove();
        }
    }

    public void runAlgorithm() {
        initializePopulation();

        for (int iter = 0; iter < maxIterations; iter++) {
            double[] fitnessValues = new double[populationSize];
            for (int i = 0; i < populationSize; i++) {
                fitnessValues[i] = evaluateSolution(solutions[i]);
            }

            int bestSolutionIndex = 0;
            double bestFitness = fitnessValues[0];
            for (int i = 1; i < populationSize; i++) {
                if (fitnessValues[i] > bestFitness) {
                    bestSolutionIndex = i;
                    bestFitness = fitnessValues[i];
                }
            }

            System.out.println("Iteration: " + iter);
            System.out.println("Best Move:");
            printBoard(solutions[bestSolutionIndex]);
            System.out.println("Fitness: " + bestFitness);
            System.out.println("");

            solutions = generateNewSolutions(); // Generate new moves for next iteration
        }
    }

    private double evaluateSolution(char[] solution) {
        // For simplicity, we'll just return a random fitness value
        return random.nextDouble();
    }

    private char[] generateRandomMove() {
        char[] move = new char[9];
        for (int i = 0; i < 9; i++) {
            move[i] = (random.nextInt(3) == 0) ? 'X' : (random.nextInt(3) == 1) ? 'O' : ' ';
        }
        return move;
    }

    private char[][] generateNewSolutions() {
        char[][] newSolutions = new char[populationSize][9];
        for (int i = 0; i < populationSize; i++) {
            newSolutions[i] = generateRandomMove();
        }
        return newSolutions;
    }

    private void printBoard(char[] move) {
        for (int i = 0; i < 9; i++) {
            System.out.print(move[i]);
            if ((i + 1) % 3 == 0) {
                System.out.println();
            } else {
                System.out.print(" | ");
            }
        }
    }

    public static void main(String[] args) {
        TicTacToeABC abc = new TicTacToeABC();
        abc.runAlgorithm();
    }
}
