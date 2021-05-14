/*
NAME: Seyed Ruzaik
UOW ID: W1761768
IIT ID: 2018439
 */

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/*
Main Program
 */
public class Main {

    protected static ArrayList<String> allEdges = new ArrayList<>();
    protected static ArrayList<String> list = new ArrayList<>();
    public static void main(String[] args) {
        //instantiate FindMaxFlow Class
        FindMaxFlow getMaxFlow = new FindMaxFlow();
        //run the Parser class
        Parser.run();

        //set the graph from the input file
        Integer[][] graph = Parser.conversion();


        //call the Menu method
        menu(graph,getMaxFlow);

    }


    //menu method
    private static void menu(Integer[][] graph,FindMaxFlow MaxFlow) {

        System.out.println("-----------------------------------------");
        System.out.println("             Select An Option        ");
        System.out.println("-----------------------------------------");
        System.out.println();
        System.out.println("Enter 1 To Add An Edge");
        System.out.println("Enter 2 To Update The Capacity Of An Edge");
        System.out.println("Enter 3 To Delete An Edge");
        System.out.println("Enter 4 To Display The Max Flow And Quit!");
        System.out.println("Enter 5 To Display All Edges!");
        System.out.println();


        //create a scanner
        Scanner scanner = new Scanner(System.in);
        //get the choice from the user from the shown options
        System.out.print("Enter Your Choice: ");
        String input = scanner.nextLine();

        //set source
        int source = 0;
        //set the sink
        int target = graph.length-1;

        //using switch case
        switch (input) {

            //if user selects 1 it will call the addEdge method
            case "1":
                showEdges(graph);
                System.out.println();
                System.out.println("Enter The First Node: ");
                String node1 = scanner.nextLine();
                System.out.println("Enter The SecondNode: ");
                String node2 = scanner.nextLine();
                System.out.println("Enter The The Capacity: ");
                String weight = scanner.nextLine();

                addEdge(graph,Integer.parseInt(node1),Integer.parseInt(node2)
                        ,Integer.parseInt(weight));
                //save method to save the graph to text file
                save(graph);
                //call the menu method again
                menu(graph,MaxFlow);
                break;

            //if user selects 2 it will call the updateCapacity method
            case "2":
                showEdges(graph);
                System.out.println();
                System.out.println("Enter The First Node: ");
                String node_1 = scanner.nextLine();
                System.out.println("Enter The SecondNode: ");
                String node_2 = scanner.nextLine();
                System.out.println("Enter The The Capacity: ");
                String weight_ = scanner.nextLine();
                updateCapacity(graph,Integer.parseInt(node_1),Integer.parseInt(node_2),
                        Integer.parseInt(weight_));
                //save method to save the graph to text file
                save(graph);
                //call the menu method again
                menu(graph,MaxFlow);
                break;

            //if user selects 3 it will call the deleteEdge method
            case "3":
                showEdges(graph);
                System.out.println();
                System.out.println("Enter The First Node: ");
                String firstNode = scanner.nextLine();
                System.out.println("Enter The SecondNode: ");
                String secondNode = scanner.nextLine();
                deleteEdge(graph,Integer.parseInt(firstNode),Integer.parseInt(secondNode));
                //save method to save the graph to text file
                save(graph);
                //call the menu method again
                menu(graph,MaxFlow);
                break;

            //if user selects 4 it will display the max flow
            case "4":

                 //create an object of StopWatch class
                Stopwatch stopwatch = new Stopwatch();
                System.out.println();
                System.out.println("============================");
                System.out.println("           MAX-FLOW");
                System.out.println("============================");
                System.out.println();
                //Output the max flow for the given graph
                System.out.println("\nMaximum flow possible is: " + MaxFlow.maxFlow(graph, source, target));

                //Number of nodes
                System.out.println("Total Number Of Nodes : " + graph.length);

                //time taken to run the algorithm
                System.out.println("Elapsed Time: " + stopwatch.elapsedTime() + "s");
                //save method to save the graph to text file
                save(graph);
                //exit the program
                System.exit(0);
                break;

                //display the all edges
            case "5":
                showEdges(graph);
                //call the menu method again
                menu(graph,MaxFlow);
                break;

                //if an invalid option is given it will show an error message
            default:
                System.out.println();
                System.out.println("Invalid Choice!");
                System.out.println();
                //call the menu method again
                menu(graph,MaxFlow);
                break;


        }
    }

