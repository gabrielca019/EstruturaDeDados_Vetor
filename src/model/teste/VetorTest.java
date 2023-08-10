package model.teste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Cliente;
import model.Vetor;

class VetorTest {

	Vetor vetor;
	Cliente clienteFernando, clienteMaria, clienteCarlos;
	
	@BeforeEach
	void resetandoVetor() {
		vetor = new Vetor();
		clienteFernando = new Cliente("Fernando");
		clienteMaria = new Cliente("Maria");
		clienteCarlos = new Cliente("Carlos");
	}

	@Test
	void testAdicionarComNenhumElemento() {
		vetor.adicionar(clienteFernando);

		assertEquals(1, vetor.getTotalClientes());
	}

	@Test
	void testAdicionarComElementosJaExisntentes() {
		vetor.adicionar(clienteFernando);
		vetor.adicionar(clienteMaria);

		assertEquals(2, vetor.getTotalClientes());
	}

	@Test
	void testPegarUmSoElementoExistente() {
		vetor.adicionar(clienteFernando);

		assertEquals("Fernando", vetor.getClientePosicaoEspecifica(0).getNome());
		assertEquals(1, vetor.getTotalClientes());
	}

	@Test
	void testPegarDoisSoElementoExistente() {
		vetor.adicionar(clienteFernando);
		vetor.adicionar(clienteMaria);

		assertEquals("Fernando", vetor.getClientePosicaoEspecifica(0).getNome());
		assertEquals("Maria", vetor.getClientePosicaoEspecifica(1).getNome());
		assertEquals(2, vetor.getTotalClientes());
	}

	@Test
	void testPegaPosicaoNegativa() {
		vetor.adicionar(clienteFernando);
		vetor.adicionar(clienteMaria);

		assertThrows(IllegalArgumentException.class, ()->vetor.getClientePosicaoEspecifica(-1));
	}

	@Test
	void testPegaPosicaoNaoOcupada() {
		vetor.adicionar(clienteFernando);
		vetor.adicionar(clienteMaria);

		assertThrows(IllegalArgumentException.class, ()->vetor.getClientePosicaoEspecifica(4));
	}

	@Test
	void testContemSemElementos() {
		assertFalse(vetor.contem(clienteFernando));
		assertEquals(0, vetor.getTotalClientes());
	}


	@Test
	void testContemComElementos() {
		vetor.adicionar(clienteFernando);
		vetor.adicionar(clienteMaria);

		assertTrue(vetor.contem(clienteFernando));
		assertTrue(vetor.contem(clienteMaria));
	}

	@Test
	void testAdicionarPosicaoComNenhumElemento() {
		vetor.adicionarPosicaoEspecifica(clienteFernando, 0);

		assertEquals(1, vetor.getTotalClientes());
		assertEquals("Fernando", vetor.getClientePosicaoEspecifica(0).getNome());
	}

	@Test
	void testAdicionarPosicaoComElementosJaExisntentes() {
		vetor.adicionarPosicaoEspecifica(clienteFernando, 0);
		vetor.adicionarPosicaoEspecifica(clienteMaria, 1);

		vetor.adicionarPosicaoEspecifica(clienteCarlos, 1);

		assertEquals(3, vetor.getTotalClientes());
		assertEquals("Fernando", vetor.getClientePosicaoEspecifica(0).getNome());
		assertEquals("Carlos", vetor.getClientePosicaoEspecifica(1).getNome());
		assertEquals("Maria", vetor.getClientePosicaoEspecifica(2).getNome());
	}

	@Test
	void testRemoverComElementos() {
		vetor.adicionarPosicaoEspecifica(clienteFernando, 0);
		vetor.adicionarPosicaoEspecifica(clienteMaria, 1);
		vetor.adicionarPosicaoEspecifica(clienteCarlos, 2);

		vetor.removerPosicaoEspecifica(2);
		assertEquals("Fernando", vetor.getClientePosicaoEspecifica(0).getNome());
		assertEquals("Maria", vetor.getClientePosicaoEspecifica(1).getNome());
		assertEquals(2, vetor.getTotalClientes());
	}

	@Test
	void testAdicionarDobrandoTamanho() {
		Cliente cliente4 = new Cliente("Rose");
		Cliente cliente5 = new Cliente("Xuxa");
		Cliente cliente6 = new Cliente("Ze");

		vetor.adicionar(clienteFernando);
		vetor.adicionar(clienteMaria);
		vetor.adicionar(clienteCarlos);

		vetor.adicionar(cliente4);
		vetor.adicionar(cliente5);
		vetor.adicionar(cliente6);

		assertEquals("Ze", vetor.getClientePosicaoEspecifica(5).getNome());
		assertEquals(6, vetor.getTotalClientes());
	}

	@Test
	void testRemoverUltimo() {
		vetor.adicionarPosicaoEspecifica(clienteFernando, 0);
		vetor.adicionarPosicaoEspecifica(clienteMaria, 1);
		vetor.adicionarPosicaoEspecifica(clienteCarlos, 2);

		vetor.removerUltimaPosicao();
		assertEquals("Fernando", vetor.getClientePosicaoEspecifica(0).getNome());
		assertEquals("Maria", vetor.getClientePosicaoEspecifica(1).getNome());
		assertThrows(IllegalArgumentException.class, ()->vetor.getClientePosicaoEspecifica(2));
		assertEquals(2, vetor.getTotalClientes());
	}

	@Test
	void testRemoverPrimeiro() {
		vetor.adicionarPosicaoEspecifica(clienteFernando, 0);
		vetor.adicionarPosicaoEspecifica(clienteMaria, 1);
		vetor.adicionarPosicaoEspecifica(clienteCarlos, 2);

		vetor.removerPrimeiraPosicao();
		assertEquals("Maria", vetor.getClientePosicaoEspecifica(0).getNome());
		assertEquals("Carlos", vetor.getClientePosicaoEspecifica(1).getNome());
		assertThrows(IllegalArgumentException.class, ()->vetor.getClientePosicaoEspecifica(2));
		assertEquals(2, vetor.getTotalClientes());
	}

	@Test
	void testRemoverTodasPosicoes() {
		vetor.adicionarPosicaoEspecifica(clienteFernando, 0);
		vetor.adicionarPosicaoEspecifica(clienteMaria, 1);
		vetor.adicionarPosicaoEspecifica(clienteCarlos, 2);

		vetor.removerTodasPosicoes();
		assertThrows(IllegalArgumentException.class, ()->vetor.getClientePosicaoEspecifica(0));
		assertThrows(IllegalArgumentException.class, ()->vetor.getClientePosicaoEspecifica(2));
		assertEquals(0, vetor.getTotalClientes());
	}

	@Test
	void testLiberarEspaco() {
		testAdicionarDobrandoTamanho();
		assertEquals(10, vetor.getTamanhoVetor());

		vetor.removerUltimaPosicao();
		vetor.removerUltimaPosicao();
		vetor.removerUltimaPosicao();
		vetor.removerUltimaPosicao();

		assertEquals(2, vetor.getTotalClientes());
		assertEquals(5, vetor.getTamanhoVetor());
	}

}