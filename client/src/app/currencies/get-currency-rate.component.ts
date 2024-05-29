import {Component, signal, WritableSignal} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatError, MatFormField, MatLabel} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {MatButton} from "@angular/material/button";
import {CurrenciesHttpService} from "./currencies-http.service";
import {GetCurrentCurrencyValueRequest} from "./currencies-dtos";

@Component({
  selector: 'app-get-currency-rate',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatFormField,
    MatInput,
    MatButton,
    MatLabel,
    MatError
  ],
  templateUrl: './get-currency-rate.component.html'
})
export class GetCurrencyRateComponent {

  protected readonly form: FormGroup;
  protected readonly nameControl: FormControl<string | null>;
  protected readonly currencyControl: FormControl<string | null>;
  protected readonly currencyRate: WritableSignal<number> = signal(0)

  constructor(
    private httpService: CurrenciesHttpService
  ) {
    this.nameControl = new FormControl(null, [Validators.required]);
    this.currencyControl = new FormControl(null, [Validators.required, Validators.pattern('[A-Z]{3}')]);
    this.form = new FormGroup({
      'name': this.nameControl,
      'currency': this.currencyControl
    });
  }

  protected onSubmit() {
    this.httpService.getCurrentCurrencyValue(this.form.value as GetCurrentCurrencyValueRequest).subscribe(response =>
      this.currencyRate.set(response.value)
    );
  }
}
