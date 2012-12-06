package org.nees.illinois.matfiles.archive.filetypes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.nees.illinois.repo.tools.matfiles.dto.archive.ArchiveFileProperties;
import org.nees.illinois.repo.tools.matfiles.dto.archive.ArchiveFilter;
import org.nees.illinois.repo.tools.matfiles.dto.archive.ArchiveFolderProperties;

public class DaqStepArchiveFolder implements ArchiveFolderProperties {
	private final List<ArchiveFileProperties> pfiles = new ArrayList<ArchiveFileProperties>();
	private final String sp = System.getProperty("file.separator");
	public DaqStepArchiveFolder() {
}

	@Override
	public boolean isArchive(String folder) {
		File path = new File(folder);
		String [] files = path.list();
		boolean found = false;
		for (String f : files) {
			if (f.equals("STEP") == false) {
				continue;
			}
			File path1 = new File(folder + sp + f);
			String[] files1 = path1.list();
			for (String fl : files1) {
				if (fl.contains("DAQ")) {
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
		String [] subs = {"DAQ","OM","GEN"};
		for(String s : subs) {
			scanFolder((folder + sp + "STEP"), s);
		}
	}

	private void scanFolder(String folder, String sub) {
		String path = folder + sp + sub;
		File pathF = new File(path);
		String [] files = pathF.list();
		if(files == null) {
			return;
		}
		for (String f : files) {
				int found = f.indexOf("_HDR");
				if(found < 0) {
					continue;
			}
				String name = f.substring(0, found);
				ArchiveFileProperties afp = new DaqStepArchive(name,sub);
				pfiles.add(afp);
		}
	}

	@Override
	public String getExtension() {
		return "DAQ_STEP";
	}

	@Override
	public String getName() {
		return "DAQ Step Archive";
	}

}
