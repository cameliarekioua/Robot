package fr.tp.inf112.robotsim.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import fr.tp.inf112.projects.canvas.controller.Observer;
import fr.tp.inf112.projects.canvas.model.Figure;
import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;
import fr.tp.inf112.robotsim.design.BasicRectangle;
import fr.tp.inf112.robotsim.design.BasicStroke;
import fr.tp.inf112.robotsim.design.BasicStyle;
import fr.tp.inf112.robotsim.factorypathfinder.Position;

public class Factory extends Component implements fr.tp.inf112.projects.canvas.model.Canvas, fr.tp.inf112.projects.canvas.controller.Observable{
    private List<Component> components;
    private String id;
    private transient List<Observer> observers; // non sérialisé
    private transient boolean isSimulationRunning; // non sérialisé

    public Factory(String name, Position position, int width, int height){
        super(name,position,width, height,null);
        this.components=new ArrayList<Component>();
        this.isSimulationRunning=false;
    }


    public boolean isSimulationRunning(){
        return this.isSimulationRunning;
    }

    public void startSimulation(){
        this.isSimulationRunning=true;
        this.notifyObservers();
        while (isSimulationRunning()) {
            this.behave();
            try {
                Thread.sleep(200);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void endSimulation(){
        this.isSimulationRunning=false;
        this.notifyObservers();
    }

    @Override
    public void setId(String id){
        this.id=id;
    }

    @Override
    public String getId(){
        return this.id;
    }

    @Override 
    public String getName(){
        return this.name;
    }

    @Override
    public int getWidth(){
        return this.width;
    }

    @Override
    public int getHeight(){
        return this.height;
    }

    @Override
    public Collection<Figure> getFigures(){
        return new ArrayList<Figure>(this.components);

    }

    @Override 
    public Shape getShape(){
        return new BasicRectangle(this.width, this.height);
    }

    @Override
    public Style getStyle(){
        return new BasicStyle( RGBColor.LIGHT_GRAY,new BasicStroke( RGBColor.LIGHT_GRAY, (float) 1,null ));
    }

    public List<Component> getComponents(){
        return this.components;
    }


// on regarde le comportement de chaque Composant.
    @Override
    public void behave(){
        for (Component comp: this.components){
            comp.behave();
        }
    }

    // permet d'éviter les collisions entre robots 
    public boolean isPositionOccupied(Position position, Robot requester) {
    for (Component component : this.components) {
        if (component instanceof Robot && component != requester) {
            if (component.getPosition().equals(position)) {
                return true;
            }
        }
    }
    return false;
}


//cas de l'ajout des Observer,
// ici l'initialisation est paresseuse car la liste observers est non sérialisée 
    @Override 
    public boolean addObserver(Observer obs){
        if (this.observers==null){
            this.observers=new ArrayList<Observer>();
        }      
            if (this.observers.contains(obs)){
                return false;
            }else{
                this.observers.add(obs);
                return true;
            }
        }   

    @Override
    public boolean removeObserver(Observer obs){
         if (this.observers==null){
            this.observers=new ArrayList<Observer>();
        }      
        if (this.observers.contains(obs)){
                this.observers.remove(obs);
                return true;
        }else{
            return false;
    }
}

    public void notifyObservers(){
        if (this.observers==null){
            return;
        }else{
        for (Observer o:this.observers){
            o.modelChanged();
        }
    }
    }

// les deux méthodes sont utiles pour ajouter des Component à l'usine 
    private boolean checkComponentName(String name){
        for (int i=0; i<this.components.size();i++){
            if (this.components.get(i).getName().equals(name)){
                return false;
            }
        }
        return true;
    }

    public boolean addComponent(Component comp){
        if(this.checkComponentName(name)){
        this.components.add(comp);
        return true;
        }else{
            return false;
        }
    }

// regarde pour tous les composants de l'usine si il y a un overlaying
    @Override 
    public boolean overlays(Position p){
        for(Component comp:this.components){
            if(comp.overlays(p)){
                
                return true;
            }
        }
        return false;
    }

    public void printToConsole(){
        String s=new String();
        for (int i=0; i<this.components.size();i++){
            s=s+" "+this.components.get(i).toString()+", ";}
        System.out.println("The Factory is named "+this.name+" elle contient les éléments suivants : "+s);
}
}