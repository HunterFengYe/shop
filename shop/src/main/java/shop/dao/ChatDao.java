package shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import shop.utils.MyBatisDao;

@MyBatisDao
public interface ChatDao {

	void insert(Map<String, Object> chatMap);

	List<Map<String, Object>> chatInfo(@Param("from")Integer from,@Param("to") Integer to);

}
