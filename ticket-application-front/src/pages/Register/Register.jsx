import React from "react";
import {
  Row,
  Col,
  Image,
  Form,
  message,
  Input,
  Select,
  Upload,
  Button,
  InputNumber
} from "antd";
import "./../../static/style/pages/Register.css";
import axios from "axios";
import {
  UserOutlined,
  MailOutlined,
  LoadingOutlined,
  PlusOutlined,
  LockOutlined,
} from "@ant-design/icons";

function getBase64(img, callback) {
  const reader = new FileReader();
  reader.addEventListener("load", () => callback(reader.result));
  reader.readAsDataURL(img);
}

function beforeUpload(file) {
  const isJpgOrPng = file.type === "image/jpeg" || file.type === "image/png";
  if (!isJpgOrPng) {
    message.error("You can only upload JPG/PNG file!");
  }
  const isLt16M = file.size / 1024 / 1024 < 16;
  if (!isLt16M) {
    message.error("Image must smaller than 16MB!");
  }
  return isJpgOrPng && isLt16M;
}

const normFile = (e) => {
  console.log("Upload event:", e);

  if (Array.isArray(e)) {
    return e;
  }

  return e && e.fileList;
};

class Login extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      loading: false,
    };
  }

  handleChange = (info) => {
    if (info.file.status === "uploading") {
      this.setState({ loading: true });
      return;
    }
    if (info.file.status === "done") {
      // Get this url from response in real world.
      console.log("123axc")
      console.log(info.file.originFileObj)
      getBase64(info.file.originFileObj, (imageUrl) =>
        this.setState({
          imageUrl,
          loading: false,
        })
      );
    }
  };

  render() {
    const { loading, imageUrl } = this.state;

    const uploadButton = (
      <div>
        {loading ? <LoadingOutlined /> : <PlusOutlined />}
        <div style={{ marginTop: 8 }}>Upload</div>
      </div>
    );

    // 提交表单
    const onFinish = (values) => {
      console.log(values);
    };

    //这两行是性别选择框的,select的值不会直接给到Form.item,读不到下拉框的值,所以要通过这个函数
    const formRef = React.createRef();
    const onGenderChange = (value) => {
    switch (value) {
      case 'M':
        this.formRef.current.setFieldsValue({
          gender: 'M',
        });
        return;

      case 'F':
        this.formRef.current.setFieldsValue({
          gender: 'F',
        });
        return;
    }
  };
    return (
      <div id="app">
        <div className="container">
          <div className="login-brand">
            <Image
              width={100}
              src="https://gw.alipayobjects.com/zos/rmsportal/KDpgvguMpGfqaHPjicRK.svg"
              style={{ borderRadius: "100%" }}
            />
          </div>
          <div className="card" style={{ backgroundColor: "white" }}>
            <div className="card-header">
              <h4>注册</h4>
            </div>
            <div className="card-body">
              <Form
                name="register"
                size="large"
                initialValues={{
                  remember: true,
                }}
                onFinish={onFinish}
              >
                <Row style={{ marginLeft: "25px", marginRight: "25px" }}>
                  <Col
                    xs={{ span: 24 }}
                    sm={{ span: 24 }}
                    md={{ span: 24 }}
                    lg={{ span: 11 }}
                    xl={{ span: 11 }}
                  >
                    <span className="label">昵称</span>
                    <Form.Item
                      name="username"
                      hasFeedback
                      rules={[
                        {
                          required: true,
                          pattern: "^[0-9a-zA-Z]{6,10}$",
                          message: "用户名应为六位的字母和数字的组合！",
                        },
                      ]}
                    >
                      <Input
                        allowClear
                        prefix={
                          <UserOutlined className="site-form-item-icon" />
                        }
                      />
                    </Form.Item>
                  </Col>
                  <Col
                    xs={{ span: 24 }}
                    sm={{ span: 24 }}
                    md={{ span: 24 }}
                    lg={{ span: 11, offset: 1 }}
                    xl={{ span: 11, offset: 1 }}
                  >
                    <span className="label">邮箱</span>
                    <Form.Item
                      name="email"
                      hasFeedback
                      rules={[
                        {
                          required: true,
                          message: "请输入邮箱地址！",
                        },
                      ]}
                    >
                      <Input
                        allowClear
                        prefix={
                          <MailOutlined className="site-form-item-icon" />
                        }
                        addonAfter={
                          <Select
                            defaultValue="@gmail.com"
                            className="select-after"
                          >
                            <Select.Option value="@163.com">
                              @163.com
                            </Select.Option>
                            <Select.Option value="@qq.com">
                              @qq.com
                            </Select.Option>
                            <Select.Option value="@126.com">
                              @126.com
                            </Select.Option>
                            <Select.Option value="@icloud.com">
                              @icloud.com
                            </Select.Option>
                            <Select.Option value="@126.com">
                              @126.com
                            </Select.Option>
                            <Select.Option value="@outlook.com">
                              @outlook.com
                            </Select.Option>
                            <Select.Option value="@sina.com">
                              @sina.comn
                            </Select.Option>
                            <Select.Option value="@foxmail.com">
                              @foxmail.com
                            </Select.Option>
                          </Select>
                        }
                      />
                    </Form.Item>
                  </Col>
                </Row>
                {/* 第二行 */}
                <Row style={{ marginLeft: "25px", marginRight: "25px" }}>
                  <Col
                    xs={{ span: 24 }}
                    sm={{ span: 24 }}
                    md={{ span: 24 }}
                    lg={{ span: 11 }}
                    xl={{ span: 11 }}
                  >
                    <span className="label">密码</span>
                    <Form.Item
                      name="password"
                      hasFeedback
                      rules={[
                        {
                          required: true,
                          pattern:
                            "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}$",
                          message: "长度为6~12位，必须包含字母和数字！",
                        },
                      ]}
                    >
                      <Input.Password
                        allowClear
                        prefix={
                          <LockOutlined className="site-form-item-icon" />
                        }
                      />
                    </Form.Item>
                  </Col>
                  <Col
                    xs={{ span: 24 }}
                    sm={{ span: 24 }}
                    md={{ span: 24 }}
                    lg={{ span: 11, offset: 1 }}
                    xl={{ span: 11, offset: 1 }}
                  >
                    <span className="label">确认密码</span>
                    <Form.Item
                      name="confirm"
                      dependencies={["password"]}
                      hasFeedback
                      rules={[
                        ({ getFieldValue }) => ({
                          validator(_, value) {
                            if (!value || getFieldValue("password") === value) {
                              return Promise.resolve();
                            }
                            return Promise.reject(
                              new Error("两次输入的密码不一致")
                            );
                          },
                        }),
                      ]}
                    >
                      <Input.Password
                        allowClear
                        prefix={
                          <LockOutlined className="site-form-item-icon" />
                        }
                      />
                    </Form.Item>
                  </Col>
                </Row>
                {/* 第三行 */}
                <Row style={{ marginLeft: "25px", marginRight: "25px" }}>
                  <Col
                    xs={{ span: 24 }}
                    sm={{ span: 24 }}
                    md={{ span: 24 }}
                    lg={{ span: 11 }}
                    xl={{ span: 11 }}
                  >
                    <span className="label">姓</span>
                    <Form.Item
                      name="lastName"
                      hasFeedback
                      rules={[
                        {
                          required: true,
                          pattern: "[\u4E00-\u9FA5A-Za-z0-9]{1,10}$",
                          message: "中文或英文，长度范围在1~10！",
                        },
                      ]}
                    >
                      <Input allowClear />
                    </Form.Item>
                  </Col>
                  <Col
                    xs={{ span: 24 }}
                    sm={{ span: 24 }}
                    md={{ span: 24 }}
                    lg={{ span: 11, offset: 1 }}
                    xl={{ span: 11, offset: 1 }}
                  >
                    <span className="label">名</span>
                    <Form.Item
                      name="firstName"
                      hasFeedback
                      rules={[
                        {
                          required: true,
                          pattern: "[\u4E00-\u9FA5A-Za-z0-9]{1,10}$",
                          message: "中文或英文，长度范围在1~10！",
                        },
                      ]}
                    >
                      <Input allowClear />
                    </Form.Item>
                  </Col>
                </Row>
                {/* 第三四行 */}
                <Row style={{ marginLeft: "25px", marginRight: "25px" }}>
                  <Col
                    xs={{ span: 24 }}
                    sm={{ span: 24 }}
                    md={{ span: 24 }}
                    lg={{ span: 11 }}
                    xl={{ span: 11 }}
                  >
                    <span className="label">年龄</span>
                    <Form.Item
                      name="age"
                      hasFeedback
                      rules={[
                        {
                          required: true,
                          type:"number",
                          min: 10,
                          max: 150,
                          message: "年龄必须是数字，范围10~150",
                        },
                      ]}
                    >
                      <InputNumber min={1} max={150} allowClear style={{width:'100%'}}/>
                    </Form.Item>
                  </Col>
                  <Col
                    xs={{ span: 24 }}
                    sm={{ span: 24 }}
                    md={{ span: 24 }}
                    lg={{ span: 11, offset: 1 }}
                    xl={{ span: 11, offset: 1 }}
                  >
                    <span className="label">性别</span>
                    <Form.Item
                      name="gender"
                      hasFeedback
                      rules={[
                        {
                          required: true,
                          message: "请选择您的性别！",
                        },
                      ]}
                    >
                      <Select
                        allowClear
                        defaultValue="M"
                        onChange={this.onGenderChange}
                      >
                        <Select.Option value="M">男</Select.Option>
                        <Select.Option value="F">女</Select.Option>
                      </Select>
                    </Form.Item>
                  </Col>
                </Row>
                {/* 头像选择 */}
                <Row style={{ margin: "0px 25px" }}>
                  <Col span={12} className="label">
                    头像
                  </Col>
                </Row>
                <Row>
                  <Col span={24} flex style={{ textAlign: "center" }}>
                    <Form.Item>
                      <Form.Item
                        name="headImg"
                        valuePropName="fileList"
                        getValueFromEvent={normFile}
                        noStyle
                      >
                        <Upload
                          name="headImg"
                          listType="picture-card"
                          className="avatar-uploader"
                          showUploadList={false}
                          action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
                          beforeUpload={beforeUpload}
                          onChange={this.handleChange}
                        >
                          {imageUrl ? (
                            <img
                              src={imageUrl}
                              alt="avatar"
                              style={{ width: "100%" }}
                            />
                          ) : (
                            uploadButton
                          )}
                        </Upload>
                      </Form.Item>
                    </Form.Item>
                  </Col>
                </Row>
                {/* 注册按钮 */}
                <Row>
                  <Col span={1}></Col>
                  <Col span={22} flex style={{ textAlign: "center" }}>
                    <Form.Item>
                      <Button type="primary" htmlType="submit" block>
                        注册
                      </Button>
                    </Form.Item>
                  </Col>
                  <Col span={1}></Col>
                </Row>
              </Form>
            </div>
          </div>
          <div
            classNamae="under-card"
            style={{ textAlign: "center", marginTop: 10 }}
          >
            已经有账号？<a href="#/login">马上登录👉</a>
          </div>
        </div>
      </div>
    );
  }
}

export default Login; // this.props.form才可以取到
