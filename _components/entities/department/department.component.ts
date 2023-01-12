import { Component } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Department } from 'src/app/_models/department';
import { DepartmentService } from 'src/app/_services/entities/department.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import Swal from 'sweetalert2';

const numberRegex : RegExp = /^[0-9]/;
const stringRegex: RegExp = /^[a-z]/;

@Component({
  selector: 'app-department',
  templateUrl: './department.component.html',
  styleUrls: ['./department.component.css']
})
export class DepartmentComponent {
  content?: string;
  entities: Department[] = [];
  entity: Department = new Department();
  currentUser: any;
  functionFlag = false;
  constructor(private tokenService: TokenStorageService, private router: Router, 
    private titleService: Title, private service: DepartmentService){
    this.titleService.setTitle("Chức vụ")
  }

  ngOnInit(): void {
    if(!this.tokenService.getToken()){
      this.router.navigate(['/login']);
    } else {
      this.getList();
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
    if(this.entity.idDept == null){
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Không được bỏ trống "Mã phòng ban"!'
      })
    }
    else if(this.entity.name == null){
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Không được bỏ trống "Tên phòng ban"!'
      })
    }
    else if(this.entity.manager == null){
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Không được bỏ trống "Quản lí"!'
      })
    }
    else if(!numberRegex.test(this.entity.idDept.toString()) ){
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Sai kiểu dữ liệu "Mã phòng ban"!',
      })
    }
    else{
      Swal.fire({
        icon: 'success',
        title: 'OK!',
        text: 'Thêm mới phòng ban thành công',
        timer: 2000
      })
      this.service.create(this.entity).subscribe( data => {
        window.setTimeout( function() {
          window.location.reload();
        }, 3000);
      },
      error => console.log(error));
    }
  }

  changeMode(id: any){
    this.functionFlag = true
    
    this.service.get(id).subscribe(data => {
      this.entity = data;
    })
  }

  changeModeFalse(){
    this.functionFlag = false
    this.entity = new Department();
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
