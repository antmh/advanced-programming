package lab2.transportation.algorithm.vogel;

import java.util.ArrayList;

/**
 * This matrix is used in the Vogel algorithm to calculate the row and column
 * differences and to cancel rows and columns.
 */
class VogelMatrix {
    private ArrayList<ArrayList<Integer>> costMatrix;
    private boolean[] canceledRows;
    private boolean[] canceledColumns;

    public VogelMatrix(ArrayList<ArrayList<Integer>> costMatrix) {
        this.costMatrix = costMatrix;
        canceledRows = new boolean[costMatrix.size()];
        canceledColumns = new boolean[costMatrix.get(0).size()];
    }

    public MatrixCell getNextCell() {
        MatrixCell availableCell = null;
        int availableCellsNo = 0;
        for (int row = 0; row < costMatrix.size(); ++row) {
            for (int column = 0; column < costMatrix.get(0).size(); ++column) {
                if (!canceledRows[row] && !canceledColumns[column]) {
                    availableCell = new MatrixCell(row, column);
                    ++availableCellsNo;
                    if (availableCellsNo > 1) {
                        break;
                    }
                }
            }
        }
        if (availableCellsNo == 1) {
            return availableCell;
        }
        ArrayList<Integer> columnDifferences = getColumnDifferences();
        ArrayList<Integer> rowDifferences = getRowDifferences();

        System.out.println("Column differences: " + columnDifferences);
        System.out.println("Row differences: " + rowDifferences);

        int maximumDifferenceIndex = -1;
        int maximumValue = -1;

        boolean isColumnIndex = false;
        for (int column = 0; column < columnDifferences.size(); ++column) {
            int columnDifference = columnDifferences.get(column);
            if (maximumValue < columnDifference) {
                maximumDifferenceIndex = column;
                maximumValue = columnDifference;
                isColumnIndex = true;
            }
        }

        for (int row = 0; row < rowDifferences.size(); ++row) {
            int rowDifference = rowDifferences.get(row);
            if (maximumValue < rowDifference) {
                maximumDifferenceIndex = row;
                maximumValue = rowDifference;
                isColumnIndex = false;
            }
        }

        System.out.println("Maximum value: " + maximumValue);

        int selectedColumn;
        int selectedRow;
        if (isColumnIndex) {
            selectedColumn = maximumDifferenceIndex;
            selectedRow = getMinColumnIndex(selectedColumn);
        } else {
            selectedRow = maximumDifferenceIndex;
            selectedColumn = getMinRowIndex(selectedRow);
        }
        return new MatrixCell(selectedRow, selectedColumn);
    }

    /**
     * The Vogel matrix has area available for calculating row differences and
     * columns differences only when there are at least two columns and two rows.
     */
    public boolean hasAreaAvailable() {
        int canceledRowsNo = 0;
        for (boolean isCanceled : canceledRows) {
            if (isCanceled) {
                ++canceledRowsNo;
            }
        }
        if (canceledRows.length - canceledRowsNo < 2) {
            return false;
        }

        int canceledColumnsNo = 0;
        for (boolean isCanceled : canceledColumns) {
            if (isCanceled) {
                ++canceledColumnsNo;
            }
        }
        if (canceledColumns.length - canceledColumnsNo < 2) {
            return false;
        }

        return true;
    }

    public void cancelRow(int row) {
        canceledRows[row] = true;
    }

    public void cancelColumn(int column) {
        canceledColumns[column] = true;
    }

    private int getMinColumnIndex(int column) {
        int minIndex = -1;
        for (int row = 0; row < costMatrix.size(); ++row) {
            if (!canceledRows[row]
                    && (minIndex == -1 || costMatrix.get(minIndex).get(column) > costMatrix.get(row).get(column))) {
                minIndex = row;
            }
        }
        return minIndex;
    }

    private int getMinRowIndex(int row) {
        int minIndex = -1;
        System.out.println("getMinRowIndex");
        for (int column = 0; column < costMatrix.get(0).size(); ++column) {
            System.out.println(costMatrix.get(row).get(column));
            if (!canceledColumns[column]
                    && (minIndex == -1 || costMatrix.get(row).get(minIndex) > costMatrix.get(row).get(column))) {
                System.out.println("Set: " + costMatrix.get(row).get(column));
                minIndex = column;
            }
        }
        return minIndex;
    }

    private ArrayList<Integer> getColumnDifferences() {
        ArrayList<Integer> columnDifferences = new ArrayList<>();

        for (int column = 0; column < costMatrix.get(0).size(); ++column) {
            System.out.println("Get column difference for column " + column);
            if (canceledColumns[column]) {
                columnDifferences.add(-1);
            } else {
                int columnDifference = getColumnDifference(column);
                columnDifferences.add(columnDifference);
            }
        }

        return columnDifferences;
    }

    private int getColumnDifference(int column) {
        int leastValue = -1;
        int secondLeastValue = -1;
        for (int row = 0; row < costMatrix.size(); ++row) {
            if (!canceledRows[row]) {
                if (leastValue == -1 || costMatrix.get(row).get(column) < leastValue) {
                    secondLeastValue = leastValue;
                    leastValue = costMatrix.get(row).get(column);
                } else if (secondLeastValue == -1 || costMatrix.get(row).get(column) < secondLeastValue) {
                    System.out.println("Second least value " + row + ", " + column);
                    secondLeastValue = costMatrix.get(row).get(column);
                }
            }
        }

        System.out.println("Second least: " + secondLeastValue + ", least: " + leastValue);
        return secondLeastValue - leastValue;
    }

    private ArrayList<Integer> getRowDifferences() {
        ArrayList<Integer> rowDifferences = new ArrayList<>();

        for (int row = 0; row < costMatrix.size(); ++row) {
            if (canceledRows[row]) {
                rowDifferences.add(-1);
            } else {
                int rowDifference = getRowDifference(row);
                rowDifferences.add(rowDifference);
            }
        }

        return rowDifferences;
    }

    private int getRowDifference(int row) {
        int leastValue = -1;
        int secondLeastValue = -1;
        for (int column = 0; column < costMatrix.get(0).size(); ++column) {
            if (!canceledColumns[column]) {
                if (leastValue == -1 || costMatrix.get(row).get(column) < leastValue) {
                    secondLeastValue = leastValue;
                    leastValue = costMatrix.get(row).get(column);
                } else if (secondLeastValue == -1 || costMatrix.get(row).get(column) < secondLeastValue) {
                    secondLeastValue = costMatrix.get(row).get(column);
                }
            }
        }

        return secondLeastValue - leastValue;
    }
}
