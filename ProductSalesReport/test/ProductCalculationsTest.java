import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * Uses the price from the sample screenshot (R 3500).
 */
public class ProductCalculationsTest {

    // Unit Test 1: GetVat must return 15% of the price (3500 x 0.15 = 525.0)
    @Test
    public void GetVat_WithValidData_ReturnsCorrectValue() {
        // Arrange
        ProductCalculations calculations = new ProductCalculations(3500);

        // Act
        double result = calculations.GetVat();

        // Assert (0.01 allows for tiny rounding differences in decimals)
        assertEquals(525.0, result, 0.01);
    }

    // Unit Test 2: GetTotal must return price + VAT + commission (3500 + 525 + 175 = 4200.0)
    @Test
    public void GetTotal_WithValidData_ReturnsCorrectValue() {
        // Arrange
        ProductCalculations calculations = new ProductCalculations(3500);

        // Act
        double result = calculations.GetTotal();

        // Assert
        assertEquals(4200.0, result, 0.01);
    }
}