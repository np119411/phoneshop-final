package com.phoneshop.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.phoneshop.entities.ProductEntity;
import com.phoneshop.enums.ProductType;
import com.phoneshop.phones.PhoneDTO;
import com.phoneshop.utils.HibernateUtility;

public class ProductDAO {

	private final static SessionFactory factory = HibernateUtility.getSessionFactory();

	public List<ProductEntity> getListProduct() {
		Transaction transaction = null;
		List<ProductEntity> products = null;
		Session session = factory.openSession();
		try {
			transaction = session.beginTransaction();
			// Error Could not resolve root entity, Cannot resolve symbol 'Product Entity'
			// --> change version or use "jakarta.persistence" for version hibernate > 6.
			// https://stackoverflow.com/questions/43716068/invalid-syntax-error-type-myisam-in-ddl-generated-by-hibernate
			Query<ProductEntity> query = session.createQuery("FROM ProductEntity", ProductEntity.class);
			products = query.list();
			return products;
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

	public List<ProductEntity> getListProductByType(ProductType type) {
		Transaction transaction = null;
		List<ProductEntity> products = null;
		Session session = factory.openSession();
		try {
			transaction = session.beginTransaction();
			// Error Could not resolve root entity, Cannot resolve symbol 'Product Entity'
			// --> change version or use "jakarta.persistence" for version hibernate > 6.
			// https://stackoverflow.com/questions/43716068/invalid-syntax-error-type-myisam-in-ddl-generated-by-hibernate
			Query<ProductEntity> query = session.createQuery(
					"SELECT phone FROM ProductEntity phone WHERE phone.type = :product_type", ProductEntity.class);
			query.setParameter("product_type", type);
			products = query.list();
			return products;
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

	public List<ProductEntity> getListProductByTypeWithPhoneId(ProductType type, long phoneId) {
		Transaction transaction = null;
		List<ProductEntity> products = null;
		Session session = factory.openSession();
		try {
			transaction = session.beginTransaction();
			// Error Could not resolve root entity, Cannot resolve symbol 'Product Entity'
			// --> change version or use "jakarta.persistence" for version hibernate > 6.
			// https://stackoverflow.com/questions/43716068/invalid-syntax-error-type-myisam-in-ddl-generated-by-hibernate
			Query<ProductEntity> query = session.createQuery(
					"SELECT product FROM ProductEntity product WHERE product.type LIKE :product_type AND product.phone.id = :phoneid",
					ProductEntity.class);
			query.setParameter("product_type", type);
			query.setParameter("phoneid", phoneId);
			products = query.list();
			return products;
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

	public void updateProduct(ProductEntity product) {
		Transaction transaction = null;
		Session session = factory.openSession();
		try {
			transaction = session.beginTransaction();
			// Error Could not resolve root entity, Cannot resolve symbol 'Product Entity'
			// --> change version or use "jakarta.persistence" for version hibernate > 6.
			// https://stackoverflow.com/questions/43716068/invalid-syntax-error-type-myisam-in-ddl-generated-by-hibernate
			// Error: https://www.baeldung.com/hibernate-detached-entity-passed-to-persist
			session.merge(product);
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

	public static void insertProduct(ProductEntity product) {
		Transaction transaction = null;
		Session session = factory.openSession();
		try {
			transaction = session.beginTransaction();
			// Error Could not resolve root entity, Cannot resolve symbol 'Product Entity'
			// --> change version or use "jakarta.persistence" for version hibernate > 6.
			// https://stackoverflow.com/questions/43716068/invalid-syntax-error-type-myisam-in-ddl-generated-by-hibernate
			session.persist(product);
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

	public List<ProductEntity> searchProduct(String text) {
		Transaction transaction = null;
		List<ProductEntity> products = null;
		Session session = factory.openSession();
		try {
			transaction = session.beginTransaction();
			// Error Could not resolve root entity, Cannot resolve symbol 'Product Entity'
			// --> change version or use "jakarta.persistence" for version hibernate > 6.
			// https://stackoverflow.com/questions/43716068/invalid-syntax-error-type-myisam-in-ddl-generated-by-hibernate
			Query<ProductEntity> query = session.createQuery(
					"FROM ProductEntity p WHERE p.name LIKE :txt OR p.color LIKE :txt OR p.description LIKE :txt",
					ProductEntity.class);
			query.setParameter("txt", "%" + text + "%");
			products = query.list();
			return products;
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

	public ProductEntity getProductById(long productId) {
		Transaction transaction = null;
		ProductEntity product;
		Session session = factory.openSession();
		try {
			transaction = session.beginTransaction();
			// Error Could not resolve root entity, Cannot resolve symbol 'Product Entity'
			// --> change version or use "jakarta.persistence" for version hibernate > 6.
			// https://stackoverflow.com/questions/43716068/invalid-syntax-error-type-myisam-in-ddl-generated-by-hibernate
			/*
			 * Query<ProductEntity> query =
			 * session.createQuery("FROM ProductEntity p WHERE p.id = ?",
			 * ProductEntity.class); query.setParameter(1, productId); product =
			 * query.getSingleResult();
			 */
			product = session.get(ProductEntity.class, productId);
			// fix error:
			product.getId();
			return product;
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

	public void deleteProduct(long productId) {
		Transaction transaction = null;
		ProductEntity product = new ProductEntity();
		product.setId(productId);
		Session session = factory.openSession();
		try {
			transaction = session.beginTransaction();
			// Error Could not resolve root entity, Cannot resolve symbol 'Product Entity'
			// --> change version or use "jakarta.persistence" for version hibernate > 6.
			// https://stackoverflow.com/questions/43716068/invalid-syntax-error-type-myisam-in-ddl-generated-by-hibernate
			session.remove(product);
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

	public List<ProductEntity> bestPhoncaseSeller(long phoneId) {
		Transaction transaction = null;
		List<ProductEntity> productList = null;
		Session session = factory.openSession();
		try {
			transaction = session.beginTransaction();
			Query<ProductEntity> query = session.createQuery(
					"SELECT p FROM ProductEntity p WHERE p.phone.id = :phoneId AND p.id IN (SELECT product.id AS pid FROM ProductEntity product, DetailOrderEntity detail WHERE product.type = :product_type AND product.id = detail.productEntity.id GROUP BY product.id ORDER BY SUM(detail.quantity) DESC)",
					ProductEntity.class);
			query.setParameter("product_type", ProductType.PHONECASE);
			query.setParameter("phoneId", phoneId);	
			query.setMaxResults(1);
			productList = query.list();
			return productList;
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

	public List<ProductEntity> bestPhoncaseSeller1() {
		Transaction transaction = null;
		long productList = 0;
		Session session = factory.openSession();
		try {
			transaction = session.beginTransaction();
			/*
			 * Query<Long> query = session.createQuery(
			 * "SELECT MAX (detail.quantity) AS amount FROM ProductEntity product, DetailOrderEntity detail WHERE product.type = :product_type AND product.id = detail.productEntity.id GROUP BY product.id ORDER BY amount DESC"
			 * );
			 */
			String hql = "SELECT p FROM ProductEntity p WHERE p.id IN (SELECT product.id AS pid FROM ProductEntity product, DetailOrderEntity detail WHERE product.type = :product_type AND product.id = detail.productEntity.id GROUP BY product.id ORDER BY SUM(detail.quantity) DESC)";
			Query query = session.createQuery(hql);
			query.setParameter("product_type", ProductType.PHONECASE);
			List<ProductEntity> result = query.getResultList();
			System.out.println("Rows affected: " + result);
			return result;
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


	public static void main(String[] args) {

		/*
		 * ProductDAO dao = new ProductDAO(); List<PhoneDTO> productList =
		 * dao.getListProductByType(ProductType.PHONE).stream().map(product -> new
		 * PhoneDTO(product)) .collect(Collectors.toList()); for (PhoneDTO phone :
		 * productList) { System.out.println(phone.getName()); }
		 */

		/*
		 * ProductDAO productDAO = new ProductDAO(); ProductEntity phone1 = new
		 * ProductEntity("Realme 8 Pro", 6990000, true); ProductEntity phoncase1 = new
		 * ProductEntity("Red for Realme 8 Pro", 0, true); phoncase1.setPhone(phone1);
		 * insertProduct(phoncase1);
		 */
//		ProductDAO dao = new ProductDAO();
//
//		List<ProductEntity> list = dao.bestPhoncaseSeller();
//		for (ProductEntity pro : list) {
//			System.out.println(pro.getName());
//		}

		// dao.bestPhoncaseSeller1();


		/*
		 * List<ProductEntity> products = productDAO.getListProduct(); for
		 * (ProductEntity product : products) { System.out.println(product); }
		 */
	}
}
