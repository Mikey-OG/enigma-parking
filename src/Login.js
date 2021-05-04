import React, {Component} from 'react';
import AppNav from "./AppNav";
import './App.css';
import { Container, Form, FormGroup, Input, Label, Button } from 'reactstrap';
import axios from 'axios';
import { Link } from 'react-router-dom';

export default class Login extends Component {

    emptyItem={
        username: '',
        password: ''
    }

    constructor(props){
        super(props)

        this.state = {
            isLoading: true,
            Login: [],
            item: this.emptyItem
        }

        this.handleSubmit= this.handleSubmit.bind(this);
        this.handleChange= this.handleChange.bind(this);
    }

    async handleSubmit(event){
        const item = this.state.item;
        await fetch('/authenticate', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item),
        });        
        //console.log(this.state);
        event.preventDefault();
        this.props.history.push("/authenticate");
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

        const responseAddC = await fetch('/authenticate');
        const bodyAddC = await responseAddC.json();
        this.setState({AddClient : bodyAddC, isLoading: false});
        console.log(bodyAddC);
    }

    /*handleSubmit=e=>{
        e.preventDefault();

        const data = {
            email: this.email,
            password: this.password
        }

        axios.post('http://localhost:8081/authenticate', data)
            .then(res =>{
                console.log(res)
            })

            .catch(err =>{
                console.log(err)
            })
    };*/

    render(){

        const {AddClient, isLoading} = this.state;

        if (isLoading)
            return(<div>Loading...</div>)

        return(
            <div><AppNav/>
                <h2 className="text-center mt-5">Log in</h2>
                <div className="container">
                    <div className="row">
                        <div className="col"></div>
                        <div className="col"></div>
                            <Container className="mt-5">
                                <Form onSubmit={this.handleSubmit}>
                                    <FormGroup>
                                        <Label for="username">Username</Label>
                                        <Input type="text" name="username" id="username" onChange={this.handleChange}></Input>
                                    </FormGroup>
                                    <FormGroup>
                                        <Label for="password">Phone Number</Label>
                                        <Input type="password" name="password" id="password" onChange={this.handleChange}></Input>
                                    </FormGroup>
                                    <FormGroup>
                                        <Button color="primary" type="submit" >Log In</Button>{' '}
                                    </FormGroup>

                                    {/* <div className="form-group">
                                        <label>Username</label>
                                        <input type="username" className="form-control" placeholder="Username" id="username" onChange={this.handleChange}/>
                                    </div>

                                    <div className="form-group">
                                        <label>Password</label>
                                        <input type="password" className="form-control" placeholder="Password" id="password" onChange={this.handleChange}/>
                                    </div> */}

                                </Form>
                            </Container>
                        </div>
                        <div className="col"></div>
                    </div>
                </div>
            //</div> 
        );
    }
}