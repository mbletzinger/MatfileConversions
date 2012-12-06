package org.nees.illinois.matfiles.archive.filetypes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.nees.illinois.matfiles.dto.datatypes.DataElement;
import org.nees.illinois.repo.tools.matfiles.dto.archive.ArchiveFileProperties;

public class DaqStepArchive implements ArchiveFileProperties {
	private final String name;

	private final String rate = "STEP";

	private final String sp = System.getProperty("file.separator");
	private final String type;
	public DaqStepArchive(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}
	@Override
	public List<String> getDataFilename(String root) {
		String path = root + sp + rate + sp + type;
		List<String> result = new ArrayList<String>();
		File pathF = new File(path);
		for (String f : pathF.list()) {
			if (f.contains(name) && (f.contains("_HDR") == false)) {
				result.add(root + sp + rate + sp + type + sp + f);
			}
		}
		return result;
	}

	@Override
	public String getHeaderFilename(String root) {
		return root + sp + rate + sp + type + sp + name + "_HDR.txt";
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the rate
	 */
	public String getRate() {
		return rate;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	@Override
	public List<DataElement> getDataTypes() {
		return null;
	}
}
