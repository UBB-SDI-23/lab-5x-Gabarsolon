import { useEffect, useState } from "react";
import { BACKEND_API_URL } from "../../constants";
import { Smartphone } from "../../model/Smartphone";
import { Display } from "../../model/Display";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { DisplayForm } from "./DisplayForm";

export const DisplayAdd = () => {
	const navigate = useNavigate();

	const [display, setDisplay] = useState<Display>({
		type: "",
		size: 0,
		resolutionWidth: 0,
		resolutionHeight: 0,
		protection: ""
	});

	

	const addDisplay = async (event: { preventDefault: () => void }) => {
		event.preventDefault();
		try {
			await axios.post(`${BACKEND_API_URL}/display`, display);
			navigate("/displays");
		} catch (error) {
			console.log(error);
		}
	};

	return(
		<DisplayForm
			apiCallMethod={addDisplay}
			display={display}
			setDisplay={setDisplay}
			btnMsg="Add display"
		/>
	)
};
