package org.example;
import org.efectosfisicos.Vectores_AlgebraLineal.Matrix;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MatrixDemo extends JFrame {
    private Matrix matrixA;
    private Matrix matrixB;
    private JTextArea resultTextArea;

    public MatrixDemo() {
        setTitle("Operaciones con matrices");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear matrices de ejemplo
        double[][] dataA = {{1, 2, 3}, {4, 5, 6}};
        double[][] dataB = {{7, 8, 6}, {9, 10, 7}};

        matrixA = new Matrix(dataA);
        matrixB = new Matrix(dataB);

        // Crear panel para mostrar matrices
        JPanel matrixPanel = new JPanel();
        matrixPanel.setLayout(new GridLayout(1, 3));

        JTextArea matrixATextArea = createMatrixTextArea(matrixA);
        JTextArea matrixBTextArea = createMatrixTextArea(matrixB);

        matrixPanel.add(matrixATextArea);
        matrixPanel.add(new JLabel("  +  "));
        matrixPanel.add(matrixBTextArea);

        // Crear panel para mostrar resultado y botón para calcular suma
        JPanel resultPanel = new JPanel();
        resultTextArea = new JTextArea(10, 20);
        resultPanel.add(new JScrollPane(resultTextArea));

        JButton addButton = new JButton("Calcular suma");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateSum();
            }
        });
        resultPanel.add(addButton);

        // Crear contenedor principal
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(matrixPanel, BorderLayout.NORTH);
        container.add(resultPanel, BorderLayout.CENTER);
    }

    private JTextArea createMatrixTextArea(Matrix matrix) {
        JTextArea textArea = new JTextArea(3, 6);
        textArea.setEditable(false);
        textArea.setText(matrix.toString());
        return textArea;
    }

    private void calculateSum() {
        Matrix sum = matrixA.add(matrixB);
        resultTextArea.setText("Suma de A y B:\n" + sum.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MatrixDemo().setVisible(true);
            }
        });
    }
}