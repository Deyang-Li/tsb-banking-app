package nz.tsb.stevenli.entity.id;

import java.io.Serializable;
import java.util.Objects;

/**
 * This is a separate composite primary key class with primary key columns for Customer table. It is
 * used by {@link Customer}.
 */
public class CustomerId implements Serializable {
  private static final long serialVersionUID = -6403440945820625804L;

  private String customerNumber;

  private String customerUniqueId;

  /** no-argument constructor. */
  private CustomerId() { }

  /**
   * Constructor to build the class with parameters.
   *
   * @param customerNumber the given customer number.
   * @param customerUniqueId the given customer unique Id.
   */
  public CustomerId(final String customerNumber, final String customerUniqueId) {
    this.customerNumber = customerNumber;
    this.customerUniqueId = customerUniqueId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(customerNumber, customerUniqueId);
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final CustomerId other = (CustomerId) obj;
    return Objects.equals(customerNumber, other.customerNumber) &&
        Objects.equals(customerUniqueId, other.customerUniqueId);
  }
}
