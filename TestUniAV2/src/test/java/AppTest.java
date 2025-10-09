import org.example.App;
import org.example.Produto;
import org.example.Venda;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    private Produto produto;
    private Venda Venda;

    @BeforeEach
    public void setUp() {
        produto = new Produto("Caneta", 2.5, 100);
        Venda = new Venda(produto, -10);
    }

    @Test
    public void testCriarprodutoValorValido() {
        Assertions.assertEquals("Caneta", produto.getNome());
        Assertions.assertEquals(2.5, produto.getPreco());
        Assertions.assertEquals(100, produto.getEstoque());
    }

    @Test
    public void testCriarprodutoPrecoNegativo() {
        produto.setPreco(-1);
        Assertions.assertEquals("Caneta", produto.getNome());
        Assertions.assertEquals(-1, produto.getPreco());
        Assertions.assertEquals(100, produto.getEstoque());
    }

    @Test
    public void testCriarprodutoEstoqueNegativo() {
        produto.setEstoque(-10);
        Assertions.assertEquals("Caneta", produto.getNome());
        Assertions.assertEquals(2.5, produto.getPreco());
        Assertions.assertEquals(-10, produto.getEstoque());
    }

    @Test
    public void testAlterarNomeProdutoValorValido() {
        produto.setNome("Lápis");
        Assertions.assertEquals("Lápis", produto.getNome());
        Assertions.assertEquals(2.5, produto.getPreco());
        Assertions.assertEquals(100, produto.getEstoque());
    }

    @Test
    public void testAlterarPrecoValorValido() {
        produto.setPreco(1.5);
        Assertions.assertEquals("Caneta", produto.getNome());
        Assertions.assertEquals(1.5, produto.getPreco());
        Assertions.assertEquals(100, produto.getEstoque());
    }
    @Test
    public void testAlterarEstoqueValorPositivo(){
        produto.setEstoque(50);
        Assertions.assertEquals("Caneta", produto.getNome());
        Assertions.assertEquals(2.5, produto.getPreco());
        Assertions.assertEquals(50, produto.getEstoque());
    }
    @Test
    public void testAlterarValorNegativo(){
        produto.setPreco(-1.5);
        Assertions.assertEquals("Caneta", produto.getNome());
        Assertions.assertEquals(-1.5, produto.getPreco());
        Assertions.assertEquals(100, produto.getEstoque());
    }
    @Test
    public void testVendaQuantidadeMenorEstoque(){
        Venda venda = new Venda(produto, 20);
        Assertions.assertTrue(venda.realizarVenda());
        Assertions.assertEquals(80, produto.getEstoque());
        Assertions.assertEquals(50.0, venda.getTotalVenda());
    }
    @Test
    public void testVendaQuantidadeIgualEstoque(){
        Venda venda = new Venda(produto, 100);
        Assertions.assertTrue(venda.realizarVenda());
        Assertions.assertEquals(0, produto.getEstoque());
        Assertions.assertEquals(250.0, venda.getTotalVenda());
    }
    @Test
    public void testVendaQuantidadeMaiorEstoque(){
        produto.setEstoque(150);
        Venda venda = new Venda(produto, 150);
        Assertions.assertTrue(venda.realizarVenda());
        Assertions.assertEquals(0, produto.getEstoque());
        Assertions.assertEquals(375, venda.getTotalVenda());
    }
    @Test
    public void testCalculoTotalVenda(){
        Venda venda = new Venda(produto, 10);
        Assertions.assertTrue(venda.realizarVenda());
        Assertions.assertEquals(25.0, venda.getTotalVenda());
    }
    @Test
    public void testAumentoEstoqueaposVenda(){
        Venda venda = new Venda(produto, 30);
        Assertions.assertTrue(venda.realizarVenda());
        produto.aumentarEstoque(20);
        Assertions.assertEquals(90, produto.getEstoque());
    }
    @Test
    public void testDimiuirEstoqueVendaSucedida(){
        Venda venda = new Venda(produto, 40);
        Assertions.assertTrue(venda.realizarVenda());
        Assertions.assertEquals(60, produto.getEstoque());
    }
    @Test
    public void testVendaprodutoInexistente(){
        Produto produtoInexistente = new Produto("Borracha", 1.0, 0);
        Venda venda = new Venda(produtoInexistente, 10);
        Assertions.assertFalse(venda.realizarVenda());
        Assertions.assertEquals(0, produtoInexistente.getEstoque());
        Assertions.assertEquals(0.0, venda.getTotalVenda());
    }
    @Test
    public void testVendaQuantidadeNegativa(){
        Venda vendaNegativa = new Venda(produto, -10);
        Assertions.assertTrue(vendaNegativa.realizarVenda());
        Assertions.assertEquals(100, produto.getEstoque());
        Assertions.assertEquals(0.0, vendaNegativa.getTotalVenda());
    }
    @Test
    public void testAlterarEstoqueTentativaEstoqueInsuficiente(){
        boolean resultado = produto.diminuirEstoque(150);
        Assertions.assertFalse(resultado);
        Assertions.assertEquals(100, produto.getEstoque());
    }
    @Test
    public void testCriarVariosProdutosRealizarVendasCompartilhadas(){
        Produto produto2 = new Produto("Caderno", 10.0, 50);
        Venda venda1 = new Venda(produto, 30);
        Venda venda2 = new Venda(produto2, 20);
        Assertions.assertTrue(venda1.realizarVenda());
        Assertions.assertTrue(venda2.realizarVenda());
        Assertions.assertEquals(70, produto.getEstoque());
        Assertions.assertEquals(30, produto2.getEstoque());
        Assertions.assertEquals(75.0, venda1.getTotalVenda());
        Assertions.assertEquals(200.0, venda2.getTotalVenda());
    }
}
