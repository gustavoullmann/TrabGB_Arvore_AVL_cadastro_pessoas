import java.util.Date;

public class Main {

    static Tree<Long> CPF_TREE = new Tree<Long>();   
    static Tree<String> NOME_TREE = new Tree<String>(); 
    static Tree<Date> DATANASC_TREE = new Tree<Date>();
    
    public static void main(String[] args) {
        File_Importer.CSV_reader(); 
        Menu.menu();
        //File_Importer.CSV_reader(); //TODO: remover esse teste
        //CPF_TREE.inOrderTraversal(CPF_TREE.getRoot());
        //System.out.println();
        //NOME_TREE.inOrderTraversal(NOME_TREE.getRoot());
        //System.out.println();
        //DATANASC_TREE.inOrderTraversal(DATANASC_TREE.getRoot());
        /*
        Long teste = Long.valueOf("90123456780");

        Nodo <Long> returnedNode = CPF_TREE.searchNode(teste);
        System.out.println(returnedNode.printNodeAttributes());
        */

    }
}
