package com.macquarie.graph;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Hussein on 2016-06-19.
 */
public class PathFinderMasterTest {


    String TEST_INPUT_GRAPH_FILES_DIRECTORY = "resources\\test-graph-files\\";

    DirectedAcyclicGraph directedAcyclicGraph;

    File BASIC_GRAPH;
    File BASIC_PARALLEL_GRAPH;
    File LOOP_GRAPH;
    File INVALID_GRAPH_CONTENTS;
    File COMPLEX_GRAPH;
    File NO_FILE;



    @Before
    public void setUp() throws Exception {

        directedAcyclicGraph=new DirectedAcyclicGraph();
        BASIC_GRAPH=new File(TEST_INPUT_GRAPH_FILES_DIRECTORY+"basic-graph.txt");
        BASIC_PARALLEL_GRAPH=new File(TEST_INPUT_GRAPH_FILES_DIRECTORY+"basic-parallel-graph.txt");
        LOOP_GRAPH=new File(TEST_INPUT_GRAPH_FILES_DIRECTORY+"basic-parallel-graph.txt");
        INVALID_GRAPH_CONTENTS=new File(TEST_INPUT_GRAPH_FILES_DIRECTORY+"invalid-graph-contents.txt");
        COMPLEX_GRAPH=new File(TEST_INPUT_GRAPH_FILES_DIRECTORY+"complex-graph.txt");
        NO_FILE=new File(TEST_INPUT_GRAPH_FILES_DIRECTORY+"blablabla");

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void test_should_return_shortest_cost() throws FileNotFoundException {
        directedAcyclicGraph.createGraphFromFile(BASIC_GRAPH);
        assertEquals("Cheapest path from:A to:C is A=>D=>B=>C, 1.7",directedAcyclicGraph.findCheapestPath("A","C"));

    }

    @Test
    public void test_should_return_shortest_cost_if_two_direct_links_available() throws FileNotFoundException {
        directedAcyclicGraph.createGraphFromFile(BASIC_PARALLEL_GRAPH);
        assertEquals("Cheapest path from:A to:D is A=>B=>D, 2.7",directedAcyclicGraph.findCheapestPath("A","D"));

    }

    @Test
    public void test_should_return_cost_zero_if_source_and_destination_are_the_same() throws FileNotFoundException {
        directedAcyclicGraph.createGraphFromFile(BASIC_PARALLEL_GRAPH);
        assertEquals("Cheapest path from:D to:D is D, 0.0",directedAcyclicGraph.findCheapestPath("D","D"));
    }

    @Test
    public void test_should_return_cannot_find_path_if_no_path_is_found() throws FileNotFoundException {
        directedAcyclicGraph.createGraphFromFile(BASIC_PARALLEL_GRAPH);
        assertEquals("Cannot find path from:D to:A",directedAcyclicGraph.findCheapestPath("D","A"));
    }
    @Test
    public void test_should_return_nodes_not_found_when_input_node_not_found() throws FileNotFoundException {
        directedAcyclicGraph.createGraphFromFile(BASIC_GRAPH);
        assertEquals("Cannot find Source Node: A, or Destination Node: N in the Graph",directedAcyclicGraph.findCheapestPath("A","N"));
    }

    @Test(expected=FileNotFoundException.class)
    public void test_should_throw_exception_when_file_not_found() throws FileNotFoundException {
        directedAcyclicGraph.createGraphFromFile(NO_FILE);
    }

    @Test(expected=FileNotFoundException.class)
    public void test_should_throw_exception_when_file_is_null() throws FileNotFoundException {
        directedAcyclicGraph.createGraphFromFile(null);
    }



}