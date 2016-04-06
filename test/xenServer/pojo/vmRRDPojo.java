package xenServer.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.jfree.data.time.TimeSeries;

import com.sun.org.apache.bcel.internal.generic.RETURN;

/**
 * @date 2015年12月15日 下午4:34:15
 * @author yangengzhe
 */
public class vmRRDPojo {

    public String                    UUID;
    public ArrayList<Integer>        time;
    public HashMap<String, double[]> datas;

    public vmRRDPojo(){
    }

    public vmRRDPojo(String UUID, ArrayList<Integer> time, HashMap<String, double[]> datas){
        this.UUID = UUID;
        this.time = time;
        this.datas = datas;
    }

    /**
     * @return
     * @author ygz 下午5:08:47
     */
    public ArrayList<vmMetricsPojo> getMetrics() {
        if (time.size() < 1) return null;
        ArrayList<vmMetricsPojo> array = new ArrayList<vmMetricsPojo>();
        for (int i = 0; i < time.size(); i++) {
            vmMetricsPojo vm = new vmMetricsPojo(UUID, time.get(i));
            HashMap<String, Double> vmdata = new HashMap<String, Double>();
            for (Entry<String, double[]> entry : datas.entrySet()) {
                vmdata.put(entry.getKey(), entry.getValue()[i]);
            }
            vm.setDatas(vmdata);
            array.add(vm);
        }
        return array;
    }

    public int getSize() {
        return time.size();
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String uUID) {
        UUID = uUID;
    }

    public ArrayList<Integer> getTime() {
        return time;
    }

    public void setTime(ArrayList<Integer> time) {
        this.time = time;
    }

    public HashMap<String, double[]> getDatas() {
        return datas;
    }

    public void setDatas(HashMap<String, double[]> datas) {
        this.datas = datas;
    }

}
