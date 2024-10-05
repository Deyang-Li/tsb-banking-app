package nz.tsb.stevenli.controller;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import nz.tsb.stevenli.data.AccountResponse;
import nz.tsb.stevenli.data.TransactionResponse;
import nz.tsb.stevenli.data.TransferRequest;
import nz.tsb.stevenli.entity.Account;
import nz.tsb.stevenli.entity.Transaction;
import nz.tsb.stevenli.exception.BankingException;
import nz.tsb.stevenli.service.BankingService;

/** Banking application controller to expose endpoints. */
@CrossOrigin("*")
@RestController
@RequestMapping("/tsb/banking")
public class BankingController {

  private static final Logger LOGGER = LogManager.getLogger(BankingController.class);

  @Autowired private BankingService bankingService;

  /**
   * Returns all the accounts for a customer.
   *
   * @param customerNumber the given customer number.
   * @param customerUniqueId the given customer unique Id.
   * @return the list of account data.
   */
  @GetMapping("/accounts")
  public ResponseEntity<AccountResponse> getAccounts(
      @RequestParam final String customerNumber, @RequestParam final String customerUniqueId) {
    final List<Account> accounts = bankingService.getAccount(customerNumber, customerUniqueId);
    LOGGER.info(
        "Successfully fetched accounts for customerNumber={}, customerUniqueId={}",
        customerNumber,
        customerUniqueId);
    return new ResponseEntity<>(new AccountResponse(accounts), HttpStatus.OK);
  }

  /**
   * Returns all the transactions for an account.
   *
   * @param accountId the given account id.
   * @return the list of transaction data.
   */
  @GetMapping("/transactions")
  public ResponseEntity<TransactionResponse> getTransactions(@RequestParam final long accountId) {
    final List<Transaction> transactions =
        bankingService.getTransaction(accountId);
    LOGGER.info("Successfully fetched transactions for accountId={}", accountId);
    return new ResponseEntity<>(new TransactionResponse(transactions), HttpStatus.OK);
  }

  /**
   * Make a transfer between two accounts that belong to a customer.
   *
   * @param transferRequest the transfer request body.
   * @return the transfer result status.
   * @throws BankingException If the transfer failed.
   */
  @PostMapping("/transfer")
  public ResponseEntity<HttpStatus> transfer(@RequestBody final TransferRequest transferRequest) {
    bankingService.transfer(
        transferRequest.accountFromId(), transferRequest.accountToId(), transferRequest.amount());
    return ResponseEntity.ok(HttpStatus.OK);
  }
}
