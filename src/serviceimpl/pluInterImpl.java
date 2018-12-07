package serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Component;

import entity.ininterface;
import entity.inplan;
import entity.inplug;
import entity.plug;
import service.plugInter;
@Component(value="pluginterimpl")
public class pluInterImpl implements plugInter{
	@Resource(name="sessionFactory")
	public SessionFactory sessionfactory;

	@Override
	public List<plug> selectPlugAll() {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="select * from plug";
		NativeQuery<plug> query=session.createNativeQuery(str, plug.class);
		List<plug> list=query.getResultList();
		tr.commit();
		session.close();
		return list;
	}

	@Override
	public List<plug> selectPlugName(String name) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="select * from plug p where p.plname like ?";
		NativeQuery<plug> query=session.createNativeQuery(str, plug.class);
		query.setParameter(1, "%"+name+"%");
		List<plug> list=query.getResultList();
		tr.commit();
		session.close();
		return list;
	}

	@Override
	public List<plug> selectPlugId(int id) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="select * from plug where id=?";
		NativeQuery<plug> query=session.createNativeQuery(str, plug.class);
		query.setParameter(1, id);
		List<plug> list=query.getResultList();
		tr.commit();
		session.close();
		return list;
	}

	@Override
	public List<ininterface> selectInterPlug(int id) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="select f.* from inplug p,ininterface f where p.inid=f.id and p.pid=?";
		NativeQuery<ininterface> query=session.createNativeQuery(str, ininterface.class);
		query.setParameter(1, id);
		List<ininterface> list=query.getResultList();
		tr.commit();
		session.close();
		return list;
	}

	@Override
	public String addPlug(plug p) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		session.save(p);
		tr.commit();
		session.close();
		return "新增成功";
	}

	@Override
	public String modifyPlug(plug p) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="update plug set plname=?,pldes=?,updatetime=? where id=?";
		NativeQuery<plug> query=session.createNativeQuery(str, plug.class);
		query.setParameter(1, p.getPlname())
				.setParameter(2, p.getPldes())
				.setParameter(3, p.getUpdatetime())
				.setParameter(4, p.getId());
		query.executeUpdate();
		tr.commit();
		session.close();
		return "修改成功";
	}

	@Override
	public String deletePlug(int id) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="delete from plug  where id=?";
		NativeQuery<plug> query=session.createNativeQuery(str, plug.class);
		query.setParameter(1, id);
		query.executeUpdate();
		tr.commit();
		session.close();
		return "删除成功";
	}

	@Override
	public List<ininterface> selectInter(String name,int id) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="select * from ininterface where id not in(select f.id from inplug p,ininterface f where p.inid=f.id and p.pid=?) and inname like ? ";
		NativeQuery<ininterface> query=session.createNativeQuery(str, ininterface.class);
		query.setParameter(1, id)
				.setParameter(2, "%"+name+"%");
		List<ininterface> list=query.getResultList();
		tr.commit();
		session.close();
		return list;
	}

	@Override
	public String addPlugInter(List<inplug> list) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		for(inplug p:list){
			session.save(p);
			session.flush();
			session.clear();
		}
		tr.commit();
		session.close();
		return "新增成功";
	}

	@Override
	public String deletePlugInter(int pid,int inid) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="delete from inplug where pid=? and inid=?";
		NativeQuery<inplug> query=session.createNativeQuery(str,inplug.class);
		query.setParameter(1, pid)
				.setParameter(2, inid);
		query.executeUpdate();
		tr.commit();
		session.close();
		return "删除成功";
	}

	@Override
	public String addPlugSingleInter(inplug p) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		session.save(p);
		tr.commit();
		session.close();
		return "添加接口成功";
	}

	@Override
	public boolean checkExistInter(int pld, int inid) {
		// TODO Auto-generated method stub
		Session session=sessionfactory.openSession();
		Transaction tr=session.beginTransaction();
		String str="select * from inplug where pid=? and inid=?";
		NativeQuery<inplug> query=session.createNativeQuery(str, inplug.class);
		query.setParameter(1, pld)
				.setParameter(2, inid);
		List<inplug> list=query.getResultList();
		tr.commit();
		session.close();
		System.out.println(list+"---------------------------");
		System.out.println("list.size())------------------"+list.size());
		if(list.size()==0){
			return true;
		}else{
			return false;
		}
	}

}
