import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Usuario {
    private String nome;
    private String senha;

    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public static Usuario criarNovoUsuario() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Já possui cadastro? (Sim/Não)");
        String resposta = scanner.nextLine().trim().toLowerCase();

        if ("sim".equals(resposta)) {
            System.out.print("Nome: ");
            String nome = scanner.nextLine().trim();
            System.out.print("Senha: ");
            String senha = scanner.nextLine().trim();

            if (validarUsuario(nome, senha)) {
                System.out.println("Bem-vindo, " + nome + "!");
                return new Usuario(nome, senha);
            } else {
                System.out.println("Nome de usuário ou senha incorretos. Tente novamente.");
                return criarNovoUsuario();
            }
        } else if ("não".equals(resposta) || "nao".equals(resposta)) {
            System.out.print("Digite seu nome: ");
            String nome = scanner.nextLine().trim();
            System.out.print("Digite sua senha: ");
            String senha = scanner.nextLine().trim();

            // Salvar novo usuário no arquivo
            salvarUsuario(nome, senha);

            System.out.println("Cadastro criado.");
            return new Usuario(nome, senha);
        } else {
            System.out.println("Resposta inválida. Por favor, responda 'Sim' ou 'Não'.");
            return criarNovoUsuario();
        }
    }

    private static boolean validarUsuario(String nome, String senha) {
        Map<String, String> usuarios = carregarUsuariosDoArquivo();

        return usuarios.containsKey(nome) && usuarios.get(nome).equals(senha);
    }

    private static void salvarUsuario(String nome, String senha) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("usuarios.csv", true))) {
            writer.println(nome + "," + senha);
        } catch (IOException e) {
            System.err.println("Erro ao salvar o usuário no arquivo.");
            e.printStackTrace();
        }
    }

    private static Map<String, String> carregarUsuariosDoArquivo() {
        Map<String, String> usuarios = new HashMap<>();
        File arquivo = new File("usuarios.csv");

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length == 2) {
                    usuarios.put(partes[0], partes[1]);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar os usuários do arquivo.");
            e.printStackTrace();
        }

        return usuarios;
    }

    public static void main(String[] args) {
        Usuario usuario = criarNovoUsuario();
    }
}
