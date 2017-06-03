package com.dushan.salary;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 工资比较
 * @author dushan
 *
 */
public class SalaryCompare {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("请输入当前基础工资:");
		while(in.hasNextFloat()){
			//当前公司
			float salaryBaseNow = in.nextFloat();
			System.out.println("请输入当前总工资:");
			float salaryTotalNow = in.nextFloat();
			System.out.println("请输入每年几个月薪资:");
			int salaryMonthNumNow = in.nextInt();
			System.out.println("请输入年终奖数额,单独计税");
			float bonus = in.nextFloat();
			
			//跳槽公司
			System.out.println("请输入跳槽公司基础工资:");
			float salaryBaseFuture = in.nextFloat();
			System.out.println("请输入跳槽公司总工资:");
			float salaryTotalFuture = in.nextFloat();
			System.out.println("请输入跳槽公司每年几个月薪资:");
			int salaryMonthNumFuture = in.nextInt();
			System.out.println("请输入跳槽公司年终奖数额,单独计税");
			float bonusFuture = in.nextFloat();
			
			//计算当前公司五险一金
			Map<String,Object> welfareMapNow = welfare(salaryBaseNow);
			//计算每月个人所得
			double salaryPerMonthNow = taxPerMonth(salaryTotalNow,welfareMapNow);
			//计算项目奖金拿到手多少钱
			double bonusResultNow = taxSingle(bonus);

			//计算跳槽公司五险一金
			Map<String,Object> welfareMapFuture = welfare(salaryBaseFuture);
			//计算跳槽每月个人所得
			double salaryPerMonthFuture = taxPerMonth(salaryTotalFuture,welfareMapFuture);
			//计算跳槽项目奖金拿到手多少钱
			double bonusResultFuture = taxSingle(bonusFuture);
			
			//当前公司每年拿到的钱+失业，医保，养老,公积金数额
			double salaryYearNow = getSalaryAndWelfarePerYear(salaryPerMonthNow, salaryTotalNow, welfareMapNow, bonusResultNow, salaryMonthNumNow)[0];
			double welfareYearNow = getSalaryAndWelfarePerYear(salaryPerMonthNow, salaryTotalNow, welfareMapNow, bonusResultNow, salaryMonthNumNow)[1];
			double HouseYearNow = getSalaryAndWelfarePerYear(salaryPerMonthNow, salaryTotalNow, welfareMapNow, bonusResultNow, salaryMonthNumNow)[2];
			
			//跳槽公司每年拿到的钱+失业，医保，养老,公积金数额
			double salaryYearFuture = getSalaryAndWelfarePerYear(salaryPerMonthFuture, salaryTotalFuture, welfareMapFuture, bonusResultFuture, salaryMonthNumFuture)[0];
			double welfareYearFuture = getSalaryAndWelfarePerYear(salaryPerMonthFuture, salaryTotalFuture, welfareMapFuture, bonusResultFuture, salaryMonthNumFuture)[1];
			double HouseYearFuture = getSalaryAndWelfarePerYear(salaryPerMonthFuture, salaryTotalFuture, welfareMapFuture, bonusResultFuture, salaryMonthNumFuture)[2];
			
			System.out.println("当前公司每年拿到手上的钱"+salaryYearNow);
			System.out.println("当前公司每年福利"+welfareYearNow);
			System.out.println("当前公司每年公积金"+HouseYearNow);
			System.out.println("跳槽后");
			System.out.println("跳槽公司每年拿到手上的钱"+salaryYearFuture);
			System.out.println("跳槽公司每年福利"+welfareYearFuture);
			System.out.println("跳槽公司每年公积金"+HouseYearFuture);
		}
	}
	
	/**
	 * 每年拿到的钱+失业，医保，养老,公积金数额
	 * @return
	 */
	public static double[] getSalaryAndWelfarePerYear(double salaryPerMonth,float salaryTotal,Map<String,Object> welfareMap,
			double bonusResult,int salaryMonthNum){
		double LoseJobPerson = (double) welfareMap.get("LoseJobPerson");
		double LoseJobCompany = (double) welfareMap.get("LoseJobCompany");
		double HealthPerson = (double) welfareMap.get("HealthPerson");
		double HealthCompany = (double) welfareMap.get("HealthCompany");
		double OldPerson = (double) welfareMap.get("OldPerson");
		double OldCompany = (double) welfareMap.get("OldCompany");
		double HouseCompany = (double) welfareMap.get("HouseCompany");
		double HousePerson = (double) welfareMap.get("HousePerson");
		
		double welfareMonth = LoseJobPerson+LoseJobCompany+HealthPerson+HealthCompany+OldPerson+OldCompany+HouseCompany+HousePerson;
		double houseMonth = HousePerson+HouseCompany;
		
		double SalaryYear = 0;
		double welfareYear = 0;
		double houseYear = 0;
		double salaryLastYear = 0;
		double[] getSalaryAndWelfarePerYear = new double[3];
		
		if(salaryMonthNum > 12){
			salaryLastYear = taxPerMonth((salaryMonthNum-11)*salaryTotal,welfareMap);
			SalaryYear = salaryPerMonth * 11+bonusResult+salaryLastYear;
			welfareYear = welfareMonth * 12;
			houseYear = houseMonth * 12;
			getSalaryAndWelfarePerYear[0] = SalaryYear;
			getSalaryAndWelfarePerYear[1] = welfareYear;
			getSalaryAndWelfarePerYear[2] = houseYear;
		}else{
			SalaryYear = salaryPerMonth * 12+bonusResult;
			welfareYear = welfareMonth * 12;
			houseYear = houseMonth * 12;
			getSalaryAndWelfarePerYear[0] = SalaryYear;
			getSalaryAndWelfarePerYear[1] = welfareYear;
			getSalaryAndWelfarePerYear[2] = houseYear;
		}
		return getSalaryAndWelfarePerYear;
	}
	
	/**
	 * 计算五险一金
	 * @param salary
	 * @return
	 */
	public static Map<String, Object> welfare(double salary){
		Map<String,Object> welfareMap = new HashMap<String,Object>();
		double HouseCompany = salary*BaseConstants.getHouseproportationcompany();
		double HousePerson = salary*BaseConstants.getHouseproportationperson();
		double OldCompany = salary*BaseConstants.getOldproportationcompany();
		double OldPerson = salary*BaseConstants.getOldproportationperson();
		double LoseJobCompany = salary*BaseConstants.getLosejobproportationcompany();
		double LoseJobPerson = salary*BaseConstants.getLosejobproportationperson();
		double HurtCompany = salary*BaseConstants.getHurtproportationcompany();
		double BirthCompany = salary*BaseConstants.getBirthproportationcompany();
		double HealthCompany = salary*BaseConstants.getHealthproportationcompany();
		double HealthPerson = salary*BaseConstants.getHealthproportationperson();
		welfareMap.put("HouseCompany", HouseCompany);
		welfareMap.put("HousePerson", HousePerson);
		welfareMap.put("OldCompany", OldCompany);
		welfareMap.put("OldPerson", OldPerson);
		welfareMap.put("LoseJobCompany", LoseJobCompany);
		welfareMap.put("LoseJobPerson", LoseJobPerson);
		welfareMap.put("HurtCompany", HurtCompany);
		welfareMap.put("BirthCompany", BirthCompany);
		welfareMap.put("HealthCompany", HealthCompany);
		welfareMap.put("HealthPerson", HealthPerson);
		return welfareMap;
	}
	
	/**
	 * 计算每月个人所得税
	 * 个人所得税是每月工资减去个人交的失业，医保，养老，住房公积金后
	 * 再减去3500起薪，剩下来的计算相应的税率
	 * @return
	 */
	public static double taxPerMonth(float SalaryTotal,Map<String,Object> welfareMap){
		double LoseJobPerson = (double) welfareMap.get("LoseJobPerson");
		double HealthPerson = (double) welfareMap.get("HealthPerson");
		double OldPerson = (double) welfareMap.get("OldPerson");
		double taxSalary = SalaryTotal-3500-LoseJobPerson-HealthPerson-OldPerson;
		double salaryPerMonth = 0;
		
		if(taxSalary>0 && taxSalary<=1500){
			salaryPerMonth = taxSalary - taxSalary*BaseConstants.getTaxproportationfirst() - BaseConstants.getMinusfirst();
		}else if(taxSalary>1500 && taxSalary<=4500){
			salaryPerMonth = taxSalary - taxSalary*BaseConstants.getTaxproportationsecond() - BaseConstants.getMinussecond();
		}else if(taxSalary>4500 && taxSalary<=9000){
			salaryPerMonth = taxSalary - taxSalary*BaseConstants.getTaxproportationthird() - BaseConstants.getMinusthird();
		}else if(taxSalary>9000 && taxSalary<35000){
			salaryPerMonth = taxSalary - taxSalary*BaseConstants.getTaxproportationforth() - BaseConstants.getMinusforth();
		}else if(taxSalary>35000 && taxSalary<=55000){
			salaryPerMonth = taxSalary - taxSalary*BaseConstants.getTaxproportationfifth() - BaseConstants.getMinusfifth();
		}else if(taxSalary>55000 && taxSalary<=80000){
			salaryPerMonth = taxSalary - taxSalary*BaseConstants.getTaxproportationsixth() - BaseConstants.getMinussixth();
		}else{
			salaryPerMonth = taxSalary - taxSalary*BaseConstants.getTaxproportationseventh() - BaseConstants.getMinusseventh();
		}
		return salaryPerMonth;
	}
	
	/**
	 * 计算项目奖金，这个是单独计税
	 * @param bonus
	 * @return
	 */
	public static double taxSingle(float bonus){
		double bonusResult = 0;
		
		if(bonus>0 && bonus<=1500){
			bonusResult = bonus - bonus*BaseConstants.getTaxproportationfirst() - BaseConstants.getMinusfirst();
		}else if(bonus>1500 && bonus<=4500){
			bonusResult = bonus - bonus*BaseConstants.getTaxproportationsecond() - BaseConstants.getMinussecond();
		}else if(bonus>4500 && bonus<=9000){
			bonusResult = bonus - bonus*BaseConstants.getTaxproportationthird() - BaseConstants.getMinusthird();
		}else if(bonus>9000 && bonus<35000){
			bonusResult = bonus - bonus*BaseConstants.getTaxproportationforth() - BaseConstants.getMinusforth();
		}else if(bonus>35000 && bonus<=55000){
			bonusResult = bonus - bonus*BaseConstants.getTaxproportationfifth() - BaseConstants.getMinusfifth();
		}else if(bonus>55000 && bonus<=80000){
			bonusResult = bonus - bonus*BaseConstants.getTaxproportationsixth() - BaseConstants.getMinussixth();
		}else{
			bonusResult = bonus - bonus*BaseConstants.getTaxproportationseventh() - BaseConstants.getMinusseventh();
		}
		return bonusResult;
	}
}
