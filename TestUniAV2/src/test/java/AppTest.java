import org.example.App;
import org.example.Produto;
import org.example.Venda;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    private Produto produto;

    @BeforeEach
    public void setUp() {
        produto = new Produto("Caneta", 2.5, 100);
    }

    @Test
    public void testCriarprodutoValorValido() {
        assertEquals("Caneta", produto.getNome());
        assertEquals(2.5, produto.getPreco());
        assertEquals(100, produto.getEstoque());
    }

    @Test
    public void testCriarprodutoPrecoNegativo() {
        assertEquals("Caneta", produto.getNome());
        assertEquals(-1, produto.getPreco());
        assertEquals(100, produto.getEstoque());
    }

    @Test
    public void testCriarprodutoEstoqueNegativo() {
        assertEquals("Caneta", produto.getNome());
        assertEquals(2.5, produto.getPreco());
        assertEquals(-10, produto.getEstoque());
    }

    @Test
    public void testAlterarNomeProdutoValorValido() {
        produto.setNome("Lápis");
        assertEquals("Lápis", produto.getNome());
        assertEquals(2.5, produto.getPreco());
        assertEquals(100, produto.getEstoque());
    }

    @Test
    public void testAlterarPrecoValorValido() {
        produto.setPreco(1.5);
        assertEquals("Caneta", produto.getNome());
        assertEquals(1.5, produto.getPreco());
        assertEquals(100, produto.getEstoque());
    }
    @Test
    public void testAlterarEstoqueValorPositivo(){
        produto.setEstoque(50);
        assertEquals("Caneta", produto.getNome());
        assertEquals(2.5, produto.getPreco());
        assertEquals(50, produto.getEstoque());
    }
    @Test
    public void testAlterarValorNegativo(){
        assertEquals("Caneta", produto.getNome());
        assertEquals(-1.5, produto.getPreco());
        assertEquals(100, produto.getEstoque());
    }
    @Test
    public void testVendaQuantidadeMenorEstoque(){
        Venda venda = new Venda(produto, 20);
        assertTrue(venda.realizarVenda());
        assertEquals(80, produto.getEstoque());
        assertEquals(50.0, venda.getTotalVenda());
    }
    @Test
    public void testVendaQuantidadeIgualEstoque(){
        Venda venda = new Venda(produto, 100);
        assertTrue(venda.realizarVenda());
        assertEquals(0, produto.getEstoque());
        assertEquals(250.0, venda.getTotalVenda());
    }
    @Test
    public void testVendaQuantidadeMaiorEstoque(){
        Venda venda = new Venda(produto, 150);
        assertTrue(venda.realizarVenda());
        assertEquals(100, produto.getEstoque());
        assertEquals(0.0, venda.getTotalVenda());
    }
}
