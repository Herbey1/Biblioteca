package org.efectosfisicos.cinematica;
import org.example.Vector2dDemo;
import org.efectosfisicos.dinamica.Force;
public class MotionSimulator {
    public static void applyForce(Particle particle, Force force) {
        particle.applyForce(force);
    }

    public static void simulateMotion(Particle particle, double totalTime, double timeStep) {
        int steps = (int) (totalTime / timeStep);

        for (int i = 0; i < steps; i++) {
            particle.update(timeStep);
            System.out.println("Paso " + (i + 1) + ": Posicion = " + particle.getPosition() +
                    ", Velocidad = " + particle.getVelocity());
        }
    }

    public static void main(String[] args) {
        // Crear una partícula de ejemplo
        Particle particle = new Particle(1.0, new Vector2dDemo(0, 0), new Vector2dDemo(2, 3), 180);

        System.out.println("Estado inicial de la particula:");
        System.out.println(particle);

        // Aplicar una fuerza constante en el eje X
        Force constantForce = new Force(new Vector2dDemo(1,0),0.0);
        applyForce(particle, constantForce);

        // Simular el movimiento durante 5 segundos con pasos de 0.1 segundos
        simulateMotion(particle, 5.0, 0.1);

        System.out.println("\nEstado de la particula despues de la simulacion:");
        System.out.println(particle);
    }
}