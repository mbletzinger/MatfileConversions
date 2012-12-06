package org.nees.illinois.matfiles.archive.filetypes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.nees.illinois.matfiles.dto.datatypes.DataElement;
import org.nees.illinois.matfiles.dto.datatypes.StiffnessColumnType;
import org.nees.illinois.repo.tools.matfiles.dto.archive.ArchiveFileProperties;

public class OmStiffnessArchive implements ArchiveFileProperties {
	private final String name;
	private final String sp = System.getProperty("file.separator");
	private final String type = "StiffnessUpdates";
	private final List<DataElement> dtypes = new ArrayList<DataElement>();

	public OmStiffnessArchive(String name) {
		super();
		this.name = name;
		dtypes.add(new StiffnessColumnType());
	}

	@Override
	public List<String> getDataFilename(String root) {
		List<String> result = new ArrayList<String>();
		File path = new File(root);
		String[] files = path.list();
		for (String f : files) {
			if (f.contains(name + "_" + type)
					&& (f.contains("header") == false)) {
				result.add(root + sp + f);
			}
		}
		return result;
	}

	@Override
	public String getHeaderFilename(String root) {
		return root + sp + name + "_" + type + "_headers.txt";
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	@Override
	public List<DataElement> getDataTypes() {
		return dtypes;
	}
}
