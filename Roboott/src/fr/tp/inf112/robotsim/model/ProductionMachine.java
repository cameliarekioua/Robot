package fr.tp.inf112.robotsim.model;

import java.util.ArrayList;
import java.util.List;

import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;
import fr.tp.inf112.robotsim.design.*;
import fr.tp.inf112.robotsim.factorypathfinder.Position;

public class ProductionMachine extends Component{
    private List<Washer> washers;

      public ProductionMachine(String name, Position position,Factory factory) {
        super(name, position, 5, 5,factory);
        this.washers=new ArrayList<Washer>();
    }


    public List<Washer> getWashers(){
        return this.washers;
    }


    public void addWasher(){
        Washer washer=new Washer(" ",position,factory);
        this.washers.add(washer);
    }

    public Washer removeWasher(){
        if(this.washers.isEmpty()){
            return null;
        }else{
            return this.washers.remove(0);
        }
    }

//la machine produit une rondelle par tick, elle ne l'affiche pas.
    @Override
    public void behave(){
        this.addWasher();
    }

    @Override
    public boolean overlays (Position p){
        return false;
    }

    @Override 
    public Shape getShape(){
        return new BasicRectangle(this.width, this.height);
    }

    @Override
    public Style getStyle(){
        return new BasicStyle(new RGBColor(200,110,25),new BasicStroke(new RGBColor(128,128,128), (float)0.5,null ));
    }
    @Override
    public String toString(){
        return ("une machine de production; qui s'appelle"+this.name+" située en "+this.position.toString()+"avec pour dimension " +5+"x" +5);
    }
}
