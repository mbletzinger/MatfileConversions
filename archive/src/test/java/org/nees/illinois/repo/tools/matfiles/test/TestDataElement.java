package org.nees.illinois.repo.tools.matfiles.test;

import junit.framework.Assert;

import org.nees.illinois.matfiles.dto.datatypes.DataElement;
import org.nees.illinois.matfiles.dto.datatypes.OmCommandType;
import org.nees.illinois.matfiles.dto.datatypes.StiffnessColumnType;
import org.testng.annotations.Test;

public class TestDataElement {
	private final String [] badStrings = {"12.9087", "Inf", "", "ddDx" };

	@Test
	public void testOmCommandType() {
		String [] testStrings = {"<None>", "Displacement", "Force"};
		int [] idxs = { 1, 2, 3};
		DataElement de = new OmCommandType();
		cycleTests(testStrings,idxs,de);
	}
	
	@Test
	public void testStiffnessColumnType() {
		String [] testStrings = {"dDx", "dDy", "dDz", "dRx", "dRy", "dRz"};
		int [] idxs = { 1, 2, 3, 4, 5 , 6};
		DataElement de = new StiffnessColumnType();
		cycleTests(testStrings,idxs,de);
	}
	private void cycleTests(String [] good, int [] goodIdx, DataElement de) {
		for (int t = 0; t < good.length;t++) {
			int r = de.text2Index(good[t]);
			Assert.assertEquals(goodIdx[t],r);
		}
		for (int t = 0; t < badStrings.length;t++) {
			int r = de.text2Index(badStrings[t]);
			Assert.assertEquals(0,r);
		}
	}
}
