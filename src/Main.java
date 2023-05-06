import java.util.Scanner;

public class Main {
    private static final boolean test = false;
    private static int[][] testInputs1 = {{1,2,3,-1,5,-1},{1,2,3,5,4,2},{1,2,3,2,4,3,2,1},{1,2,3,3,4,5,2,1},{1,2,3,4,5,4,3,2,1},{-5,2,5,3,2,1,-3},{1,2,3,4,1,5,1,3,6,3}};
    private static String[] testOutputs1 = {"ABC-AB","ABC*A-","ABC-A--B","ABC-ABC*","ABC*AB-CA","AB-C-A-","ABC*-B--CB"};
    public static void main(String[] args) {
        if(test){
            test(1,testInputs1,testOutputs1,7);
        }else{
            PageManager pm = new PageManager();
            pm.populateFrameList();
            pm.readPageRequests();
            pm.populatePageList();
            pm.run();
        }
    }
    public static void test(int id, int[][]testInputs,String[]testOutputs, int testnumber){
        //int testnumber = 6;
        //int[][] testInputs = {{1,2,3,5,4,2},{1,2,3,2,4,3,2,1},{1,2,3,3,4,5,2,1},{1,2,3,4,5,4,3,2,1},{-5,2,5,3,2,1,-3},{1,2,3,4,1,5,1,3,6,3}};
        //String[] testOutputs = {"ABC*A-","ABC-A--B","ABC-ABC*","ABC*AB-CA","AB-C-A-","ABC*-B--CB"};


        for(int i = 0;i<testnumber;i++){
            PageManager pm = new PageManager();
            pm.populateFrameList();
            pm.loadPageRequests(testInputs[i]);
            pm.populatePageList();
            pm.run();
            String result = pm.getOutput();
            try{
                assert result.equals(testOutputs[i]):"Test "+id+" for input: "+i+" failed. Expected: "+testOutputs[i]+" Actual: "+result;
                System.out.println("Test " +id +" for input: "+i+" passed."+"\n");
            }catch(AssertionError e){
                System.err.println(e.getMessage());
            }
        }

    }
}