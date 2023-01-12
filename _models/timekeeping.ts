import { Employee } from "./employee";
import { Timekeepingmonth } from "./timekeepingmonth";

export class Timekeeping {
    id?: number;
    employee?: Employee;
    timeKeepingMonth?: Timekeepingmonth;
    timeCount?: number;
}
