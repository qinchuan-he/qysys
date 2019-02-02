package serviceimpl;

import java.math.BigInteger;
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

import entity.ininterface;
import entity.plan;
import entity.plandetail;
import entity.plug;
import service.planinter;
@Component(value="planimpl")
public class planImpl implements planinter{
	@Resource(name="sessionFactory")
	private  SessionFactory sessionfactory;

	@Override
	public List<plan> list() {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="select * from plan";
		NativeQuery<plan> query=session.createNativeQuery(str, plan.class);
		List<plan> list=query.getResultList();
		tr.commit();
		session.close();
		return list;
	}

	@Override
	public Map<String, Object> selectId(int id) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="select p.*,s.systemname,s.urlname from plan p,sysurl s where p.sysid=s.id and p.id=?";
		@SuppressWarnings("unchecked")
		NativeQuery<Object[]> query=session.createNativeQuery(str);
		query.setParameter(1, id);
		Object[] list=query.getSingleResult();
		Map<String, Object> map=new HashMap<String, Object>();
		
			map.put("id", list[0]);
			map.put("pname", list[1]);
			map.put("des", list[2]);
			map.put("createid", list[3]);
			map.put("updatetime", list[5]);
			map.put("sysid", list[6]);
			map.put("systemname", list[7]);
			map.put("sysurl", list[8]);
			
				
		tr.commit();
		session.close();
		return map;
	}

	@Override
	public List<plan> selectName(String name) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="select * from plan where pname like ?";
		NativeQuery<plan> query=session.createNativeQuery(str, plan.class);
		query.setParameter(1, "%"+name+"%");
		List<plan> list=query.getResultList();
		tr.commit();
		session.close();
		return list;
	}

	@Override
	public String addPlan(plan p) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		session.save(p);
		tr.commit();
		session.close();
		return "新增成功";
	}

	@Override
	public String modifyPlan(plan p) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="update plan set pname=?,des=?,updatetime=?, sysid=? where id=?";
		NativeQuery<plan> query=session.createNativeQuery(str, plan.class);
		query.setParameter(1, p.getPname())
				.setParameter(2, p.getDes())
				.setParameter(3, p.getUpdatetime())
				.setParameter(4, p.getSysid())
				.setParameter(5, p.getId());
		query.executeUpdate();
		tr.commit();
		session.close();
		return "更新成功";
	}

	@Override
	public List<Map<String, Object>> planDetail(int id) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="select * from ("
				+ "select pl.id id,pl.plname pname,pl.pldes  des,d.type,d.num num from plandetail d,plug pl where  pl.id=d.apiid and type=1 and d.pid=? "
				+ "UNION all "
				+ "select ip.id,ip.inname ,ip.des ,d.type,d.num   from plandetail d,ininterface ip where  ip.id=d.apiid and type=0 and d.pid=?) d "
				+ "ORDER BY d.num";	
		@SuppressWarnings("unchecked")
		NativeQuery<Object[]> query=session.createNativeQuery(str);
		query.setParameter(1, id)
				.setParameter(2, id);
		List<Object[]> list1=query.getResultList();	
		List<Map<String, Object>> list2=new ArrayList<Map<String,Object>>();
			for(Object[] o:list1){
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("id", o[0]);
				map.put("name", o[1]);
				map.put("des", o[2]);
				map.put("type", o[3]);
				map.put("num", o[4]);
				list2.add(map);
			}	
		tr.commit();
		session.close();
