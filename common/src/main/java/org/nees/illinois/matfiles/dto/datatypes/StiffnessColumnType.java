package org.nees.illinois.matfiles.dto.datatypes;

public class StiffnessColumnType extends DataElement {
	public StiffnessColumnType() {
		super("StiffnessColumns");
	}

	@Override
	public void fillElements() {
		dataElements.add("dDx");
		dataElements.add("dDy");
		dataElements.add("dDz");
		dataElements.add("dRx");
		dataElements.add("dRy");
		dataElements.add("dRz");
	}
}
