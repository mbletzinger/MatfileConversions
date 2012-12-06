package org.nees.illinois.matfiles.archive.filetypes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.nees.illinois.matfiles.dto.datatypes.DataElement;
import org.nees.illinois.repo.tools.matfiles.dto.archive.ArchiveFileProperties;

public class LbcbPluginArchive implements ArchiveFileProperties {
	private final String name;
	private final String sp = System.getProperty("file.separator");
	public LbcbPluginArchive(String name) {
		super();
		this.name = name;
	}

	@Override
	public List<String> getDataFilename(String root) {
		List<String> result = new ArrayList<String>();
		File path = new File(root);
		String[] files = path.list();
		String hdr = getHeaderBasename();
		for (String f : files) {
			if (f.contains(name + "_" ) && (f.equals(hdr) == false)) {
				result.add(root + sp + f);
			}
		}
		return result;
	}

	@Override
	public String getHeaderFilename(String root) {
		return root + sp + getHeaderBasename();
	}

	private String getHeaderBasename() {
		return name + "_hdr.txt";
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	@Override
	public List<DataElement> getDataTypes() {
		return null;
	}
}
