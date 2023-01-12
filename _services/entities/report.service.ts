import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Report } from 'src/app/_models/report';

const baseUrl = 'http://localhost:8080/api'

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  constructor(private http: HttpClient) { }

  getCount(){
    return this.http.get(`${baseUrl}` + "/reports/count");
  }

  upload(reportName: any, file: File): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();

    formData.append('file', file);
    
    const req = new HttpRequest('POST', `${baseUrl}/reports?reportName=` + reportName, formData, {
      reportProgress: true,
      responseType: 'json'
    });

    return this.http.request(req);
  }

  getFiles() {
    return this.http.get(`${baseUrl}/reports`);
  }

  get(id: any): Observable<Report> {
    return this.http.get(`${baseUrl}/reports/`+ id);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/reports/${id}`);
  }
}
