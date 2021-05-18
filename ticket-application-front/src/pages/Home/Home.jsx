import React from "react";
import {
  Menu,
  Layout,
  Breadcrumb,
  Carousel,
  Image,
  Row,
  Col,
  Card,
  Anchor,
  Button,
  message,
} from "antd";
import "../../static/style/pages/Home.css";
import "antd/dist/antd.css";
import axios from "axios";
import {
  BellTwoTone,
  SendOutlined,
  ToTopOutlined,
  FireOutlined,
  SwapOutlined,
} from "@ant-design/icons";
import { Switch, Route, NavLink, Redirect,Link } from "react-router-dom";
import Head from "../../components/Head";
import bo1 from "../../static/images/bo1.jpg";
import bo2 from "../../static/images/bo2.jpg";
import bo3 from "../../static/images/bo3.jpg";
import bo4 from "../../static/images/bo4.jpg";
import hot1 from "../../static/images/hot1.png";
import hot2 from "../../static/images/hot2.png";
import hot3 from "../../static/images/hot3.png";
import hot4 from "../../static/images/hot4.png";
import hot5 from "../../static/images/hot5.png";
import hot6 from "../../static/images/hot6.png";
import hot7 from "../../static/images/hot7.png";
import hot8 from "../../static/images/hot8.png";
import hot11 from "../../static/images/hot11.png";
import hot12 from "../../static/images/hot12.png";
import hot13 from "../../static/images/hot13.png";
import hot14 from "../../static/images/hot14.png";
import hot15 from "../../static/images/hot15.png";
import hot16 from "../../static/images/hot16.png";
import hot17 from "../../static/images/hot17.png";
import hot18 from "../../static/images/hot18.png";

const { Header, Content, Footer } = Layout;

const tabList = [
  {
    key: "tab1",
    tab: "境内",
  },
  {
    key: "tab2",
    tab: "日本",
  },
];

