package fr.tp.inf112.robotsim.model;

import java.util.ArrayList;
import java.util.List;

import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.robotsim.design.*;
import fr.tp.inf112.robotsim.factorypathfinder.Position;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;

/** Salle rectangulaire. */
public class Room extends Component {
    private List<Door> doors;
   
    public Room(String name, Position position, int width, int height,Factory factory) {
        super(name, position, width, height,factory);
        this.doors=new ArrayList<Door>();
    }

    // ajoute une porte à la pièce
    public void adddoor(Door door){
        if (this.doors==null || this.doors.contains(door)){
            throw new RuntimeException("impossible d'ajouter la porte " + door.toString());
        }else{
            this.doors.add(door);
        }
    }

//  cette méthode peremt de déterminer si la position qui est dans l'enceinte de la pièce est dans la porte 
    private Door inDoor(Position position) {
    int x = position.getX();
    int y = position.getY();

    int wallThickness = (int) this.getStyle().getStroke().getThickness();
    int marge = Math.max(wallThickness, Robot.ROBOTHEIGHT); // permet de se laisser de la marge pour différencier la porte du mur

    for (Door door : doors) {
        int doorX = door.getxCoordinate();
        int doorY = door.getyCoordinate();

        if (x >= doorX - marge&&
            x <= doorX + 2 * marge &&
            y >= doorY  &&
            y <= doorY + Door.DOORHEIGHT - Robot.ROBOTHEIGHT) {
            return door;
        }
    }
    return null;
}
    

    @Override
     public boolean overlays(Position position) {
        int x = position.getX();
        int y = position.getY();

        int wallThickness = (int) this.getStyle().getStroke().getThickness();

        Door door = this.inDoor(position);
        if (door != null ) {   // si la position est dans la porte c'est la fonction de la classe Door qui prend la main
            return door.overlays(position);
        }

        int roomX = this.getxCoordinate();
        int roomY = this.getyCoordinate();
        int roomWidth = this.getWidth();
        int roomHeight = this.getHeight();

        boolean insideXwall = (x >= roomX - wallThickness && x <= roomX) || 
                              (x >= roomX + roomWidth - Robot.ROBOTWIDTH && 
                               x <= roomX + roomWidth + wallThickness);
        
        boolean insideYwall = (y >= roomY - wallThickness && y <= roomY) || 
                              (y >= roomY + roomHeight - Robot.ROBOTHEIGHT && 
                               y <= roomY + roomHeight + wallThickness);

        if (!insideXwall && !insideYwall) {  //si on est dans aucun mur pas de soucis
            return false;
        }

        if (insideXwall && insideYwall) { //si on est dans les deux murs il ya overlaying
            return true;
        }

        boolean insideX = (x >= roomX && x <= roomX + roomWidth - Robot.ROBOTWIDTH);
        boolean insideY = (y >= roomY && y <= roomY + roomHeight - Robot.ROBOTHEIGHT);

        if ((insideX && insideYwall) || (insideXwall && insideY)) { // si on est dans l'enceinte globale de la pièce et dans un des murs il y a overlaying
            return true;
        }
        
        return false;
    }

    @Override 
    public Shape getShape(){
        return new BasicRectangle(this.width, this.height);
    }

    @Override
    public Style getStyle(){
        return new BasicStyle( RGBColor.WHITE,new BasicStroke( RGBColor.GRAY, (float)10,null ));
    }

    @Override
    public String toString(){
        return ("une pièce qui s'appelle "+this.name+"  située en "+this.position.toString()+" avec pour dimension " +this.width+"x" +this.height);
    }
}