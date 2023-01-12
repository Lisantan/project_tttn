import { Component } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Employee } from 'src/app/_models/employee';
import { Law } from 'src/app/_models/law';
import { EmployeeService } from 'src/app/_services/entities/employee.service';
import { LawService } from 'src/app/_services/entities/law.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-law',
  templateUrl: './law.component.html',
  styleUrls: ['./law.component.css']
})
export class LawComponent {
  content?: string;
  entities: Law[] = [];
  employees: Employee[] = [];
  entity: Law = new Law();
  currentUser: any;
  functionFlag = false;
  constructor(private tokenService: TokenStorageService, private router: Router, private employeeService: EmployeeService,
    private titleService: Title, private service: LawService){
    this.titleService.setTitle("Nội quy")
  }

  ngOnInit(): void {
    if(!this.tokenService.getToken()){
      this.router.navigate(['/login']);
    } else {
      this.getList();
      this.employeeService.getAll().subscribe(data => this.employees = data);
      this.currentUser = this.tokenService.getUser();
      console.log(this.entities)
    }
  }

  private getList(){
    this.service.getFiles().subscribe(data => {
      // this.entities = data;
    });
  }

  create(){
    // this.service.upload(this.entity.lawName,).subscribe( data => {
    //   window.location.reload();
    // },
    // error => console.log(error));
  }

  changeMode(id: any){
    this.functionFlag = true;
  }

  changeModeFalse(){
    this.functionFlag = false
    this.entity = new Law();
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
