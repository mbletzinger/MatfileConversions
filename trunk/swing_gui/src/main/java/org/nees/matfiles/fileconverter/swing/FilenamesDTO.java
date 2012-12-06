package org.nees.matfiles.fileconverter.swing;

import java.io.File;

public class FilenamesDTO {

	public enum FilenameType { ARCHIVEFOLDER,MATFILENAME};
	private File currentDirectory = new File(System.getProperty("user.dir"));
	private String matfilename;
	private String archiveFolder;
	
	public FilenamesDTO() {
		super();
	}
	public File getCurrentDirectory() {
		return currentDirectory;
	}
	public String getMatfilename() {
		return matfilename;
	}
	public String getArchiveFolder() {
		return archiveFolder;
	}
	public void setCurrentDirectory(File currentDirectory) {
		this.currentDirectory = currentDirectory;
	}
	public void setFilename(String filename, FilenameType type) {
		if(type == FilenameType.ARCHIVEFOLDER) {
			setArchiveFolder(filename);
			return;
		} 
		if(type == FilenameType.MATFILENAME) {
			setMatfilename(filename);
			return;
		} 
	}
	public void setMatfilename(String iniFilename) {
		this.matfilename = iniFilename;
	}
	public void setArchiveFolder(String spreadsheetFilename) {
		this.archiveFolder = spreadsheetFilename;
	}
	
}
