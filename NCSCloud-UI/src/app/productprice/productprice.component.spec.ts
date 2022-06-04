import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductpriceComponent } from './productprice.component';

describe('ProductpriceComponent', () => {
  let component: ProductpriceComponent;
  let fixture: ComponentFixture<ProductpriceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductpriceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductpriceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
