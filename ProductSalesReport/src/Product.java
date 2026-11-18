public class Product implements IProduct {

    // Single-dimensional array: the product names
    private String[] productNames;

    // Two-dimensional array: each row is a product, each column is a month
    private int[][] productSales;

    // Constructor: receives the product names and their monthly sales
    public Product(String[] productNames, int[][] productSales) {
        this.productNames = productNames;
        this.productSales = productSales;
    }

    // Adds up every sales figure for every product and every month
    @Override
    public int GetTotalProductsSold() {
        int total = 0;

        for (int p = 0; p < productSales.length; p++) {
            for (int m = 0; m < productSales[p].length; m++) {
                total += productSales[p][m];
            }
        }
        return total;
    }

    // Total divided by the number of sales figures (3 products x 3 months = 9)
    @Override
    public double GetAverageProductSold() {
        int numberOfFigures = productSales.length * productSales[0].length;
        return (double) GetTotalProductsSold() / numberOfFigures;
    }

    // Finds the highest sales figure and returns that product with the figure,
    // for example "Cozy Nights Monarch(25)"
    @Override
    public String GetMostPopularProduct() {
        int highestSales = productSales[0][0];
        String mostPopular = productNames[0];

        for (int p = 0; p < productSales.length; p++) {
            for (int m = 0; m < productSales[p].length; m++) {
                if (productSales[p][m] > highestSales) {
                    highestSales = productSales[p][m];
                    mostPopular = productNames[p];
                }
            }
        }
        return mostPopular + "(" + highestSales + ")";
    }
}