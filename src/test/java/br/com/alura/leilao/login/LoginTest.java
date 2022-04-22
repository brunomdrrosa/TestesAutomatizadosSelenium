package br.com.alura.leilao.login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.lance.LancesPage;

public class LoginTest {

    private LoginPage paginaDeLogin;

    @BeforeEach
    public void beforeEach() {
        this.paginaDeLogin = new LoginPage();
    }

    @AfterEach
    public void afterEach() {
        this.paginaDeLogin.fechar();
    }

    @Test
    public void deveriaEfetuarLoginComDadosValidos() {
        paginaDeLogin.efetuarLogin("fulano", "pass");

        String nomeUsuarioLogado = paginaDeLogin.getNomeUsuarioLogado();
        Assertions.assertFalse(paginaDeLogin.isPaginaDeLogin());
        Assertions.assertEquals("fulano", nomeUsuarioLogado);
    }

    @Test
    public void naoDeveriaLogarComDadosInvalidos() {
        paginaDeLogin.efetuarLogin("invalido", "senha123");

        Assertions.assertTrue(paginaDeLogin.isPaginaAtual());
        Assertions.assertNull(paginaDeLogin.getNomeUsuarioLogado());
        Assertions.assertTrue(paginaDeLogin.isMensagemDeLoginInvalidoVisivel());
    }

    @Test
    public void naoDeveriaAcessarPaginaRestritaSemEstarLogado() {
        LancesPage paginaDeLances = new LancesPage();

        Assertions.assertFalse(paginaDeLances.isPaginaAtual());
        Assertions.assertFalse(paginaDeLances.isTituloLeilaoVisivel());

        paginaDeLances.fechar();
    }
}
