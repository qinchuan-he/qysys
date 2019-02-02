package util;

/**
 * @author sean
 * 2019-01-30,后端执行的方法，实测发现http和https都是一样的，只是url不一样，post和get不用去专门设置方法，会自动判断（post需要设置输入输出流）
 * 
 * 
 */
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;
@Component(value="executeInterface")
public class ExecuteInterface {
	
//	public static void main(String[] args){
////		String url="http://192.168.1.219:8000/data/api/retrieve/es/search_fulltext/page?name=&source=&file_id=&url=";
////		String method="get";
//		String url="https://testcyprexsvc.fir.ai/account/user/judge/register/";
//		String method="post";
//		String parameter="{ 'key' : 'mobile' , 'value' : 19958658569 } ";	
//		String name=httpRequest(url, method, parameter,"19958658569");
//		System.out.println(name);
//	}
	
	//http请求，实测发现不分http和https,约束必须传入json类字符串（和数据库存储匹配）
	public  JSONObject httpRequest(String requestUrl,String requestMethod,String parameter,String sessionId){
//		System.out.println("==========================开始调用执行方法");
		StringBuffer buffer = null;
		System.out.println(parameter);			
//		if(parameter.length()!=0){
//
//		}
		
		String cookie="fir_session_id="+sessionId;//设定死cookie，正常应该一个用户一个，那就根据用户账号，目前不加
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection conn=(HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Cookie", cookie);
			if(requestMethod.equals("post")){
				//conn.setRequestMethod("post");
				JSONObject json=JSONObject.fromObject(parameter);
				@SuppressWarnings("unchecked")
				Map<String, Object> map=json;
				String str="";
				for(java.util.Map.Entry<String, Object> entry:map.entrySet()){
					str+=entry.getKey()+"="+entry.getValue()+"&";
				}
				conn.setDoOutput(true);
				conn.setDoInput(true);
				OutputStream os=conn.getOutputStream();
				os.write(str.getBytes("utf-8"));
				os.close();
			}
			
			InputStream is=conn.getInputStream();
			InputStreamReader isr=new InputStreamReader(is);//字节转换成字符
			BufferedReader br=new BufferedReader(isr);
			 buffer=new StringBuffer();
			String line;
			while((line=br.readLine())!=null){
				buffer.append(line);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
//		System.out.println("--------------"+buffer.toString());
		JSONObject result=JSONObject.fromObject(buffer.toString());
		return result;
	}
	

}
