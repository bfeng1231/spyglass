import { HttpClient } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';

import { AccountApiService } from './account-api.service';

describe('AccountApiService', () => {
    let service: AccountApiService;

    let mockHttpClient = {
        get: () => {}
    }

    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [{provide: HttpClient, useValue: mockHttpClient}]
        });
        service = TestBed.inject(AccountApiService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});
