import React, {Component} from 'react';
import AppNav from '../AppNav';
import '../App.css';
import {Container, Form, FormGroup, Input, Label, Button} from 'reactstrap';
import {Link} from 'react-router-dom';
import Datetime from "react-datetime";
import Autosuggest from 'react-autosuggest';

let guests;

const getSuggestions = value => {
    const inputValue = value.trim().toLowerCase();
    const inputLength = inputValue.length;

    return inputLength === 0 ? [] : guests.filter(guest =>
        guest.firstName.toLowerCase().slice(0, inputLength) === inputValue
    );
};

const getSuggestionValue = suggestion => suggestion.firstName + " " + suggestion.lastName;

const renderSuggestion = suggestion => (
    <span id={suggestion.accountId}>
        {suggestion.firstName} {suggestion.lastName}
    </span>
);

class AddAppointment extends Component {

    emptyAppointment = {
        guestId: '',
        employeeEmail: '',
        appointmentDate: '',
    };

    constructor(props) {
        super(props);

        this.state = {
            value: '',
            suggestions: [],
            appointment: this.emptyAppointment
        };

        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleDatetimeChange = this.handleDatetimeChange.bind(this);
    }

    async handleSubmit(event) {
        event.preventDefault();
        const appointment = this.state.appointment;
        await fetch('/appointment', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(appointment),
        }).then((response) => {
            console.log(response);
            if (response.status === 200 || response.status === 201) {
                this.props.history.push("/calender");
            }
        });

    }

    handleInputChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let appointment = {...this.state.appointment};
        appointment[name] = value;
        this.setState({appointment});
    }

    handleDatetimeChange(date) {
        let appointment = {...this.state.appointment};
        appointment.appointmentDate = date.toDate();
        this.setState({appointment});
        console.log(appointment)
    }

    handleSuggestionChange = (event, {newValue}) => {
        this.setState({
            value: newValue
        });
    };

    async componentDidMount() {
        const response = await fetch('/account');
        guests = await response.json();
        this.setState({isLoading: false});
    }

    onSuggestionsFetchRequested = ({value}) => {
        this.setState({
            suggestions: getSuggestions(value)
        });
    };

    onSuggestionsClearRequested = () => {
        this.setState({
            suggestions: []
        });
    };

    onSuggestionSelected = (event, {suggestion, suggestionValue, index, method}) => {
        event.preventDefault();
        let appointment = {...this.state.appointment};
        appointment.guestId = suggestion.accountId;
        this.setState({appointment});
    };

    render() {
        const {value, suggestions} = this.state;

        const inputProps = {
            value,
            onChange: this.handleSuggestionChange
        };

        return (
            <div><AppNav/>
                <h2 className="text-center mt-5">Add Appointment</h2>
                <div className="container">
                    <div className="row">
                        <div className="col"/>
                        <div className="col">
                            <Container className="mt-5">
                                <Form onSubmit={this.handleSubmit}>
                                    <FormGroup>
                                        <Label for="guest">Guest</Label>
                                        <Autosuggest
                                            name="guest" id="guest"
                                            suggestions={suggestions}
                                            onSuggestionsFetchRequested={this.onSuggestionsFetchRequested}
                                            onSuggestionsClearRequested={this.onSuggestionsClearRequested}
                                            getSuggestionValue={getSuggestionValue}
                                            renderSuggestion={renderSuggestion}
                                            onSuggestionSelected={this.onSuggestionSelected}
                                            inputProps={inputProps}
                                        />
                                    </FormGroup>
                                    <FormGroup>
                                        <Label for="employeeEmail">Email of Employee</Label>
                                        <Input type="email" name="employeeEmail" id="employeeEmail"
                                               onChange={this.handleInputChange}/>
                                    </FormGroup>
                                    <FormGroup>
                                        <Label for="appointmentDate">Appointment Date</Label>
                                        <Datetime type="text" name="appointmentDate" id="appointmentDate"
                                                  inputProps={{readOnly: true}}
                                                  timeFormat="HH:mm"
                                                  onChange={this.handleDatetimeChange}/>
                                    </FormGroup>
                                    <FormGroup>
                                        <Button color="primary" type="submit">Save</Button>{' '}
                                        <Button color="secondary" tag={Link} to="/home">Cancel</Button>
                                    </FormGroup>
                                </Form>
                            </Container>
                        </div>
                        <div className="col"/>
                    </div>
                </div>
            </div>);
    }
}

export default AddAppointment;