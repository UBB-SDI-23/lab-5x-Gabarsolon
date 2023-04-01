import { Smartphone } from "./Smartphone";

export interface Display{
    id: number;
    type: string;
    size: number;
    resolutionWidth: number;
    resolutionHeight: number;
    protection: string;
    smartphones: Smartphone[];
}