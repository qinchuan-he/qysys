package serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Component;

import entity.ininterface;
import service.Inter;

@Component("ininterimpl")
public class InInterfaceimpl implements Inter{
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
//		inter.setInname(in.getInname());
//		inter.setUrl(in.getUrl());
//		inter.setMethod(in.getDes());
//		inter.setParam(in.getParam());
//		inter.setChecker(in.getChecker());
//		inter.setCreateid(in.getCreateid());
//		inter.setCreatetime(in.getCreatetime());
//		inter.setUpdatetime(in.getUpdatetime());
//		inter.setSysid(in.getSysid());
		session.save(in);
		tr.commit();
		session.close();
		return "新增成功";
	}

	@Override
	public String modifyIn(ininterface in) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="update ininterface i set i.inname=?,i.url=?,i.method=?,i.des=?,i.param=?,i.checker=?,i.updatetime=?,i.sysid=? where i.id=?";
		NativeQuery<ininterface> query=session.createNativeQuery(str, ininterface.class);
		query.setParameter(1, in.getInname())
				.setParameter(2, in.getUrl())
				.setParameter(3, in.getMethod())
				.setParameter(4, in.getDes())
				.setParameter(5, in.getParam())
				.setParameter(6, in.getChecker())
				.setParameter(7, in.getUpdatetime())
				.setParameter(8, in.getSysid())
				.setParameter(9, in.getId());
		query.executeUpdate();
		tr.commit();
		session.close();
		return "修改成功";
	}

	@Override
	public String deleteIn(int id) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="delete from ininterface where id=?";
		NativeQuery<ininterface> query=session.createNativeQuery(str, ininterface.class);
		query.setParameter(1, id);
		query.executeUpdate();
		tr.commit();
		session.close();
		return "删除成功";
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectquery(int id) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="select i.id,i.inname,i.url,i.method,i.des,i.param,i.checker,i.updatetime,s.systemname,s.urlname,i.sysid from ininterface i,sysurl s where i.sysid=s.id and i.id=?";		
		NativeQuery<Object[]> query=session.createNativeQuery(str);
		query.setParameter(1, id);
//		List<Object[]> list=query.getResultList();
		Object[] o=query.getSingleResult();//这里确定只有一个返回结果
		Map<String,Object> map=new HashMap<String, Object>();
//		List<Map<String, Object>> list1=new ArrayList<Map<String,Object>>();
//		for(Object[] o:list){
			map.put("id", o[0]);
			map.put("inname", o[1]);
			map.put("url", o[2]);
			map.put("method", o[3]);
			map.put("des", o[4]);
			map.put("param", o[5]);
			map.put("check", o[6]);
			map.put("updatetime", o[7]);
			map.put("systemname", o[8]);
			map.put("urlname", o[9]);
			map.put("sysid", o[10]);
//			list1.add(map);
//		}
		tr.commit();
		session.close();
		
		return map;
	}

}









