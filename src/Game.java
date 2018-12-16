
import java.util.*;
import java.util.stream.Collectors;

public class Game {

    public static void main(String[] args) {

        //Creating the frontier
        ArrayList<Node> frontier = new ArrayList<Node>();
        //Creating the initial state of the board and assigning into initial node
        Board board = new Board();
        Node initialState = new Node();
        fillBoard(board.getBoard());
        initialState.setBoard(board);
        initialState.setDepthLevel(0);

        //Calculating the elapsed time
        long startTime = System.currentTimeMillis();
        long elapsedTime = 0L;
        //Holds the time that optimum solution is found
        long optimumTime = 0L;

        //removed holds the removed node from the frontier
        Node removed = null;
        //childNodes holds the possible moves specified board
        ArrayList<Node> childNodes = new ArrayList<Node>();
        Board currentBoard;

        //Counts the number of visited nodes
        long nodeCounter = 0;
        //Holds the suboptimum state
        Node subOptimum = initialState;
        //Holds the solution is optimum or not
        boolean isOptimum = false;
        //Algorithm holds the entered choice and time holds the entered execution time
        int algorithm = 0;
        int time = 0;

        while (true) {
            //Informing the user to enter the input
            System.out.println("Please enter the algorithm number that you want to use:" + "\n" +
                    "1:BFS" + "\n" + "2:DFS" + "\n" + "3:IDS" + "\n" +
                    "4:DFS with Random Selection" + "\n" + "5:DFS with Node Selection Heuristic" +
                    "\n" + "6:Exit");

            Scanner sc = new Scanner(System.in);
            algorithm = sc.nextInt();

            if (algorithm > 0 && algorithm < 7) {
                if(algorithm != 6){
                    System.out.println("Please enter the time in minutes: ");
                    time = sc.nextInt();
                }

                frontier.add(initialState);

                switch (algorithm) {
                    case (1): //BFS algorithm
                        //This algorithm takes the node from beginning of the frontier and adds the children to the end of the frontier
                        //The algorithm runs if the time is not over or frontier is not empty
                        while (elapsedTime < time * 60 * 1000 && !frontier.isEmpty()) {
                            //Getting the node from 0 index
                            removed = frontier.get(0);
                            //Increasing the number of visited nodes
                            nodeCounter++;
                            //Getting the board of the node
                            currentBoard = removed.getBoard();
                            //Removing from the frontier
                            frontier.remove(0);
                            //If it is not the optimum solution, assign the node to the sub optimum,
                            // Finding the children and add them to the frontier
                            if (!(checkPawn(currentBoard) == true)) {
                                if (subOptimum.getDepthLevel() < removed.getDepthLevel())
                                    subOptimum = removed;
                                childNodes = addChildren(currentBoard, removed);
                                frontier.addAll(childNodes);
                                //If it is optimum solution, find the time that optimum solution is found
                            } else {
                                subOptimum = removed;
                                isOptimum = true;
                                optimumTime = System.currentTimeMillis() - startTime;
                                break;
                            }
                            //Calculating the elapsed time
                            elapsedTime = System.currentTimeMillis() - startTime;
                        }
                        break;
                    case (2): //DFS algorithm
                        //This algorithm takes the node from beginning of the frontier
                        // Adding the children to the beginning of the frontier
                        //The algorithm runs if the time is not over or frontier is not empty
                        while (elapsedTime < time * 60 * 1000 && !frontier.isEmpty()) {
                            //Getting the node from 0 index
                            removed = frontier.get(0);
                            //Increasing the number of visited nodes
                            nodeCounter++;
                            //Getting the board of the node
                            currentBoard = removed.getBoard();
                            //Removing from the frontier
                            frontier.remove(0);
                            //If it is not the optimum solution, assign the node to the sub optimum,
                            // Finding the children and add them to the frontier
                            if (!(checkPawn(currentBoard) == true)) {
                                if (subOptimum.getDepthLevel() < removed.getDepthLevel()) {
                                    subOptimum = removed;
                                }
                                childNodes = addChildren(currentBoard, removed);
                                frontier.addAll(0, childNodes);
                                //If it is optimum solution, find the time that optimum solution is found
                            } else {
                                subOptimum = removed;
                                isOptimum = true;
                                optimumTime = System.currentTimeMillis() - startTime;
                                break;
                            }
                            //Calculating the elapsed time
                            elapsedTime = System.currentTimeMillis() - startTime;
                        }
                        break;
                    case (3): //IDS algorithm
                        //Holding the limit of the iteration
                        int deepening = 0;
                        //This algorithm takes the node from beginning of the frontier
                        //Adding the children to the beginning of the frontier
                        //The algorithm runs if the time is not over or frontier is not empty
                        while (elapsedTime < time * 60 * 1000 && !frontier.isEmpty()) {
                            //Getting the node from 0 index
                            removed = frontier.get(0);
                            //Increasing the number of visited nodes
                            nodeCounter++;
                            //Getting the board of the node
                            currentBoard = removed.getBoard();
                            //Removing from the frontier
                            frontier.remove(0);
                            //If it is not the optimum solution, assign the node to the sub optimum,
                            // Finding the children and add them to the frontier
                            if (!(checkPawn(currentBoard) == true)) {
                                if (subOptimum.getDepthLevel() < removed.getDepthLevel())
                                    subOptimum = removed;
                                if (removed.getDepthLevel() != deepening) {
                                    childNodes = addChildren(currentBoard, removed);
                                    frontier.addAll(0, childNodes);
                                    //If the frontier is empty and the deepining doesn't reach to 31,
                                    //Increasing the deepining value and add the initial state to the frontier
                                } else if (frontier.isEmpty() && deepening < 31) {
                                    deepening++;
                                    frontier.add(initialState);
                                }
                                //If it is optimum solution, find the time that optimum solution is found
                            } else {
                                subOptimum = removed;
                                isOptimum = true;
                                optimumTime = System.currentTimeMillis() - startTime;
                                break;
                            }
                            //Calculating the elapsed time
                            elapsedTime = System.currentTimeMillis() - startTime;
                        }
                        break;
                    case (4)://DFS algorithm with random selection
                        //This algorithm takes the node from beginning of the frontier
                        //Shuffling the children order
                        //Adding the children to the beginning of the frontier
                        //The algorithm runs if the time is not over or frontier is not empty
                        while (elapsedTime < time * 60 * 1000 && !frontier.isEmpty()) {
                            //Getting the node from 0 index
                            removed = frontier.get(0);
                            //Increasing the number of visited nodes
                            nodeCounter++;
                            //Getting the board of the node
                            currentBoard = removed.getBoard();
                            //Removing from the frontier
                            frontier.remove(0);
                            //If it is not the optimum solution, assign the node to the sub optimum,
                            // Finding the children, shuffle and add them to the frontier
                            if (!(checkPawn(currentBoard) == true)) {
                                if (subOptimum.getDepthLevel() < removed.getDepthLevel())
                                    subOptimum = removed;
                                childNodes = addChildren(currentBoard, removed);
                                Collections.shuffle(childNodes);
                                frontier.addAll(0, childNodes);
                                //If it is optimum solution, find the time that optimum solution is found
                            } else {
                                subOptimum = removed;
                                isOptimum = true;
                                optimumTime = System.currentTimeMillis() - startTime;
                                break;
                            }
                            //Calculating the elapsed time
                            elapsedTime = System.currentTimeMillis() - startTime;
                        }
                        break;
                    case (5): //DFS algorithm with Node Selection Heuristic
                        while (elapsedTime < time * 60 * 1000 && !frontier.isEmpty()) {
                            //Getting the node from 0 index
                            removed = frontier.get(0);
                            //Increasing the number of visited nodes
                            nodeCounter++;
                            //Getting the board of the node
                            currentBoard = removed.getBoard();
                            //Removing from the frontier
                            frontier.remove(0);
                            //If it is not the optimum solution, assign the node to the sub optimum,
                            // Find the children, sort them according to the heuristic
                            // Add them to the frontier
                            if (!(checkPawn(currentBoard) == true)) {
                                if (subOptimum.getDepthLevel() < removed.getDepthLevel()) {
                                    subOptimum = removed;
                                }
                                childNodes = addChildren(currentBoard, removed);
                                childNodes = childNodes.stream().sorted(Comparator.comparing(Game::heuristic).reversed()).collect(Collectors.toCollection(ArrayList::new));
                                frontier.addAll(0, childNodes);
                                //If it is optimum solution, find the time that optimum solution is found
                            } else {
                                subOptimum = removed;
                                isOptimum = true;
                                optimumTime = System.currentTimeMillis() - startTime;
                                break;
                            }
                            //Calculate the elapsed time
                            elapsedTime = System.currentTimeMillis() - startTime;
                        }
                        break;
                    case (6)://Exit case
                        break;
                    default: //Invalid input case
                        System.out.println("Please enter valid option");
                }

                if (algorithm > 0 && algorithm < 6) {
                    //Printing the name of the algorithm that is executed
                    System.out.println("Name of the chosen algorithm: " + findAlgorithm(algorithm));
                    //Printing the time of the algorithm that is executed
                    System.out.println("The specified time: " + time);
                    //Printing the number of nodes that are expanded
                    //System.out.println("Number of nodes expanded: " + nodeCounter);

                    //Creating the stack to order the path from the beginning to the end
                    Stack<Node> path = new Stack<>();
                    Node pathNode = subOptimum;
                    while (pathNode != null) {
                        path.push(pathNode);
                        pathNode = pathNode.getParent();
                    }
                    //Printing the result
                    printResult(path, nodeCounter, isOptimum);

                    if(optimumTime != 0)
                        System.out.println("The time to find the optimum solution is " + optimumTime/(1000) + " seconds");
                }
                //Exit option is chosen
                if (algorithm == 6)
                    break;
            }
            else {
                System.out.println("Please enter valid option");
                System.out.println();
            }
        }


    }

