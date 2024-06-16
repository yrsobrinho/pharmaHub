import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Classe responsável por mostrar e gerenciar as interfaces gráficas
public class GUI {
    // Troca o ícone padrão da janela por um ícone criado pelos integrantes do grupo
//    private void setWindowIcon(JFrame frame) {
//        try {
//            BufferedImage iconImage = ImageIO.read(GUI.class.getResource("\\pharmaHub.png"));
//            frame.setIconImage(iconImage);
//        } catch (IOException e) {
//            System.err.println("Icon image not found.");
//        }
//    }

    // Interface inicial do programa, com botões de login e registro
    public class InitialInterface extends JFrame {
        public InitialInterface() {
            super("PharmaHub: Início");
       //     setWindowIcon(this);  // Define o ícone da janela

            JPanel mainPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);

//            ImageIcon logoIcon = null;
//            try {
//                BufferedImage logoImage = ImageIO.read(Objects.requireNonNull(GUI.class.getResource("\\pharmaHub.png")));
//                Image scaledImage = logoImage.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
//                logoIcon = new ImageIcon(scaledImage);
//            } catch (IOException e) {
//                System.err.println("Logo image not found.");
//            }
//
//            JLabel logoLabel = new JLabel(logoIcon);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.insets = new Insets(0, 0, 20, 0); // Espaçamento abaixo do logo
            //mainPanel.add(logoLabel, gbc);

            JButton loginButton = new JButton("Login");
            loginButton.setPreferredSize(new Dimension(400, 50));
            loginButton.setBackground(Color.WHITE);
            loginButton.setForeground(Color.BLACK);
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.insets = new Insets(0, 0, 10, 0); // Espaçamento entre botões
            mainPanel.add(loginButton, gbc);

            JButton registerButton = new JButton("Registrar");
            registerButton.setPreferredSize(new Dimension(400, 50));
            registerButton.setBackground(Color.WHITE);
            registerButton.setForeground(Color.BLACK);
            gbc.gridy = 2;
            mainPanel.add(registerButton, gbc);

            JButton exitButton = new JButton("Fechar");
            exitButton.setPreferredSize(new Dimension(400, 50));
            exitButton.setBackground(Color.WHITE);
            exitButton.setForeground(Color.BLACK);
            gbc.gridy = 3;
            mainPanel.add(exitButton, gbc);

