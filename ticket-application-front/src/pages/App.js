import React, { Component } from 'react';
import { HashRouter, Route, Switch, Redirect } from 'react-router-dom';
import Login from './Login/Login';
import Register from './Register/Register';
import 'antd/dist/antd.css';
import { get, post, showNotification } from './../Api/HttpUtils';
import Home from './Home/Home';
class App extends React.Component {
  render() {
      return (
          <HashRouter>
              <Switch>
                  <Redirect exact from="/" to="/login"></Redirect>
                  <Route path="/home" history={this.props.history}  component={Home}></Route>
                  <Route path="/login" component={Login}></Route> 
                  <Route path="/register" component={Register}></Route> 
                  <Redirect to="/login"></Redirect>
              </Switch>
          </HashRouter>
      )
  }
}
export default App
