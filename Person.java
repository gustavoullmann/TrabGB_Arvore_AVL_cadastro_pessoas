import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import java.util.Calendar;
import java.util.Date;

public class Person {

    private Long cpf;
    private Integer rg;
    private String nome;
    private Date dataNascimento;
    private String municipio;
    
    public Person(Long cpf, Integer rg, String nome, Date dataNascimento, String municipio) {
        this.cpf = cpf;
        this.rg = rg;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.municipio = municipio;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public Integer getRg() {
        return rg;
    }

    public void setRg(Integer rg) {
        this.rg = rg;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String dateConverter() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String dateConverted = (df.format(getDataNascimento()));  
        return dateConverted; 
    }

    public void printInformations() {
        System.out.println(
            "CPF: " + cpf + "\n" +
            "RG: " + rg + "\n" +
            "Nome: " + nome + "\n" +
            "Data de Nascimento: " + dateConverter() + "\n" +
            "Município: " + municipio);
    }
}
