package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Car {
    private String carId;
    private String carName;
    private String carColor;
    private int carQuantity;

    // Open for extension: Subclasses can override this method
    public void update(Car updatedCar) {
        this.carName = updatedCar.getCarName();
        this.carColor = updatedCar.getCarColor();
        this.carQuantity = updatedCar.getCarQuantity();
    }
}
