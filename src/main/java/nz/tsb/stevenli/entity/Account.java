package nz.tsb.stevenli.entity;

import java.time.OffsetDateTime;
import java.util.List;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import nz.tsb.stevenli.data.Status;

/** The entity bean of account table of database. */
@Entity
@Table(
    name = "account",
    indexes = {
      @Index(name = "IDX_CUSTOMER_NUMBER", columnList = "a_customer_number"),
      @Index(name = "IDX_CUSTOMER_UNIQUE_ID", columnList = "a_customer_unique_id")
    })
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account-id-generator")
  @SequenceGenerator(name = "account-id-generator", sequenceName = "accountid_sequence")
  @Column(name = "account_id")
  private long accountId;

  @Column(name = "account_number", nullable = false)
  private String accountNumber;

  @Column(name = "account_name", nullable = false)
  private String accountName;

  @Column(name = "amount", nullable = false)
  private double amount;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Status status;

  @Column(name = "account_description", nullable = false)
  private String accountDescription;

  @Column(name = "created_date", nullable = false)
  @TimeZoneStorage(TimeZoneStorageType.NATIVE)
  private OffsetDateTime createdDate;

  @JsonIgnoreProperties("account")
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "a_customer_number", referencedColumnName = "customer_number")
  @JoinColumn(name = "a_customer_unique_id", referencedColumnName = "customer_unique_id")
  private Customer customer;

  @JsonIgnoreProperties("account")
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "account")
  private List<Transaction> transaction;

  public long getAccountId() {
    return accountId;
  }

  public void setAccountId(final long accountId) {
    this.accountId = accountId;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(final String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(final String accountName) {
    this.accountName = accountName;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(final double amount) {
    this.amount = amount;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(final Status status) {
    this.status = status;
  }

  public String getAccountDescription() {
    return accountDescription;
  }

  public void setAccountDescription(final String accountDescription) {
    this.accountDescription = accountDescription;
  }

  public OffsetDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(final OffsetDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(final Customer customer) {
    this.customer = customer;
  }

  public List<Transaction> getTransaction() {
    return transaction;
  }

  public void setTransaction(final List<Transaction> transaction) {
    this.transaction = transaction;
  }
}
