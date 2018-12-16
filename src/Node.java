
//Node class to hold the movements
public class Node {
	//Parent node to hold the parent
	private Node parent;
	//The label of the moved pawn from the location
	private int source;
	//The label of the moved pawn to the location
	private int destination;
	//Current board state
	private Board board;
	//Holds the depth of the node
	private int depthLevel;

	//Constructor
	public Node(){
		this.parent = null;
		this.source = 0;
		this.destination = 0;
	}

	//Getter and the setter methods

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public int getDestination() {
		return destination;
	}

	public void setDestination(int destination) {
		this.destination = destination;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public int getDepthLevel() {
		return depthLevel;
	}

	public void setDepthLevel(int depthLevel) {
		this.depthLevel = depthLevel;
	}


}
