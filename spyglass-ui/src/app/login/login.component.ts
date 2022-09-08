import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AccountApiService } from '../services/account-api.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

    router: Router

    constructor(private fb: FormBuilder, router: Router, private service: AccountApiService) { 
        this.router = router
    }

    loginForm = this.fb.group({
        email: ['', Validators.pattern('[^@ \t\r\n]+@[^@ \t\r\n]+\.[^@ \t\r\n]+')],
        password: ['', Validators.required]
    })

    registerForm = this.fb.group({
        firstName: ['', Validators.required],
        lastName: ['', Validators.required],
        dob: ['', Validators.required],
        email: ['', Validators.pattern('[^@ \t\r\n]+@[^@ \t\r\n]+\.[^@ \t\r\n]+')],
        password: ['', Validators.required],
        confirmPassword: ['', Validators.required]
    })

    formType: string = ''

    errorMsg: string = ''

    ngOnInit(): void {
        if (this.router.url === '/register')
            this.formType = 'register'
        else {
            this.formType = 'login'
            if (localStorage.getItem('refresh_token') != null) {
                let token: any = localStorage.getItem('refresh_token')!.split('.')
                token = JSON.parse(window.atob(token[1]))

                if (Date.now() > token.exp)
                    this.router.navigate(['dashboard'])
            }
        }
            
    }

    submitForm() {
        this.errorMsg = ''
        if (this.formType === 'login') {
            this.service.login(this.loginForm.value.email, this.loginForm.value.password).subscribe({
                next: (resp) => { 
                    console.log(resp)
                    localStorage.setItem('access_token', resp.access_token)
                    localStorage.setItem('refresh_token', resp.refresh_token)
                    this.router.navigate(['dashboard'])
                },
                error: () => { this.errorMsg = 'Incorrect email or password' }
            })
        }
        else {
            if (this.registerForm.value.password === this.registerForm.value.confirmPassword){
                this.service.register(this.registerForm.value).subscribe({
                    next: () => {
                        localStorage.clear()
                        this.router.navigate(['login'])
                    },
                    error: () => { this.errorMsg = 'Email not available' }
                })
            }
            else
                this.errorMsg = 'Passwords do not match'
        }
        
    }

}
