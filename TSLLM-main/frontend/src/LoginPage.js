import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const API_URL = `${process.env.REACT_APP_API_BASE_URL}/user/login`;
axios.defaults.withCredentials = true;

function LogInForm() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.post(API_URL, {
        userId: email,
        password: password,
      });
      if (response.data) {
        // 서버 응답에서 헤더 정보를 가져와서 저장합니다.
        const { access } = response.headers;        
        // 서버 응답에서 받은 데이터와 헤더 정보를 함께 저장합니다.
        const userData = {
          ...response.data,
          access
        };


        localStorage.setItem('user', JSON.stringify(userData));

        navigate('/after_login');
      }
    } catch (error) {
      console.error("로그인 실패:", error);
    }
  };

  return (
    <>
      <main className="formContainer">
        <header className="header">
          <h1 className="appName">Trading Assistor</h1>
        </header>
        <form className="signInForm" onSubmit={handleSubmit}>
        {" "}
        {/* onSubmit 이벤트 추가 */}
          <label htmlFor="emailInput" className="visually-hidden">
            Email Address
          </label>
          <input
            type="email"
            id="emailInput"
            className="input emailInput"
            placeholder="이메일 주소를 입력해주세요"
            aria-label="Email Address"
            value={email}
            onChange={handleEmailChange}
          />
          <label htmlFor="passwordInput" className="visually-hidden">
            Password
          </label>
          <input  
            type="password"
            id="passwordInput"
            className="input passwordInput"
            placeholder="비밀번호를 입력해주세요"
            aria-label="Password"
            value={password}
            onChange={handlePasswordChange}
          />
          <button type="submit" className="submitButton" style={{ textDecoration: 'none' }}>
            로그인
          </button>
        </form>
      </main>
      <style jsx>{`
        .formContainer {
          background-color: #fff;
          display: flex;
          flex-direction: column;
          align-items: center;
          color: #0f2d52;
          font-weight: 700;
          padding: 50px;
        }
        @media (max-width: 991px) {
          .formContainer {
            padding: 20px;
          }
        }
        .header {
          margin-top: 214px;
          text-align: center;
        }
        @media (max-width: 991px) {
          .header {
            margin-top: 40px;
          }
        }
        .appName {
          color: #2c97eb;
          font-size: 30px;
          font-family: SF Pro Display, -apple-system, Roboto, Helvetica, sans-serif;
        }
        .signInForm {
          display: flex;
          flex-direction: column;
          align-items: center;
        }
        .input {
          width: 342px;
          max-width: 100%;
          margin-top: 20px;
          border-radius: 10px;
          border: 1px solid rgba(38, 115, 209, 0.4);
          padding: 20px;
          font-size: 15px;
          font-family: Raleway, sans-serif;
        }
        .submitButton {
          display: flex;
          justify-content: center;
          align-items: center;
          border-radius: 10px;
          background-color: #2673d1;
          margin-top: 40px;
          width: 342px;
          color: #fff;
          text-align: center;
          padding: 20px;
          font-size: 17px;
          font-family: Raleway, sans-serif;
          cursor: pointer;
        }
        .visually-hidden {
          position: absolute;
          left: -10000px;
          top: auto;
          width: 1px;
          height: 1px;
          overflow: hidden;
        }
      `}</style>
    </>
  );
}

export default LogInForm;
