import { Employee } from "./employee";

export class Salary {
    id?: number;
    employee?: Employee;
    taxId?: number;
    tax?: number;
    taxLevel?: string;
    assuranceMedical?: number;
	assuranceSocial?: number;
    assuranceUnemployed?: number;
	assurance?: number;
    unionFee?: number;
}
