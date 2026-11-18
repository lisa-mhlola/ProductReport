import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * Q.1.4 - Unit tests for the Product class
 * Uses the same sales figures as the sample screenshot.
 */
public class ProductTest {

    // Test data: the product names and sales from the sample screenshot
    private final String[] products = {"Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"};
    private final int[][] sales = {
            {25, 18, 20},   // Cozy Nights Monarch, months 1 to 3
            {15, 10, 12},   // Restonic Barbados, months 1 to 3
            {18, 15, 20}    // Sealy Phuket, months 1 to 3
    };

    // Unit Test 1: GetTotalProductsSold must return 153 (all 9 figures added up)
    @Test
    public void GetTotalProductSales_WithValidData_ReturnsCorrectValue() {
        // Arrange
        Product product = new Product(products, sales);

        // Act
        int result = product.GetTotalProductsSold();

        // Assert
        assertEquals(153, result);
    }

    // Unit Test 2: GetAverageProductSold must return 17.0 (153 divided by 9)
    @Test
    public void GetAverageSales_WithValidData_ReturnsCorrectValue() {
        // Arrange
        Product product = new Product(products, sales);

        // Act
        double result = product.GetAverageProductSold();

        // Assert (0.01 allows for tiny rounding differences in decimals)
        assertEquals(17.0, result, 0.01);
    }
}