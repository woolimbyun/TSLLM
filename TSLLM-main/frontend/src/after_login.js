import React, { useEffect } from 'react';
import Header_after_login from './Header_after_login';
import TradingViewWidget from './TradingViewWidget'; 

function MainPage2() {
    useEffect(() => {
        const user = JSON.parse(localStorage.getItem("user"));
        const access = user?.access;
        const userId = user?.id;

        // 토큰과 ID를 이용한 원하는 작업 수행
        console.log("access:", access);
        console.log("User ID:", userId);
    }, []);

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
      <Header_after_login />
      <div style={menuContainerStyle}>
        <div style={tradingViewWidgetStyle}>
          <TradingViewWidget /> {}
        </div>
      </div>
    </div>
  );
}

export default MainPage2;