    //In this heuristic, I used Pagoda board position. According to it, heuristic compares the pagodaBoard and node board.
    //Then, heuristic assigns a value to node board and returns that value
    private static int heuristic(Node node){
        int [][] pagodaBoard = {
                {0,0,0,0,0,0,0},
                {0,0,0,1,0,0,0},
                {0,0,0,0,0,0,0},
                {0,1,0,1,0,1,0},
                {0,0,0,0,0,0,0},
                {0,0,0,1,0,0,0},
                {0,0,0,0,0,0,0}
        };

        int value = 0;
        Location[][] locations = node.getBoard().getBoard();

        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations.length; j++) {
                if(locations[i][j] != null && locations[i][j].isCheck()){
                    value += pagodaBoard[i][j];
                }
            }
        }
        return value;
    }

    //Returns the name of the algorithm according to the entered input
    private static String findAlgorithm(int algorithm) {
        if (algorithm == 1)
            return "BFS";
        else if (algorithm == 2)
            return "DFS";
        else if (algorithm == 3)
            return "IDS";
        else if (algorithm == 4)
            return "DFS with Random Selection";
        else if (algorithm == 5)
            return "DFS with Node Selection Heuristic";
        else return "Invalid option";
    }

    //Printing the board and the information to the console
    private static void printResult(Stack<Node> stack, long nodeCounter, boolean isOptimal) {
        Node node = null;
            while (!stack.empty()) {
                node = stack.pop();
                System.out.println("Depth Level: " + node.getDepthLevel());
                if (node.getDestination() != 0 && node.getSource() != 0) {
                    System.out.println("Source: " + node.getSource() +
                            "\n" + "Destination: " + node.getDestination());
                }
                drawBoard(node.getBoard().getBoard());
                System.out.println();
            }
            System.out.println("Number of visited nodes: " + nodeCounter);

                if (isOptimal) {
                    System.out.println("Optimum solution found.");
                } else {
                    System.out.println("Sub-optimum Solution Found with " + (32 - node.getDepthLevel()) + " remaining pegs");
                    System.out.println();
                }
    }

    //Draws the board
    private static void drawBoard(Location[][] board) {
            //For loop to look for the board
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    //Drawing the upper border
                    if (i == 0 && j == 0) {
                        System.out.println("---------");
                    }
                    //If it is first column, draw the left border
                    if (j == 0) {
                        System.out.print("|");
                    }
                    //Assigning the specified location
                    Location location = board[i][j];
                    //If the location is null, then there is invalid place
                    if (location == null) {
                        System.out.print(" ");
                        //Else if the location is empty, then write O for the point
                    } else if (!location.isCheck()) {
                        System.out.print("O");
                        //Else if the location is full, then write X for the point
                    } else if (location.isCheck()) {
                        System.out.print("X");
                    }
                    //If it is last column, draw the right border
                    if (j == 6) {
                        System.out.print("|");
                    }
                }
                System.out.println();
                //Drawing the lower border
                if (i == 6) {
                    System.out.println("---------");
                }
            }
     }

    //Checks the board is in the optimum state or not
    private static boolean checkPawn(Board board) {
        //Calculates number of pawn of the board
        int numOfPawn = 0;
        Location[][] location = board.getBoard();

        //for loop to check whether there is a pawn or not.
        for (int i = 0; i < location.length; i++) {
            for (int j = 0; j < location.length; j++) {
                if (location[i][j] != null && location[i][j].isCheck() == true)
                    numOfPawn++;
            }
        }
        //If there is only one pawn and it is in the 3,3 coordinate which is 17, it is optimum state
        if (numOfPawn == 1 && location[3][3].isCheck() == true)
            return true;
            //Otherwise it is not optimum
        else
            return false;
    }

    //Finds the possible moves according to the board
    private static ArrayList<Node> addChildren(Board board, Node parent) {
        ArrayList<Node> childNodes = new ArrayList<>();
        Location[][] location = board.getBoard();

        //For loop to look into the board
        for (int row = 0; row < location.length; row++) {
            for (int column = 0; column < location.length; column++) {
                //If the location is nut null and there is no pawn, there might be possible moves
                if (location[row][column] != null && location[row][column].isCheck() == false) {
                    //These if cases are searching possible moves for the empty location.
                    // Searching is done by this order: up,left,right,down
                    //This is the up case. Looks for row-2,column if row-1,column is full.
                    if (row - 2 >= 0 && location[row - 2][column] != null && location[row - 1][column].isCheck() && location[row - 2][column].isCheck()) {
                        //Creating new node
                        Node newNode = new Node();
                        Board currentBoard = new Board();
                        Location[][] newLocation;
                        //Copying the current board
                        newLocation = copyArray(location);
                        //Setting the source and destination of the node
                        newNode.setSource(location[row - 2][column].getLabel());
                        newNode.setDestination(location[row][column].getLabel());
                        //Moving the pawn in the board
                        newLocation[row - 1][column].setCheck(false);
                        newLocation[row - 2][column].setCheck(false);
                        newLocation[row][column].setCheck(true);
                        //Assigning the board
                        currentBoard.setBoard(newLocation);
                        newNode.setBoard(currentBoard);
                        //Setting the parent node
                        newNode.setParent(parent);
                        newNode.setDepthLevel(parent.getDepthLevel() + 1);
                        //Adding new node to the childNodes Arraylist
                        childNodes.add(newNode);
                    }
                    //This is the left case. Looks for row,column-2 if row,column-1 is full.
                    if (column - 2 >= 0 && location[row][column - 2] != null && location[row][column - 1].isCheck() && location[row][column - 2].isCheck()) {
                        //Creating new node
                        Node newNode = new Node();
                        Location[][] newLocation;
                        Board currentBoard = new Board();
                        //Copying the current board
                        newLocation = copyArray(location);
                        //Setting the source and destination of the node
                        newNode.setSource(location[row][column - 2].getLabel());
                        newNode.setDestination(location[row][column].getLabel());
                        //Moving the pawn in the board
                        newLocation[row][column - 1].setCheck(false);
                        newLocation[row][column - 2].setCheck(false);
                        newLocation[row][column].setCheck(true);
                        //Assigning the board
                        currentBoard.setBoard(newLocation);
                        newNode.setBoard(currentBoard);
                        //Setting the parent node
                        newNode.setParent(parent);
                        newNode.setDepthLevel(parent.getDepthLevel() + 1);
                        //Adding new node to the childNodes Arraylist
                        childNodes.add(newNode);
                    }
                    if (column + 2 < 7 && location[row][column + 2] != null && location[row][column + 1].isCheck() && location[row][column + 2].isCheck()) {
                        //Creating new node
                        Node newNode = new Node();
                        Location[][] newLocation;
                        Board currentBoard = new Board();
                        //Copying the current board
                        newLocation = copyArray(location);
                        //Setting the source and destination of the node
                        newNode.setSource(location[row][column + 2].getLabel());
                        newNode.setDestination(location[row][column].getLabel());
                        //Moving the pawn in the board
                        newLocation[row][column + 1].setCheck(false);
                        newLocation[row][column + 2].setCheck(false);
                        newLocation[row][column].setCheck(true);
                        //Assigning the board
                        currentBoard.setBoard(newLocation);
                        newNode.setBoard(currentBoard);
                        //Setting the parent node
                        newNode.setParent(parent);
                        newNode.setDepthLevel(parent.getDepthLevel() + 1);
                        //Adding new node to the childNodes Arraylist
                        childNodes.add(newNode);
                    }
                    if (row + 2 < 7 && location[row + 2][column] != null && location[row + 1][column].isCheck() && location[row + 2][column].isCheck()) {
                        //Creating new node
                        Node newNode = new Node();
                        Location[][] newLocation;
                        Board currentBoard = new Board();
                        //Copying the current board
                        newLocation = copyArray(location);
                        //Setting the source and destination of the node
                        newNode.setSource(location[row + 2][column].getLabel());
                        newNode.setDestination(location[row][column].getLabel());
                        //Moving the pawn in the board
                        newLocation[row + 1][column].setCheck(false);
                        newLocation[row + 2][column].setCheck(false);
                        newLocation[row][column].setCheck(true);
                        //Assigning the board
                        currentBoard.setBoard(newLocation);
                        newNode.setBoard(currentBoard);
                        //Setting the parent node
                        newNode.setParent(parent);
                        newNode.setDepthLevel(parent.getDepthLevel() + 1);
                        //Adding new node to the childNodes Arraylist
                        childNodes.add(newNode);
                    }
                }
            }
        }
        //Sorts the children according to sum of their source and destination values and returns the arraylist of children nodes
        return childNodes.stream().sorted(Comparator.comparing(x -> x.getSource() + x.getDestination())).collect(Collectors.toCollection(ArrayList::new));
    }

    //Setting the initial state of the board
    private static void fillBoard(Location[][] board) {
        //LabelIndex holds the label of the specified coordinates
        int labelIndex = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                //Restricting the board boundries
                if (i < 2 && j < 2) {
                    board[i][j] = null;
                } else if (i < 2 && j > 4) {
                    board[i][j] = null;
                } else if (i > 4 && j < 2) {
                    board[i][j] = null;
                } else if (i > 4 && j > 4) {
                    board[i][j] = null;
                    //Setting the center point and assigning empty point
                } else if (i == 3 && j == 3) {
                    labelIndex++;
                    board[i][j] = new Location();
                    board[i][j].setCheck(false);
                    board[i][j].setLabel(labelIndex);
                    //Setting the other points and filling the points
                } else {
                    labelIndex++;
                    board[i][j] = new Location();
                    board[i][j].setCheck(true);
                    board[i][j].setLabel(labelIndex);
                }
            }
        }
    }

    //Copying the specified array
    private static Location[][] copyArray(Location[][] board) {

        //Copying the board, and values of the locations
        Location[][] copyBoard = new Location[7][7];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != null) {
                    copyBoard[i][j] = new Location();
                    copyBoard[i][j].setCheck(board[i][j].isCheck());
                    copyBoard[i][j].setLabel(board[i][j].getLabel());
                }
            }
        }
        return copyBoard;
    }
}
