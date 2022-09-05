import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AccountApiService } from '../services/account-api.service';
import { AuthApiService } from '../services/auth-api.service';

@Component({
    selector: 'app-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

    constructor(private router: Router, private accountService: AccountApiService, private authService: AuthApiService) { }

    user: any = {
        firstName: '',
        email: ''
    }

    ngOnInit(): void {
        let access_token: any = localStorage.getItem('access_token')
        try {
            let token = access_token.split('.')
            token = JSON.parse(window.atob(token[1]))
            console.log(token)
            
            this.accountService.getUser(token.sub, access_token).subscribe({
                next: resp => this.user = resp,
                error: () => {
                    console.log('Use refresh')
                    this.authService.refreshToken(localStorage.getItem('refresh_token')).subscribe({
                        next: () => this.accountService.getUser(token.sub, localStorage.getItem('access_token')).subscribe(resp => this.user = resp),
                        error: () => console.log('refresh token expired')
                    })
                }
            })

        } catch {
            this.router.navigate(['login'])
        }
    }

}
