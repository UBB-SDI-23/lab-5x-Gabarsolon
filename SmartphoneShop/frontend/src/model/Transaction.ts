import { Customer } from "./Customer";
import { Smartphone } from "./Smartphone";

export class Transaction{
    id?: number;
    customer: Customer = new Customer();
    smartphone: Smartphone = new Smartphone();
    quantity: number = 0;
    dateTime: string = "";
}