package Model;

/**
 * @author Alexis HOUPERT
 * This thread will call the method that calcul new value for pie chart
 */
public class ThreadUpdateDataPieChart extends Thread {
    public void run() {
        try {
            for (; ; ) {
                DataPieChart.calculData();
                Thread.sleep(120000);
            }
        } catch (InterruptedException e) {
            System.out.println("sleep interrupted");
        }
    }
}
