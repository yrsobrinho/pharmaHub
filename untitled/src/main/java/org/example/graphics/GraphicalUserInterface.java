package org.example.graphics;

import javax.swing.*;

public class GraphicalUserInterface {

    public class ProductSearchInterface extends JFrame {
        JLabel searchField = new JLabel("Consultar por: ");
        JComboBox<String> searchBy = new JComboBox<>();

        public ProductSearchInterface() {
            super("PharmaHub: Consultar produtos");
            searchBy.addItem("Nome");
            searchBy.addItem("ID");
            searchBy.addItem("Fabricante");

            setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
            add(searchField);
            add(searchBy);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            pack();
            setVisible(true);
        }
    }



    public static void main(String[] args) {
        new GraphicalUserInterface().new ProductSearchInterface();
    }
}


