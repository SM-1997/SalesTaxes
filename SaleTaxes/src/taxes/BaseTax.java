package taxes;

public class BaseTax implements CalculateTax
{
    private static final int BASE_TAX = 10;
    
    public double calculateTax(final double price) {
        return roundUp(price / ONE_HOUNDRED * BASE_TAX);
    }
}