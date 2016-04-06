package xenServer.pojo;

import java.util.HashMap;

/**
 * @date 2015年12月15日 下午5:15:28
 * @author yangengzhe
 */
public class vmMetricsPojo {

    public String                  UUID;
    public int                     time;
    public HashMap<String, Double> datas;

    public vmMetricsPojo(){
    }

    public vmMetricsPojo(String uuid, int time){
        this.UUID = uuid;
        this.time = time;
    }
    
    public int getSize(){
        return datas.size();
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String uUID) {
        UUID = uUID;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public HashMap<String, Double> getDatas() {
        return datas;
    }

    public void setDatas(HashMap<String, Double> datas) {
        this.datas = datas;
    }
}
