package shop.dao;

import java.util.List;
import java.util.Map;

import shop.utils.MyBatisDao;

@MyBatisDao
public interface CommentDao {

	List<Map<String, Object>> getCom(Integer arId);

	void addCom(Map<String, Object> comMap);

	List<Map<String, Object>> aboutMe(Integer userId);

}
