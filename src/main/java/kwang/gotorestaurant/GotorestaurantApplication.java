package kwang.gotorestaurant;

import jakarta.persistence.*;
import kwang.gotorestaurant.domain.MenuRestaurant;
import kwang.gotorestaurant.domain.Restaurant;
import kwang.gotorestaurant.domain.mealoralcohol;
import kwang.gotorestaurant.domain.mealtype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@ComponentScan(basePackages = {"kwang.gotorestaurant","kwang.gotorestaurant.controller"})
public class GotorestaurantApplication {

	public static void main(String[] args) {
		SpringApplication.run(GotorestaurantApplication.class, args);

	}

}
