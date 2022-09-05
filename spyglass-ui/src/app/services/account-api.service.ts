import { Injectable, Type } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http'
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
    providedIn: 'root'
})
export class AccountApiService {

    constructor(private http: HttpClient) { }

    login(email: any, password: any): Observable<any> {
        let body = new HttpParams()
            .set('username', email)
            .set('password', password)

        return this.http.post(`${environment.api}/login`, body)
    }

    getUser(email: any): Observable<any> {
        let token = ''
        let headers = new HttpHeaders({
            'Authorization': `Bearer ${token}`
        })
        let options = {headers}
        return this.http.get(`${environment.api}/user/${email}`, options)
    }
}
