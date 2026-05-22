package prog2.vista;

import prog2.adaptador.Adaptador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class AppBiblioUB extends JFrame{

    private Adaptador adaptador;

    private JButton btnGestioUsuaris;
    private JButton btnGestioPrestecs;
    private JButton btnGestioExemplars;
    private JButton btnGuardar;
    private JButton btnCarregar;

    private JPanel panelPrinc;
    private JLabel lblTitol;

    public AppBiblioUB() {
        adaptador = new Adaptador();

        setTitle("Aplicació Biblioteca de la UB - Menú");

        setSize(500,450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        inicialitzarAPP();
    }
    private void inicialitzarAPP(){
        //separem el titol dels botons
        setLayout(new BorderLayout(20,20));
        //cream els titols
        lblTitol = new JLabel("Gestió de la Biblioteca de la UB", SwingConstants.CENTER);
        lblTitol.setFont(new Font("Arial", Font.BOLD, 25) );
        lblTitol.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        add(lblTitol,BorderLayout.NORTH);

        //cream els panels

        panelPrinc = new JPanel();

        panelPrinc.setBorder(BorderFactory.createEmptyBorder(15,35,20,30));

        panelPrinc.setLayout(new GridLayout(5,1,0,20));

        //cream els botons

        btnGestioUsuaris   = new JButton("Gestió d'Usuaris");
        btnGestioPrestecs  = new JButton("Gestió de Préstecs");
        btnGestioExemplars = new JButton("Gestió d'Exemplars");
        btnGuardar= new JButton("Guardar Dades");
        btnCarregar= new JButton("Carregar Dades");

        //ho afegim
        panelPrinc.add(btnGestioUsuaris);
        panelPrinc.add(btnGestioPrestecs);
        panelPrinc.add(btnGestioExemplars);
        panelPrinc.add(btnGuardar);
        panelPrinc.add(btnCarregar);

        add(panelPrinc,BorderLayout.CENTER);

        missatgesClicarBoto();
    }

    private void missatgesClicarBoto(){
        //per gestio usuari
        btnGestioUsuaris.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmGestioUsuaris finestraUsuaris = new FrmGestioUsuaris(AppBiblioUB.this, adaptador);
                finestraUsuaris.setVisible(true);
            }
        });

        //per gestio prestecs
        btnGestioPrestecs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmGestioPrestecs finestraPrestecs = new FrmGestioPrestecs(AppBiblioUB.this, adaptador);
                JOptionPane.showMessageDialog(AppBiblioUB.this, "Opció: Gestió Préstecs, mostrar informació:","Informacio", JOptionPane.INFORMATION_MESSAGE);
                finestraPrestecs.setVisible(true);
            }
        });

        //per gestio exemplars
        btnGestioExemplars.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmGestioExemplars finestraExemplars = new FrmGestioExemplars(AppBiblioUB.this, adaptador);
                JOptionPane.showMessageDialog(AppBiblioUB.this, "Opció: Gestió Exemplars, mostrar informació:","Informacio", JOptionPane.INFORMATION_MESSAGE);
                finestraExemplars.setVisible(true);
            }
        });

        //per guardar dades
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Arxiu que vols guardar?");

                int opcio = fileChooser.showSaveDialog(AppBiblioUB.this);

                if(opcio == JFileChooser.APPROVE_OPTION){
                    File fitxer = fileChooser.getSelectedFile();

                    try{
                        adaptador.guardaDades(fitxer.getAbsolutePath());
                        JOptionPane.showMessageDialog(AppBiblioUB.this, "Dades guardades a : " + fitxer.getName(), "Finalitzat amb èxit", JOptionPane.INFORMATION_MESSAGE);
                    }
                    catch(BiblioException exc){
                        JOptionPane.showMessageDialog(AppBiblioUB.this,"Error al guardar", "Error",JOptionPane.ERROR_MESSAGE);


                    }
                }
            }
        });
        //per carregar dades
        btnCarregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();

                fileChooser.setDialogTitle("Selecciona l'arxiu que vols carregar");

                int opcio = fileChooser.showOpenDialog(AppBiblioUB.this);
                if(opcio == JFileChooser.APPROVE_OPTION){
                    File fitxer = fileChooser.getSelectedFile();
                    try{
                        adaptador.carregaDades(fitxer.getAbsolutePath());
                        JOptionPane.showMessageDialog(AppBiblioUB.this, "Dades carregades a " + fitxer.getName(), "Finalitzada amb exit", JOptionPane.INFORMATION_MESSAGE);
                    }
                    catch(BiblioException exc){
                        JOptionPane.showMessageDialog(AppBiblioUB.this, "Error en cargar" + exc.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);

                    }
                }
            }
        });
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                new AppBiblioUB().setVisible(true);
            }
        });
    }

}
