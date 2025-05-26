import java.math.BigDecimal;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CalculaClass {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/yyyy");

    public static BigDecimal calculaValorSalarioBaseTotal(List<Funcionario> listaFuncionarios, String mesAno) {

        if (listaFuncionarios == null || listaFuncionarios.isEmpty() || mesAno == null || mesAno.trim().isEmpty()) {
            return BigDecimal.ZERO;
        }

        BigDecimal valorTotal = BigDecimal.ZERO;
        YearMonth referencia = YearMonth.parse(mesAno, FORMATTER);

        for (Funcionario funcionario : listaFuncionarios) {
            if (funcionario != null) {
                valorTotal = valorTotal.add(funcionario.getSalarioBaseFuncionario(referencia));
            }
        }
        return valorTotal;
    }

    public static BigDecimal calculaValorSalarioTotalComBeneficio(List<Funcionario> listaFuncionarios, String mesAno) {

        if (listaFuncionarios == null || listaFuncionarios.isEmpty() || mesAno == null || mesAno.trim().isEmpty()) {
            return BigDecimal.ZERO;
        }

        BigDecimal valorTotal = BigDecimal.ZERO;
        YearMonth referencia = YearMonth.parse(mesAno, FORMATTER);

        for (Funcionario funcionario : listaFuncionarios) {
            if (funcionario != null) {
                valorTotal = valorTotal.add(funcionario.getSalarioBaseFuncionarioComBeneficios(referencia));
            }
        }
        return valorTotal;
    }

    public static BigDecimal calculaValorBeneficiosTotal(List<Funcionario> listaFuncionariosComBeneficio, String mesAno) {

        if (listaFuncionariosComBeneficio == null || listaFuncionariosComBeneficio.isEmpty() || mesAno == null || mesAno.trim().isEmpty()) {
            return BigDecimal.ZERO;
        }

        BigDecimal valorTotal = BigDecimal.ZERO;
        YearMonth referencia = YearMonth.parse(mesAno, FORMATTER);

        for (Funcionario funcionario : listaFuncionariosComBeneficio) {
            if (funcionario != null && funcionario.getCargo() != null) {
                if (funcionario.getCargo() instanceof Secretario) {
                    valorTotal = valorTotal.add(funcionario.getSalarioBaseFuncionario(referencia)
                            .multiply(funcionario.getCargo().getBeneficios()));
                }

                if (funcionario.getCargo() instanceof Vendedor) {
                    Vendedor vendedor = (Vendedor) funcionario.getCargo();
                    if (vendedor.getListaVendas() != null) {
                        Vendas vendaDoMes = vendedor.getListaVendas()
                                .stream()
                                .filter(venda -> venda.getMesVenda().equals(referencia))
                                .findFirst()
                                .orElse(new Vendas(BigDecimal.ZERO, referencia));

                        valorTotal = valorTotal.add(funcionario.getCargo().getBeneficios().multiply(vendaDoMes.getValorVenda()));
                    }
                }
            }
        }
        return valorTotal;
    }

    public static Funcionario funcionarioComMaiorGanho(List<Funcionario> listaFuncionarios, String mesAno) {

        if (listaFuncionarios == null || listaFuncionarios.isEmpty() || mesAno == null || mesAno.trim().isEmpty()) {
            return null;
        }

        Funcionario funcionarioComMaiorSalario = null;
        BigDecimal maiorSalario = BigDecimal.ZERO;
        YearMonth referencia = YearMonth.parse(mesAno, FORMATTER);

        for (Funcionario funcionario : listaFuncionarios) {
            if (funcionario != null) {
                BigDecimal salarioAtual = funcionario.getSalarioBaseFuncionarioComBeneficios(referencia);
                if (salarioAtual.compareTo(maiorSalario) > 0) {
                    maiorSalario = salarioAtual;
                    funcionarioComMaiorSalario = funcionario;
                }
            }
        }
        return funcionarioComMaiorSalario;
    }

    public static String funcionarioComMaiorBeneficio(List<Funcionario> listaFuncionarios, String mesAno) {

        if (listaFuncionarios == null || listaFuncionarios.isEmpty() || mesAno == null || mesAno.trim().isEmpty()) {
            return null;
        }

        Funcionario funcionarioComMaiorBeneficio = null;
        BigDecimal maiorBeneficio = BigDecimal.ZERO;
        YearMonth referencia = YearMonth.parse(mesAno, FORMATTER);

        for (Funcionario funcionario : listaFuncionarios) {
            if (funcionario != null && funcionario.getCargo() != null) {
                BigDecimal beneficioAtual = BigDecimal.ZERO;

                if (funcionario.getCargo() instanceof Secretario) {
                    beneficioAtual = funcionario.getCargo().getSalario()
                            .multiply(funcionario.getCargo().getBeneficios());
                }

                if (funcionario.getCargo() instanceof Vendedor) {
                    Vendedor vendedor = (Vendedor) funcionario.getCargo();
                    if (vendedor.getListaVendas() != null) {
                        Vendas vendaDoMes = vendedor.getListaVendas()
                                .stream()
                                .filter(venda -> venda.getMesVenda().equals(referencia))
                                .findFirst()
                                .orElse(new Vendas(BigDecimal.ZERO, referencia));

                        beneficioAtual = funcionario.getCargo().getBeneficios().multiply(vendaDoMes.getValorVenda());
                    }
                }

                if (beneficioAtual.compareTo(maiorBeneficio) > 0) {
                    maiorBeneficio = beneficioAtual;
                    funcionarioComMaiorBeneficio = funcionario;
                }
            }
        }
        if (funcionarioComMaiorBeneficio != null) {
            return funcionarioComMaiorBeneficio.getNome();
        } else {
            return null;
        }
    }

    public static Funcionario vendedorQueMaisVendeu(List<Funcionario> listaVendedores, String mesAno) {

        if (listaVendedores == null || listaVendedores.isEmpty() || mesAno == null || mesAno.trim().isEmpty()) {
            return null;
        }

        Funcionario vendedorComMaisVendas = null;
        BigDecimal maiorVenda = BigDecimal.ZERO;
        YearMonth referencia = YearMonth.parse(mesAno, FORMATTER);

        for (Funcionario vendedor : listaVendedores) {
            if (vendedor != null && vendedor.getCargo() != null) {
                Vendedor vendedorCargo = (Vendedor) vendedor.getCargo();
                if (vendedorCargo.getListaVendas() != null) {
                    Vendas vendaDoMes = vendedorCargo.getListaVendas()
                            .stream()
                            .filter(venda -> venda.getMesVenda().equals(referencia))
                            .findFirst()
                            .orElse(new Vendas(BigDecimal.ZERO, referencia));

                    BigDecimal valorVendaAtual = vendaDoMes.getValorVenda();

                    if (valorVendaAtual.compareTo(maiorVenda) > 0) {
                        maiorVenda = valorVendaAtual;
                        vendedorComMaisVendas = vendedor;
                    }
                }
            }
        }
        return vendedorComMaisVendas;
    }
}