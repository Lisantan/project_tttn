import { TestBed } from '@angular/core/testing';

import { ActivityhistoryService } from './activityhistory.service';

describe('ActivityhistoryService', () => {
  let service: ActivityhistoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ActivityhistoryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
