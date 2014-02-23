package com.map.color;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.bcel.internal.classfile.Utility;
import com.sun.org.apache.xml.internal.utils.UnImplNode;

public class RangeBalancedTree {

  private RangeNode root; // Root of the BST 
  
  class RangeNode {
    InetAddress lowerLim;                        // IP Address lower limit
    InetAddress upperLim;                        // IP Address upper limit
    List<Region> data = new ArrayList<Region>(); // Regions list for a given range 
    RangeNode leftChild, rightChild, parent;     // Left and right sub trees
    int N;                                       // Node count of childrens for balancing

    public RangeNode(InetAddress lowerLim, InetAddress upperLim, 
                     RangeNode parent) {
      this.lowerLim = lowerLim;
      this.upperLim = upperLim;
      this.parent = parent;
      this.N = 1;
    }

    public RangeNode(InetAddress lowerLim, InetAddress upperLim, 
        String region, RangeNode parent) {
        this.lowerLim = lowerLim;
        this.upperLim = upperLim;
        this.parent = parent;
        this.data.add(new RegionImpl(region));
        this.N = 1;
    }    
    
    public void updateData(String region){
      this.data.add(new RegionImpl(region));
    }
    
    public void updateData(Region region){
      this.data.add(region);
    }

    public boolean contains(InetAddress ip) {
      throw new UnsupportedOperationException("yet to be implemented");
    }
  }

  
  
  
  
  public void addNode(RangeNode node) {
    
  }
  
  public void addAll(List<RangeNode> nodes) {
    
  }
    
  public void listNodes(RangeNode node) {
    
  }
  
  public List<Region> queryData(Inet4Address ipAddr) {
    throw new UnsupportedOperationException("Need to implement");
  }
  
}
