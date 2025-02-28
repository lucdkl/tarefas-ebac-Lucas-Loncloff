package br.com.vkl;
import java.util.*;

public class Input {
    String comando;
    Scanner scanner = new Scanner(System.in);
    Set<Pessoa> lista = new LinkedHashSet<>();


    public static void main(String[] args) {
        Input input = new Input();
        input.menu();
    }

    private void menu() {
        do {
            System.out.println("Selecione uma opcao.");
            System.out.println("1.Adicionar Pessoas");
            System.out.println("2.Ordenar e listar");
            System.out.println("Sair");
            this.comando = scanner.nextLine();
            switch (comando) {
                case "1":
                    adicionarInfo();
                    break;
                case "2":
                    listar();
                    break;
                case "Sair":
                    System.out.println("Saindo... Até mais!");
                    return;
                default:
                    if (!comando.equalsIgnoreCase("Sair")) {
                        System.out.println("Erro.");
                    }
                    break;

            }

        }while (!comando.equalsIgnoreCase("Sair")) ;
    }

    private void adicionarInfo(){
        System.out.println("**** Selecione o tipo de pessoa ****");
        System.out.println("1.Pessoa Fisica");
        System.out.println("2.Pessoa Juridica");
        short escolha;
        try {
            escolha = scanner.nextShort();
            scanner.nextLine();
        }catch (InputMismatchException e){
            System.out.println("Erro, tente novamente.");
            scanner.nextLine();
            return;
        }
        criarPessoa(escolha);
    }

    private void criarPessoa(short escolha) {
        System.out.println("**** Adicione as informações, separando as por ';' ****");
        System.out.println("**** Informações:Numero do Documento;Nome;Idade ****");
        String line = scanner.nextLine();
        String[] rawInfo = line.split(";");
        if (rawInfo.length < 3){
            System.out.println("**** Informações erradas. ****");
            System.out.println("**** Pressione Enter para continuar ****");
            scanner.nextLine();
            return;
        }

        Long documento;
        String nome = rawInfo[1];
        Short idade;

        try {
            documento = Long.valueOf(rawInfo[0]);
        }catch (NumberFormatException e){
            System.out.println("**** Documento errado. ****");
            System.out.println("**** Pressione Enter para continuar ****");
            scanner.nextLine();
            return;
        }

        try {
            idade = Short.valueOf(rawInfo[2]);
        }catch (NumberFormatException e){
            System.out.println("**** Idade errada. ****");
            System.out.println("**** Pressione Enter para continuar ****");
            scanner.nextLine();
            return;
        }

        switch (escolha){
            case 1 -> atributoPessoaFisica(documento, nome, idade);
            case 2 -> atributoPessoaJuridica(documento, nome, idade);
        }
    }

    private void atributoPessoaFisica(Long documento, String nome, Short idade) {
        PessoaFisica pf = new PessoaFisica();
        pf.setCpf(documento);
        pf.setNome(nome);
        pf.setIdade(idade);
        lista.add(pf);
    }

    private void atributoPessoaJuridica(Long documento, String nome, Short idade){
        PessoaJuridica pj = new PessoaJuridica();
        pj.setCnpj(documento);
        pj.setNome(nome);
        pj.setIdade(idade);
        lista.add(pj);
    }

    private void listar() {
        if (!lista.isEmpty()){
            Map<String, List<Pessoa>> mapaPessoa = new HashMap<>();
            for (Pessoa pessoa : lista){
                String tipo = pessoa.getTipo();
                mapaPessoa.putIfAbsent(tipo, new ArrayList<>());
                mapaPessoa.get(tipo).add(pessoa);
            }
            for (Map.Entry<String, List<Pessoa>> entry : mapaPessoa.entrySet()){
                System.out.println("**** Tipo: " + entry.getKey() + " ****");
                for (Pessoa p : entry.getValue()){
                    System.out.println("Nome: " + p.getNome() + " Idade: " + p.getIdade() + " Numero Documento: " + p.getDoc());
                }
                System.out.println();
            }
        }else {
            System.out.println("**** Sem Dados ****");
        }
        System.out.println("**** Pressione Enter para continuar ****");
        scanner.nextLine();
    }
}

