package org.example;

import java.util.Objects;
import javax.swing.*;
import java.awt.*;

public class Vector2dDemo {
    public double x;
    public double y;

    public Vector2dDemo(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2dDemo() {
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Vector2dDemo add(Vector2dDemo other) {
        return new Vector2dDemo(this.x + other.x, this.y + other.y);
    }

    public Vector2dDemo subtract(Vector2dDemo other) {
        return new Vector2dDemo(this.x - other.x, this.y - other.y);
    }

    public Vector2dDemo scale(double scalar) {
        return new Vector2dDemo(this.x * scalar, this.y * scalar);
    }

    public double dotProduct(Vector2dDemo other) {
        return this.x * other.x + this.y * other.y;
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public Vector2dDemo normalize() {
        double magnitude = magnitude();
        if (magnitude != 0) {
            return new Vector2dDemo(x / magnitude, y / magnitude);
        } else {
            return new Vector2dDemo(0, 0); // Avoid division by zero
        }
    }

    public double angleWith(Vector2dDemo other) {
        double dotProduct = dotProduct(other);
        double magnitudesProduct = magnitude() * other.magnitude();

        if (magnitudesProduct == 0) {
            throw new ArithmeticException("Cannot compute angle with zero magnitude vectors");
        }

        double cosTheta = dotProduct / magnitudesProduct;
        return Math.toDegrees(Math.acos(cosTheta));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vector2dDemo vector2D = (Vector2dDemo) obj;
        return Double.compare(vector2D.x, x) == 0 &&
                Double.compare(vector2D.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
    public void displayVector() {
        JFrame frame = new JFrame("Vector 2D");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 400);
        frame.getContentPane().add(new VectorPanel());
        frame.setVisible(true);
    }
    private class VectorPanel extends JPanel{
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            int centerX = getWidth()/2;
            int centerY = getHeight()/2;
            int vectorScale = 20; //Ajustar
            int endX = (int)(centerX+x*vectorScale);
            int endY = (int)(centerY-y*vectorScale);
            g2d.setColor(Color.BLUE);
            g2d.drawLine(centerX, centerY, endX, endY);
            g2d.setColor(Color.RED);
            g2d.fillOval(centerX - 4, centerY - 4, 8, 8); //Origen del vector
            g2d.setColor(Color.BLACK);
            g2d.drawString("("+x+", "+")",endX, endY);
        }
    }

    public static void main(String[] args) {
        Vector2dDemo vectorA = new Vector2dDemo(2, 3);
        Vector2dDemo vectorB = new Vector2dDemo(-1, 4);

        System.out.println("Vector A: " + vectorA);
        System.out.println("Vector B: " + vectorB);

        Vector2dDemo sum = vectorA.add(vectorB);
        Vector2dDemo difference = vectorA.subtract(vectorB);
        Vector2dDemo scaledVector = vectorA.scale(1.5);
        double dotProduct = vectorA.dotProduct(vectorB);
        double magnitude = vectorA.magnitude();
        Vector2dDemo normalizedVector = vectorA.normalize();
        double angle = vectorA.angleWith(vectorB);

        System.out.println("Suma: " + sum);
        System.out.println("Diferencia: " + difference);
        System.out.println("Scaled Vector: " + scaledVector);
        System.out.println("Producto punto: " + dotProduct);
        System.out.println("Magnitud del Vector A: " + magnitude);
        System.out.println("Normalized Vector A: " + normalizedVector);
        System.out.println("Angulo entre Vector A y Vector B: " + angle + " grados");

        // Testing equality
        Vector2dDemo vectorC = new Vector2dDemo(2, 3);
        System.out.println("Vector A es igual a Vector C: " + vectorA.equals(vectorC));
        //Interfaz
        vectorA.displayVector();
    }
}
