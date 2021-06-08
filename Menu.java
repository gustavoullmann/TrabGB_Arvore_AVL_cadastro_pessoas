import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.format.DateTimeParseException;

public class Menu {          

    public static void menu() {

        Scanner input = new Scanner(System.in);

        System.out.println("\n" + "\033[1;33m" + "Escolha uma opção no menu abaixo:"  + "\n");

        System.out.println("\t" + "'imp' - Para importar um arquivo CSV;");
        System.out.println("\t" + "'i' - Para inserir um nodo;");
        System.out.println("\t" + "'b' - Para buscar um nodo;");
        System.out.println("\t" + "'n' - Para buscar pela data de nascimento;");
        System.out.println("\t" + "'p' - Para imprimir a árvore em múltiplos percursos");
        System.out.println("\t" + "'s' - Para sair do programa");

        System.out.print("\n" + "Qual a opção desejada? " + "\033[0m");
        
        try {
            String option = input.nextLine();

            switch(option.toLowerCase()) {

                case "imp":

                    try {
                        System.out.print("\n\t" + "\033[31m" + "ATENÇÃO: esta função está configurada para trabalhar com arquivos .CSV cujo separador é ';'." + 
                        "\n\t" + "As colunas devem respeitar a seguinte ordem: " + 
                        "\033[32m" + "CPF" + "\033[0m" + ";" + 
                        "\033[32m" + "RG" + "\033[0m" + ";" + 
                        "\033[32m" + "Nome" + "\033[0m" + ";" + 
                        "\033[32m" + "DataNascimento" + "\033[0m" + ";"  + 
                        "\033[32m" + "MunicípioNascimento" + "\033[0m" + "\n");
                        
                        System.out.print("\n" + "\033[1;33m" + "Informe o caminho (path) para o arquivo CSV: " + "\033[0m");

                        String filePath = input.nextLine();
                        File_Importer.CSV_reader(filePath);

                        menu();
                    }
                    catch (Exception e) {
                        menu();
                    }
                    break;

                case "i":
            
                //     try {
                //         System.out.print("\n\t" + "\033[31m" + "ATENÇÃO: Digite qualquer tecla não numérica para sair e voltar ao Menu principal! " + "\033[0m" + "\n");
                        
                //         boolean userIntegerInput = true;

                //         while(userIntegerInput) {
                //             System.out.print("\n" + "\033[1;33m" + "Digite um valor inteiro para inserir na árvore: " + "\033[0m");
                        
                //             int key = input.nextInt();
                //             Nodo newNode = Main.AVL_TREE.insertNode(key);
                //             Main.AVL_TREE.checkTreeUnbalanceFromLeaf(newNode);

                //             System.out.println();
                //             System.out.println(Main.AVL_TREE.printHeader());
                //             Main.AVL_TREE.printTree(Main.AVL_TREE.getRoot(), 0);
                //         }
                //         menu();
                //     } 
                //     catch (Exception InputMismatchException) {
                //         System.out.println("\n\t" + "\033[31m" + "ATENÇÃO: você digitou uma tecla não numérica! Voltando para o Menu inicial..." + "\033[0m");
                //         menu();
                //     }
                     break;

                case "b":                                       
                   
                    // System.out.print("\n" + "\033[1;33m" + "Digite um valor inteiro para buscar na árvore: " + "\033[0m");
                        
                    // try {
                    //     int key = input.nextInt();
                    //     Main.AVL_TREE.printSearchNodePath(key);
                    //     Nodo returnedNode = Main.AVL_TREE.searchNode(key);

                    //     if(returnedNode.getKey() == null) {
                    //         System.out.println("\n" + "\033[31m" + "ATENÇÃO: o valor digitado não foi encontrado na árvore!" + "\033[0m");
                    //         menu();
                    //     }
                    //     else {
                    //         System.out.println("\n" + "\033[1;33m" + "Abaixo as informações do nó '" + key + "'" + "\033[0m" + "\n");
                    //         System.out.println(returnedNode.printNodeAttributes());
                    //         menu();
                    //     }
                    // } 
                    // catch (Exception InputMismatchException) {
                    //     System.out.println("\n\t" + "\033[31m" + "ATENÇÃO: você digitou uma tecla não numérica! Voltando para o Menu inicial..." + "\033[0m");
                    //     menu();
                    // }
                    break;

                case "n":
                    
                    System.out.println("\n" + "\033[1;33m" + "Digite uma data inicial e uma data final para busca, no formato 'dd/mm/aaaa':" + "\033[0m");
                    
                    System.out.print("\n\t" + "\033[1;33m" + "Data inicial: " + "\033[0m");

                    //String inputMinDate = input.nextLine();
                    //LocalDate minDate = readDataNasc(inputMinDate);

                    System.out.print("\t" + "\033[1;33m" + "Data final: " + "\033[0m");      
                    
                    //String inputMaxDate = input.nextLine();
                    //LocalDate maxDate = readDataNasc(inputMaxDate);


                    break;

                case "p":

                    // Nodo rootNode = Main.AVL_TREE.getRoot();
                    // Tree tree = Main.AVL_TREE;

                    // System.out.println("\n" + "\033[1;33m" + "Imprimir a árvore em múltiplos percursos: " + "\033[0m" + "\n");

                    // System.out.println(tree.printHeader());
                    // tree.printTree(rootNode, 0);
                    // System.out.println("\033[1;33m" + "Legenda: " + "\033[0m" + "nó[" + "\033[32m" + "fator balanceamento" + "\033[0m" + "]" + "\n");

                    // System.out.print("\n" + "\033[1;33m" + "Pré ordem: " + "\033[0m" + "\t");
                    // tree.preOrderTraversal(rootNode);
                    // System.out.println();

                    // System.out.print("\033[1;33m" + "Em ordem: " + "\033[0m" + "\t");
                    // tree.inOrderTraversal(rootNode);
                    // System.out.println();

                    // System.out.print("\033[1;33m" + "Pós ordem: " + "\033[0m" + "\t");
                    // tree.postOrderTraversal(rootNode);
                    // System.out.print("\n");

                    // menu();
                    break;
                
                case "s":

                    System.exit(0);

                default:
                    System.out.println("\n\t" + "\033[31m" + "ATENÇÃO: a opção digitada não consta no menu!" + "\033[0m");
                    System.out.println("\t" + "\033[31m" + "Digite apenas as letras 'i', 'b', 'r', 'p' ou 's'!" + "\033[0m" + "\n");
                    menu();
                    break;
            }
        }
        catch (Exception e) {
            System.out.println("\n\t" + "\033[31m" + "ATENÇÃO: a opção digitada não consta no menu!" + "\033[0m");
            System.out.println("\t" + "\033[31m" + "Digite apenas as letras 'i', 'b', 'r', 'p' ou 's'!" + "\033[0m" + "\n");
            menu();
        }
        input.close();
    }

    public static LocalDate readDataNasc(String inputDate) {

        LocalDate dataNascimento = null;

        try {
            DateTimeFormatter convertInputDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dataNascimento = LocalDate.parse(inputDate, convertInputDate);
        }
        catch (DateTimeParseException e){
            System.out.println("\033[31m" + "Data inválida! Informe em formato 'dd/mm/aaaa'!" + "\033[0m");
            menu();
        }
        return dataNascimento;
    }
}
