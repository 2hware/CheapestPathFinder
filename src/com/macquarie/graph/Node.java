package com.macquarie.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hussein on 2016-06-19.
 */
class Node implements Comparable<Node>
{
    private String name;
    private List<Path> links;
    private double minCost = Double.POSITIVE_INFINITY;
    private Node previous;
    public Node(String argName) { name = argName; this.links =new ArrayList<>(); }
    public String toString() { return name; }
    public int compareTo(Node other)
    {
        return Double.compare(minCost, other.minCost);
    }

    public String getName() {
        return name;
    }

    public List<Path> getLinks() {
        return links;
    }

    public void addLink(Path path)
    {
        this.links.add(path);
    }

    public double getMinCost() {
        return minCost;
    }

    public void setMinCost(double minCost) {
        this.minCost = minCost;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    @Override
    public boolean equals(Object obj) {
        return this.getName().trim().equals(((Node)obj).getName());
    }
}
