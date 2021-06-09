
import java.util.Scanner;

public class Menu {   
    public static void menu() {
       
        Scanner input = new Scanner(System.in);

        System.out.println("\n" + "\033[1;33m" + "Escolha uma opção no menu abaixo:"  + "\n");

        System.out.println("\t" + "'b' - Para buscar uma pessoa pelo CPF;");
        System.out.println("\t" + "'s' - Para sair do programa");

        System.out.print("\n" + "Qual a opção desejada? " + "\033[0m");
        
        try {
            String option = input.nextLine();

            switch(option.toLowerCase()) {
                case "b":                                     
                    System.out.print("\n" + "\033[1;33m" + "Digite o CPF que você quer buscar: " + "\033[0m");   
                    try {
                        Long cpf = input.nextLong();
                        Nodo <Long> returnedNode = Main.CPF_TREE.searchNode(cpf);

                        if(returnedNode.getPessoa() == null) {
                            System.out.println("\n" + "\033[31m" + "ATENÇÃO: Não foi encontrado nenhuma pessoa com o CPF '" + cpf + "'" + "\033[0m");
                            menu();
                        }
                        else {
                            System.out.println("\n" + "\033[1;33m" + "Abaixo as informações da pessoa com o CPF '" + cpf + "'" + "\033[0m" + "\n");
                            System.out.println(returnedNode.printNodeAttributes());
                            menu();
                        } 
                    } 
                    catch (Exception InputMismatchException) {
                        System.out.println("\n\t" + "\033[31m" + "ATENÇÃO: você digitou uma tecla não numérica! Voltando para o Menu inicial..." + "\033[0m");
                        menu();
                    }
                    break;
                case "s":
                    System.out.println("\n\t" + "\033[31m" + "SAINDO..." + "\033[0m");
                    System.exit(0);

                default:
                    System.out.println("\n\t" + "\033[31m" + "ATENÇÃO: a opção digitada não consta no menu!" + "\033[0m");
                    System.out.println("\t" + "\033[31m" + "Digite apenas as letras 'b' ou 's'!" + "\033[0m" + "\n");
                    menu();
                    break;
            }
        }
        catch (Exception e) {
            System.out.println("\n\t" + "\033[31m" + "ATENÇÃO: a opção digitada não consta no menu!" + "\033[0m");
            System.out.println("\t" + "\033[31m" + "Digite apenas as letras 'b' ou 's'!" + "\033[0m" + "\n");
            menu();
        }
        input.close();
    }
}

