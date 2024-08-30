package kwang.gotorestaurant.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@Getter @Setter
public class MenuRestaurant {
    @Id
    @GeneratedValue
    private Long menuRestaurantid;

    private mealtype mealtype;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurantId")
    private Restaurant restaurant;

    public void setRestaurant(Restaurant restaurant){
        this.restaurant = restaurant;
        restaurant.getMenuRestaurants().add(this);
    }

}
