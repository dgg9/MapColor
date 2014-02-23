package com.map.color;

import com.map.color.MapViewImpl;
import com.map.color.RegionImpl;
import com.map.color.UserExperience;

public class TestMapViewImpl {

  public static void main(String args[]) {
    MapViewImpl mapview = new MapViewImpl();
    mapview.lightUp(new RegionImpl("California"), UserExperience.NORMAL);
  }
}
