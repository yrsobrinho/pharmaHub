package org.example.graphics;

import javax.swing.*;
import java.awt.*;

public class GraphicalUserInterface {

    public class ProductSearchInterface extends JFrame {
        JPanel centralizeItems = new JPanel();
        JPanel searchPanel = new JPanel();
        JLabel searchField = new JLabel("Consultar por: ");
        JComboBox<String> searchBy = new JComboBox<>();

        public ProductSearchInterface() {
            super("PharmaHub: Consultar produtos");
            searchBy.addItem("Nome");
            searchBy.addItem("ID do Produto");
            searchBy.addItem("ID do Fabricante");

            searchPanel.setLayout(new GridLayout(2, 1));
            searchPanel.add(searchField, BorderLayout.SOUTH);
            searchPanel.add(searchBy, BorderLayout.NORTH);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.CENTER;

            centralizeItems.add(searchPanel, gbc);

            add(centralizeItems);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            pack();
            setVisible(true);
        }
    }

    public class ProductInsertionInterface extends JFrame {
        JPanel centralizeItems = new JPanel(new GridBagLayout());
        JPanel registerPanel = new JPanel(new GridBagLayout());

        JLabel registrationFirld = new JLabel("Identifique o produto a ser adicionado: ");
        JLabel nameLabel = new JLabel("Nome do Produto: ");
        JLabel idLabel = new JLabel("ID do Produto: ");
        JLabel manufacturerIdLabel = new JLabel("ID do Fabricante: ");

        JTextField productName = new JTextField(20);
        JTextField productDescription = new JTextField(20);
        JTextField productIDManufacturer = new JTextField(20);

        public ProductInsertionInterface() {
            super("PharmaHub: Inserir produtos");

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            registerPanel.add(registrationFirld, gbc);

            gbc.gridy++;
            registerPanel.add(nameLabel, gbc);
            gbc.gridx = 1;
            registerPanel.add(productName, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            registerPanel.add(idLabel, gbc);
            gbc.gridx = 1;
            registerPanel.add(productDescription, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            registerPanel.add(manufacturerIdLabel, gbc);
            gbc.gridx = 1;
            registerPanel.add(productIDManufacturer, gbc);

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            centralizeItems.add(registerPanel, gbc);

            add(centralizeItems);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            pack();
            setVisible(true);
        }
    }

    public class RegisterInterface extends JFrame {
        JPanel centralizeItems = new JPanel(new GridBagLayout());
        JPanel registerPanel = new JPanel(new GridBagLayout());

        JLabel registrationFirld = new JLabel("Preencha os campos para se registrar: ");
        JLabel usernameLabel = new JLabel("Nome de Usuário: ");
        JLabel passwordLabel = new JLabel("Senha: ");
        JLabel confirmPasswordLabel = new JLabel("Confirme a Senha: ");

        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JPasswordField confirmPasswordField = new JPasswordField(20);

        public RegisterInterface() {
            super("PharmaHub: Registrar");

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            registerPanel.add(registrationFirld, gbc);

            gbc.gridy++;
            registerPanel.add(usernameLabel, gbc);
            gbc.gridx = 1;
            registerPanel.add(usernameField, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            registerPanel.add(passwordLabel, gbc);
            gbc.gridx = 1;
            registerPanel.add(passwordField, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            registerPanel.add(confirmPasswordLabel, gbc);
            gbc.gridx = 1;
            registerPanel.add(confirmPasswordField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            centralizeItems.add(registerPanel, gbc);

            add(centralizeItems);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            pack();
            setVisible(true);
        }
    }

    public static void main(String[] args) {
        new GraphicalUserInterface().new RegisterInterface();
    }
}


