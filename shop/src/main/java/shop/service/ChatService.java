package shop.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.dao.ChatDao;

@Service
public class ChatService {
    @Autowired
    private ChatDao chatDao;

	public void insert(Map<String, Object> chatMap) {
		// TODO Auto-generated method stub
		chatDao.insert(chatMap);
	}

	public List<Map<String, Object>> chatInfo(Integer from, Integer to) {
		// TODO Auto-generated method stub
		return chatDao.chatInfo(from,to);
	}
}
