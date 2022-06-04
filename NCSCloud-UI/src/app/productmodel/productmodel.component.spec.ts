import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductmodelComponent } from './productmodel.component';

describe('ProductmodelComponent', () => {
  let component: ProductmodelComponent;
  let fixture: ComponentFixture<ProductmodelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductmodelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductmodelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
