import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Person {

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
            "CPF: " + cpf + "\n" +
            "RG: " + rg + "\n" +
            "Nome: " + nome + "\n" +
            "Data de Nascimento: " + dateConverter() + "\n" +
            "Município: " + municipio);
    }
}
