/**
 * ******************************
 * Copyright 2015: smanna@cpp.edu
 * CS 241
 * PA4
 * ******************************
 * !!!WARNING!!! You are not allowed
 * to make any changes to this code.
 */

import java.util.ArrayList;
import java.util.Collections;

public class Vertex implements Comparable<Vertex> {
  
  private int label; //label is for easy identification
  private boolean visited;
  private ArrayList<Vertex> neighbors;
  
  public Vertex(int label) {
    this.label = label;
    this.visited = false;
    neighbors = new ArrayList<Vertex>();
  }

  public void addNeighbor(Vertex v) {
    neighbors.add(v);
  }

  public int compareTo(Vertex that) {
    return this.label - that.label;
  }
  
  public int getDegree() {
    return neighbors.size();
  }
  
  public int getLabel() {
    return label;
  }

  public Vertex[] getNeighbors() {
    Vertex[] output = new Vertex[neighbors.size()];
    //sort so dfs will visit in ascending order
    Collections.sort(neighbors);
    for (int i = 0; i < neighbors.size(); i++) {
      output[i] = neighbors.get(i);
    }
    return output;
  }
  
  public void setVisited(boolean visited) {
    this.visited = visited;
  }

  public String toString() {
    return "[" + label + "]";
  }

  public boolean visited() {
    return visited;
  }

}
