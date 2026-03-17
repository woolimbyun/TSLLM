import React from "react";
import { Menu } from "antd";
import {
  LineChartOutlined,
  OrderedListOutlined,
  HistoryOutlined,
  SettingOutlined,
} from "@ant-design/icons";
import { Link, Route, Routes } from "react-router-dom";


import OrderPage from "./OrderPage";
import HistoryPage from "./HistoryPage";
import SettingsPage from "./SettingsPage";

function getItem(label, key, icon, path) {
  return {
    key,
    icon,
    label,
    path,
  };
}

const items = [
  getItem("Orders", "sub2", <OrderedListOutlined />, "/orders"),
  getItem("History", "sub3", <HistoryOutlined />, "/history"),
  getItem("Setting", "sub4", <SettingOutlined />, "/setting"),
];

const onClick = (e) => {
  console.log("Click", e);
};

const TradingMenu = () => {
  const customMenuItemStyle = {
    padding: "4px 12px",
  };

  return (
    <>
      <Menu mode="vertical" onClick={onClick}>
        {items.map((item) => (
          <Menu.Item key={item.key} icon={item.icon} style={customMenuItemStyle}>
            <Link to={item.path}>{item.label}</Link>
          </Menu.Item>
        ))}
      </Menu>

      <Routes>
        <Route path="/orders" element={<OrderPage />} />
        <Route path="/history" element={<HistoryPage />} />
        <Route path="/setting" element={< SettingsPage/>} />
      </Routes>
    </>
  );
};

export default TradingMenu;