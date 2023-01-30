# The Traveling Salesman Problem

![TSP Image](https://blog-cdn.route4me.com/2019/12/16606fe4-traveling-salesman.jpg)


## Overview

The traveling salesman problem (TSP): given a set of cities and the distances between each,
what is the shortest path that visits each city once and returns to the starting city? The problem
is usually modeled with a weighted graph and has been studied extensively. The solution
involves making a decision about which city to visit next and as such it belongs to a large class
of combinatorial optimization problems that can be solved by searching for the optimal answer.
The time complexity of such a search is exponential and only problems of modest size can be
solved. Applications of the TSP include: vehicle routing, scheduling, integrated circuit design,
DNA sequencing and the tracking of stars. This assignment involves the implementation of a
program to experiment with some basic solutions to the TSP and to time their performance.

## Assignment

A program is needed to take as input a directed weighted graph which represents a network of
cities in a TSP. The graph is stored in a file and the filename is given to the program as a
_command line_ argument. The input files are in the matrix market (.mtx) format. An example input
file is:

%%MatrixMarket matrix coordinate real general
3 3 6
1 2 1.
2 1 2.
1 3 3.
3 1 4.
2 3 5.
3 2 6.

Also provided on the _command line_ is a directive to indicate which algorithm should be used to
solve the TSP and when timing should be performed:

HEURISTIC - an approximate algorithm that uses a heuristic,
BACKTRACK - an exact algorithm that uses brute-force search,
TIME - a measuring of the running time of the above approaches.

An example of the output for the above input file using the HEURISTIC command is:

cost = 10.0, visitOrder = [1, 2, 3]

The assignment can be partitioned into five main tasks:

### 1) The Graph

A weighted directed graph is needed to represent the ‘cities’ and their distances. The graph
should support operations that will be needed by the algorithms, mainly a method to find all
vertices that are adjacent to a given vertex. The graph class should be in **DGraph.java**.


### 2) The Heuristic Approach

A simple way to arrive at an approximate solution to the TSP is to traverse the graph and at
each vertex that you arrive at, make a decision about which way to go based on the weights of
the incident edges. A simple ‘heuristic’ or rule-of-thumb is to take the edge of lowest weight.

The heuristic approach is implemented as a **method in the DGraph class**.

### 3) The Backtracking Approach

Recursive backtracking is a general approach to solving problems that can be represented with
a graph. It involves exploring a possible solution, recording its value, and then backing up to the
previous vertex to make a different decision and record the value of that solution, and so on.

The backtracking approach is implemented as a **method in the DGraph class**.


### 5) Timing

The output for the TIME command should look as follows:

heuristic: cost = 935.3299999999999, 0 milliseconds
backtrack: cost = 835.8799999999999, 5 milliseconds
