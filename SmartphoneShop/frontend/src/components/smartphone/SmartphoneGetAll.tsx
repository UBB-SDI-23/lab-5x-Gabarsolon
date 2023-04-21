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
    TableSortLabel
} from "@mui/material";
import Pagination from '../pagination/Pagination'
import AddIcon from "@mui/icons-material/Add";
import DeleteForeverIcon from "@mui/icons-material/Delete";
import EditIcon from "@mui/icons-material/Edit"
import {Link} from "react-router-dom";
import {BACKEND_API_URL} from "../../constants";
import {inherits} from "util";

function SmartphoneGetAll() {
    const [smartphones, setSmartphones] = useState<Smartphone[]>([]);
    const [loading, setLoading] = useState(false);
    const [price, setPrice] = useState(-1);
    const [currentPage, setCurrentPage] = useState(1);
    const [sortColumn, setSortColumn] = useState("brand");
    const [sortOrder, setSortOrder] = useState<"asc" | "desc">("asc");
    const [count, setCount] = useState(1);

    const handleSort = (column: string) => {
        if (column === sortColumn) {
          setSortOrder(sortOrder === "asc" ? "desc" : "asc");
        } else {
          setSortColumn(column);
          setSortOrder("asc");
        }
        if(sortOrder == "asc"){
                smartphones.sort((a, b) => a.launchDate.localeCompare(b.launchDate));
            }       
        else{
                smartphones.sort((b, a) => a.launchDate.localeCompare(b.launchDate));
            }  
            
      };
      

    const handlePriceTextFIeld = (event : React.ChangeEvent < HTMLInputElement | HTMLTextAreaElement >) => {
        const inputtedPrice = Number(event.target.value);
        if (!isNaN(inputtedPrice)) {
            setPrice(Number(inputtedPrice));
        } else {
            setPrice(-1);
        }
    }

    useEffect(() => {
        setLoading(true);
        fetch(`${BACKEND_API_URL}/smartphone/count`)
            .then(res => res.json())
            .then(data => setCount(data));
        if (price == -1) {
            fetch(`${BACKEND_API_URL}/smartphone/byPage/${currentPage-1}`)
                .then(res => res.json())
                .then(data => {
                    setSmartphones(data);
                    setLoading(false);
                })
        } else {
            fetch(`${BACKEND_API_URL}/smartphone/getWithPriceHigherThan/${price}`)
                .then(res => res.json())
                .then(data => {
                    setSmartphones(data);
                    setLoading(false);
                })
        }
    }, [price, currentPage]);

    return (
        <Container sx={{
            marginTop: "40px"
        }}>
            <Typography variant="h3" color="black" align="left">All Smartphones</Typography>
            {(
                <List
                    sx={{
                    display: "flex",
                    flexDirection: "row",
                    padding: "1px"
                }}>
                    <ListItem sx={{
                        width: "auto"
                    }}>
                        <Button variant="outlined" component={Link} to={`/smartphones/add`}>
                            + Add new smartphone
                        </Button>
                    </ListItem>
                    <ListItem sx={{
                        width: "auto"
                    }}>
                        <TextField
                            id="filter"
                            label="Having price higher than:"
                            fullWidth
                            sx={{
                            mb: 2
                        }}
                            onChange={(event) => {
                            handlePriceTextFIeld(event);
                        }}></TextField>
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
                                <TableCell>
                                        Brand
                                    </TableCell>
                                <TableCell>
                                    Model
                                </TableCell>
                                <TableCell>
                                    Price
                                </TableCell>
                                <TableCell>
                                    Storage Capacity
                                </TableCell>
                                <TableCell>
                                    <TableSortLabel
                                            active={sortColumn === "launchDate"}
                                            direction={sortOrder}
                                            onClick={() => handleSort("launchDate")}
                                    >
                                    Launch date
                                    </TableSortLabel>
                                </TableCell>
                                <TableCell>
                                    Display Type
                                </TableCell>
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
                                            sx={{
                                            mr: 3
                                        }}
                                            to={`/smartphones/delete/${smartphone.id}`}>
                                            <Tooltip title="Delete" arrow>
                                                <DeleteForeverIcon
                                                    sx={{
                                                    color: "red"
                                                }}/>
                                            </Tooltip>
                                        </IconButton>
                                        <IconButton
                                            component={Link}
                                            sx={{
                                            mr: 3
                                        }}
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
                    <Pagination
                        onPageChange={(page: number) => setCurrentPage(page)}
                        totalCount={count}
                        currentPage={currentPage}
                        pageSize={10}
                        className="pagination-bar"
                    />
                </TableContainer>
            )}
        </Container>
    );
}

export default SmartphoneGetAll;
