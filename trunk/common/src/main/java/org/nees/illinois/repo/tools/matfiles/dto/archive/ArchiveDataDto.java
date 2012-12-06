package org.nees.illinois.repo.tools.matfiles.dto.archive;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class ArchiveDataDto {	
	private final List<List<Double>> data = new ArrayList<List<Double>>();
	private final Logger log = Logger.getLogger(ArchiveDataDto.class);
	public void addRow(List<Double> row) {
		log.debug("Adding row: " + dumpRow(row));
		data.add(row);
	}
	public List<Double> getRow(int r) {
		return data.get(r);
	}
	public void clear() {
		data.clear();
	}
	public int numRows() {
		return data.size();
	}
	public String dumpRow(List<Double>row) {
	String result = "";
	for (Double r : row) {
		result += r + ",";
	}
	result += "\n";
	return result;
	}
}
