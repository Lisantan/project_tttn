import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Recruit } from 'src/app/_models/recruit';

const baseUrl = 'http://localhost:8080/api/recruits'
@Injectable({
  providedIn: 'root'
})
export class RecruitService {

  constructor(private httpClient: HttpClient) { }

  getAll(): Observable<Recruit[]> {
    return this.httpClient.get<Recruit[]>(baseUrl);
  }

  getCount(){
    return this.httpClient.get(`${baseUrl}` + "/count");
  }

  get(id: any): Observable<Recruit> {
    return this.httpClient.get(`${baseUrl}/${id}`);
  }

  create(data: any): Observable<any> {
    return this.httpClient.post(baseUrl, data);
  }

  update(id: any, data: any): Observable<any> {
    return this.httpClient.put(`${baseUrl}/${id}`, data);
  }

  delete(id: any): Observable<any> {
    return this.httpClient.delete(`${baseUrl}/${id}`);
  }
}
