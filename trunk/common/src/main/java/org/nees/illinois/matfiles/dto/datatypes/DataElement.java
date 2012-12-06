package org.nees.illinois.matfiles.dto.datatypes;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public abstract class DataElement {
	protected final List<String> dataElements = new ArrayList<String>();

	private final Logger log = Logger.getLogger(DataElement.class);
	private final String name;
	public DataElement(String name) {
		this.name = name;
		fillElements();
		log.debug("List " + dataElements);
	}

	abstract public void fillElements();

	public List<String> getDataElements() {
		return dataElements;
	}

	public String getName() {
		return name;
	}

	public int text2Index(String text) {
		if (text.contains("Dx")) {
			log.debug("Comparing \"" + text + "\" to " + dataElements);
		}
		return dataElements.indexOf(text) + 1; // Use MATLAB base 1 indexing
	}
}
