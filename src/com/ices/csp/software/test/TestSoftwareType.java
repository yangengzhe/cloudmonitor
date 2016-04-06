package  com.ices.csp.software.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



import org.cspframework.manage.role.service.*;
import org.cspframework.manage.role.domain.*;

import com.ices.csp.software.domain.SoftwareType;
import com.ices.csp.software.dto.SoftwareDto;
import com.ices.csp.software.service.SoftwareService;
import com.ices.csp.software.service.SoftwareTypeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test/applicationContext.xml"})

public class TestSoftwareType {
	
	@Autowired
	private SoftwareTypeService softwareTypeService;
	
	@Autowired
	private SoftwareService softwareService;
	
	@Test
	public void testCreateSoftware(){
		//int id=2;
		String code="3";
		String name="软件3";
		String version="2.0";
		
		SoftwareDto softwareDto=new SoftwareDto();
		softwareDto.setCode(code);
		softwareDto.setName(name);
		softwareDto.setVersion(version);
		softwareDto.setTypeId(1);
		
		/*Software software=new Software();
		//software.setId(id);
		software.setCode(code);
		software.setName(name);
		software.setVersion(version);*/
		
		
		try{
			softwareService.create(softwareDto);
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	@Test
	public void testQuerySoftware()throws Exception{
		List<SoftwareDto> lists=softwareService.getSoftwares("WS");
		for(int i=0;i<lists.size();i++){
			
			SoftwareDto st=lists.get(i);
			
				System.out.println(st.getCode()+
						","+st.getName()+","+st.getVersion()+","+st.getTypeName());
			
			
		}
	}
	
	

	@Test
	public void test() {
		//fail("Not yet implemented");
		//int id=3;
		String code="4";
		String name="antivirus program";
		String demo="杀毒软件";
		SoftwareType softwareType=new SoftwareType();
		//softwareType.setId(id);
		softwareType.setCode(code);
		softwareType.setName(name);
		softwareType.setDemo(demo);
		try{
			softwareTypeService.insert(softwareType);
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	
	@Test
	public void test3(){
		List<SoftwareType> lists=softwareTypeService.getAll();
		for(int i=0;i<lists.size();i++){
			
			SoftwareType st=lists.get(i);
			System.out.println(st.getCode()+
					","+st.getName()+","+st.getDemo());
		}
	}

}
