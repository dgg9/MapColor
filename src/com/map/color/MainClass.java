package com.map.color;


public class MainClass {

  /**
   * This would read through a flat file - process IPs and regions and build a
   * concurrent hashmap data structure - Call MapController constructor and set
   * the rule engine -
   * 
   * @param args
   */
  public static void main(String[] args) {
    (new MapModel()).parseFile();
  }

}
