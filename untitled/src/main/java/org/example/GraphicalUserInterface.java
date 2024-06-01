package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static javax.swing.text.StyleConstants.setBackground;

public class GraphicalUserInterface {
    public class GeneralInterface extends JFrame {
        public GeneralInterface() {
            super("PharmaHub: Principal");

            JPanel buttonPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);

            JButton productSearchButton = new JButton("Consultar Produtos");
            JButton productInsertButton = new JButton("Inserir Produtos");
            JButton exitButton = new JButton("Sair");

            Dimension buttonSize = new Dimension(200, 50);
            productSearchButton.setPreferredSize(buttonSize);
            productInsertButton.setPreferredSize(buttonSize);
            exitButton.setPreferredSize(buttonSize);

            productSearchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new ProductSearchInterface();
                }
            });

            productInsertButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new ProductInsertionInterface();
                }
            });

            exitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            // Layout buttons on the panel
            gbc.gridx = 0;
            gbc.gridy = 0;
            buttonPanel.add(productSearchButton, gbc);

            gbc.gridy++;
            buttonPanel.add(productInsertButton, gbc);

            gbc.gridy++;
            buttonPanel.add(exitButton, gbc);

            add(buttonPanel, BorderLayout.CENTER);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            pack();
            setVisible(true);
        }
    }


    public class ProductSearchInterface extends JFrame implements ActionListener {
        JPanel centralizeItems = new JPanel(new GridBagLayout());
        JPanel searchPanel = new JPanel(new GridBagLayout());
        JLabel searchField = new JLabel("Consultar por: ");
        JComboBox<String> searchBy = new JComboBox<>();
        JButton searchButton = new JButton("Enviar");

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

            gbc.gridy++;
            searchPanel.add(searchButton, gbc);

            centralizeItems.add(searchPanel, gbc);

            add(centralizeItems);

            searchButton.addActionListener(this);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            pack();
            setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == searchButton) {
                String searchCriteria = (String) searchBy.getSelectedItem();
                JOptionPane.showMessageDialog(this, "Buscar por: " + searchCriteria);
                // Adicione aqui a lógica de busca conforme o critério selecionado
            }
        }
    }

    public class ProductInsertionInterface extends JFrame implements ActionListener {
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
        JButton insertButton = new JButton("Enviar");

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

            gbc.gridy++;
            gbc.gridx = 0;
            gbc.gridwidth = 2;
            registerPanel.add(insertButton, gbc);

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            centralizeItems.add(registerPanel, gbc);

            add(centralizeItems);

            insertButton.addActionListener(this);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            pack();
            setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == insertButton) {
                String name = productName.getText();
                String price = productPrice.getText();
                String manufacturerID = productIDManufacturer.getText();
                String category = productCategory.getText();
                JOptionPane.showMessageDialog(this, "Produto inserido:\nNome: " + name + "\nPreço: " + price + "\nID do Fabricante: " + manufacturerID + "\nCategoria: " + category);
                // Adicione aqui a lógica de inserção de produto
            }
        }
    }

    public class RegisterInterface extends JFrame implements ActionListener {
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
            submitButton.addActionListener(this);

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

        public void actionPerformed(ActionEvent e) {
            if (passwordField.getText().equals(confirmPasswordField.getText())) {
                try {
                    if (DatabaseManager.register(usernameField.getText(), passwordField.getText())) {
                        // mostrar tela depois do registro ou modal escrito "registro com sucesso"
                        System.out.println("yasmi");
                    }
                    else {
                        JOptionPane.showMessageDialog(new JFrame(), "Não foi possível registrar esse usuário.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else {
                JOptionPane.showMessageDialog(new JFrame(), "As senhas não coincidem.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public class LoginInterface extends JFrame implements ActionListener {
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
            submitButton.addActionListener(this);

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

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean authenticated = false;
            try {
                authenticated = DatabaseManager.login(usernameField.getText(), passwordField.getText());
            } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            if (authenticated) {
                new GeneralInterface();
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Credenciais incorretas.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DatabaseManager.createUserTable();
        DatabaseManager.createManufacturerTable();
        DatabaseManager.createProductTable();
        //SwingUtilities.invokeLater(() -> new GraphicalUserInterface().new ProductSearchInterface());
        //SwingUtilities.invokeLater(() -> new GraphicalUserInterface().new ProductInsertionInterface());
        SwingUtilities.invokeLater(() -> new GraphicalUserInterface().new RegisterInterface());
        //SwingUtilities.invokeLater(() -> new GraphicalUserInterface().new LoginInterface());
        //SwingUtilities.invokeLater(() -> new GraphicalUserInterface().new GeneralInterface());
    }
}




