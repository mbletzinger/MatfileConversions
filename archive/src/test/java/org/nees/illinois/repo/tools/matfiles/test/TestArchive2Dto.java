package org.nees.illinois.repo.tools.matfiles.test;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.nees.illinois.matfiles.archive.filetypes.OmContinuousArchive;
import org.nees.illinois.matfiles.convert.ArchiveText2Dto;
import org.nees.illinois.matfiles.dto.MatDataDto;
import org.nees.illinois.repo.tools.matfiles.dto.archive.ArchiveFileProperties;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jmatio.io.MatFileWriter;
import com.jmatio.types.MLArray;

public class TestArchive2Dto {
	private final Logger log = Logger.getLogger(TestArchive2Dto.class);
	private String dataFolder;
	@BeforeClass
	public void setUp() throws Exception {
		URL u = ClassLoader.getSystemResource("OM/LBCB1_Continuous_headers.txt");
		String df = u.getPath();
		File dff = new File(df);
		dataFolder = dff.getParent();
	}
	@Test
	public void testOmArchive2Dto() {
		ArchiveText2Dto at2dto = new ArchiveText2Dto();
		ArchiveFileProperties afp = new OmContinuousArchive("LBCB1");
		at2dto.parse(afp, dataFolder);
		log.info("Columns = " + at2dto.getArchive().getHeaders().numColumns());
		Assert.assertEquals(37, at2dto.getArchive().getHeaders().numColumns());
		log.info("Rows = " + at2dto.getArchive().getData().numRows());
		Assert.assertEquals(79, at2dto.getArchive().getData().numRows());
	}
	@Test
	public void testOmArchive2MatDto() {
		ArchiveText2Dto lbcb1 = new ArchiveText2Dto();
		ArchiveFileProperties afp = new OmContinuousArchive("LBCB1");
		lbcb1.parse(afp, dataFolder);
		log.info("Columns = " + lbcb1.getArchive().getHeaders().numColumns());
		Assert.assertEquals(37, lbcb1.getArchive().getHeaders().numColumns());
		log.info("Rows = " + lbcb1.getArchive().getData().numRows());
		Assert.assertEquals(79, lbcb1.getArchive().getData().numRows());
		MatDataDto lbcb1m = new MatDataDto(lbcb1.getArchive());
		log.info("Columns  m = " + lbcb1m.getColumns());
		Assert.assertEquals(37, lbcb1m.getColumns());
		log.info("Rows = " + lbcb1m.getRows());
		Assert.assertEquals(79, lbcb1m.getRows());		
	}
	@Test
	public void testWriteOmArchive() {
		ArchiveText2Dto lbcb1 = new ArchiveText2Dto();
		ArchiveFileProperties afp = new OmContinuousArchive("LBCB1");
		lbcb1.parse(afp, dataFolder);
		log.info("Columns = " + lbcb1.getArchive().getHeaders().numColumns());
		Assert.assertEquals(37, lbcb1.getArchive().getHeaders().numColumns());
		log.info("Rows = " + lbcb1.getArchive().getData().numRows());
		Assert.assertEquals(79, lbcb1.getArchive().getData().numRows());
		MatDataDto lbcb1m = new MatDataDto(lbcb1.getArchive());
		log.info("Columns  m = " + lbcb1m.getColumns());
		Assert.assertEquals(37, lbcb1m.getColumns());
		log.info("Rows = " + lbcb1m.getRows());
		Assert.assertEquals(79, lbcb1m.getRows());		
		ArrayList<MLArray> list = new ArrayList<MLArray>();
		list.add(lbcb1m.getData());
		list.add(lbcb1m.getHeaders());
		try {
			new MatFileWriter("Lbcb1Data.mat", list);
		} catch (IOException e) {
			log.error("matfile write failed because", e);
			Assert.fail();
		}
	}
	@Test
	public void testMultiWriteOmArchive() {
		ArchiveText2Dto lbcb1 = new ArchiveText2Dto();
		ArchiveFileProperties afp = new OmContinuousArchive("LBCB1");
		lbcb1.parse(afp, dataFolder);
		log.info("Columns = " + lbcb1.getArchive().getHeaders().numColumns());
		Assert.assertEquals(37, lbcb1.getArchive().getHeaders().numColumns());
		log.info("Rows = " + lbcb1.getArchive().getData().numRows());
		Assert.assertEquals(79, lbcb1.getArchive().getData().numRows());
		MatDataDto lbcb1m = new MatDataDto(lbcb1.getArchive());
		log.info("Columns  m = " + lbcb1m.getColumns());
		Assert.assertEquals(37, lbcb1m.getColumns());
		log.info("Rows = " + lbcb1m.getRows());
		Assert.assertEquals(79, lbcb1m.getRows());		

		ArchiveText2Dto extSensors = new ArchiveText2Dto();
		afp = new OmContinuousArchive("ExternalSensors");
		extSensors.parse(afp, dataFolder);
		log.info("Columns = " + extSensors.getArchive().getHeaders().numColumns());
		Assert.assertEquals(5, extSensors.getArchive().getHeaders().numColumns());
		log.info("Rows = " + extSensors.getArchive().getData().numRows());
		Assert.assertEquals(79, extSensors.getArchive().getData().numRows());
		MatDataDto extSensorsm = new MatDataDto(extSensors.getArchive());
		log.info("Columns  m = " + extSensorsm.getColumns());
		Assert.assertEquals(5, extSensorsm.getColumns());
		log.info("Rows = " + extSensorsm.getRows());
		Assert.assertEquals(79, extSensorsm.getRows());		

		ArrayList<MLArray> list = new ArrayList<MLArray>();
		list.add(lbcb1m.getData());
		list.add(lbcb1m.getHeaders());
		list.add(extSensorsm.getData());
		list.add(extSensorsm.getHeaders());
		try {
			new MatFileWriter("Lbcb1Data.mat", list);
		} catch (IOException e) {
			log.error("matfile write failed because", e);
			Assert.fail();
		}
	}
}
