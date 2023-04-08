import { Button, Card, CardActions, Container, IconButton } from "@mui/material";
import {Link, useNavigate, useParams} from "react-router-dom";
import ArrowBackIcon from "@mui/icons-material/ArrowBack"
import axios from "axios";
import { BACKEND_API_URL } from "../../constants";

export const SmartphoneDelete = () => {
    const {id} = useParams();
    const navigate = useNavigate();

    const handleDelete = async (event: { preventDefault: () => void }) => {
        event.preventDefault();
        await axios.delete(`${BACKEND_API_URL}/smartphone/${id}`);
        navigate("/smartphones");
    }

    return ( 
        <Container>
            <Card>
                <IconButton component={Link} to={`/smartphones`}>
                    <ArrowBackIcon/>
                </IconButton>
                Are you sure you want to delete the selected smartphone?
            <CardActions>
               <Button onClick={handleDelete}>Delete it</Button>
               <Button onClick={() => navigate("/smartphones")}>Cancel</Button>
            </CardActions>
            </Card>
        </Container>
    )
}