import { TestBed } from '@angular/core/testing';

import { GoalApiService } from './goal-api.service';
import { HttpClient } from '@angular/common/http';

describe('GoalApiService', () => {
    let service: GoalApiService;

    let mockHttpClient = {
        get: () => {}
    }

    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [{provide: HttpClient, useValue: mockHttpClient}]
        });
        service = TestBed.inject(GoalApiService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});
