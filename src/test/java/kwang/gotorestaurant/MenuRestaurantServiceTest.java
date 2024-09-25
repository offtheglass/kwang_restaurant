package kwang.gotorestaurant;

import jakarta.persistence.EntityManager;
import kwang.gotorestaurant.domain.*;
import kwang.gotorestaurant.repository.MenuRestaurantRepository;
import kwang.gotorestaurant.service.MenuRestaurantService;
import kwang.gotorestaurant.service.RestaurantService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class MenuRestaurantServiceTest {
//    @Autowired
//    RestaurantRepository rr;
    @Autowired
    EntityManager em;

    @Autowired
    MenuRestaurantRepository mr;
    @Test
//    @Rollback(false)
    public void testJPQL() throws Exception{
        Restaurant res1 = new Restaurant();
        res1.setName("res1");
        res1.setAveragePrice(10000);
        res1.setSaturdayopentime(17);
        res1.setSundayopentime(17);
        res1.setMaxNumOfPeople(29);
        res1.setMeal_alcohol(mealoralcohol.Meal);
        em.persist(res1);

        Restaurant res2 = new Restaurant();
        res2.setName("res2");
        res2.setAveragePrice(10000);
        res2.setSaturdayopentime(17);
        res2.setSundayopentime(17);
        res2.setMaxNumOfPeople(29);
        res2.setMeal_alcohol(mealoralcohol.Meal);
        em.persist(res2);

        // koreanres1
        MenuRestaurant menures1 = new MenuRestaurant();
//        em.persist(menures1);

        mr.save(menures1,res1);
//        menures1.setRestaurant(res1);
        menures1.setMealtype(mealtype.Korean);

        // heavyres1

        MenuRestaurant menures2 = new MenuRestaurant();
//        em.persist(menures2);
        mr.save(menures2,res1);
//        menures2.setRestaurant(res1);
        menures2.setMealtype(mealtype.heavy);

        // japaneseres2
        MenuRestaurant menures3 = new MenuRestaurant();
//        em.persist(menures3);
        mr.save(menures3,res2);
//        menures3.setRestaurant(res2);

        menures3.setMealtype(mealtype.Japanese);

        // lightres2
        MenuRestaurant menures4 = new MenuRestaurant();
//        em.persist(menures4);
        mr.save(menures4,res2);
//        menures4.setRestaurant(res2);

        menures4.setMealtype(mealtype.light);

        // soopres1
        MenuRestaurant menures5 = new MenuRestaurant();
//        em.persist(menures5);
        mr.save(menures5,res1);
//        menures5.setRestaurant(res1);
        menures5.setMealtype(mealtype.soop);

        // soopres2
        MenuRestaurant menures6 = new MenuRestaurant();
//        em.persist(menures6);
        mr.save(menures6,res2);
//        menures6.setRestaurant(res2);
        menures6.setMealtype(mealtype.soop);



//        멤버삭제
        mr.delete(menures6);
        mr.delete(menures2);
//        List<mealtype> mealtypes = new ArrayList<>();
//        mealtypes.add(mealtype.Korean);
//        mealtypes.add(mealtype.soop);
//        mealtypes.add(mealtype.heavy);
//        mealtypes.add(mealtype.light);

//
//        List<Restaurant> findres = rr.findByProperty(1,18,11,19000, mealoralcohol.Meal,mealtypes);
//
//        for (Restaurant findre : findres) {
//            System.out.println(findre.getName());
//        }

    }


}
