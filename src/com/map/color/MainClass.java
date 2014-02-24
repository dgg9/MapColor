package com.map.color;

/**
 * This can be exposed as a RestEndPoint.
 * @author gayash
 *
 */
public class MainClass {

  public static void main(String[] args) {
    MapController appDynamicsUsage = new MapController(new MapViewImpl());
    // Dummy ip.   
    appDynamicsUsage.hit("45.32.190.27", 8000);
    }
}
