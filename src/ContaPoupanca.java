/**
 * Classe que representa uma Conta Poupança e implementa a interface Remunerada.
 */
public class ContaPoupanca extends Conta implements Remunerada {
    public ContaPoupanca(int numeroConta, String nomeCorrentista, String cpfCorrentista) {
        super(numeroConta, nomeCorrentista, cpfCorrentista);
    }

    @Override
    public void depositar(double valor) {
        saldo += valor;
        registrarOperacao("Depósito", valor);
    }

    @Override
    public void sacar(double valor) throws SaldoInsuficienteException {
        if (saldo < valor) {
            throw new SaldoInsuficienteException("Saldo insuficiente para saque");
        }
        saldo -= valor;
        registrarOperacao("Saque", valor);
    }

    @Override
    public void aplicarCorrecao(double taxa) {
        double valorCorrecao = saldo * (taxa / 100);
        saldo += valorCorrecao;
    }
}

