package shop.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.dao.CommentDao;

@Service
public class CommentService {
    @Autowired
    private CommentDao comDao;

	public List<Map<String, Object>> getCom(Integer arId) {
		// TODO Auto-generated method stub
		return comDao.getCom(arId);
	}

	public void addCom(Map<String, Object> comMap) {
		// TODO Auto-generated method stub
		comDao.addCom(comMap);
	}

	public List<Map<String, Object>> aboutMe(Integer userId) {
		// TODO Auto-generated method stub
		return comDao.aboutMe(userId);
	}
    
}
