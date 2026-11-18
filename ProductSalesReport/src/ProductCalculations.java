/*
 * Works out the VAT, commission and total sale amount for a product price.
 */
public class ProductCalculations implements IProductCalculations {

    // Rates used in the calculations
    private static final double VAT_RATE = 0.15;         // 15% of the product price
    private static final double COMMISSION_RATE = 0.05;  // 5% of the product price

    // The product price entered on the form
    private double price;

    // Constructor: receives the product price
    public ProductCalculations(double price) {
        this.price = price;
    }

    // VAT is 15% of the product price
    @Override
    public double GetVat() {
        return price * VAT_RATE;
    }

    // Commission is 5% of the product price
    @Override
    public double GetCommission() {
        return price * COMMISSION_RATE;
    }

    // Total sale amount = price + VAT + commission
    @Override
    public double GetTotal() {
        return price + GetVat() + GetCommission();
    }
}