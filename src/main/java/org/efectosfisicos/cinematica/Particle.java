package org.efectosfisicos.cinematica;
import org.example.Vector2dDemo;
public class Particle {

    private double mass;
    private Vector2dDemo position;
    private Vector2dDemo velocity;
    private Vector2dDemo acceleration;

    public Particle(double mass, Vector2dDemo position, Vector2dDemo velocity) {
        this.mass = mass;
        this.position = position;
        this.velocity = velocity;
        this.acceleration = new Vector2dDemo(0, 0);
    }

    public double getMass() {
        return mass;
    }

    public Vector2dDemo getPosition() {
        return position;
    }

    public Vector2dDemo getVelocity() {
        return velocity;
    }

    public Vector2dDemo getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector2dDemo acceleration) {
        this.acceleration = acceleration;
    }
    public void applyForce(Vector2dDemo force){
        // F = ma -> a = F/m
        Vector2dDemo newAcceleration = force.scale(1/mass);
        acceleration = acceleration.add(newAcceleration);
    }
    public void update(double time) {
        // Actualizar velocidad y posición usando las ecuaciones de movimiento
        double newX = position.getX() + velocity.getX() * time + 0.5 * acceleration.getX() * time * time;
        double newY = position.getY() + velocity.getY() * time + 0.5 * acceleration.getY() * time * time;

        double newVx = velocity.getX() + acceleration.getX() * time;
        double newVy = velocity.getY() + acceleration.getY() * time;

        position = new Vector2dDemo(newX, newY);
        velocity = new Vector2dDemo(newVx, newVy);
    }

    @Override
    public String toString() {
        return "Particula{" +
                "masa=" + mass +
                ", posicion=" + position +
                ", velocidad=" + velocity +
                ", aceleracion=" + acceleration +
                '}';
    }

    public static void main(String[] args) {
        // Crear una partícula de ejemplo
        Particle particle = new Particle(1.0, new Vector2dDemo(0, 0), new Vector2dDemo(2, 3));

        System.out.println("Estado inicial de la particula:");
        System.out.println(particle);

        // Aplicar una aceleración
        Vector2dDemo acceleration = new Vector2dDemo(1, 1);
        particle.setAcceleration(acceleration);

        // Actualizar la partícula después de un intervalo de tiempo
        double timeInterval = 1.0;
        particle.update(timeInterval);

        System.out.println("\nEstado de la particula despues de la simulacion:");
        System.out.println(particle);
    }
}