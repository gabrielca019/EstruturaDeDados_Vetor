package model;

import java.util.Arrays;

public class Vetor {

	private Cliente[] clientes = new Cliente[5];
	private int totalClientes = 0; 
	
	public int getTamanhoVetor() { 
		return clientes.length;
	}
	
	private void aumentarEspacoVetor() {
		if (totalClientes == getTamanhoVetor()) {
			Cliente[] novoVetor = Arrays.copyOf(clientes, clientes.length * 2);
			clientes = novoVetor;
		}
	}
	
	public void liberarEspacoVetor() {
		if ((totalClientes < (clientes.length * 0.25)) && (clientes.length != 5)) {
			Cliente[] novoVetor = Arrays.copyOf(clientes, clientes.length / 2);
			clientes = novoVetor;
		}
	}
		
	public int pegaTotalClientes() {
		return totalClientes;
	}
	
	private boolean posicaoValidaInsercao(int posicao) {
		return (posicao>=0) && (posicao <= totalClientes);
	}
	
	private boolean posicaoOcupada(int posicao) {
		return (posicao >= 0) && (posicao < totalClientes);
	}
	
	public Cliente pegar(int posicao) {
		if (!posicaoOcupada(posicao)) {
			throw new IllegalArgumentException("Posicao invalida");
		}
		return clientes[posicao];
	}
	
	public boolean contem(Cliente clienteBuscado) {
		for (int i=0; i<totalClientes; i++) {
			if (clienteBuscado.equals(clientes[i])) {
				return true;
			}
		}
		return false;
	}
	
	public void adicionar(Cliente novoCliente) {
		aumentarEspacoVetor();
		clientes[totalClientes] = novoCliente;
		totalClientes++;
	}
	
	public void adicionar(Cliente novoCliente, int posicaoAdicionar) {
		aumentarEspacoVetor();
		if (!posicaoValidaInsercao(posicaoAdicionar)){
			throw new IllegalArgumentException("Posicao invalida");
		}
		
		for (int i=totalClientes; i>=posicaoAdicionar; i--) {
			clientes[i+1] = clientes[i];
		}
		clientes[posicaoAdicionar] = novoCliente;
		totalClientes++;
	}
	
	public void remover(int posicaoRemover) {
		if (!posicaoOcupada(posicaoRemover)) {
			throw new IllegalArgumentException("Posicao invalida");
		}
		
		for (int i=posicaoRemover; i < totalClientes; i++) {
			clientes[i] = clientes[i+1];
		}
		
		totalClientes--;
		liberarEspacoVetor();
	}
	
	public void removerUltimaPosicao() {
		if(!posicaoOcupada(totalClientes - 1)) { 
			throw new IllegalArgumentException("Posicao invalida");
		}
		
		clientes[totalClientes - 1] = null;
		totalClientes--;
		liberarEspacoVetor();
	}
	
	public void removerPrimeiraPosicao() {
		if(!posicaoOcupada(0)) {
			throw new IllegalArgumentException("Posicao invalida");
		}
		
		for (int i = 0; i < totalClientes; i++) {
			clientes[i] = clientes[i+1];
		}
		
		totalClientes--;
		liberarEspacoVetor();
	}
	
	public void removerTodasPosicoes() {
		for (int i = 0; i < totalClientes; i++) {
			clientes[i] = null;
		}
		
		totalClientes = 0;
		liberarEspacoVetor();
	}
	
}