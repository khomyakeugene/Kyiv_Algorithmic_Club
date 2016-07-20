package com.company.algorithms.revert;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Yevhen on 20.07.2016.
 */
public class NodeTest {
    private IntNode intNode = new IntNode(1, new IntNode(2, new IntNode(3, new IntNode(4, new IntNode(5, new IntNode(6, null))))));

    @BeforeClass
    public static void setUpClass() throws Exception {


    }

    @Test
    public void revertTest() throws Exception {
        Node.revert(intNode);

    }

}