package com.ices.csp.vmorder.apply.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.cspframework.core.dto.util.DtoHelper;
import org.cspframework.manage.enterprise.domain.Enterprise;
import org.cspframework.manage.user.domain.LoginUser;
import org.cspframework.manage.user.domain.User;
import org.springframework.stereotype.Service;

import com.ices.csp.common.enumitem.NetType;
import com.ices.csp.common.enumitem.RentType;
import com.ices.csp.common.enumitem.TenancyUnit;
import com.ices.csp.common.enumitem.VMItemState;
import com.ices.csp.common.enumitem.VMOrderState;
import com.ices.csp.node.domain.Node;
import com.ices.csp.os.domain.OSVersion;
import com.ices.csp.vmorder.apply.dto.ApplyDto;
import com.ices.csp.vmorder.apply.dto.OrderDto;
import com.ices.csp.vmorder.domain.VMItem;
import com.ices.csp.vmorder.domain.VMOrder;

@Service
public class ApplyServiceImpl implements ApplyService{
	@Override
	public void addorder(ApplyDto applyDto) throws Exception {
		VMOrder vmorder = DtoHelper.dismantle(VMOrder.class, applyDto);
		LoginUser user = User.getCurrentUser();
		Integer userid = user.getUserId();
		//创建订单编号
		String data = applyDto.getApplyDate();
		String data2 = data.substring(0, 4)+data.substring(5, 7)+data.substring(8,10);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		String datetime = sdf.format(date);
		String time = datetime.substring(10, 19);
		String time2 = datetime.substring(11, 13)+datetime.substring(14, 16)+datetime.substring(17, 19);
		String data3 = data2 + time2;
		//String randomno = UUID.randomUUID().toString();
		String orderno = "OR" + data3 + userid;
		vmorder.setNumber(orderno);


		String applydate = applyDto.getApplyDate().substring(0,10)+time;
		vmorder.setApplyDate(applydate);
		VMOrderState state = VMOrderState.applying;
		vmorder.setState(state);
		vmorder.setAmount(applyDto.getOrders().size());

		vmorder.setEnterprise(Enterprise.findById(user.getEnterpriseId()));
		vmorder.setApplicant(User.findById(user.getUserId()));
		vmorder.insert();
		List<OrderDto> result2 = applyDto.getOrders();
		int i=1;
		for (OrderDto orderDto : result2) {
			//VMItem vmitem = new VMItem();
			VMItem vmitem = DtoHelper.dismantle(VMItem.class, orderDto);
			vmitem.setVmorder(vmorder);
			vmitem.setNo(i);
			vmitem.setInitDate(orderDto.getInitDate()+time);
			VMItemState state2 = VMItemState.applying;
			vmitem.setState(state2);
			vmitem.setNode(Node.findByNameCurrent(orderDto.getNodeName()));
			vmitem.setOsversion(OSVersion.findByname(orderDto.getOsversion()));
			vmitem.setNetType(NetType.getMatchByName(orderDto.getNetType()));
			vmitem.setRentType(RentType.getMatchByName(orderDto.getRentType()));
			vmitem.setUnit(TenancyUnit.getMatchByName(orderDto.getUnit()));
			vmitem.insert();
			i++;
		}
	}
}
