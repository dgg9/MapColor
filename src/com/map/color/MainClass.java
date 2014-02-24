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
    MapController appDynamicsUsage = new MapController(new MapViewImpl());
    //for(int i = 0; i < 255; i++)
      appDynamicsUsage.hit("10.20.10.21", 10000);
  }
}
