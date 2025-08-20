package fr.tp.inf112.robotsim.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import fr.tp.inf112.projects.canvas.model.Canvas;
import fr.tp.inf112.projects.canvas.model.CanvasChooser;
import fr.tp.inf112.projects.canvas.model.impl.AbstractCanvasPersistenceManager;

public class CanvasPersistenceManager extends AbstractCanvasPersistenceManager {
    
    public CanvasPersistenceManager(final CanvasChooser canvasChooser){
        super(canvasChooser);
    }


    @Override
    public Canvas read(String canvasId) throws IOException{
        String fullPath = canvasId  ;
        File file = new File(fullPath);

        if (!file.exists()) {
            throw new FileNotFoundException("Le fichier " + fullPath + " n'existe pas.");
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            Object o = in.readObject();

            if (!(o instanceof Canvas)) {
                throw new IOException("Le fichier ne contient pas un objet Canvas valide.");
            }

            Canvas canvas = (Canvas) o;
            canvas.setId(canvasId);

            return canvas;

        } catch (ClassNotFoundException e) {
            throw new IOException("Classe non trouvée lors de la lecture du fichier.", e);
        }
    }

    @Override
    public void persist(Canvas canvasModel) throws IOException{
        String fullPath = canvasModel.getId() ;
        File file = new File(fullPath);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs(); //créer le chemin si il n'existe pas.
        }

    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
        out.writeObject(canvasModel);
    }
    }

    @Override 
    public boolean delete(Canvas canvasModel) throws IOException{
        File file = new File(canvasModel.getId());
        return file.delete();
    }


}
