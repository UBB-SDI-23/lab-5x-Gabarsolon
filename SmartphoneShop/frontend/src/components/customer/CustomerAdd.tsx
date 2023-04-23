import { useEffect, useState } from "react";
import { BACKEND_API_URL } from "../../constants";
import { Smartphone } from "../../model/Smartphone";
import { Display } from "../../model/Display";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { CustomerForm } from "./CustomerForm";
import { Customer } from "../../model/Customer";

export const CustomerAdd = () => {
	const navigate = useNavigate();

	const [customer, setCustomer] = useState<Customer>(
        new Customer()
    );

	

	const addCustomer = async (event: { preventDefault: () => void }) => {
		event.preventDefault();
		try {
			await axios.post(`${BACKEND_API_URL}/customer`, customer);
			navigate("/customers");
		} catch (error) {
			console.log(error);
		}
	};

	return(
		<CustomerForm
			apiCallMethod={addCustomer}
			customer={customer}
			setCustomer={setCustomer}
			btnMsg="Add customer"
		/>
	)
};
