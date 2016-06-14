package shop.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.dao.ArticleDao;

@Service
public class ArticleService {
    @Autowired
    private ArticleDao arDao;

	public void publish(Map<String, Object> uploadMap) {
		// TODO Auto-generated method stub
		arDao.publish(uploadMap);
	}

	public Integer getArUser(Integer arId) {
		// TODO Auto-generated method stub
		return arDao.getArUser(arId);
	}

	public List<Map<String, Object>> getArByClass(Integer classId) {
		// TODO Auto-generated method stub
		return arDao.getArByClass(classId);
	}

	public Map<String, Object> getArById(Integer arId) {
		// TODO Auto-generated method stub
		return arDao.getArById(arId);
	}

	public List<Map<String, Object>> getReply(Integer arId) {
		// TODO Auto-generated method stub
		return arDao.getReply(arId);
	}
}