            // Abre interface de login de usuário
            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new LoginInterface();
                    dispose();
                }
            });

            // Abre interface de registro de usuário
            registerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new RegisterInterface();
                    dispose();
                }
            });

            // Fecha a aplicação com confirmação
            exitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int response = JOptionPane.showConfirmDialog(
                            InitialInterface.this,
                            "Tem certeza de que deseja encerrar a aplicação?",
                            "PharmaHub: Confirmação",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                    );
                    if (response == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                }
            });

            add(mainPanel, BorderLayout.CENTER);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setLocationRelativeTo(null);
            setVisible(true);
        }
    }

    // Interface geral, com opções de consulta, inserção e remoção de produtos
    public class GeneralInterface extends JFrame {
        public GeneralInterface() {
            super("PharmaHub: Principal");
            //setWindowIcon(this);  // Define o ícone da janela

            JPanel buttonPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);

            JButton productSearchButton = new JButton("Consultar Produtos");
            JButton productInsertButton = new JButton("Inserir Produtos");
            JButton productRemoveButton = new JButton("Remover Produtos");
            JButton addManufacturerButton = new JButton("Adicionar Fabricante");
            JButton exitButton = new JButton("Voltar à tela inicial");

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

            addManufacturerButton.setPreferredSize(buttonSize);
            addManufacturerButton.setBackground(Color.WHITE);
            addManufacturerButton.setForeground(Color.BLACK);

            exitButton.setPreferredSize(buttonSize);
            exitButton.setBackground(Color.WHITE);
            exitButton.setForeground(Color.BLACK);

            // Abre a interface de consulta de produtos
            productSearchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new ProductSearchInterface();
                    dispose();
                }
            });

            // Abre a interface de inserção de produtos
            productInsertButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new ProductInsertionInterface();
                    dispose();
                }
            });

            // Abre a interface de remoção de produtos
            productRemoveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new ProductRemoveInterface();
                    dispose();
                }
            });

            // Abre a interface de adição de fabricantes
            addManufacturerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new ManufacturerInsertionInterface();
                    dispose();
                }
            });

            // Desconecta o usuário e volta à interface inicial de escolha entre registro e login
            exitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new InitialInterface();
                    dispose();
                }
            });

            gbc.gridx = 0;
            gbc.gridy = 0;
            buttonPanel.add(productSearchButton, gbc);

            gbc.gridy++;
            buttonPanel.add(productInsertButton, gbc);

            gbc.gridy++;
            buttonPanel.add(productRemoveButton, gbc);

            gbc.gridy++;
            buttonPanel.add(addManufacturerButton, gbc);

            gbc.gridy++;
            buttonPanel.add(exitButton, gbc);

            add(buttonPanel, BorderLayout.CENTER);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setVisible(true);
        }
    }

    // Interface para a inserção de um novo fabricante
    public class ManufacturerInsertionInterface extends JFrame implements ActionListener {
        private JTextField nameField;
        private JButton insertButton;
        private JButton backButton;

        public ManufacturerInsertionInterface() {
            super("PharmaHub: Inserir fabricante");
            //setWindowIcon(this);

            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setVisible(true);

            nameField = new JTextField(20);
            insertButton = new JButton("Inserir");
            backButton = new JButton("Voltar");

            insertButton.addActionListener(this);
            insertButton.setForeground(Color.BLACK);
            insertButton.setBackground(Color.WHITE);
            backButton.setForeground(Color.BLACK);
            backButton.setBackground(Color.WHITE);

            JPanel insertPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.anchor = GridBagConstraints.CENTER;
            insertPanel.add(new JLabel("Nome do Fabricante:"), gbc);
            gbc.gridy = 1;
            insertPanel.add(nameField, gbc);
            gbc.gridy = 2;
            insertPanel.add(insertButton, gbc);

            JPanel bottomPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbcBottom = new GridBagConstraints();
            gbcBottom.gridx = 0;
            gbcBottom.gridy = 0;
            gbcBottom.insets = new Insets(10, 10, 10, 10); // Add some padding
            gbcBottom.anchor = GridBagConstraints.CENTER;
            bottomPanel.add(backButton, gbcBottom);

            getContentPane().setBackground(Color.WHITE);

            getContentPane().setLayout(new BorderLayout());

            getContentPane().add(insertPanel, BorderLayout.CENTER);
            getContentPane().add(bottomPanel, BorderLayout.SOUTH);

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
            try {
                if (nameField.getText().isBlank()) {
                    JOptionPane.showMessageDialog(this, "Nome vazio!");
                    return;
                }
                if (DatabaseManager.insertManufacturer(new Manufacturer(null, nameField.getText().trim())))
                    JOptionPane.showMessageDialog(this, "Fabricante inserido!");
                else
                    JOptionPane.showMessageDialog(this, "Erro ao inserir fabricante");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    // Interface de remoção de produtos
    public class ProductRemoveInterface extends JFrame implements ActionListener {
        JPanel centralizeItems = new JPanel(new GridBagLayout());
        JPanel removePanel = new JPanel(new BorderLayout(10, 10));
        JLabel removeFieldLabel = new JLabel("Remover por: ");
        JComboBox<String> removeBy = new JComboBox<>();
        JTextField removeField = new JTextField(20);
        JButton removeButton = new JButton("Remover");

        JButton backButton = new JButton("Voltar");


        public ProductRemoveInterface() {
            super("PharmaHub: Remover produtos");
            //setWindowIcon(this);

            removeBy.addItem("Nome");
            removeBy.addItem("ID");

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

            removeButton.addActionListener(this);
            backButton.addActionListener(e -> {
                new GeneralInterface();
                dispose();
            });

            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setVisible(true);
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == removeButton) {
                String removeCriteria = (String) removeBy.getSelectedItem();
                String removeText = removeField.getText();
                JOptionPane.showMessageDialog(this, "Remover por: " + removeCriteria + "\nTermo de remoção: " + removeText);

                List<Product> products = new ArrayList<>();
                Product product = null;
                try {
                    if (removeCriteria.equals("Nome")) {
                        products = DatabaseManager.searchByProductName(removeText);
                        if (products.size() > 0) {
                            DatabaseManager.deleteProductByName(products.get(0).getName());
                            JOptionPane.showMessageDialog(this, "Produto removido com sucesso");
                        }
                        else
                            JOptionPane.showMessageDialog(this, "Produto não encontrado!");
                    } else if (removeCriteria.equals("ID")) {
                        product = DatabaseManager.searchByProductId(Integer.parseInt(removeText));
                        if (product != null) {
                            DatabaseManager.deleteProductById(product.getId());
                            JOptionPane.showMessageDialog(this, "Produto removido com sucesso");
                        }
                        else
                            JOptionPane.showMessageDialog(this, "Produto não encontrado!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "ID do Fabricante deve ser um número válido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao acessar o banco de dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    public class ProductSearchInterface extends JFrame implements ActionListener {
        JPanel searchPanel = new JPanel(new GridBagLayout());
        JLabel searchFieldLabel = new JLabel("Consultar por: ");
        JComboBox<String> searchBy = new JComboBox<>();
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Enviar");
        JButton showAllButton = new JButton("Mostrar Todos");
        JButton backButton = new JButton("Voltar");

        JTable productTable;
        JScrollPane scrollPane;

        public ProductSearchInterface() {
            super("PharmaHub: Consultar produtos");
            //setWindowIcon(this);

            searchBy.addItem("Nome");
            searchBy.addItem("ID do Fabricante");

            searchButton.setBackground(Color.WHITE);
            searchButton.setForeground(Color.BLACK);

            showAllButton.setBackground(Color.WHITE);
            showAllButton.setForeground(Color.BLACK);

            backButton.setBackground(Color.WHITE);
            backButton.setForeground(Color.BLACK);

            Dimension buttonSize = new Dimension(150, 30);
            searchButton.setPreferredSize(buttonSize);
            showAllButton.setPreferredSize(buttonSize);
            backButton.setPreferredSize(buttonSize);

            searchField.setPreferredSize(new Dimension(300, 30));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            searchPanel.add(searchFieldLabel, gbc);

            gbc.gridx = 1;
            searchPanel.add(searchBy, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 2;
            searchPanel.add(searchField, gbc);

            gbc.gridy = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            searchPanel.add(searchButton, gbc);

            gbc.gridy = 3;
            searchPanel.add(showAllButton, gbc);

            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.add(searchPanel, BorderLayout.NORTH);

            JPanel bottomPanel = new JPanel();
            bottomPanel.add(backButton);
            mainPanel.add(bottomPanel, BorderLayout.SOUTH);

            add(mainPanel, BorderLayout.NORTH);

            searchButton.addActionListener(this);
            showAllButton.addActionListener(e -> displayAllProducts());
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

                List<Product> products = new ArrayList<>();
                try {
                    if (searchCriteria.equals("Nome"))
                        products = DatabaseManager.searchByProductName(searchText);
                    else if (searchCriteria.equals("ID do Fabricante"))
                        products = DatabaseManager.searchProductsByManufacturerId(Integer.parseInt(searchText));
                    if (products.size() > 0) {
                        displayProducts(products);
                    } else {
                        JOptionPane.showMessageDialog(this, "Nenhum produto encontrado");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao acessar o banco de dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao acessar o banco de dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "ID do Fabricante deve ser um número válido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        private void displayAllProducts() {
            List<Product> products = new ArrayList<>();
            try {
                products = DatabaseManager.findAll();
                if (products.size() > 0) {
                    displayProducts(products);
                } else {
                    JOptionPane.showMessageDialog(this, "Nenhum produto encontrado");
                }
            } catch (SQLException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao acessar o banco de dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void displayProducts(List<Product> products) {
            String[] columnNames = {"ID", "Nome", "Preço", "ID do Fabricante", "Nome do Fabricante"};
            Object[][] data = new Object[products.size()][5];

            for (int i = 0; i < products.size(); i++) {
                Product product = products.get(i);
                data[i][0] = product.getId();
                data[i][1] = product.getName();
                data[i][2] = product.getPrice();
                data[i][3] = product.getManufacturer().getId();
                data[i][4] = product.getManufacturer().getName();
            }

            if (scrollPane != null) {
                remove(scrollPane);
            }

            productTable = new JTable(data, columnNames);
            scrollPane = new JScrollPane(productTable);

            add(scrollPane, BorderLayout.CENTER);
            revalidate();
            repaint();
        }
    }

    // Interface para inserção de produtos
    public class ProductInsertionInterface extends JFrame implements ActionListener {
        JPanel centralizeItems = new JPanel(new GridBagLayout());
        JPanel insertPanel = new JPanel(new BorderLayout(10, 10));
        JLabel productNameLabel = new JLabel("Nome do Produto: ");
        JTextField productNameField = new JTextField(20);
        JLabel productManufacturerNameLabel = new JLabel("Nome do Fabricante: ");
        JTextField productManufacturerNameField = new JTextField(20);
        JLabel productPriceLabel = new JLabel("Preço: ");
        JTextField productPriceField = new JTextField(20);
        JButton insertButton = new JButton("Inserir");

        JButton backButton = new JButton("Voltar");

        public ProductInsertionInterface() {
            super("PharmaHub: Inserir produtos");
            //setWindowIcon(this);

            insertButton.setBackground(Color.WHITE);
            insertButton.setForeground(Color.BLACK);

            backButton.setBackground(Color.WHITE);
            backButton.setForeground(Color.BLACK);

            productNameField.setPreferredSize(new Dimension(300, 30));
            productManufacturerNameField.setPreferredSize(new Dimension(300, 30));
            productPriceField.setPreferredSize(new Dimension(300, 30));

            JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
            inputPanel.add(productNameLabel);
            inputPanel.add(productNameField);
            inputPanel.add(productManufacturerNameLabel);
            inputPanel.add(productManufacturerNameField);
            inputPanel.add(productPriceLabel);
            inputPanel.add(productPriceField);

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
                String productName = productNameField.getText().trim();
                String productManufacturerName = productManufacturerNameField.getText().trim();
                Double productPrice = Double.parseDouble(productPriceField.getText().trim());
                JOptionPane.showMessageDialog(this, "Nome do Produto: " + productName + "\nNome do Fabricante: " + productManufacturerName);

                Manufacturer m;
                try {
                    m = DatabaseManager.searchManufacturerByName(productManufacturerName);
                    if (m != null) {
                        Product p = new Product(null, productName, productPrice, m);
                        if (DatabaseManager.insertProduct(p)) {
                            JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!");
                        } else
                            JOptionPane.showMessageDialog(this, "Erro ao cadastrar produto.");
                    } else
                        JOptionPane.showMessageDialog(this, "Erro ao cadastrar produto.");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

    }


    // Interface para registro do usuário
    public class RegisterInterface extends JFrame implements ActionListener {
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
            //setWindowIcon(this);

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

            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.add(registerPanel, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(backButton);
            mainPanel.add(buttonPanel, BorderLayout.SOUTH);

            add(mainPanel);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setVisible(true);

            backButton.addActionListener(e -> {
                new InitialInterface();
                dispose();
            });
        }

        // Realiza o registro de um usuário
        @Override
        public void actionPerformed(ActionEvent e) {
            if (new String(passwordField.getPassword()).equals(new String(confirmPasswordField.getPassword()))) {
                try {
                    if (DatabaseManager.register(usernameField.getText(), new String(passwordField.getPassword()))) {
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

    // Interface para login do usuário
    public class LoginInterface extends JFrame implements ActionListener {
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
            //setWindowIcon(this);

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

            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.add(loginPanel, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(backButton);
            mainPanel.add(buttonPanel, BorderLayout.SOUTH);

            add(mainPanel);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setVisible(true);

            backButton.addActionListener(e -> {
                new InitialInterface();
                dispose();
            });
        }

        // Tenta realizar a autenticação do usuário. Caso não seja possível, mostra uma modal de erro
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean authenticated = false;
            try {
                authenticated = DatabaseManager.login(usernameField.getText(), new String(passwordField.getPassword()));
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

    // Função principal do projeto, cria as tabelas e inicializa a interface gráfica principal
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        try {
            DatabaseManager.createUserTable();
            DatabaseManager.createManufacturerTable();
            DatabaseManager.createProductTable();
        } catch (SQLException ex) {
            System.out.println("As tabelas já foram criadas!");
            System.out.println(ex.getMessage());
        }
        new GUI().new InitialInterface();
    }
}
