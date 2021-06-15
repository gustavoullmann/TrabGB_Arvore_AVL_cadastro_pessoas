import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.format.DateTimeParseException;

public class Menu {          

    public static void menu() {

        Scanner input = new Scanner(System.in);

        if(Main.CPF_TREE.getRoot().getKey() == null) {
            System.out.println("\n\t" + "\033[31m" + "ATENÇÃO: não existem registros carregados! Escolha a opção 'i' para fazer a importação!" + "\033[0m");
        }

        System.out.println("\n" + "\033[1;33m" + "Escolha uma opção no menu abaixo:"  + "\n");

        System.out.println("\t" + "'i' - Para importar um arquivo CSV;");
        System.out.println("\t" + "'c' - Para buscar pelo CPF;");
        System.out.println("\t" + "'n' - Para buscar pelo nome;");
        System.out.println("\t" + "'d' - Para buscar pela data de nascimento;");
        System.out.println("\t" + "'p' - Para imprimir a árvore em múltiplos percursos");
        System.out.println("\t" + "'s' - Para sair do programa");

        System.out.print("\n" + "Qual a opção desejada? " + "\033[0m");
        
        try {
            String option = input.nextLine();

            switch(option.toLowerCase()) {

                case "i":                                                                           //NOVO

                    try {
                        System.out.print("\n\t" + "\033[31m" + "ATENÇÃO: esta função está configurada para trabalhar com arquivos '.CSV' cujo separador é ';'." + 
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

                case "c":                                                                           //NOVO
                    if(Main.CPF_TREE.getRoot().getKey() == null) {
                        System.out.println("\n\t" + "\033[31m" + "ATENÇÃO: não é possível buscar por CPF!" + "\033[0m");                //caso o usuário tente efetuar busca sem carregar um CSV
                        menu();
                    }
                    else {
                        System.out.print("\n" + "\033[1;33m" + "Digite o CPF que você quer buscar: " + "\033[0m"); 

                        try {                                      
                            Long cpf = input.nextLong();
                            Nodo <Long> returnedNode = Main.CPF_TREE.searchNode(cpf);

                            if(returnedNode.getPessoa() == null) {
                                System.out.println("\n\t" + "\033[31m" + "ATENÇÃO: Não foi encontrado nenhuma pessoa com o CPF '" + cpf + "'" + "\033[0m");
                                menu();
                            }
                            else {
                                System.out.println("\n" + "\033[1;33m" + "Abaixo as informações da pessoa com o CPF '" + cpf + "'" + "\033[0m" + "\n");
                                returnedNode.getPessoa().printInformations();
                                menu();
                            } 
                        } 
                        catch (Exception InputMismatchException) {
                            System.out.println("\n\t" + "\033[31m" + "ATENÇÃO: você digitou uma tecla não numérica! Voltando para o Menu inicial..." + "\033[0m");
                            menu();
                        }
                    }
                    break;

                case "n":                                                                           //NOVO

                    if(Main.NOME_TREE.getRoot().getKey() == null) {
                        System.out.println("\n\t" + "\033[31m" + "ATENÇÃO: não é possível buscar por nome!" + "\033[0m");              //caso o usuário tente efetuar busca sem carregar um CSV
                        menu();
                    }
                    else {
                        System.out.print("\n" + "\033[1;33m" + "Digite o nome, ou parte desse, que você quer buscar: " + "\033[0m");

                        try {
                            String sequence = input.next().toLowerCase();                                                               //converte para lowercase a chave de busca 

                            Nodo<String> subTreeRootNode = Search_String.searchSubTreeRootNode(Main.NOME_TREE, sequence);               //encontra o primeiro nó que possui a sequencia informada pelo usuário

                            if(subTreeRootNode.getKey() == null) {
                                System.out.println("\n\t" + "\033[31m" + "Não foram encontrados resultados para os nomes pesquisados!" + "\033[0m");
                            }
                            else {
                                Search_String.inOrderTraversal(subTreeRootNode, sequence);                                              //faz a impressão em tela das informações da(s) pessoa(s) encontrada(s)
                            }
                            menu();
                        }
                        catch (Exception InputMismatchException) {
                            System.out.println("\n\t" + "\033[31m" + "ATENÇÃO: você digitou nome inválido! Voltando para o Menu inicial..." + "\033[0m");
                            menu();
                        }
                    }
                    break;

                case "d":                                                                           //NOVO

                    if(Main.DATANASC_TREE.getRoot().getKey() == null) {
                        System.out.println("\n\t" + "\033[31m" + "ATENÇÃO: não é possível buscar por data de nascimento!" + "\033[0m");         //caso o usuário tente efetuar busca sem carregar um CSV
                        menu();
                    }
                    else {
                        System.out.print("\n\t" + "\033[31m" + "ATENÇÃO: Digite uma data inicial e uma data final para busca, no formato " +
                        "\033[32m" + "dd" + "\033[0m" + "/" + "\033[32m" + "mm" + "\033[0m" + "/" + "\033[32m" + "aaaa" + "\033[31m" +
                        "\n\t" + "Caso deseje pesquisar por um único dia, informe datas iguais para início e fim!" + "\033[0m" + "\n");
    
                        System.out.print("\n\t" + "\033[1;33m" + "Data inicial: " + "\033[0m");
    
                        String inputMinDate = input.nextLine();
                        LocalDate minDate = readDataNasc(inputMinDate);                                                                          //converte data string informada pelo usuário em LocalDate
    
                        System.out.print("\t" + "\033[1;33m" + "Data final: " + "\033[0m");      
                        
                        String inputMaxDate = input.nextLine();
                        LocalDate maxDate = readDataNasc(inputMaxDate);                                                                         //converte data string informada pelo usuário em LocalDate
    
                        if(minDate.isAfter(maxDate)) {
                            System.out.println("\n\t" + "\033[31m" + "ATENÇÃO: a data final é anterior a data inicial!" + "\033[0m");
                            menu();
                        } 
                        else {
                            Nodo<LocalDate> rootNode = Main.DATANASC_TREE.getRoot();
                            Tree<LocalDate> searchByRangeResultTree = new Tree<LocalDate>();                                                    //Árvore temporária para receber os nós dentro do intervalo pesquisado
                            searchByRangeResultTree.searchNodeByRangeDate(rootNode, minDate, maxDate);                                          //insere ná árvore temporária os nós dentro do intervalos de datas 
                            Nodo<LocalDate> rootNodeResultTree = searchByRangeResultTree.getRoot();                                             //retorna o root da árvore temporária
    
                            if(rootNodeResultTree.getKey() == null) {
                                System.out.println("\n\t" + "\033[31m" + "Não foram encontrados resultados para as datas pesquisadas!" + "\033[0m");
                            }
                            else {
                                searchByRangeResultTree.printInformationsInOrderTraversal(rootNodeResultTree);                                  //percorre a árvore temporária em ordem, fazendo a impressão cronológica das informações 
                            }
                            menu();
                        }
                    }
                    break;

                case "p":                                                                           //NOVO

                    System.out.println("\n" + "\033[1;33m" + "Escolha uma árvore para impressão:"  + "\n");

                    System.out.println("\t" + "'c' - Para imprimir árvore de CPF;");
                    System.out.println("\t" + "'n' - Para imprimir árvore de nome;");
                    System.out.println("\t" + "'d' - Para imprimir árvore de data de nascimento;");
            
                    System.out.print("\n" + "Qual a opção desejada? " + "\033[0m");

                    try {
                        String option2 = input.nextLine();

                        switch(option2.toLowerCase()) {

                            case "c":
                                Tree treeCpf = Main.CPF_TREE;
                                Nodo rootNodeCpf = Main.CPF_TREE.getRoot();

                                System.out.println("\n" + "\033[1;33m" + "Imprimir a árvore de CPF em múltiplos percursos: " + "\033[0m" + "\n");
                                printer(rootNodeCpf, treeCpf);
                                break;
                               
                            case "n":
                                Tree treeNome = Main.NOME_TREE;
                                Nodo rootNodeNome = Main.NOME_TREE.getRoot();

                                System.out.println("\n" + "\033[1;33m" + "Imprimir a árvore de Nomes em múltiplos percursos: " + "\033[0m" + "\n");
                                printer(rootNodeNome, treeNome);
                                break;

                            case "d":
                                Tree treeDate = Main.DATANASC_TREE;
                                Nodo rootNodeDate = Main.DATANASC_TREE.getRoot();

                                System.out.println("\n" + "\033[1;33m" + "Imprimir a árvore de Datas de Nascimento em múltiplos percursos: " + "\033[0m" + "\n");
                                printer(rootNodeDate, treeDate);
                                break;
                            
                            default:
                                System.out.println("\n\t" + "\033[31m" + "ATENÇÃO: a opção digitada não consta no menu!" + "\033[0m");
                                System.out.println("\t" + "\033[31m" + "Digite apenas as letras 'c', 'n' ou 'd'!" + "\033[0m" + "\n");
                                menu();
                                break;
                        }
                    }
                    catch (Exception e) {
                        System.out.println("\n\t" + "\033[31m" + "ATENÇÃO: a opção digitada não consta no menu!" + "\033[0m");
                        menu();
                    }
                    menu();
                    break;
                
                case "s":

                    System.exit(0);

                default:
                    System.out.println("\n\t" + "\033[31m" + "ATENÇÃO: a opção digitada não consta no menu!" + "\033[0m");
                    System.out.println("\t" + "\033[31m" + "Digite apenas as letras 'i', 'c', 'n', 'd', 'p' ou 's'!" + "\033[0m" + "\n");
                    menu();
                    break;
            }
        }
        catch (Exception e) {
            System.out.println("\n\t" + "\033[31m" + "ATENÇÃO: a opção digitada não consta no menu!" + "\033[0m");
            System.out.println("\t" + "\033[31m" + "Digite apenas as letras 'i', 'c', 'n', 'd', 'p' ou 's'!" + "\033[0m" + "\n");
            menu();
        }
        input.close();
    }

    public static LocalDate readDataNasc(String inputDate) {                                        //NOVO: converte a data String (dd/mm/yyyy) informada pelo usuário em LocalDate (yyyy/mm/dd) usada na Search

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

    public static <T> void printer(Nodo<T> rootNode, Tree tree) {

        System.out.println(tree.printHeader());
        tree.printTree(rootNode, 0);
        System.out.println("\033[1;33m" + "Legenda: " + "\033[0m" + "nó[" + "\033[32m" + "fator balanceamento" + "\033[0m" + "]" + "\n");

        System.out.print("\n" + "\033[1;33m" + "Pré ordem: " + "\033[0m" + "\t");
        tree.preOrderTraversal(rootNode);
        System.out.println();

        System.out.print("\033[1;33m" + "Em ordem: " + "\033[0m" + "\t");
        tree.inOrderTraversal(rootNode);
        System.out.println();

        System.out.print("\033[1;33m" + "Pós ordem: " + "\033[0m" + "\t");
        tree.postOrderTraversal(rootNode);
        System.out.print("\n");
    }
}
