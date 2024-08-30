package kwang.gotorestaurant.service;


import kwang.gotorestaurant.domain.MenuRestaurant;
import kwang.gotorestaurant.domain.Restaurant;
import kwang.gotorestaurant.repository.MenuRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MenuRestaurantService {
    @Autowired
    MenuRestaurantRepository mr;

    public void join(MenuRestaurant menuRestaurant, Restaurant restaurant){
        mr.save(menuRestaurant,restaurant);
    }
    public void delete(MenuRestaurant menuRestaurant){
        System.out.println("ms.delete");
        mr.delete(menuRestaurant);
    }

}
