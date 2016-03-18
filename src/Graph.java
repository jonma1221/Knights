/**
 * ******************************
 * Copyright 2015: smanna@cpp.edu
 * CS 241
 * PA4
 * ******************************
 * !!!WARNING!!! You are not allowed
 * to make any changes to this code.
 */

import java.util.*;

public class Graph {

  private int order; //number of vertices in graph
  private Vertex[] vertices; //array of all vertices
  private TreeMap<Edge, Edge> edges = new TreeMap<Edge, Edge>(); //all edges

  /**
   * Builds graph with desired amount of vertices.
   */
  public Graph(int order) {
    this.order = order;
    vertices = new Vertex[order];
    createVertices();
  }

  /**
   *  Add weighted edge
   *  @param u vertex
   *  @param v vertex
   *  @param weight edge weight
   */
  public void addEdge(int u, int v) {
    //add both to each other's list of neighbors (undirected)
    vertices[u].addNeighbor(vertices[v]);
    vertices[v].addNeighbor(vertices[u]);
    //create edge and put in edges map
    Edge edge = new Edge(u,v);
    edges.put(edge,edge);
  }

  /**
   * Fills graph with vertices up to the desired amount. Also
   * labels vertices from 0 to n-1;
   */
  public void createVertices() {
    for (int i = 0; i < order; i++) {
      vertices[i] = new Vertex(i);
    }
  }

  /**
   *  Returns the maximum degree in the graph
   *  @return degree of Vertex with most neighbors
   */
  public int getDegree() {
    int max = 0;
    int current;
    for (Vertex vertex: vertices) {
      current = vertex.getDegree();
      if (current > max) max = current;
    }
    return max;
  }

  /**
   *  Return degree of vertex
   *  @param int label of vertex
   *  @return number of neighbors of v
   */
  public int getDegree(int label) {
    return vertices[label].getDegree();
  }

  /**
   *  Return information for edge e
   *  @return Edge
   */
  public Edge getEdge(Edge e) {
    return edges.get(e);
  }

  /**
   *  Return list of all edges
   *  @return array of edges
   */
  public Edge[] getEdges() {
    return edges.keySet().toArray(new Edge[0]);
  }

  /**
   *  Return list of neighbors of vertex
   *  @param int label corresponding to vertex
   *  @return array of neighbors of vertex
   */
  public Vertex[] getNeighbors(int label) {
    return vertices[label].getNeighbors();
  }

  /**
   *  Return number of vertices in the graph
   *  @return number of vertices
   */
  public int getOrder() {
    return order;
  }

  /**
   *  Return vertex of specified label
   *  @return vertex
   */
  public Vertex getVertex(int label) {
    return vertices[label];
  }

  /**
   * Unmark all vertices in the graph
   */
  public void resetVisit() {
    for (int i = 0; i < order; i++) {
      vertices[i].setVisited(false);;
    }
  }
}
