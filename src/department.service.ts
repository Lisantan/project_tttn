import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Department } from '../models/department';

const baseUrl = 'http://localhost:8080/api/departments';

@Injectable({
  providedIn: 'root'
})

export class DepartmentService {

  constructor(private httpClient: HttpClient) { }

  getAll(): Observable<Department[]> {
    return this.httpClient.get<Department[]>(baseUrl);
  }

  get(id: any): Observable<Department> {
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
