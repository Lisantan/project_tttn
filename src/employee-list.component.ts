import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from 'src/app/models/employee';
import { EmployeeService } from 'src/app/services/employee.service';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

  entities: Employee[] = [];

  constructor(private service: EmployeeService,
    private router: Router) { }

  ngOnInit(): void {
    this.getList();
  }

  private getList(){
    this.service.getAll().subscribe(data => {
      this.entities = data;
    });
  }

  update(id: any){
    this.router.navigate(['employees/update', id]);
  }

  delete(id: any){
    this.service.delete(id).subscribe( data => {
      console.log(data);
      this.getList();
    })
  }

  goToCreate(){
    this.router.navigate(['employees/create']);
  }

  goToDepartmentList(){
    this.router.navigate(['departments']);
  }
}
