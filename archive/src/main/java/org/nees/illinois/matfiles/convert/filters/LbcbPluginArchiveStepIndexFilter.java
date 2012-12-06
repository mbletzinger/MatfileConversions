package org.nees.illinois.matfiles.convert.filters;

import java.util.ArrayList;
import java.util.List;

import org.nees.illinois.repo.tools.matfiles.dto.archive.ArchiveDataDto;
import org.nees.illinois.repo.tools.matfiles.dto.archive.ArchiveDto;
import org.nees.illinois.repo.tools.matfiles.dto.archive.ArchiveFilter;
import org.nees.illinois.repo.tools.matfiles.dto.archive.ArchiveHeaderDto;

public class LbcbPluginArchiveStepIndexFilter implements ArchiveFilter {
	ArchiveDto ad;
	List<Double> steps = new ArrayList<Double>();

	@Override
	public void setArchive(ArchiveDto ad) {
		this.ad = ad;
	}

	@Override
	public ArchiveDto getArchive() {
		return ad;
	}

	@Override
	public void filter() {
		ArchiveHeaderDto hdr = ad.getHeaders();
		ArchiveDataDto data = ad.getData();
		extractSteps(data);
		List<Double> si = calcStepIndex();
		addStepIndexes(data, si);
		hdr.addHeader("StepIndex");
	}

	private void extractSteps(ArchiveDataDto data) {
		steps.clear();
		for (int r = 0; r < data.numRows(); r++) {
			List<Double> row = data.getRow(r);
			steps.add(row.get(0));
		}
	}

	private List<Double> calcStepIndex() {
		double currentStep = steps.get(0).doubleValue();
		List<Double> stepIndex = new ArrayList<Double>();

		int row = 1;
		int start = 0;

		while (row < steps.size()) {
			while (row < steps.size() && currentStep == steps.get(row)) {
				row++;
			}
			int numSubs = row - start;
			for (int i = 1; i <= numSubs; i++) {
				Double val = currentStep - 1 + (double) i / (double) numSubs;
				stepIndex.add(val);
			}
			if (row < steps.size()) {
				currentStep = steps.get(row);
				start = row;
			}
		}
		return stepIndex;
	}

	private void addStepIndexes(ArchiveDataDto data, List<Double> si) {
		for (int r = 0; r < data.numRows(); r++) {
			List<Double> row = data.getRow(r);
			row.add(si.get(r));
		}
	}
}
