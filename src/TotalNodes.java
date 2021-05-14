/*
NAME: Seyed Ruzaik
UOW ID: W1761768
IIT ID: 2018439
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TotalNodes {
    protected List<String> list = new ArrayList<>();



    //read the text file and add the data to the list
    public void read(){

        String fileName = "test.txt";

        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                list.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }


    //get the total number of nodes
    public int getNumOfNodes(){
        read();
        int numOfNodes = 0;
        if (list.size() > 0 && !list.get(0).isEmpty()){
           numOfNodes = Integer.parseInt(list.get(0));

        }else {
            System.out.println();
         System.out.println("Total Number Of Nodes Is Not Present In The Text File!");
         System.out.println();
         System.exit(0);
        }
        return numOfNodes;
    }
}
