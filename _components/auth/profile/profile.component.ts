import { Component } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { TimekeepingmonthService } from 'src/app/_services/entities/timekeepingmonth.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
  content?: string;
  currentUser: any;
  currentToken: any;
  constructor(private tokenService: TokenStorageService, private service: TimekeepingmonthService, private router: Router, private titleService: Title){
    this.titleService.setTitle("Trang cá nhân")
  }

  ngOnInit(): void {
    if(!this.tokenService.getToken()){
      this.router.navigate(['/login']);
    } else {
      this.currentUser = this.tokenService.getUser();
      this.currentToken = this.tokenService.getToken();
    }
  }
}
