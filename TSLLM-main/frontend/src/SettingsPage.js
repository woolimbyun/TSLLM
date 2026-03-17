import React, { useState } from "react";
import Header from "./Header_after_login";
import TradingMenu from "./TradingMenu";
import axios from "axios"; // axios import 추가

const API_URL = process.env.REACT_APP_API_BASE_URL;

function SettingsPage() {
  const [accessKey, setAccessKey] = useState("");
  const [secretKey, setSecretKey] = useState("");
  const [showSuccessModal, setShowSuccessModal] = useState(false); // 성공 모달 상태 추가

  const tradingPageStyle = {
    display: "flex",
    flexDirection: "column",
    height: "100vh",
  };

  const menuContainerStyle = {
    display: "flex",
    flexDirection: "row",
    width: "100%",
    alignItems: "center",
    justifyContent: "center",
    padding: "20px",
  };

  const tradingMenuStyle = {
    width: "200px",
  };

  const apiKeyContainerStyle = {
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
    justifyContent: "center",
    width: "100%",
  };

  const inputGroupStyle = {
    display: "flex",
    flexDirection: "row",
    alignItems: "center",
    width: "60%",
    margin: "10px 0",
  };

  const inputStyle = {
    padding: "15px 20px",
    flexGrow: "1",
    fontSize: "1rem",
    backgroundColor: "white",
    border: "1px solid #E5E7EB",
    borderRadius: "4px",
    marginRight: "10px",
  };

  const buttonStyle = {
    padding: "15px 20px",
    minWidth: "100px",
    fontSize: "1rem",
    backgroundColor: "#2c97eb",
    color: "white",
    border: "none",
    borderRadius: "4px",
    cursor: "pointer",
  };

  const handleSubmit = () => {
    console.log("Submitting Access Key:", accessKey);
    console.log("Submitting Secret Key:", secretKey);

    const user = JSON.parse(localStorage.getItem("user"));
    const accessToken = user?.accessToken;
    const userId = user?.id;
    console.log(userId)
    console.log(accessToken)
    // PUT API 호출
    axios
      .put(`${API_URL}/user/update-apikey`, {
        id: userId,
        accessKey: accessKey,
        secretKey: secretKey,
      },{
        headers:{
          Authorization: accessToken
        }
      })
      .then((response) => {
        console.log("PUT API 호출 성공:", response);
        window.alert("Success!"); // 브라우저 팝업 창 표시
      })
      .catch((error) => {
        console.error("PUT API 호출 오류:", error);
      });
};

  return (
    <div style={tradingPageStyle}>
      <Header />
      <TradingMenu style={tradingMenuStyle} />
      <div style={menuContainerStyle}>
        <div style={apiKeyContainerStyle}>
          <div style={inputGroupStyle}>
            <input
              type="text"
              id="accessKeyInput"
              value={accessKey}
              onChange={(e) => setAccessKey(e.target.value)}
              placeholder="Access Key"
              style={inputStyle}
            />
          </div>
          <div style={inputGroupStyle}>
            <input
              type="text"
              id="secretKeyInput"
              value={secretKey}
              onChange={(e) => setSecretKey(e.target.value)}
              placeholder="Secret Key"
              style={inputStyle}
            />
          </div>
          <button onClick={handleSubmit} style={buttonStyle}>
            Submit
          </button>{" "}
          {/* Submit 버튼 하나로 통일 */}
        </div>
      </div>
      {/* 성공 모달 */}
      {showSuccessModal && (
        <div className="modal">
          <div className="modal-content">
            <span className="close" onClick={() => setShowSuccessModal(false)}>
              &times;
            </span>
            <p>Success!</p>
          </div>
        </div>
      )}
    </div>
  );
}

export default SettingsPage;
