package fr.tp.inf112.robotsim.design;

import java.io.Serializable;

import fr.tp.inf112.projects.canvas.model.RectangleShape;


public class BasicRectangle implements RectangleShape,Serializable{

    private final int width;
    private final int height;

    public BasicRectangle(int width,int height){
        this.width=width;
        this.height=height;
    }

    @Override 
    public int getWidth(){
        return this.width;
    }

    @Override
    public int getHeight(){
        return this.height;
    }

}
