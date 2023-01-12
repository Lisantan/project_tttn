import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Employee } from 'src/app/_models/employee';

const baseUrl = 'http://localhost:8080/api/employees'

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private httpClient: HttpClient) { }

  getCount(){
    return this.httpClient.get(`${baseUrl}` + "/count");
  }

  getSalary(){
    return this.httpClient.get(`${baseUrl}` + "/salaryCount");
  }

  getAll(): Observable<Employee[]> {
    return this.httpClient.get<Employee[]>(baseUrl);
  }

  get(id: any): Observable<Employee> {
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
