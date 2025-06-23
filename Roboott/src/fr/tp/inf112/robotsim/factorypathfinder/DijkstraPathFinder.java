package fr.tp.inf112.robotsim.factorypathfinder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import fr.tp.inf112.robotsim.FactoryPathFinder;
import fr.tp.inf112.robotsim.model.Component;
import fr.tp.inf112.robotsim.model.Factory;

public class DijkstraPathFinder implements FactoryPathFinder, Serializable{
    private DefaultDirectedGraph<Position,DefaultEdge> factoryGraph;
    private final Factory factory;

    public DijkstraPathFinder(Factory factory){
        this.factory=factory;
    }


    @Override 
    public List<Position> findPath(Component source, Component target){
        factorytoGraph();
        
        GraphPath<Position, DefaultEdge> path = DijkstraShortestPath.findPathBetween(this.factoryGraph, source.getPosition(), target.getPosition());
        
        if (path==null){
            return null; //lorsqu'aucun chemin n'existe la liste des positions est mise à null.
        }

        return path.getVertexList();
    }

    //transforme la factory en graph en construisant la liste des sommets puis celle des arrêtes.
    private void factorytoGraph(){
        this.factoryGraph=new DefaultDirectedGraph<>(DefaultEdge.class);
        int width =this.factory.getWidth();
        int height=this.factory.getHeight();

        for (int x=0;x<width;x++){
            for(int y=0; y<height;y++){
                this.factoryGraph.addVertex(new Position(x,y));
            }
        }

        for (Position vertex:this.factoryGraph.vertexSet()){

            List<Position> neighbours=new ArrayList<Position>();
            neighbours.add(new Position(vertex.getX()+1,vertex.getY()));
            neighbours.add(new Position(vertex.getX()-1,vertex.getY()));
            neighbours.add(new Position(vertex.getX(),vertex.getY()+1));
            neighbours.add(new Position(vertex.getX(),vertex.getY()-1));

            for (Position neighbour: neighbours ){
                if (this.factoryGraph.containsVertex(neighbour) && !this.factory.overlays(neighbour)){ // on n'ajoute pas l'arrête si on sors des coordonnées de factory où si le sommet est dans un Component sur lequel on ne peut pas passer.
                    this.factoryGraph.addEdge(vertex, neighbour);
                }
            }
        }

    }


}
