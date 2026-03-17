import React, { useState, useEffect } from "react";
import axios from "axios";
import "./TransactionHistory.css";

const API_URL = process.env.REACT_APP_API_BASE_URL;

function TransactionHistory() {
  const [transactions, setTransactions] = useState([]);
  const [accessToken, setAccessToken] = useState("");
  const [userId, setUserId] = useState("");

  useEffect(() => {
    const user = JSON.parse(localStorage.getItem("user"));
    const accessToken = user?.accessToken;
    const userId = user?.id;

    setAccessToken(accessToken);
    setUserId(userId);

    if (accessToken && userId) {
      axios
        .get(`${API_URL}/upbit/history?userId=${userId}`, {
          headers: {
            Authorization: accessToken,
          },
        })
        .then((response) => {
          setTransactions(response.data);
        })
        .catch((error) => {
          console.error("Error:", error);
        });
    }
  }, [accessToken, userId]);
  const transactions_example = [
    {
        market : '11111111111111111',
        side : '11111111111111111',
        state : '111111111111111',
        price : '1111111111111111111',
        volume : '11111111111111111',
        executed_volume : '111111111111111',
        remaining_volume : '11111111111111',
        created_at : '111111111111111'
    }, {
        market : '11111111111111111',
        side : '11111111111111111',
        state : '111111111111111',
        price : '1111111111111111111',
        volume : '11111111111111111',
        executed_volume : '111111111111111',
        remaining_volume : '11111111111111',
        created_at : '111111111111111'
    }, {
        market : '11111111111111111',
        side : '11111111111111111',
        state : '111111111111111',
        price : '1111111111111111111',
        volume : '11111111111111111',
        executed_volume : '111111111111111',
        remaining_volume : '11111111111111',
        created_at : '111111111111111'
    }, {
        market : '11111111111111111',
        side : '11111111111111111',
        state : '111111111111111',
        price : '1111111111111111111',
        volume : '11111111111111111',
        executed_volume : '111111111111111',
        remaining_volume : '11111111111111',
        created_at : '111111111111111'
    }, {
        market : '11111111111111111',
        side : '11111111111111111',
        state : '111111111111111',
        price : '1111111111111111111',
        volume : '11111111111111111',
        executed_volume : '111111111111111',
        remaining_volume : '11111111111111',
        created_at : '111111111111111'
    }, {
        market : '11111111111111111',
        side : '11111111111111111',
        state : '111111111111111',
        price : '1111111111111111111',
        volume : '11111111111111111',
        executed_volume : '111111111111111',
        remaining_volume : '11111111111111',
        created_at : '111111111111111'
    }, {
        market : '11111111111111111',
        side : '11111111111111111',
        state : '111111111111111',
        price : '1111111111111111111',
        volume : '11111111111111111',
        executed_volume : '111111111111111',
        remaining_volume : '11111111111111',
        created_at : '111111111111111'
    }, {
        market : '11111111111111111',
        side : '11111111111111111',
        state : '111111111111111',
        price : '1111111111111111111',
        volume : '11111111111111111',
        executed_volume : '111111111111111',
        remaining_volume : '11111111111111',
        created_at : '111111111111111'
    }, {
        market : '11111111111111111',
        side : '11111111111111111',
        state : '111111111111111',
        price : '1111111111111111111',
        volume : '11111111111111111',
        executed_volume : '111111111111111',
        remaining_volume : '11111111111111',
        created_at : '111111111111111'
    }, {
        market : '11111111111111111',
        side : '11111111111111111',
        state : '111111111111111',
        price : '1111111111111111111',
        volume : '11111111111111111',
        executed_volume : '111111111111111',
        remaining_volume : '11111111111111',
        created_at : '111111111111111'
    }, {
        market : '11111111111111111',
        side : '11111111111111111',
        state : '111111111111111',
        price : '1111111111111111111',
        volume : '11111111111111111',
        executed_volume : '111111111111111',
        remaining_volume : '11111111111111',
        created_at : '111111111111111'
    }, {
        market : '11111111111111111',
        side : '11111111111111111',
        state : '111111111111111',
        price : '1111111111111111111',
        volume : '11111111111111111',
        executed_volume : '111111111111111',
        remaining_volume : '11111111111111',
        created_at : '111111111111111'
    }, {
        market : '11111111111111111',
        side : '11111111111111111',
        state : '111111111111111',
        price : '1111111111111111111',
        volume : '11111111111111111',
        executed_volume : '111111111111111',
        remaining_volume : '11111111111111',
        created_at : '111111111111111'
    }, {
        market : '11111111111111111',
        side : '11111111111111111',
        state : '111111111111111',
        price : '1111111111111111111',
        volume : '11111111111111111',
        executed_volume : '111111111111111',
        remaining_volume : '11111111111111',
        created_at : '111111111111111'
    }, {
        market : '11111111111111111',
        side : '11111111111111111',
        state : '111111111111111',
        price : '1111111111111111111',
        volume : '11111111111111111',
        executed_volume : '111111111111111',
        remaining_volume : '11111111111111',
        created_at : '111111111111111'
    }, {
        market : '11111111111111111',
        side : '11111111111111111',
        state : '111111111111111',
        price : '1111111111111111111',
        volume : '11111111111111111',
        executed_volume : '111111111111111',
        remaining_volume : '11111111111111',
        created_at : '111111111111111'
    }, {
        market : '11111111111111111',
        side : '11111111111111111',
        state : '111111111111111',
        price : '1111111111111111111',
        volume : '11111111111111111',
        executed_volume : '111111111111111',
        remaining_volume : '11111111111111',
        created_at : '111111111111111'
    }, {
        market : '11111111111111111',
        side : '11111111111111111',
        state : '111111111111111',
        price : '1111111111111111111',
        volume : '11111111111111111',
        executed_volume : '111111111111111',
        remaining_volume : '11111111111111',
        created_at : '111111111111111'
    }, {
        market : '11111111111111111',
        side : '11111111111111111',
        state : '111111111111111',
        price : '1111111111111111111',
        volume : '11111111111111111',
        executed_volume : '111111111111111',
        remaining_volume : '11111111111111',
        created_at : '111111111111111'
    }, {
        market : '11111111111111111',
        side : '11111111111111111',
        state : '111111111111111',
        price : '1111111111111111111',
        volume : '11111111111111111',
        executed_volume : '111111111111111',
        remaining_volume : '11111111111111',
        created_at : '111111111111111'
    }, {
        market : '11111111111111111',
        side : '11111111111111111',
        state : '111111111111111',
        price : '1111111111111111111',
        volume : '11111111111111111',
        executed_volume : '111111111111111',
        remaining_volume : '11111111111111',
        created_at : '111111111111111'
    }, {
        market : '11111111111111111',
        side : '11111111111111111',
        state : '111111111111111',
        price : '1111111111111111111',
        volume : '11111111111111111',
        executed_volume : '111111111111111',
        remaining_volume : '11111111111111',
        created_at : '111111111111111'
    }, {
        market : '11111111111111111',
        side : '11111111111111111',
        state : '111111111111111',
        price : '1111111111111111111',
        volume : '11111111111111111',
        executed_volume : '111111111111111',
        remaining_volume : '11111111111111',
        created_at : '111111111111111'
    }, {
        market : '11111111111111111',
        side : '11111111111111111',
        state : '111111111111111',
        price : '1111111111111111111',
        volume : '11111111111111111',
        executed_volume : '111111111111111',
        remaining_volume : '11111111111111',
        created_at : '111111111111111'
    }, {
        market : '11111111111111111',
        side : '11111111111111111',
        state : '111111111111111',
        price : '1111111111111111111',
        volume : '11111111111111111',
        executed_volume : '111111111111111',
        remaining_volume : '11111111111111',
        created_at : '111111111111111'
    }, {
        market : '11111111111111111',
        side : '11111111111111111',
        state : '111111111111111',
        price : '1111111111111111111',
        volume : '11111111111111111',
        executed_volume : '111111111111111',
        remaining_volume : '11111111111111',
        created_at : '111111111111111'
    }, {
        market : '11111111111111111',
        side : '11111111111111111',
        state : '111111111111111',
        price : '1111111111111111111',
        volume : '11111111111111111',
        executed_volume : '111111111111111',
        remaining_volume : '11111111111111',
        created_at : '111111111111111'
    }, {
        market : '11111111111111111',
        side : '11111111111111111',
        state : '111111111111111',
        price : '1111111111111111111',
        volume : '11111111111111111',
        executed_volume : '111111111111111',
        remaining_volume : '11111111111111',
        created_at : '111111111111111'
    }, {
        market : '11111111111111111',
        side : '11111111111111111',
        state : '111111111111111',
        price : '1111111111111111111',
        volume : '11111111111111111',
        executed_volume : '111111111111111',
        remaining_volume : '11111111111111',
        created_at : '111111111111111'
    }, {
        market : '11111111111111111',
        side : '11111111111111111',
        state : '111111111111111',
        price : '1111111111111111111',
        volume : '11111111111111111',
        executed_volume : '111111111111111',
        remaining_volume : '11111111111111',
        created_at : '111111111111111'
    }, {
        market : '11111111111111111',
        side : '11111111111111111',
        state : '111111111111111',
        price : '1111111111111111111',
        volume : '11111111111111111',
        executed_volume : '111111111111111',
        remaining_volume : '11111111111111',
        created_at : '111111111111111'
    }, {
        market : '11111111111111111',
        side : '11111111111111111',
        state : '111111111111111',
        price : '1111111111111111111',
        volume : '11111111111111111',
        executed_volume : '111111111111111',
        remaining_volume : '11111111111111',
        created_at : '111111111111111'
    }, {
        market : '11111111111111111',
        side : '11111111111111111',
        state : '111111111111111',
        price : '1111111111111111111',
        volume : '11111111111111111',
        executed_volume : '111111111111111',
        remaining_volume : '11111111111111',
        created_at : '111111111111111'
    }
  ]
  return (
    <div className="ledger">
      <div className="simple-striped-desktop">
        <span>Market</span>
        <span>Side</span>
        <span>State</span>
        <span>Price</span>
        <span>Volume</span>
        <span>Executed Volume</span>
        <span>Remaining Volume</span>
        <span>Executed Time</span>
      </div>
      <div className="body">
        {transactions.map((transaction, index) => (
          <div key={index} className="row">
            <span><strong>{transaction.market}</strong></span>
            <span style={{ color: transaction.side === 'ask' ? 'green' : 'red' }} >{transaction.side}</span>
            <span>{transaction.state}</span>
            <span>{transaction.price}</span>
            <span>{transaction.volume}</span>
            <span>{transaction.executed_volume}</span>
            <span>{transaction.remaining_volume}</span>
            <span><strong>{transaction.created_at}</strong></span>
          </div>
        ))}
      </div>
    </div>
  );
}

export default TransactionHistory;
