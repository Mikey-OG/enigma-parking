import React, { Component } from "react";
import {Table, Button} from "reactstrap";

class GuestList extends Component {
    state = {
        isLoading : true,
        Guests : []
    }

    async componentDidMount() {
        const response=await fetch('/account');
        const body = await response.json();
        this.setState({Guests :body , isLoading: false});
    }

    render() {
        const {Guests , isLoading} = this.state;
        if (isLoading) {
           return (<div>Loading...</div>)
        }

        let rows=
            Guests.map(  guest =>
                <tr id={guest.id}>
                    <td width="20%">{guest.firstName}</td>
                    <td width="20%">{guest.lastName}</td>
                    <td width="20%">{guest.licensePlate}</td>
                    <td width="20%">{guest.phoneNumber}</td>
                    <td width="20%"><Button size="sm" color="primary">Edit</Button><a> </a>
                        <Button size="sm" color="danger">Delete</Button></td>
                </tr>
            );

        return (
            <div>
                <container>
                    <h2>Guest List</h2>
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
                </container>
            </div>
        );
    }
}

export default GuestList;