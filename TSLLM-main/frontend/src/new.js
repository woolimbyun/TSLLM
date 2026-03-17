import React from "react";
import { Menu } from "antd";
import {
  LineChartOutlined,
  OrderedListOutlined,
  HistoryOutlined,
  SettingOutlined,
} from "@ant-design/icons";
function getItem(label, key, icon) {
  return {
    key,
    icon,
    label,
  };
}
const items = [
  getItem("Trading", "sub1", <LineChartOutlined />),
  getItem("Orders", "sub2", <OrderedListOutlined />),
  getItem("History", "sub3", <HistoryOutlined />),
  getItem("Setting", "sub4", <SettingOutlined />),
];
const onClick = (e) => {
  console.log("Click", e);
};

const TradingMenu = () => {
  return <Menu mode="vertical" onClick={onClick} items={items} />;
};

export default TradingMenu;
