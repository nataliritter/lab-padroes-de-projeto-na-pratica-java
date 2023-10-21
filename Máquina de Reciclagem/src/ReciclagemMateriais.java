import java.util.Scanner;

public class ReciclagemMateriais {
    private double precoPapel = 1.00;
    private double precoAluminio = 4.00;
    private double precoCobre = 8.00;
    private double precoPlastico = 2.00;

    public double calcularValor(String material, double kg) {
        double valorTotal = 0.0;

        if (material.equals("papel")) {
            valorTotal = precoPapel * kg;
        } else if (material.equals("aluminio")) {
            valorTotal = precoAluminio * kg;
        } else if (material.equals("cobre")) {
            valorTotal = precoCobre * kg;
        } else if (material.equals("plastico")) {
            valorTotal = precoPlastico * kg;
        }

        return valorTotal;
    }

    public void processarVenda() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o tipo de material (papel, aluminio, cobre, plastico): ");
        String material = scanner.next();
        System.out.print("Digite a quantidade em kg: ");
        double kg = scanner.nextDouble();

        double valorTotal = calcularValor(material, kg);

        System.out.println("O valor total do ticket será de R$" + valorTotal + " e ele está sendo impresso, aguarde.");
        scanner.close();
    }

    public static void main(String[] args) {
        ReciclagemMateriais reciclagem = new ReciclagemMateriais();
        reciclagem.processarVenda();
    }
}