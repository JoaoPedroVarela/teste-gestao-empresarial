import java.math.BigDecimal;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Funcionario {
    private String nome;
    private Cargo cargo;
    private YearMonth mesAnoContratacao;
    private Integer anosTrabalhados;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/yyyy");

    public Funcionario(String nome, Cargo cargo, String mesAno) {
        this.nome = nome;
        this.cargo = cargo;
        this.mesAnoContratacao = YearMonth.parse(mesAno, FORMATTER);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public YearMonth getMesAnoContratacao() {
        return mesAnoContratacao;
    }

    public void setMesAnoContratacao(String mesAnoContratacao) {
        this.mesAnoContratacao = YearMonth.parse(mesAnoContratacao, FORMATTER);
    }

    public Integer getAnosTrabalhados(YearMonth mesAnoReferencia) {
        int anoRef = mesAnoReferencia.getYear();
        int mesRef = mesAnoReferencia.getMonthValue();

        int anoContr = mesAnoContratacao.getYear();
        int mesContr = mesAnoContratacao.getMonthValue();

        int anos = anoRef - anoContr;

        if (mesRef < mesContr) {
            anos -= 1;
        }

        return anos;
    }

    public BigDecimal getSalarioBaseFuncionario(YearMonth mesAnoReferencia) {
        Integer anosTrabalhados = getAnosTrabalhados(mesAnoReferencia);

        return cargo.getSalario().add(cargo.getBonificacaoPorAno().multiply(BigDecimal.valueOf(anosTrabalhados)));
    }

    public BigDecimal getSalarioBaseFuncionarioComBeneficios(YearMonth mesAnoReferencia) {

        if (cargo instanceof Secretario) {
            return getSalarioBaseFuncionario(mesAnoReferencia)
                    .add(getSalarioBaseFuncionario(mesAnoReferencia).multiply(cargo.getBeneficios()));
        }

        if (cargo instanceof Vendedor) {
            Vendas vendaDoMes = ((Vendedor) cargo).getListaVendas()
                    .stream().filter(venda -> venda.getMesVenda().equals(mesAnoReferencia))
                    .findFirst().orElse(new Vendas(BigDecimal.ZERO, mesAnoReferencia));

            return getSalarioBaseFuncionario(mesAnoReferencia)
                    .add(cargo.getBeneficios().multiply(vendaDoMes.getValorVenda()));

        }
        return getSalarioBaseFuncionario(mesAnoReferencia);
    }
}
