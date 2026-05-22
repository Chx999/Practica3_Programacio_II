package prog2.vista;

import prog2.adaptador.Adaptador;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FrmGestioExemplars extends JDialog {
    private Adaptador adaptador;

    private JTextField txtId;
    private JTextField txtTitol;
    private JTextField txtAutor;
    private JCheckBox chkAdmetPrestecLlarg;

    private JButton btnAcceptar;
    private JButton btnCancelar;

    private JList<String> lstExemplars;
    private DefaultListModel<String> modelLlista;

    public FrmGestioExemplars(JFrame original, Adaptador adaptador) {
        super(original, "Gestió d'Exemplars", true);
        this.adaptador = adaptador;

        setSize(600, 400);
        setLocationRelativeTo(original);
        setLayout(new BorderLayout(15, 15));

        inicialitzar();
        refresh();
    }

    private void refresh() {
        modelLlista.clear();

        List<String> llistaExemplars = adaptador.recuperaExemplars();

        for (int i = 0; i < llistaExemplars.size(); i++) {
            String exemplars = llistaExemplars.get(i);
            modelLlista.addElement(exemplars);
        }
    }

    private void inicialitzar() {
        JPanel panelLlista = new JPanel(new BorderLayout());
        panelLlista.setBorder(BorderFactory.createTitledBorder("Exemplars"));

        modelLlista = new DefaultListModel<>();
        lstExemplars = new JList<>(modelLlista);
        JScrollPane scrollLlista = new JScrollPane(lstExemplars);
        panelLlista.add(scrollLlista, BorderLayout.CENTER);

        panelLlista.setPreferredSize(new Dimension(300, 0));
        add(panelLlista, BorderLayout.WEST);

        JPanel panFormulari = new JPanel(new GridBagLayout());
        panFormulari.setBorder(BorderFactory.createTitledBorder("Dades per omplir"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //id
        gbc.gridx = 0; gbc.gridy = 0;
        panFormulari.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1; txtId = new JTextField(15);
        panFormulari.add(txtId, gbc);

        //titol
        gbc.gridx = 0; gbc.gridy = 1;
        panFormulari.add(new JLabel("Titol:"), gbc);
        gbc.gridx = 1; txtTitol = new JTextField(15);
        panFormulari.add(txtTitol, gbc);

        //autor
        gbc.gridx = 0; gbc.gridy = 2;
        panFormulari.add(new JLabel("Autor:"), gbc);
        gbc.gridx = 1; txtAutor = new JTextField(15);
        panFormulari.add(txtAutor, gbc);

        //verificar si por prestec llarg
        gbc.gridx = 1; gbc.gridy = 3;
        chkAdmetPrestecLlarg = new JCheckBox("Es posible préstec llarg");
        panFormulari.add(chkAdmetPrestecLlarg, gbc);

        //boto acceptar i cancelar
        JPanel panBotons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        btnAcceptar = new JButton("Acceptar");
        btnCancelar = new JButton("Cancelar");
        panBotons.add(btnAcceptar);
        panBotons.add(btnCancelar);

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        panFormulari.add(panBotons, gbc);

        add(panFormulari, BorderLayout.CENTER);

        configuracio();


    }

    private void configuracio(){
        //boto acceptar
        btnAcceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txtId.getText().trim();
                String titol = txtTitol.getText().trim();
                String autor = txtAutor.getText().trim();
                boolean admetLlarg = chkAdmetPrestecLlarg.isSelected();

                if (id.isEmpty() || titol.isEmpty() || autor.isEmpty()) {
                    JOptionPane.showMessageDialog(FrmGestioExemplars.this, "Has d'omplir tot", "Buit", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                try {
                    adaptador.afegirExemplar(id, titol, autor, admetLlarg);

                    JOptionPane.showMessageDialog(FrmGestioExemplars.this, "Operacio Finalitzada amb exit", "Èxit", JOptionPane.INFORMATION_MESSAGE);

                    txtId.setText("");
                    txtTitol.setText("");
                    txtAutor.setText("");
                    chkAdmetPrestecLlarg.setSelected(false);

                    refresh();
                } catch (BiblioException exc) {
                    JOptionPane.showMessageDialog(FrmGestioExemplars.this, exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            });
        //boto cancelar
        btnCancelar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
        });
    }
}


