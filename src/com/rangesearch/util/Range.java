package com.rangesearch.util;

/**
 * Represents a range of some ordered type.
 */
public class Range<T extends Comparable<T>> {
	public final T from;
	public final T until;

	public Range(T from, T until) {
		this.from = from;
		this.until = until;
	}

	/**
	 * 
	 * @param T
	 *            obj object checked if in the range of the InetAddressDecorator
	 * @return -1 if obj is greater than the (Range.from and Range.until) <br/>
	 *         0 if obj in between [Range.from, Range.until) <br/>
	 *         1 if obj is less (Range.from, Range.until) <br/>
	 */
	public int inRange(T obj) {
		if (this.from.compareTo(obj) > 0) {
			return 1;
		} else if ((this.from.compareTo(obj) <= 0)
				&& (obj.compareTo(this.until) < 0)) {
			return 0;
		}
		return -1;
	}
}