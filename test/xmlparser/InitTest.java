package xmlparser;

import javax.xml.bind.JAXBException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.BaseSpringTest;
import com.ices.xml.jaxb.JAXBContextWrapper;
import com.ices.xml.xml.PlatformInit;

public class InitTest extends BaseSpringTest{
	@Autowired
	private JAXBContextWrapper jaxb;
	@Test
	public void testExecute() throws InterruptedException {
		PlatformInit pi = null;
		try {
			pi = jaxb.unmarshal("J://saas.xml", PlatformInit.class);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		System.out.println(pi);
	}
}
