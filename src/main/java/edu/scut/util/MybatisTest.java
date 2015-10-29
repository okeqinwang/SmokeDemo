package edu.scut.util;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import edu.scut.dao.UserMapper;
import edu.scut.model.User;

public class MybatisTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SqlSessionFactory sf = MyBatisUtil.getSessionFactory();
		SqlSession session = sf.openSession();
		UserMapper umaper = session.getMapper(UserMapper.class);
	
		User m = new User();
		m.setName("tongy");
		m.setAge(99);
		m.setSex("female");
		umaper.insertUser(m);
		
		session.commit();
		
		List<User> ulist = umaper.findByName("tongy");
		for(User u: ulist){
			System.out.println(u.toString());
		}
		session.close();
		
	}

}
