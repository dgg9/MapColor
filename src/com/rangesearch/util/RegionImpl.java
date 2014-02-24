package com.rangesearch.util;

import java.util.List;

import com.rangesearch.tree.BuildMapImpl;

public class RegionImpl implements Region {

  private StringBuilder region;

  public RegionImpl(List<String> subRegions) {
    region = new StringBuilder();
    for (String subRegion : subRegions) {
      if (region.length() > 0) {
        region.append(BuildMapImpl.INPUT_VALUE_SEPARATOR + subRegion);
      } else {
        region.append(subRegion);
      }
    }
  }

  public RegionImpl(String subRegions) {
    region = new StringBuilder(subRegions);
  }

  @Override
  public String getName() {
    return this.region.toString();
  }
  
  @Override
  public String toString() {
    return this.region.toString();
  }

}
