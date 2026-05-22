package prog2.vista;

import prog2.adaptador.Adaptador;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
public class FrmGestioUsuaris extends JDialog {

    private Adaptador adaptador;

    private JTextField txtEmail;
    private JTextField txtNom;
    private JTextField txtAdreca;
    private JCheckBox chkEsEstudiant;

    private JButton btnAcceptar;
    private JButton btnCancelar;

    private JList<String> lstUsuaris;
    private DefaultListModel<String> modelLlista;

    public FrmGestioUsuaris(JFrame original, Adaptador adaptador){
        super(original, "Gestio d'Usuaris", true);
        this.adaptador = adaptador;

        setSize(600,400);
        setLocationRelativeTo(original);
        setLayout(new BorderLayout(20,20));

        inicialitzar();

        refresh();
    }

    private void refresh(){
        modelLlista.clear();

        List<String> llistaUsuaris = adaptador.recuperaUsuaris();

        for(int i = 0; i < llistaUsuaris.size(); i++){
            String usuari = llistaUsuaris.get(i);
            modelLlista.addElement(usuari);
        }
    }

    private void inicialitzar() {
        //cream el panel
        JPanel panelLlista = new JPanel(new BorderLayout());
        panelLlista.setBorder(BorderFactory.createTitledBorder("Usuaris"));

        modelLlista = new DefaultListModel<>();
        lstUsuaris = new JList<>(modelLlista);

        JScrollPane scrollLlista = new JScrollPane(lstUsuaris);
        panelLlista.add(scrollLlista, BorderLayout.CENTER);

        panelLlista.setPreferredSize(new Dimension(300, 0));
        add(panelLlista, BorderLayout.WEST);

        JPanel panelFormulari = new JPanel(new GridBagLayout());
        panelFormulari.setBorder(BorderFactory.createTitledBorder("Dades del nou usuari"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //email
        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulari.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; txtEmail = new JTextField(15);
        panelFormulari.add(txtEmail, gbc);


        //nom
        gbc.gridx = 0; gbc.gridy = 1;
        panelFormulari.add(new JLabel("Nom:"), gbc);
        gbc.gridx = 1; txtNom = new JTextField(15);
        panelFormulari.add(txtNom, gbc);

        //adreça
        gbc.gridx = 0; gbc.gridy = 2;
        panelFormulari.add(new JLabel("Adreça:"), gbc);
        gbc.gridx = 1; txtAdreca = new JTextField(15);
        panelFormulari.add(txtAdreca, gbc);

        //verificar
        gbc.gridx = 1; gbc.gridy = 3;
        chkEsEstudiant = new JCheckBox("es estudiant");
        panelFormulari.add(chkEsEstudiant, gbc);

        //boto acceptar i cancelar
        JPanel panBotons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        btnAcceptar = new JButton("Acceptar");
        btnCancelar = new JButton("Cancelar");
        panBotons.add(btnAcceptar);
        panBotons.add(btnCancelar);

        //modificar la posicio del panel dels botons
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        panelFormulari.add(panBotons, gbc);

        add(panelFormulari, BorderLayout.CENTER);

        configuracio();
    }

    private void configuracio(){
        //boto acceptar
        btnAcceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText().trim();
                String nom = txtNom.getText().trim();
                String adreca = txtAdreca.getText().trim();
                boolean esEstudiant = chkEsEstudiant.isSelected();

                if (email.isEmpty() || nom.isEmpty() || adreca.isEmpty()) {
                    JOptionPane.showMessageDialog(FrmGestioUsuaris.this, "Has de escriure totes les informacions", "Buit", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                try{
                    adaptador.afegirUsuari(email, nom, adreca, esEstudiant);
                    JOptionPane.showMessageDialog(FrmGestioUsuaris.this, "Operacio finalitzada amb exit", "Èxit", JOptionPane.INFORMATION_MESSAGE);

                    txtEmail.setText("");
                    txtNom.setText("");
                    txtAdreca.setText("");
                    chkEsEstudiant.setSelected(false);

                    refresh();
                }
                catch(BiblioException exc){
                    JOptionPane.showMessageDialog(FrmGestioUsuaris.this, exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

                }


            }
        });
    }

}
