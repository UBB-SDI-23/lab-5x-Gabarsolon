import { useEffect, useState } from "react";
import { Smartphone } from "../../model/Smartphone";
import { Display } from "../../model/Display";
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, IconButton, Tooltip, Container, Button, Typography } from "@mui/material";
import AddIcon from "@mui/icons-material/Add";
import { Link } from "react-router-dom";
import { BACKEND_API_URL } from "../../constants";

function SmartphoneGetAll() {
  const [smartphones, setSmartphones] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    setLoading(true);
    fetch(`${BACKEND_API_URL}/smartphone`)
      .then(res => res.json())
      .then(data => {
        setSmartphones(data);
        setLoading(false);
      })
  }, []);;

  return (
      <Container sx={{marginTop: "40px"}}>
        <Typography variant="h3" color="black" align="left">All Smartphones</Typography>
        {!loading && (
          <Button variant="outlined" component={Link} sx={{ float:"left"}} to={`/smartphones/add`}>
            + Add new smartphone
          </Button> 
			  )}
        {!loading && (
          <TableContainer component={Paper}>
            <Table>
              <TableHead sx={{ backgroundColor: '#F5F5F5' }}>
                <TableRow>
                  <TableCell>#</TableCell>
                  <TableCell>Brand</TableCell>
                  <TableCell>Model</TableCell>
                  <TableCell>Price</TableCell>
                  <TableCell>Storage Capacity</TableCell>
                  <TableCell>Launch Date</TableCell>
                  <TableCell>Display Type</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {smartphones.map((smartphone: Smartphone, index) => (
                  <TableRow key={index}>
                    <TableCell>{index + 1}</TableCell>
                    <TableCell>{smartphone.brand}</TableCell>
                    <TableCell>{smartphone.model}</TableCell>
                    <TableCell>{smartphone.price}</TableCell>
                    <TableCell>{smartphone.storageCapacity}</TableCell>
                    <TableCell>{smartphone.launchDate}</TableCell>
                    <TableCell>{smartphone.display.type}</TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
        )}
      </Container>
  );
}

export default SmartphoneGetAll;
