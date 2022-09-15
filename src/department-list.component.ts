import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Department } from 'src/app/models/department';
import { Employee } from 'src/app/models/employee';
import { DepartmentService } from 'src/app/services/department.service';

@Component({
  selector: 'app-department-list',
  templateUrl: './department-list.component.html',
  styleUrls: ['./department-list.component.css']
})
export class DepartmentListComponent implements OnInit {

  entities: Department[] = [];

  constructor(private service: DepartmentService,
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
    this.router.navigate(['departments/update', id]);
  }

  delete(id: any){
    this.service.delete(id).subscribe( data => {
      console.log(data);
      this.getList();
    })
  }

  goToCreate(){
    this.router.navigate(['/departments/create']);
  }

  goToEmployeeList(){
    this.router.navigate(['/employees']);
  }
}
