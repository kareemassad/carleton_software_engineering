import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.HashMap;
import java.util.Map;

public class CpuPerThread {
    private int sampleTime = 20000;
    private ThreadMXBean threadMxBean = ManagementFactory.getThreadMXBean();
    private RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
    private OperatingSystemMXBean osMxBean = ManagementFactory
            .getOperatingSystemMXBean();
    private Map<Long, Long> threadInitialCPU = new HashMap<Long, Long>();
    private Map<Long, Float> threadCPUUsage = new HashMap<Long, Float>();
    private long initialUptime = runtimeMxBean.getUptime();

    public static void main(String[] args) {

        Thread ih1;
        ih1 = new Thread(new Host());
        ih1.start();
        new CpuPerThread().measure();
    }

    private void measure() {

        ThreadInfo[] threadInfos = threadMxBean.dumpAllThreads(false, false);
        for (ThreadInfo info : threadInfos) {
            threadInitialCPU.put(info.getThreadId(),
                    threadMxBean.getThreadCpuTime(info.getThreadId()));
        }

        try {
            Thread.sleep(sampleTime);
        } catch (InterruptedException e) {
        }

        long upTime = runtimeMxBean.getUptime();

        Map<Long, Long> threadCurrentCPU = new HashMap<Long, Long>();
        threadInfos = threadMxBean.dumpAllThreads(false, false);
        for (ThreadInfo info : threadInfos) {
            threadCurrentCPU.put(info.getThreadId(),
                    threadMxBean.getThreadCpuTime(info.getThreadId()));
        }

        // CPU over all processes
        int nrCPUs = osMxBean.getAvailableProcessors();
        // total CPU: CPU % can be more than 100% (devided over multiple cpus)
        // long nrCPUs = 1;
        // elapsedTime is in ms.
        long elapsedTime = (upTime - initialUptime);
        for (ThreadInfo info : threadInfos) {
            // elapsedCpu is in ns
            Long initialCPU = threadInitialCPU.get(info.getThreadId());
            if (initialCPU != null) {
                long elapsedCpu = threadCurrentCPU.get(info.getThreadId())
                        - initialCPU;
                float cpuUsage = elapsedCpu * 100 / (elapsedTime * 1000000F * nrCPUs);
                threadCPUUsage.put(info.getThreadId(), cpuUsage);
            }
        }

        // threadCPUUsage contains cpu % per thread
        System.out.println(threadCPUUsage);
        // You can use osMxBean.getThreadInfo(theadId) to get information on
        // every thread reported in threadCPUUsage and analyze the most CPU
        // intentive threads

    }
}