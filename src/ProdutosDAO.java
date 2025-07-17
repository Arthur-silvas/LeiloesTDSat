
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
            String sql = "INSERT INTO produtos (nome, valor, status) VALUE (?, ?, ?)";
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

        return listagem;
    }

}
