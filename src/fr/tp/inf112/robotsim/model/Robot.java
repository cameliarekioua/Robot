package fr.tp.inf112.robotsim.model;

import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;
import fr.tp.inf112.robotsim.design.*;
import fr.tp.inf112.robotsim.factorypathfinder.Position;
import fr.tp.inf112.robotsim.FactoryPathFinder;
import java.util.*;



public class Robot extends Component {
    private double speed;
    private List<Component> toVisit=new ArrayList<Component>(); 
    private FactoryPathFinder pathFinder; // classe permettant de calculer l'existence d'un chemin
    private int currentTarget=0;//la position dans la liste toVisit du composant que l'on vise actuellement
    private Queue<Position> currentPath=new LinkedList<Position>();//la succesion de position pour arriver à cette current Target
    private double leftoverMovement=0; // permet de stocker les déplacements non entiers
    private boolean isStuck; //permet d'afficher visuellement que le robot est bloqué
    private Washer washer; //renvoie la rondelle que porte le robot


    public static final int ROBOTWIDTH=5;
    public static final int ROBOTHEIGHT=10;

    public Robot( String name,Position position, double speed,Factory factory, FactoryPathFinder pathfinder){
        super(name,position,ROBOTWIDTH,ROBOTHEIGHT,factory);
        this.speed=speed;
        this.pathFinder=pathfinder;
        this.isStuck=false;
        this.washer=null;
    }

    public void isStuck(){
        this.isStuck=true;
        this.factory.notifyObservers();
    }

    public void isnotStuck(){
        this.isStuck=false;
        this.factory.notifyObservers();
    }

    public boolean getisStuck(){
        return this.isStuck;
    }


    // les méthodes classiques, le robot est ici de forme ovale 
    @Override 
    public String toString(){
        return "un robot; qui s'appelle" +name +"et qui avance à " + speed +" km/h.";
    }
   
    @Override 
    public Shape getShape(){
        return new BasicOval(this.width, this.height);
    }

    @Override
    public Style getStyle(){
        if(isStuck){
            return new BasicStyle( RGBColor.BLACK,new BasicStroke(RGBColor.GRAY, (float)0.5,null));
        }else{
            if (isCarryingwasher()){
                return new BasicStyle( RGBColor.PINK,new BasicStroke(RGBColor.GRAY, (float)0.5,null));

            }else{
                return new BasicStyle( RGBColor.RED,new BasicStroke(RGBColor.GRAY, (float)0.5,null));
            }
        }
    }
    public Position peekNextPosition() {
        return currentPath.peek();
}

//On ajoute ici un nouveau composant à visiter.
    public void addTarget(Component comp){
        this.toVisit.add(comp);
    }

    private boolean isAt(Component comp) {
        return this.getPosition().equals(comp.getPosition());
}

// définit ici le comportement du robot
@Override
public void behave() {
    if (this.toVisit.isEmpty() || this.pathFinder == null) {
        this.isStuck();
        return;
    }

    if (currentTarget >= toVisit.size()) { // si le robot a fini toutes ses cibles il s'arrête
        this.isStuck();
        return;
    }

    leftoverMovement += this.speed;

    while (leftoverMovement >= 1) {
        if (this.currentPath.isEmpty()) {
            Component target = toVisit.get(currentTarget);

            // Si le robot est déjà dessus, on passe au suivant
            if (isAt(target)) {
                if (!this.visitTarget(target)){
                    return;
                }
                currentTarget++;
                if (currentTarget >= toVisit.size()) {
                    this.isStuck();
                    return;
                }
                target = toVisit.get(currentTarget);
            }

            List<Position> path = this.pathFinder.findPath(this, target);
            if (path == null || path.isEmpty()) {
                this.isStuck();
                return;
            }
            currentPath.addAll(path);
        }

        Position next = currentPath.peek();
        if (next != null) {
            if (!this.factory.isPositionOccupied(next, this)) {
                this.isnotStuck();
                this.move(currentPath.poll());
                leftoverMovement -= 1;
            } else {
                this.isStuck(); // si un autre robot bloque, on s'arrete
                break;
            }
        } else {
            break;
        }
    }
    }


        
// Au vu de l'utilisation de DijkstraPathFinder qui nous forunit
//  une liste de position de cases adjacentes, les déplacements sont de case en case 
    private void move(Position target) {
        int targetX = target.getX();
        int targetY = target.getY();
        this.setxCoordinate(targetX);
        this.setyCoordinate(targetY);
    }

    private boolean visitTarget(Component target){
        if(target instanceof ProductionMachine){
            Washer washer =this.takeWasher((ProductionMachine) target);
            return (washer!=null);
        }

        if(target instanceof Washerstorage){
            this.dropWasher((Washerstorage)target);
            return true;
        }

        return true;
    }
    
//le cas des collisions netre robots est géré par la méthode behave().
    @Override 
    public boolean overlays(Position p){
        return false;
    }


    public boolean isCarryingwasher(){
        return (!(this.washer==null));
    }

    public Washer getWasher(){
        return this.washer;
    }

    private Washer takeWasher(ProductionMachine pm) {
        Washer washer = pm.removeWasher();

        if (washer == null) {
            return null; // rien à prendre
        }

        if (!this.isCarryingwasher()) {
            this.setWasher(washer); // le robot prend le washer
            return washer;
        }                    
                 // le robot porte déjà un washer, donc il ne peut pas en prendre un autre
        return this.getWasher();
}

    private void dropWasher(Washerstorage wt){
        if(isCarryingwasher()){
            Washer washer=this.getWasher();
            wt.addWasher(washer);
            this.setWasher(null);
        }
    }

    public void setWasher(Washer washer){
        this.washer=washer;
        this.factory.notifyObservers();
    }

}
