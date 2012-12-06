package org.nees.illinois.matfiles.fileconverter;

import org.nees.matfiles.fileconverter.swing.ArchiveText2MatfileFrame;

public class ArchiveText2Matfile {

	private ArchiveText2MatfileFrame gui;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final ArchiveText2Matfile dm = new ArchiveText2Matfile();
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				dm.startGui();
			}
		});

	}
	public ArchiveText2Matfile() {
		gui = new ArchiveText2MatfileFrame();
	}
	public void startGui() {
		gui.display();
	}
}
