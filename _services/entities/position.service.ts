import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Position } from 'src/app/_models/position';
import { TokenStorageService } from '../token-storage.service';

const baseUrl = 'http://localhost:8080/api/positions'

@Injectable({
  providedIn: 'root'
})
export class PositionService {

  header: any;
  constructor(private httpClient: HttpClient, private tokenService: TokenStorageService) {
    this.header = new HttpHeaders().set(
      "Authorization",
       "Bearer " + this.tokenService.getUser().token
    );
  }

  getAll(): Observable<Position[]> {
    return this.httpClient.get<Position[]>(baseUrl);
  }

  get(id: any): Observable<Position> {
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
