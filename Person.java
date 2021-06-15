import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Person {                                               //NOVO: classe criada para instanciar e armazenar as informações do arquivo CSV

    private Long cpf;
    private Integer rg;
    private String nome;
    private LocalDate dataNascimento;
    private String municipio;
    
    public Person(Long cpf, Integer rg, String nome, LocalDate dataNascimento, String municipio) {
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String dateConverter() {
        String dateConverted = dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return dateConverted;
    }

    public void printInformations() {
        System.out.println(
            "\033[32m" +
            "\t" + "CPF: " + cpf + "\n" +
            "\t" + "RG: " + rg + "\n" +
            "\t" + "Nome: " + nome + "\n" +
            "\t" + "Data de Nascimento: " + dateConverter() + "\n" +
            "\t" + "Município: " + municipio + "\033[0m");
    }
}