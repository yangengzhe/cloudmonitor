package com.ices.csp.vmorder.apply.ctrl;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ices.csp.vmorder.apply.dto.ApplyDto;
import com.ices.csp.vmorder.apply.service.ApplyService;
@Path(value = "apply")
@Component
public class ApplyCtrl {
	
	@Autowired
	private ApplyService applyService;
	
	@PUT
	@Produces({ "application/json" })
	@Path(value = "addorder")
	public Map<String, Object> addorder(ApplyDto applyDto) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			applyService.addorder(applyDto);
			result.put("status", "success");
		} catch (Exception e) {
			result.put("status", "error");
		}
		return result;
	}
}
