package com.microblog.util;

import java.util.Arrays;
import java.util.List;

public class Helper {

	/**
	 * Get tags as a list of strings from a CSV
	 * 
	 * @param tagsInCSV
	 * @return
	 */
	public static List<String> getTags(String tagsInCSV) {
		return Arrays.asList(tagsInCSV.split("\\s*,\\s*"));
	}

	/**
	 * Get tags as a CSV
	 * 
	 * @param tags
	 * @return
	 */
	public static String getTagsCSV(List<String> tags) {
		String str = "";
		for (String tag : tags)
			str += tag + ", ";
		return str;
	}
}
