import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder } from '@angular/forms';
import { AccountApiService } from '../services/account-api.service';
import { AuthApiService } from '../services/auth-api.service';
import { GoalApiService } from '../services/goal-api.service';

import { DashboardComponent } from './dashboard.component';

import { SlideMenuModule } from 'primeng/slidemenu';
import { ButtonModule } from 'primeng/button';
import { ReactiveFormsModule } from '@angular/forms';

describe('DashboardComponent', () => {
    let component: DashboardComponent;
    let fixture: ComponentFixture<DashboardComponent>;

    let mockAccountService = {
        save: () => {
            return { subscribe: () => {} };
        }
    };

    let mockAuthService = {
        save: () => {
            return { subscribe: () => {} };
        }
    };

    let mockGoalService = {
        save: () => {
            return { subscribe: () => {} };
        }
    };

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            providers: [{provide: FormBuilder}, {provide: AccountApiService, useValue: mockAccountService},
                {provide: AuthApiService, useValue: mockAuthService},{provide: GoalApiService, useValue: mockGoalService}],
            declarations: [ DashboardComponent ],
            imports: [
                SlideMenuModule,
                ButtonModule,
                ReactiveFormsModule
            ]
        })
        .compileComponents();

        fixture = TestBed.createComponent(DashboardComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
