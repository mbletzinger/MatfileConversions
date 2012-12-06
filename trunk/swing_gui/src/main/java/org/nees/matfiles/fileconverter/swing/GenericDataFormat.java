package org.nees.matfiles.fileconverter.swing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public abstract class GenericDataFormat {
	private SimpleDateFormat format;
	private Logger log = Logger.getLogger(GenericDataFormat.class);

	public GenericDataFormat(String format) {
		super();
		this.format = new SimpleDateFormat(format);
	}

	public String format(Date date) {
		return format.format(date);
	}
	
	public Date parse(String string) {
		try {
			return format.parse(string);
		} catch (ParseException e) {
			log.error("String [" + string + "] cannot be parsed",e);
			return null;
		}
	}

	protected SimpleDateFormat getFormat() {
		return format;
	}

	protected void setFormat(SimpleDateFormat format) {
		this.format = format;
	}

}
