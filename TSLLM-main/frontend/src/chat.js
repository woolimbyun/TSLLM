import React from 'react';
import Header from './Header_after_login';
import './style.css';

const Chat = () => {
  return (
    <div style={{ position: 'relative' }}>
      <Header />
      <iframe
        src="https://www.chatbase.co/chatbot-iframe/QRJXiSmHFCw9sRntSBxCb"
        title="Chatbot"
        width="100%"
        style={{ height: '100%', minHeight: '800px', border: 'none' }}
      ></iframe>
      <div style={{
        position: 'absolute',
        bottom: 0,
        left: 0,
        width: '100%',
        height: '30px', /* 다른 박스의 높이 조절 */
        backgroundColor: 'white', /* 다른 박스의 배경색 설정 */
        zIndex: 1, /* 다른 요소 위에 표시되도록 설정 */
      }}>
        {/* 다른 박스에 넣을 내용 */}
      </div>
    </div>
  );
};

export default Chat;
