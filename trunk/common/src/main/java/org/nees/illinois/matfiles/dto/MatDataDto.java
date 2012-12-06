package org.nees.illinois.matfiles.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.nees.illinois.matfiles.dto.datatypes.DataElement;
import org.nees.illinois.repo.tools.matfiles.dto.archive.ArchiveDataDto;
import org.nees.illinois.repo.tools.matfiles.dto.archive.ArchiveDto;
import org.nees.illinois.repo.tools.matfiles.dto.archive.ArchiveHeaderDto;

import com.jmatio.types.MLCell;
import com.jmatio.types.MLChar;
import com.jmatio.types.MLDouble;

public class MatDataDto {
	private int columns;

	private MLDouble data;

	private final List<MLCell> dataElements = new ArrayList<MLCell>();
	
	private MLCell headers;

	private final Logger log = Logger.getLogger(MatDataDto.class);

	private final String name;
	
	private int rows;

	public MatDataDto(ArchiveDto ad) {
		super();
		this.name = ad.getProperties().getName();
		setHeaders(ad.getHeaders());
		setData(ad.getData());
	}

	public MatDataDto(String name) {
		super();
		this.name = name;
	}

	/**
	 * @return the columns
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 * @return the data
	 */
	public MLDouble getData() {
		return data;
	}

	public List<MLCell> getDataElements() {
		return dataElements;
	}

	/**
	 * @return the headers
	 */
	public MLCell getHeaders() {
		return headers;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * @param columns
	 *            the columns to set
	 */
	public void setColumns(int columns) {
		this.columns = columns;
	}

	public void setData(ArchiveDataDto add) {
		rows = add.numRows();
		if (rows == 0) {
			log.info("No data for this archive");
			return;
		}
		Double[] vals = new Double[rows * columns];
		int flatidx = 0;
		for (int c = 0; c < columns; c++) {
			for (int r = 0; r < rows; r++) {
				List<Double> row = add.getRow(r);
				Double num = row.get(c);
				vals[flatidx] = num;
				flatidx++;
			}
		}
		data = new MLDouble((name + "_data"), vals, rows);

		log.debug("Archive Row Data: " + data.contentToString());
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(MLDouble data) {
		this.data = data;
	}
	public void setDataElements(DataElement de) {
		
		MLCell dtCell = new MLCell(de.getName(), new int[] { de.getDataElements().size(), 1 });
		int i = 0;
		for (String h : de.getDataElements()) {
			MLChar mlh = new MLChar(h, h);
			dtCell.set(mlh, i);
			i++;
		}
		dataElements.add(dtCell);
	}

	public void setHeaders(ArchiveHeaderDto ahd) {
		columns = ahd.numColumns();
		headers = new MLCell((name + "_hdrs"), new int[] { columns, 1 });
		int i = 0;
		for (String h : ahd.getHeaders()) {
			MLChar mlh = new MLChar(h, h);
			headers.set(mlh, i);
			i++;
		}
	}

	/**
	 * @param headers
	 *            the headers to set
	 */
	public void setHeaders(MLCell headers) {
		this.headers = headers;
	}

	/**
	 * @param rows
	 *            the rows to set
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}
}
