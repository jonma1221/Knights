/**
 * ******************************
 * Copyright 2015: smanna@cpp.edu
 * CS 241
 * PA4
 * ******************************
 * !!!WARNING!!! You are not allowed
 * to make any changes to this code.
 */

public class Edge implements Comparable<Edge> {

  private int u,v;

  public Edge(int u, int v) {
    this.u = Math.min(u,v);
    this.v = Math.max(u,v);
  }

  /**
   *  Returns if the Edges are equal
   *  @return true if endpoints match; false otherwise
   */
  public boolean equals(Object obj) {
    Edge that = (Edge)obj;
    return this.u == that.u && this.v == that.v;
  }

  /**
   *  Comparison is based on lexicographic comparison of endpoint indices
   *  @return 0 -> = | 1 -> greater | -1 -> less than |
   */
  public int compareTo(Edge that) {
    if (this.u == that.u) return this.v - that.v;
    return this.u - that.u;
  }

  /**
   *  Returns the String representation of the Edge
   *  @return String representation of Edge
   */
  public String toString() {
    return ("(" + u + "," + v + ")");
  }
}
