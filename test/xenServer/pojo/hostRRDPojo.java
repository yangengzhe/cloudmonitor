package xenServer.pojo;

import java.util.HashMap;

/**
 * @date 2015年12月15日 下午3:07:02
 * @author yangengzhe
 *
 */
public class hostRRDPojo {
    public int start;
    public int step;
    public int end;
    public int rows;
    public int columns;
    public HashMap<String,vmRRDPojo> legend;
    
    /**
     * @param uuid
     * @return
     * @author ygz 下午4:36:45
     */
    public vmRRDPojo getVMByUUID(String uuid)
    {
        vmRRDPojo vmrrd = null;
        if(uuid!=null)
            vmrrd = legend.get(uuid);
        return vmrrd;
    }
    public int getVMCount()
    {
        return legend.size();
    }
    public hostRRDPojo(){
        
    }
    public hostRRDPojo(int start,int step,int end,int rows,int columns){
        this.start=start;
        this.step=step;
        this.end=end;
        this.rows=rows;
        this.columns=columns;
    }
    
    public int getStart() {
        return start;
    }
    
    public void setStart(int start) {
        this.start = start;
    }
    
    public int getStep() {
        return step;
    }
    
    public void setStep(int step) {
        this.step = step;
    }
    
    public int getEnd() {
        return end;
    }
    
    public void setEnd(int end) {
        this.end = end;
    }
    
    public int getRows() {
        return rows;
    }
    
    public void setRows(int rows) {
        this.rows = rows;
    }
    
    public int getColumns() {
        return columns;
    }
    
    public void setColumns(int columns) {
        this.columns = columns;
    }
    
    public HashMap<String, vmRRDPojo> getLegend() {
        return legend;
    }
    
    public void setLegend(HashMap<String, vmRRDPojo> legend) {
        this.legend = legend;
    }
    
    
    
    

}
