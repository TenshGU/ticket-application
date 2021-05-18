import React from "react";
import {
  Layout,
  Anchor,
  Button,
  message,
  Form,
  Input,
  Radio,
  Select,
  Cascader,
  DatePicker,
  InputNumber,
  TreeSelect,
  Divider,
  Row,
  Col,
  Table,
  Tag,
  Space,
  Descriptions,
} from "antd";
import "../../static/style/pages/PersonalCenter.css";
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
const { Header, Content, Footer } = Layout;
export default class personalCenter extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      username: "",
      phone: "",
      firstName: "",
      lastName: "",
      gender: "",
      age: "",
      email: "",
      image: "",
    };
  }

  componentDidMount = () => {
    axios
      .get("http://localhost:8080/myself", {
        headers: {
          bear: sessionStorage.getItem("bear"),
        },
      })
      .then((res) => {
        if (res.data.code == 200) {
          this.setState({
            username: res.data.infoMap.username,
            phone: res.data.infoMap.phone,
            firstName: res.data.infoMap.firstName,
            lastName: res.data.infoMap.lastName,
            gender: res.data.infoMap.gender,
            age: res.data.infoMap.age,
            email: res.data.infoMap.email,
            image: res.data.infoMap.image,
          });
        } else {
          this.props.history.push("login");
          message.warning("请重新登录。");
        }
      })
      .catch(function (error) {
        console.log(error);
      });
  };

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
                <div id="card1" className="card11">
                  <Row
                    style={{
                      height: "200px",
                      margin: "30px 20%",
                      textAlign: "center",
                    }}
                  >
                    <Descriptions
                      title="个人信息"
                      bordered
                      size={this.state.size}
                      extra={<Button type="primary" onClick={()=>{
                          this.props.history.go(-1)
                      }}>返回</Button>}
                    >
                      <Descriptions.Item label="用户名">
                        {this.state.username}
                      </Descriptions.Item>
                      <Descriptions.Item label="手机号">
                        {this.state.phone}
                      </Descriptions.Item>
                      <Descriptions.Item label="姓名">
                        {this.state.lastName+" "+this.state.firstName}
                      </Descriptions.Item>
                      <Descriptions.Item label="性别">
                        {this.state.gender == "M" ? "男" : "女"}
                      </Descriptions.Item>
                      <Descriptions.Item label="年龄">
                        {this.state.age}
                      </Descriptions.Item>
                      <Descriptions.Item label="邮箱">
                        {this.state.email}
                      </Descriptions.Item>
                    </Descriptions>
                  </Row>
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
