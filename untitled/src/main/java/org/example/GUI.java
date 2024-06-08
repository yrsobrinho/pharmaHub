package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                    dispose();
                }
            });

            registerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new RegisterInterface();
                    dispose();
                }
            });

            add(mainPanel, BorderLayout.CENTER);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setExtendedState(JFrame.MAXIMIZED_BOTH);
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
            JButton productRemoveButton = new JButton("Remover Produtos");
            JButton exitButton = new JButton("Sair");

            Dimension buttonSize = new Dimension(200, 50);
            productSearchButton.setPreferredSize(buttonSize);
            productSearchButton.setBackground(Color.WHITE);
            productSearchButton.setForeground(Color.BLACK);

            productInsertButton.setPreferredSize(buttonSize);
            productInsertButton.setBackground(Color.WHITE);
            productInsertButton.setForeground(Color.BLACK);

            productRemoveButton.setPreferredSize(buttonSize);
            productRemoveButton.setBackground(Color.WHITE);
            productRemoveButton.setForeground(Color.BLACK);

            exitButton.setPreferredSize(buttonSize);
            exitButton.setBackground(Color.WHITE);
            exitButton.setForeground(Color.BLACK);

            productSearchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new ProductSearchInterface();
                    dispose();
                }
            });

            productInsertButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new ProductInsertionInterface();
                    dispose();
                }
            });

            productRemoveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new ProductRemoveInterface();
                    dispose();
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
            buttonPanel.add(productRemoveButton, gbc);

            gbc.gridy++;
            buttonPanel.add(exitButton, gbc);

            add(buttonPanel, BorderLayout.CENTER);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setVisible(true);
        }
    }

    public class ProductRemoveInterface extends JFrame {
        // implements ActionListener
        JPanel centralizeItems = new JPanel(new GridBagLayout());
        JPanel removePanel = new JPanel(new BorderLayout(10, 10));
        JLabel removeFieldLabel = new JLabel("Remover por: ");
        JComboBox<String> removeBy = new JComboBox<>();
        JTextField removeField = new JTextField(20);
        JButton removeButton = new JButton("Remover");
        JButton backButton = new JButton("Voltar");


        public ProductRemoveInterface() {
            super("PharmaHub: Remover produtos");
            setWindowIcon(this);

            removeBy.addItem("Nome");
            removeBy.addItem("ID do Fabricante");

            removeButton.setBackground(Color.WHITE);
            removeButton.setForeground(Color.BLACK);

            backButton.setBackground(Color.WHITE);
            backButton.setForeground(Color.BLACK);


            removeField.setPreferredSize(new Dimension(300, 30));

            JPanel comboBoxPanel = new JPanel(new BorderLayout(5, 5));
            comboBoxPanel.add(removeFieldLabel, BorderLayout.WEST);
            comboBoxPanel.add(removeBy, BorderLayout.CENTER);

            removePanel.add(comboBoxPanel, BorderLayout.NORTH);
            removePanel.add(removeField, BorderLayout.CENTER);
            removePanel.add(removeButton, BorderLayout.SOUTH);

            centralizeItems.add(removePanel);

            add(centralizeItems, BorderLayout.CENTER);

            JPanel bottomPanel = new JPanel();
            bottomPanel.add(backButton);
            add(bottomPanel, BorderLayout.SOUTH);

            //removeButton.addActionListener(this);
            backButton.addActionListener(e -> {
                new GeneralInterface();
                dispose();
            });

            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setVisible(true);
        }

        /*@Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == removeButton) {
                String removeCriteria = (String) removeBy.getSelectedItem();
                String removeText = removeField.getText();
                JOptionPane.showMessageDialog(this, "Remover por: " + removeCriteria + "\nTermo de remoção: " + removeText);

                List<Product> products = new ArrayList<>();
                try {
                    if (removeCriteria.equals("Nome"))
                        products = DatabaseManager.searchByProductName(removeText);
                    else if (removeCriteria.equals("ID do Fabricante"))
                        products = DatabaseManager.searchProductsByManufacturerId(Integer.parseInt(removeText));
                    if (products.size() > 0) {
                        // Assuming there is a method to remove product in DatabaseManager
                        if (DatabaseManager.removeProduct(products.get(0).getId())) {
                            JOptionPane.showMessageDialog(this, "Produto removido com sucesso");
                        } else {
                            JOptionPane.showMessageDialog(this, "Erro ao remover o produto");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Nenhum produto encontrado");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }*/
    }

    public class ProductSearchInterface extends JFrame implements ActionListener {
        JPanel centralizeItems = new JPanel(new GridBagLayout());
        JPanel searchPanel = new JPanel(new BorderLayout(10, 10));
        JLabel searchFieldLabel = new JLabel("Consultar por: ");
        JComboBox<String> searchBy = new JComboBox<>();
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Enviar");
        JButton backButton = new JButton("Voltar");

        public ProductSearchInterface() {
            super("PharmaHub: Consultar produtos");
            setWindowIcon(this);

            searchBy.addItem("Nome");
            searchBy.addItem("ID do Fabricante");

            searchButton.setBackground(Color.WHITE);
            searchButton.setForeground(Color.BLACK);

            backButton.setBackground(Color.WHITE);
            backButton.setForeground(Color.BLACK);


            searchField.setPreferredSize(new Dimension(300, 30));

            JPanel comboBoxPanel = new JPanel(new BorderLayout(5, 5));
            comboBoxPanel.add(searchFieldLabel, BorderLayout.WEST);
            comboBoxPanel.add(searchBy, BorderLayout.CENTER);

            searchPanel.add(comboBoxPanel, BorderLayout.NORTH);
            searchPanel.add(searchField, BorderLayout.CENTER);
            searchPanel.add(searchButton, BorderLayout.SOUTH);

            centralizeItems.add(searchPanel);

            add(centralizeItems, BorderLayout.CENTER);

            JPanel bottomPanel = new JPanel();
            bottomPanel.add(backButton);
            add(bottomPanel, BorderLayout.SOUTH);

            searchButton.addActionListener(this);
            backButton.addActionListener(e -> {
                new GeneralInterface();
                dispose();
            });

            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == searchButton) {
                String searchCriteria = (String) searchBy.getSelectedItem();
                String searchText = searchField.getText();
                JOptionPane.showMessageDialog(this, "Consultar por: " + searchCriteria + "\nTermo de consulta: " + searchText);
                // Realize a consulta ao banco de dados aqui com base nos critérios selecionados e texto fornecido
            }
        }
    }

    public class ProductInsertionInterface extends JFrame implements ActionListener {
        JPanel centralizeItems = new JPanel(new GridBagLayout());
        JPanel insertPanel = new JPanel(new BorderLayout(10, 10));
        JLabel productNameLabel = new JLabel("Nome do Produto: ");
        JTextField productNameField = new JTextField(20);
        JLabel productManufacturerIdLabel = new JLabel("ID do Fabricante: ");
        JTextField productManufacturerIdField = new JTextField(20);
        JButton insertButton = new JButton("Inserir");
        JButton backButton = new JButton("Voltar");

        public ProductInsertionInterface() {
            super("PharmaHub: Inserir produtos");
            setWindowIcon(this);

            insertButton.setBackground(Color.WHITE);
            insertButton.setForeground(Color.BLACK);

            backButton.setBackground(Color.WHITE);
            backButton.setForeground(Color.BLACK);


            productNameField.setPreferredSize(new Dimension(300, 30));
            productManufacturerIdField.setPreferredSize(new Dimension(300, 30));

            JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
            inputPanel.add(productNameLabel);
            inputPanel.add(productNameField);
            inputPanel.add(productManufacturerIdLabel);
            inputPanel.add(productManufacturerIdField);

            insertPanel.add(inputPanel, BorderLayout.CENTER);
            insertPanel.add(insertButton, BorderLayout.SOUTH);

            centralizeItems.add(insertPanel);

            add(centralizeItems, BorderLayout.CENTER);

            JPanel bottomPanel = new JPanel();
            bottomPanel.add(backButton);
            add(bottomPanel, BorderLayout.SOUTH);

            insertButton.addActionListener(this);
            backButton.addActionListener(e -> {
                new GeneralInterface();
                dispose();
            });

            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == insertButton) {
                String productName = productNameField.getText();
                String productManufacturerId = productManufacturerIdField.getText();
                JOptionPane.showMessageDialog(this, "Nome do Produto: " + productName + "\nID do Fabricante: " + productManufacturerId);
                // Realize a inserção no banco de dados aqui com os dados fornecidos
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
        JButton backButton = new JButton("Voltar");

        public RegisterInterface() {
            super("PharmaHub: Registrar");
            setWindowIcon(this);

            submitButton.setBackground(Color.WHITE);
            submitButton.setForeground(Color.BLACK);

            backButton.setBackground(Color.WHITE);
            backButton.setForeground(Color.BLACK);


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

            gbc.gridy++;
            registerPanel.add(backButton, gbc);

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            centralizeItems.add(registerPanel, gbc);

            add(centralizeItems);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setVisible(true);

            backButton.addActionListener(e -> {
                new InitialInterface();
                dispose();
            });
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (passwordField.getText().equals(confirmPasswordField.getText())) {
                try {
                    if (DatabaseManager.register(usernameField.getText(), passwordField.getText())) {
                        new LoginInterface();
                        dispose();
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
        JButton backButton = new JButton("Voltar");

        public LoginInterface() {
            super("PharmaHub: Login");
            setWindowIcon(this);

            submitButton.setBackground(Color.WHITE);
            submitButton.setForeground(Color.BLACK);

            backButton.setBackground(Color.WHITE);
            backButton.setForeground(Color.BLACK);


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

            gbc.gridy++;
            loginPanel.add(backButton, gbc);

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            centralizeItems.add(loginPanel, gbc);

            add(centralizeItems);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setVisible(true);

            backButton.addActionListener(e -> {
                new InitialInterface();
                dispose();
            });
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
                dispose();
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Credenciais incorretas.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        try {
            DatabaseManager.createUserTable();
            DatabaseManager.createManufacturerTable();
            DatabaseManager.createProductTable();
        } catch (SQLException ex) {
            System.out.println("As tabelas já foram criadas!");
            System.out.println(ex.getMessage());
        }
        SwingUtilities.invokeLater(() -> new GUI().new InitialInterface());
    }
}
