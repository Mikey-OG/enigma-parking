import React, { Component } from 'react';
import {Redirect, Route, Switch, BrowserRouter as Router } from 'react-router-dom';
import Home from './Home';
import AddClient from './AddClient';
import GuestList from './GuestList';

class App extends Component {
    state = {  }
    render() { 
        return ( 
            <Router>
                <Switch>
                    <Route exact path="/">
                        <Redirect to="/home" />
                    </Route>
                    <Route path='/home' exact={true} component={GuestList}/>
                    <Route path='/addclient' exact={true} component={AddClient}/>
                </Switch>
            </Router>
         );
    }
}
 
export default App;