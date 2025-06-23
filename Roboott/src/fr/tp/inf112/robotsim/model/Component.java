package fr.tp.inf112.robotsim.model;

import fr.tp.inf112.projects.canvas.model.Figure;
import fr.tp.inf112.robotsim.factorypathfinder.Position;

import java.io.Serializable;

public abstract class Component implements Figure, Serializable {
    protected String name;
    protected Position position;
    protected final int width;
    protected final int height;
    protected final Factory factory; 

    public Component(String name, Position position, int width, int height,Factory factory) {
        this.name = name;
        this.position = position;
        this.width = width;
        this.height = height;
        this.factory=factory;
    }

//Getters and Setters

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name=name;
        this.factory.notifyObservers();
    }

    public int getxCoordinate(){
        return this.position.getX();
    }

    public void setxCoordinate(int x){
       
        this.position.setX(x);
        this.factory.notifyObservers();
    }
    
    public int getyCoordinate(){
        return this.position.getY();
    }

    public void setyCoordinate(int y){
        this.position.setY(y);
        this.factory.notifyObservers();
    }

    public Position getPosition(){
        return this.position;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }


//comportement du composant
    public void behave() {
    }

    public abstract boolean overlays(Position p);

}