import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe principal do sistema bancário Banco Object.
 * Contém métodos estáticos para gerenciamento de contas e operações.
 */
public class Banco {
    public static List<Conta> contas = new ArrayList<>();
    public static List<String> cpfsPix = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Cria uma conta corrente no banco.
     */
    public static void criarContaCorrente() {
        System.out.print("Número da conta: ");
        int numeroConta = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nome do correntista: ");
        String nome = scanner.nextLine();
        System.out.print("CPF do correntista: ");
        String cpf = scanner.nextLine();

        ContaCorrente novaConta = new ContaCorrente(numeroConta, nome, cpf);
        contas.add(novaConta);
        System.out.println("Conta corrente criada com sucesso!");
    }

    /**
     * Cria uma conta poupança no banco.
     */
    public static void criarContaPoupanca() {
        System.out.print("Número da conta: ");
        int numeroConta = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nome do correntista: ");
        String nome = scanner.nextLine();
        System.out.print("CPF do correntista: ");
        String cpf = scanner.nextLine();

        ContaPoupanca novaConta = new ContaPoupanca(numeroConta, nome, cpf);
        contas.add(novaConta);
        System.out.println("Conta poupança criada com sucesso!");
    }

    /**
     * Realiza um depósito em uma conta.
     */
    public static void efetuarDeposito() {
        System.out.print("Número da conta para depósito: ");
        int numeroConta = scanner.nextInt();
        System.out.print("Valor do depósito: ");
        double valor = scanner.nextDouble();

        for (Conta conta : contas) {
            if (conta.getNumeroConta() == numeroConta) {
                conta.depositar(valor);
                System.out.println("Depósito realizado com sucesso!");
                return;
            }
        }
        System.out.println("Conta não encontrada.");
    }

    /**
     * Realiza um saque em uma conta.
     */
    public static void efetuarSaque() {
        System.out.print("Número da conta para saque: ");
        int numeroConta = scanner.nextInt();
        System.out.print("Valor do saque: ");
        double valor = scanner.nextDouble();

        for (Conta conta : contas) {
            if (conta.getNumeroConta() == numeroConta) {
                try {
                    conta.sacar(valor);
                    System.out.println("Saque realizado com sucesso!");
                } catch (SaldoInsuficienteException e) {
                    System.out.println("Erro: " + e.getMessage());
                }
                return;
            }
        }
        System.out.println("Conta não encontrada.");
    }

    /**
     * Aplica uma correção monetária a todas as contas poupança.
     */
    public static void aplicarCorrecao() {
        System.out.print("Taxa de correção (%): ");
        double taxa = scanner.nextDouble();

        for (Conta conta : contas) {
            if (conta instanceof ContaPoupanca) {
                ((ContaPoupanca) conta).aplicarCorrecao(taxa);
            }
        }
        System.out.println("Correção aplicada às contas poupança.");
    }

    /**
     * Cadastra um CPF para permitir transações via PIX.
     */
    public static void cadastrarPix() {
        System.out.print("CPF do correntista para cadastrar no PIX: ");
        String cpf = scanner.next();

        for (Conta conta : contas) {
            if (conta instanceof ContaCorrente && conta.getCpfCorrentista().equals(cpf)) {
                cpfsPix.add(cpf);
                System.out.println("CPF cadastrado para PIX com sucesso.");
                return;
            }
        }
        System.out.println("Conta corrente não encontrada para o CPF fornecido.");
    }

    /**
     * Realiza uma transação PIX entre contas correntes.
     */
    public static void efetuarPix() {
        System.out.print("CPF do correntista que enviará o PIX: ");
        String cpfOrigem = scanner.next();
        System.out.print("CPF do destinatário do PIX: ");
        String cpfDestino = scanner.next();
        System.out.print("Valor do PIX: ");
        double valor = scanner.nextDouble();

        for (Conta conta : contas) {
            if (conta instanceof ContaCorrente && conta.getCpfCorrentista().equals(cpfOrigem) && cpfsPix.contains(cpfOrigem)) {
                ((ContaCorrente) conta).efetuarPix(cpfDestino, valor);
                System.out.println("PIX efetuado com sucesso!");
                return;
            }
        }
        System.out.println("Erro ao efetuar PIX. Verifique se o CPF está cadastrado para PIX e se há saldo suficiente.");
    }

    /**
     * Consulta e exibe o extrato de uma conta.
     */
    public static void consultarExtrato() {
        System.out.print("Número da conta para consultar o extrato: ");
        int numeroConta = scanner.nextInt();

        for (Conta conta : contas) {
            if (conta.getNumeroConta() == numeroConta) {
                conta.consultarExtrato();
                return;
            }
        }
        System.out.println("Conta não encontrada.");
    }

    /**
     * Método principal para execução do sistema bancário.
     * Exibe o menu de operações e processa as seleções do usuário.
     */
    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n===== Menu Banco Object =====");
            System.out.println("1. Criar Conta Corrente");
            System.out.println("2. Criar Conta Poupança");
            System.out.println("3. Efetuar Depósito");
            System.out.println("4. Efetuar Saque");
            System.out.println("5. Aplicar Correção (Poupança)");
            System.out.println("6. Cadastrar PIX");
            System.out.println("7. Efetuar PIX");
            System.out.println("8. Consultar Extrato");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> criarContaCorrente();
                case 2 -> criarContaPoupanca();
                case 3 -> efetuarDeposito();
                case 4 -> efetuarSaque();
                case 5 -> aplicarCorrecao();
                case 6 -> cadastrarPix();
                case 7 -> efetuarPix();
                case 8 -> consultarExtrato();
                case 0 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}