export default class Home extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      key: "tab1",
      noTitleKey: "app",
    };
  }

  componentDidMount = () => {
    axios
      .get("http://localhost:8080/flight", {
        headers: {
          bear: sessionStorage.getItem("bear"),
        },
      })
      .then((res) => {
        if (res.data.code==200) {
          this.setState({
            total: res.data.infoMap.toal,
            flights: res.data.infoMap.flights,
          });
          console.log(res.data.infoMap);
        }
        else{
          message.warning("请重新登录。")
        }
      })
      .catch(function (error) {
        console.log(error);
      });
  };

  handleClickFun = (e, link) => {
    e.preventDefault();
    if (link.href) {
      // 找到锚点对应得的节点
      let element = document.getElementById(link.href);
      // 如果对应id的锚点存在，就跳滚动到锚点顶部
      element && element.scrollIntoView({ block: "start", behavior: "smooth" });
    }
  };

  clicka = (e) => {
    console.log(e.target);
    this.props.history.push("/BuyTicket");
  };

  onTabChange = (key, type) => {
    console.log(key, type);
    this.setState({ [type]: key });
  };

  render() {
    return (
      <div>
        <div className="anchor">
          <Anchor showInkInFixed="true" onClick={this.handleClickFun}>
            <Anchor.Link href="#top" title={<ToTopOutlined />} />
            <Anchor.Link href="#card1" title={<FireOutlined />} />
            <Anchor.Link href="#card2" title={<SendOutlined />} />
          </Anchor>
        </div>

        <Layout>
          <Header style={{ backgroundColor: "#F4F4F4" }}>
            <Head></Head>
          </Header>
          <Content>
            <Layout className="layout">
              <Header id="top" className="header">
                <Carousel autoplay dotPosition="bottom" className="header-bo">
                  <Image src={bo1} />

                  <Image src={bo2} />
                  <Image src={bo3} />
                  <Image src={bo4} />
                </Carousel>
              </Header>
              <Content style={{ padding: "2% 13%" }}>
                <div id="card1" className="card1">
                  <Card
                    style={{ width: "100%" }}
                    title={<span className="hot">热门</span>}
                    extra={<Link to="/buyTicket">更多热门机票</Link>}
                    tabList={tabList}
                    activeTabKey={this.state.key}
                    onTabChange={(key) => {
                      this.onTabChange(key, "key");
                    }}
                  >
                    {
                      {
                        tab1: (
                          <p>
                            <Card.Grid
                              tabIndex="0"
                              hidefocus="true"
                              className="gridStyle"
                              onClick={() => {
                                this.props.history.push("/buyTicket");
                              }}
                            >
                              <img alt="" src={hot1} width="100%" />
                            </Card.Grid>
                            <Card.Grid
                              className="gridStyle"
                              onClick={() => {
                                this.props.history.push("/buyTicket");
                              }}
                            >
                              <img alt="" src={hot2} width="100%" />
                            </Card.Grid>
                            <Card.Grid
                              className="gridStyle"
                              onClick={() => {
                                this.props.history.push("/buyTicket");
                              }}
                            >
                              <img alt="" src={hot3} width="100%" />
                            </Card.Grid>
                            <Card.Grid
                              className="gridStyle"
                              onClick={() => {
                                this.props.history.push("/buyTicket");
                              }}
                            >
                              <img alt="" src={hot4} width="100%" />
                            </Card.Grid>
                            <Card.Grid
                              className="gridStyle"
                              onClick={() => {
                                this.props.history.push("/buyTicket");
                              }}
                            >
                              <img alt="" src={hot5} width="100%" />
                            </Card.Grid>
                            <Card.Grid
                              className="gridStyle"
                              onClick={() => {
                                this.props.history.push("/buyTicket");
                              }}
                            >
                              <img alt="" src={hot6} width="100%" />
                            </Card.Grid>
                            <Card.Grid
                              className="gridStyle"
                              onClick={() => {
                                this.props.history.push("/buyTicket");
                              }}
                            >
                              <img alt="" src={hot7} width="100%" />
                            </Card.Grid>
                            <Card.Grid
                              className="gridStyle"
                              onClick={() => {
                                this.props.history.push("/buyTicket");
                              }}
                            >
                              <img alt="" src={hot8} width="100%" />
                            </Card.Grid>
                          </p>
                        ),
                        tab2: (
                          <p>
                            <Card.Grid
                              className="gridStyle"
                              onClick={() => {
                                this.props.history.push("/buyTicket");
                              }}
                            >
                              <img alt="" src={hot11} width="99%" />
                            </Card.Grid>
                            <Card.Grid
                              className="gridStyle"
                              onClick={() => {
                                this.props.history.push("/buyTicket");
                              }}
                            >
                              <img alt="" src={hot12} width="100%" />
                            </Card.Grid>
                            <Card.Grid
                              className="gridStyle"
                              onClick={() => {
                                this.props.history.push("/buyTicket");
                              }}
                            >
                              <img alt="" src={hot13} width="100%" />
                            </Card.Grid>
                            <Card.Grid
                              className="gridStyle"
                              onClick={() => {
                                this.props.history.push("/buyTicket");
                              }}
                            >
                              <img alt="" src={hot14} width="102%" />
                            </Card.Grid>
                            <Card.Grid
                              className="gridStyle"
                              onClick={() => {
                                this.props.history.push("/buyTicket");
                              }}
                            >
                              <img alt="" src={hot15} width="100%" />
                            </Card.Grid>
                            <Card.Grid
                              className="gridStyle"
                              onClick={() => {
                                this.props.history.push("/buyTicket");
                              }}
                            >
                              <img alt="" src={hot16} width="100%" />
                            </Card.Grid>
                            <Card.Grid
                              className="gridStyle"
                              onClick={() => {
                                this.props.history.push("/buyTicket");
                              }}
                            >
                              <img alt="" src={hot17} width="100%" />
                            </Card.Grid>
                            <Card.Grid
                              className="gridStyle"
                              onClick={() => {
                                this.props.history.push("/buyTicket");
                              }}
                            >
                              <img alt="" src={hot18} width="100%" />
                            </Card.Grid>
                          </p>
                        ),
                      }[this.state.key]
                    }
                  </Card>
                </div>
                <div id="card2" className="card2">
                  <Card
                    title={
                      <span style={{ color: "#06c", fontWeight: "400px" }}>
                        特价机票
                      </span>
                    }
                    extra={<Link to="/buyTicket">更多特价优惠机票</Link>}
                  >
                    {!this.state.flights ? (
                      <div></div>
                    ) : (
                      this.state.flights.map((item) => (
                        <Card.Grid className="gridStyle1">
                          <p className="place">
                            {item.leaveAirportName}
                            <SwapOutlined className="placeicon" />
                            {item.arriveAirportName}
                          </p>
                          <p className="date">
                            {item.leaveTime.split(" ")[0]}
                            <span className="date-icon">-</span>
                            {item.arriveTime.split(" ")[0]}
                          </p>
                          <p>
                            ￥<span className="money">{item.price}</span>
                            <span>起</span>
                            <Button
                              className="cardButton1"
                              onClick={this.clicka}
                            >
                              立抢
                            </Button>
                          </p>
                        </Card.Grid>
                      ))
                    )}
                  </Card>
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
