import React from 'react';
import Header from './Header_after_login';
import TradingMenu from './TradingMenu';
import OrderTable from './OrderTable';

function TradingPage() {
  const tradingPageStyle = {
    display: 'flex',
    flexDirection: 'column', 
    height: '100vh', 
  };

  const menuContainerStyle = {
    display: 'flex',
    flexDirection: 'row', 
  };

  const tradingMenuStyle = {
    width: '200px', 
  };

  return (
    <div style={tradingPageStyle}>
      <Header />
      <div style={menuContainerStyle}>
        <div style={tradingMenuStyle}>
          <TradingMenu />
        </div>
        <OrderTable />
      </div>
    </div>
  );
}

export default TradingPage;
