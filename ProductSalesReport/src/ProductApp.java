import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
 * GUI application that captures a mattress sale, uses the ProductCalculations
 * class to work out the VAT, commission and total sale amount, shows them in a
 * message and saves them to a file named data.txt.
 */
public class ProductApp extends JFrame {

    // The mattress products shown in the combo box
    private static final String[] PRODUCTS = {"Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"};

    // Line of asterisks used in data.txt
    private static final String STARS = "******************************";

    // Form components
    private JTextField customerNameField = new JTextField();
    private JComboBox<String> productComboBox = new JComboBox<>(PRODUCTS);
    private JTextField priceField = new JTextField();
    private JButton processButton = new JButton("PROCESS");

    // Constructor: builds the window, the menus and the form
    public ProductApp() {

        // Window settings
        setTitle("Product App");
        setSize(520, 330);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centre the window on the screen

        // Q.2.4 - Menu system: File (Exit) and Tools (Process, Clear)
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu toolsMenu = new JMenu("Tools");

        JMenuItem exitItem = new JMenuItem("Exit");
        JMenuItem processItem = new JMenuItem("Process");
        JMenuItem clearItem = new JMenuItem("Clear");

        fileMenu.add(exitItem);
        toolsMenu.add(processItem);
        toolsMenu.add(clearItem);
        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        setJMenuBar(menuBar);

        // Panel for the form
        JPanel panel = new JPanel(null);

        JLabel nameLabel = new JLabel("Customer Name:");
        nameLabel.setBounds(30, 40, 120, 28);
        customerNameField.setBounds(170, 40, 300, 28);

        JLabel productLabel = new JLabel("Select Product:");
        productLabel.setBounds(30, 90, 120, 28);
        productComboBox.setBounds(170, 90, 300, 28);

        JLabel priceLabel = new JLabel("Enter Price:");
        priceLabel.setBounds(30, 140, 120, 28);
        priceField.setBounds(170, 140, 300, 28);

        processButton.setBounds(170, 195, 300, 32);

        panel.add(nameLabel);
        panel.add(customerNameField);
        panel.add(productLabel);
        panel.add(productComboBox);
        panel.add(priceLabel);
        panel.add(priceField);
        panel.add(processButton);
        add(panel);

        processButton.addActionListener(e -> processSale());
        processItem.addActionListener(e -> processSale());  // same logic as the Process button
        clearItem.addActionListener(e -> clearForm());
        exitItem.addActionListener(e -> System.exit(0));    // closes the application
    }

    // Captures the sale, calculates the amounts, saves them to data.txt and shows them
    private void processSale() {

        // Capture the customer's name and the selected product
        String customerName = customerNameField.getText().trim();
        String product = (String) productComboBox.getSelectedItem();

        // Make sure a name was entered
        if (customerName.isEmpty()) {
            showError("Please enter the customer's name.");
            return;
        }

        // Make sure the price is a number bigger than 0
        double price;
        try {
            price = Double.parseDouble(priceField.getText().trim());
        } catch (NumberFormatException ex) {
            showError("Please enter a valid price, for example 3500.");
            return;
        }
        if (price <= 0) {
            showError("The price must be more than 0.");
            return;
        }

        // The ProductCalculations class works out the amounts
        IProductCalculations calculations = new ProductCalculations(price);
        double vat = calculations.GetVat();
        double commission = calculations.GetCommission();
        double total = calculations.GetTotal();

        // The lines shown in the message and saved in the file
        String[] details = {
                "Customer Name: " + customerName,
                "Product: " + product,
                "VAT: R " + roundToCents(vat),
                "COMMISSION: R " + roundToCents(commission),
                "TOTAL: R " + roundToCents(total)
        };

        saveToFile(details);
        JOptionPane.showMessageDialog(this, String.join("\n", details));
    }

    // Rounds an amount to 2 decimal places (cents) so the output stays tidy
    private double roundToCents(double amount) {
        return Math.round(amount * 100.0) / 100.0;
    }

    // Adds the sale to data.txt. The DATA LOG heading is written once, when the file is new
    private void saveToFile(String[] details) {
        File file = new File("data.txt");
        boolean isNewFile = !file.exists() || file.length() == 0;

        // true = add to the end of the file instead of overwriting it
        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
            if (isNewFile) {
                writer.println("DATA LOG");
                writer.println(STARS);
            }
            for (String line : details) {
                writer.println(line);
            }
            writer.println(STARS);
        } catch (IOException ex) {
            showError("Could not save to data.txt: " + ex.getMessage());
        }
    }

    // Clears the text boxes and sets the combo box back to index 0 (the first product)
    private void clearForm() {
        customerNameField.setText("");
        priceField.setText("");
        productComboBox.setSelectedIndex(0);
        customerNameField.requestFocus();
    }

    // Shows an error message box
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductApp().setVisible(true));
    }
}
