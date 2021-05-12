import React from "react";
import {
  Card,
  Form,
  Input,
  message,
  Button,
  Checkbox,
  Space,
  Image
} from "antd";
import "./../../static/style/pages/Login.css";
import axios from 'axios';
import { UserOutlined, LockOutlined , BellOutlined ,EyeInvisibleOutlined, EyeTwoTone } from "@ant-design/icons";
import imgURL from '../../static/images/横幅.png';
const FormItem = Form.Item;

class Login extends React.Component {
  constructor(props) {
    super(props);
    console.log("qiguai")
    console.log(this.props)
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
            params: { username: values.username,
              password: values.password,
              code: values.code },
          })
          .then((res) => {
            if(res.data != null){
              if(res.data.msg=="账号或者密码错误"){
                message.error(res.data.msg);
              }
              else if(res.data.msg=="验证码错误"){
                message.error(res.data.msg);
              }
              else{
                sessionStorage.setItem('username',values.username);
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
      
      message.info('请联系系统管理员！');
      console.log("213");
      
      return false;
    };
    
    //点击验证码时候更新验证码
    const changeCode = (e) => {
      
      var date = new Date().getTime();
      e.target.src = "http://localhost:8080/blog/user/verifycode?"+date;
      console.log(e.target);
      
    };

    return (
      <div className="login-body">
        <duv className="login-wrapper">
          <div className="site-card-border-less-wrapper">
            <Card
              title={
                <div className="svg">
                  <Image
                  width={150}
                  src={imgURL}
                />
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
                      min:4,
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

                {/* 密码框 */}
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
                  <Input.Password
                    prefix={<LockOutlined className="site-form-item-icon" />}
                    type="password"
                    allowClear 
                    placeholder="Password"
                    iconRender={visible => (visible ? <EyeTwoTone /> : <EyeInvisibleOutlined />)}
                  />
                </Form.Item>

                {/* 验证码框 */}
                <Form.Item
                  name="code"
                  rules={[
                    {
                      required: true,
                      message: "Please input code!",
                    },

                  ]}
                >
                  <Space>
                  <Input
                    prefix={<BellOutlined />}
                    placeholder="verify-code"
                    className="login-form-code"
                  />
                  <img id="checkCode" onClick={changeCode} src="http://localhost:8080/blog/user/verifycode" />
                  </Space>
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
                <div style={{textAlign:'center'}}>
                  还没有账号？<a href="#/register">去注册</a>
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
