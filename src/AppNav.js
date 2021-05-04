import React, {Component} from 'react';
import {Navbar, NavbarBrand, Nav, NavItem, NavLink, Button} from 'reactstrap';
import Modal from "react-bootstrap/Modal";

class AppNav extends Component {
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
            <div>
                <Navbar color="dark" dark expand="md">
                    <NavbarBrand href="/">Parking Manager</NavbarBrand>
                    <Nav style={{position: 'absolute', left: '50%', transform: 'translate(-50%)'}} navbar>
                        <NavItem>
                            <NavLink href="/home">Home</NavLink>
                        </NavItem>
                        <NavItem>
                            <NavLink href="/addclient">Add Guest</NavLink>
                        </NavItem>
                    </Nav>
                    <Button size="sm" className="ml-auto" color="danger" onClick={() => this.openLogoutDialog()}>
                        Logout
                    </Button>
                </Navbar>
                <Modal show={showLogoutDialog} onHide={() => this.closeLogoutDialog()}>
                    <Modal.Header closeButton>
                        <Modal.Title>Delete Guest</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>Are you sure you want to logout?</Modal.Body>
                    <Modal.Footer>
                        <Button color="danger" onClick={() => this.logout()}>
                            Logout
                        </Button>
                        <Button color="primary" onClick={() => this.closeLogoutDialog()}>
                            Cancel
                        </Button>
                    </Modal.Footer>
                </Modal>
            </div>
        );
    }
}

export default AppNav;
