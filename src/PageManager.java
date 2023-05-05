import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PageManager {

    private int pageFaults = 0;
    private String output_debug = "";
    private int[] pageRequests;
    private Fifo fifo;
    private Frame frameA = new Frame('A');
    private Frame frameB = new Frame('B');
    private Frame frameC = new Frame('C');
    private boolean actionTookPlace = false;
    private int frozenCount = 0;

    private List<Frame> frameList = new ArrayList<>();
    private List<Page> pageList = new ArrayList<>();

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

    //this is the printPageList function that prints the pageList.
    public void printPageList(){
        for (int i = 0; i < this.pageList.size(); i++) {
            System.out.print(this.pageList.get(i).getNumber()+",");
        }
    }

    //this it the populatePageList function that initializes the pageList with the numbers from the
    //pageRequests array.
    public void populatePageList(){
        for (int i = 0; i < this.pageRequests.length; i++) {
            Page page = new Page(this.pageRequests[i]);
            pageList.add(page);
        }
    }

    public void populateFrameList(){
        frameList.add(frameA);
        frameList.add(frameB);
        frameList.add(frameC);
    }

    //this is the run function that runs the page swapping second change algorithm.

    public void run(){
        //végigmegyünk a lapokon amik a lapkéréseknek felelnek meg
        for(Page page:pageList){
            actionTookPlace = false;
            //majd minden lapra végigmegyünk az elérhető frame-eken
            //megnézzük hogy a kért lap benne van-e már egy frame-ben.
            for(Frame frameX:frameList) {
                if (page.getNumber() == frameX.getPageContainer().getNumber()) {
                    frameX.setReferenced(true);
                    if (frameX.getPageContainer().getLockedFor() != 0) {
                        frameX.getPageContainer().setLockedFor(0);
                    }
                    output_debug += "-";
                    actionTookPlace = true;
                    break;
                }
            }
            //ha van üres lap és nem volt sehol akkor az üresbe tesszük. Laphiba van.
            if(!actionTookPlace){
                for(Frame frameX:frameList){
                    if(frameX.getPageContainer() == null){
                        frameX.setPageContainer(page);
                        fifo.addFrame(frameX);
                        pageFaults++;
                        output_debug += frameX.getLetter()+page.getNumber()+" ";
                        actionTookPlace = true;
                        break;
                    }
                }
            }
            if(!actionTookPlace){
                for(Frame frameX:frameList){
                    if(frameX.getPageContainer().getLockedFor()!=0){
                        frozenCount++;
                    }else{

                    }
                    if(frozenCount ==3)
                }
            }




        }
    }
}
