package com.ices.csp.vmorder.createvms;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.xensource.xenapi.APIVersion;
import com.xensource.xenapi.Connection;
import com.xensource.xenapi.Session;
import com.xensource.xenapi.VM;
import com.xensource.xenapi.VM.Record;

import com.ices.csp.vmorder.createvms.TargetServer;

/**
 * 2015.10.27 16:14pm
 * @author ZZY
 * 根据虚拟机的唯一标识uuid来关闭虚拟机，如果成功返回1，异常返回-1，已经处于关机返回0，虚拟机不存在返回2
 */
public class ShutdownVMByWake {
	public  int shutdownVM(String uuid){
		// TODO Auto-generated method stub
		//连接服务器信息，主机名，用户名，用户密码
		String[] args = new String[3];
		args[0] = "172.29.131.53";
		args[1] = "root";
		args[2] = "123456";
		//存储服务器信息
    	 TargetServer server = new TargetServer(args[0], args[1], args[2]);
         try {
        	 //连接并登陆
        	 Connection connection =new Connection(new URL("http://" + server.Hostname));
			Session.loginWithPassword(connection, server.Username, server.Password, APIVersion.latest().toString());
	        //获取所有的虚拟机
			Map<VM, VM.Record> vmmapall = VM.getAllRecords(connection);
            Map<VM,VM.Record> vmmap=new HashMap<VM, VM.Record>();
            
            Set<Entry<VM, Record>> entries = vmmapall.entrySet();
            for(Map.Entry entry: entries) {
            	//获取关键字
                VM key = (VM)entry.getKey();
                VM.Record value = (VM.Record)entry.getValue();
                //System.out.println(value.powerState);
                //找到所有的虚拟机
                if(!value.isControlDomain && !value.isASnapshot && !value.isATemplate)
                {
                	vmmap.put(key, value);
                }
            }
            if(vmmap.size()>0){
            	Set<Entry<VM, Record>> entriesrun = vmmap.entrySet();
                for(Map.Entry entry: entriesrun) {
                	VM key= (VM)entry.getKey();
                	VM.Record  value=(VM.Record)entry.getValue();
                	//根据uuid查询vm
                	if(value.uuid.equals(uuid)){
                		 VM vm=key;
                		 System.out.println(vm.getNameLabel(connection));
                		 System.out.println(value.powerState.toString());
                		 //根据vm的状态来判断是否启用
                         if(value.powerState.toString().equals("Halted"))
                         {
                         	System.out.println("虚拟机已经停止！");
                         	return 0;
                         }else
                         {
                        	 	vm.shutdown(connection);
                         		System.out.println("虚拟机停止完毕！");
                         		return 1;
                         }
                	}
                }
            }
            return 2;
         } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		} 
	}
	public static void main(String[] args) {
		ShutdownVMByWake test = new ShutdownVMByWake();
		int result = test.shutdownVM("ba425989-bc10-51f5-c835-7a64291453ca");
		System.out.println(result);
	}
}
