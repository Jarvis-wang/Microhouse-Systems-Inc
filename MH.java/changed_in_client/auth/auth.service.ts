import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, map } from 'rxjs/operators';

import { JwtResponse } from './jwt-response';
import { AuthLoginInfo } from './login-info';
import { SignUpInfo } from './signup-info';
import { TokenStorageService } from './token-storage.service'; // Assuming you have this service

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loginUrl = 'http://localhost:8080/api/auth/signin';
  private signupUrl = 'http://localhost:8080/api/auth/signup';
  private userIdUrl = 'http://localhost:8080/api/auth/userId';

  constructor(private http: HttpClient, private tokenStorage: TokenStorageService) {
  }

  attemptAuth(credentials: AuthLoginInfo): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(this.loginUrl, credentials, httpOptions).pipe(
      tap((res: JwtResponse) => {
        this.tokenStorage.saveToken(res.accessToken);
        this.tokenStorage.saveUsername(res.username);
        this.tokenStorage.saveAuthorities(res.authorities);
        this.tokenStorage.saveUserId(res.userId); // save the user ID
      })
    );
  }

  signUp(info: SignUpInfo): Observable<string> {
    return this.http.post<string>(this.signupUrl, info, httpOptions);
  }

  public getCurrentUserId(): Observable<number> {
    return this.http.get<{userId: number}>(this.userIdUrl, httpOptions).pipe(
      map(response => response.userId)
    );
  }
}
