import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe abstrata que representa uma Conta bancária.
 */
public abstract class Conta {
    protected int numeroConta;
    protected String nomeCorrentista;
    protected String cpfCorrentista;
    protected List<Operacao> operacoes = new ArrayList<>();
    protected double saldo;

    /**
     * Construtor para inicializar uma nova conta.
     * @param numeroConta Número da conta.
     * @param nomeCorrentista Nome do correntista.
     * @param cpfCorrentista CPF do correntista.
     */
    public Conta(int numeroConta, String nomeCorrentista, String cpfCorrentista) {
        this.numeroConta = numeroConta;
        this.nomeCorrentista = nomeCorrentista;
        this.cpfCorrentista = cpfCorrentista;
        this.operacoes = new ArrayList<>();
        this.saldo = 0.0;
    }

    /**
     * Deposita um valor na conta.
     * @param valor Valor do depósito.
     */
    public abstract void depositar(double valor);

    /**
     * Realiza um saque da conta.
     * @param valor Valor do saque.
     * @throws SaldoInsuficienteException se o saldo for insuficiente.
     */
    public abstract void sacar(double valor) throws SaldoInsuficienteException;

    /**
     * Registra uma operação na lista de operações da conta.
     * @param tipo Tipo da operação.
     * @param valor Valor da operação.
     */
    protected void registrarOperacao(String tipo, double valor) {
        Operacao operacao = new Operacao(new Date(), valor, tipo);
        operacoes.add(operacao);
    }

    /**
     * Exibe o extrato completo da conta.
     */
    public void consultarExtrato() {
        System.out.println("Extrato da conta " + numeroConta + " de " + nomeCorrentista);
        double saldoAtual = 0.0;
        for (Operacao op : operacoes) {
            saldoAtual += (op.getTipo().equals("Depósito") || op.getTipo().equals("PIX In")) ? op.getValor() : -op.getValor();
            System.out.println(op + "\tSaldo: " + saldoAtual);
        }
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public String getCpfCorrentista() {
        return cpfCorrentista;
    }

    public double getSaldo() {
        return saldo;
    }
}
