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
        appointmentId: '',
        guestId: '',
        employeeEmail: '',
        appointmentStartDate: '',
        appointmentEndDate: ''
    };

    constructor(props) {
        super(props);

        this.state = {
            value: '',
            suggestions: [],
            appointment: this.emptyAppointment,
            showGuestError: false,
            showEmailError: false,
            showStartDateError: false,
            showEndDateError: false
        };

        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleStartDatetimeChange = this.handleStartDatetimeChange.bind(this);
        this.handleEndDatetimeChange = this.handleEndDatetimeChange.bind(this);
    }

    async handleSubmit(event) {
        event.preventDefault();
        if (this.formValidation()){
            const appointment = this.state.appointment;
            await fetch('/appointment', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(appointment),
            }).then((response) => {
                console.log(response);
                if (response.ok) {
                    this.props.history.push("/calendar");
                }
            });
        }
    }

    formValidation(){
        let validated = true;

        this.setState({
            showGuestError: false,
            showEmailError: false,
            showStartDateError: false,
            showEndDateError: false
        });

        const appointment = this.state.appointment;
        if ( appointment.guestId === '' ){
            this.setState({showGuestError: true});
            validated = false;
        }
        if ( appointment.employeeEmail === '' ){
            this.setState({showEmailError: true});
            validated = false;
        }
        if ( appointment.appointmentStartDate === '' ){
            this.setState({showStartDateError: true});
            validated = false;
        }
        if ( appointment.appointmentEndDate === '' ){
            this.setState({showEndDateError: true});
            validated = false;
        }
        return validated;
    }

    handleInputChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let appointment = {...this.state.appointment};
        appointment[name] = value;
        this.setState({appointment});
    }

    handleStartDatetimeChange(date) {
        let appointment = {...this.state.appointment};
        appointment.appointmentStartDate = date.toDate();
        this.setState({appointment});
    }

    handleEndDatetimeChange(date) {
        let appointment = {...this.state.appointment};
        appointment.appointmentEndDate = date.toDate();
        this.setState({appointment});
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
        const {value, suggestions, showGuestError, showEmailError, showStartDateError, showEndDateError} = this.state;

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
                                        <p style={{color: 'red'}}>{showGuestError ? 'Missing Guest, make sure to select one from the suggestions' : ''}</p>
                                    </FormGroup>
                                    <FormGroup>
                                        <Label for="employeeEmail">Email of Employee</Label>
                                        <Input type="email" name="employeeEmail" id="employeeEmail"
                                               onChange={this.handleInputChange}/>
                                        <p style={{color: 'red'}}>{showEmailError ? 'Missing Email' : ''}</p>
                                    </FormGroup>
                                    <FormGroup>
                                        <Label for="appointmentStartDate">Appointment Start Date</Label>
                                        <Datetime type="text" name="appointmentStartDate" id="appointmentStartDate"
                                                  inputProps={{readOnly: true}}
                                                  locale={'nl'}
                                                  timeFormat="HH:mm"
                                                  onChange={this.handleStartDatetimeChange}/>
                                        <p style={{color: 'red'}}>{showStartDateError ? 'Missing Start Date' : ''}</p>
                                    </FormGroup>
                                    <FormGroup>
                                        <Label for="appointmentEndDate">Appointment End Date</Label>
                                        <Datetime type="text" name="appointmentEndDate" id="appointmentEndDate"
                                                  inputProps={{readOnly: true}}
                                                  locale={'nl'}
                                                  timeFormat="HH:mm"
                                                  onChange={this.handleEndDatetimeChange}/>
                                        <p style={{color: 'red'}}>{showEndDateError ? 'Missing End Date' : ''}</p>
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