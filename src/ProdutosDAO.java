
/**
 *
 * @author Arthur
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {

        conn = new conectaDAO().connectDB();
        try {
            String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setDouble(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.executeUpdate();
        } catch (SQLException slqe) {
            JOptionPane.showMessageDialog(null, "Erro ao Inserir dados no banco");
            System.out.println(slqe.getMessage());
        }

        try {
            conn.close();
        } catch (SQLException sqle) {
            System.out.println("Erro ao fechar conexão");
        }

    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        listagem = new ArrayList<>();

        conn = new conectaDAO().connectDB();
        try {
            String sql = "SELECT * FROM produtos";
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();

            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getDouble("valor"));
                produto.setStatus(resultset.getString("status"));

                listagem.add(produto);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + erro.getMessage());
        }

        try {
            conn.close();
        } catch (SQLException sqle) {
            System.out.println("Erro ao fechar conexão");
        }

        return listagem;
    }

}
