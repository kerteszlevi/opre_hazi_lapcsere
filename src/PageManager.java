import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.abs;

public class PageManager {
    private boolean debug = false;

    private int pageFaults = 0;
    private String output_debug = "";
    private String output = "";
    private int[] pageRequests;
    private Fifo fifo;
    private Frame frameA = new Frame('A');
    private Frame frameB = new Frame('B');
    private Frame frameC = new Frame('C');
    private boolean actionTookPlace = false;
    private int frozenPageCount = 0;

    private List<Frame> frameList = new ArrayList<>();
    private List<Page> pageList = new ArrayList<>();

    public PageManager() {
        this.pageRequests = new int[0];
        this.fifo = new Fifo();
    }
    //lapkérések beolvasása stdin-ről
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
    //lapok feltöltése átadott referenciabemenettel
    public void loadPageRequests(int[] pageRequests){
        this.pageRequests = pageRequests;
    }

    //a bemeneti referenciatömböt írja ki stdout-ra
    public void printPageRequests() {
        for (int i = 0; i < this.pageRequests.length; i++) {
            System.out.print(this.pageRequests[i]+",");
        }
    }

    //már feltöltött laptömb kiírása
    public void printPageList(){
        for (int i = 0; i < this.pageList.size(); i++) {
            System.out.print(this.pageList.get(i).getNumber()+",");
        }
    }

    //laptömb felöltése a bemeneti tömb alapján
    public void populatePageList(){
        for (int i = 0; i < this.pageRequests.length; i++) {
            Page page = new Page(abs(this.pageRequests[i]));
            pageList.add(page);
        }
    }
    //kerettömb feltöltése 3 kerettel
    public void populateFrameList(){
        frameList.add(frameA);
        frameList.add(frameB);
        frameList.add(frameC);
    }

    //output visszaadása teszteléshez
    public String getOutput(){
        return this.output;
    }
    //az algoritmust futtató run függvény

    public void run(){
        //végigmegyünk a lapokon amik a lapkéréseknek felelnek meg
        for(Page page:pageList){
            actionTookPlace = false;
            //majd minden lapra végigmegyünk az elérhető frame-eken
            //megnézzük hogy a kért lap benne van-e már egy frame-ben.
            for(Frame frameX:frameList) {
                if (frameX.getPageContainer()!= null &&  page.getNumber() == frameX.getPageContainer().getNumber()) {
                    frameX.setReferenced(true);
                    if (frameX.getPageContainer().getLockedFor() != 0) {
                        frameX.getPageContainer().setLockedFor(0);
                    }
                    output_debug += "-,";
                    output += "-";
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
                        output_debug += String.valueOf(frameX.getLetter())+page.getNumber()+",";
                        output += String.valueOf(frameX.getLetter());
                        actionTookPlace = true;
                        break;
                    }
                }
            }
            if(!actionTookPlace){
                for(Frame frameX:frameList){
                    if(frameX.getPageContainer().getLockedFor()!=0){
                        frozenPageCount++;
                    }else{
                        //TODO
                    }
                    //nem tudjuk teljesíteni a kérést az összes lap foglalt.
                    if(frozenPageCount ==frameList.size()){
                        output_debug += "*"+",";
                        output += "*";
                        pageFaults++;
                        actionTookPlace = true;
                    }
                }
                frozenPageCount = 0;
            }
            while(!actionTookPlace){
                if(fifo.getFirst().getReferenced()){
                    fifo.getFirst().setReferenced(false);
                    fifo.addFrame(fifo.removeFirst());
                    //output_debug += fifo.getFirst().getLetter()+page.getNumber()+" ";
                    //pageFaults++;
                }else{
                    Frame firstNotLockedFrame = fifo.removeFrame(fifo.getFirstNotLocked());
                    output_debug += String.valueOf(firstNotLockedFrame.getLetter())+page.getNumber()+",";
                    output += String.valueOf(firstNotLockedFrame.getLetter());
                    firstNotLockedFrame.setPageContainer(page);
                    fifo.addFrame(firstNotLockedFrame);
                    pageFaults++;
                    actionTookPlace = true;
                }
            }
            fifo.reduceLockedForFramesInFifo();
            //System.out.println(debug?output_debug:"");
        }
        System.out.println(debug?output_debug:output);
        System.out.print(pageFaults);
    }
}
