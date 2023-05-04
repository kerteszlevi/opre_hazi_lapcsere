import java.util.Scanner;

public class PageManager {


    private int[] pageRequests;
    private Fifo fifo;
    private Frame frameA = new Frame('A');
    private Frame frameB = new Frame('B');
    private Frame frameC = new Frame('C');

    //This is the constructor of the PageManager class.
    public PageManager() {
        this.pageRequests = new int[0];
    }

    //this the readPageRequests function that takes input like this: 1,2,3,-1,5,-1
    //and stores it in the pageRequests array. It ignores new lines and empty lines and reads from the
    //standard input until EOF.
    public void readPageRequests() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (scanner.hasNextLine()) {
            input += scanner.nextLine();
        }
        String[] inputArray = input.split(",");
        int[] pageRequests = new int[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            pageRequests[i] = Integer.parseInt(inputArray[i]);
        }
        this.pageRequests = pageRequests;
    }

    //this is the printPageRequests function that prints the pageRequests array.
    public void printPageRequests() {
        for (int i = 0; i < this.pageRequests.length; i++) {
            System.out.print(this.pageRequests[i]+",");
        }
    }



}
