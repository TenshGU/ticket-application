import React, { Component } from "react";
import { Menu, Layout, Avatar, Image, Row, Col, Popover, Carousel } from "antd";
import MenuItem from "antd/lib/menu/MenuItem";
import { Switch, Route, NavLink, Redirect } from "react-router-dom";
import { UserOutlined, ExportOutlined } from "@ant-design/icons";
import axios from "axios";
import "./../static/style/components/Head.css";

const { Header, Content, Footer, Sider } = Layout;

export default class Head extends Component {
  constructor(props) {
    super(props);
    this.state = {
      username: "",
      firstName: "",
      lastName: "",
      image: "",
    };
  }

  //更新个人信息面板的信息
  componentDidMount = () => {
    axios
      .get("http://121.5.237.69/backEnd/myself", {
        headers: {
          bear: sessionStorage.getItem("bear"),
        },
      })
      .then((res) => {
        this.setState({
          username: res.data.infoMap.username,
          firstName: res.data.infoMap.firstName,
          lastName: res.data.infoMap.lastName,
          image: res.data.infoMap.image,
        });
        console.log(res.data.infoMap.image);
      })
      .catch(function (error) {
        console.log(error);
      });
  };

  render() {
    return (
      <Row className="whole-header">
        <Col textalign="middle" offset={1} span={4}>
          <span className="header-logotxt">机票订购系统</span>
          <span className="header-txt">让旅行更幸福！</span>
        </Col>
        {sessionStorage.username ? (
          <Col offset={12} span={7}>
            <Popover
              placement="bottom"
              title={
                <div style={{ textAlign: "center" }}>
                  {this.state.firstName + " " + this.state.lastName}
                </div>
              }
              content={
                <div style={{ textAlign: "center", fontSize: "12px" }}>
                  <p>
                    <UserOutlined style={{ fontSize: "15px" }} />
                    <NavLink to="/personalCenter" style={{ fontSize: "13px" }}>
                      个人中心
                    </NavLink>
                  </p>
                  <p>
                    <ExportOutlined style={{ fontSize: "15px" }} />

                    <span
                      style={{ fontSize: "13px" }}
                      onClick={(e) => {
                        sessionStorage.removeItem("bear");
                        sessionStorage.removeItem("username")
                        this.setState({
                          username:""
                        })
                      }}
                    >
                      退出
                    </span>
                  </p>
                </div>
              }
              trigger="hover"
              onMouseEnter={this.updatePersonInformation}
            >
              <Avatar src={<Image src={this.state.image} />} />
            </Popover>
            
            <NavLink to="/personalCenter" className="hello-login">
              {sessionStorage.username}
            </NavLink>
            <NavLink to="/home" className="font2">
              首页
            </NavLink>
            <NavLink to="/myOrder" className="font2">
              我的订单
            </NavLink>
            {sessionStorage.username === "admin" ? (
              <NavLink to="/ticketManagement" className="font2">
                票务管理
              </NavLink>
            ) : (
              <span></span>
            )}
          </Col>
        ) : (
          <Col offset={13} span={6}>
            <Row>
              <Col>
                <NavLink to="/login" className="hello-login">
                  您好，请登录
                </NavLink>
                <NavLink to="/register" className="hello-register">
                  免费注册
                </NavLink>
              </Col>
            </Row>
          </Col>
        )}
      </Row>
    );
  }
}
