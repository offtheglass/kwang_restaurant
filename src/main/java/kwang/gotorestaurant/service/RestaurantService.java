package kwang.gotorestaurant.service;

import kwang.gotorestaurant.domain.Restaurant;
import kwang.gotorestaurant.domain.RestaurantForm;
import kwang.gotorestaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository rr;

    public void join(Restaurant restaurant){
        rr.save(restaurant);
    }

    public  void delete(Restaurant restaurant){
        rr.delete(restaurant);
    }

    public Restaurant findById(Long id){
        return rr.findById(id);
    }

    public List<Restaurant> findByName(String name){
        return rr.findByName(name);
    }

    public List<Restaurant> findByProperty(RestaurantForm restaurantForm){
//        선택 안 됐을 때, opentime = 0, NumofPeopple = 0, avgprice = 100만, opentime = 25
        return rr.findByProperty(restaurantForm.getDay(), restaurantForm.getOpentime(), restaurantForm.getNumofPeople(), restaurantForm.getAvgprice(), restaurantForm.getMeal_alcohol(), restaurantForm.getMealtypes());
    }

    public List<Restaurant> findAll() {
        return rr.findAll();
    }


    public Restaurant getRandomRestaurant() {
        List<Restaurant> restaurants = rr.findAll();
        if (restaurants.isEmpty()) {
            return null;
        }
        int randomIndex = new Random().nextInt(restaurants.size());
        return restaurants.get(randomIndex);
    }



}
