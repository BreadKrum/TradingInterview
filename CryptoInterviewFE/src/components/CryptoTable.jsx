import React from 'react';

function CryptoTable({ prices }) {
  return (
    <table>
      <thead>
        <tr>
          <th>Symbol</th>
          <th>Price (USD)</th>
        </tr>
      </thead>
      <tbody>
        {Object.entries(prices).map(([symbol, price]) => (
          <tr key={symbol}>
            <td>{symbol}</td>
            <td>${price.toFixed(2)}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}

export default CryptoTable;