package xenServer;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.rrd4j.ConsolFun;
import org.rrd4j.DsType;
import org.rrd4j.core.RrdDb;
import org.rrd4j.core.RrdDef;
import org.rrd4j.core.Sample;
import org.rrd4j.graph.RrdGraph;
import org.rrd4j.graph.RrdGraphDef;

import com.google.common.collect.Multiset.Entry;

import xenServer.pojo.vmMetricsPojo;
import xenServer.pojo.vmRRDPojo;

public class rrd {

    static final Random RANDOM = new Random();

    static final int IMG_WIDTH  = 500;
    static final int IMG_HEIGHT = 300;

    public static void fun(ArrayList<vmMetricsPojo> vmMetrics, long start, long end, int step) throws IOException {

        long startMillis = System.currentTimeMillis();
        
        String rrdPath = "/Users/yangengzhe/Desktop/a.rrd"; // 文件路径
        String imgPath = "/Users/yangengzhe/Desktop/a.png";
        String factory = "FILE"; // 设置文件的保存方式 1.FILE 文件形式 2.SAFE 线程安全 3.NIO 缓冲区 4.MEMORY 内存
        RrdDb.setDefaultFactory(factory);
        //开始创建数据库
        System.out.println("== Creating RRD file " + rrdPath);
        RrdDef rrdDef = new RrdDef(rrdPath, start - 1, step); // 新建一个RRD定义对象,给定路径,开始时间以及时间间隔
        rrdDef.setVersion(2); // 文件版本

        for (java.util.Map.Entry<String, Double> entry : vmMetrics.get(0).getDatas().entrySet()) {
            rrdDef.addDatasource(entry.getKey(), DsType.GAUGE, 1000, 0, Double.NaN); // 单一数据源添加到RRD定义通过指定它的数据源名称、源类型
                                                                                     // DsType包含4个参数 ABSOLUTE COUNTER计数器
                                                                                     // DERIVE GAUGE直径、心跳、最小和最大的值。
        }

        // rrdDef.addDatasource("memory", DsType.DERIVE, 600, 0, 2048);
        rrdDef.addArchive(ConsolFun.AVERAGE, 0.5, 1, 80);
        rrdDef.addArchive(ConsolFun.MAX, 0.5, 1, 80);
        // rrdDef.addArchive(ConsolFun.MIN, 0.5, 1, 80);
        RrdDb rrdDb = new RrdDb(rrdDef); // 建立好模型

        // 获得数据源
        int data_count = vmMetrics.size();
        Sample sample = rrdDb.createSample();
        while (data_count > 0) {
            vmMetricsPojo vmMertic = vmMetrics.get(data_count - 1);
            sample.setTime(vmMertic.getTime());
            for (java.util.Map.Entry<String, Double> entry : vmMertic.getDatas().entrySet()) {
                sample.setValue(entry.getKey(), entry.getValue());
            }
            sample.update();
            data_count--;
        }

        rrdDb.close();

        // create graph
        RrdGraphDef gDef = new RrdGraphDef();
        gDef.setWidth(IMG_WIDTH);
        gDef.setHeight(IMG_HEIGHT);
        gDef.setFilename(imgPath);
        gDef.setStartTime(start);
        gDef.setEndTime(end);
        // gDef.setTitle("Temperatures in May-June 2010");
        // gDef.setVerticalLabel("temperature");

        gDef.datasource("cpu0", rrdPath, "cpu0", ConsolFun.MAX); // name:数据源名称 rrdPath:RRD文件路径 dsName：数据源名称在指定的RRD文件
                                                                 // sonsolFun:整合功能(平均、最小,最大,最后)
        // gDef.datasource("cpu0", rrdPath, "cpu0", ConsolFun.MAX);
        // gDef.datasource("cpu0", rrdPath, "cpu0", ConsolFun.MIN);
        // gDef.setStep(12*100000L);
        // line 和 area之间的区别是直线和区域描述
        gDef.line("cpu0", Color.BLUE, "cpu0"); // name:数据源名称 Color: 颜色 legend：注释文本
        // gDef.area("diff", Color.YELLOW, "difference"); //srcName：虚拟源名称 color：颜色 legend：注释文本

        // gDef.comment("\\r"); //上打印图形

        // gDef.gprint("sun", ConsolFun.MAX, "maxSun = %.3f%s"); //这个方法等同于 print(String, ConsolFun, String),
        // 但结果是印在图形本身,下面的图区域
        // gDef.gprint("sun", ConsolFun.AVERAGE, "avgSun = %.3f%S\\c"); //srcName:虚拟源名称 consolFun整合功能应用到源代码
        // format:格式字符串(如“平均= % 10.3 f % s”)
        // gDef.gprint("shade", ConsolFun.MAX, "maxShade = %.3f%S");
        // gDef.gprint("shade", ConsolFun.AVERAGE, "avgShade = %.3f%S\\c");
        gDef.setPoolUsed(false);
        gDef.setImageInfo("<img src='%s' width='%d' height = '%d'>"); // 创建额外的图像信息
        gDef.setImageFormat("png"); // 设置图像格式 imageFormat "PNG", "GIF" or "JPG".
        // create graph finally
        RrdGraph graph = new RrdGraph(gDef);

        System.out.println(graph.getRrdGraphInfo().dump()); // 该类，可以采用Rrd4j类实际上创建图表

        System.out.println("== Demo completed in " + ((System.currentTimeMillis() - startMillis) / 1000.0) + " sec");

    }

}
