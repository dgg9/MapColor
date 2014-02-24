package com.rangesearch.tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.map.color.UserExperience;
import com.rangesearch.util.Range;

public class TestMapRangeToUserExperience {

  MapRangeToUserExperience rangeMap;

  @Before
  public void setUp() throws Exception {
    rangeMap = new MapRangeToUserExperience();
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testMapExists() {
    assertNotNull(rangeMap.rangeList);
    assertNotNull(rangeMap.arrKeys);
  }

  @Test
  public void testRangeOrdering() {
    assertNotNull(rangeMap);
    Iterator<Range<Long>> itr = rangeMap.arrKeys.iterator();
    assertNotNull(itr);
    while (itr.hasNext()) {
      Range<Long> range = itr.next();
      assertTrue(range.from.compareTo(range.until) < 0);
    }
  }

  @Test
  public void testRangeSetOrdering() {
    assertNotNull(rangeMap);
    Iterator<Range<Long>> itr = rangeMap.arrKeys.iterator();
    assertNotNull(itr);
    Range<Long> curr = itr.next();
    Range<Long> next;
    while (itr.hasNext()) {
      next = itr.next();
      assertTrue(curr.until.compareTo(next.from) <= 0);
      curr = next;
    }
  }

  @Test
  public void testQuery() throws Exception {
    UserExperience userExperience = rangeMap.query(Math.abs((new Random()).nextLong()));
    assertNotNull(userExperience);
  }

  @Test
  public void testQueryFail(){
    try {
      rangeMap.query(new Long(-1));
    } catch (Exception e) {
      assertTrue(true);
    }
  }
  
  @Test
  public void testWithOverlappingResponseTime() throws Exception{
    UserExperience userExperience = rangeMap.query(new Long(10000));
    assertEquals(userExperience.name(), "VERY_SLOW");

    userExperience = rangeMap.query(new Long(5000));
    assertEquals(userExperience.name(), "SLOW");

    userExperience = rangeMap.query(new Long(20000));
    assertEquals(userExperience.name(), "STALLED");
    
    userExperience = rangeMap.query(new Long(0));
    assertEquals(userExperience.name(), "NORMAL");
  }
  
}
