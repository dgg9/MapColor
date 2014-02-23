package com.map.color;

import java.util.List;

public class RangeBalancedTree {
  public RangeBalancedTree() {

  }

  public void addNode(RangeNode node) {
    
  }
  
  public void deleteNode(RangeNode node) {
    
  }
  
  public void listNodes(RangeNode node) {}
  
  public RangeNode queryData() {
    throw new UnsupportedOperationException("Need to implement");
  }
  
  class RangeNode {
    int lowerLim;
    int upperLim;
    List<String> data;
    RangeNode leftChild;
    RangeNode rightChild;

    public RangeNode(int lowerLim, int upperLim, List<String> data,
        RangeNode leftChild, RangeNode rightChild) {
      this.lowerLim = lowerLim;
      this.upperLim = upperLim;
      this.data = data;
      this.leftChild = leftChild;
      this.rightChild = rightChild;
    }
  }
}
