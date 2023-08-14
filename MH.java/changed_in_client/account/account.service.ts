import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  constructor(private http: HttpClient) { }

  private apiUrl = 'http://localhost:8080/accounts';

  getAccountsByUserId(userId: number): Observable<any> {
    const url = `${this.apiUrl}/user/${userId}`;
    return this.http.get<any>(url);
  }

  getAccountTypes(): Observable<any> {
    const url = `${this.apiUrl}/types`;
    return this.http.get<any>(url);
  }
}
