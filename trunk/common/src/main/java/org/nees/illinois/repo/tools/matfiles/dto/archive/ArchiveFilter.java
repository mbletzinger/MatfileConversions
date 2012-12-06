package org.nees.illinois.repo.tools.matfiles.dto.archive;

import org.nees.illinois.repo.tools.matfiles.dto.archive.ArchiveDto;

public interface ArchiveFilter {
	public void setArchive(ArchiveDto ad);
	public ArchiveDto getArchive();
	public void filter();
}
