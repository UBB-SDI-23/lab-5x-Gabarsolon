import { useNavigate, useParams } from "react-router-dom"
import { CustomerForm } from "./CustomerForm";
import { useEffect, useState } from "react";
import { Customer } from "../../model/Customer";
import { BACKEND_API_URL } from "../../constants";
import axios from "axios";

export const CustomerUpdate = () => {
    const navigate = useNavigate();
    const {id} = useParams();

    useEffect(() => {
        fetch(`${BACKEND_API_URL}/customer/${id}`)
            .then(res => res.json())
            .then(data => {
                setCustomer(data);
            })
    }, []);;

    const [customer, setCustomer] = useState<Customer>(new Customer());

	const updateCustomer = async (event: { preventDefault: () => void }) => {
		event.preventDefault();
		try {
			await axios.put(`${BACKEND_API_URL}/customer/${id}`, customer);
			navigate("/customers");
		} catch (error) {
			console.log(error);
		}
	};

    return(
        <CustomerForm
            apiCallMethod={updateCustomer}
            customer={customer}
            setCustomer={setCustomer}
            btnMsg="Update customer"
        />
    )
}