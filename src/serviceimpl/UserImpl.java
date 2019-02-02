package serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Component;

import entity.user1;
import service.User;

@Component("Userimpl")
public class UserImpl implements User{
	@Resource(name="sessionFactory")
	private SessionFactory sessionfactoey;

	@Override
	public int login(String name, String password) {
		// TODO Auto-generated method stub
		Session session=sessionfactoey.openSession();
		Transaction tr=session.beginTransaction();
		String sql="select * from user1 where name=? and password=?";
		NativeQuery<user1> query=session.createNativeQuery(sql, user1.class).setParameter(1, name).setParameter(2, password);
		List<user1>	list=query.getResultList();
		tr.commit();
		session.close();
		if(list.size()>0){
			return 1;
		}else{
			return 0;
		}
	}

}
