package model.dao;

import connection.Conexao;
import model.bean.Pneus;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author fredaum
 */
public class PneusDAO {
    
    public void create(Pneus p){
        
        Connection con = Conexao.abrirConexao();
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement
        ("insert into pneus(descricao, medidas, preco,"
                    + "ativo) values (?,?,?,?)");
            
            stmt.setString(1, p.getDescricao());
            stmt.setString(2, p.getMedidas());
            stmt.setDouble(3, p.getPreco());
            stmt.setString(4, p.getAtivo());
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao salvar!");
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
    
    public List<Pneus> read(){
        
        Connection con = Conexao.abrirConexao();
        PreparedStatement stmt = null;
        List<Pneus> pneu = new ArrayList<>();
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("select * from pneus");
            rs = stmt.executeQuery();
            
            while(rs.next()){
            Pneus p = new Pneus();
            
                p.setCodPneu(rs.getInt("codPneu"));
                p.setDescricao(rs.getString("descricao"));
                p.setMedidas(rs.getString("medidas"));
                p.setPreco(rs.getDouble("preco"));
                p.setAtivo(rs.getString("ativo"));
                pneu.add(p);
            }
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao consultar!");
        } finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        return pneu;
    }
    
    public List<Pneus> readSelectedPneu(String descricao){
        Connection con = Conexao.abrirConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Pneus> pneu = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("select * from pneus where descricao = ?");
            stmt.setString(1, descricao);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Pneus p = new Pneus();
                
                p.setCodPneu(rs.getInt("codPneu"));
                p.setDescricao(rs.getString("descricao"));
                p.setMedidas(rs.getString("medidas"));
                p.setPreco(rs.getDouble("preco"));
                p.setAtivo(rs.getString("ativo"));
                pneu.add(p);
            }
            
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Pneu não encontrado!");
        } finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        return pneu;
    }
    
    public void update(Pneus p){
        
        Connection con = Conexao.abrirConexao();
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement("update pneus set descricao = ?,"
                    + "medidas = ?, preco = ?, ativo = ? where codPneu = ?");
            
            stmt.setString(1, p.getDescricao());
            stmt.setString(2, p.getMedidas());
            stmt.setDouble(3, p.getPreco());
            stmt.setString(4, p.getAtivo());
            stmt.setInt(5, p.getCodPneu());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar!");
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
    
    
    public void delete(Pneus p){
        
        Connection con = Conexao.abrirConexao();
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement("delete from pneus where codPneu = ?");
            stmt.setInt(1, p.getCodPneu());
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir!");
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
        
    }
}
