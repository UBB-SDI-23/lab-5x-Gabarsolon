import {useEffect, useState} from "react";
import {Smartphone} from "../../model/Smartphone";
import {Display} from "../../model/Display";
import {
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Paper,
    IconButton,
    Tooltip,
    Container,
    Button,
    Typography,
    List,
    ListItem,
    TextField,
    CircularProgress,
} from "@mui/material";
import AddIcon from "@mui/icons-material/Add";
import DeleteForeverIcon from "@mui/icons-material/Delete";
import EditIcon from "@mui/icons-material/Edit"
import {Link} from "react-router-dom";
import {BACKEND_API_URL} from "../../constants";
import { inherits } from "util";

function SmartphoneGetAll() {
    const [smartphones,
        setSmartphones] = useState([]);
    const [loading,
        setLoading] = useState(false);
    const [price, setPrice] = useState(-1);
    
    const handlePriceTextFIeld = (event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const inputtedPrice = Number(event.target.value);
        if(!isNaN(inputtedPrice)){
            setPrice(Number(inputtedPrice));
        }
        else{
            setPrice(-1);
        }
    }

    useEffect(() => {
        setLoading(true);
        if(price == -1){
            fetch(`/api/smartphone`)
            .then(res => res.json())
            .then(data => {
                setSmartphones(data);
                setLoading(false);
            })
        }
        else{
            fetch(`/api/smartphone/getWithPriceHigherThan/${price}`)
            .then(res => res.json())
            .then(data => {
                setSmartphones(data);
                setLoading(false);
            })
        }
    }, [price]);

    return (
        <Container sx={{
            marginTop: "40px"
        }}>
            <Typography variant="h3" color="black" align="left">All Smartphones</Typography>
            {(
                <List sx={{display: "flex", flexDirection: "row", padding: "1px"}}>
                    <ListItem sx={{width: "auto"}}>
                        <Button
                            variant="outlined"
                            component={Link}
                            to={`/smartphones/add`}>
                            + Add new smartphone
                        </Button>
                    </ListItem>
                    <ListItem sx={{width: "auto"}}>
                        <TextField
                            id="filter"
                            label="Having price higher than:"
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => {
                                handlePriceTextFIeld(event);
                            }}
                        >
                        </TextField>
                    </ListItem>
                </List>
            )}
            {!loading && (
                <TableContainer component={Paper}>
                    <Table>
                        <TableHead
                            sx={{
                            backgroundColor: '#F5F5F5'
                        }}>
                            <TableRow>
                                <TableCell>#</TableCell>
                                <TableCell>Brand</TableCell>
                                <TableCell>Model</TableCell>
                                <TableCell>Price</TableCell>
                                <TableCell>Storage Capacity</TableCell>
                                <TableCell>Launch Date</TableCell>
                                <TableCell>Display Type</TableCell>
                                <TableCell>Operations</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {smartphones.map((smartphone : Smartphone, index) => (
                                <TableRow key={index}>
                                    <TableCell>{index + 1}</TableCell>
                                    <TableCell>{smartphone.brand}</TableCell>
                                    <TableCell>{smartphone.model}</TableCell>
                                    <TableCell>{smartphone.price}</TableCell>
                                    <TableCell>{smartphone.storageCapacity}</TableCell>
                                    <TableCell>{smartphone.launchDate}</TableCell>
                                    <TableCell>{smartphone.display.type}</TableCell>
                                    <TableCell>
                                        <IconButton
                                            component={Link}
                                            sx={{mr: 3}}
                                            to={`/smartphones/delete/${smartphone.id}`}>
                                            <Tooltip title="Delete" arrow>
                                                <DeleteForeverIcon sx={{color: "red"}}/>
                                            </Tooltip>
                                        </IconButton>
                                        <IconButton
                                            component={Link}
                                            sx={{mr: 3}}
                                            to={`/smartphones/update/${smartphone.id}`}>
                                            <Tooltip title="Update" arrow>
                                                <EditIcon/>
                                            </Tooltip>
                                        </IconButton>
                                    </TableCell>
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
