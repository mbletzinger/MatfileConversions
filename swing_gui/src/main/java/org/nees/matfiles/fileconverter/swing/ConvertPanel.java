package org.nees.matfiles.fileconverter.swing;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

public class ConvertPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */

	private static final long serialVersionUID = 8755672558540506533L;
	private JTextArea logGui;
	private JTextArea errorGui;
	private JButton generateButton;
	private final MatFileName outfileName;
	private final static String newline = "\n\n";
	private final Logger log = Logger.getLogger(ConvertPanel.class);
	private final FilenamesDTO dto;

	@Override
	public void actionPerformed(ActionEvent e) {
		convert();
	}

	private void convert() {
		errorGui.setText(null);
		logGui.setText(null);
		DoConversion dc= new DoConversion(this, dto, outfileName);
		Thread dcthr = new Thread(dc);
		dcthr.start();
		}

	public ConvertPanel(MatFileName outfileName, FilenamesDTO dto) {
		super(new FlowLayout(FlowLayout.LEFT, 5, 5));
		this.outfileName = outfileName;
		this.dto = dto;
	}

	public void createGui(Container parent) {

		logGui = new JTextArea(10, 100);
		JPanel textP = new JPanel();
		textP.setLayout(new BoxLayout(textP, BoxLayout.Y_AXIS));
		add(textP);
		JScrollPane rScrollPane = new JScrollPane(logGui);
		logGui.setEditable(false);
		logGui.setLineWrap(false);
		logGui.setBorder(BorderFactory.createTitledBorder("Messages"));
		textP.add(rScrollPane);

		generateButton = new JButton("GENERATE");
		generateButton.setPreferredSize(new Dimension(100, 40));
		generateButton.addActionListener(this);
		add(generateButton);

		errorGui = new JTextArea(10, 100);
		JScrollPane eScrollPane = new JScrollPane(errorGui);
		errorGui.setEditable(false);
		errorGui.setLineWrap(false);
		errorGui.setBorder(BorderFactory.createTitledBorder("Errors"));
		textP.add(eScrollPane);

		parent.add(this);
	}

	public void updateLog(final String line, final boolean isError) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// Here, we can safely update the GUI
				// because we'll be called from the
				// event dispatch thread
				if (isError) {
					errorGui.append(line + newline);
				} else {
					logGui.append(line + newline);
				}
			}
		});
	}
}
