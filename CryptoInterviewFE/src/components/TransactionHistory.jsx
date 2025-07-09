import React from 'react';

function TransactionHistory({ transactions }) {
  return (
    <div>
      <h4>Transaction History</h4>
      <ul>
        {transactions.map((tx) => (
          <li key={tx.id}>
            {tx.type} {tx.quantity} {tx.crypto_symbol} at ${tx.price} = ${tx.total} on {new Date(tx.timestamp).toLocaleString()}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default TransactionHistory;