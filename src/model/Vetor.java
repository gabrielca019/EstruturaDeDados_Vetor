package model;

import java.util.Arrays;

public class Vetor {

	private Cliente[] vetor = new Cliente[5];
	private int totalClientes = 0; 
	
	public int getTamanhoVetor() { 
		return vetor.length;
	}
	
	public boolean verificarVetorCheio() {
		return totalClientes == getTamanhoVetor();
	}
	
	public boolean verificarVetorPoucosElementos() {
		return totalClientes < (vetor.length * 0.25);
	}
	
	public boolean verificarVetorMenorTamanhoPossivel() {
		return vetor.length != 5;
	}
	
	private void aumentarEspacoVetor() {
		if (verificarVetorCheio()) 
			vetor = Arrays.copyOf(vetor, vetor.length * 2);
	}
	
	private void liberarEspacoVetor() {
		if (verificarVetorPoucosElementos() && verificarVetorMenorTamanhoPossivel()) {
			Cliente[] novoVetor = Arrays.copyOf(vetor, vetor.length / 2);
			vetor = novoVetor;
		}
	}
		
	public int getTotalClientes() {
		return totalClientes;
	}
	
	private boolean posicaoValidaInsercao(int posicao) {
		return (posicao>=0) && (posicao <= totalClientes);
	}
	
	private boolean posicaoOcupada(int posicao) {
		return (posicao >= 0) && (posicao < totalClientes);
	}
	
	public Cliente getClientePosicaoEspecifica(int posicao) {
		if (!posicaoOcupada(posicao)) 
			throw new IllegalArgumentException("Posicao invalida");
		
		return vetor[posicao];
	}
	
	public boolean contem(Cliente clienteBuscado) {
		for (int i=0; i<totalClientes; i++) {
			if (clienteBuscado.equals(vetor[i])) 
				return true;	
		}
		return false;
	}
	
	public void adicionar(Cliente novoCliente) {
		aumentarEspacoVetor();
		vetor[totalClientes] = novoCliente;
		totalClientes++;
	}
	
	public void adicionarPosicaoEspecifica(Cliente novoCliente, int posicaoAdicionar) {
		aumentarEspacoVetor();
		if (!posicaoValidaInsercao(posicaoAdicionar))
			throw new IllegalArgumentException("Posicao invalida");
		
		for (int i=totalClientes; i>=posicaoAdicionar; i--) {
			vetor[i+1] = vetor[i];
		}
		vetor[posicaoAdicionar] = novoCliente;
		totalClientes++;
	}
	
	public void removerPosicaoEspecifica(int posicaoRemover) {
		if (!posicaoOcupada(posicaoRemover))
			throw new IllegalArgumentException("Posicao invalida");
	
		for (int i=posicaoRemover; i < totalClientes; i++) 
			vetor[i] = vetor[i+1];
		
		totalClientes--;
		liberarEspacoVetor();
	}
	
	public void removerUltimaPosicao() {
		if(!posicaoOcupada(totalClientes - 1)) 
			throw new IllegalArgumentException("Posicao invalida");
		
		vetor[totalClientes - 1] = null;
		totalClientes--;
		liberarEspacoVetor();
	}
	
	public void removerPrimeiraPosicao() {
		if(!posicaoOcupada(0)) 
			throw new IllegalArgumentException("Posicao invalida");
		
		for (int i = 0; i < totalClientes; i++) 
			vetor[i] = vetor[i+1];
		
		totalClientes--;
		liberarEspacoVetor();
	}
	
	public void removerTodasPosicoes() {
		for (int i = 0; i < totalClientes; i++) 
			vetor[i] = null;
		
		totalClientes = 0;
		liberarEspacoVetor();
	}
	
}