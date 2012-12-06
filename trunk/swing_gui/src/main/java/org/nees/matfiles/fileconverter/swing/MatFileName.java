package org.nees.matfiles.fileconverter.swing;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MatFileName extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5791024260125375647L;
	private FilenamesDTO dto;
	private JTextField field;
	public MatFileName(FilenamesDTO dto) {
		super();
		this.dto = dto;
	}
	public void createGui(Container parent) {
		field = new JTextField("<Enter Matfile name and press <return>...>",60);
		field.setBorder(BorderFactory.createTitledBorder("Matfile Name"));
		field.addActionListener(this);
		field.selectAll();
		add(field);
		parent.add(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		dto.setMatfilename(field.getText());
	}
	public String getName() {
		return field.getText();
	}
	
}
