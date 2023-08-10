package model.teste;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Cliente;
import model.Vetor;

class VetorTest {
	
	Vetor vetor;
	
	@BeforeEach
	void inicializa() {
		vetor = new Vetor();
	}
	
	@Test
	void testAdicionarComNenhumElemento() {
		Cliente cliente1 = new Cliente("Fernando");
		
		vetor.adicionar(cliente1);
		
		assertEquals(1, vetor.pegaTotalClientes());
	}
	
	@Test
	void testAdicionarComElementosJaExisntentes() {
		Cliente cliente1 = new Cliente("Fernando");
		Cliente cliente2 = new Cliente("Maria");
		
		vetor.adicionar(cliente1);
		vetor.adicionar(cliente2);
		
		assertEquals(2, vetor.pegaTotalClientes());
	}
	
	@Test
	void testPegarUmSoElementoExistente() {
		Cliente cliente1 = new Cliente("Fernando");
		
		vetor.adicionar(cliente1);
		
		assertEquals("Fernando", vetor.pegar(0).getNome());
		assertEquals(1, vetor.pegaTotalClientes());
	}
	
	@Test
	void testPegarDoisSoElementoExistente() {
		Cliente cliente1 = new Cliente("Fernando");
		Cliente cliente2 = new Cliente("Maria");
		
		vetor.adicionar(cliente1);
		vetor.adicionar(cliente2);
		
		assertEquals("Fernando", vetor.pegar(0).getNome());
		assertEquals("Maria", vetor.pegar(1).getNome());
		assertEquals(2, vetor.pegaTotalClientes());
	}
	
	@Test
	void testPegaPosicaoNegativa() {
		Cliente cliente1 = new Cliente("Fernando");
		Cliente cliente2 = new Cliente("Maria");
		
		vetor.adicionar(cliente1);
		vetor.adicionar(cliente2);
		
		assertThrows(IllegalArgumentException.class, ()->vetor.pegar(-1));
	}
	
	@Test
	void testPegaPosicaoNaoOcupada() {
		Cliente cliente1 = new Cliente("Fernando");
		Cliente cliente2 = new Cliente("Maria");
		
		vetor.adicionar(cliente1);
		vetor.adicionar(cliente2);
		
		assertThrows(IllegalArgumentException.class, ()->vetor.pegar(4));
	}
	
	@Test
	void testContemSemElementos() {
		Cliente cliente1 = new Cliente("Fernando");
		
		assertFalse(vetor.contem(cliente1));
		assertEquals(0, vetor.pegaTotalClientes());
	}
	
	
	@Test
	void testContemComElementos() {
		Cliente cliente1 = new Cliente("Fernando");
		Cliente cliente2 = new Cliente("Maria");
		
		vetor.adicionar(cliente1);
		vetor.adicionar(cliente2);
		
		assertTrue(vetor.contem(cliente1));
		assertTrue(vetor.contem(cliente2));
	}
	
	@Test
	void testAdicionarPosicaoComNenhumElemento() {
		Cliente cliente1 = new Cliente("Fernando");
		
		vetor.adicionar(cliente1, 0);
		
		assertEquals(1, vetor.pegaTotalClientes());
		assertEquals("Fernando", vetor.pegar(0).getNome());
	}
	
	@Test
	void testAdicionarPosicaoComElementosJaExisntentes() {
		Cliente cliente1 = new Cliente("Fernando");
		Cliente cliente2 = new Cliente("Maria");
		
		vetor.adicionar(cliente1, 0);
		vetor.adicionar(cliente2, 1);
		
		Cliente cliente3 = new Cliente("Carlos");
		vetor.adicionar(cliente3,1);
		
		assertEquals(3, vetor.pegaTotalClientes());
		assertEquals("Fernando", vetor.pegar(0).getNome());
		assertEquals("Carlos", vetor.pegar(1).getNome());
		assertEquals("Maria", vetor.pegar(2).getNome());
	}
	
