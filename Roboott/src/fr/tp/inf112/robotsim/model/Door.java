package fr.tp.inf112.robotsim.model;

import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;
import fr.tp.inf112.robotsim.design.*;
import fr.tp.inf112.robotsim.factorypathfinder.Position;

public class Door extends Component {
    protected boolean isOpen; 

    public static final int DOORWIDTH=1;
    public static final int DOORHEIGHT=20;
    
    public Door(String name, Position position,Factory factory) {
        super(name, position, DOORWIDTH, DOORHEIGHT,factory);
        this.isOpen=false;
    }



    //Les méthodes agissant sur le caractère ouvert ou fermés
    public boolean getIsOpen(){
        return this.isOpen;
    }

    public void close(){
        this.isOpen=false;
        this.factory.notifyObservers();
    }

    public void open(){
        this.isOpen=true;
        this.factory.notifyObservers();
    }



    //Si la porte est ouverte il n'y a pas d'overlay, sinon on teste classiquement si les coordonnées sont dans la porte.
    @Override
    public boolean overlays(Position p){
        if(isOpen){
            return false;
        }
        else {
            return p.getX()>=this.getxCoordinate() && p.getX()<this.getxCoordinate()+DOORWIDTH
                    && p.getY()>=this.getyCoordinate() &&p.getY()<this.getyCoordinate()+DOORHEIGHT;
        }
    }


    //affichage des caractéristiques de la porte
    @Override
    public String toString(){
        return ("une porte qui s'appelle "+this.name+" située en "+this.position.toString()+" avec pour dimension " +DOORWIDTH+"x" +DOORHEIGHT);
    }

    //La porte est rectangulaire
    @Override 
    public Shape getShape(){
        return new BasicRectangle(this.width, this.height);
    }

    
    //elle change de style en fonction de si elle est ouverte ou fermée
    @Override
    public Style getStyle(){
        if (isOpen){
            return new BasicStyle(RGBColor.WHITE,new BasicStroke( RGBColor.GRAY, (float)0.5, null));
        }else{
            return new BasicStyle(RGBColor.BLACK,new BasicStroke( RGBColor.GRAY, (float)0.5, null));
        }
    }
}