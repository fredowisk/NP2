package model.dao;

import connection.Conexao;
import model.bean.Clientes;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql. ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author fredaum
 */
public class ClientesDAO {
    public void create(Clientes c){
        Connection con = Conexao.abrirConexao();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("insert into clientes(nome, endereco, estado)"
                    + "values (?,?,?)");
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getEndereco());
            stmt.setString(3, c.getEstado());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!");
            
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
    
    public List<Clientes> read(){
        Connection con = Conexao.abrirConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Clientes> cliente = new ArrayList<>();
        
        try {
            
            stmt = con.prepareStatement("select * from clientes");
            rs = stmt.executeQuery();
            
            
            while (rs.next()){
                Clientes c = new Clientes();

                c.setCodCli(rs.getInt("codCli"));
                c.setNome(rs.getString("nome"));
                c.setEndereco(rs.getString("endereco"));
                c.setEstado(rs.getString("estado"));
                cliente.add(c);
                
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar!");
            
        } finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        return cliente;
    }
    
    public List<Clientes> readSelectedClient(String nome){
        Connection con = Conexao.abrirConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Clientes> cliente = new ArrayList<>();
        
        try {
            
            stmt = con.prepareStatement("select * from clientes where nome = ?");
            stmt.setString(1, nome);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Clientes c = new Clientes();
                
                c.setCodCli(rs.getInt("codCli"));
                c.setNome(rs.getString("nome"));
                c.setEndereco(rs.getString("endereco"));
                c.setEstado(rs.getString("estado"));
                cliente.add(c);
            }
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
        } finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        return cliente;
    }
    
    public void update(Clientes c){
        Connection con = Conexao.abrirConexao();
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement("update clientes set nome = ?, endereco = ?,"
                    + "estado = ? where codCli = ?");
            stmt.setString(1,c.getNome());
            stmt.setString(2, c.getEndereco());
            stmt.setString(3, c.getEstado());
            stmt.setInt(4, c.getCodCli());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar!");
            
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
    
    public void delete(Clientes c){
        Connection con = Conexao.abrirConexao();
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement("delete from clientes where codCli = ?");
            stmt.setInt(1, c.getCodCli());
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir!");
            
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
}
