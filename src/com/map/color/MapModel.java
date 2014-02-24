package com.map.color;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.rangesearch.inetaddress.InetAddressDecorator;
import com.rangesearch.util.Range;
import com.rangesearch.util.Region;

public class MapModel {

  public Map<Range<Long>, UserExperience> buildUserExpMap() {
    Map<Range<Long>, UserExperience> userExperiences = new HashMap<Range<Long>, UserExperience>();
    Scanner sc = null;
    try {
      sc = new Scanner(new File("UserExperience"));
      while (sc.hasNext()) {
        String line = sc.nextLine();
        if(! line.startsWith("#") ) {
          String[] args = line.split(",");
          Long until = (args[1].equals("-1"))? Long.MAX_VALUE:new Long(args[1]);
          Range<Long> range = new Range<Long>(new Long(args[0]), until);
          userExperiences.put(range, UserExperience.valueOf(args[2]));
        }
      }
      printUserExperience(userExperiences); 
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } finally {
      sc.close();
    }
    return userExperiences;
  }

  void printRegionMap(Map<Range<InetAddressDecorator>, Region> rangeToRegion) {
    for (Map.Entry<Range<InetAddressDecorator>, Region> entry : rangeToRegion.entrySet()) {
      System.out.print((((InetAddressDecorator)entry.getKey().from).toString()
          + "  " + ((InetAddressDecorator) entry.getKey().until).toString()));
      //System.out.println("from = " + entry.getKey().from.hashCode());
      //System.out.println("until = " + entry.getKey().until.hashCode());
      System.out.println(" " + ((entry.getValue()).getName()));
    }
  }

  void printUserExperience(Map<Range<Long>, UserExperience> userExperiences) {
    for (Entry<Range<Long>, UserExperience> entry : userExperiences.entrySet()) {
        System.out.print((((Long)entry.getKey().from)
            + "  " + ((Long) entry.getKey().until)));
        System.out.println(" "+ entry.getValue());
    }
  }
}
