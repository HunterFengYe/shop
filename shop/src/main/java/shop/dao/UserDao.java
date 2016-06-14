package shop.dao;

import java.util.List;
import java.util.Map;

import shop.utils.MyBatisDao;

@MyBatisDao
public interface UserDao {

	String getFr(Integer uid);

	List<Map<String, Object>> getFrList(List<Integer> list);

	Map<String, Object> getUser(String phone);

	int checkPhone(String phone);

	void insertUser(Map<String, Object> userMap);

	Map<String, Object> getUserById(Integer userId);

	String getUserName(Integer userId);

}
