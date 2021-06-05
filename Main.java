import java.util.Date;

public class Main {

    static Tree<Long> CPF_TREE = new Tree<Long>();   
    static Tree<String> NOME_TREE = new Tree<String>(); 
    static Tree<Date> DATANASC_TREE = new Tree<Date>();
    
    public static void main(String[] args) {
        //Menu.menu();
        File_Importer.CSV_reader(); //TODO: remover esse teste

    }
}
