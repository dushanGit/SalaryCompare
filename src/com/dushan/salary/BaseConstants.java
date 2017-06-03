package com.dushan.salary;

/**
 * 一些静态变量
 *
 */
public class BaseConstants {

	public static final int slaryLowest = 1860;//杭州最低工资
	//养老个人8% 、公司14%；失业企业1%，个人0.5%；工伤企业0.3%，生育企业1%；医疗企业11.5%，医疗个人2%,公积金12%公司，个人12%
	//住房公积金，公司和个人
	private static final double HouseProportationCompany = 0.12;
	private static final double HouseProportationPerson = 0.12;
	//养老保险，公司和个人
	private static final double OldProportationCompany = 0.14;
	private static final double OldProportationPerson = 0.08;
	//失业保险，公司和个人
	private static final double LoseJobProportationCompany = 0.01;
	private static final double LoseJobProportationPerson = 0.005;
	//工伤，公司
	private static final double HurtProportationCompany = 0.003;
	//生育，公司
	private static final double BirthProportationCompany = 0.01;
	//医疗保险，公司和个人
	private static final double HealthProportationCompany = 0.115;
	private static final double HealthProportationPerson = 0.02;
	
	//税率和速算扣除数
	public static final double taxProportationFirst = 0.03;
	public static final int minusFirst = 0;
	public static final double taxProportationSecond = 0.1;
	public static final int minusSecond = 105;
	public static final double taxProportationThird = 0.2;
	public static final int minusThird = 555;
	public static final double taxProportationForth = 0.25;
	public static final int minusForth = 1005;
	public static final double taxProportationFifth = 0.3;
	public static final int minusFifth = 2755;
	public static final double taxProportationSixth = 0.35;
	public static final int minusSixth = 5505;
	public static final double taxProportationSeventh = 0.45;
	public static final int minusSeventh = 13505;
	
	public static int getSlarylowest() {
		return slaryLowest;
	}
	public static double getHouseproportationcompany() {
		return HouseProportationCompany;
	}
	public static double getHouseproportationperson() {
		return HouseProportationPerson;
	}
	public static double getOldproportationcompany() {
		return OldProportationCompany;
	}
	public static double getOldproportationperson() {
		return OldProportationPerson;
	}
	public static double getLosejobproportationcompany() {
		return LoseJobProportationCompany;
	}
	public static double getLosejobproportationperson() {
		return LoseJobProportationPerson;
	}
	public static double getHurtproportationcompany() {
		return HurtProportationCompany;
	}
	public static double getBirthproportationcompany() {
		return BirthProportationCompany;
	}
	public static double getHealthproportationcompany() {
		return HealthProportationCompany;
	}
	public static double getHealthproportationperson() {
		return HealthProportationPerson;
	}
	public static double getTaxproportationfirst() {
		return taxProportationFirst;
	}
	public static int getMinusfirst() {
		return minusFirst;
	}
	public static double getTaxproportationsecond() {
		return taxProportationSecond;
	}
	public static int getMinussecond() {
		return minusSecond;
	}
	public static double getTaxproportationthird() {
		return taxProportationThird;
	}
	public static int getMinusthird() {
		return minusThird;
	}
	public static double getTaxproportationforth() {
		return taxProportationForth;
	}
	public static int getMinusforth() {
		return minusForth;
	}
	public static double getTaxproportationfifth() {
		return taxProportationFifth;
	}
	public static int getMinusfifth() {
		return minusFifth;
	}
	public static double getTaxproportationsixth() {
		return taxProportationSixth;
	}
	public static int getMinussixth() {
		return minusSixth;
	}
	public static double getTaxproportationseventh() {
		return taxProportationSeventh;
	}
	public static int getMinusseventh() {
		return minusSeventh;
	}
	
}
