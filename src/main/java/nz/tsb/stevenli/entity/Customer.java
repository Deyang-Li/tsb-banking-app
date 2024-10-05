package nz.tsb.stevenli.entity;

import java.time.OffsetDateTime;
import java.util.List;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import nz.tsb.stevenli.entity.id.CustomerId;

/** The java structure of customer table of database. */
@Entity
@Table(name = "customer")
@IdClass(CustomerId.class)
public class Customer {
  @Id
  @Column(name = "customer_number")
  private String customerNumber;

  @Id
  @Column(name = "customer_unique_id")
  private String customerUniqueId;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "date_of_birth", nullable = false)
  private String dateOfBirth;

  @Column(name = "created_date", nullable = false)
  @TimeZoneStorage(TimeZoneStorageType.NATIVE)
  private OffsetDateTime createdDate;

  @JsonIgnoreProperties("customer")
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "customer")
  private List<Account> account;

  public String getCustomerNumber() {
    return customerNumber;
  }

  public void setCustomerNumber(final String customerNumber) {
    this.customerNumber = customerNumber;
  }

  public String getCustomerUniqueId() {
    return customerUniqueId;
  }

  public void setCustomerUniqueId(final String customerUniqueId) {
    this.customerUniqueId = customerUniqueId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  public String getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(final String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public OffsetDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(final OffsetDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public List<Account> getAccount() {
    return account;
  }

  public void setAccount(final List<Account> account) {
    this.account = account;
  }
}
