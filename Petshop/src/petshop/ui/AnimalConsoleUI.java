package petshop.ui;

import java.util.List;
import java.util.Scanner;

import petshop.model.Animal;
import petshop.util.ValidadorEntrada;

public class AnimalConsoleUI {
    final String CABECALHO = "===";
    final String SEPARADOR = "-----------------------------------";
    private Scanner leia = new Scanner(System.in);

    public String solicitarNomeAnimal() {
        System.out.println("Nome do animal: ");
        return leia.nextLine();
    }

    public float solicitarPeso() {
        return ValidadorEntrada.lerFloatPositivo(leia, "Peso do animal: ");
    }

    public float solicitarAltura() {
        return ValidadorEntrada.lerFloatPositivo(leia, "Altura do animal: ");
    }

    public String solicitarCpfDono() {
        return ValidadorEntrada.lerCpfValido(leia);
    }

    public int escolherAnimal(List<Animal> animaisEncontrados) {
        for (int i = 0; i < animaisEncontrados.size(); i++) {
            System.out.println((i + 1) + " — Dono CPF: " + animaisEncontrados.get(i).getCpfDono());
        }
        return ValidadorEntrada.lerInteiroValido(leia, 1, animaisEncontrados.size());
    }

    public void mostrarCabecalho(String titulo) {
        System.out.println(CABECALHO + " " + titulo + " " + CABECALHO);
        System.out.println(SEPARADOR);
    }

    public void mostrarMensagem(String msg) {
        System.out.println(msg);
    }

    public void mostrarDetalhesAnimal(Animal animal) {
        System.out.println(animal.toStringDetalhado());
    }
}