//		System.out.println(list2);
		return list2;
	}

	@Override
	public String deletePlan(int id) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="delete from plan where id=?";
		NativeQuery<plandetail> query=session.createNativeQuery(str, plandetail.class);
		query.setParameter(1, id);
		query.executeUpdate();
		tr.commit();
		session.close();
		return "删除成功";
	}

	@Override
	public String deleteDetail(int planid, int apiid, int type) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="delete from plandetail where  pid=? and apiid=? and type=?";
		NativeQuery<plandetail> query=session.createNativeQuery(str, plandetail.class);
		query.setParameter(1, planid)
				.setParameter(2, apiid)
				.setParameter(3, type);
		query.executeUpdate();
		tr.commit();
		session.close();
		if(type==0){
			return "删除接口成功";
		}else{
			return "删除组件成功";
		}
	}

	@Override
	public boolean checkPlan(int planid, int apiid, int type) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="select * from plandetail where pid=? and apiid=? and type=?";
		NativeQuery<plandetail> query=session.createNativeQuery(str, plandetail.class);
		query.setParameter(1, planid)
				.setParameter(2, apiid)
				.setParameter(3, type);
		List<plandetail> list=query.getResultList();
		tr.commit();
		session.close();
		if(list.size()==0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<plug> selectPlugName(String name, int planid) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="select * from plug where id not in(select pd.apiid from plandetail pd where  type=1 and pid=?) and plname like ?";
		NativeQuery<plug> query=session.createNativeQuery(str, plug.class);
		query.setParameter(1, planid)
				.setParameter(2, "%"+name+"%");
		List<plug> list=query.getResultList();
		tr.commit();
		session.close();
		return list;
	}

	@Override
	public String addPlug(plandetail p) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		session.save(p);
		tr.commit();
		session.close();
		return "计划添加组件成功";
	}

	@Override
	public List<ininterface> selectInterName(String name, int planid) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="select * from ininterface where id not in(select apiid from plandetail where pid=? and type=0)and inname like ?";
		NativeQuery<ininterface> query=session.createNativeQuery(str, ininterface.class);
		query.setParameter(1, planid)
				.setParameter(2, "%"+name+"%");
		List<ininterface> list=query.getResultList();
		tr.commit();
		session.close();
		return list;
	}

	@Override
	public String addInter(plandetail p) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		session.save(p);
		tr.commit();
		session.close();
		return "计划添加接口成功";
	}

	@Override
	public String plandetailAdd(plandetail pd) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		session.save(pd);
		tr.commit();
		session.close();
		return "新增成功";
	}

	@Override
	public int maxNum(int id) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
//		String str="select  IFNULL(max(num),0) from plandetail where pid=?";//这里要给为空默认值，不然为空会报空指针异常
		String str="select max(num) from plandetail where pid=?";//这种直接走object类型，排队类型为非空，再转int(object需要先转String再转int)
		@SuppressWarnings("unchecked")
		NativeQuery<Object> query=session.createNativeQuery(str);
		query.setParameter(1, id);
		Object	result=query.getSingleResult();
//		System.out.println("---------------result:"+result);
		tr.commit();
		session.close();
		int max;
		if(result==null){
			max=0;
		}else{
			String s=result.toString();
//			System.out.println("---------------s:"+s);
			max=Integer.parseInt(s);
		}
