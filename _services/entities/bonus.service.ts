import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Bonus } from 'src/app/_models/bonus';

const baseUrl = 'http://localhost:8080/api/bonuses'

@Injectable({
  providedIn: 'root'
})
export class BonusService {

  constructor(private httpClient: HttpClient) { }

  getAll(): Observable<Bonus[]> {
    return this.httpClient.get<Bonus[]>(baseUrl);
  }

  get(id: any): Observable<Bonus> {
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
