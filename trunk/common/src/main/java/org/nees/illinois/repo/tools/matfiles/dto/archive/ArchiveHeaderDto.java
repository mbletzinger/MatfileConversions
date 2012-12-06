package org.nees.illinois.repo.tools.matfiles.dto.archive;

import java.util.ArrayList;
import java.util.List;

public class ArchiveHeaderDto {
	private final List<String> headers = new ArrayList<String>();
	public ArchiveHeaderDto() {
		super();
	}
	/**
	 * @return the headers
	 */
	public List<String> getHeaders() {
		List<String> result = new ArrayList<String>();
		result.addAll(headers);
		return result;
	}
	/**
	 * @return the addTime
	 */
	public int numColumns() {
		return headers.size();
	}
	/**
	 * @return the headers
	 */
	public String [] getHeaderStringArray() {
		String [] result = new String[headers.size()];
		int i = 0;
		for (String h : headers) {
			result[i] = h;
			i++;
		}
		return result;
	}
	public void addHeader(String h) {
		headers.add(h);
	}
}
