import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, map, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
    providedIn: 'root'
})
export class AuthApiService {

    constructor(private http: HttpClient, private router: Router) { }

    refreshToken(token: any): Observable<any> {
        let options = {headers: new HttpHeaders({
            'Authorization': `Bearer ${token}`
        })}
        return this.http.get(`${environment.api}/auth/token/refresh`, options)
            .pipe(map(resp => {
                let temp: any = resp
                localStorage.setItem('access_token', temp.access_token)
                //console.log(temp.access_token)
            }))
            .pipe(catchError(() => {
                this.router.navigate(['login'])
                localStorage.clear()
                return throwError(() => new Error())
            }))
    }
}
