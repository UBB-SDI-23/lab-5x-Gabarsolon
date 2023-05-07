import { Button, Card, CardActions, CardContent, IconButton, InputLabel, Select, TextField, MenuItem, Autocomplete} from "@mui/material";
import { Container } from "@mui/system";
import { useCallback, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { BACKEND_API_URL } from "../../constants";
import { Smartphone } from "../../model/Smartphone";
import { Customer } from "../../model/Customer";
import ArrowBackIcon from "@mui/icons-material/ArrowBack";
import axios from "axios";
import {debounce} from "lodash";
import { Transaction } from "../../model/Transaction";

export const TransactionForm = (
    { apiCallMethod, transaction, setTransaction, btnMsg} : 
    { apiCallMethod: any, transaction: Transaction, setTransaction: any, btnMsg: string}) =>{

    const [customers, setCustomers] = useState<Customer[]>([]);
	const [smartphones, setSmartphones] = useState<Smartphone[]>([]);

	const fetchSuggestionsCustomers = async(query: string) => {
		try{
			const response = await axios.get<Customer[]>(
				`${BACKEND_API_URL}/customer/autocomplete?query=${query}`
			);
			const data = await response.data;
			setCustomers(data);
		}catch(error){
			console.error("Error fetching suggestions:", error);
		}
	}

    const fetchSuggestionsSmartphones = async(query: string) => {
		try{
			const response = await axios.get<Smartphone[]>(
				`${BACKEND_API_URL}/smartphone/autocomplete?query=${query}`
			);
			const data = await response.data;
			setSmartphones(data);
		}catch(error){
			console.error("Error fetching suggestions:", error);
		}
	}
    
    const debouncedFetchSuggestionsCustomers = useCallback(debounce(fetchSuggestionsCustomers, 500), []);
	const debouncedFetchSuggestionsSmartphones = useCallback(debounce(fetchSuggestionsSmartphones, 500), []);

	useEffect(() => {
		return () => {
			debouncedFetchSuggestionsSmartphones.cancel();
            debouncedFetchSuggestionsCustomers.cancel();
		};
	},[debouncedFetchSuggestionsSmartphones, debouncedFetchSuggestionsCustomers]);

    const handleInputChangeCustomers = (event: any, value: any, reason: any) => {
		console.log("input", value, reason);

		if (reason === "input") {
			debouncedFetchSuggestionsCustomers(value);
		}
	};

	const handleInputChangeSmartphones = (event: any, value: any, reason: any) => {
		console.log("input", value, reason);

		if (reason === "input") {
			debouncedFetchSuggestionsSmartphones(value);
		}
	};

	const [errors, setErrors] = useState({
		customer: "",
		smartphone: "",
		quantity: "",
		dateTime: ""
	});

	const validateForm = () => {
		let valid = true;

		const newErrors = {
			customer: "",
			smartphone: "",
			quantity: "",
			dateTime: ""
		}
		if(transaction.customer.firstName===""){
			newErrors.customer="Customer is required!";
			valid = false;
		}
		if(transaction.smartphone.brand===""){
			newErrors.smartphone="Smartphone is required!";
			valid = false;
		}
		if(isNaN(transaction.quantity) || transaction.quantity <= 0){
			newErrors.quantity="Quantity must be a positive number";
			valid = false;
		}
		if(transaction.dateTime===""){
			newErrors.dateTime="Date and time is required!";
			valid = false;
		}
		
		let launchDate = new Date(transaction.dateTime).getTime();
		let currentDate = new Date().getTime();
		if(launchDate > currentDate){
			transaction.dateTime="Date must be in the past or present";
		}

		setErrors(newErrors);
		return valid;
	}
	
	const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
		event.preventDefault();
		if(validateForm()){
			apiCallMethod();
		}
	}

    return (
		<Container>
			<Card>
				<CardContent>
					<IconButton component={Link} to={`/transactions`} sx={{float: "left"}}>
						<ArrowBackIcon/>
					</IconButton>
					<IconButton component={Link} sx={{ mr: 3 }} to={`/transactions`}>
					</IconButton>{" "}
					<form onSubmit={handleSubmit}>
                        <Autocomplete
                            id="customer"
                            options={customers}
                            value={transaction.customer}
                            getOptionLabel={(option) => `${option.firstName} ${option.lastName} ${option.email}`}
                            renderInput={(params) => <TextField {...params} 
							label="Customer" 
							variant="outlined"
							error={!!errors.customer}
							helperText={errors.customer} />}
                            filterOptions={(x) => x}
                            sx={{mb: 2}}
                            onInputChange={handleInputChangeCustomers}
                            onChange={(event, value) => {
                                if (value) {
                                    setTransaction({ ...transaction, customer: value });
                                }
                            }}
						/>
                        <Autocomplete
							id="smartphone"
							options={smartphones}
							value={transaction.smartphone}
							getOptionLabel={(option) => `${option.brand} ${option.model} ${option.price} RON`}
							renderInput={(params) => <TextField {...params} 
							label="Smartphone" 
							variant="outlined" 
							error={!!errors.smartphone}
							helperText={errors.smartphone}/>}
							filterOptions={(x) => x}
                            sx={{mb: 2}}
							onInputChange={handleInputChangeSmartphones}
							onChange={(event, value) => {
								if (value) {
									setTransaction({ ...transaction, smartphone: value });
								}
							}}
						/>
						<TextField
							id="quantity"
							label="Quantity"
							variant="outlined"
                            value={transaction.quantity}
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => setTransaction({ ...transaction, quantity: Number(event.target.value)})}
							error={!!errors.quantity}
							helperText={errors.quantity}
						/>
						<InputLabel sx={{ float:"left"}}>
							Launch Date:
						</InputLabel>
						<TextField
							type="datetime-local"
							id="price"
							variant="outlined"
                            value={transaction.dateTime}
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => setTransaction({ ...transaction, dateTime: event.target.value})}
							error={!!errors.dateTime}
							helperText={errors.dateTime}
						/>
						
						<Button type="submit" sx={{float: "left"}}>{btnMsg}</Button>
					</form>
				</CardContent>
				<CardActions></CardActions>
			</Card>
		</Container>
	);
}