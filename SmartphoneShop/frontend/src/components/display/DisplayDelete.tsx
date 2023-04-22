import { Button, Card, CardActions, Container, IconButton } from "@mui/material";
import {Link, useNavigate, useParams} from "react-router-dom";
import ArrowBackIcon from "@mui/icons-material/ArrowBack"
import axios from "axios";
import { BACKEND_API_URL } from "../../constants";

export const DisplayDelete = () => {
    const {id} = useParams();
    const navigate = useNavigate();

    const handleDelete = async (event: { preventDefault: () => void }) => {
        event.preventDefault();
        await axios.delete(`${BACKEND_API_URL}/display/${id}`);
        navigate("/displays");
    }

    return ( 
        <Container>
            <Card>
                <IconButton component={Link} to={`/displays`}>
                    <ArrowBackIcon/>
                </IconButton>
                Are you sure you want to delete the selected display?
            <CardActions>
               <Button onClick={handleDelete}>Delete it</Button>
               <Button onClick={() => navigate("/displays")}>Cancel</Button>
            </CardActions>
            </Card>
        </Container>
    )
}