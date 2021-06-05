
public class Nodo <T> {

    private T key;
	private Person pessoa;
    private Nodo <T> parent;
    private Nodo <T> rightSon;
    private Nodo <T> leftSon;
    private int nodeHeight;
    private int balanceFactor;

    public Nodo(T key, Person pessoa) {
        this.key = key;
		this.pessoa = pessoa;
        this.parent = null;
        this.rightSon = null;
        this.leftSon = null;
        this.nodeHeight = -1;			//nodeHeight = -1 ao criar nó vazio, facilita a implementação do cálculo da altura do nó pai
        this.balanceFactor = 0;
    }

    public Nodo() {       			    //construtor vazio para montar filhos vazios de nó folha; 
		this.key = null;
		this.pessoa = null;
        this.parent = null;
        this.rightSon = null;
        this.leftSon = null;
        this.nodeHeight = -1;			//nodeHeight = -1 ao criar nó vazio, facilita a implementação do cálculo da altura do nó pai
        this.balanceFactor = 0;
	}

    public T getKey() {
		return key;
	}

	public void setKey(T key) {
		this.key = key;
	}

	public Person getPessoa() {
		return pessoa;
	}

	public void setPessoa(Person pessoa) {
		this.pessoa = pessoa;
	}

	public Nodo <T> getParent() {
		return parent;
	}

	public void setParent(Nodo <T> parent) {
		this.parent = parent;
	}

	public Nodo <T> getRightSon() {
		return rightSon;
	}

	public void setRightSon(Nodo <T> rightSon) {
		this.rightSon = rightSon;
	}

	public Nodo <T> getLeftSon() {
		return leftSon;
	}

	public void setLeftSon(Nodo <T> leftSon) {
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

	public static <T> void calculateBalanceFactor(Nodo <T> node) {               

		if(node.getKey() != null) {
			int heightLeftSon = node.getLeftSon().getNodeHeight();
			int heightRightSon = node.getRightSon().getNodeHeight();

			node.setBalanceFactor((heightLeftSon) - (heightRightSon));
		}
	}

	public static <T> void calculateNodeHeight(Nodo <T> node) {					

		if(node.getKey() != null) {
			int heightLeftSon = node.getLeftSon().getNodeHeight();
			int heightRightSon = node.getRightSon().getNodeHeight();

			int maxSonHeight = Math.max(heightLeftSon, heightRightSon);

			node.setNodeHeight(maxSonHeight + 1);
		}
	}

	public String balanceFactorLabel(Nodo <T> node) {

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
				"Key: " + key + "\n" +
				"Pessoa: " + pessoa + "\n" +
				"Parent: " + parent + "\n" +
				"Right Son: " + rightSon + "\n" +
				"Left Son: " + leftSon + "\n" +
				"Node Height: " + nodeHeight + "\n" +
				"Balance Factor: " + balanceFactor + "\033[0m";
	}   
}
