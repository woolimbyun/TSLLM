import React from 'react';
import Header from './Header';
import TradingViewWidget from './TradingViewWidget'; 

function MainPage() {
  const mainPageStyle = {
    display: 'flex',
    flexDirection: 'column', 
    height: '100vh', 
  };

  const menuContainerStyle = {
    display: 'flex',
    flexDirection: 'row', 
  };

  const tradingViewWidgetStyle = {
    flex: '1', 
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    width: '80%',
    height: '700px'
  };

  return (
    <div style={mainPageStyle}>
      <Header />
      <div style={menuContainerStyle}>
        
        <div style={tradingViewWidgetStyle}>
          <TradingViewWidget /> {}
        </div>
      </div>
    </div>
  );
}

export default MainPage;
