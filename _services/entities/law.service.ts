import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Law } from 'src/app/_models/law';

const baseUrl = 'http://localhost:8080/api'

@Injectable({
  providedIn: 'root'
})

export class LawService {

  constructor(private http: HttpClient) { }

  upload(reportName: any, file: File): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();

    formData.append('file', file);
    
    const req = new HttpRequest('POST', `${baseUrl}/laws?lawName=` + reportName, formData, {
      reportProgress: true,
      responseType: 'json'
    });

    return this.http.request(req);
  }

  getFiles() {
    return this.http.get(`${baseUrl}/laws`);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/laws/${id}`);
  }
}
