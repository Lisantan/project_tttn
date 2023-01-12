import { Component } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Department } from 'src/app/_models/department';
import { Employee } from 'src/app/_models/employee';
import { Position } from 'src/app/_models/position';
import { DepartmentService } from 'src/app/_services/entities/department.service';
import { EmployeeService } from 'src/app/_services/entities/employee.service';
import { PositionService } from 'src/app/_services/entities/position.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-employee-create',
  templateUrl: './employee-create.component.html',
  styleUrls: ['./employee-create.component.css']
})
export class EmployeeCreateComponent {
  currentUser: any;
  entity: Employee = new Employee();
  departments: Department[] = [];
  positions: Position[] = [];

  constructor(private service: EmployeeService, private router: Router, private positionService: PositionService, private departmentService: DepartmentService,
    private tokenService: TokenStorageService, private title: Title){
      this.title.setTitle("Thêm mới nhân viên")
  }

  ngOnInit(): void {
    if(!this.tokenService.getToken()){
      this.router.navigate(['/login']);
    } else {
      this.departmentService.getAll().subscribe(data => this.departments = data);
      this.positionService.getAll().subscribe(data => this.positions = data);
      this.currentUser = this.tokenService.getUser();
    }
  }

  logout(): void {
    this.tokenService.signOut();
    window.location.reload();
  }

  save(){
    this.service.create(this.entity).subscribe(data => {
      console.log(data);
      this.goToList();
    })
    console.log(this.entity);
  }

  goToList(){
    this.router.navigate(['/employees']);
  }
}
