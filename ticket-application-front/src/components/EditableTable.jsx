import React, { useContext, useState, useEffect, useRef } from "react";
import {
  Table,
  DatePicker,
  Input,
  Button,
  Popconfirm,
  Form,
  message,
  Modal,
  Divider,
  Select,
  Row,
  Col,
  InputNumber,
} from "antd";
import moment from "moment";
import {
  UserOutlined,
  RocketOutlined,
  PayCircleOutlined,
  DashboardOutlined,
} from "@ant-design/icons";
import "../static/style/components/EditableTable.css";
import axios from "axios";

const EditableContext = React.createContext(null);

const EditableRow = ({ index, ...props }) => {
  const [form] = Form.useForm();
  return (
    <Form form={form} component={false}>
      <EditableContext.Provider value={form}>
        <tr {...props} />
      </EditableContext.Provider>
    </Form>
  );
};

const EditableCell = ({
  title,
  editable,
  children,
  dataIndex,
  record,
  handleSave,
  ...restProps
}) => {
  const [editing, setEditing] = useState(false);
  const inputRef = useRef(null);
  const form = useContext(EditableContext);
  useEffect(() => {
    if (editing) {
      inputRef.current.focus();
    }
  }, [editing]);

  const toggleEdit = () => {
    setEditing(!editing);
    form.setFieldsValue({
      [dataIndex]: record[dataIndex],
    });
  };

  const save = async () => {
    try {
      const values = await form.validateFields();
      toggleEdit();
      handleSave({ ...record, ...values });
    } catch (errInfo) {
      console.log("Save failed:", errInfo);
    }
  };

  let childNode = children;

  if (editable) {
    childNode = editing ? (
      <Form.Item
        style={{
          margin: 0,
        }}
        name={dataIndex}
        rules={[
          {
            required: true,
            message: `${title} is required.`,
          },
        ]}
      >
        <Input ref={inputRef} onPressEnter={save} onBlur={save} />
      </Form.Item>
    ) : (
      <div
        className="editable-cell-value-wrap"
        style={{
          paddingRight: 24,
        }}
        onClick={toggleEdit}
      >
        {children}
      </div>
    );
  }

  return <td {...restProps}>{childNode}</td>;
};

