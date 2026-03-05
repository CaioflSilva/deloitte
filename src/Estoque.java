import java.util.ArrayList;

public class Estoque {

   public ArrayList<Produto> produtos = new ArrayList<>();

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public void listarProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Estoque vazio.");
        } else {
            for (int i = 0; i < produtos.size(); i++) {
                System.out.println(i + " - " + produtos.get(i));
            }
        }
    }

    public void removerProduto(int indice) {
        if (indice >= 0 && indice < produtos.size()) {
            produtos.remove(indice);
            System.out.println("Produto removido!");
        } else {
            System.out.println("Índice inválido");
        }
    }

    public void alterarProduto(int indice, String novoNome, double novoPreco, int novaQuantidade) {
        if (indice >= 0 && indice < produtos.size()) {
            Produto produto = produtos.get(indice);
            produto.setNome(novoNome);
            produto.setPreco(novoPreco);
            produto.setQuantidade(novaQuantidade);
            System.out.println("Produto alterado com sucesso!");
        } else {
            System.out.println("Índice inválido para alteração.");
        }
    }
}