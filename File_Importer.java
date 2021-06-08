import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class File_Importer {

    public static void CSV_reader(String filePath) {

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
                Date dataNasc = readDataNasc(row, currentLine);

                if(cpf == null || rg == null || dataNasc == null) {
                    totalImported--;
                }
                else {
                    Person pessoa = new Person(cpf, rg, row[2], dataNasc, row[4]);
                    boolean duplicatedCpf = checkDuplicatedCpf(cpf);
                    
                    if(duplicatedCpf) {
                        totalImported--;
                        System.out.println("\033[31m" + "CPF duplicado na linha " + currentLine + "! Registro não importado!" + "\033[0m");
                    } 
                    else {
                        Nodo <Long> newNodeCpf = Main.CPF_TREE.insertNode(cpf, pessoa);
                        Main.CPF_TREE.checkTreeUnbalanceFromLeaf(newNodeCpf);
                        
                        Nodo <String> newNodeNome = Main.NOME_TREE.insertNode(row[2], pessoa);
                        Main.NOME_TREE.checkTreeUnbalanceFromLeaf(newNodeNome);

                        Nodo <Date> newNodeData = Main.DATANASC_TREE.insertNode(dataNasc, pessoa);
                        Main.DATANASC_TREE.checkTreeUnbalanceFromLeaf(newNodeData);
                    }
                }   
            }
        } 
        catch (Exception e) {
            System.out.println("\033[31m" + "\n\t" + "ATENÇÃO: Arquivo ou diretório inexistente! Nenhum registro importado!" + "\033[0m");
        }
        finally {
            System.out.println("\033[32m" + "\n\t" + "Foram importados " + totalImported + " registros!" + "\033[0m");
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean checkDuplicatedCpf(Long cpf) {
        
        boolean duplicatedCpf = false;
        Nodo <Long> node = Main.CPF_TREE.searchNode(cpf);

        if(node.getKey() != null && node.getKey().compareTo(cpf) == 0) {
            duplicatedCpf = true;
        }
        return duplicatedCpf;
    }

    public static Long readCPF(String[] row, int line) {

        Long cpf = null;
        
        try {
            cpf = Long.valueOf(row[0]);
        } catch (NumberFormatException e) {
            System.out.println("\033[31m" + "CPF inválido na linha " + line + "! Informe apenas números! Registro não importado!" + "\033[0m");                    
        }
        return cpf;
    }

    public static Integer readRG(String[] row, int line) {

        Integer rg = null;

        try {
            rg = Integer.valueOf(row[1]);
        } catch (NumberFormatException e) {
            System.out.println("\033[31m" + "RG inválido na linha " + line + "! Informe apenas números! Registro não importado!" + "\033[0m");                    
        }
        return rg;
    }

    public static Date readDataNasc(String[] row, int line) {
        
        Date dataNascimento = null;
        
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dataNascimento = dateFormat.parse(row[3]);
        } catch (NumberFormatException | ParseException e) {
            System.out.println("\033[31m" + "Data inválida na linha " + line + "! Informe em formato 'dd/mm/aaaa'! Registro não importado!" + "\033[0m");                    
        }
        return dataNascimento;
    }
} 