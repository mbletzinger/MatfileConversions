package org.nees.illinois.repo.tools.matfiles.dto.archive;


public class ArchiveDto {
	private final ArchiveDataDto data = new ArchiveDataDto();
	private final ArchiveHeaderDto headers = new ArchiveHeaderDto();
	private final ArchiveFileProperties properties;
	public ArchiveDto(ArchiveFileProperties props) {
		super();
		this.properties = props;
	}
	/**
	 * @return the data
	 */
	public ArchiveDataDto getData() {
		return data;
	}
	/**
	 * @return the headers
	 */
	public ArchiveHeaderDto getHeaders() {
		return headers;
	}
	/**
	 * @return the name
	 */
	public ArchiveFileProperties getProperties() {
		return properties;
	}
}
