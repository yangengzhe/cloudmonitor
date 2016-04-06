/**
 * 
 */
package  com.ices.csp.software.test;


//import javax.ws.rs.client.Client;
//import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.client.Entity;
//import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import com.ices.csp.software.domain.SoftwareType;


/**
 * @author MFC
 *
 */
public class ClientTest {
//	private static String serverUri = "http://localhost:8080/softwaretype";  
//
//	
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		System.out.println("开始测试...");
//		insert();
//		System.out.println("测试结束...");
//
//	}
//	private static void insert(){
//		int id=4;
//		String code="4";
//		String name="图形软件";
//		SoftwareType softwareType=new SoftwareType();
//		softwareType.setId(id);
//		softwareType.setCode(code);
//		softwareType.setName(name);
//		Client client=ClientBuilder.newClient().register(JacksonJsonProvider.class);
//		WebTarget target=client.target(serverUri+"/insertSoftwareType");
//		Response response=target.request().buildPost(Entity.entity(softwareType, MediaType.APPLICATION_JSON)).invoke();
//		Response response1=target.request().get();
//		//SoftwareType hm=response1.readEntity(SoftwareType.class);
//				
//		//.readEntity(HashMap.class);
//		
//		//System.out.println("返回值："+hm.get("success"));
//	}
//
}
