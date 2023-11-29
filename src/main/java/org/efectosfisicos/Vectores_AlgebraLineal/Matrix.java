package org.efectosfisicos.Vectores_AlgebraLineal;
import java.util.Arrays;
public class Matrix {
    private final int rows;
    private final int columns;
    private final double[][] data;

    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.data = new double[rows][columns];
    }

    public Matrix(double[][] data) {
        this.rows = data.length;
        this.columns = data[0].length;
        this.data = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(data[i], 0, this.data[i], 0, columns);
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public double get(int row, int column) {
        return data[row][column];
    }

    public void set(int row, int column, double value) {
        data[row][column] = value;
    }

    public Matrix add(Matrix other) {
        if (this.rows != other.rows || this.columns != other.columns) {
            throw new IllegalArgumentException("Las dimensiones de las matrices deben de ser las mismas para la suma.");
        }

        Matrix result = new Matrix(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result.set(i, j, this.get(i, j) + other.get(i, j));
            }
        }
        return result;
    }

    public Matrix subtract(Matrix other) {
        if (this.rows != other.rows || this.columns != other.columns) {
            throw new IllegalArgumentException("Las dimensiones de las matrices deben de ser las mismas para la resta.");
        }

        Matrix result = new Matrix(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result.set(i, j, this.get(i, j) - other.get(i, j));
            }
        }
        return result;
    }

    public Matrix multiplyScalar(double scalar) {
        Matrix result = new Matrix(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result.set(i, j, this.get(i, j) * scalar);
            }
        }
        return result;
    }

    public Matrix multiply(Matrix other) {
        if (this.columns != other.rows) {
            throw new IllegalArgumentException("El numero de columnas de A, debe de ser igual al numero de renglones de B para la multiplicación.");
        }

        Matrix result = new Matrix(this.rows, other.columns);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.columns; j++) {
                double sum = 0;
                for (int k = 0; k < this.columns; k++) {
                    sum += this.get(i, k) * other.get(k, j);
                }
                result.set(i, j, sum);
            }
        }
        return result;
    }

    public Matrix transpose() {
        Matrix result = new Matrix(this.columns, this.rows);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                result.set(j, i, this.get(i, j));
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder matrixString = new StringBuilder();
        for (double[] row : data) {
            matrixString.append(Arrays.toString(row)).append("\n");
        }
        return matrixString.toString();
    }

    public static void main(String[] args) {
        double[][] dataA = {{1, 2, 3}, {4, 5, 6}};
        double[][] dataB = {{7, 8, 5}, {9, 10, 4},{1, 2, 9}};

        Matrix matrixA = new Matrix(dataA);
        Matrix matrixB = new Matrix(dataB);

        System.out.println("Matriz A:");
        System.out.println(matrixA);

        System.out.println("Matriz B:");
        System.out.println(matrixB);

        //Operaciones con matrices
        Matrix sum = matrixA.add(matrixB);
        /*Matrix difference = matrixA.subtract(matrixB);
        Matrix scalarProduct = matrixA.multiplyScalar(2);
        Matrix product = matrixA.multiply(matrixB);
        Matrix transposedA = matrixA.transpose();*/

        System.out.println("Suma de A y B:");
        System.out.println(sum);

        /*System.out.println("Difference of A and B:");
        //System.out.println(difference);

        System.out.println("Scalar product of A (multiplied by 2):");
        System.out.println(scalarProduct);

        System.out.println("Product of A and B:");
        System.out.println(product);

        System.out.println("Transpose of A:");
        System.out.println(transposedA);*/
    }
}
