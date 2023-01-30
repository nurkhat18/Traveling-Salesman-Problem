/**
 * Author: Nurkhat Jumabaev
 * FileName: Main.java
 *
 * this class is the main class for the Travelling Salesman Problem
 * the class is used to read the file and create a DGraph
 * and perform the tasks based on the given command.
 */

import java.io.File;
import java.text.DecimalFormat;
import java.util.*;
import java.io.FileNotFoundException;

/**
 * this class has only one method which is only main
 * it takes commands from arguments
 * first given argument will be the file name
 * and the second one will be the command such as
 * HEURISTIC.
 */
public class Main {
    
    public static void main(String[] args) throws FileNotFoundException {
        // used to round the cost to one decimal
        DecimalFormat df = new DecimalFormat("0.0");

        // reading all the vertices and edges from a file
        ArrayList<String[]> arrList = new ArrayList<>();
        Scanner sc = new Scanner(new File(args[0]));
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            char charArray[] = s.toCharArray();
            boolean bool = Character.isDigit(charArray[0]);
            if (bool) {
                String[] values = s.split("\\s+");
                arrList.add(values);

            }
        }
        sc.close();
        // find out how many vertices needed to create the graph
        int numVertices = Integer.parseInt(arrList.get(0)[0]);
        DGraph dGraph = new DGraph(numVertices);
        // adding all the vertices and connecting it with the given weight
        for (int i = 0; i < arrList.size(); i++) {
            String[] edge = arrList.get(i);
            dGraph.addEdge(Integer.parseInt(edge[0]) - 1,
                    Integer.parseInt(edge[1]), Double.parseDouble(edge[2]));
        }

        // for each command printing out some values.

        // when the command is HEURISTIC
        if (args[1].equals("HEURISTIC")){
            List<Integer> path = new ArrayList<>();
            System.out.print("cost = " + df.format(dGraph.tspHeuristic(1, path)) + ", ");
            System.out.println("visitOrder = " + path);

        }

        // when the command is TIME
        else if (args[1].equals("TIME")) {
            // time interval and cost for Heuristic method
            List<Integer> tspCycle = new ArrayList<>();
            long startTime = System.nanoTime();
            double tspCost = dGraph.tspHeuristic(1, tspCycle);
            long endTime = System.nanoTime();
            float duration = (endTime - startTime)/1000000.0f;
            System.out.println("heuristic: cost = " + tspCost + ", " + duration + " milliseconds.");
            

            // time interval and cost for backtracking method.
            startTime = System.nanoTime();
            tspCost = dGraph.tspBacktracking(1, tspCycle);
            endTime = System.nanoTime();
            duration = (endTime - startTime)/1000000.0f;
            List<Integer> minPath = new ArrayList<>();
            minPath = new ArrayList<>();
            int startIndex = tspCycle.size() - numVertices;
            for (int i = startIndex; i < tspCycle.size(); i++){
                minPath.add(tspCycle.get(i));
            }
            System.out.println("backtrack: cost = " + tspCost + ", " + duration + " milliseconds.");


        }

        // when the command is BACKTRACK
        else if (args[1].equals("BACKTRACK")) {
            List<Integer> path = new ArrayList<>();
            System.out.print("cost = " + df.format(dGraph.tspBacktracking(1, path)) + ", ");

            List<Integer> minPath = new ArrayList<>();
            int startIndex = path.size() - numVertices;
            for (int i = startIndex; i < path.size(); i++){
                minPath.add(path.get(i));
            }
            System.out.println("visitOrder = " + minPath);
        } 
    }

}
