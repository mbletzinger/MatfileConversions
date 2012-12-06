package org.nees.matfiles.fileconverter.swing;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class ExtensionFileFilter extends FileFilter {

	private String extension;
	public ExtensionFileFilter(String extension) {
		super();
		this.extension = extension;
	}

	@Override
	public boolean accept(File f) {
	    if (f.isDirectory()) {
	        return true;
	    }
		String path = f.getPath();
		char delim = path.charAt(path.length() - extension.length() - 1);
		if(path.endsWith(extension) && delim == '.') {
			return true;
		}
		return false;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}
