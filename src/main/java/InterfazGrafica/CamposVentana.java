package InterfazGrafica;

import java.io.Serializable;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class CamposVentana implements Serializable{
    private JTextField[] textVacPac;
    private JTextField[] textVacSan;
    private JTextField[] textObs;
    private JTextArea colaRecep, salEsp, salDesc;
    private JTextField pacRecep, auxRecep, auxDosis, dosis;

    // Constructor:
    public CamposVentana(JTextArea colaRecep, JTextArea salEsp, JTextArea salDesc,
            JTextField pacRecep, JTextField auxRecep, JTextField auxDosis,
            JTextField dosis, JTextField[] textVacPac, JTextField[] textVacSan,
            JTextField[] textObs) {
        
        this.textVacPac = textVacPac;
        this.textVacSan = textVacSan;
        this.textObs = textObs;
        this.colaRecep = colaRecep;
        this.salEsp = salEsp;
        this.salDesc = salDesc;
        this.pacRecep = pacRecep;
        this.auxRecep = auxRecep;
        this.auxDosis = auxDosis;
        this.dosis = dosis;
    }

    // Getters:
    public JTextField[] getTextVacPac() {
        return textVacPac;
    }

    public JTextField[] getTextVacSan() {
        return textVacSan;
    }

    public JTextField[] getTextObs() {
        return textObs;
    }

    public JTextArea getColaRecep() {
        return colaRecep;
    }

    public JTextArea getSalEsp() {
        return salEsp;
    }

    public JTextArea getSalDesc() {
        return salDesc;
    }

    public JTextField getPacRecep() {
        return pacRecep;
    }

    public JTextField getAuxRecep() {
        return auxRecep;
    }

    public JTextField getAuxDosis() {
        return auxDosis;
    }

    public JTextField getDosis() {
        return dosis;
    }
    
}
