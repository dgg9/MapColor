package com.map.color;

import static org.junit.Assert.assertTrue;

import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;

public class RangeBalanceTreeTest {
  RangeBalancedTree testTree = new RangeBalancedTree();
  
  @Before
  public void init() throws UnknownHostException{
    testTree.createRangeNode("10.20.10.1", "10.20.10.255", "CALIFORNIA");
    testTree.createRangeNode("10.20.20.1", "10.20.20.255", "NEVADA");
    testTree.createRangeNode("10.20.30.1", "10.20.30.255", "ILLINOIS");
    testTree.createRangeNode("10.20.40.1", "10.20.40.20", "WASHINGTON");
    testTree.createRangeNode("10.20.50.1", "10.20.50.255", "NEW YORK");
    testTree.createRangeNode("10.20.60.1", "10.20.60.255", "GEORGIA");
    testTree.createRangeNode("10.20.70.1", "10.20.70.255", "ALASKA");
    testTree.createRangeNode("10.20.40.10", "10.20.40.255", "SEATTLE");
    testTree.createRangeNode("10.20.90.1", "10.20.90.255", "NEBRASKA");
    testTree.createRangeNode("10.20.100.1", "10.20.100.255", "TEXAS");
    testTree.createRangeNode("10.20.10.21", "10.20.10.55", "MOUNTAIN VIEW");
  }
  
  @Test
  public void testSimple() {
    assertTrue(false);
  }
}
