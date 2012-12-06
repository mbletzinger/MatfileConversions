package org.nees.matfiles.fileconverter.swing;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.nees.matfiles.fileconverter.swing.FilenamesDTO.FilenameType;

public class ArchiveText2MatfileFrame {

	private JFrame frame;
	private PathSelector archiveFolderSelector;
	private ConvertPanel generate;
	private MatFileName project;

	public ArchiveText2MatfileFrame() {
		frame = new JFrame("Archive Text File to Matfile Conversion");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		JPanel filePanel = new JPanel();
		filePanel.setLayout(new BoxLayout(filePanel,BoxLayout.Y_AXIS));
		frame.setContentPane(mainPanel);
		FilenamesDTO dto = new FilenamesDTO();
		
		project = new MatFileName(dto);
		project.createGui(mainPanel);

		mainPanel.add(filePanel);

		archiveFolderSelector = new PathSelector(dto,FilenameType.ARCHIVEFOLDER);
		archiveFolderSelector.setDialogTitle("Select Archive Folder");
		archiveFolderSelector.setLabel("Archive Folder Location");
		archiveFolderSelector.setDirectory(true);
		archiveFolderSelector.createGui(filePanel);
		
		generate = new ConvertPanel(project,dto);
		generate.createGui(mainPanel);
		GuiAppender.setGenerate(generate);
	}
	public void display() {
		frame.pack();
		frame.setVisible(true);
	}
}
