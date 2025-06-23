package fr.tp.inf112.robotsim.model;

import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.robotsim.design.*;
import fr.tp.inf112.robotsim.factorypathfinder.Position;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;

/** Station de recharge. */
public class ChargingStation extends Component {
    public ChargingStation(String name, Position position,Factory factory) {
        super(name, position, 5, 5,factory);
    }

 @Override 
    public Shape getShape(){
        return new BasicRectangle(this.width, this.height);
    }

    @Override
    public Style getStyle(){
        return new BasicStyle( RGBColor.BLUE,new BasicStroke( RGBColor.GRAY, (float)0.5, null ));
    }

    @Override 
    public boolean overlays(Position p){
        return false;
    }
    @Override
    public String toString(){
        return ("une aire de production qui s'appelle "+this.name+", située en "+this.position.toString()+" avec pour dimension " +5+" x " +5);
    }
}