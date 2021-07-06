
import java.time.LocalDate;

public class Main {

    static Tree<Long> CPF_TREE = new Tree<Long>();   
    static Tree<String> NOME_TREE = new Tree<String>(); 
    //static Tree<LocalDate> DATANASC_TREE = new Tree<LocalDate>();
    static Tree<Long> DATACPF_TREE = new Tree<Long>();
    
    public static void main(String[] args) {
        
        Menu.menu();
    }
}

    // Autores:

    // Gustavo Ullmann
    // Igor Cassolli 
    // Vinícius F. M. Flores