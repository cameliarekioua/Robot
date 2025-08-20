package fr.tp.inf112.robotsim.simulator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import fr.tp.inf112.projects.canvas.view.CanvasViewer;
import fr.tp.inf112.projects.canvas.view.FileCanvasChooser;
import fr.tp.inf112.robotsim.FactoryPathFinder;
import fr.tp.inf112.robotsim.factorypathfinder.DijkstraPathFinder;
import fr.tp.inf112.robotsim.factorypathfinder.Position;
import fr.tp.inf112.robotsim.model.*;

/**
 * Point d'entrée de l'application.
 */
public class SimulatorApplication {
     private static final Logger LOGGER = Logger.getLogger(SimulatorApplication.class.getName());

    public static void main(String[] args) throws IOException{

        LogManager.getLogManager().readConfiguration(      
            new FileInputStream("config/logging.properties")
        );
        LOGGER.info("Starting the robot simulator...");
        LOGGER.config("With parameters '" + Arrays.toString(args) + "'.");



        Factory factory = new Factory("MyFactory", new Position(0,0),300, 300);
        LOGGER.info("Factory initialized");
        FactoryPathFinder pathfinder=new DijkstraPathFinder(factory);
        LOGGER.info("pathFinder initialized");

        // composants
        Robot r1 = new Robot("R1", new Position(50,50),10,factory,pathfinder);
        LOGGER.info("new robot created"+r1.getName());
        Robot r2 = new Robot("R2", new Position(100,150),5,factory,pathfinder);
        // LOGGER.info("new robot created"+r2.getName());
        
        createRoom("Room 1", new Position(5,5),100,100,factory);
        createRoom("Room 2", new Position(150,100),50,50,factory);
        
        ProductionMachine pm1=creatProductionAreaandmachine("work area 1",  new Position(50,50), 20, 20, factory);
        ProductionMachine pm2=creatProductionAreaandmachine("work area 2",  new Position(175,125), 20,15, factory);

        Washerstorage wt=new Washerstorage("wt1", new Position(250,250), 20, 20, factory);

        ChargingStation cs1 = new ChargingStation("Cs1", new Position(150,10),factory);
         ChargingStation cs2 = new ChargingStation("Cs2", new Position(150,20),factory);
        LOGGER.info("new ChargingStation created");
        
       
        //targets' definition
        r1.addTarget(cs1);
        r1.addTarget(pm1);
        r1.addTarget(pm2); 
        r1.addTarget(wt);
        r1.addTarget(cs1);

        r2.addTarget(cs2);
        r2.addTarget(pm1);
        r2.addTarget(wt);
        r2.addTarget(pm2);
        r2.addTarget(wt);
        r2.addTarget(cs2);

        // ajout
        factory.addComponent(cs1);
        factory.addComponent(cs2);
        factory.addComponent(wt);

        factory.addComponent(r1);
        factory.addComponent(r2);
        
        //chooser & persistencemanager
        FileCanvasChooser chooser = new FileCanvasChooser("txt", "Canvas");
        CanvasPersistenceManager persistenceManager = new CanvasPersistenceManager(chooser);
        
        
        // contrôleur & vue
         SimulatorController ctrl = new SimulatorController(factory,persistenceManager);
         CanvasViewer viewer = new CanvasViewer(ctrl);
    }


    public static void createRoom(String name, Position position, int width, int height, Factory factory){
        LOGGER.info("new room with a door created");
        
        Room room =new Room(name, position, width, height, factory);
        AutoDoor door=new AutoDoor("   door "+ name, new Position(position.getX()+width,position.getY()+height/2-Door.DOORHEIGHT/2),factory);
        factory.addComponent(room);
        factory.addComponent(door);
        room.adddoor(door);
    }

    public static ProductionMachine creatProductionAreaandmachine(String name, Position position, int width, int height,Factory factory){
        LOGGER.info("new production area with a production machine created");

        ProductionArea area =new ProductionArea(name, position, width, height, factory);
        ProductionMachine pm=new ProductionMachine("machine of "+name, new Position(position.getX()+width/2,position.getY()+height/2), factory);
        factory.addComponent(area);
        factory.addComponent(pm);
        return pm;
    }
}