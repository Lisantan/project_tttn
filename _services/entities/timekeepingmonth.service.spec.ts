import { TestBed } from '@angular/core/testing';

import { TimekeepingmonthService } from './timekeepingmonth.service';

describe('TimekeepingmonthService', () => {
  let service: TimekeepingmonthService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TimekeepingmonthService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
