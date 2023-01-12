import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Department } from 'src/app/_models/department';
import { StorageService } from '../storage.service';
import { TokenStorageService } from '../token-storage.service';

const baseUrl = 'http://localhost:8080/api/departments'

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {
  header: any;
  constructor(private httpClient: HttpClient, private tokenService: TokenStorageService) {
  }

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
