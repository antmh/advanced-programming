package lab2.transportation.algorithm.vogel;

class MatrixCell {
    private int row;
    private int column;
    
    public MatrixCell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public final int getRow() {
        return row;
    }

    public final int getColumn() {
        return column;
    }
}
