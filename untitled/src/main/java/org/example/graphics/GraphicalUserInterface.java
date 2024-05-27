package org.example.graphics;

import javax.swing.*;
import java.awt.*;

public class GraphicalUserInterface {

    public class ProductSearchInterface extends JFrame {
        JPanel centralizeItems = new JPanel(new GridBagLayout());
        JPanel searchPanel = new JPanel(new GridBagLayout());
        JLabel searchField = new JLabel("Consultar por: ");
        JComboBox<String> searchBy = new JComboBox<>();

        public ProductSearchInterface() {
            super("PharmaHub: Consultar produtos");
            searchBy.addItem("Nome");
            searchBy.addItem("ID do Produto");
            searchBy.addItem("ID do Fabricante");

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.CENTER;
            searchPanel.add(searchField, gbc);

            gbc.gridy++;
            searchPanel.add(searchBy, gbc);

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

        JLabel registrationField = new JLabel("Identifique o produto a ser adicionado: ");
        JLabel nameLabel = new JLabel("Nome: ");
        JLabel priceLabel = new JLabel("Preço: ");
        JLabel manufacturerIDLabel = new JLabel("ID do Fabricante: ");
        JLabel categoryLabel = new JLabel("Categoria: ");

        JTextField productName = new JTextField(20);
        JTextField productIDManufacturer = new JTextField(20);
        JTextField productPrice = new JTextField(20);
        JTextField productCategory = new JTextField(20);

        public ProductInsertionInterface() {
            super("PharmaHub: Inserir produtos");

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            registerPanel.add(registrationField, gbc);

            gbc.gridy++;
            registerPanel.add(nameLabel, gbc);
            gbc.gridx = 1;
            registerPanel.add(productName, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            registerPanel.add(categoryLabel, gbc);
            gbc.gridx = 1;
            registerPanel.add(productCategory, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            registerPanel.add(priceLabel, gbc);
            gbc.gridx = 1;
            registerPanel.add(productPrice, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            registerPanel.add(manufacturerIDLabel, gbc);
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

        JLabel registrationField = new JLabel("Preencha os campos para se registrar: ");
        JLabel usernameLabel = new JLabel("Nome de Usuário: ");
        JLabel passwordLabel = new JLabel("Senha: ");
        JLabel confirmPasswordLabel = new JLabel("Confirme a Senha: ");

        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JPasswordField confirmPasswordField = new JPasswordField(20);
        JButton submitButton = new JButton("Enviar");

        public RegisterInterface() {
            super("PharmaHub: Registrar");

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            registerPanel.add(registrationField, gbc);

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
            gbc.gridy++;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            registerPanel.add(submitButton, gbc);

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

    public class LoginInterface extends JFrame {
        JPanel centralizeItems = new JPanel(new GridBagLayout());
        JPanel loginPanel = new JPanel(new GridBagLayout());

        JLabel loginField = new JLabel("Entre com suas credenciais: ");
        JLabel usernameLabel = new JLabel("Nome de Usuário: ");
        JLabel passwordLabel = new JLabel("Senha: ");

        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JButton submitButton = new JButton("Enviar");

        public LoginInterface() {
            super("PharmaHub: Login");

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            loginPanel.add(loginField, gbc);

            gbc.gridy++;
            loginPanel.add(usernameLabel, gbc);
            gbc.gridx = 1;
            loginPanel.add(usernameField, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            loginPanel.add(passwordLabel, gbc);
            gbc.gridx = 1;
            loginPanel.add(passwordField, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            loginPanel.add(submitButton, gbc);

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            centralizeItems.add(loginPanel, gbc);

            add(centralizeItems);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            pack();
            setVisible(true);
        }
    }



    public static void main(String[] args) {
        //SwingUtilities.invokeLater(() -> new GraphicalUserInterface().new ProductSearchInterface());
        SwingUtilities.invokeLater(() -> new GraphicalUserInterface().new ProductInsertionInterface());
        //SwingUtilities.invokeLater(() -> new GraphicalUserInterface().new RegisterInterface());
        //SwingUtilities.invokeLater(() -> new GraphicalUserInterface().new LoginInterface());
    }
}




