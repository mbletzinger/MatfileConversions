package org.nees.illinois.matfiles.archive.filetypes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.nees.illinois.repo.tools.matfiles.dto.archive.ArchiveFileProperties;
import org.nees.illinois.repo.tools.matfiles.dto.archive.ArchiveFilter;
import org.nees.illinois.repo.tools.matfiles.dto.archive.ArchiveFolderProperties;

public class KryptonContinuousArchiveFolder implements ArchiveFolderProperties {
	private final List<ArchiveFileProperties> pfiles = new ArrayList<ArchiveFileProperties>();
	private final String sp = System.getProperty("file.separator");

	public KryptonContinuousArchiveFolder() {
	}

	@Override
	public boolean isArchive(String folder) {
		File path = new File(folder);
		String[] files = path.list();
		boolean found = false;
		for (String f : files) {
			if (f.equals("CONT") == false) {
				continue;
			}
			File path1 = new File(folder + sp + f);
			String[] files1 = path1.list();
			for (String fl : files1) {
				if (fl.contains("Samples")) {
					found = true;
					break;
				}
			}
			break;
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
		String path = folder + sp + "CONT";
		File pathF = new File(path);
		String[] files = pathF.list();
		if (files == null) {
			return;
		}
		for (String f : files) {
			int found = f.indexOf("_HDR");
			if (found < 0) {
				continue;
			}
			String name = f.substring(0, found);
			ArchiveFileProperties afp = new KryptonContinuousArchive(name);
			pfiles.add(afp);
		}
	}

	@Override
	public String getExtension() {
		return "Krypton_CONT";
	}

	@Override
	public String getName() {
		return "Krypton Continuous Archive";
	}
}
