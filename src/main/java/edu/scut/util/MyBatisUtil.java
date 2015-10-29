package edu.scut.util;
/**
 * MyBatis SessionFactory������
 */
import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public final class MyBatisUtil {
    private static SqlSessionFactory sessionFactory = buildSessionFactory();
    private MyBatisUtil() {
    }
 
    private static SqlSessionFactory buildSessionFactory(){
        if (null == sessionFactory) {
            String resource = "mybatis-config.xml";
            try {
                sessionFactory = new SqlSessionFactoryBuilder().build(Resources
                        .getResourceAsReader(resource));
                //���ע��ӳ��
//                sessionFactory.getConfiguration().addMapper(FileHashMapper.class);
//                sessionFactory.getConfiguration().addMapper(UploadFileMapper.class);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
    
    /**
     * ��ȡSessionFactory
     * @return	sessionFactory
     */
    public static SqlSessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
