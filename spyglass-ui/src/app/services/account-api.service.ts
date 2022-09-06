import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http'
import { map, Observable } from 'rxjs';
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

    getUser(email: any, token: any): Observable<any> {
        let headers = new HttpHeaders({
            'Authorization': `Bearer ${token}`
        })
        let options = {headers}
        return this.http.get(`${environment.api}/user/${email}`, options)
            .pipe(map(resp => {
                let temp: any = resp
                delete temp.password
                return temp
            }
        ))
    }
}
