package com.ices.csp.vmorder.audit.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.cspframework.common.PagingBean;
import org.cspframework.core.dto.exception.DtoException;
import org.cspframework.core.dto.util.DtoHelper;
import org.cspframework.manage.enterprise.domain.Enterprise;
import org.cspframework.manage.user.domain.LoginUser;
import org.cspframework.manage.user.domain.User;
import org.springframework.stereotype.Service;

import com.ices.csp.common.enumitem.AuditOpinion;
import com.ices.csp.common.enumitem.IsCreated;
import com.ices.csp.common.enumitem.VMOrderState;
import com.ices.csp.vmorder.domain.VMItem;
import com.ices.csp.vmorder.domain.VMOrder;
import com.ices.csp.vmorder.myorders.dto.MyordersDto;

@Service
public class AuditServiceImpl implements AuditService{
	@Override
	public void createvmitem(int[] vmsid) {
		IsCreated iscreatedno = IsCreated.NO;
		IsCreated iscreatedyes = IsCreated.YES;
		VMOrder vmorder = VMItem.findById(vmsid[0]).getVmorder();
		List<VMItem> result = VMItem.findItemsByOrderId(vmorder.getId());
		for (VMItem vmitem : result) {
			vmitem.setIscreated(iscreatedno);
		}
		for (int i = 0; i < vmsid.length; i++) {
			VMItem vmitem = VMItem.findById(vmsid[i]);
			vmitem.setIscreated(iscreatedyes);
		}

	}
	@Override
	public void allvmitemnocreate(Integer orderid) {
		IsCreated iscreatedno = IsCreated.NO;
		List<VMItem> result = VMItem.findItemsByOrderId(orderid);
		for (VMItem vmitem : result) {
			vmitem.setIscreated(iscreatedno);
		}
	}
	@Override
	public void changestateAudit(Integer orderid) {
		VMOrderState vmstate = VMOrderState.audited;
		VMOrder vmorder = VMOrder.findById(orderid);
		vmorder.setState(vmstate);
	}
	@Override
	public void commitAudit(MyordersDto myordersDto) throws Exception {
		VMOrder vmorder = VMOrder.findById(myordersDto.getId());
		AuditOpinion op = AuditOpinion.getMatchByName(myordersDto.getAuditOpinion());
		LoginUser user = User.getCurrentUser();
		vmorder.setAuditor(User.findById(user.getUserId()));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		String datetime = sdf.format(date);
		String time = datetime.substring(10, 19);
		String auditdate = myordersDto.getAuditDate().substring(0,10)+time;
		vmorder.setAuditDate(auditdate);
		vmorder.setAuditOpinion(op);
		vmorder.setAuditDesc(myordersDto.getAuditDesc());

	}
	
	@Override
	public List<MyordersDto> findVMOrderISApply(PagingBean pb) throws DtoException {
		List<MyordersDto> resultList = new ArrayList<MyordersDto>();
		List<VMOrder> result = VMOrder.findVMOrderISApply(pb);
		for (VMOrder vmorder : result) {
			MyordersDto myordersDto = DtoHelper.build(MyordersDto.class, vmorder);
			resultList.add(myordersDto);
		}
		return resultList;
	}
	@Override
	public List<MyordersDto> searchAudit(Map<String, String> data) throws DtoException {
		List<MyordersDto> resultList = new ArrayList<MyordersDto>();
		Enterprise enterprise = null;
		if(data.get("enterprise")!=null){
			enterprise = Enterprise.findByName(data.get("enterprise"));
		}
		try{
			List<VMOrder> result = VMOrder.findByParamForAudit(data.get("number"),enterprise,data.get("formdate"),data.get("todate"));
			for (VMOrder vmorder : result) {
				MyordersDto myordersDto = DtoHelper.build(MyordersDto.class, vmorder);
				resultList.add(myordersDto);
			}
			return resultList;
			
			}catch(Exception e){
				return resultList;
			
			}

	}
}
