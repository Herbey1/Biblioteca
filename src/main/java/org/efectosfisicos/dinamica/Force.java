package org.efectosfisicos.dinamica;
import org.example.Vector2dDemo;
public class Force {
    public Vector2dDemo force;
    public double torque;
    public Force(Vector2dDemo force, double torque){
        this.force = force;
        this.torque = torque;
    }
    public Vector2dDemo getForce(){
        return force;
    }

    public double getTorque() {
        return torque;
    }
    //Aceleracion resultante basada en la masa de la particula
    public Vector2dDemo calculateAcceleration(double mass){
        // F = ma -> a = F/m
        return force.scale(1/mass);
    }
    //Aceleracion angular resultante
    public double calculateAngularAcceleration(double momentOfInertia){
        // Torque = I * alpha
        return torque / momentOfInertia;
    }
}
