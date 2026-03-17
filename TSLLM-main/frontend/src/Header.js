import React from 'react';
import { Link } from 'react-router-dom';


function Header() {

  const showAlert = (e) => {
    e.preventDefault();
    alert("로그인을 해주세요");
  };

  return (
    <header>
      <nav className="navbar">
        <div className="logo">Trading Assistor</div>
        <div className="menu-auth">
          <div className="menu-items">
            <a href="/stratege-creation" className="menu-link" onClick={showAlert} >전략생성</a>
            <a href="/trading" className="menu-link" onClick={showAlert} >트레이딩</a>
          </div>
          <div className="auth-buttons">
            <Link to="/login">
              <button className="login-button">로그인</button>
            </Link>
            <Link to="/signin">
              <button className="signup-button">회원가입</button>
            </Link>
          </div>
        </div>
      </nav>
      <style jsx>{`
        .navbar {
          justify-content: space-between;
          align-items: center;
          background-color: #fff;
          display: flex;
          gap: 20px;
          padding: 15px 64px;
        }
        @media (max-width: 991px) {
          .navbar {
            flex-wrap: wrap;
            padding: 15px 20px;
          }
        }
        .logo {
          color: #2c97eb;
          font-weight: 700;
          font-size: 30px;
          font-family: SF Pro Display, -apple-system, Roboto, Helvetica, sans-serif;
        }
        @media (max-width: 991px) {
          .logo {
            font-size: 24px;
            margin-bottom: 10px;
          }
        }
        .menu-auth {
          display: flex;
          gap: 20px;
        }
        .menu-items {
          display: flex;
          gap: 17px;
          font-size: 15px;
          color: #000;
          font-weight: 400;
        }
        .menu-link {
          font-family: SF Pro Display, -apple-system, Roboto, Helvetica, sans-serif;
          margin-top: 11px;
          text-decoration: none;
          color: #000;
        }
        .auth-buttons {
          display: flex;
          gap: 20px;
        }
        .login-button,
        .signup-button {
          font-size: 16px;
          font-weight: 600;
          padding: 8px 16px;
          border-radius: 4px;
          border: none;
          cursor: pointer;
        }
        .login-button {
          background-color: #fff;
          color: #2c97eb;
          border: 1px solid #2c97eb;
        }
        .signup-button {
          background-color: #2c97eb;
          color: #fff;
        }
        @media (max-width: 991px) {
          .login-button,
          .signup-button {
            flex-grow: 1;
            justify-content: center;
          }
        }
      `}</style>
    </header>
  );
}

export default Header;
