package fr.tp.inf112.robotsim.model;

import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;
import fr.tp.inf112.robotsim.design.BasicOval;
import fr.tp.inf112.robotsim.design.BasicStroke;
import fr.tp.inf112.robotsim.design.BasicStyle;
import fr.tp.inf112.robotsim.factorypathfinder.Position;

public class Washer extends Component{

    public Washer(String name, Position position, Factory factory) {
		super(name, position, 1,2, factory);
	}


    @Override 
     public Shape getShape(){
        return new BasicOval(this.width, this.height);
    }

    @Override 
    public Style getStyle(){
        return new BasicStyle( RGBColor.BLACK,new BasicStroke(RGBColor.GRAY, (float)0,null));
    }

    @Override 
    public boolean overlays(Position p){
        return false;
    }

    @Override 
    public String toString(){
        return "une rondelle";
    }


}
