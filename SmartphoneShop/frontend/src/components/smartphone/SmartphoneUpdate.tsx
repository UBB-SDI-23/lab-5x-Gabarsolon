import { useNavigate, useParams } from "react-router-dom"
import { SmartphoneForm } from "./SmartphoneForm"
import { useEffect, useState } from "react";
import { Smartphone } from "../../model/Smartphone";
import { Display } from "../../model/Display";
import { BACKEND_API_URL } from "../../constants";
import axios from "axios";

export const SmartphoneUpdate = () => {
    const navigate = useNavigate();
    const {id} = useParams();

    useEffect(() => {
        fetch(`${BACKEND_API_URL}/smartphone/${id}`)
            .then(res => res.json())
            .then(data => {
                setSmartphone(data);
            })
    }, []);;

    const [smartphone, setSmartphone] = useState<Smartphone>(new Smartphone());

	const updateSmartphone = async (event: { preventDefault: () => void }) => {
		event.preventDefault();
		try {
			console.log(smartphone);
			await axios.put(`${BACKEND_API_URL}/smartphone/${id}`, smartphone);
			navigate("/smartphones");
		} catch (error) {
			console.log(error);
		}
	};

    return(
        <SmartphoneForm
            apiCallMethod={updateSmartphone}
            smartphone={smartphone}
            setSmartphone={setSmartphone}
            btnMsg="Update smartphone"
        />
    )
}