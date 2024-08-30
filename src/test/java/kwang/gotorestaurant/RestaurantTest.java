package kwang.gotorestaurant;

import jakarta.persistence.EntityManager;
import kwang.gotorestaurant.domain.MenuRestaurant;
import kwang.gotorestaurant.domain.Restaurant;
import kwang.gotorestaurant.domain.mealtype;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.fail;

//@RunWith(SpringRunner.class) // JUNIT실행할 때 스프링이랑 같이 실행하겠다.


@RunWith(SpringRunner.class) // JUNIT실행할 때 스프링이랑 같이 실행하겠다.
@SpringBootTest  // 스프링부트를 띄운 상태로 테스트를 하겠다. 얘네 두 개가 있어야 스프링이랑 integration해서 테스트할 수 있음
@Transactional  // @Transactional이 @Test에 있으면 롤백해서 commit이 안 나감
public class RestaurantTest {

    @Autowired
    EntityManager em;

    @Test
//    @Rollback(false) // @Test랑 @Transactional이 함께 작용하면 다 rollback시키니까 이를 안 시키고 DB에 쿼리가 날아가서 어떻게 적용되는지 보려고 @rollback(false)함
    public void testMember() throws Exception{

        Restaurant res = new Restaurant();
        res.setName("asddd");
        em.persist(res);

        MenuRestaurant menuRestaurant = new MenuRestaurant();
        menuRestaurant.setMealtype(mealtype.Korean);
        em.persist(menuRestaurant);

        MenuRestaurant menuRestaurant2 = new MenuRestaurant();
        menuRestaurant2.setMealtype(mealtype.Chinese);
        em.persist(menuRestaurant2);

        menuRestaurant.setRestaurant(res);
        menuRestaurant2.setRestaurant(res);

        MenuRestaurant findmenures = em.find(MenuRestaurant.class,menuRestaurant.getMenuRestaurantid());
        MenuRestaurant findmenures2 = em.find(MenuRestaurant.class,menuRestaurant2.getMenuRestaurantid());

        System.out.println(findmenures.getRestaurant().getName());
        System.out.println(findmenures2.getRestaurant().getName());

    }



}
