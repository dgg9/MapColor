package com.map.color;

class RegionImpl implements Region {

  String region; 
  
  public RegionImpl(String region) {
    this.region = region;
  }
  
  public String getName() {
    return this.region;
  }

}
