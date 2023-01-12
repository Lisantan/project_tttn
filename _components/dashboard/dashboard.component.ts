import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Report } from 'src/app/_models/report';
import { EmployeeService } from 'src/app/_services/entities/employee.service';
import { ReportService } from 'src/app/_services/entities/report.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  currentUser: any;
  content?: string;
  entities: any;
  entity: Report = new Report();
  functionFlag = false;
  selectedFiles?: FileList;
  currentFile?: File;
  message = '';

  fileInfos?: Observable<Report>;

  constructor(private tokenService: TokenStorageService, private router: Router,
    private titleService: Title, private service: ReportService){
    this.titleService.setTitle("Nội quy")
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
    this.service.getFiles().subscribe(data => {
      this.entities = data;
      console.log(this.entities)
    });
  }

  selectFile(event: any): void {
    this.selectedFiles = event.target.files;
  }

  upload(): void {

    if (this.selectedFiles) {
      const file: File | null = this.selectedFiles.item(0);

      if (file) {
        this.currentFile = file;

        this.service.upload(this.entity.name, this.currentFile).subscribe({
          next: (event: any) => {
            if (event.type === HttpEventType.UploadProgress) {
            } else if (event instanceof HttpResponse) {
              this.message = event.body.message;
              this.fileInfos = this.service.getFiles();
            }
          },
          error: (err: any) => {
            console.log(err);

            if (err.error && err.error.message) {
              this.message = err.error.message;
            } else {
              this.message = 'Could not upload the file!';
            }

            this.currentFile = undefined;
          }
        });
      }

      this.selectedFiles = undefined;
    }
    window.location.reload();
  }

  delete(id: any){
    this.service.delete(id).subscribe( data => {
    })
    window.location.reload();
  }

  logout(): void {
    this.tokenService.signOut();
    window.location.reload();
  }

}
