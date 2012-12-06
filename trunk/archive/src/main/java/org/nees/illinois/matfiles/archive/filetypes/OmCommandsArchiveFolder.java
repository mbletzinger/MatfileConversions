package org.nees.illinois.matfiles.archive.filetypes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.nees.illinois.repo.tools.matfiles.dto.archive.ArchiveFileProperties;
import org.nees.illinois.repo.tools.matfiles.dto.archive.ArchiveFilter;
import org.nees.illinois.repo.tools.matfiles.dto.archive.ArchiveFolderProperties;

public class OmCommandsArchiveFolder implements ArchiveFolderProperties {
	private final List<ArchiveFileProperties> pfiles = new ArrayList<ArchiveFileProperties>();

	public OmCommandsArchiveFolder() {
	}

	@Override
	public boolean isArchive(String folder) {
		File path = new File(folder);
		String[] files = path.list();
		boolean found = false;
		for (String f : files) {
			for (ArchiveFileProperties afp : pfiles) {
				if (f.contains(afp.getName() + "_Commands")) {
					found = true;
					break;
				}
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
		ArchiveFileProperties afp = new OmCommandsArchive("LBCB1");
		pfiles.add(afp);
		afp = new OmCommandsArchive("LBCB2");
		pfiles.add(afp);
		afp = new OmCommandsArchive("Combined");
		pfiles.add(afp);
	}

	@Override
	public String getExtension() {
		return "OM_Commands";
	}

	@Override
	public String getName() {
		return "OM Commands Archive";
	}
}
