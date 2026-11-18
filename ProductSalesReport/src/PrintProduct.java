/*
 * Responsible for printing the data for the product sales report.
 * It gets the figures from a Product object through the iProduct interface.
 */
public class PrintProduct {

    // The object that holds the calculated figures
    private iProduct product;

    // Constructor: receives the product data to print
    public PrintProduct(iProduct product) {
        this.product = product;
    }

    // Prints the report title and the line of asterisks
    public void PrintReportHeading() {
        System.out.println();
        System.out.println("PRODUCT SALES REPORT - 2026");
        System.out.println();
        System.out.println("******************************************");
    }

    // Prints the total product sales
    public void PrintTotalProductSales() {
        System.out.println("TOTAL PRODUCT SALES: " + product.GetTotalProductsSold());
    }

    // Prints the average product sales as a whole number, like the sample (17)
    public void PrintAverageProductSales() {
        System.out.println("AVERAGE PRODUCT SALES: " + Math.round(product.GetAverageProductSold()));
    }

    // Prints the most popular product and its highest sales figure
    public void PrintMostPopularProduct() {
        System.out.println("MOST POPULAR PRODUCT: " + product.GetMostPopularProduct());
    }
}