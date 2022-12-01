package InterfazGrafica;

import ClienteServidor.InterfaceVentServer;
import java.awt.Color;
import java.awt.Component;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JTextField;

// Esta clase solo sirve para colocar datos desde el cliente, no inicia el programa
// ni tiene ninguna interacción directa con el hospital. Para el correcto funcionamiento
// de los botones abrir/cerrar puesto, utilziamos la interfaz remota del servidor
// para llamar a los métodos correspondientes.
public final class VentanaV2Forma extends javax.swing.JFrame {

    private CamposVentana cv;
    private InterfaceVentServer ventanaRemota;

    public VentanaV2Forma(InterfaceVentServer ventanaRemota) {
        initComponents();

        // Cambios estéticos de la interfaz:
        //  Hacemos que aparezca centrada la ventana:
        setLocationRelativeTo(null);
        //  Y que no se pueda cambiar su tamaño:
        setResizable(false);
        //  Cambiamos el color de fondo:
        Color fondo = new Color(51, 51, 51);
        getContentPane().setBackground(fondo);

        // Inicializamos los campos de la ventana
        iniCamposVentanaObj();
        // Inicializamos la ventana remota
        this.ventanaRemota = ventanaRemota;
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelHospital = new javax.swing.JLabel();
        jPanelRecepcion = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaColaRecep = new javax.swing.JTextArea();
        jLabelAuxRecep = new javax.swing.JLabel();
        jLabelPacRecep = new javax.swing.JLabel();
        jTextFieldPacRecep = new javax.swing.JTextField();
        jTextFieldAuxRecep = new javax.swing.JTextField();
        jLabelRecepcion = new javax.swing.JLabel();
        jPanelSalEspera = new javax.swing.JPanel();
        jLabelSalEsp = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaSalaEsp = new javax.swing.JTextArea();
        jPanelSalDesc = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaSalDesc = new javax.swing.JTextArea();
        jPanelSalVac = new javax.swing.JPanel();
        jLabelSalVac = new javax.swing.JLabel();
        jTextFieldVacP1 = new javax.swing.JTextField();
        jLabelVac1 = new javax.swing.JLabel();
        jLabelVac2 = new javax.swing.JLabel();
        jTextFieldVacP2 = new javax.swing.JTextField();
        jLabelVac3 = new javax.swing.JLabel();
        jTextFieldVacP3 = new javax.swing.JTextField();
        jTextFieldVacP4 = new javax.swing.JTextField();
        jLabelVac4 = new javax.swing.JLabel();
        jTextFieldVacP5 = new javax.swing.JTextField();
        jLabelVac5 = new javax.swing.JLabel();
        jTextFieldVacP6 = new javax.swing.JTextField();
        jLabelVac6 = new javax.swing.JLabel();
        jTextFieldVacP7 = new javax.swing.JTextField();
        jLabelVac7 = new javax.swing.JLabel();
        jTextFieldVacP8 = new javax.swing.JTextField();
        jLabelVac8 = new javax.swing.JLabel();
        jTextFieldVacP9 = new javax.swing.JTextField();
        jLabelVac9 = new javax.swing.JLabel();
        jTextFieldVacP10 = new javax.swing.JTextField();
        jLabelVac10 = new javax.swing.JLabel();
        jTextFieldVacS1 = new javax.swing.JTextField();
        jTextFieldVacS3 = new javax.swing.JTextField();
        jTextFieldVacS2 = new javax.swing.JTextField();
        jTextFieldVacS4 = new javax.swing.JTextField();
        jTextFieldVacS5 = new javax.swing.JTextField();
        jTextFieldVacS6 = new javax.swing.JTextField();
        jTextFieldVacS7 = new javax.swing.JTextField();
        jTextFieldVacS10 = new javax.swing.JTextField();
        jTextFieldVacS8 = new javax.swing.JTextField();
        jTextFieldVacS9 = new javax.swing.JTextField();
        jButtonP1 = new javax.swing.JButton();
        jButtonP2 = new javax.swing.JButton();
        jButtonP3 = new javax.swing.JButton();
        jButtonP4 = new javax.swing.JButton();
        jButtonP5 = new javax.swing.JButton();
        jButtonP6 = new javax.swing.JButton();
        jButtonP7 = new javax.swing.JButton();
        jButtonP8 = new javax.swing.JButton();
        jButtonP9 = new javax.swing.JButton();
        jButtonP10 = new javax.swing.JButton();
        jPanelObs = new javax.swing.JPanel();
        jLabelSalObs = new javax.swing.JLabel();
        jLabelObs2 = new javax.swing.JLabel();
        jLabelObs1 = new javax.swing.JLabel();
        jTextFieldObs1 = new javax.swing.JTextField();
        jLabelObs4 = new javax.swing.JLabel();
        jTextFieldObs4 = new javax.swing.JTextField();
        jLabelObs3 = new javax.swing.JLabel();
        jTextFieldObs3 = new javax.swing.JTextField();
        jTextFieldObs5 = new javax.swing.JTextField();
        jLabelObs6 = new javax.swing.JLabel();
        jLabelObs5 = new javax.swing.JLabel();
        jTextFieldObs6 = new javax.swing.JTextField();
        jTextFieldObs7 = new javax.swing.JTextField();
        jLabelObs7 = new javax.swing.JLabel();
        jLabelObs8 = new javax.swing.JLabel();
        jTextFieldObs8 = new javax.swing.JTextField();
        jTextFieldObs9 = new javax.swing.JTextField();
        jLabelObs9 = new javax.swing.JLabel();
        jLabelObs10 = new javax.swing.JLabel();
        jTextFieldObs10 = new javax.swing.JTextField();
        jTextFieldObs11 = new javax.swing.JTextField();
        jLabelObs11 = new javax.swing.JLabel();
        jLabelObs12 = new javax.swing.JLabel();
        jTextFieldObs12 = new javax.swing.JTextField();
        jTextFieldObs13 = new javax.swing.JTextField();
        jLabelObs13 = new javax.swing.JLabel();
        jLabelObs14 = new javax.swing.JLabel();
        jTextFieldObs14 = new javax.swing.JTextField();
        jLabelObs16 = new javax.swing.JLabel();
        jTextFieldObs16 = new javax.swing.JTextField();
        jLabelObs15 = new javax.swing.JLabel();
        jTextFieldObs15 = new javax.swing.JTextField();
        jLabelObs18 = new javax.swing.JLabel();
        jTextFieldObs17 = new javax.swing.JTextField();
        jLabelObs17 = new javax.swing.JLabel();
        jTextFieldObs18 = new javax.swing.JTextField();
        jLabelObs20 = new javax.swing.JLabel();
        jTextFieldObs19 = new javax.swing.JTextField();
        jLabelObs19 = new javax.swing.JLabel();
        jTextFieldObs20 = new javax.swing.JTextField();
        jTextFieldObs2 = new javax.swing.JTextField();
        jPanelAuxDosis = new javax.swing.JPanel();
        jLabelPrepDosis = new javax.swing.JLabel();
        jTextFieldAuxDosis = new javax.swing.JTextField();
        jLabelAuxDosis = new javax.swing.JLabel();
        jTextFieldDosis = new javax.swing.JTextField();
        jLabelDosis = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1280, 720));

        jLabelHospital.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabelHospital.setForeground(new java.awt.Color(255, 255, 255));
        jLabelHospital.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelHospital.setText("HOSPITAL");

        jPanelRecepcion.setBackground(new java.awt.Color(204, 255, 204));
        jPanelRecepcion.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        jTextAreaColaRecep.setEditable(false);
        jTextAreaColaRecep.setColumns(20);
        jTextAreaColaRecep.setLineWrap(true);
        jTextAreaColaRecep.setRows(5);
        jTextAreaColaRecep.setWrapStyleWord(true);
        jScrollPane1.setViewportView(jTextAreaColaRecep);

        jLabelAuxRecep.setText("Auxiliar");

        jLabelPacRecep.setText("Paciente");

        jTextFieldPacRecep.setEditable(false);
        jTextFieldPacRecep.setToolTipText("");

        jTextFieldAuxRecep.setEditable(false);

        jLabelRecepcion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelRecepcion.setText("Recepción");

        javax.swing.GroupLayout jPanelRecepcionLayout = new javax.swing.GroupLayout(jPanelRecepcion);
        jPanelRecepcion.setLayout(jPanelRecepcionLayout);
        jPanelRecepcionLayout.setHorizontalGroup(
            jPanelRecepcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRecepcionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelRecepcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelRecepcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanelRecepcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldPacRecep)
                    .addComponent(jLabelPacRecep, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanelRecepcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldAuxRecep)
                    .addComponent(jLabelAuxRecep, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addGap(38, 38, 38))
        );
        jPanelRecepcionLayout.setVerticalGroup(
            jPanelRecepcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRecepcionLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelRecepcion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelRecepcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelRecepcionLayout.createSequentialGroup()
                        .addGroup(jPanelRecepcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelPacRecep)
                            .addComponent(jLabelAuxRecep))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelRecepcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldPacRecep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldAuxRecep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jPanelSalEspera.setBackground(new java.awt.Color(255, 204, 204));
        jPanelSalEspera.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        jLabelSalEsp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelSalEsp.setText("Sala de espera");

        jTextAreaSalaEsp.setEditable(false);
        jTextAreaSalaEsp.setColumns(20);
        jTextAreaSalaEsp.setLineWrap(true);
        jTextAreaSalaEsp.setRows(5);
        jTextAreaSalaEsp.setWrapStyleWord(true);
        jScrollPane2.setViewportView(jTextAreaSalaEsp);

        javax.swing.GroupLayout jPanelSalEsperaLayout = new javax.swing.GroupLayout(jPanelSalEspera);
        jPanelSalEspera.setLayout(jPanelSalEsperaLayout);
        jPanelSalEsperaLayout.setHorizontalGroup(
            jPanelSalEsperaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSalEsperaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSalEsperaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addComponent(jLabelSalEsp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelSalEsperaLayout.setVerticalGroup(
            jPanelSalEsperaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSalEsperaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelSalEsp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanelSalDesc.setBackground(new java.awt.Color(255, 255, 204));
        jPanelSalDesc.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sala de descanso");

        jTextAreaSalDesc.setEditable(false);
        jTextAreaSalDesc.setColumns(20);
        jTextAreaSalDesc.setLineWrap(true);
        jTextAreaSalDesc.setRows(5);
        jTextAreaSalDesc.setWrapStyleWord(true);
        jScrollPane3.setViewportView(jTextAreaSalDesc);

        javax.swing.GroupLayout jPanelSalDescLayout = new javax.swing.GroupLayout(jPanelSalDesc);
        jPanelSalDesc.setLayout(jPanelSalDescLayout);
        jPanelSalDescLayout.setHorizontalGroup(
            jPanelSalDescLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSalDescLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSalDescLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanelSalDescLayout.setVerticalGroup(
            jPanelSalDescLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSalDescLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3)
                .addContainerGap())
        );

        jPanelSalVac.setBackground(new java.awt.Color(204, 204, 255));
        jPanelSalVac.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        jLabelSalVac.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelSalVac.setText("Sala de vacunación");

        jTextFieldVacP1.setEditable(false);
        jTextFieldVacP1.setMinimumSize(new java.awt.Dimension(72, 20));

        jLabelVac1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelVac1.setText("Puesto 1");

        jLabelVac2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelVac2.setText("Puesto 2");

        jTextFieldVacP2.setEditable(false);

        jLabelVac3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelVac3.setText("Puesto 3");

        jTextFieldVacP3.setEditable(false);

        jTextFieldVacP4.setEditable(false);

        jLabelVac4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelVac4.setText("Puesto 4");

        jTextFieldVacP5.setEditable(false);

        jLabelVac5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelVac5.setText("Puesto 5");

        jTextFieldVacP6.setEditable(false);

        jLabelVac6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelVac6.setText("Puesto 6");

        jTextFieldVacP7.setEditable(false);

        jLabelVac7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelVac7.setText("Puesto 7");

        jTextFieldVacP8.setEditable(false);

        jLabelVac8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelVac8.setText("Puesto 8");

        jTextFieldVacP9.setEditable(false);

        jLabelVac9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelVac9.setText("Puesto 9");

        jTextFieldVacP10.setEditable(false);

        jLabelVac10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelVac10.setText("Puesto 10");

        jTextFieldVacS1.setEditable(false);

        jTextFieldVacS3.setEditable(false);

        jTextFieldVacS2.setEditable(false);

        jTextFieldVacS4.setEditable(false);

        jTextFieldVacS5.setEditable(false);

        jTextFieldVacS6.setEditable(false);

        jTextFieldVacS7.setEditable(false);

        jTextFieldVacS10.setEditable(false);

        jTextFieldVacS8.setEditable(false);

        jTextFieldVacS9.setEditable(false);

        jButtonP1.setBackground(new java.awt.Color(204, 204, 255));
        jButtonP1.setForeground(new java.awt.Color(255, 0, 0));
        jButtonP1.setText("Cerrar");
        jButtonP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonP1ActionPerformed(evt);
            }
        });

        jButtonP2.setBackground(new java.awt.Color(204, 204, 255));
        jButtonP2.setForeground(new java.awt.Color(255, 0, 0));
        jButtonP2.setText("Cerrar");
        jButtonP2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonP2ActionPerformed(evt);
            }
        });

        jButtonP3.setBackground(new java.awt.Color(204, 204, 255));
        jButtonP3.setForeground(new java.awt.Color(255, 0, 0));
        jButtonP3.setText("Cerrar");
        jButtonP3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonP3ActionPerformed(evt);
            }
        });

        jButtonP4.setBackground(new java.awt.Color(204, 204, 255));
        jButtonP4.setForeground(new java.awt.Color(255, 0, 0));
        jButtonP4.setText("Cerrar");
        jButtonP4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonP4ActionPerformed(evt);
            }
        });

        jButtonP5.setBackground(new java.awt.Color(204, 204, 255));
        jButtonP5.setForeground(new java.awt.Color(255, 0, 0));
        jButtonP5.setText("Cerrar");
        jButtonP5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonP5ActionPerformed(evt);
            }
        });

        jButtonP6.setBackground(new java.awt.Color(204, 204, 255));
        jButtonP6.setForeground(new java.awt.Color(255, 0, 0));
        jButtonP6.setText("Cerrar");
        jButtonP6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonP6ActionPerformed(evt);
            }
        });

        jButtonP7.setBackground(new java.awt.Color(204, 204, 255));
        jButtonP7.setForeground(new java.awt.Color(255, 0, 0));
        jButtonP7.setText("Cerrar");
        jButtonP7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonP7ActionPerformed(evt);
            }
        });

        jButtonP8.setBackground(new java.awt.Color(204, 204, 255));
        jButtonP8.setForeground(new java.awt.Color(255, 0, 0));
        jButtonP8.setText("Cerrar");
        jButtonP8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonP8ActionPerformed(evt);
            }
        });

        jButtonP9.setBackground(new java.awt.Color(204, 204, 255));
        jButtonP9.setForeground(new java.awt.Color(255, 0, 0));
        jButtonP9.setText("Cerrar");
        jButtonP9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonP9ActionPerformed(evt);
            }
        });

        jButtonP10.setBackground(new java.awt.Color(204, 204, 255));
        jButtonP10.setForeground(new java.awt.Color(255, 0, 0));
        jButtonP10.setText("Cerrar");
        jButtonP10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonP10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelSalVacLayout = new javax.swing.GroupLayout(jPanelSalVac);
        jPanelSalVac.setLayout(jPanelSalVacLayout);
        jPanelSalVacLayout.setHorizontalGroup(
            jPanelSalVacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelSalVac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelSalVacLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanelSalVacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSalVacLayout.createSequentialGroup()
                        .addComponent(jButtonP1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonP2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonP3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonP4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonP5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonP6, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonP7, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonP8, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonP9, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonP10, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelSalVacLayout.createSequentialGroup()
                        .addGroup(jPanelSalVacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSalVacLayout.createSequentialGroup()
                                .addGroup(jPanelSalVacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelVac1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldVacP1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelSalVacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldVacP2)
                                    .addComponent(jLabelVac2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanelSalVacLayout.createSequentialGroup()
                                .addComponent(jTextFieldVacS1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldVacS2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelSalVacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelSalVacLayout.createSequentialGroup()
                                .addGroup(jPanelSalVacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldVacP3)
                                    .addComponent(jLabelVac3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelSalVacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldVacP4)
                                    .addComponent(jLabelVac4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelSalVacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldVacP5)
                                    .addComponent(jLabelVac5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelSalVacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldVacP6)
                                    .addComponent(jLabelVac6, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanelSalVacLayout.createSequentialGroup()
                                .addComponent(jTextFieldVacS3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldVacS4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldVacS5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldVacS6, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelSalVacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelSalVacLayout.createSequentialGroup()
                                .addGroup(jPanelSalVacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldVacP7)
                                    .addComponent(jLabelVac7, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelSalVacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldVacP8)
                                    .addComponent(jLabelVac8, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelSalVacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldVacP9)
                                    .addComponent(jLabelVac9, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelSalVacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldVacP10)
                                    .addComponent(jLabelVac10, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanelSalVacLayout.createSequentialGroup()
                                .addComponent(jTextFieldVacS7, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldVacS8, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldVacS9, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldVacS10, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanelSalVacLayout.setVerticalGroup(
            jPanelSalVacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSalVacLayout.createSequentialGroup()
                .addComponent(jLabelSalVac, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelSalVacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelSalVacLayout.createSequentialGroup()
                        .addComponent(jLabelVac3)
                        .addGap(3, 3, 3)
                        .addComponent(jTextFieldVacP3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelSalVacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanelSalVacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelSalVacLayout.createSequentialGroup()
                                .addComponent(jLabelVac4)
                                .addGap(3, 3, 3)
                                .addComponent(jTextFieldVacP4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelSalVacLayout.createSequentialGroup()
                                .addGroup(jPanelSalVacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabelVac1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelVac2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(3, 3, 3)
                                .addGroup(jPanelSalVacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldVacP2)
                                    .addComponent(jTextFieldVacP1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGroup(jPanelSalVacLayout.createSequentialGroup()
                            .addComponent(jLabelVac5)
                            .addGap(3, 3, 3)
                            .addComponent(jTextFieldVacP5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelSalVacLayout.createSequentialGroup()
                            .addComponent(jLabelVac6)
                            .addGap(3, 3, 3)
                            .addComponent(jTextFieldVacP6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelSalVacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelSalVacLayout.createSequentialGroup()
                            .addComponent(jLabelVac8)
                            .addGap(3, 3, 3)
                            .addComponent(jTextFieldVacP8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanelSalVacLayout.createSequentialGroup()
                            .addComponent(jLabelVac7)
                            .addGap(3, 3, 3)
                            .addComponent(jTextFieldVacP7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanelSalVacLayout.createSequentialGroup()
                            .addComponent(jLabelVac9)
                            .addGap(3, 3, 3)
                            .addComponent(jTextFieldVacP9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanelSalVacLayout.createSequentialGroup()
                            .addComponent(jLabelVac10)
                            .addGap(3, 3, 3)
                            .addComponent(jTextFieldVacP10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelSalVacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSalVacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldVacS1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldVacS3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldVacS2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldVacS4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldVacS5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldVacS6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldVacS7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextFieldVacS8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelSalVacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldVacS10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldVacS9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelSalVacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonP1)
                    .addComponent(jButtonP2)
                    .addComponent(jButtonP3)
                    .addComponent(jButtonP4)
                    .addComponent(jButtonP5)
                    .addComponent(jButtonP6)
                    .addComponent(jButtonP7)
                    .addComponent(jButtonP8)
                    .addComponent(jButtonP9)
                    .addComponent(jButtonP10))
                .addContainerGap())
        );

        jPanelObs.setBackground(new java.awt.Color(255, 204, 255));
        jPanelObs.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        jLabelSalObs.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelSalObs.setText("Sala de observación");

        jLabelObs2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelObs2.setText("Puesto 2");

        jLabelObs1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelObs1.setText("Puesto 1");

        jTextFieldObs1.setEditable(false);

        jLabelObs4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelObs4.setText("Puesto 4");

        jTextFieldObs4.setEditable(false);

        jLabelObs3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelObs3.setText("Puesto 3");

        jTextFieldObs3.setEditable(false);

        jTextFieldObs5.setEditable(false);

        jLabelObs6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelObs6.setText("Puesto 6");

        jLabelObs5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelObs5.setText("Puesto 5");

        jTextFieldObs6.setEditable(false);

        jTextFieldObs7.setEditable(false);

        jLabelObs7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelObs7.setText("Puesto 7");

        jLabelObs8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelObs8.setText("Puesto 8");

        jTextFieldObs8.setEditable(false);

        jTextFieldObs9.setEditable(false);

        jLabelObs9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelObs9.setText("Puesto 9");

        jLabelObs10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelObs10.setText("Puesto 10");

        jTextFieldObs10.setEditable(false);

        jTextFieldObs11.setEditable(false);

        jLabelObs11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelObs11.setText("Puesto 11");

        jLabelObs12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelObs12.setText("Puesto 12");

        jTextFieldObs12.setEditable(false);

        jTextFieldObs13.setEditable(false);

        jLabelObs13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelObs13.setText("Puesto 13");

        jLabelObs14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelObs14.setText("Puesto 14");

        jTextFieldObs14.setEditable(false);

        jLabelObs16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelObs16.setText("Puesto 16");

        jTextFieldObs16.setEditable(false);

        jLabelObs15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelObs15.setText("Puesto 15");

        jTextFieldObs15.setEditable(false);

        jLabelObs18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelObs18.setText("Puesto 18");

        jTextFieldObs17.setEditable(false);

        jLabelObs17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelObs17.setText("Puesto 17");

        jTextFieldObs18.setEditable(false);

        jLabelObs20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelObs20.setText("Puesto 20");

        jTextFieldObs19.setEditable(false);

        jLabelObs19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelObs19.setText("Puesto 19");

        jTextFieldObs20.setEditable(false);

        jTextFieldObs2.setEditable(false);

        javax.swing.GroupLayout jPanelObsLayout = new javax.swing.GroupLayout(jPanelObs);
        jPanelObs.setLayout(jPanelObsLayout);
        jPanelObsLayout.setHorizontalGroup(
            jPanelObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelObsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldObs1)
                    .addComponent(jLabelObs1, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                    .addComponent(jLabelObs2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldObs2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelObs3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldObs3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldObs4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelObs4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelObs5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldObs5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldObs6, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelObs6, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelObs7, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldObs7, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldObs8, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelObs8, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelObs9, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldObs9, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldObs10, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelObs10, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelObs11, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldObs11, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldObs12, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelObs12, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelObs13, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldObs13, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldObs14, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelObs14, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelObs15, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldObs15, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldObs16, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelObs16, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelObs17, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldObs17, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldObs18, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelObs18, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelObs19, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldObs19, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldObs20, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelObs20, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addComponent(jLabelSalObs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelObsLayout.setVerticalGroup(
            jPanelObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelObsLayout.createSequentialGroup()
                .addComponent(jLabelSalObs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelObsLayout.createSequentialGroup()
                            .addGroup(jPanelObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelObs3)
                                .addComponent(jLabelObs1))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanelObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextFieldObs3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldObs1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelObsLayout.createSequentialGroup()
                            .addComponent(jLabelObs5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextFieldObs5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelObsLayout.createSequentialGroup()
                                .addGroup(jPanelObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelObs10)
                                    .addComponent(jLabelObs8)
                                    .addComponent(jLabelObs6)
                                    .addComponent(jLabelObs4)
                                    .addComponent(jLabelObs2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldObs10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldObs8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldObs6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldObs4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldObs2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanelObsLayout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jLabelObs9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldObs9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelObsLayout.createSequentialGroup()
                                .addComponent(jLabelObs12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldObs12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelObsLayout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jLabelObs11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldObs11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelObsLayout.createSequentialGroup()
                                .addComponent(jLabelObs14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldObs14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelObsLayout.createSequentialGroup()
                                .addComponent(jLabelObs16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldObs16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanelObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelObsLayout.createSequentialGroup()
                                        .addComponent(jLabelObs18)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldObs18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelObsLayout.createSequentialGroup()
                                        .addComponent(jLabelObs20)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldObs20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelObsLayout.createSequentialGroup()
                                        .addGap(46, 46, 46)
                                        .addGroup(jPanelObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanelObsLayout.createSequentialGroup()
                                                .addComponent(jLabelObs17)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextFieldObs17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanelObsLayout.createSequentialGroup()
                                                .addComponent(jLabelObs19)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextFieldObs19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGroup(jPanelObsLayout.createSequentialGroup()
                                    .addGap(46, 46, 46)
                                    .addGroup(jPanelObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanelObsLayout.createSequentialGroup()
                                            .addComponent(jLabelObs13)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextFieldObs13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanelObsLayout.createSequentialGroup()
                                            .addComponent(jLabelObs15)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextFieldObs15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelObsLayout.createSequentialGroup()
                        .addComponent(jLabelObs7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldObs7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelAuxDosis.setBackground(new java.awt.Color(204, 255, 255));
        jPanelAuxDosis.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        jLabelPrepDosis.setText("Sala de preparacion de dosis");

        jTextFieldAuxDosis.setEditable(false);

        jLabelAuxDosis.setText("Auxiliar");

        jTextFieldDosis.setEditable(false);

        jLabelDosis.setText("Dosis listas");

        javax.swing.GroupLayout jPanelAuxDosisLayout = new javax.swing.GroupLayout(jPanelAuxDosis);
        jPanelAuxDosis.setLayout(jPanelAuxDosisLayout);
        jPanelAuxDosisLayout.setHorizontalGroup(
            jPanelAuxDosisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAuxDosisLayout.createSequentialGroup()
                .addGroup(jPanelAuxDosisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAuxDosisLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelPrepDosis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelAuxDosisLayout.createSequentialGroup()
                        .addGroup(jPanelAuxDosisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelAuxDosisLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(jPanelAuxDosisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldAuxDosis)
                                    .addComponent(jLabelAuxDosis, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)))
                            .addGroup(jPanelAuxDosisLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanelAuxDosisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelDosis)
                                    .addComponent(jTextFieldDosis, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelAuxDosisLayout.setVerticalGroup(
            jPanelAuxDosisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAuxDosisLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelPrepDosis)
                .addGap(22, 22, 22)
                .addComponent(jLabelAuxDosis)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldAuxDosis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelDosis)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldDosis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelHospital, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanelRecepcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanelSalEspera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanelAuxDosis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanelSalDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanelObs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelSalVac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelHospital)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelAuxDosis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelSalEspera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelRecepcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelSalDesc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelSalVac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanelObs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jButtonP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonP1ActionPerformed
        abrirCerrarPuestoRemoto(0);
    }//GEN-LAST:event_jButtonP1ActionPerformed

    private void jButtonP2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonP2ActionPerformed
        abrirCerrarPuestoRemoto(1);
    }//GEN-LAST:event_jButtonP2ActionPerformed

    private void jButtonP3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonP3ActionPerformed
        abrirCerrarPuestoRemoto(2);
    }//GEN-LAST:event_jButtonP3ActionPerformed

    private void jButtonP4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonP4ActionPerformed
        abrirCerrarPuestoRemoto(3);
    }//GEN-LAST:event_jButtonP4ActionPerformed

    private void jButtonP5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonP5ActionPerformed
        abrirCerrarPuestoRemoto(4);
    }//GEN-LAST:event_jButtonP5ActionPerformed

    private void jButtonP6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonP6ActionPerformed
        abrirCerrarPuestoRemoto(5);
    }//GEN-LAST:event_jButtonP6ActionPerformed

    private void jButtonP7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonP7ActionPerformed
        abrirCerrarPuestoRemoto(6);
    }//GEN-LAST:event_jButtonP7ActionPerformed

    private void jButtonP8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonP8ActionPerformed
        abrirCerrarPuestoRemoto(7);
    }//GEN-LAST:event_jButtonP8ActionPerformed

    private void jButtonP9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonP9ActionPerformed
        abrirCerrarPuestoRemoto(8);
    }//GEN-LAST:event_jButtonP9ActionPerformed

    private void jButtonP10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonP10ActionPerformed
        abrirCerrarPuestoRemoto(9);
    }//GEN-LAST:event_jButtonP10ActionPerformed

    private void abrirCerrarPuestoRemoto(int nPuesto) {
        try {
            ventanaRemota.abrirCerrarPuesto(nPuesto);
        } catch (RemoteException ex) {
            System.out.println("Error al intentar abrir/cerrar el puesto 2 de"
                    + "la ventana remota");
        }
    }

    public CamposVentana getCamposVentana() {
        return cv;
    }
    
    /**
     * Rellena la ventana con toda la información recibida.
     * @param info Debe contener en la primera posición un objeto tipo 
     * CamposVentana y otro que sea un array de booleanos indicando qué puestos
     * están abiertos y cuáles no.
     */
    public void rellenarInfoVentana(Object[] info) {
        CamposVentana camposV = (CamposVentana) info[0];
        // JTextFields del panel de vacunación
        Component[] componentesSalVac = jPanelSalVac.getComponents();
        JTextField[] textosSalVacPac = camposV.getTextVacPac();
        JTextField[] textosSalVacSan = camposV.getTextVacSan();
        ArrayList<Integer> indPac = new ArrayList<>(
                Arrays.asList(0, 1, 4, 5, 6, 7, 12, 13, 14, 15));
        int i = 0, iPac = 0, iSan = 0;
        for (Component comp : componentesSalVac) {
            if (comp instanceof JTextField) { // para no coger labels
                if (indPac.contains(i)) {
                    ((JTextField) comp).setText(textosSalVacPac[iPac].getText());
                    iPac++;
                } else {
                    ((JTextField) comp).setText(textosSalVacSan[iSan].getText());
                    iSan++;
                }
                i++;
            }
        }
        // JTextFields del panel de observación
        Component[] componentesSalObs = jPanelObs.getComponents();
        i = 0;
        JTextField[] textosSalObs = camposV.getTextObs();
        for (Component comp : componentesSalObs) {
            if (comp instanceof JTextField) {
                ((JTextField) comp).setText(textosSalObs[i].getText());
                i++;
            }
        }
        // Textos preparacion dosis:
        jTextFieldAuxDosis.setText(camposV.getAuxDosis().getText());
        jTextFieldDosis.setText(camposV.getDosis().getText());
        // Textos recepcion:
        jTextFieldAuxRecep.setText(camposV.getAuxRecep().getText());
        jTextFieldPacRecep.setText(camposV.getPacRecep().getText());
        jTextAreaColaRecep.setText(camposV.getColaRecep().getText());
        // Texto sala espera:
        jTextAreaSalaEsp.setText(camposV.getSalEsp().getText());
        // Texto sala descanso:
        jTextAreaSalDesc.setText(camposV.getSalDesc().getText());

        // Estado de los puestos (abiertos/cerrados)
        reflejarEstadoPuestosEnBotones((boolean[]) info[1]);
    }

    /**
     * Rellena con los strings adecuados en cada botón. "Abrir" si está cerrado
     * y "Cerrar" si está abierto el puesto correspondiente a dicho botón.
     * @param puestosCerrados indica que puestos están cerrados y cuales no.
     * Debe tener longitud 10.
     */
    private void reflejarEstadoPuestosEnBotones(boolean[] puestosCerrados) {
        for (int i = 0; i < 10; i++) {
            switch (i) {
                case (0):
                    cambiarEstadoBoton(puestosCerrados[i], jButtonP1);
                    break;
                case (1):
                    cambiarEstadoBoton(puestosCerrados[i], jButtonP2);
                    break;
                case (2):
                    cambiarEstadoBoton(puestosCerrados[i], jButtonP3);
                    break;
                case (3):
                    cambiarEstadoBoton(puestosCerrados[i], jButtonP4);
                    break;
                case (4):
                    cambiarEstadoBoton(puestosCerrados[i], jButtonP5);
                    break;
                case (5):
                    cambiarEstadoBoton(puestosCerrados[i], jButtonP6);
                    break;
                case (6):
                    cambiarEstadoBoton(puestosCerrados[i], jButtonP7);
                    break;
                case (7):
                    cambiarEstadoBoton(puestosCerrados[i], jButtonP8);
                    break;
                case (8):
                    cambiarEstadoBoton(puestosCerrados[i], jButtonP9);
                    break;
                case (9):
                    cambiarEstadoBoton(puestosCerrados[i], jButtonP10);
                    break;

            }
        }
    }

    /**
     * Cambia el estado de un solo botón."Abrir" si está cerrado
     * y "Cerrar" si está abierto.
     * @param cerrado Indica si está cerrado o abierto el puesto correspondiente.
     * @param boton Botón cuyas propiedades se desean cambiar.
     */
    private void cambiarEstadoBoton(boolean cerrado, JButton boton) {
        if(boton.getText().equals("Cerrar") && cerrado){
            // lo ponemos a "Abrir"
            boton.setText("Abrir");
            boton.setForeground(Color.green);
        }
        else if(boton.getText().equals("Abrir") && !cerrado){
            // lo ponemos a "Cerrar"
            boton.setText("Cerrar");
            boton.setForeground(Color.red);
        }
    }
    
    /**
     * Inicializa el objeto CamposVentana con todos los campos de texto de la
     * interfaz. Dicho objeto es necesario para el constructor del Hospital,
     * clase central del programa.
     */
    public void iniCamposVentanaObj() {
        // JTextFields del panel de vacunación
        Component[] componentesSalVac = jPanelSalVac.getComponents();
        // Creamos un array con los índices que tienen los jtextfield de pacientes
        // dentro del panel
        ArrayList<Integer> indPac = new ArrayList<>(
                Arrays.asList(0, 1, 4, 5, 6, 7, 12, 13, 14, 15));
        int i = 0, iPac = 0, iSan = 0;
        JTextField[] textVacPac = new JTextField[10];
        JTextField[] textVacSan = new JTextField[10];
        for (Component comp : componentesSalVac) {
            if (comp instanceof JTextField) { // para no coger labels
                if (indPac.contains(i)) {
                    textVacPac[iPac] = (JTextField) comp;
                    iPac++;
                } else {
                    textVacSan[iSan] = (JTextField) comp;
                    iSan++;
                }
                i++;
            }
        }
        // JTextFields del panel de observación
        Component[] componentesSalObs = jPanelObs.getComponents();
        i = 0;
        JTextField[] textObs = new JTextField[20];
        for (Component comp : componentesSalObs) {
            if (comp instanceof JTextField) {
                textObs[i] = (JTextField) comp;
                i++;
            }
        }
        // Creamos el objeto con todo:
        cv = new CamposVentana(jTextAreaColaRecep, jTextAreaSalaEsp, jTextAreaSalDesc,
                jTextFieldPacRecep, jTextFieldAuxRecep, jTextFieldAuxDosis, jTextFieldDosis,
                textVacPac, textVacSan, textObs);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonP1;
    private javax.swing.JButton jButtonP10;
    private javax.swing.JButton jButtonP2;
    private javax.swing.JButton jButtonP3;
    private javax.swing.JButton jButtonP4;
    private javax.swing.JButton jButtonP5;
    private javax.swing.JButton jButtonP6;
    private javax.swing.JButton jButtonP7;
    private javax.swing.JButton jButtonP8;
    private javax.swing.JButton jButtonP9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelAuxDosis;
    private javax.swing.JLabel jLabelAuxRecep;
    private javax.swing.JLabel jLabelDosis;
    private javax.swing.JLabel jLabelHospital;
    private javax.swing.JLabel jLabelObs1;
    private javax.swing.JLabel jLabelObs10;
    private javax.swing.JLabel jLabelObs11;
    private javax.swing.JLabel jLabelObs12;
    private javax.swing.JLabel jLabelObs13;
    private javax.swing.JLabel jLabelObs14;
    private javax.swing.JLabel jLabelObs15;
    private javax.swing.JLabel jLabelObs16;
    private javax.swing.JLabel jLabelObs17;
    private javax.swing.JLabel jLabelObs18;
    private javax.swing.JLabel jLabelObs19;
    private javax.swing.JLabel jLabelObs2;
    private javax.swing.JLabel jLabelObs20;
    private javax.swing.JLabel jLabelObs3;
    private javax.swing.JLabel jLabelObs4;
    private javax.swing.JLabel jLabelObs5;
    private javax.swing.JLabel jLabelObs6;
    private javax.swing.JLabel jLabelObs7;
    private javax.swing.JLabel jLabelObs8;
    private javax.swing.JLabel jLabelObs9;
    private javax.swing.JLabel jLabelPacRecep;
    private javax.swing.JLabel jLabelPrepDosis;
    private javax.swing.JLabel jLabelRecepcion;
    private javax.swing.JLabel jLabelSalEsp;
    private javax.swing.JLabel jLabelSalObs;
    private javax.swing.JLabel jLabelSalVac;
    private javax.swing.JLabel jLabelVac1;
    private javax.swing.JLabel jLabelVac10;
    private javax.swing.JLabel jLabelVac2;
    private javax.swing.JLabel jLabelVac3;
    private javax.swing.JLabel jLabelVac4;
    private javax.swing.JLabel jLabelVac5;
    private javax.swing.JLabel jLabelVac6;
    private javax.swing.JLabel jLabelVac7;
    private javax.swing.JLabel jLabelVac8;
    private javax.swing.JLabel jLabelVac9;
    private javax.swing.JPanel jPanelAuxDosis;
    private javax.swing.JPanel jPanelObs;
    private javax.swing.JPanel jPanelRecepcion;
    private javax.swing.JPanel jPanelSalDesc;
    private javax.swing.JPanel jPanelSalEspera;
    private javax.swing.JPanel jPanelSalVac;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextAreaColaRecep;
    private javax.swing.JTextArea jTextAreaSalDesc;
    private javax.swing.JTextArea jTextAreaSalaEsp;
    private javax.swing.JTextField jTextFieldAuxDosis;
    private javax.swing.JTextField jTextFieldAuxRecep;
    private javax.swing.JTextField jTextFieldDosis;
    private javax.swing.JTextField jTextFieldObs1;
    private javax.swing.JTextField jTextFieldObs10;
    private javax.swing.JTextField jTextFieldObs11;
    private javax.swing.JTextField jTextFieldObs12;
    private javax.swing.JTextField jTextFieldObs13;
    private javax.swing.JTextField jTextFieldObs14;
    private javax.swing.JTextField jTextFieldObs15;
    private javax.swing.JTextField jTextFieldObs16;
    private javax.swing.JTextField jTextFieldObs17;
    private javax.swing.JTextField jTextFieldObs18;
    private javax.swing.JTextField jTextFieldObs19;
    private javax.swing.JTextField jTextFieldObs2;
    private javax.swing.JTextField jTextFieldObs20;
    private javax.swing.JTextField jTextFieldObs3;
    private javax.swing.JTextField jTextFieldObs4;
    private javax.swing.JTextField jTextFieldObs5;
    private javax.swing.JTextField jTextFieldObs6;
    private javax.swing.JTextField jTextFieldObs7;
    private javax.swing.JTextField jTextFieldObs8;
    private javax.swing.JTextField jTextFieldObs9;
    private javax.swing.JTextField jTextFieldPacRecep;
    private javax.swing.JTextField jTextFieldVacP1;
    private javax.swing.JTextField jTextFieldVacP10;
    private javax.swing.JTextField jTextFieldVacP2;
    private javax.swing.JTextField jTextFieldVacP3;
    private javax.swing.JTextField jTextFieldVacP4;
    private javax.swing.JTextField jTextFieldVacP5;
    private javax.swing.JTextField jTextFieldVacP6;
    private javax.swing.JTextField jTextFieldVacP7;
    private javax.swing.JTextField jTextFieldVacP8;
    private javax.swing.JTextField jTextFieldVacP9;
    private javax.swing.JTextField jTextFieldVacS1;
    private javax.swing.JTextField jTextFieldVacS10;
    private javax.swing.JTextField jTextFieldVacS2;
    private javax.swing.JTextField jTextFieldVacS3;
    private javax.swing.JTextField jTextFieldVacS4;
    private javax.swing.JTextField jTextFieldVacS5;
    private javax.swing.JTextField jTextFieldVacS6;
    private javax.swing.JTextField jTextFieldVacS7;
    private javax.swing.JTextField jTextFieldVacS8;
    private javax.swing.JTextField jTextFieldVacS9;
    // End of variables declaration//GEN-END:variables
}
