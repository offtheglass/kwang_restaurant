package kwang.gotorestaurant.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Entity
@RequiredArgsConstructor
@Setter @Getter
public class Restaurant {
    @Id
    @GeneratedValue
    private Long restaurantId;

    @Column(unique = true)
    private String name;

    private mealoralcohol meal_alcohol;

    private URL url;

    private int saturdayopentime;  //  토요일에 안 열면 24

    private int sundayopentime;    //  일요일에 안 열면 24

    private int maxNumOfPeople;

    private int averagePrice;

    @OneToMany(mappedBy = "restaurant",orphanRemoval = true)
    private List<MenuRestaurant> menuRestaurants = new ArrayList<>();
}
