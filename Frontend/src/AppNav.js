import React, {Component} from 'react';
import {Navbar, NavbarBrand, Nav, NavItem, NavLink, Button} from 'reactstrap';
import {Link} from 'react-router-dom';
import Modal from "react-bootstrap/Modal";
import { authProvider } from './authProvider';
import { AzureAD, AuthenticationState} from 'react-aad-msal';

class AppNav extends Component {

    // logout = () => {
    //     this.props.logoutUser();
    // };

    constructor(props){
        super(props);

        this.state = {
            showLogoutDialog: false,
        };

        this.openLogoutDialog = this.openLogoutDialog.bind(this);
        this.closeLogoutDialog = this.closeLogoutDialog.bind(this);
    }

    openLogoutDialog(){
        this.setState({showLogoutDialog: true});
    }

    closeLogoutDialog(){
        this.setState({showLogoutDialog: false});
    }

    logout(){
        this.closeLogoutDialog();
    }

    render() {
        const {showLogoutDialog} = this.state;

        return (

            <AzureAD provider={authProvider} forceLogin={true}>
                {
                    ({login, logout, authenticationState, error, accountInfo}) => {
                    switch (authenticationState) {
                        case AuthenticationState.Authenticated:
                        return (
                            <div>
                            <Navbar color="dark" dark expand="md">
                                <NavbarBrand href="/">Parking Manager</NavbarBrand>
                                <Nav style={{position: 'absolute', left: '50%', transform: 'translate(-50%)', overflow: 'hidden'}} navbar>
                                    <NavItem>
                                        <NavLink href="/home">Home</NavLink>
                                    </NavItem>
                                    <NavItem>
                                        <NavLink href="/addGuest">Add Guest</NavLink>
                                    </NavItem>
                                </Nav>
                                <Link to="/" size="sm" className="ml-auto btn btn-danger btn-sm" color="danger" onClick={logout}>
                                    Logout
                                </Link>
                            </Navbar>
                        </div>
                        );
                        case AuthenticationState.Unauthenticated:
                        return (
                            <div>
                            {error && <p><span>An error occured during authentication, please try again!</span></p>}
                            <p>
                                <span>Hey stranger, you look new!</span>
                                <button onClick={login}>Login</button>
                            </p>
                            </div>
                        );
                        case AuthenticationState.InProgress:
                        return (<p>Authenticating...</p>);
                    }
                    }
                }
            </AzureAD>


        );
    }
}

export default AppNav;
