package jwd.wafepa.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.wafepa.model.Activity;
import jwd.wafepa.repository.ActivityRepository;
import jwd.wafepa.service.ActivityService;

@Service
@Transactional // zato sto koristimo metode poput "save" koje menjaju podatke
public class JpaActivityService implements ActivityService {

	// referenca na repozitorijum
	@Autowired 		// da bi smo dobili instancu ovog interfejsa
	private ActivityRepository activityRepository;
	
	@Override
	public Activity findOne(Long id) {
		
		return activityRepository.findOne(id);
	}

	@Override
	public List<Activity> findAll() {
		return activityRepository.findAll();
	}
	
	@Override
	public Activity save(Activity activity) {
		return activityRepository.save(activity);
	}

	@Override
	public List<Activity> save(List<Activity> activities) {
		return activityRepository.save(activities);
	}

	@Override
	public Activity delete(Long id) {
		Activity toDelete = activityRepository.findOne(id);
		if (toDelete == null)
			return null;
		activityRepository.delete(toDelete);
		return toDelete;
	}

	@Override
	public void delete(List<Long> ids) {
		for (Long id : ids) {
			delete(id);
		}
	}

	@Override
	public List<Activity> findByName(String name) {
		return activityRepository.findByNameContaining(name);
	}

	
	@PostConstruct
	public void init(){
		save(new Activity("Swimming"));
		save(new Activity("Running"));
	}
}
