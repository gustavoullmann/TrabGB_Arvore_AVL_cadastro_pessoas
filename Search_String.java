import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Search_String {

    public static boolean matchesPattern(String sequence, String pieceOfString) {

        Pattern pattern = Pattern.compile("^" + sequence, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(pieceOfString);
        boolean matchFound = matcher.find();

        return matchFound;
    } 

    public static Nodo<String> searchSubTreeRootNode(Tree<String> tree, String sequence) {

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

    public static void inOrderTraversal(Nodo <String> rootNode, String sequence) {

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