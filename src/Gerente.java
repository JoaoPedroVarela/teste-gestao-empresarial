import java.math.BigDecimal;

public class Gerente extends Cargo{
    public Gerente() {
        super("Gerente",
                BigDecimal.valueOf(20000),
                BigDecimal.valueOf(3000),
                BigDecimal.valueOf(0));
    }
}
