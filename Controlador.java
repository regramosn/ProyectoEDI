/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculadora;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *<pre>
 * Clase Controlador
 * </pre>
 * @author Rosa Gutierrez
 */
public class Controlador extends Vista{
    private double res;
    protected StringBuilder cad= new StringBuilder();
    protected String[] arr= new String[1000];
     
    /**
     * <pre>
     * Controlador
     * 
     * Aquí se une a los botones la sus acciones de Escucha para darles una funcionalidad
     * a cada uno de los números y de los operadores.
     * </pre>
     */
    public Controlador () {
        super();
        uno.addActionListener(new EscuchadorNums());
	dos.addActionListener(new EscuchadorNums());
	tres.addActionListener(new EscuchadorNums());
	cuatro.addActionListener(new EscuchadorNums());
	cinco.addActionListener(new EscuchadorNums());
	seis.addActionListener(new EscuchadorNums());
	siete.addActionListener(new EscuchadorNums());
	ocho.addActionListener(new EscuchadorNums());
	nueve.addActionListener(new EscuchadorNums());
	cero.addActionListener(new EscuchadorNums());
	multiplicacion.addActionListener(new EscuchadorOps());
	division.addActionListener(new EscuchadorOps());
	suma.addActionListener(new EscuchadorOps());
        resta.addActionListener(new EscuchadorOps());
        punto.addActionListener(new EscuchadorOps());
        potencia.addActionListener(new EscuchadorPow());
        parentesis1.addActionListener(new EscuchadorPar());
        parentesis2.addActionListener(new EscuchadorPar());
	igual.addActionListener(new EscuchadorIgual());
        borrar.addActionListener(new EscuchadorBorrar());
    }
    /**
     * <pre>
     * Clase EscuchadorNums
     * 
     * Esta clase da a los botones de números el valor númerico que representa
     * </pre>
     */
    private class EscuchadorNums implements ActionListener {
		
        @Override
        public void actionPerformed(ActionEvent ae) {
            String txt = ae.getActionCommand();
            cad.append(txt);
            screen.setText(cad.toString());
        }
    }
    /**
     * <pre>
     * Clase EscuchadorOps
     * 
     * Esta clase otorga a los botones de operadores, el símbolo de la operación que representa
     * </pre> 
     */
    private class EscuchadorOps implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String txt = ae.getActionCommand();
            cad.append(txt);
            screen.setText(cad.toString());
        }
        
    }
    /**
     * <pre>
     * Clase EscuchadorPow
     * 
     * Esta clase incluye el caso especial del símbolo de potencia que tuvo que ser reemplazado por inconsistencias
     * de escritura en el código con el símbolo "^".
     * </pre> 
     */
    private class EscuchadorPow implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            cad.append("?");
            screen.setText(cad.toString());
        }
    }
    /**
     * <pre>
     * Clase EscuchadorPar
     * 
     * Esta clase da a la representación de paréntesis el valor de paréntesis.
     * </pre> 
     */
    private class EscuchadorPar implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            String txt = ae.getActionCommand();
            cad.append(txt);
            screen.setText(cad.toString());
        }
    }
    
    /**
     * <pre>
     * Clase EscuchadorIgual
     * 
     * Esta clase le da al boton "=" la capacidad de resolver la operación dada o mostrar error
     * en el caso de que no este bien escrita.
     * </pre> 
     */
    private class EscuchadorIgual implements ActionListener {
        String[] arr;
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            if(DeteccionErrores.detectaErrores(cad.toString())) {
                arr= MetodosCalc.convertirStringArr(cad.toString());
                try{
                    res= MetodosCalc.calculadora(arr);
                    screen.setText(Double.toString(res)); 
                }
                catch(ArithmeticException error) {
                    screen.setText("ERROR MATEMATICO");
                } 
            }
            else {
                screen.setText("ERROR DE SINTAXIS");
            }
        }
    } 
    /**
     * <pre>
     * Clase EscuchadorBorrar
     * 
     * Esta clase da al botón borrar la capacidad de poder borrar el último elemto agregado.
     * </pre> 
     */
    private class EscuchadorBorrar implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            cad.setLength(0);
            res=0;
            screen.setText("");
        }
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Controlador().setVisible(true);
            }
        });
    }
}
