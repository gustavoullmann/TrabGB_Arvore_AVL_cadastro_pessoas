
public class Nodo {

    private Integer data;
    private Nodo parent;
    private Nodo rightSon;
    private Nodo leftSon;
    private int nodeHeight;
    private int balanceFactor;

    public Nodo(Integer data) {
        this.data = data;
        this.parent = null;
        this.rightSon = null;
        this.leftSon = null;
        this.nodeHeight = -1;			//nodeHeight = -1 ao criar nó vazio, facilita a implementação do cálculo da altura do nó pai
        this.balanceFactor = 0;
    }

    public Nodo() {       			    //construtor vazio para montar filhos vazios de nó folha; 
		this.data = null;
        this.parent = null;
        this.rightSon = null;
        this.leftSon = null;
        this.nodeHeight = -1;			//nodeHeight = -1 ao criar nó vazio, facilita a implementação do cálculo da altura do nó pai
        this.balanceFactor = 0;
	}

    public Integer getData() {
		return data;
	}

	public void setData(Integer data) {
		this.data = data;
	}

	public Nodo getParent() {
		return parent;
	}

	public void setParent(Nodo parent) {
		this.parent = parent;
	}

	public Nodo getRightSon() {
		return rightSon;
	}

	public void setRightSon(Nodo rightSon) {
		this.rightSon = rightSon;
	}

	public Nodo getLeftSon() {
		return leftSon;
	}

	public void setLeftSon(Nodo leftSon) {
		this.leftSon = leftSon;
	}

	public int getNodeHeight() {
		return nodeHeight;
	}

	public void setNodeHeight(int height) {
		this.nodeHeight = height;
	}

	public int getBalanceFactor() {
		return balanceFactor;
	}

	public void setBalanceFactor(int balanceFactor) {
		this.balanceFactor = balanceFactor;
	}

	public static void calculateBalanceFactor(Nodo node) {               

		if(node.getData() != null) {
			int heightLeftSon = node.getLeftSon().getNodeHeight();
			int heightRightSon = node.getRightSon().getNodeHeight();

			node.setBalanceFactor((heightLeftSon) - (heightRightSon));
		}
	}

	public static void calculateNodeHeight(Nodo node) {					

		if(node.getData() != null) {
			int heightLeftSon = node.getLeftSon().getNodeHeight();
			int heightRightSon = node.getRightSon().getNodeHeight();

			int maxSonHeight = Math.max(heightLeftSon, heightRightSon);

			node.setNodeHeight(maxSonHeight + 1);
		}
	}

	public String balanceFactorLabel(Nodo node) {

		String label = "";
		int balanceFactor = node.getBalanceFactor();

		if(balanceFactor >= -1 && balanceFactor <= 1) {
			label = "\033[32m" + balanceFactor + "\033[0m";
		}
		else {
			label = "\033[31m" + balanceFactor + "\033[0m";
		}
		return label;
	}
	
	public String printNodeAttributes() {							//Não fizemos override de toString: gerava erro recursivo e "consumia" o método que imprime o endereço de memória do objeto
		return 	"\033[32m" +
				"This Node: " + toString() + "\n" +
				"Data: " + data + "\n" +
				"Parent: " + parent + "\n" +
				"Right Son: " + rightSon + "\n" +
				"Left Son: " + leftSon + "\n" +
				"Node Height: " + nodeHeight + "\n" +
				"Balance Factor: " + balanceFactor + "\033[0m";
	}   
}
