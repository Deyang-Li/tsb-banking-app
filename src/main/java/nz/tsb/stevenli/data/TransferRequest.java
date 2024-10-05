package nz.tsb.stevenli.data;

/**
 * A record to store transfer API request body.
 *
 * @param accountFromId the account id where the transfer is from.
 * @param accountToId the account id where the transfer is to.
 * @param amount the transfer amount.
 */
public record TransferRequest(long accountFromId, long accountToId, double amount) {
}
