import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Department } from 'src/app/models/department';
import { Employee } from 'src/app/models/employee';
import { EmployeeService } from 'src/app/services/employee.service';

@Component({
  selector: 'app-employee-create',
  templateUrl: './employee-create.component.html',
  styleUrls: ['./employee-create.component.css']
})
export class EmployeeCreateComponent implements OnInit {

  entity: Employee = new Employee();
  departments: Department[] =[];
  constructor(private service: EmployeeService,
    private router: Router) { }

  ngOnInit(): void {
  }

  save(){
    this.service.create(this.entity).subscribe( data =>{
      console.log(data);
      this.goToList();
    },
    error => console.log(error));
  }

  goToList(){
    this.router.navigate(['/departments']);
  }
  
  onSubmit(){
    console.log(this.entity);
    this.save();
  }

}
