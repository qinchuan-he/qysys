package action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//import net.sf.json.JsonConfig;



import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ViewResolver;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;

import entity.plan;
import entity.plug;
import serviceimpl.planImpl;
import serviceimpl.pluInterImpl;
import util.DateJsonValueProcessor;
/**
 * 测试类，目前就测试了list为空情况下的list.size()值和对应if的判断
 * @author sean
 *
 */

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str="spring-mvc.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(str);
//		pluInterImpl obj=ac.getBean("pluginterimpl", pluInterImpl.class);
		planImpl p=ac.getBean("planimpl", planImpl.class);
//		long a=p.maxNum(1);//数据库存在
		long b=p.maxNum(2);//数据库不存在
		
		
		
		
		
		
		
		
//		List<Map<String, Object>> list=p.planDetail(1);
//		List<plan> list=p.selectName("");
//		for(plan pp:list){
//			System.out.println(pp.updatetime);
//			System.out.println(pp.getUpdatetime());
//		}
//		for(plan pp:list){
//			System.out.println(pp.updatetime+"-------"+pp.createtime);
//		}
//		
//		Map<String, List<plan>> map=new HashMap<String, List<plan>>();
//		map.put("list", list);
//		
//		JsonConfig config=new JsonConfig();
//		config.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor());
////		System.out.println(list);
//		JSONArray json=JSONArray.fromObject(list,config);
//		JSONObject j=JSONObject.fromObject(map);
//		String j=JSONObject.toJSONString(list);
//		
////		System.out.println(json);
//		System.out.println(j);
//		System.out.println(list.toString());
//		Date d=new Date();
//		SimpleDateFormat smp=new SimpleDateFormat("yyyy--MM--dd HH:mm:ss");
//		String s=smp.format(d);
//		System.out.println(s);
		
//		for(plan pl:list){
//			System.out.println(pl.getPname()+"-----------"+pl.getDes()+"-----------"+pl.getUpdatetime());
//		}
//		
		

		//List<plug> list=obj.selectPlugAll();
////		List<plug> list=obj.selectPlugName("测试");
////		for(plug p:list){
////			System.out.println(p.id+"----"+p.plname+"--------"+p.pldes);
////		}
//		boolean b=obj.checkExistInter(1, 4);
//		System.out.println(b);
//		if(b){
//			System.out.println("可以加入");
//		}else{
//			System.out.println("已经存在");
//		}
////		System.out.println(list);//测试别的所以把上面的注释掉了，需要直接全部放开一次就好
		
		
		
		

	}
	
//	public static void testChar(){
//		String str="aASssaZz";
//		int A=0;
//		int a=0;
//		for(int i=0;i<str.length();i++){
////			if(str.charAt(i)>64&&str.charAt(i)<91){
////				A=A+1;
////			}else if(str.charAt(i)>96&&str.charAt(i)<123){
////				a=a+1;
////			}
//			char c=str.charAt(i);
//			
//			if(c>='a'&&c<='z'){
//				a=a+1;
//			}else if(c>='A'&&c<='Z'){
//				A=A+1;
//			}
//			
//			
////			System.out.println(str.charAt(i));
//			
//		}
//		System.out.println(A);
//		System.out.println(a);
//		
//		
//	}

}




















