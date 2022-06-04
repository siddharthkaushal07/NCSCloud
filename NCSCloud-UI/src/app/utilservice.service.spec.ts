import { TestBed } from '@angular/core/testing';

import { UtilserviceService } from './utilservice.service';

describe('UtilserviceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UtilserviceService = TestBed.get(UtilserviceService);
    expect(service).toBeTruthy();
  });
});
