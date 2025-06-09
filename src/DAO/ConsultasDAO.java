package DAO;

import java.sql.Connection;
import java.sql.SQLException;

import Datab.Datab;
import Model.Consultas;

public class ConsultasDAO {

    private Connection conecta;

    //Construtor
    public ConsultasDAO() throws SQLException{
        this.conecta = Datab.getConnection();
        if (this.conecta != null) {
        System.out.println(" Conexão estabelecida com sucesso!");
    } else {
        System.out.println(" Falha ao conectar ao banco de dados!");
    }
}

    public void consultarProdutos( Consultas obj) {
        try {
            Connection conn = Datab.getConnection();

            String sql = "select * from consultas where nomeProdutos like ?";

            java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + obj.getProduto() + "%");

            java.sql.ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("Produto: " + rs.getString("nomeProdutos"));
                System.out.println("Categoria: " + rs.getString("categoria"));
                System.out.println("Preço: " + rs.getDouble("preco"));
                System.out.println("Quantidade: " + rs.getInt("quantidade"));
                System.out.println("Fornecedor: " + rs.getString("fornecedor"));
            }

            rs.close();
            stmt.close();
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
}
