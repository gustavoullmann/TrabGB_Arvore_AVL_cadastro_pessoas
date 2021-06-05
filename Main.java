import java.util.Date;

public class Main {

    static Tree<Long> CPF_TREE = new Tree<Long>();   
    static Tree<String> NOME_TREE = new Tree<String>(); 
    static Tree<Date> DATANASC_TREE = new Tree<Date>();
    
    public static void main(String[] args) {
        //Menu.menu();
        File_Importer.CSV_reader(); //TODO: remover esse teste

        CPF_TREE.inOrderTraversal(CPF_TREE.getRoot());
        System.out.println();
        NOME_TREE.inOrderTraversal(NOME_TREE.getRoot());
        System.out.println();
        DATANASC_TREE.inOrderTraversal(DATANASC_TREE.getRoot());

    }
}
