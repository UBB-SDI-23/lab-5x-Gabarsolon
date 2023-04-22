import { Button, Card, CardActions, CardContent, IconButton, InputLabel, Select, TextField, MenuItem, Autocomplete} from "@mui/material";
import { Container } from "@mui/system";
import { useCallback, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { BACKEND_API_URL } from "../../constants";
import { Display } from "../../model/Display";
import ArrowBackIcon from "@mui/icons-material/ArrowBack"
import axios from "axios";
import {debounce} from "lodash";

export const DisplayForm = (
    { apiCallMethod, display, setDisplay, btnMsg} : 
    { apiCallMethod: any, display: Display, setDisplay: any, btnMsg: any}) =>{

    
    return (
		<Container>
			<Card>
				<CardContent>
					<IconButton component={Link} to={`/displays`} sx={{float: "left"}}>
						<ArrowBackIcon/>
					</IconButton>
					<IconButton component={Link} sx={{ mr: 3 }} to={`/displays`}>
					</IconButton>{" "}
					<form onSubmit={apiCallMethod}>
						<TextField
							id="type"
							label="Type"
							variant="outlined"
                            value={display.type}
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => setDisplay({ ...display, type: event.target.value })}
						/>
						<TextField
							id="size"
							label="Size"
							variant="outlined"
                            value={display.size}
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => setDisplay({ ...display, size: Number(event.target.value) })}
						/>
						<TextField
							id="resolutionWidth"
							label="Resolution Width"
							variant="outlined"
                            value={display.resolutionWidth}
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => setDisplay({ ...display, resolutionWidth: Number(event.target.value) })}
						/>
						<TextField
							id="resolutionHeight"
							label="Resolution Height"
							variant="outlined"
                            value={display.resolutionHeight}
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => setDisplay({ ...display, resolutionHeight: Number(event.target.value) })}
						/>
						<TextField
							id="protection"
							label="Protection"
							variant="outlined"
                            value={display.protection}
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => setDisplay({ ...display, protection: event.target.value })}
						/>
						<Button type="submit" sx={{float: "left"}}>{btnMsg}</Button>
					</form>
				</CardContent>
				<CardActions></CardActions>
			</Card>
		</Container>
	);
}