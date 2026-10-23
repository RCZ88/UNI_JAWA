package NAR.reNHlorist;

import java.time.LocalDate;

public class EventPackage extends Bouquet {
    private Florist assignedFlorist;
    private double discountPercent;
    private LocalDate deliveryDate;
    private String deliveryAddress;

    public EventPackage(String productCode, String name, double basePrice, int stock) {
        super(productCode, name, basePrice, stock);
    }

    public Florist getAssignedFlorist() {
        return assignedFlorist;
    }

    public void setFlorist(Florist florist) {
        this.assignedFlorist = florist;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void applyDiscount(double discountPercent) {
        this.discountPercent = discountPercent;
    }

        // TODO: Return this product's category name.
    public double calculateDiscountedPrice() {

        return (100-discountPercent)/100 * super.getBasePrice();
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void scheduleDelivery(LocalDate deliveryDate, String deliveryAddress) {
        this.deliveryDate = deliveryDate;
        this.deliveryAddress = deliveryAddress;
    }

    public boolean hasDeliveryScheduled() {
        return deliveryDate != null;
    }

    @Override
    public String getCategory() {
        return "Event Package";
    }
}
