package org.nees.matfiles.fileconverter.swing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.nees.illinois.matfiles.archive.filetypes.DaqContinuousArchiveFolder;
import org.nees.illinois.matfiles.archive.filetypes.DaqStepArchiveFolder;
import org.nees.illinois.matfiles.archive.filetypes.KryptonContinuousArchiveFolder;
import org.nees.illinois.matfiles.archive.filetypes.KryptonStepArchiveFolder;
import org.nees.illinois.matfiles.archive.filetypes.LbcbPluginArchiveFolder;
import org.nees.illinois.matfiles.archive.filetypes.OmCommandsArchiveFolder;
import org.nees.illinois.matfiles.archive.filetypes.OmContinuousArchiveFolder;
import org.nees.illinois.matfiles.archive.filetypes.OmStepArchiveFolder;
import org.nees.illinois.matfiles.archive.filetypes.OmStiffnessArchiveFolder;
import org.nees.illinois.matfiles.convert.ArchiveFolder2Matfile;
import org.nees.illinois.repo.tools.matfiles.dto.archive.ArchiveFolderProperties;

import com.jmatio.io.MatFileWriter;

public class DoConversion implements Runnable {

	private final Logger log = Logger.getLogger(DoConversion.class);
	private final ConvertPanel cp;
	private final FileExtensionDateFormat dateFormat = new FileExtensionDateFormat();
	private final FilenamesDTO dto;
	private ArchiveFolder2Matfile af2m = new ArchiveFolder2Matfile();
	private final MatFileName outfileName;

	@Override
	public void run() {
		List<ArchiveFolderProperties> folders = new ArrayList<ArchiveFolderProperties>();
		folders.add(new DaqContinuousArchiveFolder());
		folders.add(new DaqStepArchiveFolder());
		folders.add(new KryptonContinuousArchiveFolder());
		folders.add(new KryptonStepArchiveFolder());
		folders.add(new LbcbPluginArchiveFolder());
		folders.add(new OmContinuousArchiveFolder());
		folders.add(new OmCommandsArchiveFolder());
		folders.add(new OmStepArchiveFolder());
		folders.add(new OmStiffnessArchiveFolder());
		for (ArchiveFolderProperties f : folders) {
			convert(f);
		}

	}
	private void convert(ArchiveFolderProperties folder) {
		folder.scan(dto.getArchiveFolder());
		if (folder.isArchive(dto.getArchiveFolder())) {
			log.info("Processing as a " + folder.getName());
			af2m.setApp(folder);
			af2m.convert(dto.getArchiveFolder());
			String fn = outfileName.getName() + "_" + folder.getExtension()
					+ "_" + dateFormat.format(new Date()) + ".mat";
			log.info("Writing matfile to \"" + fn + "\"");
			try {
				new MatFileWriter(fn, af2m.getMlList());
			} catch (IOException e) {
				log.error("matfile write failed because", e);
			}
		} else {
			log.info("\"" + dto.getArchiveFolder()
					+ "\" is not a " + folder.getName());
		}
	}
	public DoConversion(ConvertPanel cp, FilenamesDTO dto,
			MatFileName outfileName) {
		super();
		this.cp = cp;
		this.dto = dto;
		this.outfileName = outfileName;
	}

}
