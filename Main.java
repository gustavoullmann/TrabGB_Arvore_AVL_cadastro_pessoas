import java.util.Date;

public class Main {

    static Tree<Integer> AVL_TREE = new Tree<Integer>();   //TODO: remover essa instância: foi colocada só para eliminar os erros da classe Menu (métodos do trabalho antigo)
    static Tree<Long> CPF_TREE = new Tree<Long>();   
    static Tree<String> NOME_TREE = new Tree<String>(); 
    static Tree<Date> DATANASC_TREE = new Tree<Date>();
    
    public static void main(String[] args) {
        Menu.menu();
        //File_Importer.CSV_reader("/home/gustavo/Área de Trabalho/cadastro_pessoas_random.csv"); //TODO: remover esse teste   

        // CPF_TREE.printTree(CPF_TREE.getRoot(), 0);                  //TODO: remover esse teste  
        // System.out.println("\n\n" + "##############################################################################");  //TODO: remover esse teste  
        // NOME_TREE.printTree(NOME_TREE.getRoot(), 0);                //TODO: remover esse teste  
        // System.out.println("\n\n" + "##############################################################################");  //TODO: remover esse teste  
        // DATANASC_TREE.printTree(DATANASC_TREE.getRoot(), 0);        //TODO: remover esse teste  

        //Menu.menu();
    }
}
