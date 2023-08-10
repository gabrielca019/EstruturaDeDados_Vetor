package model;

public class Cliente {
	
	private String nome;

	public String getNome() {
		return nome;
	}

	public Cliente(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return this.nome;
	}
	
	@Override
	public boolean equals(Object obj) {		
		if (obj == null) {
			return false;
		}
		
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		
		Cliente outro = (Cliente) obj;
		
		return outro.getNome().equals(this.nome);
	}

}