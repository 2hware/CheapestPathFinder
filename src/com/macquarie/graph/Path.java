package com.macquarie.graph;

/**
 * Created by Hussein on 2016-06-19.
 */
class Path
{
    private final Node node;
    private final double weight;
    public Path(Node node, double weight){
        this.node = node; this.weight = weight;
    }


    public Node getNode() {
        return node;
    }

    public double getWeight() {
        return weight;
    }
}
