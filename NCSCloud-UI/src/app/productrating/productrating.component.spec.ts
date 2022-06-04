import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductratingComponent } from './productrating.component';

describe('ProductratingComponent', () => {
  let component: ProductratingComponent;
  let fixture: ComponentFixture<ProductratingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductratingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductratingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
