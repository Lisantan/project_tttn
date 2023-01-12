import { Component } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { User } from 'src/app/_models/user';
import { UserCallService } from 'src/app/_services/entities/user-call.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-account-list',
  templateUrl: './account-list.component.html',
  styleUrls: ['./account-list.component.css']
})
export class AccountListComponent {
  content?: string;
  entities: User[] = [];
  currentUser: any;
  constructor(private tokenService: TokenStorageService, private service: UserCallService, private router: Router, private titleService: Title){
    this.titleService.setTitle("Danh sách tài khoản")
  }

  ngOnInit(): void {
    if(!this.tokenService.getToken()){
      this.router.navigate(['/login']);
    } else {
      this.getList();
      this.currentUser = this.tokenService.getUser();
    }
  }

  private getList(){
    this.service.getAll().subscribe(data => {
      this.entities = data;
    });
  }

  create(){
    this.router.navigate(['/register'])
  }

  update(id: any){
    this.router.navigate(['departments/edit/', id])
  }

  delete(id: any){
    this.service.delete(id).subscribe( data => {
      this.getList();
    })
  }

  logout(): void {
    this.tokenService.signOut();
    window.location.reload();
  }
}
