package org.nees.illinois.repo.tools.matfiles.dto.archive;

import java.util.List;

import org.nees.illinois.matfiles.dto.datatypes.DataElement;


public interface ArchiveFileProperties {
	
	public String getHeaderFilename(String root);
	public List<String> getDataFilename(String root);
	public String getName();
	public List<DataElement> getDataTypes();
}
