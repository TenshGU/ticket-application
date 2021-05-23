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
} from "antd";
import "../../static/style/pages/MyOrder.css";
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
import C9 from "../../static/images/9C.png";
import U3 from "../../static/images/3U.png";
const { Header, Content, Footer } = Layout;

const columns = [
  {
    title: "订单时间",
    dataIndex: "time",
    key: "time",
    align: "center",
  },
  {
    title: "航班信息",
    dataIndex: "name",
    key: "name",
    align: "center",
    // render: (text) => <a>{text}</a>,
  },
  {
    title: (
      <span>
        <svg
          t="1621234763250"
          class="icon"
          viewBox="0 0 1026 1024"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          p-id="23414"
          width="30"
          height="20"
        >
          <path
            d="M196.233887 600.241436a84.955658 84.955658 0 0 0 51.916159 39.850489 85.881319 85.881319 0 0 0 22.237261 2.956559 85.062547 85.062547 0 0 0 42.614647-11.490605l681.312735-393.352818c24.524693-14.188493 32.943299-45.554104 18.803975-70.121554l-17.102297-29.619039C953.453027 64.924593 859.390397 39.73291 785.76501 82.146605l-169.945519 98.120151L324.206096 41.218672a17.115123 17.115123 0 0 0-15.924376 0.619958l-133.295298 76.960335a17.095883 17.095883 0 0 0-8.525495 14.295382 17.112985 17.112985 0 0 0 7.674655 14.780659l232.176502 153.30071-112.569454 64.988727-137.752584-142.483508a17.112985 17.112985 0 0 0-20.88618-2.886012l-103.674121 59.858037a17.104434 17.104434 0 0 0-6.255165 23.363875z m19.832251-465.661327l101.536334-58.622397 262.263716 125.069094-140.332894 81.060609z m-75.365545 122.522989l137.73762 142.562606a17.119399 17.119399 0 0 0 20.851975 2.930906l503.574981-290.73904c57.269177-32.968952 130.419975-13.36972 163.532159 43.816083l17.102296 29.621178a17.095883 17.095883 0 0 1-6.255165 23.357461l-681.302046 393.352819A51.291925 51.291925 0 0 1 256.976969 607.131524a51.279098 51.279098 0 0 1-31.156109-23.943215l-162.471816-281.392635z m0 0"
            p-id="23415"
            fill="#1296db"
          ></path>
          <path
            d="M805.161152 149.937971l59.248769-34.196042 17.093745 29.61904-59.248768 34.196041z m0 0M745.895282 184.148977l29.629728-17.102296 17.093746 29.621177-29.621178 17.102296z m0 0M686.657203 218.347157l29.619039-17.093746 17.102297 29.61904-29.629729 17.102296z m0 0M627.440501 252.543198l29.61904-17.093745 17.102296 29.61904-29.627591 17.102296z m0 0M568.191733 286.747791l29.612626-17.093745 17.093745 29.629729-29.604075 17.083056z m0 0M508.942965 320.952384l29.612626-17.093745 17.102296 29.619039-29.612626 17.093746z m0 0M449.687783 355.156977l29.61904-17.093745 17.102296 29.619039-29.629729 17.102297z m0 0M390.456117 389.36157l29.627591-17.093745 17.093745 29.619039-29.61904 17.102297z m0 0M0 952.074154h1026.137787v34.204593H0z m0 0M906.421712 490.31215h-34.204593c-0.224468 198.256234-160.894129 358.923758-359.148225 359.148226H102.613779v34.204593h410.455115c217.143582-0.24157 393.117662-176.207098 393.352818-393.352819z m0 0"
            p-id="23416"
            fill="#1296db"
          ></path>
          <path
            d="M803.807933 490.31215h-34.204593c-0.158196 141.613428-114.923157 256.376251-256.534446 256.534447H34.204593v34.204593h478.864301c160.492225-0.181712 290.563741-130.244676 290.739039-290.73904z m0 0"
            p-id="23417"
            fill="#1296db"
          ></path>
        </svg>
        起飞
      </span>
    ),
    dataIndex: "leave",
    key: "leave",
    align: "center",
  },
  {
    title: (
      <span>
        <svg
          t="1621234820122"
          class="icon"
          viewBox="0 0 1024 1024"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          p-id="23882"
          width="30"
          height="20"
        >
          <path
            d="M65.533867 485.275733l758.308266 203.2576c27.3152 7.317333 55.392-8.900267 62.698667-36.2176l8.842667-32.9664c21.8496-81.924267-26.7328-166.0736-108.608-188.106666l-189.166934-50.692267-107.658666-303.784533a17.073067 17.073067 0 0 0-11.675734-10.7904L329.9328 26.176a17.0624 17.0624 0 0 0-21.124267 19.899733l55.6672 271.940267-125.3184-33.582933 3.328-197.749334a17.077333 17.077333 0 0 0-12.650666-16.768L114.432 39.050667a17.066667 17.066667 0 0 0-20.9088 12.0832L5.205333 380.840533c-12.149333 45.493333 14.850133 92.226133 60.3264 104.4352z m335.790933-157.393066L347.767467 66.2336 460.8 96.541867l96.817067 273.290666zM38.158933 389.700267l83.9168-313.258667 86.141867 23.125333L204.8 297.250133a17.064533 17.064533 0 0 0 12.650667 16.7744l560.4992 150.184534c63.684267 17.141333 101.467733 82.5984 84.4672 146.3168l-8.8256 32.974933a17.073067 17.073067 0 0 1-20.906667 12.0832L74.3552 452.366933c-27.2896-7.317333-43.490133-35.3664-36.1984-62.666666z m0 0"
            p-id="23883"
            fill="#1296db"
          ></path>
          <path
            d="M743.790933 525.725867l8.842667-32.968534 65.949867 17.685334-8.842667 32.9664z m0 0M677.8496 508.066133l8.832-32.974933 32.977067 8.832-8.832 32.977067z m0 0M611.9168 490.391467l8.832-32.974934 32.968533 8.842667-8.832 32.9664z m0 0M545.966933 472.7168l8.840534-32.9664 32.968533 8.832-8.834133 32.968533z m0 0M480.049067 455.04l8.834133-32.964267 32.974933 8.832-8.840533 32.9664z m0 0M414.0672 437.399467l8.832-32.9664 32.974933 8.832-8.832 32.968533z m0 0M348.149333 419.7248l8.832-32.9664 32.968534 8.832-8.832 32.968533z m0 0M282.325333 402.176l8.840534-32.977067 32.992 8.842667-8.840534 32.974933z m0 0M0 963.1424h1024v34.133333H0z m0 0M78.549333 603.3344l13.585067-31.3344 512 221.8752-13.576533 31.332267z m0 0M9.717333 688.416l14.683734-30.833067 358.4 170.658134-14.683734 30.833066z m0 0"
            p-id="23884"
            fill="#1296db"
          ></path>
        </svg>
        到达
      </span>
    ),
    dataIndex: "arrive",
    key: "arrive",
    align: "center",
  },
  {
    title: "价格",
    dataIndex: "price",
    key: "price",
    align: "center",
  },
];

