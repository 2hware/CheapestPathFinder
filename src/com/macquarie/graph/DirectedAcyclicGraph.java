package com.macquarie.graph;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DirectedAcyclicGraph {

    public DirectedAcyclicGraph() {
        this.nodeSet = new HashSet<>();
    }

    private Set<Node> nodeSet;

    /*
        return String message of cheapest path cost from source node to target node.
    */
    public String findCheapestPath(String sourceNodeName, String targetNodeString) {

        Node sourceNode = findNode(sourceNodeName);
        Node targetNode = findNode(targetNodeString);

        if (sourceNode != null && targetNode != null) {
            calculatePathsFromSource(sourceNode);
            List<Node> path = getCheapestPathToTarget(targetNode);
            String result = "";
            int sizeCount = 0;
            for (Node node : path) {
                result += ((++sizeCount) == path.size()) ? node.getName() : node.getName() + "=>";
            }
            if (targetNode.getMinCost() != Double.POSITIVE_INFINITY)
                return "Cheapest path from:" + sourceNode.getName() + " to:" + targetNode.getName() + " is " + result + ", " + targetNode.getMinCost();
            else
                return "Cannot find path from:" + sourceNode.getName() + " to:" + targetNode.getName();

        } else {

            return "Cannot find Source Node: " + sourceNodeName + ", or Destination Node: " + targetNodeString + " in the Graph";
        }
    }

    /*
        Calculate cost of all paths from source node.
    */
    private void calculatePathsFromSource(Node source) {
        source.setMinCost(0);
        PriorityQueue<Node> nodeQueue = new PriorityQueue<>();
        nodeQueue.add(source);
        while (!nodeQueue.isEmpty()) {
            Node nodeQ = nodeQueue.poll();
            if (nodeQ.getLinks() != null)
                for (Path path : nodeQ.getLinks()) {
                    Node node = path.getNode();
                    double weight = path.getWeight();
                    double distanceThroughU = nodeQ.getMinCost() + weight;
                    if (distanceThroughU < node.getMinCost()) {
                        nodeQueue.remove(node);
                        node.setMinCost(distanceThroughU);
                        node.setPrevious(nodeQ);
                        nodeQueue.add(node);
                    }
                }
        }
    }

    /*
        return cheapest path's nodes to target node.
     */
    private List<Node> getCheapestPathToTarget(Node target) {
        List<Node> path = new ArrayList<>();
        for (Node node = target; node != null; node = node.getPrevious())
            path.add(node);
        Collections.reverse(path);
        return path;
    }


    /*
    Create Nodes and Links for the Graph from input file.
     */
    public void createGraphFromFile(File file) throws FileNotFoundException {
        if (file != null) {
            Scanner scanner = new Scanner(file);
            while (scanner.useDelimiter("\\n").hasNext()) {
                String definition = scanner.next();
                if (definition.matches("[a-zA-Z]+ => [a-z-A-Z]+: [0-9]+\\.?[0-9]*\\r?")) {
                    String[] rulesSplit = definition.split("=>|:");
                    Node nodeFrom = getNodeInstance(rulesSplit[0]);
                    Node nodeTo = getNodeInstance(rulesSplit[1]);
                    nodeFrom.addLink(new Path(nodeTo, Double.parseDouble(rulesSplit[2].trim())));
                }
            }
            scanner.close();
        } else
            throw new FileNotFoundException();
    }

    /*
       Return new Node if the node is not already added to the nodeSet of the graph.
       Otherwise, it will return reference to node from the nodeSet
        */
    private Node getNodeInstance(String nodeName) {
        Optional<Node> optional = nodeSet.stream().filter(n -> n.getName().equals(nodeName.trim())).findFirst();
        if (optional.isPresent()) {
            return optional.get();
        } else {
            Node node = new Node(nodeName.trim());
            nodeSet.add(node);
            return node;
        }
    }

    /*
           Return node from nodeSet if exist otherwise null.
            */
    private Node findNode(String nodeName) {
        Optional<Node> optional = nodeSet.stream().filter(n -> n.getName().equals(nodeName.trim())).findFirst();
        if (optional.isPresent())
            return optional.get();
        else
            return null;
    }
}


