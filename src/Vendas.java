import java.math.BigDecimal;
import java.time.YearMonth;

public class Vendas {
    private BigDecimal valorVenda;
    private YearMonth mesVenda;

    public Vendas(BigDecimal valorVenda, YearMonth mesVenda) {
        this.valorVenda = valorVenda;
        this.mesVenda = mesVenda;
    }

    public BigDecimal getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(BigDecimal valorVenda) {
        this.valorVenda = valorVenda;
    }

    public YearMonth getMesVenda() {
        return mesVenda;
    }

    public void setMesVenda(YearMonth mesVenda) {
        this.mesVenda = mesVenda;
    }
}
