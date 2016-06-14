package shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.dao.ClassDao;

@Service
public class ClassService {
    @Autowired
    private ClassDao classDao;

	public String getCla(Integer classId) {
		// TODO Auto-generated method stub
		 return classDao.getCla(classId);
	}
}
