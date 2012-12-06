package org.nees.illinois.matfiles.archive.filetypes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.nees.illinois.repo.tools.matfiles.dto.archive.ArchiveFileProperties;
import org.nees.illinois.repo.tools.matfiles.dto.archive.ArchiveFilter;
import org.nees.illinois.repo.tools.matfiles.dto.archive.ArchiveFolderProperties;

public class OmVoltsArchiveFolder implements ArchiveFolderProperties {
	private final List<ArchiveFileProperties> pfiles = new ArrayList<ArchiveFileProperties>();

	public OmVoltsArchiveFolder() {
	}

	@Override
	public boolean isArchive(String folder) {
		File path = new File(folder);
		String[] files = path.list();
		boolean found = false;
		for (String f : files) {
			if (f.contains("_Volts")) {
				found = true;
			}
		}
		return found;
	}

	@Override
	public List<ArchiveFileProperties> getFiles() {
		return pfiles;
	}

	@Override
	public List<ArchiveFilter> getFilters() {
		return null;
	}

	@Override
	public void scan(String folder) {
		ArchiveFileProperties afp = new OmVoltsArchive();
		pfiles.add(afp);

	}

	@Override
	public String getExtension() {
		return "OM_Voltss";
	}

	@Override
	public String getName() {
		return "OM Volts Archive";
	}


}
