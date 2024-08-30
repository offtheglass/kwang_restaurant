package kwang.gotorestaurant.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import kwang.gotorestaurant.domain.*;
import kwang.gotorestaurant.service.MenuRestaurantService;
import kwang.gotorestaurant.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Controller
@Slf4j
public class RestaurantController {

    @Autowired
    RestaurantService rs;

    @Autowired
    MenuRestaurantService ms;

    @Autowired
    EntityManagerFactory emf;

    @Transactional
    @PostMapping("/addRestaurant")
    public String addRestaurant(
            @RequestParam("name") String name,
            @RequestParam("meal_alcohol") mealoralcohol meal_alcohol,
            @RequestParam("url") String url,
            @RequestParam("saturdayopentime") int saturdayopentime,
            @RequestParam("sundayopentime") int sundayopentime,
            @RequestParam("maxNumOfPeople") int maxNumOfPeople,
            @RequestParam("averagePrice") int averagePrice,
            @RequestParam(name = "mealtypes",required = false) List<mealtype> mealtypes) throws MalformedURLException {

        System.out.println("post add restaurant");
        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
        restaurant.setMeal_alcohol(meal_alcohol);
        restaurant.setUrl(new URL(url));
        restaurant.setSaturdayopentime(saturdayopentime);
        restaurant.setSundayopentime(sundayopentime);
        restaurant.setMaxNumOfPeople(maxNumOfPeople);
        restaurant.setAveragePrice(averagePrice);
        rs.join(restaurant);



        List<mealtype> mealTypeList = (mealtypes != null) ? mealtypes : new ArrayList<>();

        for(mealtype mt:mealTypeList){
            MenuRestaurant menuRestaurant = new MenuRestaurant();
            menuRestaurant.setMealtype(mt);
            ms.join(menuRestaurant,restaurant);
        }


        return "redirect:/";
    }

    @GetMapping("/addRestaurant")
    public String addRestaurant() {

        log.info("addRestaurant controller");
        return "addRestaurant";
    }


    @GetMapping("/listRestaurants")
    public String listRestaurants(Model model) {
        List<Restaurant> restaurants = rs.findAll();
        model.addAttribute("restaurants", restaurants);
        return "listRestaurants";
    }

    @GetMapping("/random")
    public String getRandomRestaurant(Model model) {
        Restaurant randomRestaurant = rs.getRandomRestaurant();
        model.addAttribute("restaurant", randomRestaurant);
        return "random";
    }


    @Transactional
    @GetMapping("/delete/{id}")
    public String deleteRestaurant(@PathVariable("id") Long id) {
        Restaurant restaurant = rs.findById(id);
        rs.delete(restaurant);
        return "redirect:/listRestaurants";
    }

    @GetMapping("/edit/{id}")
    public String editRestaurant(@PathVariable("id") Long id,Model model) {
        Restaurant restaurant = rs.findById(id);
        model.addAttribute("restaurant", restaurant);
        return "editRestaurant";
    }

    @Transactional
    @PostMapping("/edit/{id}")
    public String updateRestaurant(
            @PathVariable("id") Long id,
            @RequestParam("name") String name,
            @RequestParam("meal_alcohol") mealoralcohol meal_alcohol,
            @RequestParam("url") String url,
            @RequestParam("saturdayopentime") int saturdayopentime,
            @RequestParam("sundayopentime") int sundayopentime,
            @RequestParam("maxNumOfPeople") int maxNumOfPeople,
            @RequestParam("averagePrice") int averagePrice,
            @RequestParam(name = "mealtypes",required = false) List<mealtype> mealtypes) throws MalformedURLException {

//        EntityManager em = emf.createEntityManager();
//        EntityTransaction etr = em.getTransaction();

        Restaurant restaurant = rs.findById(id);
        restaurant.setName(name);
        restaurant.setMeal_alcohol(meal_alcohol);
        restaurant.setUrl(new URL(url));
        restaurant.setSaturdayopentime(saturdayopentime);
        restaurant.setSundayopentime(sundayopentime);
        restaurant.setMaxNumOfPeople(maxNumOfPeople);
        restaurant.setAveragePrice(averagePrice);
//
        List<MenuRestaurant> toRemove = new ArrayList<>();
        for (MenuRestaurant menures : restaurant.getMenuRestaurants()) {
            toRemove.add(menures);
        }

        for (MenuRestaurant menures : toRemove) {
            ms.delete(menures);
            restaurant.getMenuRestaurants().remove(menures);
        }

        restaurant.getMenuRestaurants().clear(); // DB에서 사라지는지 확인

        if (mealtypes != null && !mealtypes.isEmpty()) {
            for (mealtype type : mealtypes) {
                MenuRestaurant menuRestaurant = new MenuRestaurant();
                menuRestaurant.setMealtype(type);
                menuRestaurant.setRestaurant(restaurant);
                ms.join(menuRestaurant,restaurant);
            }
        }


//        em.flush();
//        etr.commit();

        return "redirect:/listRestaurants";
    }




    @PostMapping("/search")
    public String searchRestaurants(@RequestParam("day") int day,
                                    @RequestParam("opentime") int opentime,
                                    @RequestParam("numofpeople") int numofpeople,
                                    @RequestParam("avgprice") int avgprice,
                                    @RequestParam("mealoralcohol") String mealoralcohol,
                                    @RequestParam("mealtypes") List<mealtype> mealtypes,
                                    Model model) {

        System.out.println("postmapping, mealoralcohol is "+ mealoralcohol);
        mealoralcohol m_a = null;
        if(mealoralcohol.startsWith("M")){
            m_a = kwang.gotorestaurant.domain.mealoralcohol.Meal;
        }
        else if(mealoralcohol.startsWith("A")){
            m_a = kwang.gotorestaurant.domain.mealoralcohol.Alcohol;
        }



        RestaurantForm rsForm = new RestaurantForm(day, opentime, numofpeople, avgprice, m_a, mealtypes);
        List<Restaurant> restaurants = rs.findByProperty(rsForm);

        for(Restaurant res: restaurants){
            System.out.println(res.getName());
        }


        model.addAttribute("restaurants",restaurants);
        return "searchResult";

    }


}
