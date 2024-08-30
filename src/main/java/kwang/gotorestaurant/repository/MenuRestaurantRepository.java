package kwang.gotorestaurant.repository;

import jakarta.persistence.EntityManager;
import kwang.gotorestaurant.domain.MenuRestaurant;
import kwang.gotorestaurant.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;

@Repository
public class MenuRestaurantRepository {
    @Autowired
    EntityManager em;
    public void save(MenuRestaurant menuRestaurant, Restaurant restaurant){
        em.persist(menuRestaurant);
        menuRestaurant.setRestaurant(restaurant);
    }

    public void delete(MenuRestaurant menuRestaurant){
        Assert.notNull(menuRestaurant,"Entity must not be null!");
        // Find the managed instance in the persistence context
        MenuRestaurant managedMenuRestaurant = em.find(MenuRestaurant.class, menuRestaurant.getMenuRestaurantid());

        if (managedMenuRestaurant != null) {
            // Remove from the associated Restaurant's menuRestaurants list
            Restaurant restaurant = managedMenuRestaurant.getRestaurant();
            if (restaurant != null) {
                restaurant.getMenuRestaurants().remove(managedMenuRestaurant);
            }

            // Remove the MenuRestaurant
            em.remove(managedMenuRestaurant);

        }
    }

}

