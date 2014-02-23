package com.map.color;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.map.color.MapController.Range;

public class MapModel {
  Map<Range<Inet4Address>, Region> rangeToRegion = new LinkedHashMap<MapController.Range<Inet4Address>, Region>();
  Map<Range<Long>, UserExperience> userExperiences = new HashMap<MapController.Range<Long>, UserExperience>();

  public Map<Range<Inet4Address>, Region> parseFile() {

    Scanner sc = null;
    try {
      sc = new Scanner(new File("IP"));
      while (sc.hasNext()) {
        String line = sc.nextLine();
        String[] args = line.split(",");
        Inet4Address startIP = (Inet4Address) Inet4Address.getByName(args[0]);
        Inet4Address endIP = (Inet4Address) Inet4Address.getByName(args[1]);
        Range<Inet4Address> range = new Range<Inet4Address>(startIP, endIP);
        Region region = new RegionImpl(args[2]);
        rangeToRegion.put(range, region);
      }
      printRegionsMap();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (UnknownHostException e) {
      e.printStackTrace();
    } finally {
      if (sc != null)
        sc.close();
    }
    return null;
  }

  public Map<Range<Long>, UserExperience> buildUserExpMap() {
    Scanner sc = null;
    try {
      sc = new Scanner(new File("UserExperience"));
      while (sc.hasNext()) {
        String line = sc.nextLine();
        String[] args = line.split(",");
        Range<Long> range = new Range<Long>(new Long(args[0]),
            new Long(args[1]));
        userExperiences.put(range, UserExperience.valueOf(args[2]));
      }
      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } finally {
      sc.close();
    }
    return userExperiences;
  }

  void printRegionsMap() {
    for (Map.Entry<Range<Inet4Address>, Region> entry : rangeToRegion.entrySet()) {
      // System.out.println((((Inet4Address)
      // entry.getKey().from).getHostAddress()
      // + "  " + ((Inet4Address) entry.getKey().until).getHostAddress()));
      System.out.println("from = " + entry.getKey().from.hashCode());
      System.out.println("until = " + entry.getKey().until.hashCode());
      // System.out.println(" " + (((entry.getValue()).get(0)).getName()));
    }
  }
}
