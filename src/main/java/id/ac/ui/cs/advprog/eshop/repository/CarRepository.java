package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class CarRepository {
    static int id = 0;
    private List<Car> carData = new ArrayList<>();



    public Iterator<Car> findAll() {
        return carData.iterator();
    }

    public Car findById(String id) {
        for (Car car : carData) {
            if (car.getCarId().equals(id)) {
                return car;
            }
        }
        return null;
    }


    public void delete(String id) {
        carData.removeIf(car -> car.getCarId().equals(id));
    }

    public Car save(Car car) {
        for (int i = 0; i < carData.size(); i++) {
            if (carData.get(i).getCarId().equals(car.getCarId())) {
                carData.set(i, car); // Update existing car
                return car;
            }
        }
        carData.add(car); // Add new car if not found
        return car;
    }
}