export default class MyOrder extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      datalist: [],
    };
  }

  componentDidMount = () => {
    //初始化没有搜索条件时候的列表
    axios
      .get("http://121.5.237.69/backEnd/orders", {
        headers: {
          bear: sessionStorage.getItem("bear"),
        },
        params: {},
      })
      .then((res) => {
        if (res.data.code == 200) {
          console.log(res.data.infoMap);
          const data = [];
          if (res.data.infoMap.total != 0) {
            console.log("啊啊啊")
            res.data.infoMap.orders.map((item) => {
              data.push({
                key: item.id,
                time: moment(item.orderTime).format("YYYY-MM-DD  HH:mm:ss"),
                name: (
                  <div>
                    {item.flight.plane.company.name == "南方航空" ? (
                      <div>
                        <div className="listTimep11">
                          <img alt="" src={C9} width="20px" />
                          南方航空
                        </div>

                        <span className="listTimep2">
                          {item.flight.name + " " + item.flight.plane.name}
                        </span>
                      </div>
                    ) : (
                      <div>
                        <div className="listTimep11">
                          <img alt="" src={U3} width="20px" />
                          北方航空
                        </div>

                        <span className="listTimep2">
                          {item.flight.name + " " + item.flight.plane.name}
                        </span>
                      </div>
                    )}
                  </div>
                ),
                leave: (
                  <div className="listTime">
                    <div className="listTimep1">{item.flight.leaveTime}</div>
                    <div className="listTimep2">
                      {item.flight.leaveAirportName}
                    </div>
                  </div>
                ),
                arrive: (
                  <div className="listTime">
                    <div className="listTimep1">{item.flight.arriveTime}</div>
                    <div className="listTimep2">
                      {item.flight.arriveAirportName}
                    </div>
                  </div>
                ),
                price: (
                  <Space size="middle">
                    <p>
                      ￥<span className="money">{item.flight.price}</span>
                    </p>
                  </Space>
                ),
              });
            });
          }
          this.setState({
            datalist: data,
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
                  <div>
                    <Table
                      columns={columns}
                      dataSource={this.state.datalist}
                      pagination={{pageSize:5}}
                    />
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
