package xenServer;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;

import java.util.Map.Entry;

import com.xensource.xenapi.APIVersion;
import com.xensource.xenapi.Connection;
import com.xensource.xenapi.Console;
import com.xensource.xenapi.Host;
import com.xensource.xenapi.Session;
import com.xensource.xenapi.VM;
import com.xensource.xenapi.VM.Record;
import com.xensource.xenapi.VMGuestMetrics;
import com.xensource.xenapi.VMMetrics;

import sun.misc.BASE64Encoder;
import xenServer.pojo.hostRRDPojo;
import xenServer.pojo.vmMetricsPojo;
import xenServer.pojo.vmRRDPojo;
import xenServer.util.monitorUtil;

import com.ices.csp.vmorder.createvms.TargetServer;

/**
 * @date 2015年12月14日 下午3:03:03
 * @author yangengzhe
 */
public class monitorTest {

    public static final long         past_seconds = 60 * 60;
    // 服务器信息，主机名，用户名，用户密码
    public final TargetServer server       = new TargetServer("172.29.131.53", "root", "123456");

    /**
     * @param host 所在主机ip
     * @param uuid 查询uuid
     * @return
     * @author ygz 下午1:16:41
     */
    public String monitorVM(String host, String uuid) {
        try {
            // 连接并登陆
            Connection connection = new Connection(new URL("http://" + server.Hostname));
            Session.loginWithPassword(connection, server.Username, server.Password, APIVersion.latest().toString());
            URL url = new URL("http://" + host + "/rrd_updates?start="
                              + (System.currentTimeMillis() / 1000 - past_seconds));
            URLConnection urlConnection = url.openConnection();
            String encoding = new BASE64Encoder().encode((server.Username + ":" + server.Password).getBytes());
            urlConnection.setRequestProperty("Authorization", "Basic " + encoding);
            String rrdXportData = IOUtils.toString(urlConnection.getInputStream());
//            System.out.println(rrdXportData);
            hostRRDPojo host_rrd = monitorUtil.parse_RRD(rrdXportData);
            vmRRDPojo vm =host_rrd.getVMByUUID(uuid);
            ArrayList<vmMetricsPojo> metrics= vm.getMetrics();
            
            int i=1;
            for(vmMetricsPojo metric :metrics){
                
                System.out.println(i+": "+monitorUtil.timeFormat(metric.time)+": "+metric.datas.get("cpu0"));
                i++;
            }

//             //获取所有的虚拟机
//             Map<VM, VM.Record> vmmapall = VM.getAllRecords(connection);
//             Map<VM,VM.Record> vmmap=new HashMap<VM, VM.Record>();
//            
//             Set<Entry<VM, Record>> entries = vmmapall.entrySet();
//             for(Map.Entry entry: entries) {
//             //获取关键字
//             VM key = (VM)entry.getKey();
//             VM.Record value = (VM.Record)entry.getValue();
//             //System.out.println(value.powerState);
//             //找到所有的虚拟机
//             if(!value.isControlDomain && !value.isASnapshot &&
//             !value.isATemplate)
//             {
//             vmmap.put(key, value);
//             }
//             }
//             if(vmmap.size()>0){
//             Set<Entry<VM, Record>> entriesrun = vmmap.entrySet();
//             for(Map.Entry entry: entriesrun) {
//             VM key= (VM)entry.getKey();
//             VM.Record value=(VM.Record)entry.getValue();
//             //根据uuid查询vm
//             if(value.uuid.equals(uuid)){
//             VM vm=key;
//             System.out.println(vm.getNameLabel(connection));
//            
//            
//             URL url = new URL("http://172.29.131.95" + "/rrd_updates?start="
//             + (System.currentTimeMillis() / 1000 ));
//             URLConnection urlConnection = url.openConnection();
//             String encoding = new BASE64Encoder().encode(("root" + ":" +
//             "123456").getBytes());
//             urlConnection.setRequestProperty ("Authorization", "Basic " +
//             encoding);
//            
//             String rrdXportData =
//             IOUtils.toString(urlConnection.getInputStream());
//             System.out.println(rrdXportData);
            
            
            // vm.setOtherConfig(connection, put_map);
//             }
//             }
//             }
            return "suc";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "error";
        }
    }

    public static void main(String[] args) {
        monitorTest test = new monitorTest();
        String result = test.monitorVM("172.29.131.50", "9b0a5332-5fe3-8a69-292f-5be2a7281f8f");
        // String result = test.startVM("4cf84667-e4f6-d3e1-da64-f23646a25293");
        System.out.println(result);
    }
}
