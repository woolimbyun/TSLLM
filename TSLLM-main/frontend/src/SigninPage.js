import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";


function SignInForm() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
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
      const API_URL_SIGNUP = `${process.env.REACT_APP_API_BASE_URL}/user/signup`; // $ 기호 제거
      const API_URL_LOGIN = `${process.env.REACT_APP_API_BASE_URL}/user/login`;

      // 회원가입 요청
      const signupResponse = await axios.post(API_URL_SIGNUP, {
      userId: email,
      password: password,
      });

      // 로그인 요청
      const loginResponse = await axios.post(API_URL_LOGIN, {
      userId: email,
      password: password,
      });
      if (loginResponse.data) {
        // 서버 응답에서 헤더 정보를 가져옵니다.
        const { access } = loginResponse.headers;
  
        // 사용자 정보를 객체에 저장합니다.
        const userData = {
          ...loginResponse.data,
          access
        };
  
        // 로컬 스토리지에 사용자 정보를 저장합니다.
        localStorage.setItem('user', JSON.stringify(userData));
        
        // 사용자 정보를 콘솔에 출력합니다.
        console.log(userData);
  
        // 로그인 이후 페이지로 이동합니다.
        navigate('/after_login');
      }

    } catch (error) {
      console.error("회원가입 실패:", error);
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
            이메일 주소
          </label>
          <input
            type="email"
            id="emailInput"
            className="input emailInput"
            placeholder="이메일 주소를 입력해주세요"
            aria-label="이메일 주소"
            value={email} // value 프로퍼티 추가
            onChange={handleEmailChange} // onChange 프로퍼티 추가
          />
          <label htmlFor="passwordInput" className="visually-hidden">
            비밀번호
          </label>
          <input
            type="password"
            id="passwordInput"
            className="input passwordInput"
            placeholder="비밀번호를 입력해주세요"
            aria-label="비밀번호"
            value={password} // value 프로퍼티 추가
            onChange={handlePasswordChange} // onChange 프로퍼티 추가
          />
          <button type="submit" className="submitButton">
            회원가입
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
          font-family: SF Pro Display, -apple-system, Roboto, Helvetica,
            sans-serif;
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

export default SignInForm;
