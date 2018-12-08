/*
Path in a Graph:
Given a directed graph, write a method that takes 2 nodes as arguments and return true if there exists a path between them.
(a) Is it possible for there to be a path from a to b but not from b to a? Yes.
(b) For a non-directed graph, write an algorithm to find out how many disjoint groups of nodes there are.
*/

import java.util.*;

public class PathInAGraph {

  public static class Graph {
    List<GraphNode> allNodes;
  }

  public static class GraphNode {
    int value;
    List<GraphNode> neighbors;

    GraphNode(int value, List<GraphNode> neighbors) {
      this.value = value;
      this.neighbors = neighbors;
    }
  }

  public static boolean hasPath(GraphNode from, GraphNode to) {
    if (from == to) {
      return true;
    }

    Set<GraphNode> seen = new HashSet<>();
    LinkedList<GraphNode> queue = new LinkedList<>();

    queue.add(from);

    while (!queue.isEmpty()) {
      GraphNode next = queue.removeFirst();
      if (next == to) {
        return true;
      }
      for (GraphNode neighbor: next.neighbors) {
        if (seen.add(neighbor)) {
          queue.addLast(neighbor);
        }
      }
    }

    return false;
  }

  public static List<List<GraphNode>> getDisjointNodeGroups(Graph graph) {
    List<List<GraphNode>> groups = new ArrayList<>();
    Set<GraphNode> seen = new HashSet<>();

    for (GraphNode node: graph.allNodes) {
      if (seen.add(node)) {
        List<GraphNode> group = new ArrayList<>();
        LinkedList<GraphNode> queue = new LinkedList<>();

        group.add(node);
        queue.add(node);

        while (!queue.isEmpty()) {
          GraphNode next = queue.removeFirst();
          
          for (GraphNode neighbor: next.neighbors) {
            if (seen.add(neighbor)) {
              group.add(neighbor);
              queue.add(neighbor);
            }
          }
        }

        groups.add(group);
      }
    }

    return groups;
  }
}
