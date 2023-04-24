import { useEffect, useState } from "react";
import { BACKEND_API_URL } from "../../constants";
import { Smartphone } from "../../model/Smartphone";
import { Display } from "../../model/Display";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { TransactionForm } from "./TransactionForm";
import { Customer } from "../../model/Customer";
import { Transaction } from "../../model/Transaction";

export const TransactionAdd = () => {
	const navigate = useNavigate();

	const [transaction, setTransaction] = useState<Transaction>(
        new Transaction()
    );

	const addTransaction = async (event: { preventDefault: () => void }) => {
		event.preventDefault();
		try {
			console.log(transaction);
			await axios.post(`${BACKEND_API_URL}/transaction`, transaction);
			navigate("/transactions");
		} catch (error) {
			console.log(error);
		}
	};

	return(
		<TransactionForm
			apiCallMethod={addTransaction}
			transaction={transaction}
			setTransaction={setTransaction}
			btnMsg="Add transaction"
		/>
	)
};
