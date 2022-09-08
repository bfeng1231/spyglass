import { TestBed } from '@angular/core/testing';

import { AuthApiService } from './auth-api.service';
import { HttpClient } from '@angular/common/http';

describe('AuthApiService', () => {
    let service: AuthApiService;

    let mockHttpClient = {
        get: () => {}
    }

    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [{provide: HttpClient, useValue: mockHttpClient}]
        });
        service = TestBed.inject(AuthApiService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});
