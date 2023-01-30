/**
 * Author: Nurkhat Jumabaev
 * FileName: DGraph.java
 *
 * This class is used to create a directed graph.
 * It would have different methods to solve tsp problem
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * this class creates a graph
 * And it has inner private class called edge.
 * Graph will be represented as ArrayList of LinkedLists
 * LinkedList represent each adjacent of vertices.
 */
public class DGraph {

    /**
     * the class is used to create and edge and vertices
     * to connect it to another vertices
     * it stores two things.
     * one the vertices number and the weight that edge has.
     */
    private class Edge {
        int label;
        double weight;

        Edge(int v, double w) {
            label = v;
            weight = w;
        }
    }

    private ArrayList<LinkedList<Edge>> adjList = new ArrayList<>();
    private int numVertices;

    /**
     * Constructore for the DGraph class
     * which only takes the number of vertices
     * needed to create the graph.
     * @param numVertices
     */
    DGraph(int numVertices){
        this.numVertices = numVertices;
        for (int i = 0; i < numVertices; i++){
            adjList.add(new LinkedList<Edge>());
        }
    }

    /**
     * this method is used to connect u and v vertices
     * with the given weight w.
     * @param u
     * @param v
     * @param w
     */
    void addEdge(int u, int v, double w){
        adjList.get(u).add(new Edge(v, w));
    }

    /**
     * this method uses approximate algorithm to find
     * a solution for TSP problem.
     * @param start
     * @param path
     * @return
     */
    public double tspHeuristic(int start, List<Integer> path){
        boolean visited[] = new boolean[numVertices];
        LinkedList<Integer> queue = new LinkedList<>();
        visited[start-1] = true;
        queue.add(start);
        double tspCost = 0.0;

        while (queue.size() != 0){
            int u = queue.pollFirst();
            path.add(u);

            double minEdgeWeight = Double.MAX_VALUE;
            int minVertex = -1;
            for (int i = 0; i < adjList.get(u-1).size(); i++){
                boolean bool = visited[adjList.get(u-1).get(i).label - 1];

                if (adjList.get(u-1).get(i).weight < minEdgeWeight && !bool){
                    minVertex = adjList.get(u-1).get(i).label;
                    minEdgeWeight = adjList.get(u-1).get(i).weight;
                }
            }
            if (minVertex != -1 && !visited[minVertex-1]){
                visited[minVertex-1] = true;
                if (minVertex != 1) {
                    queue.add(minVertex);
                }
                tspCost += minEdgeWeight;
            }
            if (path.size() == numVertices-1){
                visited[start-1] = false;
            }

        }
        return tspCost;

    }

    /**
     * the method is used to find an exact solution
     * for the TSP problem by using exhaustive algorithm.
     * it is the method as the below one except it has fewer parameters.
     * @param start
     * @param minPath
     * @return
     */
    public Double tspBacktracking(int start, List<Integer> minPath){
        ArrayList<Integer> path = new ArrayList<>();
        path.add(start);

        double tspCost = Double.MAX_VALUE;
        double currCost = 0;
        boolean[] visited = new boolean[numVertices];
        return tspBacktracking(start, path, minPath, visited, tspCost, currCost);
    }

    /**
     * this method uses backtracking algorithm
     * to find an exact solution for the TSP problem.
     * it uses recursive algorithm.
     * @param start
     * @param path
     * @param visited
     * @param tspCost
     * @param currCost
     * @return
     */
    double tspBacktracking(int start, List<Integer> path,
                           List<Integer> minPath, boolean[]
                                   visited, double tspCost, double currCost){
        visited[start-1] = true;
        // base case
        if (path.size() == numVertices){
            // this code adds the weight
            // from last vertices to the starting vertices to the currCost.
            int lastVert = path.get(path.size() - 1);
            int index = -1;
            for (int i = 0; i <adjList.get(lastVert-1).size(); i++){
                if (adjList.get(lastVert-1).get(i).label == 1){
                    index = i;
                    break;
                }
            }
            currCost += adjList.get(lastVert - 1).get(index).weight;

            // if there is a path with better cost.
            if (currCost < tspCost){
                tspCost = currCost;
                for (int i = 0; i < path.size(); i++){
                    minPath.add(path.get(i));
                }

            }
        }
        // otherwise doing recursion
        else{
            for (int i = 0; i < adjList.get(start-1).size(); i++){
                // finding the vertices and weight
                int v = adjList.get(start - 1).get(i).label;
                double w = adjList.get(start -1).get(i).weight;
                // adding the vertices if it is not visited
                if(!visited[v - 1]){
                    path.add(v);
                    currCost += w;
                    tspCost = tspBacktracking(v, path, minPath, visited, tspCost, currCost);
                    visited[v - 1] = false;
                    path.remove(path.size()-1);
                    currCost -= w;
                }
            }
        }
        return tspCost;
    }
}