//		System.out.println(max+"---------------------------------");
		return max;
	}

	@Override
	public List<ininterface> planexecute(int apiid, int type) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str1="select * from ininterface  where id=?";
		String str2="select face.* from inplug inp,ininterface face where inp.inid=face.id and inp.pid=? ORDER BY inp.id ";
		List<ininterface> list=new ArrayList<ininterface>();
			if(type==1){
				NativeQuery<ininterface> query=session.createNativeQuery(str2, ininterface.class).setParameter(1, apiid);
				list=query.getResultList();
			}else if(type==0){
				NativeQuery<ininterface> query=session.createNativeQuery(str1, ininterface.class).setParameter(1, apiid);
				list=query.getResultList();
			}
		tr.commit();
		session.close();
		return list;
	}

	@Override
	public List<Map<String, Object>> list(int page, int limit) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="select p.id,p.pname,p.des,p.sysid,s.systemname from plan p,sysurl s where p.sysid=s.id limit ?,?";
		@SuppressWarnings("unchecked")
		NativeQuery<Object[]> query=session.createNativeQuery(str).setParameter(1, page*limit).setParameter(2, limit);
		List<Object[]> obj=query.getResultList();
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		tr.commit();
		session.close();
		for(Object[] o:obj){
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("id", o[0]);
			map.put("pname", o[1]);
			map.put("des", o[2]);
			map.put("sysid", o[3]);
			map.put("systemname", o[4]);
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> selectName(String name, int page, int limit) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="select p.id,p.pname,p.des,p.sysid,s.systemname from plan p,sysurl s where p.sysid=s.id and p.pname like ? limit ?,?";
		@SuppressWarnings("unchecked")
		NativeQuery<Object[]> query=session.createNativeQuery(str);
			query.setParameter(1, "%"+name+"%")
					.setParameter(2, page*limit)
					.setParameter(3, limit);
		List<Object[]> obj=query.getResultList();
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		for(Object[] o:obj){
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("id", o[0]);
			map.put("pname", o[1]);
			map.put("des", o[2]);
			map.put("sysid", o[3]);
			map.put("systemname", o[4]);
			list.add(map);
		}
		tr.commit();
		session.close();
		return list;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="select count(1) from plan";
		@SuppressWarnings("unchecked")
		NativeQuery<Object> query=session.createNativeQuery(str);	
		int count=Integer.parseInt(query.getSingleResult().toString());
		tr.commit();
		session.close();
		return count;
	}

	@Override
	public int count(String name) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="select count(1) from plan where pname like ?";
		@SuppressWarnings("unchecked")
		NativeQuery<Object> query=session.createNativeQuery(str);
			query.setParameter(1, "%"+name+"%");
		int count=Integer.parseInt(query.getSingleResult().toString());
		tr.commit();
		session.close();
		return count;
	}

	@Override
	public int count(int pid) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="select count(1) from (select  d.id from plandetail d,plug pl where pl.id=d.apiid and type=1 and d.pid=? "
				+ "UNION all "
				+ "select d.id from plandetail d,ininterface ip where  ip.id=d.apiid and type=0 and d.pid=?) d";	
		@SuppressWarnings("unchecked")
		NativeQuery<Object> query=session.createNativeQuery(str);
		query.setParameter(1, pid)
				.setParameter(2, pid);
		int count=Integer.parseInt(query.getSingleResult().toString());
		tr.commit();
		session.close();
		return count;
	}

	@Override
	public int countInterface(String name, int planid) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="select count(1) from ininterface where id not in(select apiid from plandetail where pid=? and type=0)and inname like ?";
		@SuppressWarnings("unchecked")
		NativeQuery<Object> query=session.createNativeQuery(str);
		query.setParameter(1, planid)
				.setParameter(2, "%"+name+"%");
		int count=Integer.parseInt(query.getSingleResult().toString());
		tr.commit();
		session.close();
		return count;
	}

	@Override
	public int countPlug(String name, int planid) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="select count(1) from plug where id not in(select pd.apiid from plandetail pd where  type=1 and pid=?) and plname like ?";
		@SuppressWarnings("unchecked")
		NativeQuery<Object> query=session.createNativeQuery(str);
		query.setParameter(1, planid)
				.setParameter(2, "%"+name+"%");
		int count=Integer.parseInt(query.getSingleResult().toString());
		tr.commit();
		session.close();
		return count;
	}

	@Override
	public List<Map<String, Object>> planDetail(int id, int page, int limit) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="select * from (select d.id id, pl.id apiid,pl.plname pname,pl.pldes  des,d.type,d.num num from plandetail d,plug pl "
				+ "where  pl.id=d.apiid and type=1 and d.pid=? UNION all select d.id id, ip.id,ip.inname ,ip.des ,d.type,d.num  "
				+ " from plandetail d,ininterface ip where  ip.id=d.apiid and type=0 and d.pid=?) d ORDER BY d.num limit ?,?";	
		@SuppressWarnings("unchecked")
		NativeQuery<Object[]> query=session.createNativeQuery(str);
		query.setParameter(1, id)
				.setParameter(2, id)
				.setParameter(3, page*limit)
				.setParameter(4, limit);
		List<Object[]> list1=query.getResultList();	
		List<Map<String, Object>> list2=new ArrayList<Map<String,Object>>();
			for(Object[] o:list1){
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("id", o[0]);
				map.put("apiid", o[1]);
				map.put("name", o[2]);
				map.put("des", o[3]);
				map.put("type", o[4]);
				map.put("num", o[5]);
				list2.add(map);
			}	
		tr.commit();
		session.close();
