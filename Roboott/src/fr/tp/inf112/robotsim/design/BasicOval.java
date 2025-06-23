package fr.tp.inf112.robotsim.design;

import java.io.Serializable;

import fr.tp.inf112.projects.canvas.model.OvalShape;

// cette classe permet d'implémenter l'interface OvalShape
public class BasicOval implements OvalShape,Serializable{
    
    private final int width;
    private final int height;

    public BasicOval(int width,int height){
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
