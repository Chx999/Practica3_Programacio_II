package prog2.vista;

import prog2.adaptador.Adaptador;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FrmGestioPrestecs extends JDialog {
    private Adaptador adaptador;

    private JComboBox<String> cmbUsuaris;
    private JComboBox<String> cmbExemplars;
    private JCheckBox chkEsLlarg;
    private JButton btnAfegir;

    private JList<String> lstPrestecs;
    private DefaultListModel<String> modelLlista;
    private JCheckBox chkFiltreNoRetornats;
    private JButton btnRetornar;

    private JButton btnCancelar;

    public FrmGestioPrestecs(JFrame original, Adaptador adaptador) {
        super(original, "Gestió de Préstecs", true);
        this.adaptador = adaptador;

        setSize(600, 400);
        setLocationRelativeTo(original);
        setLayout(new BorderLayout(15, 15));

        inicialitzar();
        refresh();
    }

    private void refresh() {
        modelLlista.clear();
        List<String> llista;

        if (chkFiltreNoRetornats.isSelected()) {
            llista = adaptador.recuperaPrestecsNoRetornats();
        } else {
            llista = adaptador.recuperaPrestecs();
        }

        for (int i = 0; i < llista.size(); i++) {
            modelLlista.addElement(llista.get(i));
        }

        cmbUsuaris.removeAllItems();
        List<String> usuaris = adaptador.recuperaUsuaris();
        for (int i = 0; i < usuaris.size(); i++) {
            cmbUsuaris.addItem(usuaris.get(i));
        }

        cmbExemplars.removeAllItems();
        List<String> exemplars = adaptador.recuperaExemplars();
        for (int i = 0; i < exemplars.size(); i++) {
            cmbExemplars.addItem(exemplars.get(i));
        }
    }

    private void inicialitzar() {
        JPanel panelEsquerra = new JPanel(new BorderLayout(10, 10));
        panelEsquerra.setBorder(BorderFactory.createTitledBorder("Préstecs"));

        modelLlista = new DefaultListModel<>();
        lstPrestecs = new JList<>(modelLlista);
        JScrollPane scroll = new JScrollPane(lstPrestecs);
        panelEsquerra.add(scroll, BorderLayout.CENTER);

        JPanel panelFiltres = new JPanel(new FlowLayout(FlowLayout.LEFT));
        chkFiltreNoRetornats = new JCheckBox("els no retornats");
        btnRetornar = new JButton("Retornar opció");
        panelFiltres.add(chkFiltreNoRetornats);
        panelFiltres.add(btnRetornar);
        panelEsquerra.add(panelFiltres, BorderLayout.SOUTH);

        panelEsquerra.setPreferredSize(new Dimension(380, 0));
        add(panelEsquerra, BorderLayout.WEST);

        JPanel panFormulari = new JPanel(new GridBagLayout());
        panFormulari.setBorder(BorderFactory.createTitledBorder("Nou prestec"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //usuari
        gbc.gridx = 0;
        gbc.gridy = 0;
        panFormulari.add(new JLabel("Usuari:"), gbc);
        gbc.gridx = 1;
        cmbUsuaris = new JComboBox<>();
        panFormulari.add(cmbUsuaris, gbc);

        //exemplar
        gbc.gridx = 0;
        gbc.gridy = 1;
        panFormulari.add(new JLabel("Exemplar:"), gbc);
        gbc.gridx = 1;
        cmbExemplars = new JComboBox<>();
        panFormulari.add(cmbExemplars, gbc);

        //verificar prestec llarg
        gbc.gridx = 1;
        gbc.gridy = 2;
        chkEsLlarg = new JCheckBox("Prestec llarg");
        panFormulari.add(chkEsLlarg, gbc);

        //afegir i cancelar
        JPanel panBotonsDreta = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnAfegir = new JButton("Afegir prestec");
        btnCancelar = new JButton("Cancelar");
        panBotonsDreta.add(btnAfegir);
        panBotonsDreta.add(btnCancelar);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        panFormulari.add(panBotonsDreta, gbc);

        add(panFormulari, BorderLayout.CENTER);

        configuracio();

    }

    private void configuracio(){
        //cancelar
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //els que no han estat retornats
        chkFiltreNoRetornats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refresh();
            }
        });
        //afegir
        btnAfegir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int uPos = cmbUsuaris.getSelectedIndex();
                int ePos = cmbExemplars.getSelectedIndex();
                boolean esLlarg = chkEsLlarg.isSelected();

                if (uPos == -1 || ePos == -1) {
                    JOptionPane.showMessageDialog(FrmGestioPrestecs.this, "Has de seleccionar tot", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    adaptador.afegirPrestec(ePos, uPos, esLlarg);
                    JOptionPane.showMessageDialog(FrmGestioPrestecs.this, "Operacio finalitzada amb exit", "Èxit", JOptionPane.INFORMATION_MESSAGE);
                    chkEsLlarg.setSelected(false);
                    refresh();
                } catch (BiblioException exc) {
                    JOptionPane.showMessageDialog(FrmGestioPrestecs.this, exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //retornar
        btnRetornar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String prestecSeleccionat = lstPrestecs.getSelectedValue();

                if (prestecSeleccionat == null) {
                    JOptionPane.showMessageDialog(FrmGestioPrestecs.this, "Has de seleccionar un prestec", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                List<String> totsElsPrestecs = adaptador.recuperaPrestecs();
                int posReal = totsElsPrestecs.indexOf(prestecSeleccionat);

                try {
                    adaptador.retornarPrestec(posReal);
                    JOptionPane.showMessageDialog(FrmGestioPrestecs.this, "Operació finalitzada amb exit", "Èxit", JOptionPane.INFORMATION_MESSAGE);
                    refresh();
                } catch (BiblioException exc) {
                    JOptionPane.showMessageDialog(FrmGestioPrestecs.this, exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
