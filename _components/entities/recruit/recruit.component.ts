import { Component } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Employee } from 'src/app/_models/employee';
import { EmployeeService } from 'src/app/_services/entities/employee.service';
import { RecruitService } from 'src/app/_services/entities/recruit.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-recruit',
  templateUrl: './recruit.component.html',
  styleUrls: ['./recruit.component.css']
})
export class RecruitComponent {
  currentUser: any;
  entities: Employee[] = [];

  constructor(private service: EmployeeService, private router: Router, 
    private tokenService: TokenStorageService, private title: Title){
      this.title.setTitle("Danh sách nhân viên tuyển dụng")
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
