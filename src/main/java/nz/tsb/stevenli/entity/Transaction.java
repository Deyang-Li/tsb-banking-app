package nz.tsb.stevenli.entity;

import java.time.OffsetDateTime;

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
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import nz.tsb.stevenli.data.TransactionStatus;

/** The entity bean of transaction table of database. */
@Entity
@Table(
    name = "transaction",
    indexes = {@Index(name = "IDX_ACCOUNT_ID", columnList = "t_account_id")})
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction-id-generator")
  @SequenceGenerator(name = "transaction-id-generator", sequenceName = "transactionid_sequence")
  @Column(name = "transaction_id")
  private long transactionId;

  @Column(name = "amount", nullable = false)
  private double amount;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private TransactionStatus transactionStatus;

  @Column(name = "account_from", nullable = false)
  private long accountFrom;

  @Column(name = "account_to", nullable = false)
  private long accountTo;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "created_date", nullable = false)
  @TimeZoneStorage(TimeZoneStorageType.NATIVE)
  private OffsetDateTime createdDate;

  @JsonIgnoreProperties("transaction")
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "t_account_id", referencedColumnName = "account_id")
  private Account account;

  public long getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(final long transactionId) {
    this.transactionId = transactionId;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(final double amount) {
    this.amount = amount;
  }

  public TransactionStatus getTransactionStatus() {
    return transactionStatus;
  }

  public void setTransactionStatus(final TransactionStatus transactionStatus) {
    this.transactionStatus = transactionStatus;
  }

  public long getAccountFrom() {
    return accountFrom;
  }

  public void setAccountFrom(final long accountFrom) {
    this.accountFrom = accountFrom;
  }

  public long getAccountTo() {
    return accountTo;
  }

  public void setAccountTo(final long accountTo) {
    this.accountTo = accountTo;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(final String description) {
    this.description = description;
  }

  public OffsetDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(final OffsetDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(final Account account) {
    this.account = account;
  }
}
