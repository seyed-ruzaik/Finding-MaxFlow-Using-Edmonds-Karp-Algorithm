import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


/*
NAME: Seyed Ruzaik
UOW ID: W1761768
IIT ID: 2018439
 */

public class FindMaxFlow {

   protected ArrayList<Integer> bottleNeckCap = new ArrayList<>();

   public int maxFlow(Integer[][] Graph, int source, int target){

       //declare and initialize residualCapacity as total available Graph initially.
       int[][] residualCapacity = new int[Graph.length][Graph[0].length];
       for (int i = 0; i < Graph.length; i++) {
           for (int j = 0; j < Graph[0].length; j++) {
               residualCapacity[i][j] = Graph[i][j];
           }
       }

       //this is parent map for storing BFS parent
       Map<Integer,Integer> parent = new HashMap<>();

       //stores all the augmented paths
       List<List<Integer>> augmentedPaths = new ArrayList<>();

       //max flow we can get in this network
       int maxFlow = 0;

       //see if augmented path can be found from source to target.
       while(BFS(residualCapacity, parent, source, target)){
           List<Integer> augmentedPath = new ArrayList<>();
           int flow = Integer.MAX_VALUE;
           //find minimum residualCapacity in augmented path
           //also add vertices to augmented path list
           int v = target;
           while(v != source){
               augmentedPath.add(v);
               int u = parent.get(v);
               if (flow > residualCapacity[u][v]) {
                   flow = residualCapacity[u][v];
               }
               v = u;
           }
           augmentedPath.add(source);
           Collections.reverse(augmentedPath);
           augmentedPaths.add(augmentedPath);

           //add min Graph to max flow
           maxFlow += flow;
           //add flow to the the bottleNeck ArrayList
           bottleNeckCap.add(flow);

           //decrease residualCapacity by min Graph from u to v in augmented path
           // and increase residualCapacity by min Graph from v to u
           v = target;
           while(v != source){
               int u = parent.get(v);
               residualCapacity[u][v] -= flow;
               residualCapacity[v][u] += flow;
               v = u;
           }
       }
       printAugmentedPaths(augmentedPaths);
       return maxFlow;
   }

   /*
    * Prints all the augmented path which contribute to max flow
    */
   private void printAugmentedPaths(List<List<Integer>> augmentedPaths) {
       AtomicInteger count = new AtomicInteger();
       System.out.println("_____________________________");
       System.out.println("       Possible paths   ");
       System.out.println("_____________________________");
       System.out.println();
       augmentedPaths.forEach(path -> {
           int x = count.intValue();
           path.forEach(i -> System.out.print(i + " => "));
           System.out.println("\b\b\b = { "+bottleNeckCap.get(x)+" } ");
           System.out.println();
           count.getAndIncrement();
       });
       System.out.println();
   }

   /*
    * Breadth first search to find augmented path
    */
   private boolean BFS(int[][] residualCapacity, Map<Integer,Integer> parent,
                       int source, int target){
       Set<Integer> visited = new HashSet<>();
       Queue<Integer> queue = new LinkedList<>();
       queue.add(source);
       visited.add(source);
       boolean foundAugmentedPath = false;
       //see if we can find augmented path from source to target
       while(!queue.isEmpty()){
           int u = queue.poll();
           for(int v = 0; v < residualCapacity.length; v++){
               //explore the vertex only if it is not visited and its residualCapacity is
               //greater than 0
               if(!visited.contains(v) &&  residualCapacity[u][v] > 0){
                   //add in parent map saying v got explored by u
                   parent.put(v, u);
                   //add v to visited
                   visited.add(v);
                   //add v to queue for BFS
                   queue.add(v);
                   //if target is found then augmented path is found
                   if ( v == target) {
                       foundAugmentedPath = true;
                       break;
                   }
               }
           }
       }
       //returns if augmented path is found from source to target or not
       return foundAugmentedPath;
   }


}