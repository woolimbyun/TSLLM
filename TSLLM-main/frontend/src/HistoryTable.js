import React, { useState, useEffect } from 'react';
import './HistoryCookie.css';

function HistoryCookie() {
  const [transactions, setTransactions] = useState([]);

  useEffect(() => {
    //api 설정
    fetch('/api/transactions')
      .then(response => response.json())
      .then(data => setTransactions(data))
      .catch(error => console.error('Error fetching transactions:', error));
  }, []);

  return (
    <div className="order-table-container">
      <div className="order-table-header">
          <span>Date</span>
          <span>ID</span>
          <span >Amount</span>
          <span>Asset</span>
          <span>TXID</span>
          <span>Status</span>
        </div>
        <div className="order-table-body">
        {transactions.map((transaction, index) => (
          <div key={index} className="order-table-row">
            <span >{transaction.created_at}</span>
            <span >{transaction.id}</span>
            <span>{transaction.volume}</span>
            <span>{transaction.balance}</span>
            <span>{transaction.txid}</span>
            <span>{transaction.status}</span>
          </div>
          
        ))}
      </div>
    </div>
  );
}

export default HistoryCookie;
