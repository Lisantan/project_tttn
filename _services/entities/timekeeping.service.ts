import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Timekeeping } from 'src/app/_models/timekeeping';

const baseUrl = 'http://localhost:8080/api/timekeepings'

@Injectable({
  providedIn: 'root'
})
export class TimekeepingService {

  constructor(private httpClient: HttpClient) { }

  getAll(): Observable<Timekeeping[]> {
    return this.httpClient.get<Timekeeping[]>(baseUrl);
  }

  get(id: any): Observable<Timekeeping> {
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
