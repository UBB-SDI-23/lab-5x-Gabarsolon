import { Display } from "./Display";

export class Smartphone{
    id?: number;
    brand: string = "";
    model: string = "";
    price: number = 0;
    storageCapacity: number = 0;
    launchDate: string = "";
    display: Display = new Display(); 
}