package kwang.gotorestaurant.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import kwang.gotorestaurant.domain.MenuRestaurant;
import kwang.gotorestaurant.domain.Restaurant;
import kwang.gotorestaurant.domain.mealoralcohol;
import kwang.gotorestaurant.domain.mealtype;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RestaurantRepository {
    private final EntityManager em;

    public void save(Restaurant restaurant) {
        em.persist(restaurant);
    }

    public Restaurant findById(Long id) {
        return em.find(Restaurant.class, id);
    }

    public void delete(Restaurant restaurant) {
        Assert.notNull(restaurant, "Entity must not be null!");
        Restaurant restaurant1 = em.find(Restaurant.class, restaurant.getRestaurantId());
        em.remove(restaurant1);
    }

    public List<Restaurant> findByName(String name) {
        String query = "select r from Restaurant r where r.name =:param";
        return em.createQuery(query).setParameter("param", name).getResultList();
    }

    public List<Restaurant> findByProperty(int day, int opentime, int NumofPeople, int avgprice, mealoralcohol mealoralcohol, List<mealtype> mealtypes) { // 선택 안 됐을 때, opentime(내가 식당에 갈 시간) = 0, NumofPeopple = 0, avgprice = 100만, mealtypes = new arrayList<>();

        System.out.println("findByProperty(day = "+day+", opentime = "+opentime+", NumofPeople = "+NumofPeople+", avgprice = "+avgprice+", meal_alcohol = "+mealoralcohol.toString());
        System.out.println("mealtypes is as below");
        for(mealtype mt:mealtypes){
            System.out.println(mt.toString());
        }

        String jpql = "";                                                                                                                               // day, meal_alcoohol은 필수 선택값
        if (mealtypes.size() == 0) {
            jpql = "select distinct r from Restaurant r where r.maxNumOfPeople>=:numofpeople and r.meal_alcohol=:MealOrAlcohol and r.averagePrice<=:avgp";
        }
        else {
            jpql = "select distinct r from Restaurant r join MenuRestaurant mr on r.restaurantId = mr.restaurant.restaurantId where r.maxNumOfPeople>=:numofpeople and r.meal_alcohol=:MealOrAlcohol and r.averagePrice<=:avgp";
        }

//        String jpql = "select distinct r from Restaurant r join MenuRestaurant mr on r.restaurantId = mr.restaurant.restaurantId where r.maxNumOfPeople>=:numofpeople and r.meal_alcohol=:MealOrAlcohol and r.averagePrice<=:avgp";

        // day,1: 토요일, 2: 일요일
        if (day == 1) {  // 토요일
            if (opentime != 0) { // open time 입력이 된 경우
                jpql += " and r.saturdayopentime <=:opentime";
            }
        } else {          // 일요일
            if (opentime != 0) { // open time 입력이 된 경우
                jpql += " and r.sundayopentime <=:opentime";
            }
        }

//        for(int i = 0; i<mealtypes.size(); i++){
//            if (i==0)
//                jpql += " and mr.mealtype = :mealtype"+i;//mealtypes.get(i).toString();
//            else{
//                jpql += " or mr.mealtype = :mealtype"+i; //mealtypes.get(i).toString();
//            }
//        }

        // original, 얘는 고른 mealtype이랑 식당의 mealtype이 정확하게 일치해야 select됨, 예를 들어 Korean, Meat를 골랐으면
        // 식당은 Koeran,Meat 모두를 가지고 있는 얘들만 선발됨.

        if (mealtypes.size() > 0) {
            jpql += " and mr.mealtype in :mealtypes group by r having count(distinct mr.mealtype) = :numofmealtype";
        }


        // 얘는 식당이 가지고 있는 mealtype중 하나라도 mealtypes에 해당되는 게 있으면 select됨. 식당이 Korean, Meat이고
        // 골라진 Mealtype이 Japanese,Meat,Soup면 그 식당은 선발됨(Meat가 겹치니깐)
//        if(mealtypes.size()>0) {
//            jpql += " and mr.mealtype in :mealtypes group by r";
//        }


        System.out.println(jpql);
        Query query = em.createQuery(jpql).setParameter("numofpeople", NumofPeople).setParameter("MealOrAlcohol", mealoralcohol).setParameter("avgp", avgprice);

        if (opentime != 0) {
            query.setParameter("opentime", opentime);
        }


        if (mealtypes.size() > 0) {
            // original
            query.setParameter("mealtypes", mealtypes).setParameter("numofmealtype", mealtypes.size());


            //        for(int i = 0;i<mealtypes.size();i++){
//            query.setParameter("mealtype"+i,mealtypes.get(i));
//        }


//        System.out.println(query.toString());

            List<Restaurant> result = query.getResultList();
            System.out.println("num of result is");
            System.out.println(result.size());
            return result;
        }
        return query.getResultList();
    }

        public List<Restaurant> findAll () {
            return em.createQuery("select r from Restaurant r", Restaurant.class).getResultList();
        }


    }
