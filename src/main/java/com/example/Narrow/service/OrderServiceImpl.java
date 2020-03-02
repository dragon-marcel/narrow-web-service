package com.example.Narrow.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Narrow.model.Currency;
import com.example.Narrow.model.Order;
import com.example.Narrow.repository.OrderRepository;

import org.apache.tomcat.util.buf.StringUtils;

@Service
public class OrderServiceImpl implements OrderService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private CurrencyService currencyService;

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findAll() {
		return em.createQuery("from Order where status = :status").setParameter("status", 1).getResultList();
	}

	@Override
	public Order save(Order order) {
		String nrOrder = getNewNrOrder();
		order.setCreateDate(new Date());
		order.setNumber(nrOrder);
		order.setStatus(1);
		Currency currency = currencyService.getActualCurrency();
		System.out.print("CURRENCY:" + order.toString());
		order.setCurrency(currency);
		if (order.getSymbol().equals("PLN")) {
			order.setValueSellEUR(order.getValueSellPLN().divide(currency.getSalesPrice(), 2, RoundingMode.HALF_UP));
			order.setValuePurchaseEUR(
					order.getValuePurchasePLN().divide(currency.getSalesPrice(), 2, RoundingMode.HALF_UP));
			order.setProfitEUR(order.getProfitPLN().divide(currency.getSalesPrice(), 2, RoundingMode.HALF_UP));
			order.setPriceSellEUR(order.getPriceSellPLN().divide(currency.getSalesPrice(), 2, RoundingMode.HALF_UP));
			order.setPricePurchaseEUR(
					order.getPricePurchasePLN().divide(currency.getSalesPrice(), 2, RoundingMode.HALF_UP));
		} else {
			order.setValueSellPLN(order.getValueSellEUR().multiply(currency.getSalesPrice()));
			order.setValuePurchasePLN(order.getValuePurchaseEUR().multiply(currency.getSalesPrice()));
			order.setProfitPLN(order.getProfitEUR().multiply(currency.getSalesPrice()));
			order.setPriceSellPLN(order.getPriceSellEUR().multiply(currency.getSalesPrice()));
			order.setPricePurchasePLN(order.getPricePurchaseEUR().multiply(currency.getSalesPrice()));

		}
		orderRepository.save(order);
		System.out.print("NOWY :" + order.toString());
		return order;
	}

	@Override
	public Order editOrder(Order order) {

		Currency currency = order.getCurrency();
		if (order.getSymbol().equals("PLN")) {
			order.setValueSellEUR(order.getValueSellPLN().divide(currency.getSalesPrice(), 2, RoundingMode.HALF_UP));
			order.setValuePurchaseEUR(
					order.getValuePurchasePLN().divide(currency.getSalesPrice(), 2, RoundingMode.HALF_UP));
			order.setProfitEUR(order.getProfitPLN().divide(currency.getSalesPrice(), 2, RoundingMode.HALF_UP));
			order.setPriceSellEUR(order.getPriceSellPLN().divide(currency.getSalesPrice(), 2, RoundingMode.HALF_UP));
			order.setPricePurchaseEUR(
					order.getPricePurchasePLN().divide(currency.getSalesPrice(), 2, RoundingMode.HALF_UP));
		} else {
			order.setValueSellPLN(order.getValueSellEUR().multiply(currency.getSalesPrice()));
			order.setValuePurchasePLN(order.getValuePurchaseEUR().multiply(currency.getSalesPrice()));
			order.setProfitPLN(order.getProfitEUR().multiply(currency.getSalesPrice()));
			order.setPriceSellPLN(order.getPriceSellEUR().multiply(currency.getSalesPrice()));
			order.setPricePurchasePLN(order.getPricePurchaseEUR().multiply(currency.getSalesPrice()));

		}
		orderRepository.save(order);
		System.out.print("EDYT :" + order.toString());

		return order;
	}

	@Override
	public Order findById(Long id) {
		return orderRepository.findById(id).orElse(null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findAllByIdUser(Long id) {
		return em.createQuery("from Order where user_id = ?1").setParameter(1, id).getResultList();
	}

    @Override
    public String getNewNrOrder() {
	StringBuilder sbNumber = new StringBuilder();

	StringBuilder sqlSB = new StringBuilder();
	sqlSB.append("select o.number  AS NUMER ").append("FROM ").append(" Order o ").append("WHERE o.number <> '' ")
		.append("AND o.number LIKE  concat('%',year(current_date())) ORDER BY o.id desc ");

	List<String> number = em.createQuery(sqlSB.toString()).setMaxResults(1).getResultList();
	if (number.size() > 0 && !number.isEmpty()) {
	    String numberOrder = number.get(0);
	    String[] arrayName = numberOrder.split("/");
	    Integer num = Integer.parseInt(arrayName[0]) + 1;
	    arrayName[0] = num.toString();
	    StringUtils.join(arrayName, '/', sbNumber);

	} else {
	    String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
	    sbNumber.append("1/ZAM/").append(year);
	}

	return sbNumber.toString();

    }

	@Override
	public List<Order> getOrdersRaport(String start, String end, Long idUser) {

		StringBuilder hqlQuerry = new StringBuilder();
		hqlQuerry.append(
				"from Order where status = :status and createDate >= cast (:start as date) and createDate <= cast (:end as date) ");
		if (!idUser.equals(0L) && idUser != null) {
			hqlQuerry.append("and :idUser = user_id ");
		}
		Query query = em.createQuery(hqlQuerry.toString());
		query.setParameter("status", 1).setParameter("start", start).setParameter("end", end);
		if (!idUser.equals(0L) && idUser != null) {
			query.setParameter("idUser", idUser);

		}
		@SuppressWarnings("unchecked")
		List<Order> orders = query.getResultList();
		return orders;
	}

	@Override
	public void delete(Order order) {
		order.setStatus(2);
		orderRepository.save(order);

	}

	@Override
	public int getCountOrders() {
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH) + 1;
		int year = calendar.get(Calendar.YEAR);
		Long count = (Long) em.createQuery("select count(*) from Order where year(createDate) =:year and month(createDate) =:month").setParameter("year", year)
				.setParameter("month", month).getSingleResult();

		return count.intValue();

	}

	@Override
	public int getCountOrdersByIdUser(Long id) {
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH) + 1;
		int year = calendar.get(Calendar.YEAR);
		Long count = (Long) em.createQuery("select count(*) from Order where status =1 and user.id = :user_id and year(createDate) =:year and month(createDate) =:month")
				.setParameter("user_id", id).setParameter("year", year).setParameter("month", month).getSingleResult();
		return count.intValue();

	}

	@Override
	public String getProfitPerMonthByIdUser(Long id) {
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH) + 1;
		int year = calendar.get(Calendar.YEAR);

		BigDecimal profit = (BigDecimal) em.createQuery(
				"select coalesce(sum(profitPLN),0) from Order where status =1 and user.id = :user_id and year(createDate) =:year and month(createDate) =:month")
				.setParameter("user_id", id).setParameter("year", year).setParameter("month", month).getSingleResult();

		return profit.toString();

	}

	@Override
	public String getProfitPerMonth() {
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH) + 1;
		int year = calendar.get(Calendar.YEAR);

//		if (month.length() == 1) {
//			month = "0" + month;
//		}

		BigDecimal profit = (BigDecimal) em.createQuery(
				"select coalesce(sum(profitPLN),0) from Order where status=1 and year(createDate) =:year and month(createDate) =:month")
				.setParameter("year", year).setParameter("month", month).getSingleResult();
		return profit.toString();
	}

}