package vega;

import vega.helperClasses.GraphViz;
import vega.pseudoCode.PseudoCode;
import vega.algorithms.GraphAlgorithm;
import vega.graph.Graph;
import java.io.*;
import java.util.ArrayList;

/**
 * The controller class is the plug-point that a developer uses to interface with
 * VEGA.  The controller class is used to generate the "snapshots" of the graph as
 * an algorithm manipulates the grpah.
 * @author w_jossey
 */
public class Controller {

    int counter = 0;
    int imageCounter = 0;
    ArrayList<AlgorithmStep> stepTracker = new ArrayList();
    PseudoCode pseudoCode;
    Graph g;
    GraphAlgorithm a;

    public Controller(Graph g, PseudoCode pseudoCode, GraphAlgorithm a) {
        this.g = g;
        this.a = a;
        this.pseudoCode = pseudoCode;
    }

    /**
     * Method generates the jpegs on the hard drive so that the gui can access the 
     * pre-generated images, rather than generate on-the-go.  
     * @param fileName prefix for the file that will temporarily placed on the hard drive.
     */
    public void generateGraphInstance(int line, String procedure) {
        //1.  Convert the graph to DOT format
        //2.  Pass the graph off to Graphviz
        //3.  Take the returned byte array (image) and pass it off to the UI.
        //NOTE:  UI has a buffer which contains the graphs as they are processed.
        GraphViz graph = new GraphViz();
        //System.out.println("G.tostring(): " + g.toString());
        graph.add(g.toString());
        stepTracker.add(new AlgorithmStep(line, procedure));
        //System.out.println(g.toString());
        //System.out.println("Printing iteration: " + counter);
        File out = new File(a.getInstanceID() + a.getFileName() + counter++ + ".png");
        byte[] image = graph.getGraph(graph.getDotSource());
        if (graph.writeGraphToFile(image, out) == -1) {
            System.out.println("Failed!");
        }

    }

    /**
     * Gets the total number of images that were created during the running of the algorithm.
     * @return the total number of images created on the hard drive for the algorithm instance
     */
    public int getImageCount() {
        return counter;
    }

    /**
     * Return the filename of the next image.
     * @return the filename of the next image
     */
    public String[] getNextGraphImage() {
        String[] returnStringTuple = new String[2];
        if (getImageCount() > imageCounter) {
            returnStringTuple[0] = a.getInstanceID() + a.getFileName() + imageCounter + ".png";
            returnStringTuple[1] = getPseudoCodeWithLineMarker(stepTracker.get(imageCounter).getLine(),
                    stepTracker.get(imageCounter++).getProcedure());
            return returnStringTuple;
        } else {
            return null;
        }

    }

    /**
     * Return the filename of the previous image.
     * @return the filename of the previous image.
     */
    public String[] getPreviousGraphImage() {
        String[] returnStringTuple = new String[2];
        if (imageCounter > 1) {
            returnStringTuple[0] = a.getInstanceID() + a.getFileName() + (imageCounter - 2) + ".jpeg";
            returnStringTuple[1] = getPseudoCodeWithLineMarker(stepTracker.get(imageCounter - 2).getLine(),
                    stepTracker.get(imageCounter-- - 2).getProcedure());
            return returnStringTuple;
        } else {
            if (imageCounter == 1) {
                returnStringTuple[0] = a.getInstanceID() + a.getFileName() + (--imageCounter) + ".jpeg";
                returnStringTuple[1] = pseudoCode.getPseudoCodeWithLineMarker(stepTracker.get(imageCounter).getLine(),
                        stepTracker.get(imageCounter).getProcedure());
                return returnStringTuple;
            } else {
                return null;
            }
        }
    }

    /**
     * Gets the pseudocode for a particular procudure with a line marked.
     * @param line
     * @param procedure
     * @return
     */
    public String getPseudoCodeWithLineMarker(int line, String procedure) {
        return pseudoCode.getPseudoCodeWithLineMarker(line, procedure);

    }

    /**
     * Gets pseudocode without a line marker.
     * @return
     */
    public String getPseudoCodeWithoutLineMarker() {
        return pseudoCode.getPseudoCodeWithoutLineMarker();
    }

    /**
     * Gets pseudoCode without a line marker for a specific procedure.
     * @param procedure
     * @return
     */
    public String getPseudoCodeWithoutLineMarker(String procedure) {
        return pseudoCode.getPseudoCodeWithoutLineMarker(procedure);
    }

    private class AlgorithmStep {

        private String procedure;
        private int line;

        private AlgorithmStep(int line, String procedure) {
            this.procedure = procedure;
            this.line = line;
        }

        private int getLine() {
            return line;
        }

        private String getProcedure() {
            return procedure;
        }

        private void setLine(int line) {
            this.line = line;
        }

        private void setProcedure(String procedure) {
            this.procedure = procedure;
        }
    }
}
