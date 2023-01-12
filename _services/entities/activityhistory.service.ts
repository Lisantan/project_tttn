import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Activityhistory } from 'src/app/_models/activityhistory';

const baseUrl = 'http://localhost:8080/api/activities'

@Injectable({
  providedIn: 'root'
})
export class ActivityhistoryService {

  constructor(private httpClient: HttpClient) { }

  getAll(): Observable<Activityhistory[]> {
    return this.httpClient.get<Activityhistory[]>(baseUrl);
  }

  get(id: any): Observable<Activityhistory> {
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
