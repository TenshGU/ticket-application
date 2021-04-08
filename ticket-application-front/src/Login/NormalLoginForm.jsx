/*eslint-disable */
import React from "react";
import {
  Card,
  Form,
  Input,
  message,
  Button,
  Checkbox,
  Col,
  Icon,
  Modal,
} from "antd";
import "./NormalLoginForm.css";
import md5 from "md5";
import { UserOutlined, LockOutlined } from "@ant-design/icons";
const FormItem = Form.Item;

class NormalLoginForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      userId: "",
      password: "",
      data: "",
    };
  }

  componentDidMount(e) {
    // 一进入登录界面就将jwtToken、userId置为空 --- 风险评估的sessionStorage也置为空
    localStorage.setItem("jwtToken", "");
    localStorage.setItem("userId", "");
    sessionStorage.setItem("riskTraceId", "");
    sessionStorage.setItem("riskEventName", "");
    localStorage.setItem("timeCrossing", "");
    if (e != null && !e) {
      this.setState({
        forgetPasswordVisible: true,
      });
    } else {
      this.setState({
        forgetPasswordVisible: false,
      });
    }
  }

  handleSubmit = (e) => {
    this.props.form.validateFields((err, values) => {
      if (!err) {
        let user = {
          username: this.props.form.getFieldValue("username"),
          password: this.props.form.getFieldValue("password"),
        };
        user.password = values.password ? md5(values.password) : "";
        this.$post("/login", user)
          .then((res) => {
            if (res.data != null) {
              if (res.data.event === 0) {
                if (res.data.obj != null) {
                  localStorage.setItem("jwtToken", res.data.obj.jwtToken);
                  localStorage.setItem("username", res.data.obj.username);
                  localStorage.setItem("userId", res.data.obj.userId);
                  if (res.data.obj.timeCrossing === 1) {
                    localStorage.setItem("endtime", res.data.obj.endtime);
                  } else {
                    localStorage.setItem("endtime", "-1");
                  }
                  localStorage.setItem(
                    "timeCrossing",
                    res.data.obj.timeCrossing
                  );
                } else {
                  message.error("平台错误");
                }
                this.props.history.push("/home");
              } else {
                message.error(res.data.msg);
              }
            }
          })
          .catch((e) => {
            console.log(e);
          });
      }
    });
  };

  render() {
    const onFinish = (values) => {
      console.log(values.username);
      console.log(values.password);
      console.log("Received values of form: ", values);
    };


    const forgetPW = (e) => {
      
      message.info('请联系系统管理员！');
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
                  <svg
                    t="1617780748178"
                    class="icon"
                    viewBox="0 0 1654 1024"
                    version="1.1"
                    xmlns="http://www.w3.org/2000/svg"
                    p-id="1394"
                    width="50"
                    height="50"
                  >
                    <path
                      d="M488.448 45.134769c131.032615-32.295385 286.404923-11.145846 442.407385 39.620923a49.230769 49.230769 0 0 1-30.483693 93.617231c-75.854769-24.694154-151.473231-41.550769-222.208-47.576615l-14.926769-1.10277 34.422154 19.771077a49.230769 49.230769 0 0 1 20.637538 62.424616l-2.441846 4.804923a49.230769 49.230769 0 0 1-62.385231 20.598154l-4.804923-2.402462-172.898461-99.249231c-38.518154-22.055385-30.404923-79.872 12.681846-90.505846z"
                      fill="#5AC2EE"
                      p-id="1395"
                    ></path>
                    <path
                      d="M1419.500308 75.697231a819.987692 819.987692 0 0 0-130.835693 12.878769c-12.130462 2.087385-23.630769 4.332308-34.343384 6.577231l-15.123693 3.308307-13.115076 3.15077L373.444923 314.88 89.954462 204.051692a49.230769 49.230769 0 0 0-49.624616 8.192l-17.998769 15.123693-3.938462 3.702153A49.230769 49.230769 0 0 0 16.541538 296.999385l131.072 153.639384-74.043076 19.534769a49.230769 49.230769 0 0 0-34.658462 61.44l11.697231 40.054154c4.608 15.635692 16.620308 27.963077 32.177231 33.004308 59.707077 19.219692 130.048 29.184 209.053538 31.468308 87.276308 2.520615 180.145231-4.411077 272.068923-17.447385 22.094769-3.150769 42.850462-6.459077 61.952-9.806769l31.783385-5.907692c4.923077-0.945231 8.782769-1.772308 11.539692-2.363077l16.738462-4.096-116.696616 317.164307c-4.332308 11.815385-4.017231 24.851692 0.945231 36.43077 4.135385 9.609846 11.342769 21.110154 22.843077 32.492307 63.566769 62.542769 159.901538 35.170462 274.628923-100.233846l2.678154-3.426461 279.394461-395.776 389.356308-101.45477c99.170462-26.702769 130.599385-136.861538 99.091692-221.341538a49.230769 49.230769 0 0 0-9.452307-15.635692c-16.068923-17.92-41.117538-33.949538-71.916308-46.985847-37.730462-16.029538-83.968-22.567385-137.334154-22.055384z m0.94523 98.461538c40.999385-0.393846 74.712615 4.371692 97.910154 14.217846l6.73477 2.993231c8.664615 4.017231 15.872 8.073846 21.307076 11.736616l2.56 1.811692 0.275693 1.102769c7.483077 34.264615-6.144 72.664615-35.328 80.502154l-406.449231 105.944615-5.474462 1.811693a49.230769 49.230769 0 0 0-22.291692 17.447384l-288.374154 408.418462 1.260308-1.417846c-49.073231 57.895385-87.433846 87.355077-113.742769 94.838153l-5.041231 1.181539 136.428308-370.609231a49.230769 49.230769 0 0 0-57.737846-64.905846l-105.235693 25.403077-15.202461 3.032615-10.003693 1.890462a1764.430769 1764.430769 0 0 1-71.916307 11.657846c-86.843077 12.327385-174.395077 18.825846-255.448616 16.502154l-17.408-0.630154c-17.211077-0.787692-33.870769-2.008615-49.900307-3.662769l-5.316923-0.590769 26.466461-6.931693 4.647385-1.457231a49.230769 49.230769 0 0 0 20.243692-78.099692L190.385231 349.026462l161.870769 63.291076c9.491692 3.741538 19.928615 4.371692 29.853538 1.890462l875.638154-218.978462 7.483077-1.693538a879.064615 879.064615 0 0 1 40.251077-7.955692 724.873846 724.873846 0 0 1 115.003077-11.421539z"
                      fill="#5AC2EE"
                      p-id="1396"
                    ></path>
                    <path
                      d="M1574.596923 130.520615a49.230769 49.230769 0 0 1 35.84 91.569231l-4.726154 1.851077-147.692307 49.230769a49.230769 49.230769 0 0 1-35.84-91.56923l4.726153-1.851077 147.692308-49.23077z"
                      fill="#5AC2EE"
                      p-id="1397"
                    ></path>
                  </svg>
                  <p className="svg-p">模拟售票系统</p>
                </div>
              }
              bordered={true}
              style={{ width: 300 }}
            >
              <Form
                name="normal_login"
                className="login-form"
                initialValues={{
                  remember: true,
                }}
                onFinish={onFinish}
              >
                <Form.Item
                  name="username"
                  rules={[
                    {
                      required: true,
                      min:6,
                      max:10,
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
                <Form.Item
                  name="password"
                  hasFeedback
                  rules={[
                    {
                      required: true,
                      message: "Please input your Password!",
                    },

                  ]}
                >
                  <Input
                    prefix={<LockOutlined className="site-form-item-icon" />}
                    type="password"
                    allowClear 
                    placeholder="Password"
                  />
                </Form.Item>
                <Form.Item>
                  <Form.Item name="remember" valuePropName="checked" noStyle>
                    <Checkbox>记住我</Checkbox>
                  </Form.Item>

                  <a href="javascript:void(0);" className="login-form-forgot" onClick={forgetPW}>
                    忘记密码
                  </a>
                </Form.Item>

                <Form.Item>
                  <Button
                    type="primary"
                    htmlType="submit"
                    className="login-form-button"
                  >
                    登录
                  </Button>
                </Form.Item>
                <a href="##" className="register-button">注册!</a>
              </Form>
            </Card>
          </div>
        </duv>
      </div>
    );
  }
}

export default NormalLoginForm; // this.props.form才可以取到
