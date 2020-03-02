package com.example.Narrow.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Narrow.model.ChartBarOrder;
import com.example.Narrow.model.ChartDonutOrder;
import com.example.Narrow.model.ChartLineOrder;
import com.example.Narrow.model.Currency;
import com.example.Narrow.model.StatisticData;
import com.example.Narrow.model.StatisticProfitMonthByUser;

@Service
public class ChartOrderServiceImpl implements ChartOrderService {
	@PersistenceContext
	private EntityManager em;

	@Autowired
	private CurrencyService currencyService;
	@Autowired
	private TaskService TaskService;

	@Autowired
	private OrderService orderService;

	@SuppressWarnings("unchecked")
	@Override
	public List<ChartLineOrder> getChartOrderLasWeekByUser(Long id) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -7);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);

		List<Object[]> chartData = em.createQuery(
				"select createDate , count(*)  from Order where  status =1 and createDate between :date and CURDATE() and user.id =:user group by createDate")
				.setParameter("date", cal.getTime()).setParameter("user", id).getResultList();
		List<ChartLineOrder> data = new ArrayList<>();
		for (int a = 0; a < 7; a++) {
			cal.add(Calendar.DATE, 1);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String date = format.format(cal.getTime());
			boolean flag = true;
			for (int b = 0; b < chartData.size(); b++) {
				Object[] dataChart = (Object[]) chartData.get(b);
				String datee = (String) dataChart[0].toString();

				int number = ((Long) dataChart[1]).intValue();

				if (date.equals(datee)) {
					ChartLineOrder chartLineOrder = new ChartLineOrder();
					chartLineOrder.setDate(datee);
					chartLineOrder.setNumberOrders(number);
					data.add(chartLineOrder);
					flag = false;
					break;
				}
			}
			if (flag) {
				ChartLineOrder chartLineOrder = new ChartLineOrder();
				chartLineOrder.setDate(date);
				chartLineOrder.setNumberOrders(0);
				data.add(chartLineOrder);
			}

		}

		return data;
	}

	@Override
	public List<ChartDonutOrder> getChartDonutData() {
		@SuppressWarnings("unchecked")
		List<ChartDonutOrder> chartData = em
				.createQuery("select user.username , count(*)  from Order where status =1 group by user.username")
				.getResultList();

		return chartData;

	}

	@Override
	public List<StatisticData> getStatistic(Long id) {
		List<StatisticData> statistic = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH) + 1;
		int countOrders = orderService.getCountOrders();
		StatisticData orders = new StatisticData("Zamówienia w " + getMonth(month, true), String.valueOf(countOrders),
				"assignment");
		statistic.add(orders);

		int countMyOrders = orderService.getCountOrdersByIdUser(id);
		StatisticData myOrders = new StatisticData("Moje zamówienia w " + getMonth(month, true),
				String.valueOf(countMyOrders), "assignment");
		statistic.add(myOrders);

		Currency currency = currencyService.getActualCurrency();

		StatisticData currencySellData = new StatisticData("Kurs sprzedaży EUR",
				currency.getSalesPrice().toString() + " zł", "euro_symbol");
		statistic.add(currencySellData);

		String profit = orderService.getProfitPerMonth();
		StatisticData profitPLN = new StatisticData("Zysk w " + getMonth(month, true), profit + " zł", "trending_up");
		statistic.add(profitPLN);

		String profitByUser = orderService.getProfitPerMonthByIdUser(id);
		StatisticData myProfitPLN = new StatisticData("Mój zysk w " + getMonth(month, true), profitByUser + " zł",
				"trending_up");
		statistic.add(myProfitPLN);

		int countTask = TaskService.getCountTaskByUserId(id);
		StatisticData task = new StatisticData("Zadania", String.valueOf(countTask), "calendar_today");
		statistic.add(task);

		return statistic;
	}

	@Override
	public List<ChartBarOrder> getChartBarData() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);

		@SuppressWarnings("unchecked")
		List<Object[]> data = em.createQuery(
				"select user.username , count(*),month(createDate),year(createDate)  from Order where status =1 and year(createDate) =:year  group by user.username,month(createDate),year(createDate) order by month(createDate)")
				.setParameter("year", year).getResultList();
		List<String> user = new ArrayList<>();
		List<ChartBarOrder> chartData = new ArrayList<ChartBarOrder>();
		for (int a = 0; a < data.size(); a++) {
			String userNameString = data.get(a)[0].toString();
			if (!user.contains(userNameString)) {
				user.add(userNameString);
			}
		}

		for (int a = 1; a <= 12; a++) {
			boolean flaga = false;
			for (int b = 0; b < user.size(); b++) {
				String userName = user.get(b);
				for (int c = 0; c < data.size(); c++) {
					String name = data.get(c)[0].toString();
					int number = Integer.valueOf(data.get(c)[1].toString());
					int month = Integer.valueOf(data.get(c)[2].toString());
					ChartBarOrder chartBarOrder = new ChartBarOrder();

					if (name.equals(userName) && month == a) {
						chartBarOrder.setMonthYear(String.valueOf(a) + '-' + year);
						chartBarOrder.setNumber(number);
						chartBarOrder.setUserName(name);
						chartData.add(chartBarOrder);
						flaga = true;
						break;

					}
					flaga = false;

				}
				if (!flaga) {
					ChartBarOrder chartBarOrder = new ChartBarOrder();

					chartBarOrder.setMonthYear(String.valueOf(a) + '-' + year);
					chartBarOrder.setNumber(0);
					chartBarOrder.setUserName(userName);

					chartData.add(chartBarOrder);
					flaga = true;
				}
			}

		}
		return chartData;
	}

	@Override
	public List<StatisticProfitMonthByUser> getProfitMonthsByUser(Long id) {

		List<StatisticProfitMonthByUser> statisticData = new ArrayList<StatisticProfitMonthByUser>();
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		@SuppressWarnings("unchecked")
		List<Object[]> result = em.createQuery(
				"select month(createDate), sum(profitPLN), sum(profitEUR),count(*)  from Order where user.id = :idUser and year(createDate) =:year group by month(createDate) order by month(createDate) desc ")
				.setParameter("idUser", id).setParameter("year", year).getResultList();

		for (int a = 0; a < result.size(); a++) {
			int month = (int) result.get(a)[0];
			BigDecimal profitPLN = (BigDecimal) result.get(a)[1];
			BigDecimal profitEUR = (BigDecimal) result.get(a)[2];
			Integer quantity = Integer.valueOf(result.get(a)[3].toString());
			String monthName = getMonth(month, false);

			StatisticProfitMonthByUser statistic = new StatisticProfitMonthByUser();
			statistic.setDate(monthName);
			statistic.setProfitPLN(profitPLN);
			statistic.setProfitEUR(profitEUR);
			statistic.setQuantity(quantity);
			statisticData.add(statistic);
		}
		return statisticData;

	}

	public String getMonth(int month, boolean nazwa) {
		String montNameString;

		switch (month) {
		case 1:
			montNameString = nazwa ? "Styczeniu" : "Styczeń";
			break;
		case 2:
			montNameString = nazwa ? "Lutym" : "Luty";
			break;
		case 3:
			montNameString = nazwa ? "Marcu" : "Marzec";
			break;
		case 4:
			montNameString = nazwa ? "Kwietniu" : "Kwiecień";
			break;
		case 5:
			montNameString = nazwa ? "Maju" : "Maj";
			break;
		case 6:
			montNameString = nazwa ? "Czerwcu" : "Czerwiec";
			break;
		case 7:
			montNameString = nazwa ? "Lipcu" : "Lipiec";
			break;
		case 8:
			montNameString = nazwa ? "Sierpniu" : "Sierpień";
			break;
		case 9:
			montNameString = nazwa ? "Wrzesniu" : "Wrzesień";
			break;
		case 10:
			montNameString = nazwa ? "Pazdzierniku" : "Pazdziernik";
			break;
		case 11:
			montNameString = nazwa ? "Listopadzie" : "Listopad";
			break;
		case 12:
			montNameString = nazwa ? "Grudniu" : "Grudzień";
			break;

		default: {
			montNameString = "";
		}
		}
		return montNameString;
	}

}
