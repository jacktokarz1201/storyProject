package jack.stories.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jack.stories.dao.Contribution;
import jack.stories.dao.ContributionsDao;

@Service("contributionsService")
public class ContributionsService {

	private ContributionsDao contributionsDao;

	@Autowired
	public void setContributionsDao(ContributionsDao contributionsDao) {
		this.contributionsDao = contributionsDao;
	}
	
	public boolean createContribution(Contribution contribution) {
		return contributionsDao.createContribution(contribution);
	}

	public boolean exists(String title) {
		return contributionsDao.exists(title);
	}

	public boolean checkLength(Contribution contribution) {
		return contributionsDao.checkLength(contribution);
	}

	public boolean checkRepeat(Contribution contribution) {
		return contributionsDao.checkRepeat(contribution);
	}
}
