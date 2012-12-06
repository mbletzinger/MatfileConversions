package org.nees.illinois.matfiles.archive.filetypes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.nees.illinois.matfiles.convert.filters.LbcbPluginArchiveStepIndexFilter;
import org.nees.illinois.repo.tools.matfiles.dto.archive.ArchiveFileProperties;
import org.nees.illinois.repo.tools.matfiles.dto.archive.ArchiveFilter;
import org.nees.illinois.repo.tools.matfiles.dto.archive.ArchiveFolderProperties;

public class OmStepArchiveFolder implements ArchiveFolderProperties {
	private final List<ArchiveFileProperties> pfiles = new ArrayList<ArchiveFileProperties>();
	private final List<ArchiveFilter> filters = new ArrayList<ArchiveFilter>();

	public OmStepArchiveFolder() {
}

	@Override
	public boolean isArchive(String folder) {
		File path = new File(folder);
		String [] files = path.list();
		boolean found = false;
		for (String f : files) {
			if (f.contains( pfiles.get(0).getName() + "_Step")) {
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
		return filters;
	}

	@Override
	public void scan(String folder) {
		ArchiveFileProperties afp = new OmStepArchive("LBCB1");
		pfiles.add(afp);
		afp = new OmStepArchive("LBCB2");
		pfiles.add(afp);
		afp = new OmStepArchive("ExternalSensors");
		pfiles.add(afp);
		afp = new OmStepArchive("Combined");
		pfiles.add(afp);
		ArchiveFilter f = new LbcbPluginArchiveStepIndexFilter();
		filters.add(f);		
	}

	@Override
	public String getExtension() {
		return "OM_STEP";
	}

	@Override
	public String getName() {
		return "OM Step Archive";
	}

}
