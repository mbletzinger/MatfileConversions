package org.nees.illinois.repo.tools.matfiles.test;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.apache.log4j.Logger;
import org.nees.illinois.matfiles.archive.filetypes.LbcbPluginArchiveFolder;
import org.nees.illinois.matfiles.convert.ArchiveFolder2Matfile;
import org.nees.illinois.repo.tools.matfiles.dto.archive.ArchiveFileProperties;
import org.nees.illinois.repo.tools.matfiles.dto.archive.ArchiveFolderProperties;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jmatio.io.MatFileWriter;

public class TestLbcbPluginArchiveFolder2Matfile {
	private final Logger log = Logger.getLogger(TestArchive2Dto.class);
	private String dataFolder;

	@BeforeClass
	public void setUp() throws Exception {
		URL u = ClassLoader.getSystemResource("LbcbPlugin/CorrectionData_hdr.txt");
		String df = u.getPath();
		File dff = new File(df);
		dataFolder = dff.getParent();
	}

	@Test
	public void testArchiveFolderProperties() {
		ArchiveFolderProperties afp = new LbcbPluginArchiveFolder();
		afp.scan(dataFolder);
		Assert.assertTrue(afp.isArchive(dataFolder));
		for (ArchiveFileProperties f : afp.getFiles()) {
			log.debug("Archive Type: " + f.getName());
			String hdr = f.getHeaderFilename(dataFolder);
			log.debug("Header file: \"" + hdr + "\"");
			Assert.assertTrue(hdr.contains("_hdr"));
//			File path = new File(dataFolder);
			List<String> dfiles = f.getDataFilename(dataFolder);
			String str = "";
			for (String d : dfiles) {
				str += "\t" + d + "\n";
			}
			log.debug("Data files:\n" + str);
			Assert.assertEquals(3, dfiles.size());

		}
	}

	@Test
	public void testArchiveFolder2Matfile() {
		ArchiveFolderProperties afp = new LbcbPluginArchiveFolder();
		afp.scan(dataFolder);
		Assert.assertTrue(afp.isArchive(dataFolder));
		ArchiveFolder2Matfile af2m = new ArchiveFolder2Matfile();
		af2m.setApp(afp);
		af2m.convert(dataFolder);
		try {
			new MatFileWriter("LbcbPluginTest.mat", af2m.getMlList());
		} catch (IOException e) {
			log.error("matfile write failed because", e);
			Assert.fail();
		}
	}
}