//		System.out.println(list2);
		return list2;
	}

	@Override
	public List<Map<String, Object>> selectInterName(String name, int planid, int page,
			int limit) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="select id, inname from ininterface where id not in(select apiid from plandetail where pid=? and type=0)and inname like ? limit ?,?";
		@SuppressWarnings("unchecked")
		NativeQuery<Object[]> query=session.createNativeQuery(str);
		query.setParameter(1, planid)
				.setParameter(2, "%"+name+"%")
				.setParameter(3, page*limit)
				.setParameter(4, limit);
		List<Object[]> obj=query.getResultList();
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		for(Object[] o:obj){
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("apiid", o[0]);
			map.put("name", o[1]);
			map.put("type", 0);
			list.add(map);
		}
		tr.commit();
		session.close();
		return list;
	}

	@Override
	public List<Map<String, Object>> selectPlugName(String name, int planid, int page,
			int limit) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="select id ,plname from plug where id not in(select pd.apiid from plandetail pd where  type=1 and pid=?) and plname like ? limit ?,?";
		@SuppressWarnings("unchecked")
		NativeQuery<Object[]> query=session.createNativeQuery(str);
		query.setParameter(1, planid)
				.setParameter(2, "%"+name+"%")
				.setParameter(3, page*limit)
				.setParameter(4, limit);
		List<Object[]> obj=query.getResultList();
		 List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		 for(Object[] o:obj){
			 Map<String, Object> map=new HashMap<String, Object>();
			 map.put("apiid", o[0]);
			 map.put("name", o[1]);
			 map.put("type", 1);
			 list.add(map);
		 }
		tr.commit();
		session.close();
		return list;
	}

	@Override
	public String deleteDetail(int id) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="delete from plandetail where  id=?";
		NativeQuery<plandetail> query=session.createNativeQuery(str, plandetail.class);
		query.setParameter(1, id);
		query.executeUpdate();
		tr.commit();
		session.close();
		return "删除成功";
	}

	@Override
	public String updateStatus(int pid, int status) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="update plan p set p.createid=? where p.Id=?";
		NativeQuery<plan> query=session.createNativeQuery(str, plan.class).setParameter(1, status).setParameter(2, pid);
		query.executeUpdate();
		tr.commit();
		session.close();
		if(status==1){
			return "执行状态更新成功";
		}else{
			return "就绪状态更新成功";
		}
	}

	@Override
	public List<Map<String, Object>> showPlanAll(int pid) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="select * from "
				+ "((select pl.id i,d.num ,infa.* from plandetail d,inplug pl,ininterface infa where  pl.pid=d.apiid and pl.inid=infa.id and d.type=1 and d.pid=?)"
				+ " UNION all "
				+ "(select 1 i,d.num ,ip.*   from plandetail d,ininterface ip where  ip.id=d.apiid and type=0 and d.pid=?)) d ORDER BY d.num,d.i";
		@SuppressWarnings("unchecked")
		NativeQuery<Object[]> query=session.createNativeQuery(str);
		query.setParameter(1, pid).setParameter(2, pid);
		List<Object[]> obj=query.getResultList();
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		for(Object[] o:obj){
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("inid", o[2]);
			map.put("url", o[4]);
			map.put("method", o[5]);
			map.put("param", o[7]);
			map.put("check", o[8]);
			list.add(map);
		}
		tr.commit();
		session.close();
		return list;
	}

	@Override
	public String showSystemUrl(int pid) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="SELECT s.urlname from plan p,sysurl s where p.sysid=s.id and p.id=?";
		NativeQuery<Object> query=session.createNativeQuery(str).setParameter(1, pid);
		String url=query.getSingleResult().toString();	
		tr.commit();
		session.close();
		return url;
	}




	


}
