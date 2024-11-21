/**
 * Classe que representa uma Conta Corrente e implementa as operações de Pix.
 */
public class ContaCorrente extends Conta implements Pix {
    public ContaCorrente(int numeroConta, String nomeCorrentista, String cpfCorrentista) {
        super(numeroConta, nomeCorrentista, cpfCorrentista);
    }

    /**
     * Depositar na conta corrente
     */
    @Override
    public void depositar(double valor) {
        saldo += valor;
        registrarOperacao("Depósito", valor);
    }

    /**
     * Sacar na conta corrente
     */
    @Override
    public void sacar(double valor) throws SaldoInsuficienteException {
        if (saldo < valor) {
            throw new SaldoInsuficienteException("Saldo insuficiente para saque");
        }
        saldo -= valor;
        registrarOperacao("Saque", valor);
    }

    /**
     * Cadastrar Pix na conta corrente
     */
    @Override
    public void cadastrarPix(String cpf) {
        Banco.cpfsPix.add(cpf);
    }

    /**
     * Efetuar Pix na conta corrente (levando em consideração se tem saldo ou não na conta)
     */

    @Override
    public void efetuarPix(String cpfDestino, double valor) throws SaldoInsuficienteException {
        if (saldo < valor) {
            throw new SaldoInsuficienteException("Saldo insuficiente para realizar o PIX");
        }
        for (Conta conta : Banco.contas) {
            if (conta instanceof ContaCorrente && conta.getCpfCorrentista().equals(cpfDestino) && Banco.cpfsPix.contains(cpfDestino)) {
                this.sacar(valor);
                ((ContaCorrente) conta).receberPix(this.cpfCorrentista, valor);
                break;
            }
        }
    }

    @Override
    public void receberPix(String cpfOrigem, double valor) {
        saldo += valor;
        registrarOperacao("PIX In", valor);
    }
}

