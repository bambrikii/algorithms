package org.bambrikii.examples.linear;

/**
 * https://www.youtube.com/watch?v=rzRZLGD_aeE&ab_channel=MeghanDeWitt
 */
public class SimplexAlgo {
    public double[] calc(double[][] input) {
        int rows = input.length;
        int cols = input[0].length;

        int arrCols = cols + rows;
        int objectFunctionRow = rows - 1;

        double[][] arr = prepareWorkingArray(input, rows, cols, arrCols);

        while (true) {
            double[] objectFunctionArray = arr[objectFunctionRow];

            int pivotCol = findPivotCol(objectFunctionArray);
            if (pivotCol == -1) {
                return extractResults(rows, arrCols, arr);
            }

            int pivotRow = findPivotRow(arr, pivotCol);
            if (pivotRow == -1) {
                break;
            }

            updatePivotWithPivotCellToOne(arr, pivotRow, pivotCol);

            relaxAllRows(arr, pivotRow, pivotCol);
        }

        return null;
    }

    private double[][] prepareWorkingArray(double[][] input, int rows, int cols, int arrCols) {
        double[][] arr = new double[rows][arrCols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols - 1; j++) {
                arr[i][j] = input[i][j];
            }
            arr[i][arrCols - 1] = input[i][cols - 1];
            arr[i][rows - 1 + i] = 1;
        }
        return arr;
    }

    // Update all rows except pivot row to have "0" in pivot column
    private void relaxAllRows(double[][] arr, int pivotRow, int pivotCol) {
        int rows = arr.length;
        int arrCols = arr[0].length;
        for (int i = 0; i < rows; i++) {
            if (i == pivotRow) {
                continue;
            }
            double div = arr[i][pivotCol];
            for (int j = 0; j < arrCols; j++) {
                arr[i][j] = arr[i][j] - arr[pivotRow][j] * div;
            }
        }
    }

    // find min divisor
    private int findPivotRow(double[][] arr, int pivotCol) {
        int rows = arr.length;
        int arrCols = arr[0].length;
        int pivotRow = -1;
        double pivotVal = 0;
        for (int i = 0; i < rows - 1; i++) {
            double pivotVal2 = arr[i][arrCols - 1] / arr[i][pivotCol];
            if (pivotRow == -1) {
                pivotRow = i;
                pivotVal = pivotVal2;
            } else if (pivotVal2 < pivotVal) {
                pivotRow = i;
                pivotVal = pivotVal2;
            }
        }
        //
        return pivotRow;
    }

    private void updatePivotWithPivotCellToOne(double[][] arr, int pivotRow, int pivotCol) {
        int arrCols = arr[0].length;
        // Update pivot row to have value "1" in pivot cell
        double pivotVal2 = arr[pivotRow][pivotCol];
        for (int j = 0; j < arrCols; j++) {
            arr[pivotRow][j] = arr[pivotRow][j] / pivotVal2;
        }
    }

    // Should return solution
    private double[] extractResults(int rows, int arrCols, double[][] arr) {
        double[] results = new double[rows];
        for (int i = 0; i < rows; i++) {
            results[i] = arr[i][arrCols - 1];
        }
        return results;
    }

    // find first negative coefficient of object function;
    private int findPivotCol(double[] objectFunctionArray) {
        int pivotCol = -1;
        for (int j = 0; j < objectFunctionArray.length - 1; j++) {
            if (objectFunctionArray[j] < 0) {
                if (pivotCol == -1) {
                    pivotCol = j;
                } else if (objectFunctionArray[j] < objectFunctionArray[pivotCol]) {
                    pivotCol = j;
                }
            }
        }
        return pivotCol;
    }
}
