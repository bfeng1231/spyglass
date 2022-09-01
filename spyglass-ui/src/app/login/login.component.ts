import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

    router: Router

    constructor(private fb: FormBuilder, router: Router) { 
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
        email: ['', Validators.required],
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
    }

}
