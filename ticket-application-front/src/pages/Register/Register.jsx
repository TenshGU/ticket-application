import React from "react";
import {
  Card,
  Form,
  Input,
  message,
  Button,
  Checkbox,
  Space,
  Image,
} from "antd";
import "./../../static/style/pages/Register.css";
import axios from "axios";
import {
  UserOutlined,
  LockOutlined,
  BellOutlined,
  EyeInvisibleOutlined,
  EyeTwoTone,
} from "@ant-design/icons";
import imgURL from "../../static/images/横幅.png";
const FormItem = Form.Item;

class Login extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      username: "",
      password: "",
      data: "",
    };
  }

  render() {
    const onFinish = (values) => {
      axios
        .get("http://localhost:8080/blog/user/login", {
          params: {
            username: values.username,
            password: values.password,
            code: values.code,
          },
        })
        .then((res) => {
          if (res.data != null) {
            if (res.data.msg == "账号或者密码错误") {
              message.error(res.data.msg);
            } else if (res.data.msg == "验证码错误") {
              message.error(res.data.msg);
            } else {
              sessionStorage.setItem("username", values.username);
              this.props.history.push("/home/index");
            }
          }
          console.log(res);
        })
        .catch((e) => {
          console.log(e);
        });
    };

    const forgetPW = (e) => {
      message.info("请联系系统管理员！");
      console.log("213");

      return false;
    };

    return (
      <div className="login-body">
        <duv className="login-wrapper">
          <div className="site-card-border-less-wrapper">
            <Card
              title={
                <div className="svg">
                  <Image width={150} src={imgURL} />
                </div>
              }
              bordered={true}
              style={{ width: 300 }}
            >
              <Form
                name="normal_login"
                className="login-form"
                enctype="multipart/form-data"
                initialValues={{
                  remember: true,
                }}
                onFinish={onFinish}
              >
                {/* 账号 */}
                <Form.Item
                  name="username"
                  label="Username"
                  rules={[
                    {
                      required: true,
                      min: 4,
                      max: 10,
                      message: "Please input correct Username!",
                    },
                  ]}
                  hasFeedback
                >
                  <Input
                    prefix={<UserOutlined className="site-form-item-icon" />}
                    placeholder="Username"
                    allowClear
                  />
                </Form.Item>

                {/* 密码框 */}
                <Form.Item
                  name="password"
                  label="Password"
                  hasFeedback
                  rules={[
                    {
                      required: true,
                      message: "Please input your Password!",
                    },
                  ]}
                >
                  <Input.Password
                    prefix={<LockOutlined className="site-form-item-icon" />}
                    type="password"
                    allowClear
                    placeholder="Password"
                    iconRender={(visible) =>
                      visible ? <EyeTwoTone /> : <EyeInvisibleOutlined />
                    }
                  />
                </Form.Item>
                {/* 邮箱 */}
                <Form.Item
                  name="email"
                  label="E-mail"
                  rules={[
                    {
                      type: "email",
                      message: "The input is not valid E-mail!",
                    },
                    {
                      required: true,
                      message: "Please input your E-mail!",
                    },
                  ]}
                >
                  <Input />
                </Form.Item>

                {/* 按钮 */}
                <Form.Item>
                  <Button
                    type="primary"
                    htmlType="submit"
                    className="login-form-button"
                  >
                    注册
                  </Button>
                </Form.Item>
                <div style={{ textAlign: "center" }}>
                  已经有账号？<a href="#/login">去登录</a>
                </div>
              </Form>
            </Card>
          </div>
        </duv>
      </div>
    );
  }
}

export default Login; // this.props.form才可以取到
