package fr.tp.inf112.robotsim.model;

import fr.tp.inf112.robotsim.factorypathfinder.Position;

public class AutoDoor extends Door {
    private int openTicks = 0;
    private static final int MAX_OPEN_TICKS = 5;
    private static final int MARGIN = Robot.ROBOTWIDTH+3; // Distance autour de la porte à surveiller

    public AutoDoor(String name, Position position, Factory factory) {
        super(name, position, factory);
    }

    @Override
    public void behave() {
        if (detectRobotNearby()) {
            this.open();
            openTicks = MAX_OPEN_TICKS;
        } else if (isOpen) {
            openTicks--;
            if (openTicks <= 0) {
                this.close();
            }
        }
    }

    private boolean detectRobotNearby() {
        int x1 = this.getxCoordinate() - MARGIN;
        int y1 = this.getyCoordinate() - MARGIN;
        int x2 = this.getxCoordinate() + this.width + MARGIN - 1;
        int y2 = this.getyCoordinate() + this.height + MARGIN - 1;

        for (Component comp : factory.getComponents()) {
            if (comp instanceof Robot robot) {
                Position pos = robot.getPosition(); // Position actuelle
                int rx = pos.getX();
                int ry = pos.getY();

                if (rx >= x1 && rx <= x2 && ry >= y1 && ry <= y2) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean overlays(Position p) {
        return false;
    }

     @Override
    public String toString(){
        return ("une porte  automatique qui s'appelle "+this.name+" située en "+this.position.toString()+" avec pour dimension " +DOORWIDTH+"x" +DOORHEIGHT);
    }

}
