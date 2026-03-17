import React, { useState, useEffect } from 'react';
import axios from 'axios';

const ChatHistory = ({ user, headers }) => {
  const [chatLog, setChatLog] = useState([]);

  useEffect(() => {
    const fetchChatLog = async () => {
      try {
        const response = await axios.get(`${process.env.REACT_APP_API_BASE_URL}/llm/history`, {
          params: {
            userId: user?.id,
          },
          headers: headers,
        });
        const { data } = response;
        setChatLog(data);
      } catch (error) {
        console.error('Error fetching chat history:', error);
      }
    };

    fetchChatLog();
  }, [user, headers]);

  return (
    <div className="chat-history">
      <button onClick={() => console.log('New chat')}>New Chat</button>
      <ul>
        {chatLog.map((chat, index) => (
          <li key={index}>
            <span>{chat.senderName}</span>: <span>{chat.message}</span>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ChatHistory;