    //adding an edge to the graph method
    private static void addEdge(Integer [][] graph,Integer node1,Integer node2,Integer capacity){

        String check = "FirstNode (" + node1 + ") to SecondNode (" + node2+")";
       /*
       checking if capacity is 0 or not if it's 0 it wont add a new edge because if there is no
       capacity then there cannot be an edge. also checking for the value of the node so that
       user cant add a new node to the graph.
        */
        if (capacity != 0 && node1 < graph.length && node2 < graph.length && !allEdges.contains(check)
                && !node1.equals(node2)){

            graph[node1] [node2] = capacity;
            System.out.println();
            System.out.println("Successfully added!");

        }else {

            System.out.println();
            System.out.println("Error check the capacity or check your nodes!");
        }
        System.out.println();

    }

    //updating the weights for an edge
    private static void updateCapacity (Integer [][] graph,Integer node1,Integer node2,Integer capacity){

        String check = "FirstNode (" + node1 + ") to SecondNode (" + node2+")";
        /*
        checking for the value of the node so that
       user cant add a new node to the graph.
         */
        if (node1 < graph.length && node2 < graph.length && allEdges.contains(check)  && !node1.equals(node2)) {
            graph[node1][node2] = capacity;
            System.out.println();
            System.out.println("Successfully updated!");
        }else{
            System.out.println();
            System.out.println("Error check your nodes!");

        }
        System.out.println();

    }

    //delete an edge from the graph method
    private static void deleteEdge(Integer [][] graph,Integer node1 , Integer node2){

        String check = "FirstNode (" + node1 + ") to SecondNode (" + node2+")";
        /*
        checking for the value of the node so that
       user cant add a new node to the graph.
         */
        if (node1 < graph.length && node2 < graph.length && allEdges.contains(check)  && !node1.equals(node2)) {
            graph[node1][node2] = 0;
            System.out.println();
            System.out.println("Successfully deleted!");
        }else{
            System.out.println();
            System.out.println("Error check your nodes!");

        }
        System.out.println();

    }

    //show all edges in the graph
    private static void showEdges(Integer [][] graph){
        allEdges.clear();
        System.out.println();
        int count = 1;
        for (int i = 0 ; i < graph.length;i++){

            for (int j = 0 ; j < graph.length;j++){

                if (graph[i][j] != 0) {
                    System.out.println("Edge " + (count) + " = FirstNode (" + i + ") to SecondNode (" + j+") => "
                            +graph[i][j]);
                    allEdges.add("FirstNode (" + i + ") to SecondNode (" + j+")");
                    count++;
                }
            }
        }
        System.out.println();

    }

    //save method to write the updated graph to text file
    private static void save(Integer[][] graph){

        list.clear();
        for (int i = 0 ; i < graph.length;i++){

            for (int j = 0 ; j < graph.length;j++){

                if (graph[i][j] != 0) {
                    list.add(i + " " + j + " " + graph[i][j]);
                }
            }
        }
        TotalNodes totalNodes = new TotalNodes();
        int num = totalNodes.getNumOfNodes();

        try {



        FileWriter writer = new FileWriter("test.txt");
        writer.write(num + System.lineSeparator());
        for (String arr: list){
            writer.write(arr +System.lineSeparator());
        }
        writer.close();

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

}
/*
*References
* GeeksForGeeks - https://www.geeksforgeeks.org/ford-fulkerson-algorithm-for-maximum-flow-problem/.
* Wikipedia - https://en.wikipedia.org/wiki/Edmonds%E2%80%93Karp_algorithm.
* CP - Algorithms - https://cp-algorithms.com/graph/edmonds_karp.html#:~:text=Edmonds%2DKarp%20algorithm%20is%2
0just,independently%20of%20the%20maximal%20flow.
* IQ - OpenGeniusOrg - https://iq.opengenus.org/edmonds-karp-algorithm-for-maximum-flow/#:~:
* text=Edmonds%E2%80%93Karp%20
* algorithm%20is%20an,case%20of%20Ford%2DFulkerson%20algorithm.
* Youtube - https://www.youtube.com/watch?v=GiN3jRdgxU4
* Oracle Java - https://docs.oracle.com/javase/tutorial/java/data/characters.html
 */