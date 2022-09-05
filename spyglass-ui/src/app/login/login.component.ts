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
        email: ['', Validators.required],
        password: ['', Validators.required]
    })

    registerForm = this.fb.group({
        firstName: ['', Validators.required],
        lastName: ['', Validators.required],
        dob: ['', Validators.required],
        email: ['', Validators.required, Validators.pattern('[^@ \t\r\n]+@[^@ \t\r\n]+\.[^@ \t\r\n]+')],
        password: ['', Validators.required],
        confirmPassword: ['', Validators.required]
    })

    formType: string = ''

    ngOnInit(): void {
        if (this.router.url === '/register')
            this.formType = 'register'
        else
            this.formType = 'login'
    }

    submitForm() {
        console.log(this.loginForm.value);
        this.service.login(this.loginForm.value.email, this.loginForm.value.password).subscribe({
            next: (resp) => { console.log(resp) },
            error: (err) => { console.log('login failed') }
        })
        // this.service.getUser(this.loginForm.value.email).subscribe({
        //     next: (resp) => { console.log(resp) },
        //     error: (err) => { console.log('whoops') }
        // })
    }

}
