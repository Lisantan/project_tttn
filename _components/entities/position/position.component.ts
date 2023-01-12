import { Component } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Position } from 'src/app/_models/position';
import { PositionService } from 'src/app/_services/entities/position.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import Swal from 'sweetalert2';

const numberRegex : RegExp = /^[0-9]/;

@Component({
  selector: 'app-position',
  templateUrl: './position.component.html',
  styleUrls: ['./position.component.css']
})
export class PositionComponent {
  content?: string;
  entities: Position[] = [];
  entity: Position = new Position();
  currentUser: any;
  functionFlag = false;
  constructor(private tokenService: TokenStorageService, private router: Router, 
    private titleService: Title, private service: PositionService){
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
    if(this.entity.idPos == null){
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Không được bỏ trống "Mã chức vụ"!'
      })
    }
    else if(this.entity.name == null){
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Không được bỏ trống "Tên chức vụ"!'
      })
    }
    else if(!numberRegex.test(this.entity.idPos.toString()) ){
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Sai kiểu dữ liệu "Mã chức vụ"!',
      })
    }
    else{
      Swal.fire({
        icon: 'success',
        title: 'OK!',
        text: 'Thêm mới chức vụ thành công',
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
    this.entity = new Position();
  }

  update(id: any){
    this.functionFlag = true
    
    if(this.entity.idPos == null){
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Không được bỏ trống "Mã chức vụ"!'
      })
    }
    else if(this.entity.name == null){
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Không được bỏ trống "Tên chức vụ"!'
      })
    }
    else if(!numberRegex.test(this.entity.idPos.toString()) ){
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Sai kiểu dữ liệu "Mã chức vụ"!',
      })
    }
    else{
      Swal.fire({
        icon: 'success',
        title: 'OK!',
        text: 'Cập nhật chức vụ thành công',
        timer: 2000
      })
      this.service.update(id, this.entity).subscribe( data => {
        window.setTimeout( function() {
          window.location.reload();
        }, 3000);
      },
      error => console.log(error));
    }
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
