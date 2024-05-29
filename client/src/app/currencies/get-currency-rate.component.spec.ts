import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetCurrencyRateComponent } from './get-currency-rate.component';

describe('GetCurrencyRateComponent', () => {
  let component: GetCurrencyRateComponent;
  let fixture: ComponentFixture<GetCurrencyRateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GetCurrencyRateComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GetCurrencyRateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
