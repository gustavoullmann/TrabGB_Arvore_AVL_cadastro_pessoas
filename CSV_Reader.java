import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CSV_Reader {

    public static void CSV_importer() {

        String file = "cadastro_pessoas.csv";
        BufferedReader CSV_Reader = null;
        String line = "";
        
        try {
           CSV_Reader = new BufferedReader(new FileReader(file));
           while((line = CSV_Reader.readLine()) != null) {
                String[] row = line.split(";");

                Person p = new Person(readCPF(row), readRG(row), row[2], readDataNasc(row), row[4]);

                p.printInformations(); //TODO: remover esse teste
                System.out.println();
           }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                CSV_Reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Long readCPF(String[] row) {

        Long cpf = null;
        
        try {
            cpf = Long.valueOf(row[0]);
        } catch (NumberFormatException e) {
            System.out.println("CPF inválido! Informe apenas números!");                    
        }
        return cpf;
    }

    public static Integer readRG(String[] row) {

        Integer rg = null;

        try {
            rg = Integer.valueOf(row[1]);
        } catch (NumberFormatException e) {
            System.out.println("RG inválido! Informe apenas números!");                    
        }
        return rg;
    }

    public static Date readDataNasc(String[] row) {
        
        Date dataNascimento = null;
        
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dataNascimento = dateFormat.parse(row[3]);
        } catch (NumberFormatException | ParseException e) {
            System.out.println("Data inválida! Informe em formato 'dd/mm/aaaa'!");                    
        }
        return dataNascimento;
    }
} 