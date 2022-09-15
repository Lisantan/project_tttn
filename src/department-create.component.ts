import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Department } from 'src/app/models/department';
import { DepartmentService } from 'src/app/services/department.service';

@Component({
  selector: 'app-department-create',
  templateUrl: './department-create.component.html',
  styleUrls: ['./department-create.component.css']
})
export class DepartmentCreateComponent implements OnInit {

  entity: Department = new Department();
  constructor(private service: DepartmentService,
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

  goToCreate(){
    this.router.navigate(['/departments/create']);
  }

}
