import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SellerRegisrationComponent } from './seller-regisration.component';

describe('SellerRegisrationComponent', () => {
  let component: SellerRegisrationComponent;
  let fixture: ComponentFixture<SellerRegisrationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SellerRegisrationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SellerRegisrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
