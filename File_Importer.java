import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class File_Importer {

    public static void CSV_reader(String filePath) {                        //NOVO: importa o CSV informado pelo usuário. Válida cada registro e só importa dados válidos. Informa erros ao usuário

        String file = filePath;
        BufferedReader reader = null;
        String line = "";

        int currentLine = 0;
        int totalImported = 0;
        
        try {
           reader = new BufferedReader(new FileReader(file));
          
           while((line = reader.readLine()) != null) {
                
                String[] row = line.split(";");
                currentLine ++;
                totalImported ++;

                Long cpf = readCPF(row, currentLine);
                Integer rg = readRG(row, currentLine);
                LocalDate dataNasc = readDataNasc(row, currentLine);
                Long dataCpf = concatDateAndCpf(dataNasc, cpf);

                if(cpf == null || rg == null || dataNasc == null) {
                    totalImported--;
                }
                else {
                    Person pessoa = new Person(cpf, rg, row[2], dataNasc, row[4]);
                    boolean duplicatedCpf = checkDuplicatedCpf(cpf);
                    
                    if(duplicatedCpf) {
                        totalImported--;
                        System.out.print("\033[31m" + "\n\t" + "CPF duplicado na linha " + currentLine + "! Registro não importado!" + "\033[0m");
                    } 
                    else {
                        Nodo <Long> newNodeCpf = Main.CPF_TREE.insertNode(cpf, pessoa);
                        Main.CPF_TREE.checkTreeUnbalanceFromLeaf(newNodeCpf);
                        
                        Nodo <String> newNodeNome = Main.NOME_TREE.insertNode(row[2].toLowerCase(), pessoa);
                        Main.NOME_TREE.checkTreeUnbalanceFromLeaf(newNodeNome);

                        // Nodo <LocalDate> newNodeData = Main.DATANASC_TREE.insertNode(dataNasc, pessoa);
                        // Main.DATANASC_TREE.checkTreeUnbalanceFromLeaf(newNodeData);

                        Nodo <Long> newNodeDataCpf = Main.DATACPF_TREE.insertNode(dataCpf, pessoa);
                        Main.DATACPF_TREE.checkTreeUnbalanceFromLeaf(newNodeDataCpf);
                    }
                }   
            }
            System.out.println();
        } 
        catch (Exception e) {
            System.out.println("\033[31m" + "\n\t" + "ATENÇÃO: Arquivo ou diretório inexistente! Nenhum registro importado!" + "\033[0m");
        }
        finally {
            System.out.println("\033[32m" + "\t" + "Foram importados " + totalImported + " registros!" + "\033[0m");
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean checkDuplicatedCpf(Long cpf) {                    //NOVO: verifica se um CPF que está sendo importado já está cadastrado. Se estiver o mesmo não é importado. 
        
        boolean duplicatedCpf = false;
        Nodo <Long> node = Main.CPF_TREE.searchNode(cpf);

        if(node.getKey() != null && node.getKey().compareTo(cpf) == 0) {
            duplicatedCpf = true;
        }
        return duplicatedCpf;
    }

    public static Long readCPF(String[] row, int line) {                    //NOVO: faz a leitura e validação de números de CPF. 

        Long cpf = null;
        
        try {
            cpf = Long.valueOf(row[0]);
        } catch (NumberFormatException e) {
            System.out.print("\n\t" + "\033[31m" + "CPF inválido na linha " + line + "! Informe apenas números! Registro não importado!" + "\033[0m");                    
        }
        return cpf;
    }

    public static Integer readRG(String[] row, int line) {                  //NOVO: faz a leitura e validação de números de RG.

        Integer rg = null;

        try {
            rg = Integer.valueOf(row[1]);
        } catch (NumberFormatException e) {
            System.out.print("\n\t" + "\033[31m" + "RG inválido na linha " + line + "! Informe apenas números! Registro não importado!" + "\033[0m");                    
        }
        return rg;
    }

    public static LocalDate readDataNasc(String[] row, int line) {          //NOVO: faz a leitura e conversão de datas texto (dd/MM/yyyy) em datas LocalDate (yyyy/mm/dd).

        LocalDate dataNascimento = null;

        try {
            DateTimeFormatter convertInputDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dataNascimento = LocalDate.parse(row[3], convertInputDate);
        }
        catch (DateTimeParseException e){
            System.out.print("\n\t" + "\033[31m" + "Data inválida na linha " + line + "! Informe em formato 'dd/mm/aaaa'! Registro não importado!" + "\033[0m");
        }
        return dataNascimento;
    }

    public static Long concatDateAndCpf(LocalDate dataNasc, Long cpf) {

        Long dataCpf = null;

        String dataNascString = convertDataToString(dataNasc);
        String cpfString = Long.toString(cpf);

        String dataCpfString = dataNascString + cpfString;

        dataCpf = Long.parseLong(dataCpfString);

        return dataCpf;
    }

    public static String convertDataToString(LocalDate dataNasc) {          //NOVO: faz a leitura, elimina as barras e converte a data para long

        String dataString = null;

        DateTimeFormatter convertInputDate = DateTimeFormatter.ofPattern("yyyyMMdd");
        dataString = dataNasc.format(convertInputDate);

        return dataString;
    }
} 