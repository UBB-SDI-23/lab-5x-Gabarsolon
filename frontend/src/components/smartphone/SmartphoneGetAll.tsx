import { useEffect, useState } from "react";
import { Smartphone } from "../../model/Smartphone";
import { Display } from "../../model/Display";
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper } from "@mui/material";

function SmartphoneGetAll() {
  const [smartphones, setSmartphones] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/api/smartphone")
      .then(res => res.json())
      .then(data => setSmartphones(data))
  }, []);

  return (
    <div className="SmartphoneGetAll">
      <TableContainer component={Paper}>
        <Table>
          <TableHead sx={{ backgroundColor: '#F5F5F5' }}>
            <TableRow>
              <TableCell sx={{ fontWeight: 'bold' }}>#</TableCell>
              <TableCell sx={{ fontWeight: 'bold' }}>Brand</TableCell>
              <TableCell sx={{ fontWeight: 'bold' }}>Model</TableCell>
              <TableCell sx={{ fontWeight: 'bold' }}>Price</TableCell>
              <TableCell sx={{ fontWeight: 'bold' }}>Storage Capacity</TableCell>
              <TableCell sx={{ fontWeight: 'bold' }}>Launch Date</TableCell>
              <TableCell sx={{ fontWeight: 'bold' }}>Display Type</TableCell>
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
    </div>
  );
}

export default SmartphoneGetAll;
