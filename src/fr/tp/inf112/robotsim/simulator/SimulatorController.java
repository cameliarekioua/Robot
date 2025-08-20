package fr.tp.inf112.robotsim.simulator;

import fr.tp.inf112.projects.canvas.controller.CanvasViewerController;
import fr.tp.inf112.projects.canvas.controller.Observer;
import fr.tp.inf112.projects.canvas.model.Canvas;
import fr.tp.inf112.projects.canvas.model.CanvasPersistenceManager;
import fr.tp.inf112.robotsim.model.Factory;

/**
 * Contrôleur MVC pour démarrer/arrêter la simulation.
 */
public class SimulatorController implements CanvasViewerController {
    private Factory factory;
    private CanvasPersistenceManager persistenceManager;

    public SimulatorController(Factory factory,CanvasPersistenceManager persistenceManager) {
        this.factory = factory;
        this.persistenceManager=persistenceManager;
    }

    @Override 
    public CanvasPersistenceManager getPersistenceManager(){
        return this.persistenceManager;
    }
    @Override 
    public Canvas getCanvas(){
        return this.factory;
    }

    @Override
    public void setCanvas(Canvas canvasmodel){
        this.factory=(Factory) canvasmodel;
    }

    @Override
    public void startAnimation() {
        factory.startSimulation();
    }

    @Override
    public void stopAnimation() {
        factory.endSimulation();
    }

    @Override
    public boolean isAnimationRunning() {
        return factory.isSimulationRunning();
    }

    @Override
    public boolean addObserver(Observer o) {
        return factory.addObserver(o);
    }
 
    @Override
    public boolean removeObserver(Observer o) {
        return factory.removeObserver(o);
    }
}