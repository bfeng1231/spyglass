import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { FooterComponent } from '../footer/footer.component';
import { NavbarComponent } from '../navbar/navbar.component';
import { AccountApiService } from '../services/account-api.service';

import { LoginComponent } from './login.component';

describe('LoginComponent', () => {
    let component: LoginComponent;
    let fixture: ComponentFixture<LoginComponent>;
    //let service: AccountApiService

    let mockAccountService = {
        login: () => {
          return { subscribe: () => {} };
        }
    };

    beforeEach(async () => {
        await TestBed.configureTestingModule({
        providers: [{provide: FormBuilder}, {provide: AccountApiService, useValue: mockAccountService}],
        declarations: [ LoginComponent, NavbarComponent, FooterComponent ],
        imports: [
            ReactiveFormsModule
        ]
        })
        .compileComponents();

        fixture = TestBed.createComponent(LoginComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();

        let fb = new FormBuilder
        
        component.loginForm = fb.group({
            email: ['test@gmail.com', Validators.pattern('[^@ \t\r\n]+@[^@ \t\r\n]+\.[^@ \t\r\n]+')],
            password: ['1234', Validators.required]
        })

        component.registerForm = fb.group({
            firstName: ['Bob', Validators.required],
            lastName: ['Rob', Validators.required],
            dob: ['1990-05-04', Validators.required],
            email: ['bob@gmail.com', Validators.pattern('[^@ \t\r\n]+@[^@ \t\r\n]+\.[^@ \t\r\n]+')],
            password: ['1234', Validators.required],
            confirmPassword: ['1234', Validators.required]
        })
    
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

    it('should log in the user', () => {
        component.formType = 'login'
        component.submitForm()
        let service = fixture.debugElement.injector.get(AccountApiService)
       
        spyOn(service, 'login').and.callThrough()
        expect(service.login).toHaveBeenCalled();
    })
});
