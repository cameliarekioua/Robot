package fr.tp.inf112.robotsim.model;

import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;
import fr.tp.inf112.robotsim.design.BasicRectangle;
import fr.tp.inf112.robotsim.design.BasicStroke;
import fr.tp.inf112.robotsim.design.BasicStyle;
import fr.tp.inf112.robotsim.factorypathfinder.Position;

import java.util.LinkedList;
import java.util.Queue;

public class Washerstorage extends Component {

    private final Queue<Washer> storage = new LinkedList<>();


    public Washerstorage(String name, Position position, int width, int height, Factory factory) {
        super(name, position, width, height, factory);
    }


    public boolean isEmpty() {
        return this.storage.isEmpty();
    }

    public void addWasher(Washer washer) {
            this.storage.add(washer);
            this.setName("stock= "+this.washerCount()); // mets à jour le nom pour tenir compte du stock.
    }

    public Washer removeWasher() {
        return this.storage.poll();
    }

    public int washerCount() {
        return this.storage.size();
    }

    @Override
    public Shape getShape() {
        return new BasicRectangle(this.width, this.height);
    }

    @Override
    public Style getStyle() {
        return new BasicStyle(RGBColor.BLUE, new BasicStroke(RGBColor.GRAY, 1.0f, null)
        );
    }

    @Override
    public boolean overlays(Position p) {
        return false; // ne bloque pas les mouvements
    }

    @Override
    public String toString() {
        return "Stock de rondelles (" + washerCount() ; 
    }
}

