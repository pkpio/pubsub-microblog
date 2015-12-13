package com.microblog.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Model of a message generated from a user / client
 * 
 * @author praveen
 *
 */
public class BlogMessage implements Serializable {
	private static final long serialVersionUID = 3510365478291108428L;
	String username;
	ArrayList<String> tags;
	String text;

	public BlogMessage(String username, ArrayList<String> tags, String text) {
		this.username = username;
		this.tags = tags;
		this.text = text;
	}

	public BlogMessage(String username, String text) {
		this.username = username;
		this.tags = new ArrayList<String>();
		this.text = text;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}

	/**
	 * Add one more tag to the existing list of tags
	 */
	public void addTag(String tag) {
		if (tags == null)
			tags = new ArrayList<String>();
		tags.add(tag);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