	@Test
	void testRemoverComElementos() {
		Cliente cliente1 = new Cliente("Fernando");
		Cliente cliente2 = new Cliente("Maria");
		Cliente cliente3 = new Cliente("Carlos");
		
		vetor.adicionar(cliente1, 0);
		vetor.adicionar(cliente2, 1);
		vetor.adicionar(cliente3,2);
		
		vetor.remover(2);
		assertEquals("Fernando", vetor.pegar(0).getNome());
		assertEquals("Maria", vetor.pegar(1).getNome());
		assertEquals(2, vetor.pegaTotalClientes());
	}
	
	@Test
	void testAdicionarDobrandoTamanho() {
		Cliente cliente1 = new Cliente("Fernando");
		Cliente cliente2 = new Cliente("Maria");
		Cliente cliente3 = new Cliente("Carlos");
		
		Cliente cliente4 = new Cliente("Rose");
		Cliente cliente5 = new Cliente("Xuxa");
		Cliente cliente6 = new Cliente("Ze");
		
		vetor.adicionar(cliente1);
		vetor.adicionar(cliente2);
		vetor.adicionar(cliente3);
		
		vetor.adicionar(cliente4);
		vetor.adicionar(cliente5);
		vetor.adicionar(cliente6);
		
		assertEquals("Ze", vetor.pegar(5).getNome());
		assertEquals(6, vetor.pegaTotalClientes());
	}
	
	@Test
	void testRemoverUltimo() {
		Cliente cliente1 = new Cliente("Fernando");
		Cliente cliente2 = new Cliente("Maria");
		Cliente cliente3 = new Cliente("Carlos");
		
		vetor.adicionar(cliente1, 0);
		vetor.adicionar(cliente2, 1);
		vetor.adicionar(cliente3, 2);
		
		vetor.removerUltimaPosicao();
		assertEquals("Fernando", vetor.pegar(0).getNome());
		assertEquals("Maria", vetor.pegar(1).getNome());
		assertThrows(IllegalArgumentException.class, ()->vetor.pegar(2));
		assertEquals(2, vetor.pegaTotalClientes());
	}
	
	@Test
	void testRemoverPrimeiro() {
		Cliente cliente1 = new Cliente("Fernando");
		Cliente cliente2 = new Cliente("Maria");
		Cliente cliente3 = new Cliente("Carlos");
		
		vetor.adicionar(cliente1, 0);
		vetor.adicionar(cliente2, 1);
		vetor.adicionar(cliente3, 2);
		
		vetor.removerPrimeiraPosicao();
		assertEquals("Maria", vetor.pegar(0).getNome());
		assertEquals("Carlos", vetor.pegar(1).getNome());
		assertThrows(IllegalArgumentException.class, ()->vetor.pegar(2));
		assertEquals(2, vetor.pegaTotalClientes());
	}
	
	@Test
	void testRemoverTodasPosicoes() {
		Cliente cliente1 = new Cliente("Fernando");
		Cliente cliente2 = new Cliente("Maria");
		Cliente cliente3 = new Cliente("Carlos");
		
		vetor.adicionar(cliente1, 0);
		vetor.adicionar(cliente2, 1);
		vetor.adicionar(cliente3, 2);
		
		vetor.removerTodasPosicoes();
		assertThrows(IllegalArgumentException.class, ()->vetor.pegar(0));
		assertThrows(IllegalArgumentException.class, ()->vetor.pegar(2));
		assertEquals(0, vetor.pegaTotalClientes());
	}
	
	@Test
	void testLiberarEspaco() {
		testAdicionarDobrandoTamanho();
		assertEquals(10, vetor.getTamanhoVetor());

		vetor.removerUltimaPosicao();
		vetor.removerUltimaPosicao();
		vetor.removerUltimaPosicao();
		vetor.removerUltimaPosicao();
		
		assertEquals(2, vetor.pegaTotalClientes());
		assertEquals(5, vetor.getTamanhoVetor());
	}

}