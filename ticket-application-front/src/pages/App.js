import React, { Component } from 'react';
import { HashRouter, Route, Switch, Redirect } from 'react-router-dom';
import Login from './Login/Login';
import Register from './Register/Register';
import 'antd/dist/antd.css';
import Home from './Home/Home';
import PersonalCenter from './PersonalCenter/PersonalCenter';
import BuyTicket from './BuyTicket/BuyTicket'
import MyOrder from './MyOrder/MyOrder';
class App extends React.Component {
  render() {
      return (
          <HashRouter>
              <Switch>
                  <Redirect exact from="/" to="/login"></Redirect>
                  <Route path="/home" history={this.props.history}  component={Home}></Route>
                  <Route path="/login" component={Login}></Route> 
                  <Route path="/register" component={Register}></Route> 
                  <Route path="/personalCenter" history={this.props.history}  component={PersonalCenter}></Route>
                  <Route path="/buyTicket" history={this.props.history}  component={BuyTicket}></Route>
                  <Route path="/myOrder" history={this.props.history}  component={MyOrder}></Route>
                  <Redirect to="/login"></Redirect>
              </Switch>
          </HashRouter>
      )
  }
}
export default App
