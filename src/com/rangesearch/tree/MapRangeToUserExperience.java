package com.rangesearch.tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

import com.map.color.UserExperience;
import com.rangesearch.exception.ExceptionMessages;
import com.rangesearch.util.Range;

public class MapRangeToUserExperience extends BuildMapImpl<Long, UserExperience> {

  private static String INPUT_FILE_PATH = "UserExperience";

  LinkedHashMap<Range<Long>, UserExperience> rangeList = new LinkedHashMap<Range<Long>, UserExperience>();
  List<Range<Long>> arrKeys = null;

  public MapRangeToUserExperience() {
    this.buildMap();
    this.arrKeys = new ArrayList<Range<Long>>(this.rangeList.keySet());
  }

  public void buildMap() {
    LinkedHashMap<Range<Long>, UserExperience> userExperiences = new LinkedHashMap<Range<Long>, UserExperience>();
    Scanner sc = null;
    try {
      sc = new Scanner(new File(INPUT_FILE_PATH));
      while (sc.hasNext()) {
        String line = sc.nextLine();
        if (!line.startsWith("#")) {
          String[] args = line.split(",");
          Long until = (args[1].equals("-1")) ? Long.MAX_VALUE : new Long(
              args[1]);
          Range<Long> range = new Range<Long>(new Long(args[0]), until);
          userExperiences.put(range, UserExperience.valueOf(args[2]));
        }
      }
    } catch (FileNotFoundException e) {
      System.out.println(ExceptionMessages.FILE_NOT_FOUND);
    } finally {
      if(sc != null) sc.close();
    }
    this.rangeList = userExperiences;
  }

  public UserExperience query(Long ip) {
    return query(ip, this.rangeList, this.arrKeys);
  }

  public void printRangeMap() {
    super.printRangeMap(this.rangeList);
  }
}
