package fr.tp.inf112.robotsim.model;

import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;
import fr.tp.inf112.robotsim.design.*;
import fr.tp.inf112.robotsim.factorypathfinder.Position;


/** Aire de travail. */
public class ProductionArea extends Component {
    public ProductionArea(String name, Position position, int width, int height,Factory factory) {
        super(name, position, width, height,factory);
    }

    @Override
    public boolean overlays(Position p){
        return false;
    }


    @Override 
    public Shape getShape(){
        return new BasicRectangle(this.width, this.height);
    }

    @Override
    public Style getStyle(){
        return new BasicStyle( RGBColor.GREEN,new BasicStroke( RGBColor.GRAY, (float)0.5, null));
    }
    @Override 
    public String toString(){
        return ("une aire de production; qui s'appelle"+this.name+",  située en "+this.position.toString()+" avec pour dimension " +this.width+"x" +this.height);
    }
}
