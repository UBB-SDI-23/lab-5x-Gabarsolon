import { CssBaseline, Container, Typography } from "@mui/material";
import React from "react";

export const AppHome = () => {
	return (
		<React.Fragment>
			<CssBaseline />

			<Container maxWidth="xl">
				<Typography variant="h1" component="h1" gutterBottom>
					Welcome to my smartphone shop. Use the navigation menu from above to find your dream device! :O
				</Typography>
			</Container>
		</React.Fragment>
	);
};
