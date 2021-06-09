import java.time.LocalDate;
import java.util.Date;

public class Main {

    static Tree<Integer> AVL_TREE = new Tree<Integer>();   //TODO: remover essa instância: foi colocada só para eliminar os erros da classe Menu (métodos do trabalho antigo)
    static Tree<Long> CPF_TREE = new Tree<Long>();   
    static Tree<String> NOME_TREE = new Tree<String>(); 
    static Tree<LocalDate> DATANASC_TREE = new Tree<LocalDate>();
    
    public static void main(String[] args) {
        
        //Menu.menu();
        
        File_Importer.CSV_reader("cadastro_pessoas.csv"); //TODO: remover esse teste   


        DATANASC_TREE.printTree(DATANASC_TREE.getRoot(), 0);

        String inputMinDate = "01/01/2001";
        LocalDate minDate = Menu.readDataNasc(inputMinDate);
        
        String inputMaxDate = "07/08/2007";
        LocalDate maxDate = Menu.readDataNasc(inputMaxDate);

        DATANASC_TREE.searchNode(minDate).getPessoa().printInformations();

        // CPF_TREE.printTree(CPF_TREE.getRoot(), 0);                  //TODO: remover esse teste  
        // System.out.println("\n\n" + "##############################################################################");  //TODO: remover esse teste  
        // NOME_TREE.printTree(NOME_TREE.getRoot(), 0);                //TODO: remover esse teste  
        // System.out.println("\n\n" + "##############################################################################");  //TODO: remover esse teste  
        // DATANASC_TREE.printTree(DATANASC_TREE.getRoot(), 0);        //TODO: remover esse teste  

        //Menu.menu();
    }
}
