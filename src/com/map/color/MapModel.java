package com.map.color;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.map.color.MapController.Range;

public class MapModel {

  /**
   * Return back a constructed RangeBalancedTree
   * @return
   */
  public Map<Range<Inet4Address>, Region> parseFile() {
    Map<Range<Inet4Address>, Region> rangeToRegion = new LinkedHashMap<MapController.Range<Inet4Address>, Region>();
    Scanner sc = null;
    try {
      sc = new Scanner(new File("IP"));
      while (sc.hasNext()) {
        String line = sc.nextLine();
        if(! line.startsWith("#")) {
          String[] args = line.split(",");
          Inet4Address startIP = (Inet4Address) Inet4Address.getByName(args[0]);
          Inet4Address endIP = (Inet4Address) Inet4Address.getByName(args[1]);
          Range<Inet4Address> range = new Range<Inet4Address>(startIP, endIP);
          Region region = new RegionImpl(args[2]);
          rangeToRegion.put(range, region);
        }
      }
      printRegionMap(rangeToRegion);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (UnknownHostException e) {
      e.printStackTrace();
    } finally {
      if (sc != null)
        sc.close();
    }
    return rangeToRegion;
  }

  public Map<Range<Long>, UserExperience> buildUserExpMap() {
    Map<Range<Long>, UserExperience> userExperiences = new HashMap<MapController.Range<Long>, UserExperience>();
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

  void printRegionMap(Map<Range<Inet4Address>, Region> rangeToRegion) {
    for (Map.Entry<Range<Inet4Address>, Region> entry : rangeToRegion.entrySet()) {
      // System.out.println((((Inet4Address)
      // entry.getKey().from).getHostAddress()
      // + "  " + ((Inet4Address) entry.getKey().until).getHostAddress()));
      System.out.println("from = " + entry.getKey().from.hashCode());
      System.out.println("until = " + entry.getKey().until.hashCode());
      // System.out.println(" " + (((entry.getValue()).get(0)).getName()));
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
