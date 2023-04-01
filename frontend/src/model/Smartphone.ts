import { Display } from "./Display";

export interface Smartphone{
    id: number;
    brand: string;
    model: string;
    price: number;
    storageCapacity: number;
    launchDate: string;
    display: Display;
}