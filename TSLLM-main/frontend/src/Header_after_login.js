import React from 'react';
import { UserOutlined } from "@ant-design/icons";
import { Link, useNavigate } from 'react-router-dom';

function Header_after_login() {
  const navigate = useNavigate();

  const logout = () => {
    localStorage.removeItem('user');
    const user = JSON.parse(localStorage.getItem("user"));
    console.log("로그아웃 되었습니다. 둘다 undefined 나와야함", user?.id, user?.Authorization);
    navigate('/')};

  return (
    <header>
      <nav className="navbar">
      <div className="logo">
          <Link to="/after_login" style={{ color: '#2c97eb', textDecoration: 'none' }}>Trading Assistor</Link>
        </div>
        <div className="menu-auth">
          <div className="menu-items">
            <Link to ="/stratege-creation" className="menu-link">전략생성</Link>
            <Link to ="/trading" className="menu-link">트레이딩</Link>
          </div>
          <div className="auth-buttons">
            <UserOutlined />
              <button className="signup-button" onClick={logout} >로그아웃</button>
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
        .signup-button {
          font-size: 16px;
          font-weight: 600;
          padding: 8px 16px;
          border-radius: 4px;
          border: none;
          cursor: pointer;
          background-color: #2c97eb;
          color: #fff;
        }
        @media (max-width: 991px) {
          .signup-button {
            flex-grow: 1;
            justify-content: center;
          }
        }
      `}</style>
    </header>
  );
}

export default Header_after_login;