package nz.tsb.stevenli.data;

import java.util.List;
import nz.tsb.stevenli.entity.Transaction;

/**
 * A record to store get transactions API's response.
 *
 * @param transactions the list of transactions.
 */
public record TransactionResponse(List<Transaction> transactions) {
}
