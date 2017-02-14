package jack.stories.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jack.stories.dao.Contribution;
import jack.stories.dao.ContributionsDao;

@Service("contributionsService")
public class ContributionsService {

	@Autowired
	private ContributionsDao contributionsDao;
	
	public boolean createContribution(Contribution contribution) {
		return contributionsDao.createContribution(contribution);
	}
}
