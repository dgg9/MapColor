package com.map.color;

import java.util.Date;

class MapViewImpl implements MapView {

  @Override
  public void lightUp(Region region, UserExperience userExperience) {
    System.out.println(new Date() + ": show " + region.getName() + " in color "
        + "for " + userExperience);
  }

}
