package org.efectosfisicos.cinematica;
import org.example.Vector2dDemo;
import org.efectosfisicos.dinamica.Force;
public class Particle {
    private double mass;
    private Vector2dDemo position;
    private Vector2dDemo velocity;
    private Vector2dDemo acceleration;
    private double orientation; //radianes

    public Particle(double mass, Vector2dDemo position, Vector2dDemo velocity, double orientation) {
        this.mass = mass;
        this.position = position;
        this.velocity = velocity;
        this.acceleration = new Vector2dDemo(0, 0);
        this.orientation = orientation;
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
    public double getOrientation(){
        return orientation;
    }

    public void setAcceleration(Vector2dDemo acceleration) {
        this.acceleration = acceleration;
    }
    public void applyForce(Force appliedForce){
        Vector2dDemo force = appliedForce.getForce();
        double torque = appliedForce.getTorque();
        // F = ma -> a = F/m
        Vector2dDemo newAcceleration = force.scale(1/mass);
        acceleration = acceleration.add(newAcceleration);

        //Torque = I * alpha
        double momentOfInertia = 0.5 * mass * mass;
        double angularAcceleration = torque / momentOfInertia;
        orientation += angularAcceleration;
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
}