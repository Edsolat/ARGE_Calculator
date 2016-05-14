package m2dl.arge.tp1;


import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

import javax.management.MBeanServerConnection;

public class Calculator {
	public int div(int n) {
		int number = (int) Math.pow((double)2, (double)n) - 1;
		int res = 0;
		
		for (int i = 2 ; i <= number ; i++) {
			if (number % i == 0 )
				res++;
		}
		
    	return res;
    }
	
	public int getProcessCpuLoad() throws Exception {
		MBeanServerConnection mbsc = ManagementFactory.getPlatformMBeanServer();

		OperatingSystemMXBean osMBean = ManagementFactory.newPlatformMXBeanProxy(
		mbsc, ManagementFactory.OPERATING_SYSTEM_MXBEAN_NAME, OperatingSystemMXBean.class);

		
		double max = osMBean.getAvailableProcessors();
		
		return (int) (osMBean.getSystemLoadAverage() /max * 100);
	}
}
