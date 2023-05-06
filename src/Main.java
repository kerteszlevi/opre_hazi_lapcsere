import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PageManager pm = new PageManager();
        pm.populateFrameList();
        pm.readPageRequests();
        //pm.printPageRequests();
        pm.populatePageList();
        //pm.printPageList();
        pm.run();
    }
}