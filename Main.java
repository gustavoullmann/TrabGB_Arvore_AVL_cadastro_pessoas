import java.time.LocalDate;

public class Main {

    static Tree<Long> CPF_TREE = new Tree<Long>();   
    static Tree<String> NOME_TREE = new Tree<String>(); 
    static Tree<LocalDate> DATANASC_TREE = new Tree<LocalDate>();
    
    public static void main(String[] args) {
        
        Menu.menu();
    }
}
