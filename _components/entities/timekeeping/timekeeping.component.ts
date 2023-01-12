import { Component } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Employee } from 'src/app/_models/employee';
import { Timekeeping } from 'src/app/_models/timekeeping';
import { Timekeepingmonth } from 'src/app/_models/timekeepingmonth';
import { EmployeeService } from 'src/app/_services/entities/employee.service';
import { TimekeepingService } from 'src/app/_services/entities/timekeeping.service';
import { TimekeepingmonthService } from 'src/app/_services/entities/timekeepingmonth.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-timekeeping',
  templateUrl: './timekeeping.component.html',
  styleUrls: ['./timekeeping.component.css']
})
export class TimekeepingComponent {
  content?: string;
  entities: Timekeeping[] = [];
  employees: Employee[] = [];
  timeKeepingMonths: Timekeepingmonth[] = [];
  entity: Timekeeping = new Timekeeping();
  entityMonth: Timekeepingmonth = new Timekeepingmonth();
  currentUser: any;
  functionFlag = false;
  constructor(private tokenService: TokenStorageService, private router: Router, private employeeService: EmployeeService,
    private titleService: Title, private service: TimekeepingService, private monthService: TimekeepingmonthService){
    this.titleService.setTitle("Chấm công")
  }

  ngOnInit(): void {
    if(!this.tokenService.getToken()){
      this.router.navigate(['/login']);
    } else {
      this.getList();
      this.employeeService.getAll().subscribe(data => this.employees = data);
      this.monthService.getAll().subscribe(data => this.timeKeepingMonths = data);
      this.currentUser = this.tokenService.getUser();
      console.log(this.entities)
    }
  }

  private getList(){
    this.service.getAll().subscribe(data => {
      this.entities = data;
    });
  }

  create(){
    this.service.create(this.entity).subscribe( data => {
      window.location.reload();
    },
    error => console.log(error));
  }

  createMonth(){
    this.monthService.create(this.entityMonth).subscribe( data => {
      window.location.reload();
    },
    error => console.log(error));
  }

  changeMode(id: any){
    this.functionFlag = true
    
    this.service.get(id).subscribe(data => {
      this.entity = data;
      console.log(data);
    })
  }

  changeModeFalse(){
    this.functionFlag = false
    this.entity = new Timekeeping();
  }

  update(id: any){
    this.functionFlag = true
    
    this.service.update(id, this.entity).subscribe(data => {
      window.location.reload()
    })
  }

  delete(id: any){
    this.service.delete(id).subscribe( data => {
      window.location.reload();
    })
  }

  logout(): void {
    this.tokenService.signOut();
    window.location.reload();
  }

  save(){
    if(this.functionFlag = false){
      this.create();
      console.log(this.entity)
      window.location.reload();
    }
  }
}