export default class EditableTable extends React.Component {
  constructor(props) {
    super(props);
    this.columns = [
      {
        title: "ID",
        dataIndex: "id",
        align: "center",
        fixed: "left",
        width: 50,
      },
      {
        title: "????????????",
        dataIndex: "name",
        editable: true,
        align: "center",
        fixed: "left",
        width: 90,
      },
      {
        title: "????????????",
        dataIndex: "leaveTime",
        editable: true,
        align: "center",
      },
      {
        title: "????????????",
        dataIndex: "arriveTime",
        editable: true,
        align: "center",
      },
      {
        title: "????????????",
        dataIndex: "leaveAirportName",
        editable: true,
        align: "center",
      },
      {
        title: "????????????",
        dataIndex: "arriveAirportName",
        editable: true,
        align: "center",
      },
      {
        title: "??????",
        dataIndex: "stock",
        editable: true,
        align: "center",
        width: 80,
      },
      {
        title: "??????",
        dataIndex: "price",
        editable: true,
        align: "center",
        width: 100,
      },
      {
        title: "????????????",
        dataIndex: "planeId",
        editable: true,
        align: "center",
        width: 80,
      },
      {
        title: "??????",
        dataIndex: "operation",
        width: 80,
        fixed: "right",
        render: (_, record) =>
          this.state.dataSource.length >= 1 ? (
            <Popconfirm
              title="?????????????"
              onConfirm={() => this.handleDelete(record.key)}
            >
              <a>??????</a>
            </Popconfirm>
          ) : null,
      },
    ];
    this.state = {
      dataSource: [],
      count: 0,
      isModalVisible: false,
    };
  }
  componentDidMount = () => {
    //??????????????????????????????????????????
    axios
      .get("http://121.5.237.69/backEnd/flight", {
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
            res.data.infoMap.flights.map((item) => {
              data.push({
                key: item.id,
                id: data.length + 1,
                name: item.name,
                leaveTime: item.leaveTime,
                arriveTime: item.arriveTime,
                leaveAirportName: item.leaveAirportName,
                arriveAirportName: item.arriveAirportName,
                stock: item.stock,
                price: item.price,
                planeId: item.plane.id,
              });
            });
          }
          this.setState({
            dataSource: data,
            count: res.data.infoMap.total,
          });
        } else {
          this.props.history.push("login");
          message.warning("??????????????????");
        }
      })
      .catch(function (error) {
        console.log(error);
      });

    //?????????????????????
    axios
      .get("http://121.5.237.69/backEnd/airports", {
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
            res.data.infoMap.airports.map((item) => {
              data.push({
                name: item.name,
              });
            });
          }
          this.setState({
            airportList: data,
            count: res.data.infoMap.total,
          });
        } else {
          message.warning("??????????????????");
        }
      })
      .catch(function (error) {
        console.log(error);
      });
    //???????????????????????????
    axios
      .get("http://121.5.237.69/backEnd/planes", {
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
            res.data.infoMap.planes.map((item) => {
              data.push({
                id: item.id,
                name: item.name,
              });
            });
          }
          this.setState({
            planesList: data,
            count: res.data.infoMap.total,
          });
        } else {
          message.warning("??????????????????");
        }
      })
      .catch(function (error) {
        console.log(error);
      });
  };

  handleDelete = (key) => {
    console.log(key);
    axios({
      method: "delete",
      url: "http://121.5.237.69/backEnd/flight/" + key,
      headers: {
        bear: sessionStorage.getItem("bear"),
      },
    })
      .then((res) => {
        //console.log(res.data)
        if ((res.data.code = 200)) {
          message.success("????????????");
        } else {
          message.warning("????????????");
        }
      })
      .catch((error) => {
        console.log(error);
      });
    const dataSource = [...this.state.dataSource];
    this.setState({
      dataSource: dataSource.filter((item) => item.key !== key),
    });
  };

  handleAdd = () => {
    //?????????????????????
    this.setState({
      isModalVisible: true,
    });
  };
  //????????????????????????
  handleSave = (row) => {
    console.log(row);
    axios.defaults.withCredentials = true;
    axios({
      method: "post",
      url: "http://121.5.237.69/backEnd/flight",
      headers: {
        bear: sessionStorage.getItem("bear"),
      },
      params: {
        id: row.key,
        name: row.name,
        leaveTime: row.leaveTime,
        arriveTime: row.arriveTime,
        leaveAirportName: row.leaveAirportName,
        arriveAirportName: row.arriveAirportName,
        stock: row.stock,
        price: row.price,
        planeId: row.planeId,
      },
    })
      .then((res) => {
        //console.log(res.data)
        if ((res.data.code = 200)) {
          message.success("????????????");
        } else {
          message.warning("????????????");
        }
      })
      .catch((error) => {
        console.log(error);
      });
    const newData = [...this.state.dataSource];
    const index = newData.findIndex((item) => row.key === item.key);
    const item = newData[index];
    newData.splice(index, 1, { ...item, ...row });
    this.setState({
      dataSource: newData,
    });
  };
  //???????????????????????????

  //???????????????
  handleOk = () => {
    this.setState({
      isModalVisible: false,
    });
  };
  //???????????????
  handleCancel = () => {
    this.setState({
      isModalVisible: false,
    });
  };

  onFinish = (values) => {
    axios({
      method: "put",
      url: "http://121.5.237.69/backEnd/flight",
      headers: {
        bear: sessionStorage.getItem("bear"),
      },
      params: {
        name: values.name,
        leaveTime: moment(values.time[0]).format("YYYY-MM-DD HH:mm:ss"),
        arriveTime: moment(values.time[1]).format("YYYY-MM-DD HH:mm:ss"),
        leaveAirportName: values.leaveAirportName,
        arriveAirportName: values.arriveAirportName,
        stock: values.stock,
        price: values.price,
        "plane.id": values.planeId,
      },
    })
      .then((res) => {
        //console.log(res.data)
        if ((res.data.code = 200)) {
          
          this.setState({
            isModalVisible: false,
          });
          window.location.reload()
          message.success("????????????");
        } else {
          message.warning(res.data.msg);
        }
      })
      .catch((error) => {
        console.log(error);
      });
  };

  render() {
    //?????????????????????????????????????????????,select????????????????????????Form.item,????????????????????????,???????????????????????????
    const formRef = React.createRef();

    const onAirportChange1 = (value) => {
      this.formRef.current.setFieldsValue({
        leaveAirportName: value,
      });
      return;
    };
    const onAirportChange2 = (value) => {
      this.formRef.current.setFieldsValue({
        arriveAirportName: value,
      });
      return;
    };
    const onplaneIdChange = (value) => {
      this.formRef.current.setFieldsValue({
        planeId: value,
      });
      return;
    };

    ///////////////
    const { dataSource } = this.state;
    const components = {
      body: {
        row: EditableRow,
        cell: EditableCell,
      },
    };
    const columns = this.columns.map((col) => {
      if (!col.editable) {
        return col;
      }

      return {
        ...col,
        onCell: (record) => ({
          record,
          editable: col.editable,
          dataIndex: col.dataIndex,
          title: col.title,
          handleSave: this.handleSave,
        }),
      };
    });
    return (
      <div>
        <Button
          onClick={this.handleAdd}
          type="primary"
          style={{
            marginBottom: 16,
          }}
        >
          ????????????
        </Button>
        <Table
          components={components}
          rowClassName={() => "editable-row"}
          bordered
          dataSource={dataSource}
          columns={columns}
          scroll={{ x: 1500, y: 300 }}
          pagination={{ pageSize: 5 }}
        />
        <Modal
          title="????????????"
          visible={this.state.isModalVisible}
          onOk={this.handleOk}
          onCancel={this.handleCancel}
          footer={null}
        >
          <Form name="flight_add" onFinish={this.onFinish}>
            <Row>
              <Col
                xs={{ span: 24 }}
                sm={{ span: 24 }}
                md={{ span: 24 }}
                lg={{ span: 7 }}
                xl={{ span: 7 }}
              >
                <span className="label">????????????</span>
                <Form.Item
                  name="name"
                  rules={[
                    {
                      required: true,
                      message: "?????????????????????!",
                    },
                  ]}
                  hasFeedback
                >
                  <Input
                    prefix={<RocketOutlined className="site-form-item-icon" />}
                    placeholder="Username"
                    allowClear
                  />
                </Form.Item>
              </Col>
              <Col
                xs={{ span: 24 }}
                sm={{ span: 24 }}
                md={{ span: 24 }}
                lg={{ span: 5, offset: 1 }}
                xl={{ span: 5, offset: 1 }}
              >
                <span className="label">????????????</span>
                <Form.Item
                  name="planeId"
                  rules={[
                    {
                      required: true,
                      message: "?????????????????????!",
                    },
                  ]}
                >
                  <Select onChange={this.onplaneIdChange}>
                    {!this.state.planesList ? (
                      <Select.Option value="demo">????????????</Select.Option>
                    ) : (
                      this.state.planesList.map((item) => (
                        <Select.Option value={item.id}>
                          {item.name}
                        </Select.Option>
                      ))
                    )}
                  </Select>
                </Form.Item>
              </Col>
              <Col
                xs={{ span: 24 }}
                sm={{ span: 24 }}
                md={{ span: 24 }}
                lg={{ span: 4, offset: 1 }}
                xl={{ span: 4, offset: 1 }}
              >
                <span className="label">??????</span>
                <Form.Item
                  name="stock"
                  rules={[
                    {
                      required: true,
                      message: "???????????????!",
                    },
                  ]}
                >
                  <InputNumber
                    prefix={
                      <DashboardOutlined className="site-form-item-icon" />
                    }
                    placeholder="??????"
                    allowClear
                  />
                </Form.Item>
              </Col>
              <Col
                xs={{ span: 24 }}
                sm={{ span: 24 }}
                md={{ span: 24 }}
                lg={{ span: 4, offset: 1 }}
                xl={{ span: 4, offset: 1 }}
              >
                <span className="label">??????</span>
                <Form.Item
                  name="price"
                  rules={[
                    {
                      required: true,
                      message: "???????????????!",
                    },
                  ]}
                >
                  <InputNumber
                    prefix={
                      <PayCircleOutlined className="site-form-item-icon" />
                    }
                    placeholder="??????"
                    allowClear
                  />
                </Form.Item>
              </Col>
            </Row>

            <Row>
              <Col
                xs={{ span: 24 }}
                sm={{ span: 24 }}
                md={{ span: 24 }}
                lg={{ span: 11 }}
                xl={{ span: 11 }}
              >
                <span className="label">????????????</span>
                <Form.Item
                  name="leaveAirportName"
                  rules={[
                    {
                      required: true,
                      message: "?????????????????????!",
                    },
                  ]}
                >
                  <Select onChange={this.onAirportChange1}>
                    {!this.state.airportList ? (
                      <Select.Option value="demo">????????????</Select.Option>
                    ) : (
                      this.state.airportList.map((item) => (
                        <Select.Option value={item.name}>
                          {item.name}
                        </Select.Option>
                      ))
                    )}
                  </Select>
                </Form.Item>
              </Col>
              <Col
                xs={{ span: 24 }}
                sm={{ span: 24 }}
                md={{ span: 24 }}
                lg={{ span: 11, offset: 2 }}
                xl={{ span: 11, offset: 2 }}
              >
                <span className="label">????????????</span>
                <Form.Item
                  name="arriveAirportName"
                  rules={[
                    {
                      required: true,
                      message: "?????????????????????!",
                    },
                  ]}
                >
                  <Select onChange={this.onAirportChange2}>
                    {!this.state.airportList ? (
                      <Select.Option value="demo">????????????</Select.Option>
                    ) : (
                      this.state.airportList.map((item) => (
                        <Select.Option value={item.name}>
                          {item.name}
                        </Select.Option>
                      ))
                    )}
                  </Select>
                </Form.Item>
              </Col>
            </Row>
            <Row>
              <Col span={24}>
                <span className="label">????????????-????????????</span>
                <Form.Item
                  name="time"
                  rules={[
                    {
                      required: true,
                      message: "???????????????!",
                    },
                  ]}
                >
                  <DatePicker.RangePicker showTime style={{ width: "100%" }} />
                </Form.Item>
              </Col>
            </Row>
            <Form.Item className="searchform">
              <Divider>
                <Button htmlType="submit">????????????</Button>
              </Divider>
            </Form.Item>
          </Form>
        </Modal>
      </div>
    );
  }
}
