package org.nees.matfiles.fileconverter.swing;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;

public class GuiAppender extends AppenderSkeleton {

	private static ConvertPanel generate = null;
	@Override
	protected void append(LoggingEvent event) {
		if(generate == null) {
			return;
		}
		if(event.getLevel().isGreaterOrEqual(Level.INFO)) {
			String line = event.getLevel() + ": " + event.getMessage();
			generate.updateLog(line,event.getLevel().isGreaterOrEqual(Level.WARN));
		}
	}

	@Override
	public void close() {

	}

	@Override
	public boolean requiresLayout() {
		return false;
	}

	public static ConvertPanel getGenerate() {
		return generate;
	}

	public static void setGenerate(ConvertPanel generate) {
		GuiAppender.generate = generate;
	}

}
