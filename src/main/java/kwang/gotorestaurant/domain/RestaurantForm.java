package kwang.gotorestaurant.domain;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RestaurantForm {  // 레스토랑을 검색할 때 쓰는 클래스
    @NotEmpty(message = "요일은 필수입니다.")
    int day;
    @NotEmpty(message = "술과 식사 선택은 필수입니다")
    mealoralcohol meal_alcohol;

    int opentime;
    int NumofPeople;
    int avgprice;
    List<mealtype> mealtypes = new ArrayList<>();

    public RestaurantForm(int day, mealoralcohol meal_alcohol) {
        this.day = day;
        this.meal_alcohol = meal_alcohol;


        this.opentime = 0;
        this.NumofPeople = 0;
        this.avgprice = 1000000;
        this.mealtypes = new ArrayList<>();
    }

    public RestaurantForm(int day, int opentime, int numofPeople, int avgprice, mealoralcohol meal_alcohol, List<mealtype> mealtypes){
        this.day = day;
        this.meal_alcohol = meal_alcohol;
        this.opentime = opentime;
        this.NumofPeople = numofPeople;
        this.avgprice = avgprice;
        this.mealtypes = mealtypes;
    }
}
