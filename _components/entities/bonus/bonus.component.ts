import { Component } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Bonus } from 'src/app/_models/bonus';
import { Employee } from 'src/app/_models/employee';
import { BonusService } from 'src/app/_services/entities/bonus.service';
import { EmployeeService } from 'src/app/_services/entities/employee.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-bonus',
  templateUrl: './bonus.component.html',
  styleUrls: ['./bonus.component.css']
})
export class BonusComponent {
  content?: string;
  entities: Bonus[] = [];
  employees: Employee[] = [];
  entity: Bonus = new Bonus();
  currentUser: any;
  functionFlag = false;
  constructor(private tokenService: TokenStorageService, private router: Router, private employeeService: EmployeeService,
    private titleService: Title, private service: BonusService){
    this.titleService.setTitle("Khen thưởng - xử phạt")
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
    this.service.getAll().subscribe(data => {
      this.entities = data;
    });
  }

  create(){
    this.service.create(this.entity).subscribe( data => {
      window.location.reload();
    },
    error => console.log(error));
  }

  changeMode(id: any){
    this.functionFlag = true
    
    this.service.get(id).subscribe(data => {
      this.entity = data;
    })
  }

  changeModeFalse(){
    this.functionFlag = false
    this.entity = new Bonus();
  }

  update(id: any){
    this.functionFlag = true
    
    this.service.update(id, this.entity).subscribe(data => {
      window.location.reload()
    })
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
