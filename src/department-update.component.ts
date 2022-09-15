import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Department } from 'src/app/models/department';
import { DepartmentService } from 'src/app/services/department.service';
@Component({
  selector: 'app-department-update',
  templateUrl: './department-update.component.html',
  styleUrls: ['./department-update.component.css']
})
export class DepartmentUpdateComponent implements OnInit {

  id?: number;
  entity: Department = new Department();
  constructor(private service: DepartmentService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.service.get(this.id).subscribe(data => {
      this.entity = data;
    }, error => console.log(error));
  }

  onSubmit(){
    this.service.update(this.id, this.entity).subscribe( data =>{
      this.goToList();
    }
    , error => console.log(error));
  }

  goToList(){
    this.router.navigate(['/departments']);
  }
}
