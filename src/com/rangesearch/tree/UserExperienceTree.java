package com.rangesearch.tree;

import static com.rangesearch.exception.ExceptionMessages.INVALID_INPUT_TYPE;

import java.io.File;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import com.rangesearch.exception.ExceptionMessages;
import com.rangesearch.inetaddress.InetAddressDecorator;
import com.rangesearch.userexperience.UserExperience;
import com.rangesearch.userexperience.UserExperienceDecorator;
import com.rangesearch.util.Range;
import com.rangesearch.util.Region;
import com.rangesearch.util.RegionImpl;

public class UserExperienceTree {
//
//	private static String INPUT_FILE_PATH = "userExperience.txt";
//
//	
//	private LinkedHashMap<Range<Long>, Region> rangeList = new LinkedHashMap<Range<Long>, Region>(); 
//	private List<Range<InetAddressDecorator>> arrKeys;
//	
//	public Map<Range<Long>, UserExperience> buildUserExpMap() {
//		   Map<Range<Long>, UserExperience> userExperiences = new HashMap<MapController.Range<Long>, UserExperience>();
//		   Scanner sc = null;
//		   try {
//		     sc = new Scanner(new File("UserExperience"));
//		     while (sc.hasNext()) {
//		       String line = sc.nextLine();
//		       if(! line.startsWith("#") ) {
//		         String[] args = line.split(",");
//		         Long until = (args[1].equals("-1"))? Long.MAX_VALUE:new Long(args[1]);
//		         Range<Long> range = new Range<Long>(new Long(args[0]), until);
//		         userExperiences.put(range, UserExperience.valueOf(args[2]));
//		       }
//		     }
//		     printUserExperience(userExperiences); 
//		   } catch (FileNotFoundException e) {
//		     e.printStackTrace();
//		   } finally {
//		     sc.close();
//		   }
//		   return userExperiences;
//		 }
//
//	@Override
//	public LinkedHashMap<Range<UserExperience>, Region> buildMap(
//			Set<String> boundarySet) {
//		/*
//		 * We create a mapping of range nodes to the regions
//		 */
//		LinkedHashMap<Range<UserExperience>, Region> rangeList = new LinkedHashMap<Range<UserExperience>, Region>();
//
//		Object[] array = boundarySet.toArray();
//
//		/*
//		 * The list [elementsInRange] has the list of all elements that can
//		 * occur in the current range being processed.
//		 */
//		List<String> elementsInRange = new ArrayList<String>();
//		/*
//		 * The list [processedElements] contains the list of all elements that
//		 * have been processed completely. These values should be removed from
//		 * the working set.
//		 */
//		Set<String> processedElements = new HashSet<String>();
//
//		/*
//		 * We process the set 2 elements at a time to create sub-ranges. So we
//		 * start at the beginning and process till the penultimate element
//		 * 
//		 * Each element in the set of the form [boundary]:[value].
//		 */
//		String[] currElement, nextElement;
//
//		String currElementValue, nextElementValue;
//		Long lowerBound, upperBound;
//
//		// Initializing the current element value and the lower bound
//		currElement = ((String) array[0]).split(SET_VALUE_SEPARATOR);
//		lowerBound = Long.parseLong(currElement[0]);
//		currElementValue = currElement[1];
//
//		for (int i = 1; i < array.length; i++) {
//			// Initializing the next element value and the upper bound
//			nextElement = ((String) array[i]).split(SET_VALUE_SEPARATOR);
//			upperBound = Long.parseLong(nextElement[0]);
//			nextElementValue = nextElement[1];
//
//			/*
//			 * Add the current element to the list of elements in range only if
//			 * it does not exist and is not processed. This signifies that the
//			 * element has not yet been encountered for the current range and
//			 * hence, it's lower bound will be set.
//			 */
//			if (!elementsInRange.contains(currElementValue)
//					&& !processedElements.contains(currElementValue)) {
//				elementsInRange.add(currElementValue);
//			}
//
//			/*
//			 * Add the next element to the processed elements list if it has
//			 * already occurred once in the elements in range. This indicates
//			 * that for the given element - we have already encountered both the
//			 * lower bound and the upper bound - so it should be considered as
//			 * processed.
//			 */
//			if (elementsInRange.contains(nextElementValue)) {
//				processedElements.add(nextElementValue);
//			}
//
//			UserExperienceDecorator lowerLimit = new UserExperienceDecorator(
//					lowerBound);
//
//			UserExperienceDecorator upperLimit = new UserExperienceDecorator(
//					upperBound);
//
//			/*
//			 * Update the data structure with the newly created range and the
//			 * elements in the range.
//			 */
//
//			rangeList.put(new Range<UserExperienceDecorator>(lowerLimit,
//					upperLimit),
//					new RegionImpl<UserExperience>(elementsInRange));
//
//			/*
//			 * After updating the datastructure, we should remove all processed
//			 * elements from the list of elements in current range.
//			 */
//			elementsInRange.removeAll(processedElements);
//
//			/*
//			 * We continue the iteration by updating the current element value
//			 * and the lower bound
//			 */
//			currElementValue = nextElementValue;
//			lowerBound = upperBound;
//		}
//
//		return rangeList;
//	}

}
