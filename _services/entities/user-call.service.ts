import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/_models/user';
import { TokenStorageService } from '../token-storage.service';

const baseUrl = 'http://localhost:8080/api/users'

@Injectable({
  providedIn: 'root'
})
export class UserCallService {

  header: any;
  constructor(private httpClient: HttpClient, private tokenService: TokenStorageService) {
  }

  getAll(): Observable<User[]> {
    return this.httpClient.get<User[]>(baseUrl);
  }

  get(id: any): Observable<User> {
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
