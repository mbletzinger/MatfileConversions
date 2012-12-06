package org.nees.illinois.matfiles.convert;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.nees.illinois.matfiles.dto.datatypes.DataElement;
import org.nees.illinois.repo.tools.matfiles.dto.archive.ArchiveDto;
import org.nees.illinois.repo.tools.matfiles.dto.archive.ArchiveFileProperties;
import org.nees.illinois.timeformats.OmDateFormat;

public class ArchiveText2Dto {
	/**
	 * @return the empty
	 */
	public boolean isEmpty() {
		return empty;
	}

	/**
	 * @param empty
	 *            the empty to set
	 */
	public void setEmpty(boolean empty) {
		this.empty = empty;
	}

	private ArchiveDto archive;
	private final Logger log = Logger.getLogger(ArchiveText2Dto.class);
	private boolean empty = true;

	/**
	 * @return the archive
	 */
	public ArchiveDto getArchive() {
		return archive;
	}

	public void parse(ArchiveFileProperties rootname, String folder) {
		archive = new ArchiveDto(rootname);
		String hname = rootname.getHeaderFilename(folder);
		List<String> dname = rootname.getDataFilename(folder);
		parseHeader(hname);
		for (String f : dname) {
			parseDataFile(f);
		}
	}

	public void parseDataFile(String strFile) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(strFile));
		} catch (FileNotFoundException e) {
			log.error("File \"" + strFile + "\" not found");
			return;
		}
		String strLine = "";
		try {
			boolean complain = false;
			boolean complain1 = false;
			int rowN = 1;
			log.debug("Datatypes used " + archive.getProperties().getDataTypes());
			while ((strLine = br.readLine()) != null) {
//			log.debug("Parsing: " + strLine);
				String[] tokens = strLine.split("\\s+");
				if ((tokens.length != archive.getHeaders().numColumns())
						&& complain == false) {
					log.error("For file \""
							+ strFile
							+ "\", actual records != header columns"
							+ (tokens.length)
							+ " != "
							+ archive.getHeaders().numColumns());
					complain = true;
				}
				if (tokens[0].contains("/")) {
					if (complain1 == false) {
						log.info("Time is in Date Format");
						complain1 = true;
					}
					String[] tk = convertTimeStamp(tokens);
					tokens = tk;
				}
				archive.getData().addRow(tokens2Double(tokens, strFile, rowN));
				rowN++;
			}
			empty = false;
			log.debug("Archive Row Text: " + archive.getData().dumpRow(archive.getData().getRow(0)));
		} catch (IOException e) {
			log.error("File \"" + strFile + "\" cannot be parsed because ", e);
		}
	}

	private String[] convertTimeStamp(String[] tokens) {
		String[] result = new String[tokens.length - 2];
		final OmDateFormat odf = new OmDateFormat();
		String timestring = tokens[0] + " " + tokens[1] + " " + tokens[2];
		Date date = odf.parse(timestring);
		result[0] = String.valueOf(date.getTime() / 1000.0);
		for (int i = 1; i < result.length; i++) {
			result[i] = tokens[i + 2];
		}
		return result;
	}

	public void parseHeader(String fileName) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			log.error("File \"" + fileName + "\" not found");
			return;
		}
		String strLine = "";
		try {
			strLine = br.readLine();
			String[] tokens = strLine.split("\\t");
			for (int t = 0; t < tokens.length; t++) {
				archive.getHeaders().addHeader(tokens[t]);
			}
		} catch (IOException e) {
			log.error("File \"" + fileName + "\" cannot be parsed because ", e);
		}
		try {
			br.close();
		} catch (IOException e) {
			log.error("File \"" + fileName + "\" cannot be parsed because ", e);
		}
	}

	/**
	 * @param archive
	 *            the archive to set
	 */
	public void setArchive(ArchiveDto archive) {
		this.archive = archive;
	}

	private List<Double> tokens2Double(String[] tokens, String filename,
			int rowNumber) {
		List<Double> row = new ArrayList<Double>();
		for (int t = 0; t < tokens.length; t++) {
			Double val;
			if (tokens[t].equals("Inf")) {
				log.info("Found an \"Inf\" in column " + t);
				val = 999999999999.9;
				row.add(val);
				continue;
			}
			int deIdx = -1;
			List<DataElement> des = archive.getProperties().getDataTypes();
			if (des != null) {
				for (DataElement d : des) {
					deIdx = d.text2Index(tokens[t]);
					if (deIdx > 0) {
						break;
					}
				}
			}
			if (deIdx > 0) {
				val = new Double(deIdx);
				row.add(val);
				continue;
			}
			try {
				val = new Double(tokens[t]);
			} catch (NumberFormatException e) {
				log.error("Token \"" + tokens[t] + "\" is not a number. Row "
						+ rowNumber + " column " + t + " file \"" + filename
						+ "\"" + deIdx);
				String str = "";
				for (DataElement d :des) {
					str += d.getDataElements();
				}
				log.debug(str);
				val = 0.0;
			}
			row.add(val);
		}
		return row;
	}
}
