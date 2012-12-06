package org.nees.illinois.repo.tools.matfiles.dto.archive;

import java.util.List;

public interface ArchiveFolderProperties {
	public boolean isArchive(String folder);
	public List<ArchiveFileProperties> getFiles();
	public List<ArchiveFilter> getFilters();
	public void scan(String folder);
	public String getExtension();
	public String getName();
}
