package org.nees.illinois.matfiles.convert;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.nees.illinois.matfiles.dto.MatDataDto;
import org.nees.illinois.matfiles.dto.datatypes.DataElement;
import org.nees.illinois.repo.tools.matfiles.dto.archive.ArchiveFileProperties;
import org.nees.illinois.repo.tools.matfiles.dto.archive.ArchiveFilter;
import org.nees.illinois.repo.tools.matfiles.dto.archive.ArchiveFolderProperties;

import com.jmatio.types.MLArray;
import com.jmatio.types.MLCell;

public class ArchiveFolder2Matfile {
	private ArchiveFolderProperties app;

	private ArrayList<MLArray> mlList;
	private final Logger log = Logger.getLogger(ArchiveFolder2Matfile.class);

	public void convert(String folder) {
		mlList = new ArrayList<MLArray>();
		for (ArchiveFileProperties afp : app.getFiles()) {
			log.info("Converting " + afp.getName());
			convertFile(folder, afp);
		}
	}

	private void convertFile(String folder, ArchiveFileProperties afp) {
		ArchiveText2Dto at2d = new ArchiveText2Dto();
		at2d.parse(afp, folder);
		if (at2d.isEmpty()) {
			return;
		}
		List<ArchiveFilter> filters = app.getFilters();
		if (filters != null) {
			for (ArchiveFilter f : filters) {
				f.setArchive(at2d.getArchive());
				f.filter();
			}
		}
		MatDataDto mdd = new MatDataDto(at2d.getArchive());
		List<DataElement> des = afp.getDataTypes();
		if (des != null) {
			for (DataElement de : des) {
				mdd.setDataElements(de);
			}
		}
		for (MLCell de : mdd.getDataElements() ) {
			mlList.add(de);
		}
		mlList.add(mdd.getData());
		mlList.add(mdd.getHeaders());
	}

	/**
	 * @return the app
	 */
	public ArchiveFolderProperties getApp() {
		return app;
	}

	/**
	 * @return the mlList
	 */
	public ArrayList<MLArray> getMlList() {
		return mlList;
	}

	/**
	 * @param app
	 *            the app to set
	 */
	public void setApp(ArchiveFolderProperties app) {
		this.app = app;
	}

	/**
	 * @param mlList
	 *            the mlList to set
	 */
	public void setMlList(ArrayList<MLArray> mlList) {
		this.mlList = mlList;
	}

}
