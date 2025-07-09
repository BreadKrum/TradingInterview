import React, { useState } from 'react';

function TradeForm({ prices, onTrade }) {
  const [symbol, setSymbol] = useState('BTC/USD');
  const [quantity, setQuantity] = useState('');
  const [mode, setMode] = useState('BUY');

  const handleSubmit = (e) => {
    e.preventDefault();
    const price = prices[symbol];
    onTrade({ symbol, quantity: parseFloat(quantity), price }, mode);
    setQuantity('');
  };

  return (
    <form onSubmit={handleSubmit}>
      <select value={symbol} onChange={(e) => setSymbol(e.target.value)}>
        {Object.keys(prices).map((s) => <option key={s} value={s}>{s}</option>)}
      </select>
      <input
        type="number"
        value={quantity}
        onChange={(e) => setQuantity(e.target.value)}
        placeholder="Quantity"
        required
        step="0.00000001"
        min="0"
      />
      <select value={mode} onChange={(e) => setMode(e.target.value)}>
        <option value="BUY">Buy</option>
        <option value="SELL">Sell</option>
      </select>
      <button type="submit">Execute</button>
    </form>
  );
}

export default TradeForm;