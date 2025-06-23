package fr.tp.inf112.robotsim;

import java.util.List;
import fr.tp.inf112.robotsim.factorypathfinder.Position;
import fr.tp.inf112.robotsim.model.*;


public interface FactoryPathFinder {

    List<Position> findPath(Component source,Component target);

}
