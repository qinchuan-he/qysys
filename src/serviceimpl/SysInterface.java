package serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Component;

import entity.sysurl;
import service.Sys;

@Component("sysInterface")
public class SysInterface implements Sys{
	@Resource(name="sessionFactory")
	private SessionFactory sessionfactory;
	@Resource(name="sysurl")
	private sysurl s;
	@Override
	public List<sysurl> sysList() {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="select * from sysurl";
		NativeQuery<sysurl> query=session.createNativeQuery(str, sysurl.class);
		List<sysurl> list=query.getResultList();
		tr.commit();
		session.close();
		return list;
	}
	
	@Override
	public List<sysurl> selectIdList(int id) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="select * from sysurl s where s.id=?";
		NativeQuery<sysurl> query=session.createNativeQuery(str, sysurl.class);
		query.setParameter(1, id);
		List<sysurl> list=query.getResultList();
		tr.commit();
		session.close();
		return list;
	}

	@Override
	public String addSys(sysurl sys) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		s.setSystemname(sys.getSystemname());
		s.setUrlname(sys.getUrlname());
		s.setCreateid(sys.getCreateid());
		s.setCreatetime(sys.getCreatetime());
		s.setUpdatetime(sys.getUpdatetime());
		s.setDes(sys.getDes());
		session.save(s);
		tr.commit();
		session.close();
		return "新增完成";
	}

	@Override
	public String modifySys(sysurl sys) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="update sysurl s set s.systemname=?,s.urlname=?,s.updatetime=?,s.des=? where s.id=?";
		NativeQuery<sysurl> query=session.createNativeQuery(str, sysurl.class);
		query.setParameter(1, sys.getSystemname())
			 .setParameter(2, sys.getUrlname())
			 .setParameter(3, sys.getUpdatetime())
			 .setParameter(4, sys.getDes())
			 .setParameter(5, sys.getId());
		query.executeUpdate();
		tr.commit();
		session.close();
		return "更新成功";
	}

	@Override
	public String deleteSys(int id) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="delete from sysurl s where s.id=?";
		NativeQuery<sysurl> query=session.createNativeQuery(str, sysurl.class);
		query.setParameter(1, id);
		query.executeUpdate();
		tr.commit();
		session.close();
		return "删除成功";
	}



}
