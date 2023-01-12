import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Employee } from 'src/app/_models/employee';
import { EmployeeService } from 'src/app/_services/entities/employee.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit{
  currentUser: any;
  entities: Employee[] = [];

  constructor(private service: EmployeeService, private router: Router, 
    private tokenService: TokenStorageService, private title: Title){
      this.title.setTitle("Danh sách nhân viên chính thức")
  }

  ngOnInit(): void {
    if(!this.tokenService.getToken()){
      this.router.navigate(['/login']);
    } else {
      this.getList();
      this.currentUser = this.tokenService.getUser();
    }
  }

  create(){
    this.router.navigate(['employees/create']);
  }

  update(id: any){
    this.router.navigate(['employees/update', id])
  }

  delete(id: any){
    this.service.delete(id).subscribe(data => this.getList());
  }

  getList(){
    this.service.getAll().subscribe(data => {
      this.entities = data;
    });
  }

  logout(): void {
    this.tokenService.signOut();
    window.location.reload();
  }

}
