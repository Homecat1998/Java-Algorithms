import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class SudokuSolver {

    int[][] sudoku = new int[9][9];

    boolean solved = false;




    public boolean safetyCheck(int[][] sudoku, int row, int col, int num){

        for (int j = 0; j < 9; j++) {
            if (sudoku[row][j] == num) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (sudoku [i][col] == num) {
                return false;
            }
        }

        int tmpRow = row / 3;
        int tmpCol = col / 3;
        for (int i = 0; i < 3; i ++){
            for (int j = 0; j < 3; j ++){
                if (sudoku[tmpRow * 3 + i][tmpCol * 3 + j] == num){
                    return false;
                }
            }
        }

        return true;
    }


    public void solve (int i, int j){

        if (i == 8 && j == 8){
            for (int attempt = 1; attempt <= 9; attempt++){
                if (safetyCheck(sudoku, i, j, attempt)){
                    sudoku[i][j] = attempt;
                    solved = true;
                    printArray(sudoku);
                }
            }

            return;
        }

        if (j == 9){
            i = i + 1;
            j = 0;
        }

        if (sudoku [i][j] == 0){
            for (int attempt = 1; attempt <= 9; attempt++){
                if (safetyCheck(sudoku, i, j, attempt)){
                    sudoku[i][j] = attempt;
                    solve(i, j + 1);
                    sudoku[i][j] = 0;
                }
            }
        } else {
            solve(i, j +1);
        }
    }

    public void printArray (int[][] sudoku){
        for (int i = 0; i < 9; i ++){
            for (int j = 0; j < 9; j ++){
                System.out.printf(sudoku[i][j] + " ");
            }
            System.out.printf("\n");
        }
        System.out.printf("\n");
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        SudokuSolver model = new SudokuSolver();


        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j++) {
                model.sudoku[i][j] = scanner.nextInt();
            }
        }

        System.out.println("\nSolving......\n");

        model.solve(0, 0);




        if (model.solved == true){
            System.out.println("Problem Solved!");
        } else {
            System.out.println("Cannot solve this problem!");
        }

    }
}
