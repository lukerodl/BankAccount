import java.util.Date;

public class Operacao {
    private Date data;
    private double valor;
    private String tipo;

    public Operacao(Date data, double valor, String tipo) {
        this.data = data;
        this.valor = valor;
        this.tipo = tipo;
    }

    public Date getData() {
        return data;
    }

    public double getValor() {
        return valor;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return String.format("%s\t%s\t%.2f", data, tipo, valor);
    }
}

