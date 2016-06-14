package shop.dao;

import java.util.List;
import java.util.Map;

import shop.utils.MyBatisDao;

@MyBatisDao
public interface ArticleDao {

	void publish(Map<String, Object> uploadMap);

	Integer getArUser(Integer arId);

	List<Map<String, Object>> getArByClass(Integer classId);

	Map<String, Object> getArById(Integer arId);

	List<Map<String, Object>> getReply(Integer arId);

}
