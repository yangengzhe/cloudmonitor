package com.ices.csp.vmorder.dispatch.service;

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

import com.ices.csp.common.enumitem.VMOrderState;
import com.ices.csp.vmorder.domain.VMItem;
import com.ices.csp.vmorder.domain.VMOrder;
import com.ices.csp.vmorder.myorders.dto.MyordersDto;

@Service
public class DispatchServiceImpl implements DispatchService{
	@Override
	public void changestateDispatch(MyordersDto myordersDto) {
		VMOrderState vmstate = VMOrderState.dispatched;
		VMOrder vmorder = VMOrder.findById(myordersDto.getId());
		vmorder.setState(vmstate);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		String datetime = sdf.format(date);
		String time = datetime.substring(10, 19);
		String dispatchdate = myordersDto.getDispatchDate().substring(0,10)+time;
		vmorder.setDispatchDate(dispatchdate);
		LoginUser user = User.getCurrentUser();
		vmorder.setDispatcher(User.findById(user.getUserId()));
	}
	@Override
	public void changestateCancel(int[] canceldispatch) {
		VMOrderState vmstate = VMOrderState.canceled;
		for (int i = 0; i < canceldispatch.length; i++) {
			VMOrder vmorder = VMOrder.findById(canceldispatch[i]);
			vmorder.setState(vmstate);
		}

	}
	@Override
	public void deleteDispatch(int[] deletedispatch) {
		for (int i = 0; i < deletedispatch.length; i++) {
			VMOrder vmorder = VMOrder.findById(deletedispatch[i]);
			List<VMItem> vmitemList = VMItem.findItemsByOrderId(deletedispatch[i]);
			for (VMItem vmitem : vmitemList) {
				vmitem.delete();
			}
			vmorder.delete();
		}

	}
	@Override
	public List<MyordersDto> findVMOrderForDispatch(PagingBean pb) throws DtoException {
		List<MyordersDto> resultList = new ArrayList<MyordersDto>();
		List<VMOrder> result = VMOrder.findVMOrderForDispatch(pb);
		for (VMOrder vmorder : result) {
			MyordersDto myordersDto = DtoHelper.build(MyordersDto.class, vmorder);
			resultList.add(myordersDto);
		}
		return resultList;
	}
	@Override
	public List<MyordersDto> searchDispatch(Map<String, String> data) throws DtoException {
		List<MyordersDto> resultList = new ArrayList<MyordersDto>();
		Enterprise enterprise = null;
		if(data.get("enterprise")!=null){
			enterprise = Enterprise.findByName(data.get("enterprise"));
		}
		try{
			List<VMOrder> result = VMOrder.findByParamForDispatch(data.get("number"),enterprise,data.get("formdate"),data.get("todate"));
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
