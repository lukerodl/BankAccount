public interface Pix {
    void cadastrarPix(String cpf);
    void efetuarPix(String cpfDestino, double valor) throws SaldoInsuficienteException;
    void receberPix(String cpfOrigem, double valor);
}

