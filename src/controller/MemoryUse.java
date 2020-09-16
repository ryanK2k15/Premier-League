package controller;

import java.text.NumberFormat;

/**
 * This class shows the current memory usage of the application.
 * 
 * @author Ryan Kelleher
 * @version 1.0
 * @since 07/05/2020
 * 
 *
 */
public class MemoryUse {
	
	public StringBuilder showMemoryUse() {
		Runtime runtime = Runtime.getRuntime();
		NumberFormat format = NumberFormat.getInstance();
		StringBuilder sb = new StringBuilder();
		long totalMemory = runtime.totalMemory();
		long freeMemory = runtime.freeMemory();
		sb.append("Total Memory: " + format.format(totalMemory / 1024) + " KB" + "\n");
		sb.append("Free Memory: " + format.format(freeMemory / 1024) + " KB" + "\n");
		sb.append("Memory Used: " + format.format((totalMemory - freeMemory) / 1024) + " KB" + "\n");
		return sb;

	}

}
