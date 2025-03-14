package br.com.vkl;

import br.com.vkl.dao.ClienteMapDAO;
import br.com.vkl.dao.IClienteDAO;
import br.com.vkl.domain.Cliente;
import br.com.vkl.domain.Validador;
import br.com.vkl.swing.TelaPrincipal;

import javax.swing.*;

public class App {

    private static IClienteDAO iClienteDAO;

    public static void main (String[] args){
       iClienteDAO = new ClienteMapDAO();

    }

    private static void inicio() {
        String opcao = criarOpcao();
        while (!isOpcaoValida(opcao)){
            if (opcao == null){
                sair();
            }
            JOptionPane.showMessageDialog(null, "Opção Invalida.", "Erro", JOptionPane.INFORMATION_MESSAGE);
            opcao = criarOpcao();
        }
        while (isOpcaoValida(opcao)){
            switch (opcao){
                case "1" -> cadastrar();
                case "2" -> consultar();
                case "3" -> apagar();
                case "4" -> alterar();
                case "5" -> sair();
            }
        }
    }

    private static void sair() {
        JOptionPane.showMessageDialog(null, "Saindo... Até mais.", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private static void alterar() {
        String dados = dadosInput("Digite dados dos clientes separados por virgula, conforme o exemplo: Nome, CPF, Telefone, Endereço, Numero do endereço, Cidade, Estado");
        String[] dadosSeparados;
        if (dados != null){
            dadosSeparados = dados.split(",");
            if (dadosSeparados.length == 7){
                String nome = Validador.validarGenerico(dadosSeparados[0]);
                String cpf = Validador.validarCpf(dadosSeparados[1]);
                String tel = Validador.validarTelefone(dadosSeparados[2]);
                String end = Validador.validarGenerico(dadosSeparados[3]);
                String num = Validador.validarNumero(dadosSeparados[4]);
                String cid = Validador.validarGenerico(dadosSeparados[5]);
                String est = Validador.validarGenerico(dadosSeparados[6]);
                Cliente cliente = new Cliente(nome,cpf,tel,end,num,cid,est);
                Boolean isCadastrado = iClienteDAO.alterar(cliente);
                if (isCadastrado){
                    JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso.", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(null, "Cliente não existe.", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                }
            }else {
                JOptionPane.showMessageDialog(null, "Informações invalidas.", "Erro", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        inicio();
    }

    private static void apagar() {
        String dados = dadosInput("Digite CPF para consultar.");
        if (dados != null){
            if (!dados.isEmpty() && dados.matches("^\\d+$")){
                if (Validador.validarCpf(dados) != null){
                    JOptionPane.showMessageDialog(null, "Cliente apagado com sucesso.", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                    iClienteDAO.excluir(Long.valueOf(dados));
                }else {
                    JOptionPane.showMessageDialog(null, "Cliente não foi encontrado ou o CPF esta incorreto.", "Erro", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }else {
                JOptionPane.showMessageDialog(null, "Informações invalidas.", "Erro", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        inicio();
    }

    private static void consultar() {
        String dados = dadosInput("Digite CPF para consultar.");
        if (dados != null){
            if (!dados.isEmpty() && dados.matches("^\\d+$")){
                Cliente cliente = iClienteDAO.consultar(Long.parseLong(dados));
                if (Validador.validarCpf(dados) != null && cliente != null){
                    JOptionPane.showMessageDialog(null, "Cliente encontrado :" + cliente, "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(null, "Cliente não foi encontrado ou o CPF esta incorreto.", "Erro", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }else {
                JOptionPane.showMessageDialog(null, "Informações invalidas.", "Erro", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        inicio();
    }

    private static String dadosInput(String s) {
        return JOptionPane.showInputDialog(null, s, "Cadastro", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void cadastrar() {
        String dados = dadosInput("Digite dados dos clientes separados por virgula, conforme o exemplo: Nome, CPF, Telefone, Endereço, Numero do endereço, Cidade, Estado");
        String[] dadosSeparados;
        if (dados != null){
            dadosSeparados = dados.split(",");
            if (dadosSeparados.length == 7){
                String nome = Validador.validarGenerico(dadosSeparados[0]);
                String cpf = Validador.validarCpf(dadosSeparados[1]);
                if (cpf == null){
                    JOptionPane.showMessageDialog(null, "Cpf incorreto.", "Erro", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                String tel = Validador.validarTelefone(dadosSeparados[2]);
                String end = Validador.validarGenerico(dadosSeparados[3]);
                String num = Validador.validarNumero(dadosSeparados[4]);
                String cid = Validador.validarGenerico(dadosSeparados[5]);
                String est = Validador.validarGenerico(dadosSeparados[6]);
                Cliente cliente = new Cliente(nome,cpf,tel,end,num,cid,est);
                Boolean isCadastrado = iClienteDAO.cadastrar(cliente);
                if (isCadastrado){
                    JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso.", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(null, "Cliente ja esta cadastrado.", "Erro", JOptionPane.INFORMATION_MESSAGE);
                }
        }else {
                JOptionPane.showMessageDialog(null, "Informações invalidas.", "Erro", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        inicio();
    }

    private static String criarOpcao() {
        return JOptionPane.showInputDialog(null, "Digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração ou 5 para sair.", "Cadastro - Selecione uma opção.", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     *
     * @param opcao
     * Troca o if por um check de null e um regex de 1 a 5, não é o melhor se queremos dinamismo, porem-todavia-entretanto,
     * e um projeto simples de escopo simples, então preferi simplificar o maximo possivel
     * @return boolean
     */
    private static boolean isOpcaoValida(String opcao) {
        return opcao != null && opcao.trim().matches("[1-5]");
    }
}
