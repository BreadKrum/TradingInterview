import React, { useEffect, useState } from 'react';
import {
  fetchPrices,
  fetchAccount,
  buyCrypto,
  sellCrypto,
  fetchTransactions,
  resetAccount
} from './api';
import CryptoTable from './components/CryptoTable';
import AccountBalance from './components/AccountBalance';
import TradeForm from './components/TradeForm';
import TransactionHistory from './components/TransactionHistory';
import ResetButton from './components/ResetButton';

function App() {
  const [prices, setPrices] = useState({});
  const [account, setAccount] = useState({ balance: 0, holdings: [] });
  const [transactions, setTransactions] = useState([]);

  const refreshAll = async () => {
    const [p, a, t] = await Promise.all([
      fetchPrices(), fetchAccount(), fetchTransactions()
    ]);
    setPrices(p.data);
    setAccount(a.data);
    setTransactions(t.data);
  };

  useEffect(() => {
    refreshAll();
    const interval = setInterval(() => fetchPrices().then(res => setPrices(res.data)), 5000);
    return () => clearInterval(interval);
  }, []);

  const handleTrade = async (data, mode) => {
    try {
      if (mode === 'BUY') await buyCrypto(data);
      else await sellCrypto(data);
      await refreshAll();
    } catch (e) {
      alert("Trade failed: " + e.response?.data || e.message);
    }
  };

  const handleReset = async () => {
    await resetAccount();
    await refreshAll();
  };

  return (
    <div>
      <h1>Crypto Trading Simulator</h1>
      <CryptoTable prices={prices} />
      <AccountBalance balance={account.balance} holdings={account.holdings || []} />
      <TradeForm prices={prices} onTrade={handleTrade} />
      <TransactionHistory transactions={transactions} />
      <ResetButton onReset={handleReset} />
    </div>
  );
}

export default App;