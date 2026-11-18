import java.util.Scanner;

/*
 * Q.1.1
 * Captures three months of sales for the company's three top-performing
 * mattresses, then uses the Product class to work out and display the
 * total sales, the average sales and the most popular product.
 */
public class ProductSalesReport {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Single-dimensional array: the names of the three products
        String[] products = {"Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"};

        // Number of months covered by the report
        final int MONTHS = 3;

        // Two-dimensional array: each row is a product, each column is a month
        int[][] sales = new int[products.length][MONTHS];

        // Capture the sales for every product, one month at a time
        for (int p = 0; p < products.length; p++) {
            for (int m = 0; m < MONTHS; m++) {
                System.out.print("Enter number of sales for month " + (m + 1)
                        + " for " + products[p] + " mattress: ");
                sales[p][m] = input.nextInt();
            }
            System.out.println(); // blank line between products
        }

        // Hand both arrays to the Product class, which does the calculations
        Product product = new Product(products, sales);

        // Display the report using the Product class methods
        System.out.println();
        System.out.println("PRODUCT SALES REPORT - 2026");
        System.out.println();
        System.out.println("******************************************");
        System.out.println("TOTAL PRODUCT SALES: " + product.GetTotalProductsSold());
        // The average comes back as a double (17.0); Math.round shows it as 17, like the sample
        System.out.println("AVERAGE PRODUCT SALES: " + Math.round(product.GetAverageProductSold()));
        System.out.println("MOST POPULAR PRODUCT: " + product.GetMostPopularProduct());

        input.close();
    }
}