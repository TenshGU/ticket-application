
import { HashRouter, Route, Switch, Redirect } from 'react-router-dom';
import Login from './Login/NormalLoginForm.jsx';
import React, { Component } from 'react'
import 'antd/dist/antd.css';
import Home from './Home'
class App extends React.Component {
  render() {
      return (
          <HashRouter>
              <Switch>
                  <Redirect exact from="/" to="/login"></Redirect>
                  <Route path="/home" component={Home}></Route>
                  <Route path="/login" component={Login}></Route>
                
              </Switch>
          </HashRouter>
      )
  }
}
export default App
