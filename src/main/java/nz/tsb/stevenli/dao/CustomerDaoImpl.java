package nz.tsb.stevenli.dao;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import nz.tsb.stevenli.entity.Customer;
import nz.tsb.stevenli.entity.id.CustomerId;

/** The implementation class for operating customer table data from database. */
@Component
public class CustomerDaoImpl implements CustomerDao {

  @PersistenceContext private EntityManager entityManager;

  @Transactional(readOnly = true)
  @Override
  public Optional<Customer> getCustomer(
      final String customerNumber, final String customerUniqueId) {
    return Optional.ofNullable(
        entityManager.find(Customer.class, new CustomerId(customerNumber, customerUniqueId)));
  }

  @Transactional(readOnly = false)
  @Override
  public void save(final Customer customer) {
    entityManager.persist(customer);
  }
}
