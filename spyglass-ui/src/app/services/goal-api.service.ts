import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
    providedIn: 'root'
})
export class GoalApiService {

    constructor(private http: HttpClient) { }

    save(id: number, body: any, token: any): Observable<any> {
        delete body.id
        body = {
            ...body,
            targetDate: body.targetDate.toISOString().substring(0, 10),
            currentAmount: 0,
            account: {id}
        }

        let options = {headers: new HttpHeaders({
            'Authorization': `Bearer ${token}`
        })}

        return this.http.post(`${environment.api}/goal`, body, options) 
    }

    update(accountId: number, body: any, token: any): Observable<any> {
        let goalId = body.id
        
        body = {
            ...body,
            targetDate: body.targetDate.toISOString().substring(0, 10),
            account: {id: accountId}
        }
        delete body.id

        let options = {headers: new HttpHeaders({
            'Authorization': `Bearer ${token}`
        })}

        return this.http.put(`${environment.api}/goal/${goalId}`, body, options) 
    }

    delete(id: number, token: any) {
        let options = {headers: new HttpHeaders({
            'Authorization': `Bearer ${token}`
        })}

        return this.http.delete(`${environment.api}/goal/${id}`, options) 
    }
}
