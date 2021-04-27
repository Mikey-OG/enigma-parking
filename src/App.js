import React, { Component } from 'react';
import {Redirect, Route, Switch, BrowserRouter as Router } from 'react-router-dom';
import "react-datetime/css/react-datetime.css";
import AddGuest from './Guests/AddGuest';
import GuestList from './Guests/GuestList';
import EditGuest from './Guests/EditGuest';
import AddAppointment from './Appointments/AddAppointment';
import EditAppointment from './Appointments/EditAppointment';

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
                    <Route path='/addAppointment' exact={true} component={AddAppointment}/>
                    <Route path='/editAppointment/:id' component={EditAppointment}/>
                </Switch>
            </Router>
         );
    }
}
 
export default App;