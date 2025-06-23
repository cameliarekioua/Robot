package fr.tp.inf112.robotsim.design;

import fr.tp.inf112.projects.canvas.model.Style;

import java.io.Serializable;

import fr.tp.inf112.projects.canvas.model.Color;
import fr.tp.inf112.projects.canvas.model.Stroke;

public class BasicStyle implements Style,Serializable{

    private final Color backgroundcolor;
    private final Stroke stroke;

    public BasicStyle(Color backgroundcolor,Stroke stroke){
        this.backgroundcolor=backgroundcolor;
        this.stroke=stroke;
    }

    @Override 
    public Color getBackgroundColor(){
        return this.backgroundcolor;
    }

    @Override 
    public Stroke getStroke(){
        return this.stroke;
    }

}
