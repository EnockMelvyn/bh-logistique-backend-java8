/**
 * 
 */
package bhci.dmg.bhLogistique.utils;

import java.io.IOException;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import lombok.extern.java.Log;

/**
 * @author ikouame
 *
 */
@Log
public class Utilities { 
	
	private static Boolean notificationMail(String url , String content) {
		OkHttpClient client = new OkHttpClient();
				  
				MediaType mediaType = MediaType.parse("application/json");
				RequestBody body = RequestBody.create(mediaType, content
						);
				Request request = new Request.Builder()
				  .url(url)
				  .method("POST", body)
				  .addHeader("Content-Type", "application/json")
				  .build();
				Response response = null;
				try {
					 response = client.newCall(request).execute();
					 log.info(response.body().string());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}finally {
					if(response!=null)
						try {
							response.body().close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
				
				return true;
	}

	public static Boolean notificationMail(String serveurUrl, String to, String cc, String subject,
			String from, String content) {
		// TODO Auto-generated method stub
		String contentMail = "{\r\n   "
				+ " \"to\": \""+to+"\",\r\n    "
				+ "\"subject\": \""+subject+"\",\r\n    "
				+ "\"from\": \""+from+"\",\r\n    "
				+ "\"content\": \""+content+"\"\r\n}";
		
		if(cc != null && cc.trim() != "") {
			   contentMail = "{\r\n   "
					+ " \"to\": \""+to+"\",\r\n    "
					+ " \"cc\": \""+cc+"\",\r\n    "
					+ "\"subject\": \""+subject+"\",\r\n    "
					+ "\"from\": \""+from+"\",\r\n    "
					+ "\"content\": \""+content+"\"\r\n}";
		}
		return notificationMail(serveurUrl, contentMail);
	}
	
	public static Boolean notificationMail(String serveurUrl, String to, String subject, String from, String content) {
		// TODO Auto-generated method stub
		String contentMail = "{\r\n   "
				+ " \"to\": \""+to+"\",\r\n    "
				+ "\"subject\": \""+subject+"\",\r\n    "
				+ "\"from\": \""+from+"\",\r\n    "
				+ "\"content\": \""+content+"\"\r\n}";
		
		return notificationMail(serveurUrl, contentMail);
	}

}
