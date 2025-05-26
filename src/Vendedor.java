import java.math.BigDecimal;
import java.util.List;

public class Vendedor extends Cargo{
    private List<Vendas> listaVendas;

    public Vendedor(List<Vendas> listaVendas) {
        super("Vendedor",
                BigDecimal.valueOf(12000),
                BigDecimal.valueOf(1800),
                BigDecimal.valueOf(30));
        this.listaVendas = listaVendas;
    }

    public List<Vendas> getListaVendas() {
        return listaVendas;
    }

    public void setListaVendas(List<Vendas> listaVendas) {
        this.listaVendas = listaVendas;
    }
}
