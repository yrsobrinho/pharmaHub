package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.sql.SQLException;

public class GUI {

    private void setWindowIcon(JFrame frame) {
        try {
            BufferedImage iconImage = ImageIO.read(getClass().getResource("/org/example/pharmaHub.png"));
            frame.setIconImage(iconImage);
        } catch (IOException e) {
            System.err.println("Icon image not found.");
        }
    }

    public class InitialInterface extends JFrame {
        public InitialInterface() {
            super("PharmaHub: Início");
            setWindowIcon(this);  // Define o ícone da janela

            JPanel mainPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);

            // Tenta carregar e redimensionar o ícone da imagem
            ImageIcon logoIcon = null;
            try {
                BufferedImage logoImage = ImageIO.read(getClass().getResource("/org/example/pharmaHub.png"));
                Image scaledImage = logoImage.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
                logoIcon = new ImageIcon(scaledImage);
            } catch (IOException e) {
                System.err.println("Logo image not found.");
            }

            JLabel logoLabel = new JLabel(logoIcon);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            mainPanel.add(logoLabel, gbc);

            JButton loginButton = new JButton("Login");
            loginButton.setPreferredSize(new Dimension(200, 50));
            loginButton.setBackground(Color.WHITE);
            loginButton.setForeground(Color.BLACK);
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.anchor = GridBagConstraints.CENTER;
            mainPanel.add(loginButton, gbc);

            JButton registerButton = new JButton("Registrar");
            registerButton.setPreferredSize(new Dimension(200, 50));
            registerButton.setBackground(Color.WHITE);
            registerButton.setForeground(Color.BLACK);
            gbc.gridx = 1;
            mainPanel.add(registerButton, gbc);

            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new LoginInterface();
                }
            });

            registerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new RegisterInterface();
                }
            });

            add(mainPanel, BorderLayout.CENTER);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setExtendedState(JFrame.MAXIMIZED_BOTH);;
            setLocationRelativeTo(null);
            setVisible(true);
        }
    }

    public class GeneralInterface extends JFrame {
        public GeneralInterface() {
            super("PharmaHub: Principal");
            setWindowIcon(this);  // Define o ícone da janela

            JPanel buttonPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);

            JButton productSearchButton = new JButton("Consultar Produtos");
            JButton productInsertButton = new JButton("Inserir Produtos");
            JButton exitButton = new JButton("Sair");

            Dimension buttonSize = new Dimension(200, 50);
            productSearchButton.setPreferredSize(buttonSize);
            productSearchButton.setBackground(Color.WHITE);
            productSearchButton.setForeground(Color.BLACK);

            productInsertButton.setPreferredSize(buttonSize);
            productInsertButton.setBackground(Color.WHITE);
            productInsertButton.setForeground(Color.BLACK);

            exitButton.setPreferredSize(buttonSize);
            exitButton.setBackground(Color.WHITE);
            exitButton.setForeground(Color.BLACK);

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
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setExtendedState(JFrame.MAXIMIZED_BOTH);;
            setVisible(true);
        }
    }

    public class ProductSearchInterface extends JFrame implements ActionListener {
        JPanel centralizeItems = new JPanel(new GridBagLayout());
        JPanel searchPanel = new JPanel(new BorderLayout(10, 10));
        JLabel searchFieldLabel = new JLabel("Consultar por: ");
        JComboBox<String> searchBy = new JComboBox<>();
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Enviar");

        public ProductSearchInterface() {
            super("PharmaHub: Consultar produtos");
            setWindowIcon(this);

            searchBy.addItem("Nome");
            searchBy.addItem("ID do Fabricante");

            searchButton.setBackground(Color.WHITE);
            searchButton.setForeground(Color.BLACK);

            searchField.setPreferredSize(new Dimension(300, 30));

            JPanel comboBoxPanel = new JPanel(new BorderLayout(5, 5));
            comboBoxPanel.add(searchFieldLabel, BorderLayout.WEST);
            comboBoxPanel.add(searchBy, BorderLayout.CENTER);

            searchPanel.add(comboBoxPanel, BorderLayout.NORTH);
            searchPanel.add(searchField, BorderLayout.CENTER);
            searchPanel.add(searchButton, BorderLayout.SOUTH);

            centralizeItems.add(searchPanel);

            add(centralizeItems);

            searchButton.addActionListener(this);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setExtendedState(JFrame.MAXIMIZED_BOTH);;
            setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == searchButton) {
                String searchCriteria = (String) searchBy.getSelectedItem();
                String searchText = searchField.getText();
                JOptionPane.showMessageDialog(this, "Buscar por: " + searchCriteria + "\nTermo de busca: " + searchText);
                // Adicione aqui a lógica de busca conforme o critério selecionado e o texto de busca
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

        JTextField productName = new JTextField(20);
        JTextField productIDManufacturer = new JTextField(20);
        JTextField productPrice = new JTextField(20);
        JButton insertButton = new JButton("Enviar");

        public ProductInsertionInterface() {
            super("PharmaHub: Inserir produtos");
            setWindowIcon(this);

            insertButton.setBackground(Color.WHITE);
            insertButton.setForeground(Color.BLACK);

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
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setExtendedState(JFrame.MAXIMIZED_BOTH);;
            setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == insertButton) {
                String name = productName.getText();
                String price = productPrice.getText();
                String manufacturer = productIDManufacturer.getText();
                JOptionPane.showMessageDialog(this, "Produto inserido:\nNome: " + name + "\nPreço: " + price + "\nFabricante: " + manufacturer);
                // Adicione aqui a lógica para inserir o produto no banco de dados
            }
        }
    }

    public class RegisterInterface extends JFrame implements ActionListener {
        JPanel centralizeItems = new JPanel(new GridBagLayout());
        JPanel registerPanel = new JPanel(new GridBagLayout());

        JLabel registrationField = new JLabel("Informe seus dados para se registrar: ");
        JLabel usernameLabel = new JLabel("Nome de Usuário: ");
        JLabel passwordLabel = new JLabel("Senha: ");
        JLabel confirmPasswordLabel = new JLabel("Confirme a Senha: ");

        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JPasswordField confirmPasswordField = new JPasswordField(20);
        JButton submitButton = new JButton("Enviar");

        public RegisterInterface() {
            super("PharmaHub: Registrar");
            setWindowIcon(this);

            submitButton.setBackground(Color.WHITE);
            submitButton.setForeground(Color.BLACK);

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
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setExtendedState(JFrame.MAXIMIZED_BOTH);;
            setVisible(true);
        }

        public void actionPerformed(ActionEvent e) {
            if (passwordField.getText().equals(confirmPasswordField.getText())) {
                try {
                    if (DatabaseManager.register(usernameField.getText(), passwordField.getText())) {
                        new LoginInterface();
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Não foi possível registrar esse usuário.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
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
            setWindowIcon(this);

            submitButton.setBackground(Color.WHITE);
            submitButton.setForeground(Color.BLACK);

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
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setExtendedState(JFrame.MAXIMIZED_BOTH);;
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
        new GUI(). new ProductSearchInterface();
        try {
            DatabaseManager.createUserTable();
            DatabaseManager.createManufacturerTable();
            DatabaseManager.createProductTable();
        } catch(SQLException ex) {
            System.out.println("As tabelas já foram criadas!");
            System.out.println(ex.getMessage());
        }
        SwingUtilities.invokeLater(() -> new GUI().new InitialInterface());
    }
}