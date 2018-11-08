package serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Component;

import entity.ininterface;
import service.Inter;

@Component("ininter")
public class InInterface implements Inter{
	@Resource(name="sessionFactory")
	private SessionFactory sessionfactory;
	@Resource(name="ininterface")
	private ininterface inter;
	
	@Override
	public List<ininterface> inList() {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="select * from ininterface";
		NativeQuery<ininterface> query=session.createNativeQuery(str, ininterface.class);
		List<ininterface> list=query.getResultList();
		tr.commit();
		session.close();		
		return list;
	}

	@Override
	public List<ininterface> selectIdList(int id) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="select * from ininterface where id=?";
		NativeQuery<ininterface> query=session.createNativeQuery(str, ininterface.class);
		query.setParameter(1, id);
		List<ininterface> list=query.getResultList();
		tr.commit();
		session.close();
		return list;
	}

	@Override
	public String addIn(ininterface in) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		inter.setInname(in.getInname());
		inter.setUrl(in.getUrl());
		inter.setMethod(in.getDes());
		inter.setParam(in.getParam());
		inter.setChecker(in.getChecker());
		inter.setCreateid(in.getCreateid());
		inter.setCreatetime(in.getCreatetime());
		inter.setUpdatetime(in.getUpdatetime());
		inter.setSysid(in.getSysid());
		session.save(inter);
		tr.commit();
		session.close();
		return "新增成功";
	}

	@Override
	public String modifyIn(ininterface in) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteIn(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
