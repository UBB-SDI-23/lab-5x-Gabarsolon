import { useEffect, useState } from "react";
import { BACKEND_API_URL } from "../../constants";
import { Smartphone } from "../../model/Smartphone";
import { Display } from "../../model/Display";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { SmartphoneForm } from "./SmartphoneForm";

export const SmartphoneAdd = () => {
	const navigate = useNavigate();

	const [smartphone, setSmartphone] = useState<Smartphone>({
		brand: "",
		model: "",
		price: 0,
		storageCapacity: 0,
		launchDate: "",
		description: "",
		display: new Display()
	});

	

	const addSmartphone = async (event: { preventDefault: () => void }) => {
		event.preventDefault();
		try {
			console.log(smartphone);
			await axios.post(`${BACKEND_API_URL}/smartphone`, smartphone);
			navigate("/smartphones");
		} catch (error) {
			console.log(error);
		}
	};

	return(
		<SmartphoneForm
			apiCallMethod={addSmartphone}
			smartphone={smartphone}
			setSmartphone={setSmartphone}
			btnMsg="Add smartphone"
		/>
	)
};
