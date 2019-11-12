
package rules;

//Imports.
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

/**
 *
 * @author fredaum
 */
//Classe que permite que o jTextField receba apenas numeros.
public final class JtextFieldSomenteLetras extends JTextField {
    
    //Variaveis para criar o maximo de numeros e quais numeros poderão ser usados.
    private int maximoCaracteres = -1;
    private String caracteres = null;

    //Metodo que vai receber qual tecla foi pressionada e chamar o evento verificador.
    public JtextFieldSomenteLetras(int maximo) {
        super();
        //Setando o maximo de caracteres e o quais numeros serão usados.
        setMaximoCaracteres(maximo);
        setCaracteres("@.,1234567890!#$%&*?");
        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldKeyTyped(evt);
            }
        });
    }
 
    //Evento que verifica qual tecla foi pressionada
    private void jTextFieldKeyTyped(KeyEvent evt) {
        if (caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
        //If que verifica a quantidade de caracteres digitados.
        if ((getText().length() >= getMaximoCaracteres()) && (getMaximoCaracteres() != -1)) {
            evt.consume();
            setText(getText().substring(0, getMaximoCaracteres()));
        }
    }

    public int getMaximoCaracteres() {
        return maximoCaracteres;
    }

    public void setMaximoCaracteres(int maximoCaracteres) {
        this.maximoCaracteres = maximoCaracteres;
    }

    public String getCaracteres() {
        return caracteres;
    }

    public void setCaracteres(String caracteres) {
        this.caracteres = caracteres;
    }
    
}

