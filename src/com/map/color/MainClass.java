package com.map.color;

/**
 * This can become a Jerseys 
 * @author gayash
 *
 */
public class MainClass {

  public static void main(String[] args) {
    MapController appDynamicsUsage = new MapController(new MapViewImpl());
      appDynamicsUsage.hit("45.32.190.27", 8000);
    }
}
