import React, { useState, useEffect } from "react";
import axios from "axios";
import './order.css';
const API_URL = process.env.REACT_APP_API_BASE_URL;

function OrderTable() {
  const [orders, setOrders] = useState([]);
  const [accessToken, setAccessToken] = useState("");
  const [userId, setUserId] = useState("");

  useEffect(() => {
    // 로컬 스토리지에서 토큰과 ID 가져오기
    const user = JSON.parse(localStorage.getItem("user"));
    const accessToken = user?.accessToken;
    const userId = user?.id;

    setAccessToken(accessToken);
    setUserId(userId);

    if (accessToken && userId) {
      axios.get(`${API_URL}/upbit/account?userId=${userId}`, {
        headers: {
          Authorization: accessToken
        }
      })
        .then(response => {
          setOrders(response.data);
        })
        .catch(error => {
          console.error('Error fetching orders:', error);
        });
    }
  }, [accessToken, userId]);
  const orders_example = [
      {
    currency: 'USD',
    balance: 1000,
    avg_buy_price: 50,
    now_price: 60,
    buyAssets: 'AAPL',
    nowAssets: 'AAPL',
    return_rate: 20,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: -16,
  }, {
    currency: 'USD',
    balance: 1000,
    avg_buy_price: 50,
    now_price: 60,
    buyAssets: 'AAPL',
    nowAssets: 'AAPL',
    return_rate: 20,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  },
  {
    currency: 'EUR',
    balance: 2000,
    avg_buy_price: 30,
    now_price: 35,
    buyAssets: 'GOOGL',
    nowAssets: 'GOOGL',
    return_rate: 16,
  }
   ]

  return (
    <div className="ledger" >
      <div className="simple-striped-desktop">
        {/* Column headers */}
        <span>Currency</span>
        <span>Balance</span>
        <span>Average Buy Price</span>
        <span>Now Price</span>
        <span>Buy Assets</span>
        <span>Now Assets</span>
        <span>Return Rate</span>
      </div>
      <div className="body">
        {orders.map((order, index) => (
          <div key={index} className="row" >
            {/* Row content based on order properties */}
            <span><strong>{order.currency}</strong></span>
            <span style={{ color: 'red' }} >{order.balance}</span>
            <span>{order.avg_buy_price}</span>
            <span>{order.now_price}</span>
            <span>{order.buyAssets}</span>
            <span>{order.nowAssets}</span>
            <span style={{ color: order.return_rate > 0 ? "green" : "red" }}>{order.return_rate}</span>
          </div>
        ))}
      </div>
    </div>
  );
}

export default OrderTable;
