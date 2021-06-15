import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Search_String {                                                                        //NOVO: classe criada para lidar mais facilmente com métodos exclusivos de uma Tree<String>.

    public static boolean matchesPattern(String sequence, String pieceOfString) {                   //NOVO: verifica se uma sequência está no início de uma String, independente de ser maiúscula ou minúscula

        Pattern pattern = Pattern.compile("^" + sequence, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(pieceOfString);
        boolean matchFound = matcher.find();

        return matchFound;
    } 

    public static Nodo<String> searchSubTreeRootNode(Tree<String> tree, String sequence) {          //NOVO: localiza e retorna o primeiro nó que inicia com a chave especificada pelo usuário

        Nodo <String> currentNode = tree.getRoot(); 
        int sequenceLenght = sequence.length();
        String currentNodeKey = currentNode.getKey().substring(0, sequenceLenght);

        while(currentNodeKey != null && (currentNodeKey.startsWith(sequence) == false)) {

            if(sequence.compareTo(currentNodeKey) < 0) {
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

    public static void inOrderTraversal(Nodo <String> rootNode, String sequence) {                  //NOVO: percorre a subárvore em ordem, fazendo a impressão das informações da pessoa de cada nó que retorna TRUE para o matchesPattern

        if(rootNode.getKey() != null) {
            inOrderTraversal(rootNode.getLeftSon(), sequence);

            if(matchesPattern(sequence, rootNode.getKey())) {

                System.out.println();
                rootNode.getPessoa().printInformations();
            }
            inOrderTraversal(rootNode.getRightSon(), sequence);
        }
    }
}