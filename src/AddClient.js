import React, { Component } from 'react';
import AppNav from './AppNav';
import './App.css';
import { Container, Form, FormGroup, Input, Label, Button } from 'reactstrap';
//import { Button } from 'bootstrap';
import { Link } from 'react-router-dom';

class AddClient extends Component {

// {
//     "accountId": 1,
//     "licensePlate": "01-VBB-1",
//     "firstName": "Henk",
//     "lastName": "Broers",
//     "phoneNumber": "+310623848125"
// }

    emptyItem={
        accountId: 16,
        licensePlate: '',
        firstName: '',
        lastName: '',
        phoneNumber: ''
    }

    constructor(props){
        super(props)

        this.state = {
            isLoading: true,
            AddClient: [],
            item: this.emptyItem
        }

        this.handleSubmit= this.handleSubmit.bind(this);
        this.handleChange= this.handleChange.bind(this);
    }

    async handleSubmit(event){
        const item = this.state.item;
        await fetch('/account', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item),
        });        
        //console.log(this.state);
        event.preventDefault();
        this.props.history.push("/account");
    }

    handleChange(event){
        const target= event.target;
        const value= target.value;
        const name = target.name;
        let item={...this.state.item};
        item[name] = value;
        this.setState({item});
        console.log(item);
    }

    async componentDidMount() {

        const responseAddC = await fetch('/account');
        const bodyAddC = await responseAddC.json();
        this.setState({AddClient : bodyAddC, isLoading: false});
        console.log(bodyAddC);
    }

    
    render() { 

        const {AddClient, isLoading} = this.state;

        if (isLoading)
            return(<div>Loading...</div>)

        return ( 
            <div><AppNav/>
                <h2 className="text-center mt-5">Add Guest</h2>
                <div className="container">
                    <div className="row">
                        <div className="col"></div>
                        <div className="col">
                            <Container className="mt-5">
                                <Form onSubmit={this.handleSubmit}>
                                    <FormGroup>
                                        <Label for="licensePlate">License Plate</Label>
                                        <Input type="text" name="licensePlate" id="licensePlate" onChange={this.handleChange}></Input>
                                    </FormGroup>
                                    <FormGroup>
                                        <Label for="firstName">First Name</Label>
                                        <Input type="text" name="firstName" id="firstName" onChange={this.handleChange}></Input>
                                    </FormGroup>
                                    <FormGroup>
                                        <Label for="lastName">Last Name</Label>
                                        <Input type="text" name="lastName" id="lastName" onChange={this.handleChange}></Input>
                                    </FormGroup>
                                    <FormGroup>
                                        <Label for="phoneNumber">Phone Number</Label>
                                        <Input type="text" name="phoneNumber" id="phoneNumber" onChange={this.handleChange}></Input>
                                    </FormGroup>
                                    <FormGroup>
                                        <Button color="primary" type="submit" >Save</Button>{' '}
                                        <Button color="secondary" tag={Link} to="/home">Cancel</Button>
                                    </FormGroup>
                                </Form>
                            </Container>
                        </div>
                        <div className="col"></div>
                    </div>
                </div>
            </div> );
    }
}
 
export default AddClient;