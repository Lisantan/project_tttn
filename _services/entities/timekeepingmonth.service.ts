import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Timekeepingmonth } from 'src/app/_models/timekeepingmonth';

const baseUrl = 'http://localhost:8080/api/timekeepingmonths'

@Injectable({
  providedIn: 'root'
})
export class TimekeepingmonthService {

  constructor(private httpClient: HttpClient) { }

  getAll(): Observable<Timekeepingmonth[]> {
    return this.httpClient.get<Timekeepingmonth[]>(baseUrl);
  }

  get(id: any): Observable<Timekeepingmonth> {
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
