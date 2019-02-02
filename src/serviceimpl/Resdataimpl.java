package serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Component;

import entity.responsedetail;
import service.resdata;
@Component(value="resdataimpl")
public class Resdataimpl implements resdata{
	@Resource(name="sessionFactory")
	private SessionFactory sessionfactory;
	
	
	@Override
	public int maxExenum(int pid) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="select max(exenum) from responsedetail where pid=?";
		NativeQuery<Object> query=session.createNativeQuery(str).setParameter(1, pid);
		Object result=query.getSingleResult();
		tr.commit();
		session.close();
		if(result==null){
			return 0;
		}else{
			int max=Integer.parseInt(result.toString());		
			return max;
		}
	}

	@Override
	public List<Map<String, Object>> selectPid(int pid, int exenum,int page,int limit) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="select r.*,f.inname,f.id inid from responsedetail r,ininterface f  where r.apiid=f.id and  pid=? and exenum=? limit ?,?";
		NativeQuery<Object[]> query=session.createNativeQuery(str);
		query.setParameter(1, pid)
				.setParameter(2, exenum)
				.setParameter(3, page*limit)
				.setParameter(4, limit);
		List<Object[]> list=query.getResultList();
		tr.commit();
		session.close();
		List<Map<String, Object>> list1=new ArrayList<Map<String,Object>>();
		for(Object[] o:list){
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("id", o[0]);
			map.put("pid", o[1]);
			map.put("apiid", o[2]);
			map.put("method", o[3]);
			map.put("result", o[4]);
			map.put("responsedata", o[5]);
			map.put("num", o[6]);
			map.put("exetime", o[7]);
			map.put("exenum", o[8]);
			map.put("inname", o[9]);
			map.put("inid", o[10]);
			list1.add(map);
		}		
		return list1;
	}

	@Override
	public List<Map<String, Object>> selectPid(int pid, int exenum) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="select r.*,f.inname,f.id from responsedetail r,ininterface f  where r.apiid=f.id and  pid=? and exenum=? ";
		NativeQuery<Object[]> query=session.createNativeQuery(str);
		query.setParameter(1, pid)
				.setParameter(2, exenum);
		List<Object[]> list=query.getResultList();
		tr.commit();
		session.close();
		List<Map<String, Object>> list1=new ArrayList<Map<String,Object>>();
		for(Object[] o:list){
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("id", o[0]);
			map.put("pid", o[1]);
			map.put("apiid", o[2]);
			map.put("method", o[3]);
			map.put("result", o[4]);
			map.put("responsedata", o[5]);
			map.put("num", o[6]);
			map.put("exetime", o[7]);
			map.put("exenum", o[8]);
			map.put("inname", o[9]);
			list1.add(map);
		}		
		return list1;
	}
	@Override
	public String addResponse(responsedetail res) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		session.save(res);
		tr.commit();
		session.close();		
		return "新增成功";
	}

	@Override
	public int countResponse(int pid, int exenum) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="select count(1) from responsedetail r  where   r.pid=? and r.exenum=? ";
		NativeQuery<Object> query=session.createNativeQuery(str).setParameter(1, pid).setParameter(2, exenum);
		int count=Integer.parseInt(query.getSingleResult().toString());
		tr.commit();
		session.close();
		return count;
	}

}
