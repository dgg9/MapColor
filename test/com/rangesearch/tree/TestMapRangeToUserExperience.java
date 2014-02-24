package com.rangesearch.tree;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.rangesearch.util.Range;

public class TestMapRangeToUserExperience {

  MapRangeToUserExperience rangeMap;

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testMapExists() {
    rangeMap = new MapRangeToUserExperience();
    assertNotNull(rangeMap.rangeList);
    assertNotNull(rangeMap.arrKeys);
  }

  @Test
  public void testRangeOrdering() {
    rangeMap = new MapRangeToUserExperience();
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
    rangeMap = new MapRangeToUserExperience();
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

}
