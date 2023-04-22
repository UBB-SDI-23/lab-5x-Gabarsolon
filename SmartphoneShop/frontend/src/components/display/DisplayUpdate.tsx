import { useNavigate, useParams } from "react-router-dom"
import { DisplayForm } from "./DisplayForm";
import { useEffect, useState } from "react";
import { Display } from "../../model/Display";
import { BACKEND_API_URL } from "../../constants";
import axios from "axios";

export const DisplayUpdate = () => {
    const navigate = useNavigate();
    const {id} = useParams();

    useEffect(() => {
        fetch(`${BACKEND_API_URL}/display/${id}`)
            .then(res => res.json())
            .then(data => {
                setDisplay(data);
            })
    }, []);;

    const [display, setDisplay] = useState<Display>({
		type: "",
		size: 0,
		resolutionWidth: 0,
		resolutionHeight: 0,
		protection: ""
	});

	const updateSmartphone = async (event: { preventDefault: () => void }) => {
		event.preventDefault();
		try {
			await axios.put(`${BACKEND_API_URL}/display/${id}`, display);
			navigate("/displays");
		} catch (error) {
			console.log(error);
		}
	};

    return(
        <DisplayForm
            apiCallMethod={updateSmartphone}
            display={display}
            setDisplay={setDisplay}
            btnMsg="Update display"
        />
    )
}