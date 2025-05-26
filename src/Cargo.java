import java.math.BigDecimal;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class Cargo {
    private String nome;
    private BigDecimal salario;
    private BigDecimal bonificacaoPorAno;
    private BigDecimal beneficios;

    public Cargo(String nome, BigDecimal salario, BigDecimal bonificacaoPorAno, BigDecimal beneficios) {
        this.nome = nome;
        this.salario = salario;
        this.bonificacaoPorAno = bonificacaoPorAno;
        this.beneficios = beneficios.divide(BigDecimal.valueOf(100));;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public BigDecimal getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(BigDecimal beneficios) {
        this.beneficios = beneficios.divide(BigDecimal.valueOf(100));
    }

    public BigDecimal getBonificacaoPorAno() {
        return bonificacaoPorAno;
    }

    public void setBonificacaoPorAno(BigDecimal bonificacaoPorAno) {
        this.bonificacaoPorAno = bonificacaoPorAno;
    }
}
