package model.dao;

import connection.Conexao;
import model.bean.Itens;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.NotaFiscal;
import model.bean.Pneus;

/**
 *
 * @author fredaum
 */
public class ItensDAO {
    
    public void create (Itens i){
        Connection con = Conexao.abrirConexao();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("insert into itens(numero, serie, item, codPneu, qtde, preco)"
                    + "values(?,?,?,?,?,?)"); 
            stmt.setInt(1, i.getNotaFiscal().getNumero());
            stmt.setString(2, i.getNotaFiscal().getSerie());
            stmt.setInt(3, i.getItem());
            stmt.setInt(4, i.getPneu().getCodPneu());
            stmt.setInt(5, i.getQtde());
            stmt.setDouble(6, i.getPreco());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!");
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }    
        
    public List<Itens> read(){
        Connection con = Conexao.abrirConexao();
        List<Itens> item = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("select n.numero, n.serie, i.item, p.codPneu, i.qtde, i.preco from itens i inner join notaFiscal n on i.numero = n.numero inner join pneus p on i.codPneu = p.codPneu");
            rs = stmt.executeQuery();
            
            while(rs.next()){
            Itens i = new Itens();
            NotaFiscal n = new NotaFiscal();
                n.setNumero(rs.getInt("n.numero"));
                n.setSerie(rs.getString("n.serie"));
                i.setItem(rs.getInt("i.item"));
            Pneus p = new Pneus();
                p.setCodPneu(rs.getInt("p.codPneu"));
                i.setQtde(rs.getInt("i.qtde"));
                i.setPreco((rs.getDouble("i.preco")));
                i.setNotaFiscal(n);
                i.setPneu(p);
                item.add(i);
            }

            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar!");
        } finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        return item;
    }    
    
    public List<Itens> readSelectedItem(int numero, int item){
        Connection con = Conexao.abrirConexao();
        List<Itens> itens = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("select n.numero, n.serie, i.item, p.codPneu, i.qtde, i.preco from itens i inner join notaFiscal n on i.numero = n.numero inner join pneus p on i.codPneu = p.codPneu where n.numero = ? and i.item = ?");
            stmt.setInt(1, numero);
            stmt.setInt(2, item);
            rs = stmt.executeQuery();
            
            while(rs.next()){
            Itens i = new Itens();
            NotaFiscal n = new NotaFiscal();
                n.setNumero(rs.getInt("n.numero"));
                n.setSerie(rs.getString("n.serie"));
                i.setItem(rs.getInt("i.item"));
            Pneus p = new Pneus();
                p.setCodPneu(rs.getInt("p.codPneu"));
                i.setQtde(rs.getInt("i.qtde"));
                i.setPreco((rs.getDouble("i.preco")));
                i.setNotaFiscal(n);
                i.setPneu(p);
                itens.add(i);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Item não encontrado!");
        } finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        return itens;
    }    
    
    public void update(Itens i){
        Connection con = Conexao.abrirConexao();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("update itens set qtde = ?, preco = ?, serie = ? "
                    + "where item = ? and numero = ?");
            
            stmt.setInt(1, i.getQtde());
            stmt.setDouble(2, i.getPreco());
            stmt.setString(3, i.getNotaFiscal().getSerie());
            stmt.setInt(4, i.getItem());
            stmt.setInt(5, i.getNotaFiscal().getNumero());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar!");
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
    
    public void delete(Itens i){
        Connection con = Conexao.abrirConexao();
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement("delete from itens where item = ? and numero = ?");
            stmt.setInt(1, i.getItem());
            stmt.setInt(2, i.getNotaFiscal().getNumero());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir!");
        } finally { 
            Conexao.fecharConexao(con, stmt);
        }
    }
}
