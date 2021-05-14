/*
NAME: Seyed Ruzaik
UOW ID: W1761768
IIT ID: 2018439
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Parser {

    private static ArrayList<ArrayList<Integer>> conversionArrayList = new ArrayList<>();
    private static final ArrayList<Integer> node1 = new ArrayList<>();
    private static final ArrayList<Integer> node2 = new ArrayList<>();
    private static final ArrayList<Integer> capacity = new ArrayList<>();
    private static final ArrayList<Integer> check = new ArrayList<>();



    //run method which will run the above class
    public static void run(){
        TotalNodes totalNodes = new TotalNodes();
        int length = totalNodes.getNumOfNodes();


        try {
            checkGraph();
            getEdges(length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }

    //convert 2d arraylist to 2D array
    public static Integer[][] conversion()
    {
        Integer [][] array = new Integer[conversionArrayList.size()][];
        for (int i = 0; i < conversionArrayList.size(); i++) {
            ArrayList<Integer> row = conversionArrayList.get(i);
            array[i] = row.toArray(new Integer[row.size()]);
        }
//return the 2D array
        return array;
    }




//get the edges
    public static void getEdges(int length) throws FileNotFoundException {

        //using Scanner to read the text file
        Scanner matrix = new Scanner(new File("test.txt"));

        //2D Arraylist matrix array
        ArrayList<ArrayList<Integer>> matrixArray = new ArrayList<>();

        //skip the first line in the text file which contains number of nodes in the graph
        matrix.nextLine();
        int count = 0;
        while(matrix.hasNextLine()){
            String[] data = matrix.nextLine().split("\\s+");

            //integer arraylist in which the text file data will read and added to the list in order
            ArrayList<Integer> innerList = new ArrayList<>();
            for (int  i = 0 ; i < 3; i++) {
                if (count == 0){
                    //add the first number which is a node to the list
                    node1.add(Integer.parseInt(data[i]));

                } else if (count == 1){
                    //add the second number which is a node to the list
                    node2.add(Integer.parseInt(data[i]));

                }else if (count == 2){
                    //add the third number which is the capacity of both the previous nodes to the list
                    capacity.add(Integer.parseInt(data[i]));
                    count = -1;
                }

                //increment count
               count++;
            }


           //add the elements in the innerList to the @D arrayList
            matrixArray.add(innerList);


        }

        //call the method which will create an empty 2D arraylist
        createEmpty2DArrayList(length);


    }

    //create empty 2D arraylists
  public static void createEmpty2DArrayList(int length) throws FileNotFoundException {

        //using scanner to read the text file
      Scanner matrix = new Scanner(new File("test.txt"));

      ArrayList<ArrayList<Integer>> matrixArray = new ArrayList<>();

      //arrayL represents the total number of nodes
      int arrayL = length;

      matrix.nextLine();
      while (matrix.hasNextLine() && arrayL > 0) {
          String[] data = matrix.nextLine().split("\\s+");


          ArrayList<Integer> innerList = new ArrayList<>();
          for (int i = 0; i < length ; i++) {
//                System.out.println("count is "+count);

              //add 0 to all
              innerList.add(0);


          }

          //add the value to matrixArray
          matrixArray.add(innerList);
              arrayL--;

      }
      //now the 2D conversionArrayList will all have 0 values
      conversionArrayList = matrixArray;
      setGraph();
  }

  //set the values for the necessary index
    public static void insertValue(ArrayList<ArrayList<Integer>> list, int row, int column, int value){
        list.get(row).set(column, value);
    }
    
    
    //delete values
    public static void deleteValue(ArrayList<ArrayList<Integer>> list, int row, int value){
        list.get(row).remove(value);
        
    }

    //search values
    public static void searchValue(ArrayList<ArrayList<Integer>> list, int row, int value){
        list.get(row).indexOf(value);
        
    }
    
//get the 2d array to represent it in the main method
    public static void setGraph(){

        //call the set value method inside the forLoop
        for (int  i = 0 ; i < node1.size();i++){

            insertValue(conversionArrayList,node1.get(i),node2.get(i),capacity.get(i));


        }
        //finally convert the 2D arraylist to an 2D Array
        conversion();

    }

    //check if the graph is valid
    public static void checkGraph () throws FileNotFoundException {
        TotalNodes totalNodes2 = new TotalNodes();

        //Scanner to read the text file
        Scanner matrix = new Scanner(new File("test.txt"));

        //skip the first line in which it contains the total number of nodes
        matrix.nextLine();
        //set count to 0
        int count = 0;
        while(matrix.hasNextLine()){
            String[] data = matrix.nextLine().split("\\s+");

            for (int  i = 0 ; i < 3; i++) {
                if (count == 0){
                    //add all the nodes to the arraylist
                    check.add(Integer.parseInt(data[i]));

                } else if (count == 1){
                    //add all the nodes to the arraylist
                    check.add(Integer.parseInt(data[i]));

                }else if (count == 2){
                    count = -1;
                }

                //increment count
                count++;
            }



        }


        /* Sorting in (descending) order*/
        check.sort(Collections.reverseOrder());

        //the total number of nodes
        int totalNodes = totalNodes2.getNumOfNodes();

        /*
        if total number of nodes is equal to the highest valued node show an error message and exit the program
         */
        if (totalNodes == check.get(0)){
            System.out.println();
            System.out.println("Graph Error. Check Your Values In Your Text File!");
            System.out.println();
            System.exit(0);

        }
    }

}
