package shop.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.dao.UserDao;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    /**��ȡ�û�����*/
	public String getFr(Integer uid) {
		// TODO Auto-generated method stub
		return userDao.getFr(uid);
	}
	/**��ȡ�û������б���Ϣ*/
	public List<Map<String, Object>> getFrList(List<Integer> list) {
		// TODO Auto-generated method stub
		return userDao.getFrList(list);
	}
	/**ͨ���ֻ���ȡ�û���Ϣ*/
	public Map<String, Object> getUser(String phone) {
		// TODO Auto-generated method stub
		return userDao.getUser(phone);
	}
	/**�鿴�û��Ƿ����*/
	public int checkPhone(String phone) {
		// TODO Auto-generated method stub
		return userDao.checkPhone(phone);
	}
	/**�����û�*/
	public void insertUser(Map<String, Object> userMap) {
		// TODO Auto-generated method stub
		userDao.insertUser(userMap);
	}
	public Map<String, Object> getUserById(Integer userId) {
		// TODO Auto-generated method stub
		return userDao.getUserById(userId);
	}
	public String getUserName(Integer userId) {
		// TODO Auto-generated method stub
		return userDao.getUserName(userId);
	}

}
