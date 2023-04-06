import { Button, Card, CardActions, CardContent, IconButton, InputLabel, Select, TextField, MenuItem} from "@mui/material";
import { DateField } from '@mui/x-date-pickers';
import { Container } from "@mui/system";
import { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import { BACKEND_API_URL } from "../../constants";
import { Smartphone } from "../../model/Smartphone";
import { Display } from "../../model/Display";
import EditIcon from "@mui/icons-material/Edit";
import DeleteForeverIcon from "@mui/icons-material/DeleteForever";
import ArrowBackIcon from "@mui/icons-material/ArrowBack";
import axios from "axios";

export const SmartphoneAdd = () => {
	const navigate = useNavigate();

	const [smartphone, setSmartphone] = useState<Smartphone>({
		brand: "",
		model: "",
		price: 0,
		storageCapacity: 0,
		launchDate: "",
		display: new Display()
	});

	const [displays, setDisplays] = useState<Display[]>([]);
	useEffect(() => {
		fetch(`${BACKEND_API_URL}/display`)
		  .then(res => res.json())
		  .then(data => {
			setDisplays(data);
		  })
	  }, []);


	const addSmartphone = async (event: { preventDefault: () => void }) => {
		event.preventDefault();
		try {
			console.log(smartphone);
			await axios.post(`${BACKEND_API_URL}/smartphone`, smartphone);
			navigate("/smartphones");
		} catch (error) {
			console.log(error);
		}
	};

	const [selectedVal, setSelectedVal] = useState(0);

	return (
		<Container>
			<Card>
				<CardContent>
					<IconButton component={Link} sx={{ mr: 3 }} to={`/smartphones`}>
						<ArrowBackIcon />
					</IconButton>{" "}
					<form onSubmit={addSmartphone}>
						<TextField
							id="brand"
							label="Brand"
							variant="outlined"
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => setSmartphone({ ...smartphone, brand: event.target.value })}
						/>
						<TextField
							id="model"
							label="Model"
							variant="outlined"
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => setSmartphone({ ...smartphone, model: event.target.value })}
						/>
						<TextField
							id="price"
							label="Price"
							variant="outlined"
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => setSmartphone({ ...smartphone, price: Number(event.target.value) })}
						/>
						<TextField
							id="storageCapcity"
							label="Storage Capacity"
							variant="outlined"
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
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => setSmartphone({ ...smartphone, launchDate: event.target.value})}
						/>
						<InputLabel sx={{float: "left"}}>
								Main display:
						</InputLabel>
						<Select
							id="display"
							variant="outlined"
							label="Display"
							value={selectedVal}
							fullWidth
							displayEmpty
							sx={{mb: 2}}
							onChange={(event) => {
								setSmartphone({ ...smartphone, 
									display: displays.find(d => d.id == event.target.value as number ) as Display})
								setSelectedVal(event.target.value as number)
							}}
						>	
							<MenuItem value="0" disabled>Pick a display</MenuItem>
							{displays.map((display: Display) => {
								return(
								    <MenuItem value={display.id}>
										{display.type} {display.size} inch {display.resolutionHeight}x{display.resolutionWidth}
									</MenuItem>)
							})}
						</Select>
						<Button type="submit" sx={{float: "left"}}>Add smartphone</Button>
					</form>
				</CardContent>
				<CardActions></CardActions>
			</Card>
		</Container>
	);
};
