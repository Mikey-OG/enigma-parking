import React, { Component } from 'react';
import {Redirect, Route, Switch, BrowserRouter as Router } from 'react-router-dom';
import AddGuest from './AddGuest';
import GuestList from './GuestList';
import EditGuest from './EditGuest';
import {config} from './Config';
import {PublicClientApplication} from '@azure/msal-browser';

class App extends Component {
    state = {  }


    // constructor(props) {
    //     super(props);
    //     this.state = {
    //         error: null,
    //         isAuthenticated: false,
    //         user: {},
    //     };
    //     this.login = this.login.bind(this);
        
    //     // Initialize the MSAL application object
    //     this.publicClientApplication = new PublicClientApplication({
    //         auth: {
    //             clientId: config.appId,
    //             redirectUri: config.redirectUri,
    //             authority: config.authority
    //         },
    //         cache: {
    //             cacheLocation: "sessionStorage",
    //             storeAuthStateInCookie: true
    //         }
    //     });
    // }

    // async login() {
    //     try {
    //         // Login via popup  
    //         await this.publicClientApplication.loginPopup(
    //             {
    //                 scopes: config.scopes,
    //                 prompt: "select_account"
    //             });
    //         this.setState({ isAuthenticated: true })

    //     }
    //     catch (err) {

    //         this.setState({
    //             isAuthenticated: false,
    //             user: {},
    //             error: err
    //         });
    //     }
    // }

    // logout() {
    //     this.publicClientApplication.logout();
    // }

    render() { 
        return (

        //     <div className="App">
        //     <header className="App-header">
        //       {this.state.isAuthenticated ? <p>
        //             Loged in
        //       </p> :
        //         <p>
        //           <button onClick={() => this.login()} >Login in</button>
        //         </p>
    
        //       }
    
        //     </header>
        //   </div>

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