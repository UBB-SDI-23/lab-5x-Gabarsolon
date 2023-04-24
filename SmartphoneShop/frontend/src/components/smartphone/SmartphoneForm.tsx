import { Button, Card, CardActions, CardContent, IconButton, InputLabel, Select, TextField, MenuItem, Autocomplete} from "@mui/material";
import { Container } from "@mui/system";
import { useCallback, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { BACKEND_API_URL } from "../../constants";
import { Smartphone } from "../../model/Smartphone";
import { Display } from "../../model/Display";
import ArrowBackIcon from "@mui/icons-material/ArrowBack"
import axios from "axios";
import {debounce} from "lodash";

export const SmartphoneForm = (
    { apiCallMethod, smartphone, setSmartphone, btnMsg} : 
    { apiCallMethod: any, smartphone: Smartphone, setSmartphone: any, btnMsg: any}) =>{

	const [displays, setDisplays] = useState<Display[]>([]);

	const fetchSuggestions = async(query: string) => {
		try{
			const response = await axios.get<Display[]>(
				`${BACKEND_API_URL}/display/autocomplete?query=${query}`
			);
			const data = await response.data;
			setDisplays(data);
		}catch(error){
			console.error("Error fetching suggestions:", error);
		}
	}
    
	const debouncedFetchSuggestions = useCallback(debounce(fetchSuggestions, 500), []);

	useEffect(() => {
		return () => {
			debouncedFetchSuggestions.cancel();
		};
	},[debouncedFetchSuggestions]);

	const handleInputChange = (event: any, value: any, reason: any) => {
		console.log("input", value, reason);

		if (reason === "input") {
			debouncedFetchSuggestions(value);
		}
	};

    return (
		<Container>
			<Card>
				<CardContent>
					<IconButton component={Link} to={`/smartphones`} sx={{float: "left"}}>
						<ArrowBackIcon/>
					</IconButton>
					<IconButton component={Link} sx={{ mr: 3 }} to={`/smartphones`}>
					</IconButton>{" "}
					<form onSubmit={apiCallMethod}>
						<TextField
							id="brand"
							label="Brand"
							variant="outlined"
                            value={smartphone.brand}
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => setSmartphone({ ...smartphone, brand: event.target.value })}
						/>
						<TextField
							id="model"
							label="Model"
							variant="outlined"
                            value={smartphone.model}
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => setSmartphone({ ...smartphone, model: event.target.value })}
						/>
						<TextField
							id="price"
							label="Price"
							variant="outlined"
                            value={smartphone.price}
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => setSmartphone({ ...smartphone, price: Number(event.target.value) })}
						/>
						<TextField
							id="storageCapcity"
							label="Storage Capacity"
							variant="outlined"
                            value={smartphone.storageCapacity}
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => setSmartphone({ ...smartphone, storageCapacity: Number(event.target.value) })}
						/>
						<InputLabel sx={{ float:"left"}}>
							Launch Date:
						</InputLabel>
						<TextField
							type="date"
							id="price"
							variant="outlined"
                            value={smartphone.launchDate}
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => setSmartphone({ ...smartphone, launchDate: event.target.value})}
						/>
						<Autocomplete
							id="display"
							options={displays}
							value={smartphone.display}
							getOptionLabel={(option) => `${option.type} ${option.size} inch ${option.resolutionWidth}x${option.resolutionHeight}`}
							renderInput={(params) => <TextField {...params} label="Main display(type size widthxheight)" variant="outlined" />}
							filterOptions={(x) => x}
							onInputChange={handleInputChange}
							onChange={(event, value) => {
								if (value) {
									setSmartphone({ ...smartphone, display: value });
								}
							}}
						/>
						<Button type="submit" sx={{float: "left"}}>{btnMsg}</Button>
					</form>
				</CardContent>
				<CardActions></CardActions>
			</Card>
		</Container>
	);
}