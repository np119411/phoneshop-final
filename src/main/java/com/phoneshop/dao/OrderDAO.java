package com.phoneshop.dao;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.phoneshop.entities.OrderEntity;
import com.phoneshop.shopping.Cart;
import com.phoneshop.utils.HibernateUtility;

public class OrderDAO {
	private final static SessionFactory factory = HibernateUtility.getSessionFactory();

	public List<OrderEntity> orderList() throws SQLException {

		Transaction transaction = null;
		Session session = factory.openSession();
		List<OrderEntity> order = null;

		try {
			transaction = session.beginTransaction();

			Query<OrderEntity> query = session.createQuery("FROM Order", OrderEntity.class);
			order = query.list();

			return order;

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();

		} finally {
			session.close();
		}

		return null;
	}

	public OrderEntity getOrderById(long orderId) {

		Transaction transaction = null;
		OrderEntity order;
		Session session = factory.openSession();

		try {
			transaction = session.beginTransaction();

			order = session.get(OrderEntity.class, orderId);
			order.getId();
			return order;

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

		finally {
			session.close();
		}

		return null;
	}

	public static void insertOrder(OrderEntity order) {
		Transaction transaction = null;
		Session session = factory.openSession();

		try {
			transaction = session.beginTransaction();
			session.persist(order);
			transaction.commit();
			return;

		} catch (Exception e) {

			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();

		} finally {
			session.close();
		}
		return;
	}

	public void updateOrder(OrderEntity order) {
		Transaction transaction = null;
		Session session = factory.openSession();

		try {
			transaction = session.beginTransaction();
			session.merge(order);
			transaction.commit();
			return;

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();

		} finally {
			session.close();
		}

		return;
	}


	public static void main(String[] args) {
		OrderEntity order = new OrderEntity("Ngan Phan", "An Giang", "0964307703");
		insertOrder(order);

	}
}
