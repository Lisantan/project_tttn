import { Department } from "./department";
import { Position } from "./position";

export class Recruit {
    id?: number;
    idEmployee?: number;
    image?: string;
    name?: string;
    gender?: string;
    dob?: string;
    email?: string;
    address?: string;
    addressPermanent?: string;
    ethnic?: string;
    socialCredit?: string;
    professionalKnowledge?: string;    
    salary?: number;
    department?: Department;
    position?: Position;
    status?: string
}
