import { Button, Card, CardActions, CardContent, IconButton, InputLabel, Select, TextField, MenuItem, Autocomplete} from "@mui/material";
import { Container } from "@mui/system";
import { useCallback, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { BACKEND_API_URL } from "../../constants";
import { Customer } from "../../model/Customer";
import ArrowBackIcon from "@mui/icons-material/ArrowBack"
import axios from "axios";
import {debounce} from "lodash";

export const CustomerForm = (
    { apiCallMethod, customer, setCustomer, btnMsg} : 
    { apiCallMethod: any, customer: Customer, setCustomer: any, btnMsg: any}) =>{

    
    return (
		<Container>
			<Card>
				<CardContent>
					<IconButton component={Link} to={`/customers`} sx={{float: "left"}}>
						<ArrowBackIcon/>
					</IconButton>
					<IconButton component={Link} sx={{ mr: 3 }} to={`/customers`}>
					</IconButton>{" "}
					<form onSubmit={apiCallMethod}>
						<TextField
							id="firstName"
							label="First Name"
							variant="outlined"
                            value={customer.firstName}
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => setCustomer({ ...customer, firstName: event.target.value })}
						/>
						<TextField
							id="Last name"
							label="Last Name"
							variant="outlined"
                            value={customer.lastName}
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => setCustomer({ ...customer, lastName: event.target.value })}
						/>
						<TextField
							id="phoneNumber"
							label="Phone Number"
							variant="outlined"
                            value={customer.phoneNumber}
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => setCustomer({ ...customer, phoneNumber: event.target.value })}
						/>
						<TextField
							id="dateOfBirth"
							label="Date of Birth"
							variant="outlined"
                            value={customer.dateOfBirth}
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => setCustomer({ ...customer, dateOfBirth: event.target.value })}
						/>
						<TextField
							id="email"
							label="Email"
							variant="outlined"
                            value={customer.email}
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => setCustomer({ ...customer, email: event.target.value })}
						/>
						<Button type="submit" sx={{float: "left"}}>{btnMsg}</Button>
					</form>
				</CardContent>
				<CardActions></CardActions>
			</Card>
		</Container>
	);
}