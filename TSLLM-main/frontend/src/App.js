import React from 'react';
import Chat from './chat';
import MainPage from './before_login';
import MainPage2 from './after_login';
import LogInForm from './LoginPage';
import SignInForm from './SigninPage';
import TradingMenu from './TradingMenu';
import TradingPage from './TradingPage';
import OrderPage from './OrderPage';
import HistoryPage from './HistoryPage';
import SettingsPage from './SettingsPage';
import { BrowserRouter, Route, Routes } from 'react-router-dom';

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Routes>
          <Route path="/" element={<MainPage/>} />
          <Route path="/after_login" element={<MainPage2/>} />
          <Route path="/login" element={<LogInForm/>}/>
          <Route path="/stratege-creation" element={<Chat/>}/>
          <Route path="/trading-menu" element={<TradingMenu/>}/>
          <Route path="/trading" element={<TradingPage/>}/>
          <Route path="/orders" element={<OrderPage/>}/>
          <Route path="/history" element={<HistoryPage/>}/>
          <Route path="/signin" element={<SignInForm/>}/>
          <Route path="/setting" element={<SettingsPage/>}/>
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
