package com.ices.csp.vmorder.createvms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.cspframework.common.PagingBean;
import org.cspframework.core.dto.exception.DtoException;
import org.cspframework.core.dto.util.DtoHelper;
import org.cspframework.manage.enterprise.domain.Enterprise;
import org.springframework.stereotype.Service;

import com.ices.csp.common.enumitem.RunState;
import com.ices.csp.common.enumitem.VMItemState;
import com.ices.csp.common.enumitem.VMOrderState;
import com.ices.csp.server.domain.Server;
import com.ices.csp.vmorder.createvms.CreateVMByWake;
import com.ices.csp.vmorder.createvms.SetIP;
import com.ices.csp.vmorder.createvms.StartVMByWake;
import com.ices.csp.vmorder.domain.VMItem;
import com.ices.csp.vmorder.domain.VMOrder;
import com.ices.csp.vmorder.myorders.dto.MyordersDto;
import com.ices.csp.vmorder.myvms.dto.MyvmsDto;

@Service
public class CreatevmsServiceImpl implements CreatevmsService{
	@Override
	public void changestateCreatevms(Integer orderid) throws Exception {
		VMOrderState vmstate = VMOrderState.finished;
		VMItemState itemstate = VMItemState.created;
		VMItemState itemstate2 = VMItemState.canceled;
		RunState runstate = RunState.ON;
		VMOrder vmorder = VMOrder.findById(orderid);
		List<VMItem> result =VMItem.findItemsByOrderId(orderid);
		for (VMItem vmitem : result) {
			if(vmitem.getIscreated().getValue().equals("创建")){
				//2015-11-25 zzy
				
				Integer cpuNmubers = vmitem.getCpu();
				Integer memSize = vmitem.getMem();
				String VMName = vmitem.getVmname();
				CreateVMByWake myvm = new CreateVMByWake();
				StartVMByWake mystart = new StartVMByWake();
				String uuid = myvm.crateVM(VMName, cpuNmubers, memSize);

				if(uuid==null){
					 System.out.println("创建虚拟机失败");    
				}else{
					//将虚拟机的uuid记录下来
					vmitem.setUuid(uuid);
				}
				//下面开机
				int i = mystart.startVM(uuid);
				if(i==1){
					System.out.println("开机成功");   
				}else{
					System.out.println("开机失败");   
				}
				int flag = 0;
				SetIP setmyip = new SetIP();
				String finalIP = vmitem.getIp();
				//下面修改ip地址
				while(flag==0){
					 Thread.sleep(10000);
					int a = setmyip.setIpByWake("172.29.131.94", 12345, finalIP);
					if(a==1){
						System.out.println("修改ip成功"+a);
						flag = 1;
					}else{
						System.out.println("修改ip失败"+a);
					}
				}
				//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				vmitem.setState(itemstate);
				vmitem.setRunstate(runstate);
			}else{
				vmitem.setState(itemstate2);
			}

		}
		vmorder.setState(vmstate);
	}
	@Override
	public void commitCreatevmsedit(MyvmsDto myvmsDto) throws Exception {
		VMItem vmitem = VMItem.findById(myvmsDto.getId());
		vmitem.setVmnumber(myvmsDto.getVmnumber());
		vmitem.setVmname(myvmsDto.getVmname());
		vmitem.setAccout(myvmsDto.getAccout());
		vmitem.setPassword(myvmsDto.getPassword());
		vmitem.setIp(myvmsDto.getIp());
		vmitem.setDnsServer1(myvmsDto.getDnsServer1());
		vmitem.setDnsServer2(myvmsDto.getDnsServer2());
		vmitem.setSubnetMask(myvmsDto.getSubnetMask());
		vmitem.setGateway(myvmsDto.getGateway());
		vmitem.setDns(myvmsDto.getDns());
		vmitem.setServer(Server.findByParam(null, myvmsDto.getServerName()).get(0));
	}
	@Override
	public List<MyordersDto> findVMOrderForCreatevms(PagingBean pb) throws DtoException {
		List<MyordersDto> resultList = new ArrayList<MyordersDto>();
		List<VMOrder> result = VMOrder.findVMOrderForCreatevms(pb);
		for (VMOrder vmorder : result) {
			MyordersDto myordersDto = DtoHelper.build(MyordersDto.class, vmorder);
			resultList.add(myordersDto);
		}
		return resultList;
	}
	@Override
	public List<MyordersDto> searchCreatevms(Map<String, String> data) throws DtoException {
		List<MyordersDto> resultList = new ArrayList<MyordersDto>();
		Enterprise enterprise = null;
		if(data.get("enterprise")!=null){
			enterprise = Enterprise.findByName(data.get("enterprise"));
		}
		try{
			List<VMOrder> result = VMOrder.findByParamForCreatevms(data.get("number"),enterprise,data.get("formdate"),data.get("todate"));
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
