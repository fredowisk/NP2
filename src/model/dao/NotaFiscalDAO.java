package model.dao;

import connection.Conexao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Clientes;
import model.bean.NotaFiscal;

/**
 *
 * @author fredaum
 */
public class NotaFiscalDAO {
    public void create(NotaFiscal n){
        
        Connection con = Conexao.abrirConexao();
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement("insert into notaFiscal(serie, codCli ,data"
                    + ", cancelada) values (?,?,?,?)");
            
            stmt.setString(1, n.getSerie());
            stmt.setInt(2, n.getCliente().getCodCli());
            stmt.setDate(3, (Date) n.getData());
            stmt.setString(4, n.getCancelada());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!");
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
    
    public List<NotaFiscal> read(){
        List<NotaFiscal> nota = new ArrayList<>();
        Connection con = Conexao.abrirConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("select n.numero, n.serie, c.codCli, n.data, n.cancelada from notaFiscal n inner join clientes c on c.codCli = n.codCli");
            rs = stmt.executeQuery();
            
            while(rs.next()){
            NotaFiscal n = new NotaFiscal();
            Clientes c = new Clientes();
            
                n.setNumero(rs.getInt("n.numero"));
                n.setSerie(rs.getString("n.serie"));
                c.setCodCli(rs.getInt("c.codCli"));
                n.setData(rs.getDate("n.data"));
                n.setCancelada(rs.getString("n.cancelada"));
                n.setCliente(c);
                nota.add(n);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar!");
        } finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        return nota;
    }
    public List<NotaFiscal> readSelectedNota(int numero){
        List<NotaFiscal> nota = new ArrayList<>();
        Connection con = Conexao.abrirConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("select n.numero, n.serie, c.codCli, n.data, n.cancelada from notaFiscal n inner join clientes c on c.codCli = n.codCli where n.numero = ?");
            stmt.setInt(1, numero);
            rs = stmt.executeQuery();
            
            while(rs.next()){
            NotaFiscal n = new NotaFiscal();
            Clientes c = new Clientes();
            
                n.setNumero(rs.getInt("n.numero"));
                n.setSerie(rs.getString("n.serie"));
                c.setCodCli(rs.getInt("c.codCli"));
                n.setData(rs.getDate("n.data"));
                n.setCancelada(rs.getString("n.cancelada"));
                n.setCliente(c);
                nota.add(n);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Nota fiscal não encontrada!");
        } finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        return nota;
    }
    
    public void update(NotaFiscal n){
        Connection con = Conexao.abrirConexao();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("update notaFiscal serie = ?,"
                    + "data = ?, cancelada = ? where numero = ?");
            stmt.setString(1, n.getSerie());
            stmt.setDate(2, (Date) n.getData());
            stmt.setString(3, n.getCancelada());
            stmt.setInt(4, n.getNumero());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar!");
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
    
    public void delete(NotaFiscal n){
        Connection con = Conexao.abrirConexao();
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement("delete from notaFiscal where numero = ?");
            stmt.setInt(1, n.getNumero());
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao excluir!");
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
}
