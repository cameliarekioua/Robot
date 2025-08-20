package fr.tp.inf112.robotsim.design;

import fr.tp.inf112.projects.canvas.model.Stroke;

import java.io.Serializable;

import fr.tp.inf112.projects.canvas.model.Color;

// cette classe permet de définir le contour via 3 paramètres la couleur du trait, son épaisseur et le pattern de tracé.
public class BasicStroke implements Stroke,Serializable{

    private final Color color;
    private final float thickness;
    private final float[] dashpattern;


    public BasicStroke(Color color, float thickness, float[] dashpattern){
        this.thickness=thickness;
        this.color=color;
        this.dashpattern=dashpattern;
    }
    @Override 
    public Color getColor(){
        return this.color;
    }

    @Override
    public float getThickness(){
        return this.thickness;
    }

    @Override
    public float[] getDashPattern(){
        return this.dashpattern;
    }
}
