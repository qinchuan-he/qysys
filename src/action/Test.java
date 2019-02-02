package action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//import net.sf.json.JsonConfig;







import org.apache.poi.xslf.model.geom.SqrtExpression;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ViewResolver;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vividsolutions.jts.awt.PointShapeFactory.Square;

import entity.plan;
import entity.plug;
import serviceimpl.planImpl;
import serviceimpl.pluInterImpl;
import util.DateJsonValueProcessor;
import util.ExecuteInterface;
/**
 * 测试类，目前就测试了list为空情况下的list.size()值和对应if的判断
 * @author sean
 *
 */

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		ExecuteInterface exe=new ExecuteInterface();
		String url="https://testcyprexsvc.fir.ai/account/user/signin/";
		String method="post";
//		String parameter="type=account&username_no=19958585555&passwd=Test123456&validCode=&inviteCode=&";
		String parameter="{'type': 'account','username_no': 19958585555,'passwd': 'Test123456','validCode': '','inviteCode': ''}";
		
		JSONObject str=
				exe.httpRequest(url, method, parameter,"19958585555");
		System.out.println(str);
		
		String url1="https://testcyprexsvc.fir.ai/account/user/info/";
		String method1="get";
		String parameter1=null;
		JSONObject str1=exe.httpRequest(url1, method1, parameter1,"19958585555");
		System.out.println(str1);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		String str="spring-mvc.xml";
//		ApplicationContext ac=new ClassPathXmlApplicationContext(str);
////		pluInterImpl obj=ac.getBean("pluginterimpl", pluInterImpl.class);
//		planImpl p=ac.getBean("planimpl", planImpl.class);
////		long a=p.maxNum(1);//数据库存在
//		long b=p.maxNum(2);//数据库不存在
		
//		for(int i=100;i<=200;i++){
//			int k=i%3;
//			if(k==0){
//				
//				continue;
//			}
//			System.out.println(i);
//		}
//		double r=1.00;
//		for(int i=2;i<=10*10*10*10+1;i++){
//			int k=i%2;
//			if(k==0){
//				double a=(double)1/i;
//				r=r+a;
//				System.out.println(r+"====="+a);
//			}else{
//				double b=(double)1/i;
//				r=r-b;
//				System.out.println("----r:"+r+"====="+b);
//			}
//			
//		}
		
//		System.out.println(10*10*10*10+1);
		
		
		
		
		//判断素数
//		int s=20;
//		int p=1;
//		int a=(int) Math.sqrt(s);
//		for(int i=2;i<=a;i++){
//			int k=s%i;
//			if(k==0){
//				p=0;
//				System.out.println("不是素数"+"---------"+i+"-----"+k);
//				break;
//			}else{
//				
//				
//				
//			}
//			
//		}
//		if(p==1){
//			System.out.println("是素数");
//		}
//		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		int x=30;int y=50;
//		x=100;
//		System.out.println(sub(x,y));
//		
//		System.out.println(x+"------"+y);
//		int z=9;
//		
//		System.out.println(z);
//		System.out.println(z+++2);
//		System.out.println(z);
		
		
		
		
		
		
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
	public static int sub(int x,int y){
		y=x>y?x:y;
		
		return y;
	}

}





















