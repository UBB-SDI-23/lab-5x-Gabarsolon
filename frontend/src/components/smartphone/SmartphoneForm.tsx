import { Button, Card, CardActions, CardContent, IconButton, InputLabel, Select, TextField, MenuItem} from "@mui/material";
import { Container } from "@mui/system";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { BACKEND_API_URL } from "../../constants";
import { Smartphone } from "../../model/Smartphone";
import { Display } from "../../model/Display";
import ArrowBackIcon from "@mui/icons-material/ArrowBack"

export const SmartphoneForm = (
    { apiCallMethod, smartphone, setSmartphone, btnMsg} : 
    { apiCallMethod: any, smartphone: Smartphone, setSmartphone: any, btnMsg: any}) =>{

	const [displays, setDisplays] = useState<Display[]>([]);
	useEffect(() => {
		fetch(`${BACKEND_API_URL}/display`)
		  .then(res => res.json())
		  .then(data => {
			setDisplays(data);
		  })
	  }, []);
    
	const [selectedVal, setSelectedVal] = useState(0);

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
						<InputLabel sx={{float: "left"}}>
								Main display:
						</InputLabel>
						<Select
							id="display"
							variant="outlined"
							label="Display"
							value={selectedVal}
                            defaultValue={smartphone.display.id}
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
						<Button type="submit" sx={{float: "left"}}>{btnMsg}</Button>
					</form>
				</CardContent>
				<CardActions></CardActions>
			</Card>
		</Container>
	);
}