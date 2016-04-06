package com.ices.csp.vmorder.myvms.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.cspframework.common.PagingBean;
import org.cspframework.core.dto.exception.DtoException;
import org.cspframework.core.dto.util.DtoHelper;
import org.cspframework.manage.enterprise.domain.Enterprise;
import org.springframework.stereotype.Service;

import com.ices.csp.common.enumitem.RunState;
import com.ices.csp.common.enumitem.VMItemState;
import com.ices.csp.node.domain.Node;
import com.ices.csp.vmorder.createvms.ShutdownVMByWake;
import com.ices.csp.vmorder.createvms.StartVMByWake;
import com.ices.csp.vmorder.domain.VMItem;
import com.ices.csp.vmorder.domain.VMItemMonitor;
import com.ices.csp.vmorder.myvms.dto.MonitorDto;
import com.ices.csp.vmorder.myvms.dto.MyvmsDto;

@Service
public class MyvmsServiceImpl implements MyvmsService{
	@Override
	public List<MonitorDto> myvmsMonitor(Integer vmitemMark) throws DtoException{
		List<MonitorDto> resultList = new ArrayList<MonitorDto>();
		List<VMItemMonitor> result = VMItemMonitor.findByVMItemid(vmitemMark);
		for (VMItemMonitor vmitemMonitor : result) {
			MonitorDto monitorDto = DtoHelper.build(MonitorDto.class, vmitemMonitor);
			resultList.add(monitorDto);
		}
		return resultList;
	}
	@Override
	public int startVMItem(Integer myvmid) throws Exception {
		VMItem vmitem = VMItem.findById(myvmid);
		int a = 0;
		if(vmitem.getRunstate().getValue()=="开机"){
			return a;
		}else{
			RunState runstate = RunState.ON;
			String uuid = vmitem.getUuid();
			if(uuid!=null && !uuid.equals("")){
				StartVMByWake myvmbywake =new StartVMByWake();
				 a = myvmbywake.startVM(uuid);//根据虚拟机的唯一标识uuid来开启虚拟机，
				//如果成功返回1，异常返回-1，已经处于开机返回0，虚拟机不存在返回2
				 if(a==1||a==0){
					 vmitem.setRunstate(runstate);
				 }else{
					 vmitem.setRunstate(RunState.Shutdown);
				 }	
			}
			return a;
		}
	}
	@Override
	public int shutdownVMItem(Integer myvmid) throws Exception {
		VMItem vmitem = VMItem.findById(myvmid);
		String a= vmitem.getRunstate().getValue();
		int b=0;
		if(a=="关机"){
			return b;
		}else{
			RunState runstate = RunState.Shutdown;
			String uuid = vmitem.getUuid();
			if(uuid!=null && !uuid.equals("")){
				ShutdownVMByWake myvmbywake =new ShutdownVMByWake();
				 b = myvmbywake.shutdownVM(uuid);//根据虚拟机的唯一标识uuid来关闭虚拟机，
				 //如果成功返回1，异常返回-1，已经处于关机返回0，虚拟机不存在返回2
				 if(b==1||b==0){
					 vmitem.setRunstate(runstate);
				 }
			}
			return b;
		}
	}
	@Override
	public void updateVMItem(MyvmsDto myvmsDto) throws Exception {
		VMItem vmitem = VMItem.findById(myvmsDto.getId());
		DtoHelper.dismantle(vmitem, myvmsDto);
	}
	@Override
	public List<MyvmsDto> findAllMyvms(PagingBean pb) throws DtoException, ParseException {
		List<MyvmsDto> resultList = new ArrayList<MyvmsDto>();
		List<VMItem> result = VMItem.findAll(pb);
		for (VMItem vmitem : result) {
			MyvmsDto myvmsDto = DtoHelper.build(MyvmsDto.class, vmitem);
			String renttime = vmitem.getTenancy().toString()+vmitem.getUnit().getValue();
			myvmsDto.setRenttime(renttime);
			String config = vmitem.getCpu().toString()+"核/"+vmitem.getMem().toString()+"GB/"+vmitem.getDisk().toString()+"GB/"+vmitem.getBandwidth().toString()+"Mbps";
			myvmsDto.setConfig(config);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			Date initDate=new Date();
			Date curDate=new Date();
			Date finalDate=new Date();
			initDate = sdf.parse(vmitem.getInitDate());
			long day=(curDate.getTime()-initDate.getTime())/(24*60*60*1000);
			String unit = vmitem.getUnit().getValue();
			int totalday = 0;
			if(unit=="年"){//统一视为一年365天
				totalday = vmitem.getTenancy()*365;
			}else if(unit=="月"){//统一视为一个月31天
				totalday = vmitem.getTenancy()*31;
			}else if(unit=="天"){
				totalday = vmitem.getTenancy();
			}else if(unit=="小时"){//小时在申请的时候存储的只是日期，所以没有，以后再修改
				totalday = 1;
			}
			long myTime=(initDate.getTime()/1000)+60*60*24*totalday; 
			finalDate.setTime(myTime*1000); 
			String FinalDate = sdf.format(finalDate);
			String valid = vmitem.getInitDate().substring(0, 10)+"—"+FinalDate.substring(0, 10);
			myvmsDto.setValid(valid);
			int remainday = totalday-(int)day;
			if(remainday<0){remainday=0;}
			String remaintime = remainday+"天";
			myvmsDto.setRemaintime(remaintime);
			resultList.add(myvmsDto);
		}
		return resultList;
	}
	@Override
	public List<MyvmsDto> findAllFinalMyvms(PagingBean pb) throws DtoException, ParseException {
		List<MyvmsDto> resultList = new ArrayList<MyvmsDto>();
		List<VMItem> result = VMItem.findAllFinalMyvms(pb);
		for (VMItem vmitem : result) {
			MyvmsDto myvmsDto = DtoHelper.build(MyvmsDto.class, vmitem);
			String renttime = vmitem.getTenancy().toString()+vmitem.getUnit().getValue();
			myvmsDto.setRenttime(renttime);
			String config = vmitem.getCpu().toString()+"核/"+vmitem.getMem().toString()+"GB/"+vmitem.getDisk().toString()+"GB/"+vmitem.getBandwidth().toString()+"Mbps";
			myvmsDto.setConfig(config);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			Date initDate=new Date();
			Date curDate=new Date();
			Date finalDate=new Date();
			initDate = sdf.parse(vmitem.getInitDate());
			long day=(curDate.getTime()-initDate.getTime())/(24*60*60*1000);
			String unit = vmitem.getUnit().getValue();
			int totalday = 0;
			if(unit=="年"){//统一视为一年365天
				totalday = vmitem.getTenancy()*365;
			}else if(unit=="月"){//统一视为一个月31天
				totalday = vmitem.getTenancy()*31;
			}else if(unit=="天"){
				totalday = vmitem.getTenancy();
			}else if(unit=="小时"){//小时在申请的时候存储的只是日期，所以没有，以后再修改
				totalday = 1;
			}
			long myTime=(initDate.getTime()/1000)+60*60*24*totalday; 
			finalDate.setTime(myTime*1000); 
			String FinalDate = sdf.format(finalDate);
			String valid = vmitem.getInitDate().substring(0, 10)+"—"+FinalDate.substring(0, 10);
			myvmsDto.setValid(valid);
			int remainday = totalday-(int)day;
			if(remainday<0){remainday=0;}
			String remaintime = remainday+"天";
			myvmsDto.setRemaintime(remaintime);
			resultList.add(myvmsDto);
		}
		return resultList;
	}
	@Override
	public List<MyvmsDto> searchMyvms(Map<String, String> data) throws DtoException {
		List<MyvmsDto> resultList = new ArrayList<MyvmsDto>();
		VMItemState state = null;
		if(data.get("vmitemstate")!=null){
			state = VMItemState.getMatchByName(data.get("vmitemstate"));
		}
		RunState runstate = null;
		if(data.get("runstate")!=null){
			runstate = RunState.getMatchByName(data.get("runstate"));
		}
		Node node = null;
		if(data.get("nodeName")!=null){
			node = Node.findByNameCurrent(data.get("nodeName"));
		}
		try{
			List<VMItem> result = VMItem.findByParam(data.get("orderNumber"),node,data.get("vmname"),state,data.get("formdate"),data.get("todate"),runstate);
			for (VMItem vmitem : result) {
				MyvmsDto myvmsDto = DtoHelper.build(MyvmsDto.class, vmitem);
				String renttime = vmitem.getTenancy().toString()+vmitem.getUnit().getValue();
				myvmsDto.setRenttime(renttime);
				String config = vmitem.getCpu().toString()+"核/"+vmitem.getMem().toString()+"GB/"+vmitem.getDisk().toString()+"GB/"+vmitem.getBandwidth().toString()+"Mbps";
				myvmsDto.setConfig(config);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				Date initDate=new Date();
				Date curDate=new Date();
				Date finalDate=new Date();
				initDate = sdf.parse(vmitem.getInitDate());
				long day=(curDate.getTime()-initDate.getTime())/(24*60*60*1000);
				String unit = vmitem.getUnit().getValue();
				int totalday = 0;
				if(unit=="年"){//统一视为一年365天
					totalday = vmitem.getTenancy()*365;
				}else if(unit=="月"){//统一视为一个月31天
					totalday = vmitem.getTenancy()*31;
				}else if(unit=="天"){
					totalday = vmitem.getTenancy();
				}else if(unit=="小时"){//小时在申请的时候存储的只是日期，所以没有，以后再修改
					totalday = 1;
				}
				long myTime=(initDate.getTime()/1000)+60*60*24*totalday; 
				finalDate.setTime(myTime*1000); 
				String FinalDate = sdf.format(finalDate);
				String valid = vmitem.getInitDate().substring(0, 10)+"—"+FinalDate.substring(0, 10);
				myvmsDto.setValid(valid);
				int remainday = totalday-(int)day;
				if(remainday<0){remainday=0;}
				String remaintime = remainday+"天";
				myvmsDto.setRemaintime(remaintime);
				resultList.add(myvmsDto);
			}
			return resultList;
			
			}catch(Exception e){
				return resultList;
			
			}

	}
	@Override
	public List<MyvmsDto> searchFinalMyvms(Map<String, String> data) throws DtoException {
		List<MyvmsDto> resultList = new ArrayList<MyvmsDto>();
		Enterprise enterprise = null;
		if(data.get("enterprise")!=null){
			enterprise = Enterprise.findByName(data.get("enterprise"));
		}
		RunState runstate = null;
		if(data.get("runstate")!=null){
			runstate = RunState.getMatchByName(data.get("runstate"));
		}
		Node node = null;
		if(data.get("nodeName")!=null){
			node = Node.findByNameCurrent(data.get("nodeName"));
		}
		try{
			List<VMItem> result = VMItem.findByParam2(data.get("orderNumber"),node,data.get("vmname"),enterprise,data.get("formdate"),data.get("todate"),runstate);
			for (VMItem vmitem : result) {
				MyvmsDto myvmsDto = DtoHelper.build(MyvmsDto.class, vmitem);
				String renttime = vmitem.getTenancy().toString()+vmitem.getUnit().getValue();
				myvmsDto.setRenttime(renttime);
				String config = vmitem.getCpu().toString()+"核/"+vmitem.getMem().toString()+"GB/"+vmitem.getDisk().toString()+"GB/"+vmitem.getBandwidth().toString()+"Mbps";
				myvmsDto.setConfig(config);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				Date initDate=new Date();
				Date curDate=new Date();
				Date finalDate=new Date();
				initDate = sdf.parse(vmitem.getInitDate());
				long day=(curDate.getTime()-initDate.getTime())/(24*60*60*1000);
				String unit = vmitem.getUnit().getValue();
				int totalday = 0;
				if(unit=="年"){//统一视为一年365天
					totalday = vmitem.getTenancy()*365;
				}else if(unit=="月"){//统一视为一个月31天
					totalday = vmitem.getTenancy()*31;
				}else if(unit=="天"){
					totalday = vmitem.getTenancy();
				}else if(unit=="小时"){//小时在申请的时候存储的只是日期，所以没有，以后再修改
					totalday = 1;
				}
				long myTime=(initDate.getTime()/1000)+60*60*24*totalday; 
				finalDate.setTime(myTime*1000); 
				String FinalDate = sdf.format(finalDate);
				String valid = vmitem.getInitDate().substring(0, 10)+"—"+FinalDate.substring(0, 10);
				myvmsDto.setValid(valid);
				int remainday = totalday-(int)day;
				if(remainday<0){remainday=0;}
				String remaintime = remainday+"天";
				myvmsDto.setRemaintime(remaintime);
				resultList.add(myvmsDto);
			}
			return resultList;
			
			}catch(Exception e){
				return resultList;
			
			}

	}
}
