package com.map.color;

import com.rangesearch.util.Region;

/**
 * A user interface for the map of the world. A map contains many regions.
 */
interface MapView {
  /**
   * Shade the given region of the map, with the appropriate color based on the
   * given User experience.
   */
  public void lightUp(Region region, UserExperience userExperience);
}
