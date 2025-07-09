import React from 'react';

function AccountBalance({ balance, holdings }) {
  return (
    <div>
      <h3>Balance: ${balance.toFixed(2)}</h3>
      <h4>Holdings:</h4>
      <ul>
        {holdings.map((h) => (
          <li key={h.crypto_symbol}>{h.crypto_symbol}: {parseFloat(h.quantity).toFixed(6)}</li>
        ))}
      </ul>
    </div>
  );
}

export default AccountBalance;