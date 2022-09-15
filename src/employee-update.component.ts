import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Employee } from 'src/app/models/employee';
import { EmployeeService } from 'src/app/services/employee.service';

@Component({
  selector: 'app-employee-update',
  templateUrl: './employee-update.component.html',
  styleUrls: ['./employee-update.component.css']
})
export class EmployeeUpdateComponent implements OnInit {

  id?: number;
  entity: Employee = new Employee();
  constructor(private service: EmployeeService,
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
    this.router.navigate(['/employees']);
  }

}
