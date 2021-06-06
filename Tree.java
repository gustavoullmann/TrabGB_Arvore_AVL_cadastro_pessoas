public class Tree <T extends Comparable<T>>{

    private Nodo <T> root;

    public Tree() {
        this.root = new Nodo<T>();
    }

    public Nodo <T> getRoot() {
        return root;
    }

    public void setRoot(Nodo <T> node) {
        root = node;
    }

    public Nodo <T> findMinNode(Nodo <T> root) {
        
        Nodo <T> minNode = root;

        while(minNode.getLeftSon().getKey() != null) {
            minNode = minNode.getLeftSon();
        }
        return minNode;        
    }

    public Nodo <T> findMaxNode(Nodo <T> root) {

        Nodo <T> maxNode = root;

        while(maxNode.getRightSon().getKey() != null) {
            maxNode = maxNode.getRightSon();
        }
        return maxNode;
    }

    public Nodo <T> insertNode(T key, Person pessoa) {                  

        Nodo <T> node = searchNode(key);

        if(node.getKey() == key) {
            System.out.println("\n" + "ATENÇÃO: o valor '" + key + "' já está existe na árvore!");
        }
        else {
            node.setKey(key);
            node.setPessoa(pessoa);

            Nodo <T> leftSonNode = new Nodo <T> ();
            Nodo <T> rightSonNode = new Nodo <T> ();
    
            node.setLeftSon(leftSonNode);
            node.setRightSon(rightSonNode);
    
            leftSonNode.setParent(node);
            rightSonNode.setParent(node);
    
            Nodo.calculateBalanceFactor(node);
            Nodo.calculateNodeHeight(node);

            updateHeigh(root);
            updateBalanceFactor(root);
        
        }
        return node;
    }

    public void removeNode (T key) {                 

        Nodo <T> node = searchNode(key);
        Nodo <T> nodeParent = node.getParent();
        Boolean isRootNode = (root == node);

        if(node.getKey() == null) {
            System.out.println("\n" + "ATENÇÃO: o valor '" + key + "' não existe na árvore!");
        }
        else if(isRootNode){
            removeRootNode(node);           
        }
        else {
            removeNonRootNode(node);         
        }
        checkTreeUnbalanceFromLeaf(nodeParent);
        
    }

    public void removeRootNode(Nodo <T> node) {
        Nodo <T> parent = node.getParent();
        Nodo <T> rightSon = node.getRightSon();
        Nodo <T> leftSon = node.getLeftSon();
        T rightSonKey = node.getRightSon().getKey();
        T leftSonKey = node.getLeftSon().getKey();

        if(rightSonKey == null && leftSonKey == null) {               //nó folha que é root: cria nova árvore vazia
            node.setKey(null);
        }
        else if(rightSonKey == null && leftSonKey != null) {          //filhos a esquerda               
            leftSon.setParent(parent);
            root = leftSon;
            
            node = null;

            updateHeigh(root);
            updateBalanceFactor(root);
        }
        else if(rightSonKey != null && leftSonKey == null) {          //filhos a direita
            rightSon.setParent(parent);
            root = rightSon;
           
            node = null;

            updateHeigh(root);
            updateBalanceFactor(root);
        }
        else {                                                          //filhos a esquerda e direita
            Nodo <T> maxNode = findMaxNode(leftSon);

            if(leftSon == maxNode) {
                maxNode.setParent(parent);
                maxNode.setRightSon(rightSon);
                rightSon.setParent(maxNode);
            } 
            else {
                maxNode.getRightSon().setParent(maxNode.getParent());
                maxNode.getParent().setRightSon(maxNode.getRightSon());

                maxNode.setParent(parent);

                maxNode.setRightSon(rightSon);
                rightSon.setParent(maxNode);

                maxNode.setLeftSon(leftSon);
                leftSon.setParent(maxNode);
            }          
            root = maxNode;
           
            node = null;

            updateHeigh(root);
            updateBalanceFactor(root);

            checkTreeUnbalanceFromLeaf(maxNode.getRightSon());
            }
        }

    public void removeNonRootNode(Nodo <T> node) {
        Nodo <T> parent = node.getParent();
        Nodo <T> rightSon = node.getRightSon();
        Nodo <T> leftSon = node.getLeftSon();
        T rightSonKey = node.getRightSon().getKey();
        T leftSonKey = node.getLeftSon().getKey();

        if(rightSonKey == null && leftSonKey == null) {               //nó folha
            if(node.getKey().compareTo(parent.getKey()) > 0) {
                parent.setRightSon(rightSon);
                rightSon.setParent(parent);
            } 
            else {
                parent.setLeftSon(leftSon);
                leftSon.setParent(parent);
            }

            node = null;

            updateHeigh(root);
            updateBalanceFactor(root);
        }             
        else if(rightSonKey == null && leftSonKey != null) {          //filhos a esquerda               
            if(leftSonKey.compareTo(parent.getKey()) > 0) {
                parent.setRightSon(leftSon);
            } 
            else {
                parent.setLeftSon(leftSon);
            }
            leftSon.setParent(parent);
          
            node = null;

            updateHeigh(root);
            updateBalanceFactor(root);
        }       
        else if(rightSonKey != null && leftSonKey == null) {          //filhos a direita
            if(rightSonKey.compareTo(parent.getKey()) > 0){
                parent.setRightSon(rightSon);
            } else {
                parent.setLeftSon(rightSon);
            }
            rightSon.setParent(parent);
          
            node = null;

            updateHeigh(root);
            updateBalanceFactor(root);
        }
        else {                                                          //filhos a esquerda e direita
            Nodo <T> maxNode = findMaxNode(leftSon);
            Nodo <T> maxNodeParent = maxNode.getParent();
            Nodo <T> maxNodeRightSon = maxNode.getRightSon();

            if(leftSon == maxNode) {
                maxNode.setParent(node.getParent());
                maxNode.setRightSon(rightSon);
                maxNode.getRightSon().setParent(maxNode);

                if(maxNode.getKey().compareTo(parent.getKey()) > 0){
                    parent.setRightSon(maxNode);
                } else {
                    parent.setLeftSon(maxNode);
                }
            } 
            else {
                maxNodeRightSon.setParent(maxNodeParent);
                maxNodeParent.setRightSon(maxNodeRightSon);

                if(maxNode.getKey().compareTo(parent.getKey()) > 0){
                    parent.setRightSon(maxNode);
                } else {
                    parent.setLeftSon(maxNode);
                }

                maxNode.setParent(parent);
                
                leftSon.setParent(maxNode);
                maxNode.setLeftSon(leftSon);

                rightSon.setParent(maxNode);
                maxNode.setRightSon(rightSon);
            }

            node = null;

            updateHeigh(root);
            updateBalanceFactor(root);

            checkTreeUnbalanceFromLeaf(maxNodeRightSon);
        }                                                                 
    }

    public Nodo <T> searchNode(T key) {

        Nodo <T> currentNode = root; 
        T currentNodeKey = currentNode.getKey();

        while(currentNodeKey != null && currentNodeKey.compareTo(key) != 0) {

            if(key.compareTo(currentNodeKey) < 0) {
                currentNode = currentNode.getLeftSon();
                currentNodeKey = currentNode.getKey();
            }
            else {
                currentNode = currentNode.getRightSon();
                currentNodeKey = currentNode.getKey();
            }
        }
        return currentNode;        
    }

    public void updateHeigh(Nodo <T> rootNode) {                        

        if(rootNode.getKey() == null) {
            rootNode.setNodeHeight(-1);
        }
        else {
            updateHeigh(rootNode.getLeftSon());
            updateHeigh(rootNode.getRightSon());

            Nodo.calculateNodeHeight(rootNode);
        }
    }

    public void updateBalanceFactor(Nodo <T> rootNode) {                
       
        if(rootNode.getKey() == null) {
            rootNode.setBalanceFactor(0);
        }
        else {
            updateBalanceFactor(rootNode.getLeftSon());
            updateBalanceFactor(rootNode.getRightSon());

            Nodo.calculateBalanceFactor(rootNode);
        }
    }

    public Nodo <T> checkTreeUnbalanceFromLeaf(Nodo <T> leaf) {

        Nodo <T> unbalancedNode = null;

        if(leaf != null) {
            Nodo <T> currentNode = leaf;
            Nodo <T> parent = currentNode.getParent();
            int balanceFactor = currentNode.getBalanceFactor();
    
            if(balanceFactor < -1 || balanceFactor > 1) {
                unbalancedNode = currentNode;
                rebalanceNode(unbalancedNode);
                updateHeigh(root);
                updateBalanceFactor(root);
            }
            else if(parent == null) {
                return unbalancedNode;
            } 
            else {
                checkTreeUnbalanceFromLeaf(parent);
            }
        }
        return unbalancedNode;
    }

    public void rebalanceNode(Nodo <T> unbalancedNode) {

        int bf = unbalancedNode.getBalanceFactor();
        int leftSonBf = unbalancedNode.getLeftSon().getBalanceFactor();
        int rightSonBf = unbalancedNode.getRightSon().getBalanceFactor();

        if((bf == -2 && rightSonBf == -1) || (bf == -2 && rightSonBf == 0)) {
            leftRotation(unbalancedNode);
        }   
        else if((bf == 2 && leftSonBf == 0) || (bf == 2 && leftSonBf == 1)) {    
            rightRotation(unbalancedNode);
        }
        else if(bf == -2 && rightSonBf == 1) {                       
            rightLeftRotation(unbalancedNode);
        }
        else if(bf == 2 && leftSonBf == -1) {                       //Não usado "else" para poder especificar a regra                                              
            leftRightRotation(unbalancedNode);
        }
    }

    public void rightRotation(Nodo <T> unbalancedNode) {

        Nodo <T> unbalancedNodeParent = unbalancedNode.getParent();
        Nodo <T> unbalancedNodeLeftSon = unbalancedNode.getLeftSon();
        Nodo <T> unbalancedNodeLeftSonsRightSon = unbalancedNodeLeftSon.getRightSon();

        unbalancedNode.setLeftSon(unbalancedNodeLeftSonsRightSon);
        unbalancedNodeLeftSonsRightSon.setParent(unbalancedNode);

        unbalancedNodeLeftSon.setRightSon(unbalancedNode);
        unbalancedNode.setParent(unbalancedNodeLeftSon);

        if(unbalancedNodeParent == null) {                              
            unbalancedNodeLeftSon.setParent(unbalancedNodeParent);
            root = unbalancedNodeLeftSon;
        }
        else {
            unbalancedNodeLeftSon.setParent(unbalancedNodeParent);
            if(unbalancedNodeParent.getKey().compareTo(unbalancedNodeLeftSon.getKey()) > 0) {
                unbalancedNodeParent.setLeftSon(unbalancedNodeLeftSon);
            }
            else {
                unbalancedNodeParent.setRightSon(unbalancedNodeLeftSon);
            }    
        }
        updateHeigh(root);
        updateBalanceFactor(root);
    }

    public void leftRotation(Nodo <T> unbalancedNode) {

        Nodo <T> unbalancedNodeParent = unbalancedNode.getParent();
        Nodo <T> unbalancedNodeRightSon = unbalancedNode.getRightSon();
        Nodo <T> unbalancedNodeRightSonsLeftSon = unbalancedNodeRightSon.getLeftSon();

        unbalancedNode.setRightSon(unbalancedNodeRightSonsLeftSon);
        unbalancedNodeRightSonsLeftSon.setParent(unbalancedNode);

        unbalancedNodeRightSon.setLeftSon(unbalancedNode);
        unbalancedNode.setParent(unbalancedNodeRightSon);

        if(unbalancedNodeParent == null) {                                  
            unbalancedNodeRightSon.setParent(unbalancedNodeParent);
            root = unbalancedNodeRightSon;
        }
        else {
            unbalancedNodeRightSon.setParent(unbalancedNodeParent);
            if(unbalancedNodeParent.getKey().compareTo(unbalancedNodeRightSon.getKey()) > 0) {
                unbalancedNodeParent.setLeftSon(unbalancedNodeRightSon);
            }
            else {
                unbalancedNodeParent.setRightSon(unbalancedNodeRightSon);
            }
        }
        updateHeigh(root);
        updateBalanceFactor(root);
    }

    public void leftRightRotation(Nodo <T> unbalancedNode) {

        Nodo <T> unbalancedNodeLeftSon = unbalancedNode.getLeftSon();

        leftRotation(unbalancedNodeLeftSon);
        rightRotation(unbalancedNode);                                   
    }

    public void rightLeftRotation(Nodo <T> unbalancedNode) { 

        Nodo <T> unbalancedNodeRightSon = unbalancedNode.getRightSon();
       
        rightRotation(unbalancedNodeRightSon);
        leftRotation(unbalancedNode);                                       
    }

    public void printTree(Nodo <T> rootNode, int level) {

        int tabulationRepetition = level;
        String tabulation = "\t".repeat(tabulationRepetition);

        if(rootNode.getLeftSon() == null || rootNode.getRightSon() == null) {
            System.out.println(tabulation + "*");
        } 
        else {
            String nodeKey = String.valueOf(rootNode.getKey());
            String nodeStatistics = "[" + rootNode.balanceFactorLabel(rootNode) + "]"; 

            System.out.println(tabulation + nodeKey + nodeStatistics);
            tabulationRepetition++;

            printTree(rootNode.getRightSon(), tabulationRepetition);
            printTree(rootNode.getLeftSon(), tabulationRepetition);
        }        
    }

    public String printHeader() {

        String header = "\033[1;34m" + "Root" + "\033[0m";
        int totalLevels = root.getNodeHeight() + 1;
        for(int i = 2; i <= totalLevels ; i++) {
            String currentLevel = "\t" + "\033[0;34m" + "level-" + i + "\033[0m";
            header += currentLevel;
        }
        return header;
    }

    public void printSearchNodePath (T key) {

        Nodo <T> currentNode = root; 
        T currentNodeKey = currentNode.getKey();
        String nodePath = "\n" + "Relação de nós visitados: ";

        while(currentNodeKey != key && currentNodeKey != null) {

            if(key.compareTo(currentNodeKey) < 0) {
                nodePath += currentNodeKey + " ";
                currentNode = currentNode.getLeftSon();
                currentNodeKey = currentNode.getKey();
            }
            else {
                nodePath += currentNodeKey + " ";
                currentNode = currentNode.getRightSon();
                currentNodeKey = currentNode.getKey();
            }
        }
        System.out.println("\033[32m" + nodePath + "\033[0m");
    }

    public void preOrderTraversal(Nodo <T> node) {
        
        if(node.getKey() != null) {
            System.out.print("\033[32m" + node.getKey() + "\033[0m" + "\t");

            preOrderTraversal(node.getLeftSon());
            preOrderTraversal(node.getRightSon());
        }
    }

    public void inOrderTraversal(Nodo <T> node) {

        if(node.getKey() != null) {
            inOrderTraversal(node.getLeftSon());

            System.out.print("\033[32m" + node.getKey() + "\033[0m" + "\t");

            inOrderTraversal(node.getRightSon());
        }
    }

    public void postOrderTraversal(Nodo <T> node) {

        if(node.getKey() != null) {
            postOrderTraversal(node.getLeftSon());
            postOrderTraversal(node.getRightSon());

            System.out.print("\033[32m" + node.getKey() + "\033[0m" + "\t");
        }
    }
 }