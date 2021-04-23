import React, { Component } from 'react';
import {Redirect, Route, Switch, BrowserRouter as Router } from 'react-router-dom';
import AddGuest from './AddGuest';
import GuestList from './GuestList';
import EditGuest from './EditGuest';

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
                    <Route path='/addGuest' exact={true} component={AddGuest}/>
                    <Route path='/editGuest/:id' component={EditGuest}/>
                </Switch>
            </Router>
         );
    }
}
 
export default App;