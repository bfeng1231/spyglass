import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AccountApiService } from '../services/account-api.service';
import { AuthApiService } from '../services/auth-api.service';
import { GoalApiService } from '../services/goal-api.service';

@Component({
    selector: 'app-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

    constructor(private router: Router, private accountService: AccountApiService, private authService: AuthApiService, private fb: FormBuilder,
        private goalService: GoalApiService) { }

    user: any = {
        firstName: '',
        email: ''
    }

    modal: string = ''
    goalId: number = 0

    goalForm: any

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

    initializeForm() {
        this.modal = 'new'
        this.goalForm = this.fb.group({
            id: [''],
            name: ['', Validators.required],
            description: [''],
            picture: [''],
            targetDate: ['', Validators.required],
            targetAmount: ['', Validators.required],
            currentAmount: ['']
        })
    }

    submitForm() {
        if (this.modal === 'new') {
            this.goalService.save(this.user.id, this.goalForm.value, localStorage.getItem('access_token')).subscribe({
                next: resp => this.user.goals.push(resp),
                error: () => {
                    console.log('Use refresh')
                    this.authService.refreshToken(localStorage.getItem('refresh_token')).subscribe({
                        next: () => this.goalService.save(this.user.id, this.goalForm.value, localStorage.getItem('access_token')).subscribe(resp => this.user.goal.push(resp)),
                        error: () => console.log('refresh token expired')
                    })
                }
            })
        }
        else {
            this.goalService.update(this.user.id, this.goalForm.value, localStorage.getItem('access_token')).subscribe({
                next: resp => {},
                error: () => {
                    console.log('Use refresh')
                    console.log(this.goalForm.value)
                    this.authService.refreshToken(localStorage.getItem('refresh_token')).subscribe({
                        next: () => this.goalService.update(this.user.id, this.goalForm.value, localStorage.getItem('access_token')).subscribe(resp => this.user.goal.push(resp)),
                        error: () => console.log('refresh token expired')
                    })
                }
            })
        }
       
    }

    prefillData(goal: any) {
        this.modal = 'edit'
        this.goalForm = this.fb.group({
            id: [goal.id],
            name: [goal.name, Validators.required],
            description: [goal.description],
            picture: [goal.picture],
            targetDate: [goal.targetDate, Validators.required],
            targetAmount: [goal.targetAmount, Validators.required],
            currentAmount: [goal.currentAmount]
        })
    }
}
