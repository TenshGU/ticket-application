import React, { useState } from "react";
import {
  Layout,
  Button,
  message,
  Form,
  Table,
  Space,
  Input,
  InputNumber,
  Popconfirm,
  Typography,
} from "antd";
import "../../static/style/pages/TicketManagement.css";
import "antd/dist/antd.css";
import axios from "axios";
import moment from "moment";
import {
  BellTwoTone,
  SendOutlined,
  ToTopOutlined,
  FireOutlined,
  SwapOutlined,
  SearchOutlined,
} from "@ant-design/icons";
import { Switch, Route, NavLink, Redirect } from "react-router-dom";
import Head from "../../components/Head";
import EditableTable from "./../../components/EditableTable";
import C9 from "../../static/images/9C.png";
import U3 from "../../static/images/3U.png";
const { Header, Content, Footer } = Layout;

//以上都是表格的相关代码

export default class TicketManagement extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
    };
  }


  //以下为表格的设置

  render() {
    return (
      <div>
        <Layout>
          <Header style={{ backgroundColor: "#F4F4F4" }}>
            <Head></Head>
          </Header>
          <Content>
            <Layout className="layout">
              <Header id="top" className="header1"></Header>
              <Content style={{ padding: "2% 13%" }}>
                <div className="card11">
                  <div id="editableTable">
                    <EditableTable data={this.state.data}/>
                  </div>
                </div>
              </Content>
              <Footer style={{ textAlign: "center" }}>
                Ticket System ©2021 Created by SCAU
              </Footer>
            </Layout>
          </Content>
        </Layout>
      </div>
    );
  }
}
