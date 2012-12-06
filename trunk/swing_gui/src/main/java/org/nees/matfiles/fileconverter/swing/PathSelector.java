package org.nees.matfiles.fileconverter.swing;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.nees.matfiles.fileconverter.swing.FilenamesDTO.FilenameType;

public class PathSelector extends JPanel implements ActionListener {

	/**
	 * @return the isDirectory
	 */
	public boolean isDirectory() {
		return isDirectory;
	}

	/**
	 * @param isDirectory the isDirectory to set
	 */
	public void setDirectory(boolean isDirectory) {
		this.isDirectory = isDirectory;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4665425858504491343L;
	private JButton button;
	private String dialogTitle;
	private boolean isDirectory;
	private String extension;
	private JTextArea field;
	private String filename;
	private String label;
	private FilenamesDTO dto;
	private FilenameType type;
	public PathSelector(FilenamesDTO dto,FilenameType type) {
		super();
		this.dto = dto;
		this.type = type;
		BoxLayout layout = new BoxLayout(this,BoxLayout.Y_AXIS);
		setLayout(layout);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		if (e.getSource() == button) {
			fc.setDialogTitle(dialogTitle);
			if ( isDirectory) {
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			} else {
				fc.setFileFilter(new ExtensionFileFilter(extension));
			}
			fc.setCurrentDirectory(dto.getCurrentDirectory());
			int result = fc.showOpenDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				dto.setCurrentDirectory(file.getParentFile());
				filename = file.getPath();
				field.setText(filename);
				dto.setFilename(filename, type);
			}
			return;
		}
	}

	public void createGui(Container parent) {
		field = new JTextArea(1,50);
		field.setEditable(false);
		field.setLineWrap(true);
		field.setBorder(BorderFactory.createTitledBorder(label));
		button = new JButton(isDirectory ? "Select Directory..." : "Select File...");
		button.addActionListener(this);
		add(field);
		add(button);
		parent.add(this);
	}

	public String getDialogTitle() {
		return dialogTitle;
	}

	public String getExtension() {
		return extension;
	}

	public String getFilename() {
		return filename;
	}

	public String getLabel() {
		return label;
	}

	public void setDialogTitle(String dialogTitle) {
		this.dialogTitle = dialogTitle;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}

}
