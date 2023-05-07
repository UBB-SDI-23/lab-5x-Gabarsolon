import {useEffect, useState} from "react";
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
import axios from "axios";

function DisplayGetAll() {
    const [displays,
        setDisplays] = useState <Display[]>([]);
    const [loading,
        setLoading] = useState(false);
    const [currentPage,
        setCurrentPage] = useState(1);
    const [count,
        setCount] = useState(1);
    const [pageVal, setPageVal] = useState(1);

    useEffect(() => {
        setLoading(true);
        fetch(`${BACKEND_API_URL}/display/count`)
            .then(res => res.json())
            .then(data => setCount(data));
        fetch(`${BACKEND_API_URL}/display/byPage/${currentPage - 1}`)
            .then(res => res.json())
            .then(data => {
                setDisplays(data)
                setLoading(false);
            });
    }, [currentPage]);

    return (
        <Container sx={{
            marginTop: "40px",
        }}>
            <Typography variant="h3" color="black" align="left">All Displays</Typography>
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
                        <Button variant="outlined" component={Link} to={`/displays/add`}>
                            + Add new display
                        </Button>
                    </ListItem>
                </List>
            )}
            {!loading && (
                <TableContainer component={Paper} >
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
                    <Table>
                        <TableHead
                            sx={{
                            backgroundColor: '#F5F5F5'
                        }}>
                            <TableRow>
                                <TableCell>#</TableCell>
                                <TableCell>
                                    Type
                                </TableCell>
                                <TableCell>
                                    Size
                                </TableCell>
                                <TableCell>
                                    Resolution Width
                                </TableCell>
                                <TableCell>
                                    Resolution Height
                                </TableCell>
                                <TableCell>
                                    Protection
                                </TableCell>
                                <TableCell>
                                    Number of smartphones having this display
                                </TableCell>
                                <TableCell>Operations</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {displays.map((display : Display, index) => (
                                <TableRow key={index}>
                                    <TableCell>{index + 1}</TableCell>
                                    <TableCell>{display.type}</TableCell>
                                    <TableCell>{display.size}</TableCell>
                                    <TableCell>{display.resolutionWidth}</TableCell>
                                    <TableCell>{display.resolutionHeight}</TableCell>
                                    <TableCell>{display.protection}</TableCell>
                                    <TableCell>{display.smartphoneCount}</TableCell>
                                    <TableCell>
                                        <IconButton
                                            component={Link}
                                            sx={{
                                            mr: 3
                                        }}
                                            to={`/displays/delete/${display.id}`}>
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
                                            to={`/displays/update/${display.id}`}>
                                            <Tooltip title="Update" arrow>
                                                <EditIcon/>
                                            </Tooltip>
                                        </IconButton>
                                    </TableCell>
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
                                value={pageVal}
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

export default DisplayGetAll;
