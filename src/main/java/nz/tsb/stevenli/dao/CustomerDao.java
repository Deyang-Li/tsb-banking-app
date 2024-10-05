package nz.tsb.stevenli.dao;

import java.util.Optional;

import nz.tsb.stevenli.entity.Customer;

/** The interface of operating customer table data. */
public interface CustomerDao {
  /**
   * Returns customer data for a given customer number and given customer unique id.
   *
   * @param customerNumber the given customer number.
   * @param customerUniqueId the given customer unique Id.
   * @return the customer data. Empty if no such customer exists.
   */
  Optional<Customer> getCustomer(String customerNumber, String customerUniqueId);

  /**
   * Persist a customer.
   *
   * @param customer the customer entity to be persisted.
   */
  void save(Customer customer);
}
