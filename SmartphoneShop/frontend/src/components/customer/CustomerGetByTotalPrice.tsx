import axios from "axios";
import { BACKEND_API_URL } from "../../constants";
import { useEffect, useState } from "react";
import { CircularProgress, Container, IconButton, List, ListItem, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, TextField, Typography } from "@mui/material";
import { Link } from "react-router-dom";
import Pagination from "../pagination/Pagination";

export const CustomerGetByTotalPrice = () => {
    interface CustomerDTO {
        firstName: string;
        lastName: string;
        phoneNumber: string;
        totalPrice: number;
    }

    const [customerDTOs, setCustomerDTOs] = useState<CustomerDTO[]>([]);
    const [loading, setLoading] = useState(false);
    const [pageVal, setPageVal] = useState(1);
    const [currentPage, setCurrentPage] = useState(1);
    const count=10;

    useEffect(() => {const fetchData = async() => {
		try{
            setLoading(true);
			const response = await axios.get<CustomerDTO[]>(
				`${BACKEND_API_URL}/transaction/getAllCustomersOrderedDescByTotalPriceOfBoughtSmartphones?pageNumber=${currentPage}`
			);
			const data = await response.data;
			setCustomerDTOs(data);
            setLoading(false);
		}catch(error){
			console.error("Error fetching suggestions:", error);
		}
	}; fetchData()}, [currentPage]);

    return (
        <Container sx={{
            marginTop: "40px",
        }}>
            <Typography variant="h3" color="black" align="left">
                Customers ordered descending by total price of brought smartphones
            </Typography>
            {loading && <CircularProgress/>}
            {!loading && (
                <TableContainer component={Paper} >
                    <Table>
                        <TableHead
                            sx={{
                            backgroundColor: '#F5F5F5'
                        }}>
                            <TableRow>
                                <TableCell>#</TableCell>
                                <TableCell>
                                    First Name
                                </TableCell>
                                <TableCell>
                                    Last Name
                                </TableCell>
                                <TableCell>
                                    Phone number
                                </TableCell>
                                <TableCell>
                                    Total price
                                </TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {customerDTOs.map((customer : CustomerDTO, index) => (
                                <TableRow key={index}>
                                    <TableCell>{index + 1}</TableCell>
                                    <TableCell>{customer.firstName}</TableCell>
                                    <TableCell>{customer.lastName}</TableCell>
                                    <TableCell>{customer.phoneNumber}</TableCell>
                                    <TableCell>{customer.totalPrice}</TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                    <List
                        sx={{
                        display: "flex",
                        flexDirection: "row",
                        padding: "1px"
                    }}>
                        <ListItem>
                            <Pagination
                                onPageChange={(page : number) => setCurrentPage(page)}
                                totalCount={count}
                                currentPage={currentPage}
                                pageSize={10}
                                className="pagination-bar"/>
                        </ListItem>
                        <ListItem>
                            <TextField
                                id="page"
                                label="Page:"
                                type="number"
                                sx={{
                                mb: 2
                            }}
                                onChange={(event) => {
                                    setPageVal(Number(event.target.value));
                                }}
                                onKeyDown={(event) => {
                                if (event.key === "Enter") 
                                    setCurrentPage(pageVal);
                                }}></TextField>
                        </ListItem>
                    </List>
                </TableContainer>
            )}
        </Container>
    );
}