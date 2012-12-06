package org.nees.illinois.matfiles.dto.datatypes;

public class OmCommandType extends DataElement {
	
	public OmCommandType() {
		super("OM_CommandTypes");
	}

	@Override
	public void fillElements() {
		dataElements.add("<None>");
		dataElements.add("Displacement");
		dataElements.add("Force");
	}
}