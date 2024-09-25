package kwang.gotorestaurant;

import jakarta.persistence.EntityManager;
import kwang.gotorestaurant.domain.MenuRestaurant;
import kwang.gotorestaurant.domain.Restaurant;
import kwang.gotorestaurant.domain.mealtype;
import kwang.gotorestaurant.repository.MenuRestaurantRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuRestaurantRepositoryTest {
    @Autowired
    MenuRestaurantRepository mrr;
    @Autowired
    EntityManager em;

    @Test
    public void test(){

    }

    @Test
//    @Rollback(false)
    public void saveTest() throws Exception{
        // given
        Restaurant res = new Restaurant();
        res.setName("res");
        em.persist(res);

        // when
        MenuRestaurant menures = new MenuRestaurant();
        menures.setMealtype(mealtype.Korean);
        // then
        mrr.save(menures,res);

    }

    @Test
//    @Rollback(false)
    public void deleteTest() throws Exception{
        // given
        Restaurant res = new Restaurant();
        res.setName("res4");
        em.persist(res);

        MenuRestaurant menures1 = new MenuRestaurant();
        menures1.setMealtype(mealtype.Korean);
        em.persist(menures1);

        MenuRestaurant menures2 = new MenuRestaurant();
        menures2.setMealtype(mealtype.Japanese);
        em.persist(menures2);

        // when

        menures1.setRestaurant(res);
        menures2.setRestaurant(res);

        em.flush();
        em.clear();

        // then

        mrr.delete(menures1);

    }

}
