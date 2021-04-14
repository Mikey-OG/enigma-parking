import React, { Component } from "react";
import {Table, Button} from "reactstrap";
import AppNav from "./AppNav";
import { Link } from 'react-router-dom';

class GuestList extends Component {

    // state = {
    //     isLoading : true,
    //     Guests : []
    // }

    constructor(props){
        super(props)

        this.state = {
            isLoading: true,
            Guests : []
        }

        this.editClient = this.editClient.bind(this);
    }

    editClient(id){
        this.props.history.push(`/editclient/${id}`);
    }

    async remove(id){
        await fetch(`/account/${id}`, {
          method: 'DELETE',
          headers : {
            'Accept' : 'application/json',
            'Content-Type' : 'application/json'
          }
        }).then(() => {
          let updatedGuests = [...this.state.Guests].filter(i => i.id !== id);
          this.setState({Guests : updatedGuests});
        });
    }

    async componentDidMount(){
        const response = await fetch('/account')
        const body = await response.json();
        this.setState({Guests :body , isLoading: false});
        console.log(body);
    }

    refreshPage() {
        window.location.reload(false);
    }

    render() {
        const {Guests , isLoading} = this.state;
        if (isLoading) {
           return (<div>Loading...</div>)
        }

        let rows=
            Guests.map(guest =>
                <tr id={guest.accountId}>
                    <td>{guest.firstName}</td>
                    <td>{guest.lastName}</td>
                    <td>{guest.licensePlate}</td>
                    <td>{guest.phoneNumber}</td>
                    <td><Button size="sm" color="primary" onClick={() => this.editClient(guest.accountId)}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(guest.accountId)} tag={Link} to="/">Delete</Button></td>
                </tr>
            );

        return (
            <div><AppNav/>
                <h2 className="text-center mt-5">Guest List</h2>
                <div className="container">
                    <div className="row">
                        <div className="col-1"></div>
                        <div className="col-10">                    
                            <Table className="mt-4">
                                <thread>
                                    <tr>
                                        <th>First Name</th>
                                        <th>Last Name</th>
                                        <th>License Plate</th>
                                        <th>Telephone Number</th>
                                        <th>Options</th>
                                    </tr>
                                    <tbody>
                                        {rows}
                                    </tbody>
                                </thread>
                            </Table>
                        </div>
                        <div className="col-1"></div>
                    </div>
                </div>
            </div>
        );
    }
}

export default GuestList;
