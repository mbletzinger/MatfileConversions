package org.nees.illinois.matfiles.archive.filetypes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.nees.illinois.matfiles.dto.datatypes.DataElement;
import org.nees.illinois.repo.tools.matfiles.dto.archive.ArchiveFileProperties;

public class OmVoltsArchive implements ArchiveFileProperties {
	private final String sp = System.getProperty("file.separator");
	private final String type = "Volts";

	public OmVoltsArchive() {
		super();
	}

	@Override
	public List<String> getDataFilename(String root) {
		List<String> result = new ArrayList<String>();
		File path = new File(root);
		String[] files = path.list();
		for (String f : files) {
			if (f.contains("LBCB1_" + type)) {
				result.add(root + sp + f);
			}
		}
		return result;
	}

	@Override
	public String getHeaderFilename(String root) {
		return root + sp + type + "__headers.txt";
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return "Volts";
	}
	@Override
	public List<DataElement> getDataTypes() {
		return null;
	}
}
