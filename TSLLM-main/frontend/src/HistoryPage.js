import React from 'react';
import Header from './Header_after_login';
import TradingMenu from './TradingMenu';
import TransactionHistory from './TransactionHistory';
import HistoryCookie from './HistoryTable';

function HistoryPage() {
  const tradingPageStyle = {
    display: 'flex',
    flexDirection: 'column',
    height: '100vh',
  };

  const menuContainerStyle = {
    display: 'flex',
    flexDirection: 'row',
    width: '100%', 
  };

  const tradingMenuStyle = {
    flex: '200px',
  };
  const transactionHistoryStyle = {
    flex: '1',
    minWidth: '0',
  };
  return (
    <div style={tradingPageStyle}>
      <Header />
      <div style={menuContainerStyle}>
        <div style={tradingMenuStyle}>
          <TradingMenu />
        </div>
        <div style={transactionHistoryStyle}>
          <TransactionHistory />
        </div>
        <TransactionHistory />
      </div>
    </div>
  );
}

export default HistoryPage;
